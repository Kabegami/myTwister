package TestsPosts;

import org.json.JSONObject;

import ServicePosts.LikePostService;

public class TestLikePost {
	public static void main(String[]  args){
		JSONObject rj = new JSONObject();
		try {		
			rj =  LikePostService.likePost("lolots1efeo4tnou1ppm8nl7l3kcb6", "3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}


}
