
package meta_db_model.relation_db;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<Column> column = new ArrayList<Column>();
    private Crud crud;
    private String code;
    private String name;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Table() {
    }

    /**
     * 
     * @param name
     * @param column
     * @param crud
     * @param code
     */
    public Table(List<Column> column, Crud crud, String code, String name) {
        super();
        this.column = column;
        this.crud = crud;
        this.code = code;
        this.name = name;
    }

    public List<Column> getColumn() {
        return column;
    }

    public void setColumn(List<Column> column) {
        this.column = column;
    }

    public Crud getCrud() {
        return crud;
    }

    public void setCrud(Crud crud) {
        this.crud = crud;
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
