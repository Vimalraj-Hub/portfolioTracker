package in.codifi.portfolio.controller;

import org.jboss.resteasy.reactive.RestResponse;

import in.codifi.portfolio.controller.spec.MasterDataDownloadControllerSpec;
import in.codifi.portfolio.model.response.GenericResponse;
import in.codifi.portfolio.service.spec.MasterDataDownloadServiceSpec;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

@Path("/download")
public class MasterDataDownloadController implements MasterDataDownloadControllerSpec {
	@Inject
	MasterDataDownloadServiceSpec masterDataDownloadServiceSpec;

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

}
