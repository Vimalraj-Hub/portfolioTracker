package in.codifi.portfolio.model.response;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApplicationScoped
public class PortfolioScoreRespModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private BigDecimal sumofProductHoldingValueAndTLScore;
	private BigDecimal sumOfHoldingValue;
	private BigDecimal calculatedPortfolioScore;

}
