package TestUser;

import org.json.JSONObject;

import ServiceUser.RequestRecoveryAccountService;

public class TestRequestRecouveryAccount {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  RequestRecoveryAccountService.sendingMailRecouvery("yoones","yoones.mirhosseini@gmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}
}
