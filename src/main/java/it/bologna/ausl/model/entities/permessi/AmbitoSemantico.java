package it.bologna.ausl.model.entities.permessi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.hypersistence.utils.hibernate.type.array.IntArrayType;
import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import it.nextsw.common.data.annotations.GenerateProjections;
import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

/**
 *
 * @author Top
 */
//@TypeDefs(
//        {
//            @TypeDef(name = "int-array", typeClass = IntArrayType.class),
//            @TypeDef(name = "string-array", typeClass = StringArrayType.class),
//        }
//)
@Entity
@Table(name = "ambiti_semantici", catalog = "internauta", schema = "permessi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cacheable(false)
@GenerateProjections({})
@DynamicUpdate
public class AmbitoSemantico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Size(max = 2147483647)
    @Column(name = "semantica")
    private String semantica;

    @Basic(optional = false)
    @Column(name = "id_predicati_ambiti", columnDefinition = "integer[]")
    @Type(IntArrayType.class)
    private Integer[] idPredicatiAmbiti;

    @Basic(optional = true)
    @Column(name = "ruoli_gestori", columnDefinition = "text[]")
    @JdbcTypeCode(SqlTypes.ARRAY)
    private String[] ruoliGestori;
    
    @Basic(optional = true)
    @Column(name = "abilitazioni_gestori", columnDefinition = "text[]")
    @JdbcTypeCode(SqlTypes.ARRAY)
    private String[] abilitazioniGestori;

    @Basic(optional = false)
    @NotNull
    @Column(name = "default_ambito")
    private Boolean default_ambito;

    @Basic(optional = true)
    @Column(name = "id_aziende", columnDefinition = "integer[]")
    @Type(IntArrayType.class)
    private Integer[] idAziende;

    public String[] getRuoliGestori() {
        return ruoliGestori;
    }

    public void setRuoliGestori(String[] ruoliGestori) {
        this.ruoliGestori = ruoliGestori;
    }

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

    public String getSemantica() {
        return semantica;
    }

    public void setSemantica(String semantica) {
        this.semantica = semantica;
    }

    public Integer[] getIdPredicatiAmbiti() {
        return idPredicatiAmbiti;
    }

    public void setIdPredicatiAmbiti(Integer[] idPredicatiAmbiti) {
        this.idPredicatiAmbiti = idPredicatiAmbiti;
    }

    public Boolean getDefault_ambito() {
        return default_ambito;
    }

    public void setDefault_ambito(Boolean default_ambito) {
        this.default_ambito = default_ambito;
    }

    public Integer[] getIdAziende() {
        return idAziende;
    }

    public void setIdAziende(Integer[] idAziende) {
        this.idAziende = idAziende;
    }
}
