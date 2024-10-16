package in.codifi.portfolio.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterDataRespModel {
//	@JsonProperty("id")
//	private String id;
	@JsonProperty("nseCode")
	private String nseCode;
	@JsonProperty("bseCode")
	private String bseCode;
	@JsonProperty("isin")
	private String isin;
	@JsonProperty("fullName")
	private String fullName;
	@JsonProperty("industryName")
	private String industryName;
	@JsonProperty("industryId")
	private int industryId;
	@JsonProperty("sectorName")
	private String sectorName;
	@JsonProperty("sectorId")
	private int sectorId;
	@JsonProperty("tlScore")
	private BigDecimal tlScore;
}
