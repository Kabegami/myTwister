package ServletsUser;

/**
 * 
 * @api {get} /activation adding  user 
 * @apiVersion 0.1.0
 * @apiName ActivationServlet
 * @apiGroup user
 * 
 * 
 * @apiParam  {String} clef
 * 
 * 
 * @apiSuccessExample {json} Succès:
 * 			{}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 There is not such request of activation account
 * 
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ServiceUser.ActivationService;

/*
String passHashed = PasswordTools.hashPassword(pass);
String query = "INSERT  Users VALUES (null, ?, ?, ?,?, ?,?,null)";
PreparedStatement ps = c.prepareStatement(query);
ps.setString(1, new String(login));
ps.setString(2, passHashed);
ps.setString(3, name);
ps.setString(4, surname);
ps.setString(5, mail);
ps.setDate(6, java.sql.Date.valueOf(bd));
ps.executeUpdate();
ps.close(); c.close();*/

public class ActivationServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public ActivationServlet(){
		
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
				PrintWriter out = null;
				String clef = request.getParameter("clef");
				JSONObject res = new JSONObject();
				
				try {
					res = ActivationService.activateAccount(clef);
					response.setContentType("application/json");     
					out = response.getWriter();
					out.print(res);
					out.flush();
				} catch (Exception e) {
					response.setContentType("text/plain");
					out = response.getWriter();
					out.println("error in ActivationServlet");
					out.println(e);
					e.printStackTrace();
				}
				

			 }

}
