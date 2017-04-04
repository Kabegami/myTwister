package ServicePosts;

import org.json.JSONException;
import org.json.JSONObject;

import Tools.Error;
import bd.AuthorizationTools;
import bd.BDException;
import bd.PostsTools;



public class GetPostsService {
	public static JSONObject getMessage(String key, String query, String from, String id_max, String id_min, String nb) throws JSONException, BDException {
		if( key == null || query == null || from == null || id_max == null || id_min == null || nb == null){
			return Error.serviceKO("Wrong arguments", -1);
		}
		try{
			int id = AuthorizationTools.getIdUSerbyKey(key);
			if (id == -1){
				return Error.serviceKO("User is not logged in", 1);
			}
			int id_from = Integer.parseInt(from);
			if(!AuthorizationTools.userExistsByID(id_from)){
				return Error.serviceKO("User is not existe", 2);
			}
			int max = Integer.parseInt(id_max);
			int min = Integer.parseInt(id_min);
			int cpt = Integer.parseInt(nb);
			AuthorizationTools.updateSession(key);
			return PostsTools.allposts(id_from,query ,cpt,max,min);
			
			
			
		}
		catch(Exception e){
			throw new BDException("can't get post , exception = " + e);
		}
		
	}
		

}
