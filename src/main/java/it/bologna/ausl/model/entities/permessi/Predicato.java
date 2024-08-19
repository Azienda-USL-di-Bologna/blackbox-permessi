package it.bologna.ausl.model.entities.permessi;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import it.nextsw.common.data.annotations.GenerateProjections;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

/**
 *
 * @author gdm
 */
@TypeDef(name = "string-array", typeClass = StringArrayType.class)
@Entity
@Table(name = "predicati", catalog = "internauta", schema = "permessi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cacheable(false)
@GenerateProjections({"permessoList"})
@DynamicUpdate
public class Predicato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "predicato")
    private String predicato;

    @Size(max = 2147483647)
    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "ruoli_gestori", columnDefinition = "text[]")
    @Type(type = "string-array")
    private String[] ruoliGestori;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "idPredicato")
    @JsonBackReference(value = "permessoList")
    private List<Permesso> permessoList;

    public Predicato() {
    }

    public Predicato(Integer id) {
        this.id = id;
    }

    public Predicato(Integer id, String predicato) {
        this.id = id;
        this.predicato = predicato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPredicato() {
        return predicato;
    }

    public void setPredicato(String predicato) {
        this.predicato = predicato;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String[] getRuoliGestori() {
        return ruoliGestori;
    }

    public void setRuoliGestori(String[] ruoliGestori) {
        this.ruoliGestori = ruoliGestori;
    }

    public List<Permesso> getPermessoList() {
        return permessoList;
    }

    public void setPermessoList(List<Permesso> permessoList) {
        this.permessoList = permessoList;
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
        if (!(object instanceof Predicato)) {
            return false;
        }
        Predicato other = (Predicato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.bologna.ausl.model.entities.permessi.Predicato[ id=" + id + " ]";
    }

}
