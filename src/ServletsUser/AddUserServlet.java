package ServletsUser;

/**
 * 
 * @api {get} /add adding  user 
 * @apiVersion 0.1.0
 * @apiName AddUserServlet
 * @apiGroup user
 * 
 * 
 * @apiParam  {String} login
 * @apiParam  {String} pass
 * @apiParam  {String} name
 * @apiParam  {String} surname
 * @apiParam  {String} mail
 * @apiParam  {String} bd / birthday
 * 
 * 
 * @apiSuccessExample {json} Succès:
 * 			{}
 * 
 * @apiError (Error) -1 Mauvais arguments
 * @apiError (Error) 1 Utilisateur avec ce login exist déja
 * @apiError (Error) 2 Utilisateur avec ce adress mail existe déja
 * @apiError (Error) 3 Utilisateurs temporaire avec ce login existe déja
 * @apiError (Error) 4 Utilisateurs temporaire avec ce adress mail existe déja
 * 
 */

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import ServiceUser.AddUserService;


public class AddUserServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public AddUserServlet() {
		 
	 }
	
	 protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String mail = request.getParameter("mail");
		String birth_date = request.getParameter("bd");
		
		PrintWriter out  = null;
		JSONObject rj = new JSONObject();
		
		try {
			rj = AddUserService.AddUser(login, pass, name, surname, mail,birth_date);
			response.setContentType("application/json");     
			out = response.getWriter();
			out.print(rj);
			out.flush();
		} catch (Exception e) {
			System.out.println("hello");
			response.setContentType("text/plain");
			out = response.getWriter();
			out.println("erreur Servlet AddUser");
			out.println(e);
			e.printStackTrace();
			}
	}
}