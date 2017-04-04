package ServletsPosts;
/**
 * 
 * @api {get} /deletecomment like one post 
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

import ServicePosts.DeletCommentService;




public class DeletCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public DeletCommentServlet(){
		
	}
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
				PrintWriter out = null;
				String key = request.getParameter("key");
				String id_comment = request.getParameter("id_comment");
				
				JSONObject res = new JSONObject();
				
				try {
					response.setContentType( "application/json" );
					out = response.getWriter ();
					res = DeletCommentService.deletcomment(key,id_comment);
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





