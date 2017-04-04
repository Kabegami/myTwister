package ServiceUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import Tools.Error;
import Tools.MailsTools;
import Tools.SessionGenerator;

import bd.AuthorizationTools;
import bd.BDException;
import bd.Database;

public class RequestRecoveryAccountService {
	
	public static JSONObject sendingMailRecouvery(String login, String mail) throws  BDException, JSONException {
		
		JSONObject ret = new JSONObject();
		if (login == null || mail == null){
			return Error.serviceKO("Problem with arguments", -1);
		}	
		try {
			if (! AuthorizationTools.userExists(login)){
				return Error.serviceKO("User not exist", 1);
			}
			
			if (! AuthorizationTools.mailExists(mail)){
				return Error.serviceKO("mail Wrong", 2);
			}
			
			int id = AuthorizationTools.getIdUserbyLogin(login);
			if (id == -1){
				return Error.serviceKO("User has not id", 3);		
			}
			if(AuthorizationTools.user_has_session(id)){
				return Error.serviceKO("you are already logged in", 7);
			}
			if(!AuthorizationTools.chekmailbyuser(login,id,mail)){
				return Error.serviceKO("mail is not for this user ", 4);
			}
			
			String clef = SessionGenerator.nextKey();
			clef = login.concat(clef);
			
			Connection c = Database.getMySQLConnection();
			Statement st = (Statement) c.createStatement();
			String query = "SELECT r.clef FROM RecouverAccount r\nWHERE r.login = '"+login+"'";
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();
			
			if(rs.next()){
					rs.close(); st.close(); c.close();
					return Error.serviceKO("You have a mail for recouvery you account befor in your inbox please chek your inbox", 6);
			}
			rs.close(); st.close();
		
			String subject = "Recouvery Account";
			String Object = "Please clik on this link : \n";
			String url = "http://li328.lip6.fr:8280/gr3_yoones_becirspahic/user/recouveryaccount?clef="+clef;
			Object = Object.concat(url).concat("\nThanks.");
			
			if(!MailsTools.sendmail(mail,subject,Object)){
				c.close();
				return Error.serviceKO("problem sending mail pleas try later", 5);
			}
			
			query = "INSERT INTO RecouverAccount VALUES (null,?, ?, ?, null)";
			PreparedStatement ps = c.prepareStatement(query);
			ps.setString(1, clef);
			ps.setString(2, login);
			ps.setString(3, mail);
			ps.executeUpdate();
			ps.close(); c.close();
			} 
		catch(Exception e){
			throw new BDException("can't add a new user , exception = " + e);
		}
		return ret;
	}
}
