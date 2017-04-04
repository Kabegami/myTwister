package ServiceUser;

import java.sql.Connection;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import bd.AuthorizationTools;
import bd.BDException;
import bd.Database;
import Tools.Error;
import Tools.PasswordTools;

public class ChangePasswordService {

	public static JSONObject changePass(String key, String pass) throws JSONException, BDException {
		JSONObject r = new JSONObject();
		
		if (key == null  || pass == null){
			return Error.serviceKO("Problem with arguments", -1);
		}
		try{
			int id_user = AuthorizationTools.getIdUSerbyKey(key);
			String passHashed = PasswordTools.hashPassword(pass);
			// login1 non connecté
			if (id_user < 0){
				return Error.serviceKO("User is not logged in", 2);
			}
			else{
				Connection c = Database.getMySQLConnection();
				Statement st = (Statement) c.createStatement();
				String query = "UPDATE Users \n  SET pass = '"+passHashed+"'\n WHERE id = "+id_user+"";
				st.executeUpdate(query);
				AuthorizationTools.updateSession(key);
				st.close();  c.close();
				
			}
		}
		catch(Exception e){
			throw new BDException("can't change password , exception = " + e);
		}
		
		return r;
	}

}
