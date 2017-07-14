package meta_db_model.no_relation_db;

import java.util.List;

import meta_db_model.BaseDBStorageModel;
import meta_db_model.Connection;

public class NoRelationDBModel extends BaseDBStorageModel<Collection> {
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
		super(connection);
		this.collection = collection;
	}

	public List<Collection> getCollection() {
		return collection;
	}

	public void setCollection(List<Collection> collection) {
		this.collection = collection;
	}

	@Override
	public List<Collection> getTableFromMetaScheme() {
		return getCollection();
	}
}
