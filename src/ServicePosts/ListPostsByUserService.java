package ServicePosts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Tools.Error;
import bd.AuthorizationTools;
import bd.BDException;
import bd.DBStatic;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class ListPostsByUserService {

	public static JSONObject listePostsByUser(String key, String id) throws BDException, JSONException {
		JSONObject finalposts = new JSONObject();
		JSONArray posts = new JSONArray();
		if (key == null || id == null){
			return Error.serviceKO("Wrong arguments", -1);
		}
		try {
			int id1 = AuthorizationTools.getIdUSerbyKey(key);
			if (id1 == -1){
				return Error.serviceKO("User is not logged in", 1);
			}
			
			if(! AuthorizationTools.userExistsByID(Integer.parseInt(id))){
				return Error.serviceKO("User witch you want see his posts is not existe", 2);
			}
			else {
				
				Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
				DB db = m.getDB(DBStatic.mongo_db);
				DBCollection collection = db.getCollection("posts");
				BasicDBObject whereQuery = new BasicDBObject();
				whereQuery.put("id", Integer.parseInt(id));
				DBCursor cursor = collection.find(whereQuery).sort(new BasicDBObject("date", -1));
				while(cursor.hasNext()) {
				    posts.put(new JSONObject(cursor.next().toString()));
				}
				m.close();
				AuthorizationTools.updateSession(key);
				finalposts.put("Posts:", posts);
				return finalposts;
			}
		} catch(Exception e){
			throw new BDException("can't find liste post cause , exception = " + e);
		}
	}

}
