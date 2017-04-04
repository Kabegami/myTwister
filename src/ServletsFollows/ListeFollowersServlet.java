package ServletsFollows;

/**
 * 
 * @api {get} /listfollowers list of followers of user 
 * @apiVersion 0.1.0
 * @apiName ListeFollowersServlet
 * @apiGroup user
 * 
 * 
 * @apiParam  {String} key
 * @apiParam  {String} loginuser
 * @apiSuccessExample {jsonArray} liste of fllowers:
 * 			{list of follower}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 Utilisateur avec ce login n'exist pas
 * @apiError (Error) 2 Utilisateur not logged in
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


import ServiceFollow.ListeFollowersService;

/*c'est service nous donne une liste des user qui suivent le user avec id : id_followed*/

public class ListeFollowersServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public ListeFollowersServlet(){
		
	}
	
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
				
				String key = request.getParameter("key");
				String loginuser = request.getParameter("loginuser");
				int id = Integer.parseInt(loginuser);
				JSONObject res = new JSONObject();
				PrintWriter out = null;
				
				try {
					res = ListeFollowersService.listeFollowers(key,id);
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
