package TestUser;

import org.json.JSONObject;

import ServiceUser.LogoutService;

public class TetsLougOut {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  LogoutService.logout("yoonestc87camm3q3devrdn3kj0f4ig1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}
}
