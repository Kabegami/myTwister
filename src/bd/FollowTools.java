package bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import bd.Database;


public class FollowTools {
	
	public static boolean freindsBefore(int id_user, int id_followed) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException  {
		boolean e = false;
		if(id_user >= 0 && id_followed>= 0){
			Connection c = Database.getMySQLConnection();
			Statement st = (Statement) c.createStatement();
			String query = "SELECT id_to FROM Followers WHERE id_from = " + id_user
					+ " and id_to = " + id_followed;
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();
			e = rs.next();
			rs.close(); st.close(); c.close();
		}
		return e;
	}
	
	
}


