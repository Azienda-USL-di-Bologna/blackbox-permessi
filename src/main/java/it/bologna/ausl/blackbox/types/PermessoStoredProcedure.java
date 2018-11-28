package it.bologna.ausl.blackbox.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Guido
 */
public class PermessoStoredProcedure {
    private String predicato;
    private Boolean propagaSoggetto;
    private Boolean propagaOggetto;
    private Boolean virtuale;
    private String ambito;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime data;
    private String tipo;

    public PermessoStoredProcedure() {
    }

    public PermessoStoredProcedure(String predicato, Boolean propagaSoggetto, Boolean propagaOggetto, Boolean virtuale, String ambito, LocalDateTime data, String tipo) {
        this.predicato = predicato;
        this.propagaSoggetto = propagaSoggetto;
        this.propagaOggetto = propagaOggetto;
        this.virtuale = virtuale;
        this.ambito = ambito;
        this.data = data;
        this.tipo = tipo;
    }

    
    public String getPredicato() {
        return predicato;
    }

    public void setPredicato(String predicato) {
        this.predicato = predicato;
    }

    public Boolean getPropagaSoggetto() {
        return propagaSoggetto;
    }

    public void setPropagaSoggetto(Boolean propagaSoggetto) {
        this.propagaSoggetto = propagaSoggetto;
    }

    public Boolean getPropagaOggetto() {
        return propagaOggetto;
    }

    public void setPropagaOggetto(Boolean propagaOggetto) {
        this.propagaOggetto = propagaOggetto;
    }

    public Boolean getVirtuale() {
        return virtuale;
    }

    public void setVirtuale(Boolean virtuale) {
        this.virtuale = virtuale;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
