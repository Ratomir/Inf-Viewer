package dbmodel;

import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class RoleModel extends BaseDbModel<RoleModel> {

	public String name;

	@Override
	public DBObject createDbObject() {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		
		docBuilder.append("name", name);

		return docBuilder.get();
	}

	@Override
	public RoleModel toObject(DBObject objectMongo) {
		RoleModel model = (new Gson()).fromJson(objectMongo.toString(), RoleModel.class);
		return model;
	}

	public static BasicDBList mapToList(List<RoleModel> objectToList) {

		BasicDBList listOfRoles = new BasicDBList();

		for (RoleModel roleModel : objectToList) {
			listOfRoles.add(roleModel.createDbObject());
		}

		return listOfRoles;
	}
}
