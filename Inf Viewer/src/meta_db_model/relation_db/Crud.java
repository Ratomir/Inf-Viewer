
package meta_db_model.relation_db;


public class Crud {

    private String create;
    private String retrieve;
    private String update;
    private String delete;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Crud() {
    }

    /**
     * 
     * @param update
     * @param retrieve
     * @param delete
     * @param create
     */
    public Crud(String create, String retrieve, String update, String delete) {
        super();
        this.create = create;
        this.retrieve = retrieve;
        this.update = update;
        this.delete = delete;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getRetrieve() {
        return retrieve;
    }

    public void setRetrieve(String retrieve) {
        this.retrieve = retrieve;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

}
