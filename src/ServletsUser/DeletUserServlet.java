package ServletsUser;

/**
 * 
 * @api {get} /delete delet  user 
 * @apiVersion 0.1.0
 * @apiName DeletUserServlet
 * @apiGroup user
 * 
 * 
 * @apiParam  {String} key
 *  
 * @apiSuccessExample {json} Succès:
 * 			{}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 Utilisateur ot logged in
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

import ServiceUser.DeletUserService;

public class DeletUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public DeletUserServlet(){
		
	}
	
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		PrintWriter out = null;
		JSONObject rj = new JSONObject();
		
		try {
			rj = DeletUserService.deletUser(key);
			response.setContentType("application/json");     
			out = response.getWriter();
			out.print(rj);
			out.flush();
		} 
		catch (Exception e) {
			response.setContentType("text/plain");
			out = response.getWriter();
			out.println("error in DeletUserServlet");
			out.println(e);
			e.printStackTrace();
		}
	}
}
