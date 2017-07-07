
package meta_db_model.relation_db;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private Connection connection;
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
        super();
        this.connection = connection;
        this.table = table;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

}
