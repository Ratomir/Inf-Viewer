
package meta_db_model.relation_db;


public class Connection {

    private String address;
    private String database;
    private String databasetype;
    private String password;
    private String port;
    private String schema;
    private String username;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Connection() {
    }

    /**
     * 
     * @param schema
     * @param port
     * @param username
     * @param address
     * @param databasetype
     * @param password
     * @param database
     */
    public Connection(String address, String database, String databasetype, String password, String port, String schema, String username) {
        super();
        this.address = address;
        this.database = database;
        this.databasetype = databasetype;
        this.password = password;
        this.port = port;
        this.schema = schema;
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDatabasetype() {
        return databasetype;
    }

    public void setDatabasetype(String databasetype) {
        this.databasetype = databasetype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
