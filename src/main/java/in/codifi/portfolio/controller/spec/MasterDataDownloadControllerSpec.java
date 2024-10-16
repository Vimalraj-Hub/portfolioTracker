package in.codifi.portfolio.controller.spec;

import org.jboss.resteasy.reactive.RestResponse;

import in.codifi.portfolio.model.response.GenericResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

public interface MasterDataDownloadControllerSpec {

	/**
	 * Method to download the master data file
	 * 
	 * @return
	 */
	@Path("/masterdata/download")
	@GET
	RestResponse<GenericResponse> masterDataDownload();

	/**
	 * Method to reload the config cache from Db as this properties contain the sftp
	 * server details.
	 * 
	 * @author vimal *
	 * @return
	 */
	@Path("/cache/properties")
	@GET
	RestResponse<GenericResponse> reloadAppCache();

	/**
	 * Method load the data from master data table from Db to cache.
	 * 
	 * @author vimal
	 * @return
	 */
	@Path("/cache/masterdata")
	@GET
	RestResponse<GenericResponse> loadCacheFromMasterDataTable();

	/**
	 * Method to calculate the user's portfolio score according the holding that
	 * user holds as per the data recieved on daily file
	 * 
	 * @author vimal
	 * 
	 * @return
	 */
	@Path("/calculate/pScore")
	@GET
	RestResponse<GenericResponse> calculatedPortfolioScoreForUser(@QueryParam("userId")String userId);

}
