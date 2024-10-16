package in.codifi.portfolio.service.spec;

import org.jboss.resteasy.reactive.RestResponse;

import in.codifi.portfolio.model.response.GenericResponse;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface MasterDataDownloadServiceSpec {



	/**
	 * Method to reloda the application proepreti cacehe
	 * 
	 * @author vimal
	 * @return
	 */
	RestResponse<GenericResponse> reloadAppCache();

	/**
	 * Method to connect to sftp server and download the data
	 * 
	 * @author vimal
	 * @throws Exception
	 */
	RestResponse<GenericResponse> getFileFromSftp();

	/**
	 * Method to load the master data from Database to cache
	 * 
	 * @author vimal
	 * @return
	 */
	RestResponse<GenericResponse> loadCacheFromMasterDataTable();

	/**
	 * Method to calculate the portfolio score for the user holdings data according
	 * to the score of each holding that user's has as per perivious day's data
	 * 
	 * @author vimal
	 * 
	 * @param userId
	 * @return
	 */
	RestResponse<GenericResponse> calculatePortfolioScore(String userId);

}
