package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bd.Database;

public class TemporaryUser {
	public static boolean mailExists(String mail) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		boolean e = false;
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();

		String query = "SELECT clef FROM TemporaryUsers WHERE mail = \'" + mail +"\'";
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
			
		e = rs.next();
		rs.close(); st.close(); c.close();
		
		return e;
	}
	public static boolean userExists(String login) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		boolean e = false;
		Connection c = Database.getMySQLConnection();
		Statement st = (Statement) c.createStatement();

		String query = "SELECT clef FROM TemporaryUsers WHERE login = \'" + login +"\'";
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
			
		e = rs.next();
		rs.close(); st.close(); c.close();
		
		return e;
	}

}
