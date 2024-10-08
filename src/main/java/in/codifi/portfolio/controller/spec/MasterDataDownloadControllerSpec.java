package in.codifi.portfolio.controller.spec;


import org.jboss.resteasy.reactive.RestResponse;

import in.codifi.portfolio.model.response.GenericResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

public interface MasterDataDownloadControllerSpec {

	/**
	 * Method to download the master data file 
	 * @return
	 */
	@Path("/masterdata")
	@GET
	RestResponse<GenericResponse> masterDataDownload();

	/**
	 * Method to reolad the config cache from Db
	 * 
	 * @author vimal *
	 * @return
	 */
	@Path("/reloadappcache")
	@GET
	RestResponse<GenericResponse> reloadAppCache();
	
	
	

}
