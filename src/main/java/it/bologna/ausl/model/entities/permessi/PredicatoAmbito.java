package it.bologna.ausl.model.entities.permessi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.bologna.ausl.internauta.utils.jpa.tools.GenericArrayUserType;
import it.nextsw.common.data.annotations.GenerateProjections;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 *
 * @author Top
 */
@TypeDefs(
        {
            @TypeDef(name = "array", typeClass = GenericArrayUserType.class)
        }
)
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
    @Type(type = "array", parameters = @Parameter(name = "elements-type", value = GenericArrayUserType.INTEGER_ELEMENT_TYPE))
    private Integer[] idPredicatiAmbitiImpliciti;
    
    @Column(name = "ruoli_gestori", columnDefinition = "text[]")
    @Type(type = "array", parameters = @Parameter(name = "elements-type", value = GenericArrayUserType.TEXT_ELEMENT_TYPE))
    private String[] ruoliGestori;
    
    @Basic(optional = true)
    @Column(name = "id_aziende", columnDefinition = "integer[]")
    @Type(type = "array", parameters = @Parameter(name = "elements-type", value = GenericArrayUserType.INTEGER_ELEMENT_TYPE))
    private Integer[] idAziende;

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
