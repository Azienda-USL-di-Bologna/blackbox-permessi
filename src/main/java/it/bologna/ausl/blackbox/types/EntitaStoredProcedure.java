package it.bologna.ausl.blackbox.types;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gdm
 */

public class EntitaStoredProcedure {
    private String idProvenienza;
    private String schema;
    private String table;

    public EntitaStoredProcedure() {
    }

    public EntitaStoredProcedure(String id, String schema, String table) {
        this.idProvenienza = id;
        this.schema = schema;
        this.table = table;
    }
    
    @JsonProperty("id_provenienza")
    public String getIdProvenienza() {
        return idProvenienza;
    }
    
    @JsonProperty("id_provenienza")
    public void setIdProvenienza(String idProvenienza) {
        this.idProvenienza = idProvenienza;
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