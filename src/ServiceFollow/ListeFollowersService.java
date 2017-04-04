package ServiceFollow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bd.AuthorizationTools;
import bd.BDException;
import bd.Database;
import Tools.Error;

/*c'est service nous donne une liste des user qui suivent le user avec id : id_followed*/

public class ListeFollowersService {
	
	public static JSONObject listeFollowers(String key, int id_followed) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, BDException {
		JSONObject finalQuery = new JSONObject();
		JSONArray follows = new JSONArray();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		if (key == null || id_followed < 0){
			return Error.serviceKO("Wrong arguments", -1);
		}	
		try {
			int id_user = AuthorizationTools.getIdUSerbyKey(key);
			if (id_user < 0){
				return Error.serviceKO("User is not logged in", 1);
			}
			String login = AuthorizationTools.getLoginUserbyId(id_followed);
			if(login.equals("")){
				return Error.serviceKO("User is not existe", 2);
			}
			else {
				
				conn = Database.getMySQLConnection();
				st = conn.createStatement();
				
				String query = "SELECT id_from FROM Followers WHERE id_to = " + id_followed;
				rs = st.executeQuery(query);
				while (rs.next()){
					JSONObject res = new JSONObject();
					int id_to = rs.getInt("id_from");
					res.put("id", id_to);
					res.put("user", AuthorizationTools.getLoginUserbyId(id_to));
					follows.put(res);
				}
			}
			finalQuery.put("followed_by:", follows);
			AuthorizationTools.updateSession(key);
			st.close(); rs.close(); conn.close();
		}
		catch(Exception e){
			throw new BDException("can't recouvery account , exception = " + e);
		}
		
		return finalQuery;
	}
}
