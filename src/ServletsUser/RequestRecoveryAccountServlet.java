package ServletsUser;
/**
 * 
 * @api {get} /requestrecouveryaccount Request for Reccouvery account in case lost password 
 * @apiVersion 0.1.0
 * @apiName RequestRecoveryAccountServlet
 * @apiGroup user
 * 
 * 
 * @apiParam  {String} login 
 * @apiParam  {String} mail
 * 
 * @apiSuccessExample {json} Succès:
 * 			{"link":url}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 Utilisateur avec ce login n'exist pas
 * @apiError (Error) 2 Utilisateur avec ce adress mail n'existe pas
 * @apiError (Error) 3 Utilisateur an'as pas Id
 * @apiError (Error) 4 mail and Id not match
 * @apiError (Error) 5 problem sending mail pleas try later
 * @apiError (Error) 6 You have a mail for recouvery you account befor in your inbox please chek your inbox
 * @apiError (Error) 7 you are already logged in
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

import ServiceUser.RequestRecoveryAccountService;


public class RequestRecoveryAccountServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public RequestRecoveryAccountServlet() {
		 
	 }
	
	protected void doGet(HttpServletRequest request,
			 HttpServletResponse response) throws ServletException, IOException {
				
				PrintWriter out = null;
				String login = request.getParameter("login");
				String mail = request.getParameter("mail");
				JSONObject res = new JSONObject();
				
				try {
					res = RequestRecoveryAccountService.sendingMailRecouvery(login,mail);
					response.setContentType("application/json");     
					out = response.getWriter();
					out.print(res);
					out.flush();
				} catch (Exception e) {
					response.setContentType("text/plain");
					out = response.getWriter();
					out.println("error in RequestRecoveryAccountServlet");
					out.println(e);
					e.printStackTrace();
				}
	}
}
