package dbmodel;

import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class UserModel extends BaseDbModel<UserModel> {
	public String firstName;
	public String lastName;
	public int age;
	public String email;
	public String username;
	public String password;

	public List<RoleModel> roles;

	@Override
	public DBObject createDbObject() {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

		docBuilder.append("firstName", firstName);
		docBuilder.append("lastName", lastName);
		docBuilder.append("age", age);
		docBuilder.append("email", email);
		docBuilder.append("username", username);
		docBuilder.append("password", password);

		docBuilder.append("roles", RoleModel.mapToList(roles));

		return docBuilder.get();
	}

	@Override
	public UserModel toObject(DBObject objectMongo) {
		if (objectMongo == null) {
			return null;
		}
		return (new Gson()).fromJson(objectMongo.toString(), UserModel.class);
	}
}
