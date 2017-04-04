package TestsPosts;

import org.json.JSONObject;

import ServicePosts.ListPostsByUserService;

public class TestListePostByUser {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  ListPostsByUserService.listePostsByUser("lolots1efeo4tnou1ppm8nl7l3kcb6","21");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}

}
