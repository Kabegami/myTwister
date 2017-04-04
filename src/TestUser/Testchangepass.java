package TestUser;

import org.json.JSONObject;
import ServiceUser.ChangePasswordService;

public class Testchangepass{
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  ChangePasswordService.changePass("yoonestc87camm3q3devrdn3kj0f4ig1","1234");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}
}
