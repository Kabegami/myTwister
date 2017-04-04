package TestFollow;

import org.json.JSONObject;

import ServiceFollow.ListeFollowedService;
/*c'est service nous donne une liste des user qui sont suivis par le user avec id : id_followed*/
public class TestListFolloweds {
	
	public static void main(String[]  args){
		
		JSONObject rj = new JSONObject();
		try {		
			rj =  ListeFollowedService.listeFolloweds("bijan4nbmlsim8rklff7desbhl3bs5t",21);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rj);
	}

}
