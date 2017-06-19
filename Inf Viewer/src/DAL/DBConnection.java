package DAL;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;

public class DBConnection {

	public static MongoClient mongoConnection = null;
	
	public DBConnection(){
			createMongoDbConnection();
	}
	
	public static MongoClient getConnection(){
		
		if(mongoConnection == null){
			createMongoDbConnection();
		}
		
		return mongoConnection;
	}
	
	public static DB getDb()
	{
		return getConnection().getDB("infviewer");
	}
	
	public static DB getDbByName(String name){
		return getConnection().getDB(name);
	}
	
	public static DBCollection getCollection(String nameOfCollection){
		return getDb().getCollection(nameOfCollection);
	}
	
	public static DBObject findOne(String collection, BasicDBObject object){
		DBCollection dbCollection = getCollection(collection);
		return dbCollection.findOne(object);
	}
	
	private static void createMongoDbConnection(){
		try {
			mongoConnection = new MongoClient(new MongoClientURI("xxxx"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
