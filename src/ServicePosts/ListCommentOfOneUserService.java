package ServicePosts;

import javax.servlet.annotation.WebServlet;

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
@WebServlet("/posts/listcommentsuser ")

public class ListCommentOfOneUserService {

	public static JSONObject listecommentsByUser(String key, String id) throws JSONException, BDException {
		JSONObject finalcomments = new JSONObject();
		JSONArray comments = new JSONArray();
		if (key == null || id == null){
			return Error.serviceKO("Wrong arguments", -1);
		}
		try {
			int id1 = AuthorizationTools.getIdUSerbyKey(key);
			if (id1 == -1){
				return Error.serviceKO("User is not logged in", 1);
			}
			
			if(! AuthorizationTools.userExistsByID(Integer.parseInt(id))){
				return Error.serviceKO("User witch you want see his comments is not existe", 2);
			}
			else {
				
				Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
				DB db = m.getDB(DBStatic.mongo_db);
				DBCollection collection = db.getCollection("comments");
				BasicDBObject whereQuery = new BasicDBObject();
				whereQuery.put("id", Integer.parseInt(id));
				DBCursor cursor = collection.find(whereQuery).sort(new BasicDBObject("date", -1));
				while(cursor.hasNext()) {
				    comments.put(new JSONObject(cursor.next().toString()));
				}
				m.close();
				AuthorizationTools.updateSession(key);
				finalcomments.put("Comments:", comments);
				return finalcomments;
			}
		} catch(Exception e){
			throw new BDException("can't recouvery account , exception = " + e);
		}
	}

}
