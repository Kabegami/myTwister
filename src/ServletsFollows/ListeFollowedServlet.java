package ServletsFollows;
/**
 * 
 * @api {get} /listfollowed adding  follower  
 * @apiVersion 0.1.0
 * @apiName ListeFollowedServlet
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
 * @apiError (Error) 1 User is not logged in
 * @apiError (Error) 2 User is not existe
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

import ServiceFollow.ListeFollowedService;
/*c'est service nous donne une liste des user qui sont suivis par le user avec id : id_followed*/


public class ListeFollowedServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public ListeFollowedServlet(){
		
	}
	
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
				
				String key = request.getParameter("key");
				String loginuser = request.getParameter("loginuser");
				int id = Integer.parseInt(loginuser);
				JSONObject res = new JSONObject();
				PrintWriter out = null;
				
				try {
					res = ListeFollowedService.listeFolloweds(key,id);
					response.setContentType("application/json");     
					out = response.getWriter();
					out.print(res);
					out.flush();
				} catch (Exception e) {
					response.setContentType("text/plain");
					out = response.getWriter();
					out.println("error in ListeFollowersServlet");
					out.println(e);
					e.printStackTrace();
				}
	}
}
