package dbmodel;

import java.util.List;

import com.google.gson.Gson;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class UserModel extends BaseDbModel<UserModel> {
	public String firstName;
	public String lastName;
	public String age;
	public String email;
	public String userName;
	public String password;
	
	public List<RoleModel> roles;

	@Override
	public DBObject createDbObject() {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		
		docBuilder.append("firstName", firstName);
		docBuilder.append("lastName", lastName);
		docBuilder.append("age", age);
		docBuilder.append("email", email);
		docBuilder.append("userName", userName);
		docBuilder.append("password", password);
		
		return docBuilder.get();
	}

	@Override
	public UserModel toObject(DBObject object) {
		UserModel model = (new Gson()).fromJson(object.toString(), UserModel.class);
		return model;
	}
}
