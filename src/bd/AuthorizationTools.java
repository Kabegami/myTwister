package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Tools.SessionGenerator;
import bd.Database;
import Tools.PasswordTools;

public class AuthorizationTools {
	public static boolean userExists(String login) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		boolean e = false;
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();

		String query = "SELECT e.id FROM Users e WHERE e.login = '" + login +"'";
		
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
		e = rs.next();
		rs.close(); st.close(); c.close();
		return e;
	}
	public static boolean userExistsByID(int id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		boolean e = false;
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();

		String query = "SELECT id FROM Users WHERE id = \'" + id +"\'";
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
			
		e = rs.next();
		rs.close(); st.close(); c.close();
		
		return e;
	}
	
	
	public static boolean checkPassword(String login, String pwd) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		boolean e = false;
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();
		String query = "SELECT pass FROM Users WHERE login = \'" + login +"\'";
		
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
		
		if (rs.next()){
			String savedpass = rs.getString("pass");
			try{
				e = PasswordTools.checkPassword(pwd,savedpass);
			}
			catch(IllegalArgumentException exc){
				e = false;
			}
		}
		rs.close(); st.close(); c.close();
		
		return e;
		
	}
	public static int getIdUserbyLogin(String login) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		int id = -1;
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();
		String query = "SELECT id FROM Users WHERE login = '" + login +"'";
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
		
		if(rs.next()){
			id = rs.getInt("id");
		}

		rs.close(); st.close(); c.close();
		return id;
	}
	public static boolean user_has_session(int id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(id < 0){
			return false;
		}
		boolean e = false;
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();
		String query = "SELECT clef FROM Sessions WHERE id = \'" + id +"\'";
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
		if(rs.next()){
			e = true;
		}
		st.close();rs.close();c.close();
		return e;
	}
	public static String insertSession(int id,String login, boolean admin) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		String  key = "";
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();
		String query = "SELECT clef FROM Sessions WHERE id = \'" + id +"\'";
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
		if(rs.next()){
			st.close();
			c.close();
			rs.close();
			return "User_has_Session";
		}
		st.close();
		rs.close();
		Statement st1 = c.createStatement();
		key = SessionGenerator.nextKey();
		/* for be shure about unucity of key*/
		key = login.concat(key);
		query = "INSERT INTO Sessions VALUES (\"" + key + "\" ," + id + " , NOW(), " + admin + ")";
		st1.executeUpdate(query);
		st1.close(); c.close();
		 
		return key;
	}
	
	public static boolean removeSession(String key) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		boolean e = false;
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();
		Statement st1 = (Statement) c.createStatement();
		String query = "SELECT s.id FROM  Sessions s\n WHERE s.clef = '" + key +"'";
		
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
		
		if (rs.next()){
			e = true;
			query = "DELETE FROM Sessions WHERE\n clef = '" + key+"'";
			st1.executeUpdate(query);
			
		}
		st.close(); st1.close();
		rs.close(); c.close();
		
		return e;
		
	}
	
	public static boolean mailExists(String mail) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		boolean e = false;
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();

		String query = "SELECT id FROM Users WHERE mail = \'" + mail +"\'";
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
			
		e = rs.next();
		rs.close(); st.close(); c.close();
		
		return e;
	}
	public static int getIdUSerbyKey(String key) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		int id = -1;
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();
		String query = "SELECT s.id FROM Sessions s WHERE clef = \'" + key +"\'";
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
		
		if(rs.next()){
			id = rs.getInt("id");
		}

		rs.close(); st.close(); c.close();
		return id;
		
	}
	public static String getLoginUserbyId(int id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		String login = "";
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();
		String query = "SELECT u.login FROM Users u \nWHERE u.id = '" + id +"'";
		
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
		
		if(rs.next()){
			login = rs.getString("login");
		}

		rs.close(); st.close(); c.close();
		return login;
	}
	public static void updateSession(String key) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		Connection conn = Database.getMySQLConnection();
		Statement st = conn.createStatement();
		
		String query = "UPDATE Sessions SET timestamp = NOW() WHERE clef = \"" + key + "\"";
		st.executeUpdate(query);
		st.close(); conn.close();
	}
	public static boolean chekmailbyuser(String login, int id, String mail) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		boolean e = false;
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();

		String query = "SELECT u.id FROM Users u \nWHERE u.mail = '" + mail +"'\nAND u.id = '"+ id +"'\nAND u.login = '"+ login+"'";
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
			
		e = rs.next();
		rs.close(); st.close(); c.close();
		
		return e;
	}

}
