package it.bologna.ausl.model.entities.permessi;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author gdm
 */
@Entity
@Table(name = "entita", catalog = "internauta", schema = "permessi")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Cacheable(false)
public class Entita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "id_provenienza")
    private Integer idProvenienza;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "idSoggetto")
    private List<Permesso> permessiSoggettoList;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "idOggetto")
    private List<Permesso> permessiOggettoList;
    @JoinColumn(name = "id_tipo_entita", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private TipoEntita idTipoEntita;
    @OneToMany(mappedBy = "idEntitaRiferimento", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Gruppo> gruppoRiferimentoList;

    @ManyToMany(mappedBy = "entitaList", fetch = FetchType.LAZY)
    private List<Gruppo> gruppiList;

    public Entita() {
    }

    public Entita(Integer id) {
        this.id = id;
    }

    public Entita(Integer id, Integer idProvenienza) {
        this.id = id;
        this.idProvenienza = idProvenienza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProvenienza() {
        return idProvenienza;
    }

    public void setIdProvenienza(Integer idProvenienza) {
        this.idProvenienza = idProvenienza;
    }

    public List<Permesso> getPermessiSoggettoList() {
        return permessiSoggettoList;
    }

    public void setPermessiSoggettoList(List<Permesso> permessiSoggettoList) {
        this.permessiSoggettoList = permessiSoggettoList;
    }

    public List<Permesso> getPermessiOggettoList() {
        return permessiOggettoList;
    }

    public void setPermessiOggettoList(List<Permesso> permessiOggettoList) {
        this.permessiOggettoList = permessiOggettoList;
    }

    public TipoEntita getIdTipoEntita() {
        return idTipoEntita;
    }

    public void setIdTipoEntita(TipoEntita idTipoEntita) {
        this.idTipoEntita = idTipoEntita;
    }

    public List<Gruppo> getGruppoRifermimentoList() {
        return gruppoRiferimentoList;
    }

    public void setGruppoRiferimentoList(List<Gruppo> gruppoRiferimentoList) {
        this.gruppoRiferimentoList = gruppoRiferimentoList;
    }

    public List<Gruppo> getGruppiList() {
        return gruppiList;
    }

    public void setGruppiList(List<Gruppo> gruppiList) {
        this.gruppiList = gruppiList;
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
        if (!(object instanceof Entita)) {
            return false;
        }
        Entita other = (Entita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "it.bologna.ausl.model.entities.permessi.Entita[ id=" + id + " ]";
    }
    
}
