
package meta_db_model.relation_db;

public class RelationDBModel {

	private Database database;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public RelationDBModel() {
	}

	/**
	 * 
	 * @param database
	 */
	public RelationDBModel(Database database) {
		super();
		this.database = database;
	}

	public Database getDatabase() {
		return database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

}
