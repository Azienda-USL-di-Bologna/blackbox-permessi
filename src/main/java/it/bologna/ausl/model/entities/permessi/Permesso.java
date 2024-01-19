package it.bologna.ausl.model.entities.permessi;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.nextsw.common.data.annotations.GenerateProjections;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author gdm
 */
@Entity
@Table(name = "permessi", catalog = "internauta", schema = "permessi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cacheable(false)
@GenerateProjections({})
@DynamicUpdate
//@NamedStoredProcedureQuery(name = "permessi.entity_has_permission", procedureName = "permessi.entity_has_permission",
//        parameters = {
//            @StoredProcedureParameter(mode = ParameterMode.IN, name = "soggetti", type = String.class),
//            @StoredProcedureParameter(mode = ParameterMode.IN, name = "predicati", type = String[].class),
//            @StoredProcedureParameter(mode = ParameterMode.IN, name = "oggetti", type = String.class),
//            @StoredProcedureParameter(mode = ParameterMode.IN, name = "ambito", type = String.class)
//        },
//        resultClasses = Boolean.class
//)
//@NamedStoredProcedureQuery(name = "permessi.parametro_array_string", procedureName = "permessi.parametro_array_string",
//        parameters = {
//            @StoredProcedureParameter(mode = ParameterMode.IN, name = "in_soggetti", type = String[].class)
//        },
//        resultClasses = Boolean.class
//)
public class Permesso implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Size(max = 2147483647)
    @Column(name = "origine_permesso")
    private String originePermesso;
    
    @Size(max = 2147483647)
    @Column(name = "spento_da")
    private String spentoDa;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "propaga_soggetto")
    private Boolean propagaSoggetto;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "propaga_oggetto")
    private Boolean propagaOggetto;
    
    @Size(max = 2147483647)
    @Column(name = "ambito")
    private String ambito;
    
    @Size(max = 2147483647)
    @Column(name = "tipo")
    private String tipo;
        
    @Basic(optional = false)
    @NotNull
    @Column(name = "data_permesso")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private ZonedDateTime dataPermesso;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "attivo_dal")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private ZonedDateTime attivoDal;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "attivo_al")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private ZonedDateTime attivoAl;
    
    @JoinColumn(name = "id_soggetto", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Entita idSoggetto;
    
    @JoinColumn(name = "id_oggetto", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Entita idOggetto;
    
    @JoinColumn(name = "id_entita_veicolante", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Entita idEntitaVeicolante;
    
    @JoinColumn(name = "id_predicato", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
    private Predicato idPredicato;
    
    @JoinColumn(name = "id_permesso_padre", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Permesso idPermessoPadre;
    
    @OneToMany(mappedBy = "idPermessoPadre", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference(value = "permessiFigliList")
    private List<Permesso> permessiFigliList;

    public Permesso() {
    }

    public Permesso(Integer id) {
        this.id = id;
    }

    public Permesso(Integer id, boolean propagaSoggetto, boolean propagaOggetto, ZonedDateTime dataPermesso) {
        this.id = id;
        this.propagaSoggetto = propagaSoggetto;
        this.propagaOggetto = propagaOggetto;
        this.dataPermesso = dataPermesso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginePermesso() {
        return originePermesso;
    }

    public void setOriginePermesso(String originePermesso) {
        this.originePermesso = originePermesso;
    }

    public String getSpentoDa() {
        return spentoDa;
    }

    public void setSpentoDa(String spentoDa) {
        this.spentoDa = spentoDa;
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

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ZonedDateTime getDataPermesso() {
        return dataPermesso;
    }

    public void setDataPermesso(ZonedDateTime dataPermesso) {
        this.dataPermesso = dataPermesso;
    }

    public ZonedDateTime getAttivoDal() {
        return attivoDal;
    }

    public void setAttivoDal(ZonedDateTime attivoDal) {
        this.attivoDal = attivoDal;
    }

    public ZonedDateTime getAttivoAl() {
        return attivoAl;
    }

    public void setAttivoAl(ZonedDateTime attivoAl) {
        this.attivoAl = attivoAl;
    }

    public Entita getIdSoggetto() {
        return idSoggetto;
    }

    public void setIdSoggetto(Entita idSoggetto) {
        this.idSoggetto = idSoggetto;
    }

    public Entita getIdOggetto() {
        return idOggetto;
    }

    public void setIdOggetto(Entita idOggetto) {
        this.idOggetto = idOggetto;
    }

    public Entita getIdEntitaVeicolante() {
        return idEntitaVeicolante;
    }

    public void setIdEntitaVeicolante(Entita idEntitaVeicolante) {
        this.idEntitaVeicolante = idEntitaVeicolante;
    }

    public Predicato getIdPredicato() {
        return idPredicato;
    }

    public void setIdPredicato(Predicato idPredicato) {
        this.idPredicato = idPredicato;
    }

    public Permesso getIdPermessoPadre() {
        return idPermessoPadre;
    }

    public void setIdPermessoPadre(Permesso idPermessoPadre) {
        this.idPermessoPadre = idPermessoPadre;
    }

    public List<Permesso> getPermessiFigliList() {
        return permessiFigliList;
    }

    public void setPermessiFigliList(List<Permesso> permessiFigliList) {
        this.permessiFigliList = permessiFigliList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permesso)) {
            return false;
        }
        Permesso other = (Permesso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.bologna.ausl.model.entities.permessi.Permesso[ id=" + id + " ]";
    }
    
}
