package ServicePosts;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import bd.AuthorizationTools;
import bd.BDException;
import bd.DBStatic;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import Tools.Error;
import Tools.MongoDbTools;

public class SendPostService {
	
	public static JSONObject writhePost(String key, String post) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, BDException {
		
		if (key == null || post == null){
			return Error.serviceKO("Wrong arguments", -1);
		}
		
		try {
			int id = AuthorizationTools.getIdUSerbyKey(key);
			if (id == -1){
				return Error.serviceKO("User is not logged in", 1);
			}
			else {
				
				String login = AuthorizationTools.getLoginUserbyId(id);
				
				Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
				DB db = m.getDB(DBStatic.mongo_db);
				DBCollection collection = db.getCollection("posts");
				
				BasicDBObject comment = new BasicDBObject();	
				
				comment.put("id_post", MongoDbTools.getNextSequencePosts());
				comment.put("id",id);
				comment.put("username", login);
				comment.put("text", post);
				comment.put("date", new Date());
				collection.insert(comment);
				m.close();
				JSONObject res = new JSONObject(comment.toString());
				AuthorizationTools.updateSession(key);

				return res;
			}
		} 
		catch(Exception e){
			throw new BDException("can't recouvery account , exception = " + e);
		}
	}

}
