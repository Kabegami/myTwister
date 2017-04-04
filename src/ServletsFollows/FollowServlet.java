package ServletsFollows;
/**
 * 
 * @api {get} /follow adding  follower  
 * @apiVersion 0.1.0
 * @apiName FollowServlet
 * @apiGroup follow
 * 
 * 
 * @apiParam  {String} key
 * @apiParam  {String} loginuser
 * 
 * 
 * @apiSuccessExample {json} Succès:
 * 			{}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 User doesn't exist
 * @apiError (Error) 2 User is not logged in
 * @apiError (Error) 3 Already Follows
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

import ServiceFollow.FollowService;



public class FollowServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public FollowServlet() {
		
	}
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
				PrintWriter out = null;
				String key = request.getParameter("key");
				String loginuser = request.getParameter("loginuser");
				int id = Integer.parseInt(loginuser);
				JSONObject res = new JSONObject();
				
				try {
					response.setContentType( "application/json" );
					res = FollowService.addFollow(key,id);
					out = response.getWriter ();
					out.println(res);
				} catch (Exception e) {
					response.setContentType("text/plain");
					out = response.getWriter();
					out.println("error in FollowServlet");
					out.println(e);
					e.printStackTrace();
				}
		}
}
