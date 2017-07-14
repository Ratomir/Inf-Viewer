
package meta_db_model.relation_db;

import java.util.ArrayList;
import java.util.List;

import meta_db_model.BaseDBStorageModel;
import meta_db_model.Connection;

public class Database extends BaseDBStorageModel<Table> {

	private List<Table> table = new ArrayList<Table>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Database() {
	}

	/**
	 * 
	 * @param connection
	 * @param table
	 */
	public Database(Connection connection, List<Table> table) {
		super(connection);
		this.setConnection(connection);
		this.table = table;
	}

	public List<Table> getTable() {
		return table;
	}

	public void setTable(List<Table> table) {
		this.table = table;
	}

	@Override
	public List<Table> getTableFromMetaScheme() {
		return getTable();
	}

}
