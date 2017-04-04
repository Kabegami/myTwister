package TestsPosts;

import org.json.JSONObject;

import ServicePosts.DeletPostService;


public class TestDeletOnePost {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  DeletPostService.deletPost("bijanvc46ntk46of76k59nm7qkprql","5");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}

}
