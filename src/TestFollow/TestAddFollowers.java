package TestFollow;

import org.json.JSONObject;

import ServiceFollow.FollowService;

public class TestAddFollowers {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  FollowService.addFollow("bijan4nbmlsim8rklff7desbhl3bs5t",21);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}
}
