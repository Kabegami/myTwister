package TestsPosts;

import org.json.JSONObject;

import ServicePosts.SendCommentService;

public class TestSendComment {
	public static void main(String[]  args){
		JSONObject rj = new JSONObject();
		try {		
			rj =  SendCommentService.writheComment("yoones2ha8chrqogv6vp8jvi7dqt63r3r","comments by yoones2 for yoones 3", "3");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}

}
