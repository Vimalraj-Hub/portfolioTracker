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

}
