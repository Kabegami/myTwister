package ServiceFollow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Tools.Error;
import bd.AuthorizationTools;
import bd.BDException;
import bd.Database;
/*c'est service nous donne une liste des user qui sont suivis par le user avec id : id_followed*/
public class ListeFollowedService {
	
	public static JSONObject listeFolloweds(String key, int id_followed) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, BDException {
		
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
				String query = "SELECT id_to FROM Followers WHERE id_from = " + id_followed;
				rs = st.executeQuery(query);
				while (rs.next()){
					JSONObject res = new JSONObject();
					int id_to = rs.getInt("id_to");
					res.put("id", id_to);
					res.put("user", AuthorizationTools.getLoginUserbyId(id_to));
					follows.put(res);
				}
			}
			finalQuery.put("follows", follows);
			AuthorizationTools.updateSession(key);
			st.close(); rs.close(); conn.close();
		}
		catch(Exception e){
			throw new BDException("can't recouvery account , exception = " + e);
		}
		return finalQuery;
	}
}
