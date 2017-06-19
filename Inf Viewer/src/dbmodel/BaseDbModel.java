package dbmodel;

import com.mongodb.DBObject;

public abstract class BaseDbModel<T> {

	public abstract DBObject createDbObject();
	
	public abstract T toObject(DBObject objectMongo);
	
}
