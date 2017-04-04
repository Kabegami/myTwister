package TestFollow;

import org.json.JSONObject;

import ServiceFollow.ListeFollowersService;
/*c'est service nous donne une liste des user qui suivent le user avec id : id_followed*/
public class TestListFollowers {
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  ListeFollowersService.listeFollowers("bijan4nbmlsim8rklff7desbhl3bs5t",20);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}

}
