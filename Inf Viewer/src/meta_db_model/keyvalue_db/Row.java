package meta_db_model.keyvalue_db;

public class Row {

	private String key;
	private String type;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Row() {
	}

	/**
	 * 
	 * @param type
	 * @param key
	 */
	public Row(String key, String type) {
		super();
		this.key = key;
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
