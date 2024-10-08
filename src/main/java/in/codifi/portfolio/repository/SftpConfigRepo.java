package in.codifi.portfolio.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import in.codifi.portfolio.entity.SftpConfigEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public interface SftpConfigRepo extends JpaRepository<SftpConfigEntity, Integer> {
	
	@Transactional
	SftpConfigEntity findByPropertyKey(String propertyKey);

}
