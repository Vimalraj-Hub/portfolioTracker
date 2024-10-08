package in.codifi.portfolio.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import org.apache.camel.builder.RouteBuilder;
import org.jboss.resteasy.reactive.RestResponse;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import in.codifi.portfolio.Dao.MasterDataDao;
import in.codifi.portfolio.config.HazelcastConfig;
import in.codifi.portfolio.entity.MasterDataEntity;
import in.codifi.portfolio.entity.SftpConfigEntity;
import in.codifi.portfolio.model.response.GenericResponse;
import in.codifi.portfolio.repository.SftpConfigRepo;
import in.codifi.portfolio.service.spec.MasterDataDownloadServiceSpec;
import in.codifi.portfolio.utility.AppConstants;
import in.codifi.portfolio.utility.EmailUtils;
import in.codifi.portfolio.utility.PrepareResponse;
import in.codifi.portfolio.utility.StringUtil;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MasterDataDownloadService extends RouteBuilder implements MasterDataDownloadServiceSpec {

	@Inject
	MasterDataDao masterDataDao;
	@Inject
	SftpConfigRepo sftpConfigRepo;
	@Inject
	PrepareResponse prepareResponse;
	@Inject
	EmailUtils emailUtils;

	/**
	 * Method to connect to sftp server and download the data
	 * 
	 * @author vimal
	 * @throws Exception
	 */
	@Override
	public RestResponse<GenericResponse> getFileFromSftp() {
		String host = getProperty("sftp.config.server");
		int port = Integer.valueOf(getProperty("sftp.config.port"));
		String username = getProperty("sftp.config.username");
		String password = getProperty("sftp.config.password");
		String remoteDirectoryPath = getProperty("sftp.config.remotepath");
		String localDirectoryPath = getProperty("sftp.config.localpath");
		boolean status = false;
		Session session = null;
		ChannelSftp channelSftp = null;

		try {
			JSch jsch = new JSch();
			session = jsch.getSession(username, host, port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.connect();

			channelSftp = (ChannelSftp) session.openChannel("sftp");
			channelSftp.connect();

			// Construct the full remote path for the CSV file
			String remoteFilePath = remoteDirectoryPath + "/master_data.csv";
			Log.info("Remote file path: " + remoteFilePath);

			// Delete the old file if it exists in the local directory
			File folder = new File(localDirectoryPath);
			File[] listOfFiles = folder.listFiles();
			if (listOfFiles != null) {
				for (File oldFile : listOfFiles) {
					if (oldFile.isFile() && oldFile.getName().equals("master_data.csv")) {
						oldFile.delete();
					}
				}
			}

			// Download the new CSV file
			String localFilePath = localDirectoryPath + "master_data.csv";
			try {
				channelSftp.get(remoteFilePath, localFilePath);
				Log.info("File downloaded: " + localFilePath);
			} catch (SftpException e) {
				Log.error("Failed to download master_data.csv from SFTP server.", e);
				emailUtils.failureEmail("Failed to download master_data.csv from SFTP server.");
				return prepareResponse.prepareFailedResponse("File download failed.");
			}

			// Count the number of lines in the CSV file
			long numberOfLines = 0;
			try {
				numberOfLines = countLines(localFilePath);
				Log.info("File master_data.csv downloaded with " + numberOfLines + " lines.");
			} catch (IOException e) {
				Log.error("Failed to read the downloaded master_data.csv.", e);
				emailUtils.failureEmail("Failed to read the downloaded master_data.csv file.");
				return prepareResponse.prepareFailedResponse("File read failed.");
			}

			// Insert file details into the database
			masterDataDao.insertFileDetails("master_data.csv", LocalDateTime.now(), numberOfLines);

			// Process the CSV file (e.g., parse and store data in the DB)
			processCsvFile(localFilePath);

			status = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (channelSftp != null) {
				channelSftp.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		}

		return prepareResponse.prepareSuccessResponseObject(status);
	}

	/**
	 * Helper method to count lines in a CSV file.
	 */
	private long countLines(String filePath) throws IOException {
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			return stream.count();
		}
	}

	/**
	 * Method to process the CSV file and store its content into the database.
	 */
	@Transactional
	public void processCsvFile(String localFilePath) throws IOException {
		 // Archive existing data
	    archiveMasterData();

	    // Clear the existing data from the main table
	    masterDataDao.truncateMasterData();

	    // Parse the CSV file and insert new data
	    try (BufferedReader br = new BufferedReader(new FileReader(localFilePath))) {
	        String line;
	        while ((line = br.readLine()) != null) {
	            // Skip the header row
	            if (line.startsWith("NSEcode")) {
	                continue;
	            }

	            // Split the CSV line into fields, handle missing fields by filling with empty strings
	            String[] fields = line.split(",", -1);

	            // Create a new MasterDataEntity object and set its fields
	            MasterDataEntity masterData = new MasterDataEntity();
	            masterData.setNseCode(!fields[0].isEmpty() ? fields[0] : "");  // Empty string if missing
	            masterData.setBseCode(!fields[1].isEmpty() ? fields[1] : "");  // Empty string if missing
	            masterData.setIsin(!fields[2].isEmpty() ? fields[2] : "");     // Empty string if missing
	            masterData.setFullName(!fields[3].isEmpty() ? fields[3] : ""); // Empty string if missing
	            masterData.setIndustryName(!fields[4].isEmpty() ? fields[4] : "");  // Empty string if missing

	            // Handle invalid or missing numbers for industryId and sectorId
	            try {
	                masterData.setIndustryId(Integer.parseInt(fields[5]));
	            } catch (NumberFormatException e) {
	                masterData.setIndustryId(0); // Default value of 0 if missing or invalid
	            }

	            masterData.setSectorName(!fields[6].isEmpty() ? fields[6] : "");  // Empty string if missing

	            try {
	                masterData.setSectorId(Integer.parseInt(fields[7]));
	            } catch (NumberFormatException e) {
	                masterData.setSectorId(0); // Default value of 0 if missing or invalid
	            }

	            // Handle missing or invalid TL_SCORE
	            if (!fields[8].isEmpty()) {
	                try {
	                    masterData.setTlScore(new BigDecimal(fields[8]));
	                } catch (NumberFormatException e) {
	                    masterData.setTlScore(BigDecimal.ZERO); // Default score of 0 if invalid
	                }
	            } else {
	                masterData.setTlScore(BigDecimal.ZERO); // Default score of 0 if missing
	            }

	            // Insert the new data into the database
	            masterDataDao.insertMasterData(masterData);
	        }
	    } catch (Exception e) {
	        Log.error("Failed to process CSV file.", e);
	        emailUtils.failureEmail("Failed to process the master data CSV file.");
	        throw e;
	    }
	}

	private void archiveMasterData() {
		try {
			// Move the existing data from the main table to the archive table
			masterDataDao.archiveMasterData();
			Log.info("Old data archived successfully.");
		} catch (Exception e) {
			Log.error("Failed to archive old master data.", e);
			emailUtils.failureEmail("Failed to archive the master data in the database.");
			throw e;
		}
	}

	/**
	 * Method to get the property from cache if not from DB
	 * 
	 * @author vimal
	 * @param key
	 * @return
	 */
	@Transactional
	public String getProperty(String key) {
		if (HazelcastConfig.getInstance().getApplicationProperties().get(key) != null) {
			String property = HazelcastConfig.getInstance().getApplicationProperties().get(key);
			return property != null ? property : null;
		} else {
			SftpConfigEntity property = sftpConfigRepo.findByPropertyKey(key);
			HazelcastConfig.getInstance().getApplicationProperties().put(key, property.getPropertyKey());
			return property != null ? property.getPropertyValue() : null;
		}
	}

	/**
	 * Method to reloda the application proepreti cacehe
	 * 
	 * @author vimal
	 * @return
	 */
	@Override
	public RestResponse<GenericResponse> reloadAppCache() {
		try {
			List<SftpConfigEntity> properties = sftpConfigRepo.findAll();
			if (StringUtil.isListNotNullOrEmpty(properties)) {
				properties.forEach(values -> HazelcastConfig.getInstance().getApplicationProperties()
						.put(values.getPropertyKey(), values.getPropertyValue()));
				return prepareResponse.prepareSuccessMessage(AppConstants.SUCCESS_STATUS);
			} else {
				return prepareResponse.prepareFailedResponse(AppConstants.PARAMETER_NULL);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
		}
		return prepareResponse.prepareFailedResponse(AppConstants.FAILED_STATUS);
	}

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

	}

}
