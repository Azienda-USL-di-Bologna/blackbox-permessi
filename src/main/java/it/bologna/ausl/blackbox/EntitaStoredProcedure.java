package it.bologna.ausl.blackbox;

/**
 * @author gdm
 */

public class EntitaStoredProcedure {
    private String id;
    private String schema;
    private String table;

    public EntitaStoredProcedure() {
    }

    public EntitaStoredProcedure(String id, String schema, String table) {
        this.id = id;
        this.schema = schema;
        this.table = table;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

       
    }