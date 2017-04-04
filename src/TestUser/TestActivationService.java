package TestUser;

import org.json.JSONObject;

import ServiceUser.ActivationService;

public class TestActivationService {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  ActivationService.activateAccount("bijan4vk7bgsjm1b6sehl8imar809l5");
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}
}
