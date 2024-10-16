package in.codifi.portfolio.controller;

import org.jboss.resteasy.reactive.RestResponse;

import com.hazelcast.internal.util.StringUtil;

import in.codifi.portfolio.controller.spec.MasterDataDownloadControllerSpec;
import in.codifi.portfolio.model.response.GenericResponse;
import in.codifi.portfolio.service.spec.MasterDataDownloadServiceSpec;
import in.codifi.portfolio.utility.AppConstants;
import in.codifi.portfolio.utility.PrepareResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/portfolio")
public class MasterDataDownloadController implements MasterDataDownloadControllerSpec {
	@Inject
	MasterDataDownloadServiceSpec masterDataDownloadServiceSpec;
	@Inject
	PrepareResponse prepareResponse;

	/**
	 * Method to download the master data file
	 * 
	 * @return
	 */
	@Override
	public RestResponse<GenericResponse> masterDataDownload() {
		return masterDataDownloadServiceSpec.getFileFromSftp();

	}

	/**
	 * Method to reolad the config cache from Db
	 * 
	 * @author vimal *
	 * @return
	 */
	@Override
	public RestResponse<GenericResponse> reloadAppCache() {
		return masterDataDownloadServiceSpec.reloadAppCache();
	}

	/**
	 * Method load the data from master data table from Db to cache.
	 * 
	 * @author vimal
	 * @return
	 */
	@Override
	public RestResponse<GenericResponse> loadCacheFromMasterDataTable() {
		return masterDataDownloadServiceSpec.loadCacheFromMasterDataTable();
	}

	/**
	 * Method to calculate the user's portfolio score according the holding that
	 * user holds as per the data recieved on daily file
	 * 
	 * @author vimal
	 * 
	 * @return
	 */
	@Override
	public RestResponse<GenericResponse> calculatedPortfolioScoreForUser(String userId) {
		if (StringUtil.isNullOrEmptyAfterTrim(userId))
			return prepareResponse.prepareFailedResponse(AppConstants.FAILED_STATUS + " Due to userId is Empty ");
		return masterDataDownloadServiceSpec.calculatePortfolioScore(userId);
	}

}
