package TestsPosts;

import org.json.JSONObject;

import ServicePosts.ListCommentsServiceForOnePost;

public class TestListCommentForOnePost {
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  ListCommentsServiceForOnePost.listecommentsBypost("lolou90hp071ph2oa263q85da5a88j","5");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}

}
