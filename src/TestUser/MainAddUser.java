package TestUser;

import org.json.JSONObject;

import ServiceUser.AddUserService;

public class MainAddUser{
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  AddUserService.AddUser("bijan","1234","bijan","taher","taherpourbijan@gmail.com","1986-09-11");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}
}
