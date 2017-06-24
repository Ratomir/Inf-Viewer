package dbmodel;

import com.google.gson.Gson;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class DatabaseTypeModel extends BaseDbModel<DatabaseTypeModel> {

	public String name;

	@Override
	public DBObject createDbObject() {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

		docBuilder.append("name", name);

		return docBuilder.get();
	}

	@Override
	public DatabaseTypeModel toObject(DBObject objectMongo) {
		DatabaseTypeModel model = (new Gson()).fromJson(objectMongo.toString(), DatabaseTypeModel.class);
		return model;
	}

}
