package meta_db_model;

import java.util.List;

public abstract class BaseDBStorageModel<T> {
	protected Connection connection;

	public BaseDBStorageModel() {
	}

	public BaseDBStorageModel(Connection connection) {
		this.connection = connection;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param connection
	 *            the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public abstract List<T> getTableFromMetaScheme();
}
