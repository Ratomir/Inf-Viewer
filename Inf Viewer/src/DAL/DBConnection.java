package DAL;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

public class DBConnection {

	public static MongoClient mongoConnection = null;

	public DBConnection() {
		createMongoDbConnection();
	}

	public static MongoClient getConnection() {

		if (mongoConnection == null) {
			createMongoDbConnection();
		}

		return mongoConnection;
	}

	public static DB getDb() {
		return getConnection().getDB("infviewer");
	}

	public static DB getDbByName(String name) {
		return getConnection().getDB(name);
	}

	public static DBCollection getCollection(String nameOfCollection) {
		return getDb().getCollection(nameOfCollection);
	}
	
	public static DBCursor find(String nameOfCollection){
		DBCollection dbCollection = getCollection(nameOfCollection);
		return dbCollection.find();
	}
	
	public static DBCursor findByQuery(String nameOfCollection, BasicDBObject object){
		DBCollection dbCollection = getCollection(nameOfCollection);
		return dbCollection.find(object);
	}

	public static DBObject findOne(String collection, BasicDBObject object) {
		DBCollection dbCollection = getCollection(collection);
		return dbCollection.findOne(object);
	}
	
	public static WriteResult deleteOne(String collection, BasicDBObject query){
		DBCollection dbCollection = getCollection(collection);
		return dbCollection.remove(query);
	}

	private static void createMongoDbConnection() {
		try {
			mongoConnection = new MongoClient("localhost", 27017);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
