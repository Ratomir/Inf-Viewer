
package meta_db_model.relation_db;


public class Column {

    private References references;
    private String code;
    private String name;
    private boolean nullable;
    private boolean primary;
    private String size;
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Column() {
    }

    /**
     * 
     * @param references
     * @param primary
     * @param name
     * @param nullable
     * @param code
     * @param type
     * @param size
     */
    public Column(References references, String code, String name, boolean nullable, boolean primary, String size, String type) {
        super();
        this.references = references;
        this.code = code;
        this.name = name;
        this.nullable = nullable;
        this.primary = primary;
        this.size = size;
        this.type = type;
    }

    public References getReferences() {
        return references;
    }

    public void setReferences(References references) {
        this.references = references;
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

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
