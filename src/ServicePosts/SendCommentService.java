package ServicePosts;


import java.util.Date;

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
import Tools.MongoDbTools;


public class SendCommentService {
	public static JSONObject writheComment(String key, String comment,String id_post) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, BDException{
		if (key == null || comment == null || id_post == null){
			return Error.serviceKO("Wrong arguments", -1);
		}
		
		try {
			int id = AuthorizationTools.getIdUSerbyKey(key);
			if (id == -1){
				return Error.serviceKO("User is not logged in", 1);
			}
			if(!PostsTools.postexiste(id_post)){
				return Error.serviceKO("Post not existe", 2);
			}
			else {
				String login = AuthorizationTools.getLoginUserbyId(id);
				
				Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
				DB db = m.getDB(DBStatic.mongo_db);
				DBCollection collection = db.getCollection("comments");
				
				BasicDBObject c = new BasicDBObject();	
				
				c.put("id_comment", MongoDbTools.getNextSequenceComment());
				c.put("id_post", Integer.parseInt(id_post));
				c.put("id",id);
				c.put("username", login);
				c.put("text", comment);
				c.put("date", new Date());
				collection.insert(c);
				
				m.close();
				JSONObject res = new JSONObject(c.toString());
				
				AuthorizationTools.updateSession(key);

				return res;
			}
		} catch(Exception e){
			throw new BDException("can't send comment , exception = " + e);
		}
	}

}
