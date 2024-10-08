package in.codifi.portfolio.utility;

import in.codifi.portfolio.cache.model.ClinetInfoModel;
import in.codifi.portfolio.controller.DefaultRestController;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppUtil extends DefaultRestController {

//	public static String getUserSession(String userId) {
//		String userSession = "";
//		String hzUserSessionKey = userId + AppConstants.HAZEL_KEY_REST_SESSION;
//		userSession = HazelcastConfig.getInstance().getRestUserSession().get(hzUserSessionKey);
//		return userSession;
//	}

	/**
	 * 
	 * Method to get client info
	 * 
	 * @author Dinesh Kumar
	 *
	 * @return
	 */
	public ClinetInfoModel getClientInfo() {
		ClinetInfoModel model = clientInfo();
		return model;
	}

	/**
	 * 
	 * Method to validate the userId
	 * 
	 * @author Dinesh Kumar
	 *
	 * @param reqUserID
	 * @return
	 */
	public boolean isValidUser(String userID) {

		try {
			String userIdFromToken = getUserId();
			if (StringUtil.isNotNullOrEmpty(userID) && StringUtil.isNotNullOrEmpty(userIdFromToken)) {
				if (userID.equalsIgnoreCase(userIdFromToken)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Method to get access token
	 * 
	 * @author DINESH KUMAR
	 *
	 * @return
	 */
	public String getAccessToken() {
		String token = "";
		try {
			token = getAcToken();
		} catch (Exception e) {
			e.printStackTrace();
			Log.error(e.getMessage());
		}
		return token;
	}

}