package TestsPosts;

import org.json.JSONObject;

import ServicePosts.GetPostsService;




public class TestGetMessage {
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  GetPostsService.getMessage("lolots1efeo4tnou1ppm8nl7l3kcb6","","21","3","3","-1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}

}