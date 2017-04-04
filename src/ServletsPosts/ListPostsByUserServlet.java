package ServletsPosts;

/**
 * 
 * @api {post} /listposts sending post
 * @apiVersion 0.1.0
 * @apiName ListPostsByUserServlet
 * @apiGroup posts
 * 
 * 
 * @apiParam  {String} key
 * @apiParam  {String} id
 * 
 * @apiSuccessExample {Array json} Succès:
 * 			{date,id_post,id,text,username}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 User is not logged in
 * @apiError (Error) 2 User witch we serach his posts is not existe
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

import ServicePosts.ListPostsByUserService;


public class ListPostsByUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public ListPostsByUserServlet(){
	}
		
	protected void doGet(HttpServletRequest request,
				 HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = null;
		String key = request.getParameter("key");
		String id = request.getParameter("id");	 
		JSONObject res = new JSONObject();
		try {
			res = ListPostsByUserService.listePostsByUser(key, id);
			response.setContentType("application/json");     
			out = response.getWriter();
			out.print(res);
			out.flush();
		} catch (Exception e) {
			response.setContentType("text/plain");
			out = response.getWriter();
			out.println("error in SendPost");
			out.println(e);
			e.printStackTrace();
		}
	}
}

