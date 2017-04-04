package ServletsPosts;
/**
 * 
 * @api {get} /sendcomment sending post 
 * @apiVersion 0.1.0
 * @apiName SendComment
 * @apiGroup posts
 * 
 * 
 * @apiParam  {String} key
 * @apiParam  {String} comment
 * @apiParam  {String} id_post
 * 
 * 
 * @apiSuccessExample {json} Succès:
 * 			{identity_coment,text,date}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 User is not logged in
 * @apiError (Error) 2 Post not existe
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

import ServicePosts.SendCommentService;


public class SendComment extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public SendComment() {
		
	}
	protected void doPost(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = null;
		String key = request.getParameter("key");
		String comment = request.getParameter("comment");
		String id_post = request.getParameter("id_post");
			 
		JSONObject res = new JSONObject();
		try {
			res = SendCommentService.writheComment(key, comment,id_post);
			response.setContentType("application/json");     
			out = response.getWriter();
			out.print(res);
			out.flush();
		} catch (Exception e) {
			response.setContentType("text/plain");
			out = response.getWriter();
			out.println("error in SendComment");
			out.println(e);
			e.printStackTrace();
		}
	}
}
