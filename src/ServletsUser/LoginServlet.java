package ServletsUser;
/**
 * 
 * @api {get} /login logged in 
 * @apiVersion 0.1.0
 * @apiName LoginServlet
 * @apiGroup user
 * 
 * 
 * @apiParam  {String} login
 * @apiParam  {String} pass
 * 
 * 
 * @apiSuccessExample {json} Succès:
 * 			{key,id,login,followers}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 Utilisateur avec ce login n'exist pas
 * @apiError (Error) 2 password Wrong
 * @apiError (Error) 3 User has not id
 * @apiError (Error) 4 User is logged in befo
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

import ServiceUser.LoginService;


public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public LoginServlet() {
		
	}
	
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
			 	
				PrintWriter out = null;
				JSONObject res = new JSONObject();
				
				String login = request.getParameter("login");
				String psw = request.getParameter("psw");
				
				try {
					res = LoginService.login(login, psw);
					response.setContentType("application/json");     
					out = response.getWriter();
					out.print(res);
					out.flush();
				} 
				catch (Exception e) {
					response.setContentType("text/plain");
					out = response.getWriter();
					out.println("error in LoginServlet");
					out.println(e);
					e.printStackTrace();
				}
		}
}
