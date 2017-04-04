package ServletsPosts;

/**
 * 
 * @api {post} /sendpost sending post
 * @apiVersion 0.1.0
 * @apiName SendPost
 * @apiGroup posts
 * 
 * 
 * @apiParam  {String} key
 * @apiParam  {String} message
 * 
 * @apiSuccessExample {json} Succès:
 * 			{date,id_post,id,text,username}
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


import ServicePosts.SendPostService;


public class SendPost extends HttpServlet{

	private static final long serialVersionUID = 1L;
	public SendPost() {
		
	}
	
	protected void doPost(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = null;
		String key = request.getParameter("key");
		String post = request.getParameter("message");
			 
		JSONObject res = new JSONObject();
		try {
			res = SendPostService.writhePost(key, post);
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
