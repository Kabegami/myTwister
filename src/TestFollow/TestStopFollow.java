package TestFollow;

import org.json.JSONObject;

import ServiceFollow.StopFollowService;

public class TestStopFollow {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  StopFollowService.stopFollow("bijan4nbmlsim8rklff7desbhl3bs5t",21);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}

}
