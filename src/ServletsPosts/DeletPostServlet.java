package ServletsPosts;
/**
 * 
 * @api {get} /deletepost like one post 
 * @apiVersion 0.1.0
 * @apiName LikePost
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

import ServicePosts.DeletPostService;


public class DeletPostServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public DeletPostServlet(){
		
	}
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
				PrintWriter out = null;
				String key = request.getParameter("key");
				String id_post = request.getParameter("id_post");
				
				JSONObject res = new JSONObject();
				
				
				
				try {
					response.setContentType( "application/json" );
					out = response.getWriter ();
					res = DeletPostService.deletPost(key,id_post);
					out.println(res);
				} catch (Exception e) {
					response.setContentType("text/plain");
					out = response.getWriter();
					out.println("error in DeletPostServlet");
					out.println(e);
					e.printStackTrace();
				}

	}

}
