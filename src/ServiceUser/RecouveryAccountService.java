package ServiceUser;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import bd.AuthorizationTools;
import bd.BDException;
import bd.Database;

import Tools.Error;
import Tools.MailsTools;
import Tools.SessionGenerator;
import Tools.PasswordTools;

public class RecouveryAccountService {
	
	public static JSONObject recouveryAccount(String clef) throws JSONException, BDException {
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
			String query = "SELECT r.login, r.mail FROM RecouverAccount r\nWHERE r.clef = '" + clef +"'";
			rs  = st.executeQuery(query);
			
			if (rs.next()){
				
				String login = rs.getString("login");
				String mail = rs.getString("mail");
				int id = AuthorizationTools.getIdUserbyLogin(login);
				if(AuthorizationTools.user_has_session(id)){
					
					query = "DELETE FROM RecouverAccount \nWHERE clef = '" +clef +"'";
					st.executeUpdate(query);
					
					if(rs != null){
						rs.close();
					}
					if(st != null){
						st.close();
					}
					if(c != null){
						c.close();
					}
					return Error.serviceKO("you are already logged in", 1);
				}
				String pass = SessionGenerator.nextKey();
				String pass2 = PasswordTools.hashPassword(pass);
				
				query = "UPDATE Users SET pass = '"+pass2+"'\nWHERE login = '" + login +"'\n And mail = '"+mail+"'";
				String url  = "http://li328.lip6.fr:8280/gr3_yoones_becirspahic/user/chagepassword";
				String texte= "Hello "+login+" Your password is : " + pass+"\nYou cans change your password later if you want\nLink for changing password is :\n"+url;
				String subject = "Your password";
				if (! MailsTools.sendmail(mail,subject,texte)){
					if(rs != null){
						rs.close();
					}
					if(st != null){
						st.close();
					}
					if(c != null){
						c.close();
					}
					return Error.serviceKO("problem sending mail pleas try later ", 2);
				}
				
				st.executeUpdate(query);
				query = "DELETE FROM RecouverAccount\nWHERE clef = '" + clef +"'";
				st.executeUpdate(query);
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
				return Error.serviceKO("There is not such request recouvery account", 3);
			}
			
			if(rs != null){
				rs.close();
			}
			if(st != null){
				st.close();
			}
			if(c != null){
				 c.close();
			}
			
		}
		catch(Exception e){
			throw new BDException("can't recouvery account , exception = " + e);
		}
		
		return r;
	}

}
