package meta_db_model.no_relation_db;

public class Collection {

	private String code;
	private String name;

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Collection() {
	}

	/**
	 * 
	 * @param name
	 * @param code
	 */
	public Collection(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
