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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gdm
 */
@Entity
@Table(name = "gruppi", catalog = "internauta", schema = "permessi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cacheable(false)
public class Gruppo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome_gruppo")
    private String nomeGruppo;
    @Size(max = 2147483647)
    @Column(name = "tipo_gruppo")
    private String tipoGruppo;
    @JoinColumn(name = "id_entita_riferimento", referencedColumnName = "id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Entita idEntitaRiferimento;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, targetEntity = Entita.class)
    @JoinTable(name = "gruppi_entita", schema = "permessi", catalog = "internauta",
        joinColumns = @JoinColumn(name = "id_entita"),
        inverseJoinColumns = @JoinColumn(name = "id_gruppo")
    )
    @JsonBackReference(value = "entitaList")
    private List<Entita> entitaList;

    public Gruppo() {
    }

    public Gruppo(Integer id) {
        this.id = id;
    }

    public Gruppo(Integer id, String nomeGruppo) {
        this.id = id;
        this.nomeGruppo = nomeGruppo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }

    public String getTipoGruppo() {
        return tipoGruppo;
    }

    public void setTipoGruppo(String tipoGruppo) {
        this.tipoGruppo = tipoGruppo;
    }

    public Entita getIdEntitaRiferimento() {
        return idEntitaRiferimento;
    }

    public void setIdEntitaRiferimento(Entita idEntitaRiferimento) {
        this.idEntitaRiferimento = idEntitaRiferimento;
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
        if (!(object instanceof Gruppo)) {
            return false;
        }
        Gruppo other = (Gruppo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.bologna.ausl.model.entities.permessi.Gruppo[ id=" + id + " ]";
    }
    
}
