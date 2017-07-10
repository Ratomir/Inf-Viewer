package render.tree.elements;

public class ReferencesElement extends TableElement {
	private String refTable;
	private String refColumn;

	/**
	 * @return the refTable
	 */
	public String getRefTable() {
		return refTable;
	}

	/**
	 * @param refTable
	 *            the refTable to set
	 */
	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}

	/**
	 * @return the refColumn
	 */
	public String getRefColumn() {
		return refColumn;
	}

	/**
	 * @param refColumn
	 *            the refColumn to set
	 */
	public void setRefColumn(String refColumn) {
		this.refColumn = refColumn;
	}
}
