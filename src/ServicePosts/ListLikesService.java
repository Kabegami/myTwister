package ServicePosts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Tools.Error;
import bd.AuthorizationTools;
import bd.BDException;
import bd.DBStatic;
import bd.PostsTools;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class ListLikesService  {

	public static JSONObject listelikes(String key, String id_post) throws JSONException, BDException {
		JSONObject finallikes = new JSONObject();
		JSONArray likes = new JSONArray();
		if (key == null || id_post == null){
			return Error.serviceKO("Wrong arguments", -1);
		}
		try {
			int id1 = AuthorizationTools.getIdUSerbyKey(key);
			if (id1 == -1){
				return Error.serviceKO("User is not logged in", 1);
			}
			
			if(! PostsTools.postexiste(id_post)){
				return Error.serviceKO("post witch you want see his likes is not existe", 2);
			}
			else {
				
				Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
				DB db = m.getDB(DBStatic.mongo_db);
				DBCollection collection = db.getCollection("likes");
				BasicDBObject whereQuery = new BasicDBObject();
				whereQuery.put("id_post",Integer.parseInt(id_post));
				DBCursor cursor = collection.find(whereQuery).sort(new BasicDBObject("date", -1));
				while(cursor.hasNext()) {
				    likes.put(new JSONObject(cursor.next().toString()));
				}
				m.close();
				AuthorizationTools.updateSession(key);
				finallikes.put("likes:", likes);
				return finallikes;
			}
		} catch(Exception e){
			throw new BDException("can't recouvery account , exception = " + e);
		}
	}

}
