package ServletsPosts;
/**
 * 
 * @api {get} /getposts like one post 
 * @apiVersion 0.1.0
 * @apiName Get posts
 * @apiGroup posts
 * 
 * 
 * @apiParam  {String} key
 * @apiParam  {String} id_post
 * 
 * 
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


import ServicePosts.GetPostsService;



public class GetPostsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public GetPostsServlet(){
		
	}
	protected void doPost(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
				PrintWriter out = null;
				String key = request.getParameter("key");
				String query = request.getParameter("query");
				String from = request.getParameter("from");
				String id_max = request.getParameter("id_max");
				String id_min = request.getParameter("id_min");
				String nb = request.getParameter("nb");
				
				JSONObject res = new JSONObject();
				
				
				
				try {
					response.setContentType( "application/json" );
					out = response.getWriter ();
					res = GetPostsService.getMessage(key,query,from,id_max,id_min,nb);
					out.println(res);
				} catch (Exception e) {
					response.setContentType("text/plain");
					out = response.getWriter();
					out.println("error in GetPostServlet");
					out.println(e);
					e.printStackTrace();
				}

	}

}
