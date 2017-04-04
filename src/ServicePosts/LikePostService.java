package ServicePosts;

import java.net.UnknownHostException;
import java.sql.SQLException;
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
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

import Tools.Error;
import Tools.MongoDbTools;

public class LikePostService {
	public static JSONObject likePost(String key,String id_post) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, BDException{
		if (key == null ||  id_post == null){
			return Error.serviceKO("Wrong arguments", -1);
		}
		
		try {
			int id = AuthorizationTools.getIdUSerbyKey(key);
			if (id == -1){
				return Error.serviceKO("User is not logged in", 1);
			}
			if (!PostsTools.postexiste(id_post)){
				return Error.serviceKO("post is not existe", 2);
			}
			else {
				String login = AuthorizationTools.getLoginUserbyId(id);
		
				
				Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
				DB db = m.getDB(DBStatic.mongo_db);
				DBCollection collection = db.getCollection("likes");
				BasicDBObject whereQuery = new BasicDBObject();
				whereQuery.put("id_post", Integer.parseInt(id_post));
				whereQuery.put("id",id);
				DBCursor coursor = collection.find(whereQuery);
				if(coursor.hasNext()){
					m.close();
					return Error.serviceKO("you have already like this post", 3);
				}
				BasicDBObject c = new BasicDBObject();	
				c.put("id_like", MongoDbTools.getNextSequencelike());
				c.put("id", id);
				c.put("login", login);
				c.put("id_post", Integer.parseInt(id_post));
				c.put("date", new Date());
				
				collection.insert(c);
				
				m.close();
				JSONObject res = new JSONObject(c.toString());
				
				AuthorizationTools.updateSession(key);

				return res;
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return Error.serviceKO("erreur UnknownHostException", 10000);
		} catch (SQLException e) {
			e.printStackTrace();
			return Error.serviceKO("erreur SQL", 1000);
		}
		
	}

}
