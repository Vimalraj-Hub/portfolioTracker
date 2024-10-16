package in.codifi.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.codifi.portfolio.entity.MasterDataEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface MasterDataRepo extends JpaRepository<MasterDataEntity, Long> {

	MasterDataEntity findByIsin(String isin);
	

}
