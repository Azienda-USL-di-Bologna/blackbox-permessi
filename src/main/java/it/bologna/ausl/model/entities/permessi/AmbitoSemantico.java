package it.bologna.ausl.model.entities.permessi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import it.nextsw.common.data.annotations.GenerateProjections;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

/**
 *
 * @author Top
 */
@TypeDefs(
        {
            @TypeDef(name = "int-array", typeClass = IntArrayType.class),
            @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        }
)
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
    @Type(type = "int-array")
    private Integer[] idPredicatiAmbiti;

    @Basic(optional = true)
    @Column(name = "ruoli_gestori", columnDefinition = "text[]")
    @Type(type = "string-array")
    private String[] ruoliGestori;

    @Basic(optional = false)
    @NotNull
    @Column(name = "default_ambito")
    private Boolean default_ambito;

    @Basic(optional = true)
    @Column(name = "id_aziende", columnDefinition = "integer[]")
    @Type(type = "int-array")
    private Integer[] idAziende;

    public String[] getRuoliGestori() {
        return ruoliGestori;
    }

    public void setRuoliGestori(String[] ruoliGestori) {
        this.ruoliGestori = ruoliGestori;
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
