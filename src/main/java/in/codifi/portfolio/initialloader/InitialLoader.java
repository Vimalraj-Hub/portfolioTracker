package in.codifi.portfolio.initialloader;

import in.codifi.portfolio.service.spec.MasterDataDownloadServiceSpec;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class InitialLoader {
	@Inject
	MasterDataDownloadServiceSpec masterDataDownloadServiceSpec;

	public void init(@Observes StartupEvent ev) {
		System.out.println("------------------Code running------------------");
		masterDataDownloadServiceSpec.reloadAppCache();
		masterDataDownloadServiceSpec.getFileFromSftp();
		masterDataDownloadServiceSpec.loadCacheFromMasterDataTable();
		System.out.println("------------------Completed Initial Start------------------");

	}

}
