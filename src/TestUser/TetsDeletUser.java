package TestUser;

import org.json.JSONObject;

import ServiceUser.DeletUserService;

public class TetsDeletUser {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  DeletUserService.deletUser("bijan4nbmlsim8rklff7desbhl3bs5t");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}
}
