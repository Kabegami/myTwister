package ServletsFollows;

/**
 * 
 * @api {get} /stop stop following 
 * @apiVersion 0.1.0
 * @apiName StopFollowServlet
 * @apiGroup user
 * 
 * 
 * @apiParam  {String} key
 * @apiParam  {String} login user
 * 
 * 
 * @apiSuccessExample {json} Succès:
 * 			{}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 User doesn't exist
 * @apiError (Error) 2 User is not logged in
 * @apiError (Error) 3 Already not Friends
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


import ServiceFollow.StopFollowService;

public class StopFollowServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public StopFollowServlet() {
		
	}
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
				
				String key = request.getParameter("key");
				String loginuser = request.getParameter("loginuser");
				int id = Integer.parseInt(loginuser);
				JSONObject res = new JSONObject();
				PrintWriter out = null;
				
				try {
					res = StopFollowService.stopFollow(key,id);
					response.setContentType("application/json");     
					out = response.getWriter();
					out.print(res);
					out.flush();
				} catch (Exception e) {
					response.setContentType("text/plain");
					out = response.getWriter();
					out.println("erreur in StopFollowServlet");
					out.println(e);
					e.printStackTrace();
				}
		}
}
