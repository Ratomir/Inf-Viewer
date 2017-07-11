package meta_db_model.no_relation_db;

import java.util.List;

import meta_db_model.BaseDBStorageModel;
import meta_db_model.Connection;

public class NoRelationDBModel extends BaseDBStorageModel {
	private Connection connection;
	private List<Collection> collection = null;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public NoRelationDBModel() {
	}

	/**
	 * 
	 * @param connection
	 * @param collection
	 */
	public NoRelationDBModel(Connection connection, List<Collection> collection) {
		super();
		this.connection = connection;
		this.collection = collection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public List<Collection> getCollection() {
		return collection;
	}

	public void setCollection(List<Collection> collection) {
		this.collection = collection;
	}
}
