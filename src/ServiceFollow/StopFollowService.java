package ServiceFollow;

import java.sql.Connection;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import bd.AuthorizationTools;
import bd.BDException;
import bd.Database;
import bd.FollowTools;
import Tools.Error;

public class StopFollowService {
	public static JSONObject stopFollow(String key, int id_followed) throws JSONException, BDException {
		
			JSONObject res = new JSONObject();
			if (key == null || id_followed < 0){
				return Error.serviceKO("Wrong arguments", -1);
			}	
			try {
				if (! AuthorizationTools.userExistsByID(id_followed)){
					return Error.serviceKO("User doesn't exist", 1);
				}
				else {
					int id_user = AuthorizationTools.getIdUSerbyKey(key);
						
					// login1 non connecté
					if (id_user < 0){
						return Error.serviceKO("User is not logged in", 2);
					}
					if (! FollowTools.freindsBefore(id_user, id_followed)){
						return Error.serviceKO("Already not Friends", 3);
					}
						
					else {
			
						Connection c = Database.getMySQLConnection();
						Statement st = (Statement) c.createStatement();
						String query = "DELETE FROM Followers WHERE\n id_from = "+id_user+" AND id_to = "+id_followed;
						st.executeUpdate(query);
						st.close(); c.close();
					}
					AuthorizationTools.updateSession(key);
				}
			} 
			catch(Exception e){
				throw new BDException("can't recouvery account , exception = " + e);
			}
			return res;
	}
}
