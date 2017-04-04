package bd;


import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;














import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class PostsTools {

	public static boolean postexiste(String id_post) throws BDException {
		if ( id_post == null){
			return false;
		}
		boolean e = false;
		try {
			Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
			DB db = m.getDB(DBStatic.mongo_db);
			DBCollection collection = db.getCollection("posts");
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("id_post", Integer.parseInt(id_post));
			DBCursor cursor = collection.find(whereQuery);
			if(cursor.hasNext()){
				e = true;
			}
		} catch(Exception ex){
			throw new BDException("can't find post cause: , exception = " + ex);
		}
		return e;
	}

	public static void deletAllLike(String id_post) throws UnknownHostException {
		if ( id_post == null){
			return;
		}
		Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
		DB db = m.getDB(DBStatic.mongo_db);
		DBCollection collection = db.getCollection("likes");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id_post", Integer.parseInt(id_post));
		collection.remove(whereQuery);
		m.close();
}
	public static void deletAllComments(String id_post) throws UnknownHostException {
		if (id_post == null){
			return;
		}
		Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
		DB db = m.getDB(DBStatic.mongo_db);
		DBCollection collection = db.getCollection("comments");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id_post", Integer.parseInt(id_post));
		collection.remove(whereQuery);
		m.close();
	}
	public static JSONObject getAllComments(int id_post) throws UnknownHostException, JSONException {
		JSONObject finalcomments = new JSONObject();
		JSONArray comments = new JSONArray();
		Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
		DB db = m.getDB(DBStatic.mongo_db);
		DBCollection collection = db.getCollection("comments");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id_post",id_post);
		DBCursor cursor = collection.find(whereQuery).sort(new BasicDBObject("date", -1));
		while(cursor.hasNext()) {
			JSONObject auteur = new JSONObject();
			JSONObject comment = new JSONObject();
			DBObject tobj = cursor.next(); 
			int id_comment = (Integer) tobj.get("id_comment");
			int id = (Integer) tobj.get("id");
			String login = (String) tobj.get("username");
			Date date = (Date) tobj.get("date");
			String text = (String) tobj.get("text");
			
			auteur.put("id_auteur_comment", id);
			auteur.put("login_auteur_comment", login);
			
			comment.put("id_comment",id_comment);
			comment.put("text",text);
			comment.put("date",date);
			
			JSONArray all = new JSONArray();
			all.put(comment);
			all.put(auteur);
			
		    comments.put(all);
		}
		m.close();
		finalcomments.put("Comments:", comments);
		return finalcomments;
	}
	public static JSONObject getAlllikes(int id_post) throws UnknownHostException, JSONException {
		JSONObject finallikes = new JSONObject();
		JSONArray likes = new JSONArray();
		Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
		DB db = m.getDB(DBStatic.mongo_db);
		DBCollection collection = db.getCollection("likes");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id_post",id_post);
		DBCursor cursor = collection.find(whereQuery).sort(new BasicDBObject("date", -1));
		while(cursor.hasNext()) {
			JSONObject auteur = new JSONObject();
			JSONObject like = new JSONObject();
			DBObject tobj = cursor.next();  
			int id_like = (Integer) tobj.get("id_like");
			int id = (Integer) tobj.get("id");
			String login = (String) tobj.get("login");
			Date date = (Date) tobj.get("date");
			
			auteur.put("id_liker", id);
			auteur.put("login_liker", login);
			
			like.put("id_like",id_like);
			like.put("date",date);
			
			JSONArray all = new JSONArray();
			all.put(like);
			all.put(auteur);
			
		    likes.put(all);
		}
		m.close();
		finallikes.put("Likes:", likes);
		return finallikes;
	}

	public static JSONObject allposts(int id_from, String query, int cpt, int max , int min) throws UnknownHostException, JSONException {
		if(cpt == -1){
			cpt = 10000000; //INF
		}
		
		//flag = true non limites
		boolean flag = true; 
		if(max != -1 && min != -1){
			//flag = false with limites
			flag = false;
		}
	
		JSONObject finalposts = new JSONObject();
		JSONArray posts = new JSONArray();
		
		Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
		DB db = m.getDB(DBStatic.mongo_db);
		DBCollection collection = db.getCollection("posts");
	
		
		BasicDBObject like = new BasicDBObject();
		like.put("text", Pattern.compile(query));
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id_from);
		
		BasicDBObject queryToDo = new BasicDBObject();
		ArrayList<BasicDBObject> and = new ArrayList<BasicDBObject>();
		and.add(whereQuery);
		and.add(like);
		queryToDo.put("$and", and);


		int i = 0;
		DBCursor cursor = collection.find(queryToDo).sort(new BasicDBObject("date", -1));
		while(cursor.hasNext() && i<= cpt) {
			
			JSONObject auteur = new JSONObject();
			JSONObject post = new JSONObject();
			
			DBObject tobj = cursor.next();  
			int id_post = (Integer) tobj.get("id_post"); 
			int id = (Integer) tobj.get("id"); 
			String login = (String) tobj.get("username");
			String text = (String) tobj.get("text");
			Date date = (Date) tobj.get("date");
			
			if(flag){
	
				auteur.put("id_auteur_post",id);
				auteur.put("login_auteur_post",login);
				
				post.put("id_post", id_post);
				post.put("text", text);
				post.put("date", date);
				
				JSONObject comments  = getAllComments(id_post);
				JSONObject likes  = getAlllikes(id_post);
				
				JSONArray all = new JSONArray();
				all.put(post);
				all.put(auteur);
				all.put(comments);
				all.put(likes);
				
				posts.put(all);
				
				i += 1;
				
			}
			else{
				
				 if(id_post >= min && id_post <= max){
					 
					 auteur.put("id_auteur_post",id);
					 auteur.put("login_auteur_post",login);
				
					 post.put("id_post", id_post);
					 post.put("text", text);
					 post.put("date", date);
				
					 JSONObject comments  = getAllComments(id_post);
					 JSONObject likes  = getAlllikes(id_post);
				
					 JSONArray all = new JSONArray();
					 all.put(post);
					 all.put(auteur);
					 all.put(comments);
					 all.put(likes);
				
					 posts.put(all);
				
					 i += 1;
				 }
			}
			
		}
		finalposts.put("Posts:", posts);
		return finalposts;
	}
}
