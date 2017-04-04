package ServletsUser;
/**
 * 
 * @api {get} /logout logout 
 * @apiVersion 0.1.0
 * @apiName LogoutServlet
 * @apiGroup user
 * 
 * 
 * @apiParam  {String} key
 * @apiSuccessExample {json} Succès:
 * 			{}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 User is not logged in
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

import ServiceUser.LogoutService;



public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public LogoutServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String key = request.getParameter("key");
		JSONObject res = new JSONObject();
		PrintWriter out = null;
		
		try {
			res = LogoutService.logout(key);
			response.setContentType("application/json");     
			out = response.getWriter();
			out.print(res);
			out.flush();
		} 
		catch (Exception e) {
			response.setContentType("text/plain");
			out = response.getWriter();
			out.println("error in LogoutServlet");
			out.println(e);
			e.printStackTrace();
		}
	}
}