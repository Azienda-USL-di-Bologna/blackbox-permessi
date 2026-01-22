package it.bologna.ausl.model.entities.permessi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import it.nextsw.common.data.annotations.GenerateProjections;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

/**
 *
 * @author Top
// */
//@TypeDefs(
//        {
//            @TypeDef(name = "int-array", typeClass = IntArrayType.class),
//            @TypeDef(name = "string-array", typeClass = StringArrayType.class),
//        }
//)
@Entity
@Table(name = "predicati_ambiti", catalog = "internauta", schema = "permessi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cacheable(false)
@GenerateProjections({"idPredicato"})
@DynamicUpdate
public class PredicatoAmbito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @JoinColumn(name = "id_predicato", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, optional = false)
    private Predicato idPredicato;

    @Size(max = 2147483647)
    @Column(name = "tipo")
    private String tipo;
    
    @Size(max = 2147483647)
    @Column(name = "ambito")
    private String ambito;

    @Basic(optional = false)
    @Column(name = "id_predicati_ambiti_impliciti", columnDefinition = "integer[]")
    @Type(IntArrayType.class)
    private Integer[] idPredicatiAmbitiImpliciti;
    
    @Column(name = "ruoli_gestori", columnDefinition = "text[]")
   @JdbcTypeCode(SqlTypes.ARRAY)
    private String[] ruoliGestori;
    
    @Basic(optional = true)
    @Column(name = "id_aziende", columnDefinition = "integer[]")
    @Type(IntArrayType.class)
    private Integer[] idAziende;
    
    @Basic(optional = true)
    @Column(name = "abilitazioni_gestori", columnDefinition = "text[]")
    @JdbcTypeCode(SqlTypes.ARRAY)
    private String[] abilitazioniGestori;
    
    public String[] getAbilitazioniGestori() {
        return abilitazioniGestori;
    }

    public void setAbilitazioniGestori(String[] abilitazioniGestori) {
        this.abilitazioniGestori = abilitazioniGestori;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Predicato getIdPredicato() {
        return idPredicato;
    }

    public void setIdPredicato(Predicato idPredicato) {
        this.idPredicato = idPredicato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public Integer[] getIdPredicatiAmbitiImpliciti() {
        return idPredicatiAmbitiImpliciti;
    }

    public void setIdPredicatiAmbitiImpliciti(Integer[] idPredicatiAmbitiImpliciti) {
        this.idPredicatiAmbitiImpliciti = idPredicatiAmbitiImpliciti;
    }

    public String[] getRuoliGestori() {
        return ruoliGestori;
    }

    public void setRuoliGestori(String[] ruoliGestori) {
        this.ruoliGestori = ruoliGestori;
    }

    public Integer[] getIdAziende() {
        return idAziende;
    }

    public void setIdAziende(Integer[] idAziende) {
        this.idAziende = idAziende;
    }

}
