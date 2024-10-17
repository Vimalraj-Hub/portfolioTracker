package in.codifi.portfolio.Dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.sql.DataSource;

import in.codifi.portfolio.entity.MasterDataEntity;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class MasterDataDao {
	@Inject
	DataSource dataSource;
	@Inject
	EntityManager entityManager;

	/**
	 * method to insert file details
	 * 
	 * @author Mugilan
	 * @return
	 */
	public void insertFileDetails(String fileName, LocalDateTime downloadTime, long numberOfLines) {
		Connection conn = null;
		PreparedStatement countStmt = null;

		try {
			conn = dataSource.getConnection();
			long count = numberOfLines;

			// Prepare the statement
			countStmt = conn.prepareStatement(
					"INSERT INTO tbl_file_upload_details(file_name, module, row_count, file_upload_time) VALUES (?, ?, ?, ?)");
			int paramPos = 1;
			countStmt.setString(paramPos++, fileName);
			countStmt.setString(paramPos++, "Admin");
			countStmt.setLong(paramPos++, count);
			countStmt.setTimestamp(paramPos++, Timestamp.valueOf(downloadTime));

			countStmt.executeUpdate();
			Log.info("Success");

		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
		} finally {
			try {
			} finally {
				try {
					if (countStmt != null) {
						countStmt.close();
					}
					if (conn != null) {
						conn.close();
					}

				} catch (Exception e) {
					e.printStackTrace();
					Log.error(e.getMessage());
				}
			}
		}
	}

	@Transactional
	public void insertCsvData(String[] data) {
		try {
			// Parsing each column from the CSV data
			String NSEcode = data[0];
			String BSEcode = data[1];
			String ISIN = data[2];
			String fullName = data[3];
			String industryName = data[4];
			int industryId = Integer.parseInt(data[5]);
			String sectorName = data[6];
			int sectorId = Integer.parseInt(data[7]);
			BigDecimal TL_SCORE = new BigDecimal(data[8]);

			// SQL Insert statement
			String sql = "INSERT INTO tbl_scrip_scores (NSEcode, BSEcode, ISIN, full_name, industry_name, industry_id, sector_name, sector_id, TL_SCORE) "
					+ "VALUES (:NSEcode, :BSEcode, :ISIN, :fullName, :industryName, :industryId, :sectorName, :sectorId, :TL_SCORE)";

			entityManager.createNativeQuery(sql).setParameter("NSEcode", NSEcode).setParameter("BSEcode", BSEcode)
					.setParameter("ISIN", ISIN).setParameter("fullName", fullName)
					.setParameter("industryName", industryName).setParameter("industryId", industryId)
					.setParameter("sectorName", sectorName).setParameter("sectorId", sectorId)
					.setParameter("TL_SCORE", TL_SCORE).executeUpdate();

			Log.info("Data inserted successfully for NSEcode: " + NSEcode);

		} catch (Exception e) {
			Log.error("Error inserting CSV data: ", e);
		}
	}

	@Transactional
	public void archiveMasterData() {
		 try {
		        // Check if there is data in the master data table
		        String countQuery = "SELECT COUNT(*) FROM tbl_master_data";
		        long count = ((Number) entityManager.createNativeQuery(countQuery).getSingleResult()).longValue();

		        // If no data exists, skip the archiving process
		        if (count == 0) {
		            System.out.println("No data found in tbl_master_data to archive.");
		            return; // Exit the method without archiving or deleting
		        }

		        // Archive data by inserting into the archive table, filtering out invalid entries
		        String archiveQuery = "INSERT INTO tbl_master_data_archive "
		                + "SELECT * FROM tbl_master_data WHERE SECTOR_ID IS NOT NULL AND SECTOR_ID REGEXP '^[0-9]+$'";
		        entityManager.createNativeQuery(archiveQuery).executeUpdate();

		        // Optionally, delete the data from the master table after archiving
		        String deleteQuery = "DELETE FROM tbl_master_data";
		        entityManager.createNativeQuery(deleteQuery).executeUpdate();

		    } catch (NoResultException e) {
		        // Handle case when the count query returns no result
		        System.out.println("No data found to archive.");
		    } catch (Exception e) {
		        // Handle any other exceptions
		        throw new RuntimeException("Failed to archive old master data.", e);
		    }
	}

	@Transactional
	public void truncateMasterData() {
		String truncateQuery = "TRUNCATE TABLE tbl_master_data";
		entityManager.createNativeQuery(truncateQuery).executeUpdate();
	}

	@Transactional
	public void insertMasterData(MasterDataEntity masterData) {
		entityManager.persist(masterData);
	}
}
