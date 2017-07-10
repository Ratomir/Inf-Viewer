package render.tree.elements;

import render.tree.TreeElement;

public class TableElement extends TreeElement {
	private String tableName;
	
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
