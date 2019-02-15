package it.bologna.ausl.blackbox.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Guido
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermessoStoredProcedure implements Serializable {
//    private Integer id;
    private String predicato;
    private Boolean propagaSoggetto;
    private Boolean propagaOggetto;
    private Boolean virtuale;
//    private String ambito;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data;
//    private String tipo;
    private String originePermesso;
    private Integer idPermessoBloccato;

    public PermessoStoredProcedure() {
    }

    public PermessoStoredProcedure(String predicato, Boolean propagaSoggetto, Boolean propagaOggetto, Boolean virtuale, String ambito, LocalDateTime data, String tipo) {
        this.predicato = predicato;
        this.propagaSoggetto = propagaSoggetto;
        this.propagaOggetto = propagaOggetto;
        this.virtuale = virtuale;
//        this.ambito = ambito;
        this.data = data;
//        this.tipo = tipo;
    }
    
    public PermessoStoredProcedure(String predicato, Boolean propagaSoggetto, Boolean propagaOggetto, String originePermesso, Integer idPermessoBloccato) {
        this.predicato = predicato;
        this.propagaSoggetto = propagaSoggetto;
        this.propagaOggetto = propagaOggetto;
        this.originePermesso = originePermesso;
        this.idPermessoBloccato = idPermessoBloccato;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getPredicato() {
        return predicato;
    }

    public void setPredicato(String predicato) {
        this.predicato = predicato;
    }
    
    @JsonProperty("propaga_soggetto")
    public Boolean getPropagaSoggetto() {
        return propagaSoggetto;
    }

    @JsonProperty("propaga_soggetto")
    public void setPropagaSoggetto(Boolean propagaSoggetto) {
        this.propagaSoggetto = propagaSoggetto;
    }
    
    @JsonProperty("propaga_oggetto")
    public Boolean getPropagaOggetto() {
        return propagaOggetto;
    }

    @JsonProperty("propaga_oggetto")
    public void setPropagaOggetto(Boolean propagaOggetto) {
        this.propagaOggetto = propagaOggetto;
    }

    public Boolean getVirtuale() {
        return virtuale;
    }

    public void setVirtuale(Boolean virtuale) {
        this.virtuale = virtuale;
    }

//    public String getAmbito() {
//        return ambito;
//    }
//
//    public void setAmbito(String ambito) {
//        this.ambito = ambito;
//    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

//    public String getTipo() {
//        return tipo;
//    }
//
//    public void setTipo(String tipo) {
//        this.tipo = tipo;
//    }
    
    @JsonProperty("origine_permesso")
    public String getOriginePermesso() {
        return originePermesso;
    }
    
    @JsonProperty("origine_permesso")
    public void setOriginePermesso(String originePermesso) {
        this.originePermesso = originePermesso;
    }
    
    @JsonProperty("id_permesso_bloccato")
    public Integer getIdPermessoBloccato() {
        return idPermessoBloccato;
    }
    
    @JsonProperty("id_permesso_bloccato")
    public void setIdPermessoBloccato(Integer idPermessoBloccato) {
        this.idPermessoBloccato = idPermessoBloccato;
    }

}
