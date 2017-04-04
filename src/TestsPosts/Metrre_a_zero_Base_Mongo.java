package TestsPosts;

import bd.DBStatic;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class Metrre_a_zero_Base_Mongo {
	public static void main(String[] args){
		try{
			Mongo m = new Mongo(DBStatic.mongo_host, DBStatic.mongo_port);
			DB db = m.getDB(DBStatic.mongo_db);
			DBCollection collection = db.getCollection("posts");
			collection.drop();
			collection = db.getCollection("likes");
			collection.drop();
			collection = db.getCollection("comments");
			collection.drop();
			collection = db.getCollection("counter_posts");
			collection.drop();
			collection = db.getCollection("counter_comments");
			collection.drop();
			collection = db.getCollection("countour_likes");
			collection.drop();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
