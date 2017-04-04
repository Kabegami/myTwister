package TestsPosts;

import org.json.JSONObject;

import ServicePosts.DeletLikeService;

public class TestDeletLike {
	public static void main(String[]  args){
		JSONObject rj = new JSONObject();
		try {		
			rj =  DeletLikeService.deletlike("lolou90hp071ph2oa263q85da5a88j", "6");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}

}
