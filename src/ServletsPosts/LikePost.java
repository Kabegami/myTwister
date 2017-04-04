package ServletsPosts;
/**
 * 
 * @api {get} /like like one post 
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
 * @apiError (Error) 2 post is not existe
 * @apiError (Error) 3 you have already like this post
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

import ServicePosts.LikePostService;



public class LikePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public LikePost() {
		
	}
	protected void doPost(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = null;
		String key = request.getParameter("key");
		String id_post = request.getParameter("id_post");
		JSONObject rj = new JSONObject();
		
		try {
			rj = LikePostService.likePost(key,id_post);
			response.setContentType("application/json");     
			out = response.getWriter();
			out.print(rj);
			out.flush();
		} catch (Exception e) {
			response.setContentType("text/plain");
			out = response.getWriter();
			out.println("error in LikePost");
			out.println(e);
			e.printStackTrace();
		}
		
	}
}
