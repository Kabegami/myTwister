package ServicePosts;

import org.json.JSONException;
import org.json.JSONObject;

import Tools.Error;
import bd.AuthorizationTools;
import bd.BDException;
import bd.DBStatic;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class DeletCommentService {

	public static JSONObject deletcomment(String key, String id_comment) throws BDException, JSONException {
		if (key == null || id_comment == null){
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
				DBCollection collection = db.getCollection("comments");
				BasicDBObject whereQuery = new BasicDBObject();
				whereQuery.put("id_comment", Integer.parseInt(id_comment));
				whereQuery.put("id", id);
				collection.remove(whereQuery);
				m.close();
				JSONObject res = new JSONObject();
				AuthorizationTools.updateSession(key);
				return res;
			}
		} catch(Exception e){
			throw new BDException("can't recouvery account , exception = " + e);
		}
	}
}
