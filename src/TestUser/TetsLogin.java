package TestUser;

import org.json.JSONObject;

import ServiceUser.LoginService;

public class TetsLogin {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  LoginService.login("yoones","1234");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}
}
