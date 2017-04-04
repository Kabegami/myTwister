package ServiceFollow;

import java.sql.Connection;
import java.sql.PreparedStatement;


import org.json.JSONException;
import org.json.JSONObject;

import bd.AuthorizationTools;
import bd.BDException;
import bd.Database;
import bd.FollowTools;
import Tools.Error;

public class FollowService {
	
	public static JSONObject addFollow(String key, int id_followed) throws JSONException, BDException {
		
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
						if (id_user < 0){
							return Error.serviceKO("User is not logged in", 2);
						}
						if (FollowTools.freindsBefore(id_user, id_followed)){
							return Error.serviceKO("Already Follows", 3);
						}
						else {
							Connection conn = Database.getMySQLConnection();
							String query = "INSERT INTO Followers VALUES (?, ?, null)";
							PreparedStatement pst = conn.prepareStatement(query);
							pst.setInt(1, id_user);
							pst.setInt(2, id_followed);
							
							pst.executeUpdate();
							pst.close(); conn.close();
						}
						AuthorizationTools.updateSession(key);
					}
				} catch(Exception e){
					throw new BDException("can't recouvery account , exception = " + e);
				}
				return res;
			}
}
