package ServiceUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import Tools.Error;
import Tools.MailsTools;
import bd.BDException;
import bd.Database;

public class ActivationService {

	public static JSONObject activateAccount(String clef) throws JSONException, BDException {
		JSONObject r = new JSONObject();
		Connection c = null;
		Statement st = null;
		ResultSet rs = null;
		
		if (clef == null){
			return Error.serviceKO("Problem with arguments", -1);
		}
		try{
			c = Database.getMySQLConnection();
			st = (Statement) c.createStatement();
			String query = "SELECT * FROM TemporaryUsers \nWHERE clef = '" + clef +"'";
			rs  = st.executeQuery(query);
			if (rs.next()){
				
				String login = rs.getString("login");
				String mail = rs.getString("mail");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String pass = rs.getString("pass");
				String birth_date = rs.getString("birth_date");
				
				query = "DELETE FROM TemporaryUsers \nWHERE clef = '" + clef +"'";
				st.executeUpdate(query);
				st.close();
				
				query = "INSERT INTO Users VALUES (null, ?, ?, ?, ?, ?,?,null)";
				PreparedStatement pst = c.prepareStatement(query);
				pst.setString(1, login);
				pst.setString(2, pass);
				pst.setString(3, name);
				pst.setString(4, surname);
				pst.setString(5, mail);
				pst.setDate(6, java.sql.Date.valueOf(birth_date));
				pst.executeUpdate();
				pst.close(); c.close();
				
				String texte= "Hello dear "+login+" Your account is active now ";
				String subject = "Confirmation of activated account";
				MailsTools.sendmail(mail,subject,texte);
			}
			else{
				if(rs != null){
					rs.close();
				}
				if(st != null){
					st.close();
				}
				if(c != null){
					 c.close();
				}
				return Error.serviceKO("There is not such request for activation account", 1);
			}
			
		}
		catch(Exception e){
			throw new BDException("can't recouvery account , exception = " + e);
		}
	
		return r;
	}
}
