package TestsPosts;

import org.json.JSONObject;

import ServicePosts.DeletCommentService;

public class TestDeletComment {
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  DeletCommentService.deletcomment("yoones2fnskfn7u255lb7jovecl1lqojr","1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}

}
