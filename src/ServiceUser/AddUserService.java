package ServiceUser;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.json.JSONException;
import org.json.JSONObject;

import bd.AuthorizationTools;
import bd.BDException;
import bd.Database;
import bd.TemporaryUser;
import Tools.Error;
import Tools.MailsTools;
import Tools.PasswordTools;
import Tools.SessionGenerator;

public class AddUserService {
	public static JSONObject AddUser(String login, String pass, String name,
			String surname, String mail,String bd) throws  JSONException, BDException {
		
		JSONObject r = new JSONObject();
		try{
			
			if (login == null || pass == null || surname == null || mail == null || bd == null ){
				return Error.serviceKO("Problem with arguments", -1);
			}
			
			if (AuthorizationTools.userExists(login)){
				return Error.serviceKO("user with this login existe try onather login", 1);
			}
			
			if (AuthorizationTools.mailExists(mail)){
				return Error.serviceKO("user with this mail existe try onather mail or recovery your account", 2);
			}
			
			if (TemporaryUser.mailExists(mail)){
				return Error.serviceKO("Temporary user with this mail existe try onather mail or recovery your account", 3);
			}
			
			if (TemporaryUser.userExists(login)){
				return Error.serviceKO("Temporary user with this login existe try onather login", 4);
			}
			
			else{
				
				String clef = SessionGenerator.nextKey();
				clef = login.concat(clef);
				String passHashed = PasswordTools.hashPassword(pass);
				
				String subject = "Activation Account";
				String url = "http://li328.lip6.fr:8280/gr3_yoones_becirspahic/user/activation?clef="+clef+"";
				String txte = "For activeted your account clik on this link :\n"+url+"\nThanks.";
				MailsTools.sendmail(mail,subject,txte);
				if(MailsTools.sendmail(mail,subject,txte)){
					Connection c = Database.getMySQLConnection();
					String query = "INSERT INTO TemporaryUsers VALUES (?, ?, ?, ?, ?, ?,?,null)";
					PreparedStatement pst = c.prepareStatement(query);
					pst.setString(1, clef);
					pst.setString(2, login);
					pst.setString(3, passHashed);
					pst.setString(4, name);
					pst.setString(5, surname);
					pst.setString(6, mail);
					pst.setDate(7, java.sql.Date.valueOf(bd));
					pst.executeUpdate();
					pst.close(); c.close();
			
				}else{
					//si il y a problem de send mail , on inscrit la perssone directment 
					//sans passer par mail activation
					Connection c = Database.getMySQLConnection();
					String query = "INSERT INTO Users VALUES (null, ?, ?, ?, ?, ?,?,null)";
					PreparedStatement pst = c.prepareStatement(query);
					pst.setString(1, login);
					pst.setString(2, passHashed);
					pst.setString(3, name);
					pst.setString(4, surname);
					pst.setString(5, mail);
					pst.setDate(6, java.sql.Date.valueOf(bd));
					pst.executeUpdate();
					pst.close(); c.close();
	
					
				}
				
			}
		}
		catch(Exception e){
			throw new BDException("can't add a new user , exception = " + e);
		}
		
		return r;
	}
}
