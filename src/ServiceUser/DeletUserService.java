package ServiceUser;

import java.sql.Connection;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import bd.AuthorizationTools;
import bd.BDException;
import bd.Database;

import Tools.Error;

public class DeletUserService {
	
public static JSONObject deletUser(String key) throws JSONException, BDException {
		
		JSONObject r = new JSONObject();
		
		if (key == null){
			return Error.serviceKO("Problem with arguments", -1);
		}
		
		try{
			
			int id = AuthorizationTools.getIdUSerbyKey(key);
			if (id < 0){
				return Error.serviceKO("user not login or there is not such user", 1);
			}
			
			Connection c = Database.getMySQLConnection();
			Statement st = (Statement) c.createStatement();
			Statement st1 = (Statement) c.createStatement();
			String query = "DELETE FROM Followers\n WHERE id_from = '" + id+"'\n OR id_to = '"+id+"';";
			st.executeUpdate(query);
			query = "DELETE FROM Users\n WHERE id = " + id+";";
			st.executeUpdate(query);
			query = "DELETE FROM Sessions\n WHERE clef = '" + key +"';";
			st1.executeUpdate(query);
			st.close(); st1.close(); c.close();
		}
		
		catch(Exception e){
			throw new BDException("can't delet user , exception = " + e);
		}
		return r;
	}

}
