package TestsPosts;

import org.json.JSONObject;

import ServicePosts.SendPostService;


public class TestSendPost {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  SendPostService.writhePost("yoones2ha8chrqogv6vp8jvi7dqt63r3r","salut by yoones2 3.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}
}
