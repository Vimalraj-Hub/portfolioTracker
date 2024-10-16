package in.codifi.portfolio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.codifi.portfolio.entity.HoldingsEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface HoldingEntityRepo extends JpaRepository<HoldingsEntity, Long> {

	List<HoldingsEntity> findAllByUserId(String userId);

}
