package ServicePosts;

import org.json.JSONException;
import org.json.JSONObject;

import bd.AuthorizationTools;
import bd.BDException;
import bd.DBStatic;
import bd.PostsTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

import Tools.Error;

public class DeletPostService {
	public static JSONObject deletPost(String key, String id_post) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, BDException {
	
		if (key == null || id_post == null){
			return Error.serviceKO("Wrong arguments", -1);
		}
		try {
			int id = AuthorizationTools.getIdUSerbyKey(key);
			if (id == -1){
				return Error.serviceKO("User is not logged in", 1);
			}
			else {
				Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
				DB db = m.getDB(DBStatic.mongo_db);
				DBCollection collection = db.getCollection("posts");
				BasicDBObject whereQuery = new BasicDBObject();
				whereQuery.put("id_post", Integer.parseInt(id_post));
				whereQuery.put("id", id);
				collection.remove(whereQuery);
				m.close();
				PostsTools.deletAllLike(id_post);
				PostsTools.deletAllComments(id_post);
				JSONObject res = new JSONObject();
				AuthorizationTools.updateSession(key);
				return res;
			}
		} catch(Exception e){
			throw new BDException("can't recouvery account , exception = " + e);
		}
	}
}
