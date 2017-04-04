package Tools;

import java.net.UnknownHostException;

import bd.DBStatic;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDbTools {
	
	
	public static Object getNextSequencePosts() throws UnknownHostException{
		
		String id_posts = "id_post";
	    MongoClient mongoClient = new MongoClient(DBStatic.mongo_host, DBStatic.mongo_port);
	    DB database = mongoClient.getDB(DBStatic.mongo_db);
	    DBCollection countersCollectionposts = database.getCollection("counter_posts");
	    if (countersCollectionposts.count() == 0) {
	    	createCountersCollectionposts();
	    }
	    BasicDBObject searchQuery = new BasicDBObject("_id_post", id_posts);
	    BasicDBObject increase = new BasicDBObject("seq", 1);
	    BasicDBObject updateQuery = new BasicDBObject("$inc", increase);
	    DBObject result = countersCollectionposts.findAndModify(searchQuery, null, null,
	            false, updateQuery, true, false);
	    return  result.get("seq");
}
	
	public static Object getNextSequenceComment( ){
		DBCollection countersCollection = null;
		DBObject result = null;
		try {
	        MongoClient mongoClient = new MongoClient(DBStatic.mongo_host, DBStatic.mongo_port);
	        DB database = mongoClient.getDB(DBStatic.mongo_db);
	        countersCollection= database.getCollection("counter_comments");
	        if (countersCollection.count() == 0) {
		    	createCountersCollectioncomments();
		    }
	        String id_comments = "id_commenst";
	        BasicDBObject searchQuery = new BasicDBObject("_id_comments", id_comments);
		    BasicDBObject increase = new BasicDBObject("seq", 1);
		    BasicDBObject updateQuery = new BasicDBObject("$inc", increase);
		    result = countersCollection.findAndModify(searchQuery, null, null,
		            false, updateQuery, true, false);
	        
	    } catch (UnknownHostException e) {
	        e.printStackTrace();
	    }
		
		return result.get("seq");

		
	}
	
	public static Object getNextSequencelike(){
		DBCollection countersCollection = null;
		DBObject result = null;
		try {
	        MongoClient mongoClient = new MongoClient(DBStatic.mongo_host, DBStatic.mongo_port);
	        DB database = mongoClient.getDB(DBStatic.mongo_db);
	 
	        		
	        countersCollection= database.getCollection("countour_likes");
	        if (countersCollection.count() == 0) {
		    	createCountersCollectionlikes();
		    }
	        String id_likes = "id_likes";
	        BasicDBObject searchQuery = new BasicDBObject("_id_likes", id_likes);
		    BasicDBObject increase = new BasicDBObject("seq", 1);
		    BasicDBObject updateQuery = new BasicDBObject("$inc", increase);
		    result = countersCollection.findAndModify(searchQuery, null, null,
		            false, updateQuery, true, false);
	        
	    } catch (UnknownHostException e) {
	        e.printStackTrace();
	    }
		
		return result.get("seq");

		
	}
	public static void createCountersCollectionposts() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(DBStatic.mongo_host, DBStatic.mongo_port);
	    DB db = mongoClient.getDB(DBStatic.mongo_db);
	    DBCollection countersCollectionposts = db.getCollection("counter_posts");
	    BasicDBObject document = new BasicDBObject();
	    document.append("_id_post", "id_post");
	    document.append("seq", 0);
	    countersCollectionposts.insert(document);
	    
	    
	}
	public static void createCountersCollectioncomments() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(DBStatic.mongo_host, DBStatic.mongo_port);
	    DB db = mongoClient.getDB(DBStatic.mongo_db);
	    DBCollection countersCollection = db.getCollection("counter_comments");
	    BasicDBObject document = new BasicDBObject();
	    document.append("_id_comments", "id_commenst");
	    document.append("seq", 0);
	    countersCollection.insert(document);
	}
	public static void createCountersCollectionlikes() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(DBStatic.mongo_host, DBStatic.mongo_port);
	    DB db = mongoClient.getDB(DBStatic.mongo_db);
	    DBCollection countersCollection = db.getCollection("countour_likes");
	    BasicDBObject document = new BasicDBObject();
	    document.append("_id_likes", "id_likes");
	    document.append("seq", 0);
	    countersCollection.insert(document);
	}
}

