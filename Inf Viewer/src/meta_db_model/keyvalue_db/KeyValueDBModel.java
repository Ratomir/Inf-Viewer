package meta_db_model.keyvalue_db;

import java.util.List;

import meta_db_model.BaseDBStorageModel;
import meta_db_model.Connection;

public class KeyValueDBModel extends BaseDBStorageModel<Row> {

	private List<Row> row = null;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public KeyValueDBModel() {
	}

	/**
	 * 
	 * @param connection
	 * @param row
	 */
	public KeyValueDBModel(Connection connection, List<Row> row) {
		super(connection);
		this.row = row;
	}

	public List<Row> getRow() {
		return row;
	}

	public void setRow(List<Row> row) {
		this.row = row;
	}

	@Override
	public List<Row> getTableFromMetaScheme() {
		return getRow();
	}
}
