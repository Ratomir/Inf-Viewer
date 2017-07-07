
package meta_db_model.relation_db;


public class References {

    private String refColumn;
    private String refTable;

    /**
     * No args constructor for use in serialization
     * 
     */
    public References() {
    }

    /**
     * 
     * @param refColumn
     * @param refTable
     */
    public References(String refColumn, String refTable) {
        super();
        this.refColumn = refColumn;
        this.refTable = refTable;
    }

    public String getRefColumn() {
        return refColumn;
    }

    public void setRefColumn(String refColumn) {
        this.refColumn = refColumn;
    }

    public String getRefTable() {
        return refTable;
    }

    public void setRefTable(String refTable) {
        this.refTable = refTable;
    }

}
