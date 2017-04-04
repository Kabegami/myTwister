package TestsPosts;

import org.json.JSONObject;

import ServicePosts.ListCommentOfOneUserService;

public class TestListCommentOfOneUser {
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  ListCommentOfOneUserService.listecommentsByUser("lolou90hp071ph2oa263q85da5a88j","10");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}

}
