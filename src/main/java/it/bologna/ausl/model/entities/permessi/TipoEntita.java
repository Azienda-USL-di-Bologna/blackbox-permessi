package it.bologna.ausl.model.entities.permessi;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.validation.constraints.Size;

/**
 *
 * @author gdm
 */
@Entity
@Table(name = "tipi_entita", catalog = "internauta", schema = "permessi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cacheable(false)
public class TipoEntita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "target_schema")
    private String targetSchema;
    @Size(max = 2147483647)
    @Column(name = "target_table")
    private String targetTable;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "idTipoEntita")
    @JsonBackReference(value = "entitaList")
    private List<Entita> entitaList;

    public TipoEntita() {
    }

    public TipoEntita(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTargetSchema() {
        return targetSchema;
    }

    public void setTargetSchema(String targetSchema) {
        this.targetSchema = targetSchema;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public void setTargetTable(String targetTable) {
        this.targetTable = targetTable;
    }

    public List<Entita> getEntitaList() {
        return entitaList;
    }

    public void setEntitaList(List<Entita> entitaList) {
        this.entitaList = entitaList;
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
        if (!(object instanceof TipoEntita)) {
            return false;
        }
        TipoEntita other = (TipoEntita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.bologna.ausl.model.entities.permessi.TipoEntita[ id=" + id + " ]";
    }
    
}
