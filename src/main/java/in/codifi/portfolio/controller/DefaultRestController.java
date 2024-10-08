/**
 * 
 */
package in.codifi.portfolio.controller;


import org.eclipse.microprofile.jwt.JsonWebToken;

import in.codifi.portfolio.cache.model.ClinetInfoModel;
import jakarta.inject.Inject;

/**
 * @author mohup
 *
 */
public class DefaultRestController {

	private static final String USER_ID_KEY = "preferred_username";
	private static final String UCC = "ucc";
	/**
	 *      * Injection point for the ID Token issued by the OpenID Connect Provider
	 *     
	 */

	@Inject
	JsonWebToken idToken;
	@Inject
	JsonWebToken accessToken;

	public String getUserId() {
		return this.idToken.getClaim(USER_ID_KEY).toString().toUpperCase();
	}

	/**
	 * 
	 * Method to get client details
	 * 
	 * @author Dinesh Kumar
	 *
	 * @return
	 */
	public ClinetInfoModel clientInfo() {
		ClinetInfoModel model = new ClinetInfoModel();
		model.setUserId(this.idToken.getClaim(USER_ID_KEY).toString().toUpperCase());
		if (this.idToken.containsClaim(UCC)) {
			model.setUcc(this.idToken.getClaim(UCC).toString());
		}
		return model;
	}

	/**
	 * Method to get AcToken
	 *
	 * @author Gowthaman M
	 * @date 09-Jul-2024
	 * @return
	 */
	public String getAcToken() {
		return this.accessToken.getRawToken();
	}
}
