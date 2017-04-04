package ServiceUser;

import org.json.JSONException;
import org.json.JSONObject;

import bd.AuthorizationTools;
import bd.BDException;

import ServiceFollow.ListeFollowersService;

import Tools.Error;

public class LoginService {
	
	public static JSONObject login(String login, String pwd) throws  BDException, JSONException{
		
		JSONObject ret = new JSONObject();
		
		if (login == null || pwd == null){
			return Error.serviceKO("Problem with arguments", -1);
		}	
		try {
			if (! AuthorizationTools.userExists(login)){
				return Error.serviceKO("User not exist", 1);
			}
			
			if (! AuthorizationTools.checkPassword(login, pwd)){
				return Error.serviceKO("password Wrong", 2);
			}
			int id = AuthorizationTools.getIdUserbyLogin(login);
			if (id == -1){
				return Error.serviceKO("User has not id", 3);		
			}
			boolean admin = false;

			/*
			 admin = AdminTools.isAdmin(id);
			 */
			String key = AuthorizationTools.insertSession(id,login, admin);
			if (key.equals("User_has_Session")){
				return Error.serviceKO("User is logged in befor", 4);
			}
			ret.put("key", key);
			ret.put("id", id);
			ret.put("login", login);
			ret.put("followed_by:", ListeFollowersService.listeFollowers(key,id).getJSONArray("followed_by:"));
	
		} catch(Exception e){
			throw new BDException("can't login , exception = " + e);
		}
		return ret;
	}

}
