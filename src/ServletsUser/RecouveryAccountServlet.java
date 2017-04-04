package ServletsUser;
/**
 * 
 * @api {get} /recouveryaccount Reccouvery account in case lost password 
 * @apiVersion 0.1.0
 * @apiName RecoveryAccountServlet
 * @apiGroup user
 * 
 * @apiParam  {String} clef
 * 
 * @apiSuccessExample {json} Succès:
 * 			{}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 you are already logged in
 * @apiError (Error) 2 problem sending mail pleas try later
 * @apiError (Error) 3 There is not such request recouvery account
 * 
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ServiceUser.RecouveryAccountService;



public class RecouveryAccountServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public RecouveryAccountServlet() {
		 
	 }
	
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
		
				PrintWriter out = null;
				String clef = request.getParameter("clef");
				JSONObject res = new JSONObject();
				
				try {
					res = RecouveryAccountService.recouveryAccount(clef);
					response.setContentType("application/json");     
					out = response.getWriter();
					out.print(res);
					out.flush();
				} catch (Exception e) {
					response.setContentType("text/plain");
					out = response.getWriter();
					out.println("error in RecouveryAccountServlet");
					out.println(e);
					e.printStackTrace();
				}
	}
}
