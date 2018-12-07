package it.bologna.ausl.blackbox.types;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gdm
 */

public class EntitaStoredProcedure {
    private Integer idProvenienza;
    private String schema;
    private String table;

    public EntitaStoredProcedure() {
    }

    public EntitaStoredProcedure(Integer id, String schema, String table) {
        this.idProvenienza = id;
        this.schema = schema;
        this.table = table;
    }

    public EntitaStoredProcedure(Object pkValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @JsonProperty("id_provenienza")
    public Integer getIdProvenienza() {
        return idProvenienza;
    }
    
    @JsonProperty("id_provenienza")
    public void setIdProvenienza(Integer idProvenienza) {
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