package ServiceUser;

import org.json.JSONException;
import org.json.JSONObject;

import bd.AuthorizationTools;
import bd.BDException;

import Tools.Error;

public class LogoutService {
	
	public static JSONObject logout(String key) throws JSONException, BDException{
		
		JSONObject r = new JSONObject();
		boolean b = false;
		
		if (key == null){
			return Error.serviceKO("Problem with arguments", -1);
		}	
		try {
			b = AuthorizationTools.removeSession(key);
			if (! b){
				return Error.serviceKO("User is not logged in or there is not such user", 1);
			}
		} 
		catch(Exception e){
			throw new BDException("can't logout , exception = " + e);
		}
		return r;
	}
}
