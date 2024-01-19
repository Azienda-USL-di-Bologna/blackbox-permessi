package it.bologna.ausl.internauta.utils.masterjobs.workers.jobs.gestorepermessi;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.bologna.ausl.internauta.utils.masterjobs.workers.jobs.JobWorkerData;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gdm
 */
public class GestorePermessiJobWorkerData extends JobWorkerData {
    public static enum Operazione{RIACCENDI, SPEGNI}
    public static class EntitaPermesso {
        private Integer idProvenienza;
        private String schema;
        private String table;

        public EntitaPermesso() {
        }

        public EntitaPermesso(Integer idProvenienza, String schema, String table) {
            this.idProvenienza = idProvenienza;
            this.schema = schema;
            this.table = table;
        }

        public Integer getIdProvenienza() {
            return idProvenienza;
        }

        public void setIdProvenienza(Integer idProvenienza) {
            this.idProvenienza = idProvenienza;
        }

        public String getSchema() {
            return schema;
        }

        public void setSchema(String schema) {
            this.schema = schema;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }
    }
    
    @JsonIgnore
    private static final Logger log = LoggerFactory.getLogger(GestorePermessiJobWorkerData.class);
    
    private EntitaPermesso soggetto;
    private EntitaPermesso entitaVeicolante;
    private Operazione operazione;
    private List<String> ambiti;
    private List<String> tipi;

    public GestorePermessiJobWorkerData() {
    }

    public GestorePermessiJobWorkerData(EntitaPermesso soggetto, Operazione operazione, List<String> ambiti, List<String> tipi) {
        this.soggetto = soggetto;
        this.operazione = operazione;
        this.ambiti = ambiti;
        this.tipi = tipi;
    }
    
    public GestorePermessiJobWorkerData(EntitaPermesso soggetto, EntitaPermesso entitaVeicolante, Operazione operazione, List<String> ambiti, List<String> tipi) {
        this.soggetto = soggetto;
        this.entitaVeicolante = entitaVeicolante;
        this.operazione = operazione;
        this.ambiti = ambiti;
        this.tipi = tipi;
    }

    public EntitaPermesso getSoggetto() {
        return soggetto;
    }

    public void setSoggetto(EntitaPermesso soggetto) {
        this.soggetto = soggetto;
    }

    public EntitaPermesso getEntitaVeicolante() {
        return entitaVeicolante;
    }

    public void setEntitaVeicolante(EntitaPermesso entitaVeicolante) {
        this.entitaVeicolante = entitaVeicolante;
    }

    public Operazione getOperazione() {
        return operazione;
    }

    public void setOperazione(Operazione operazione) {
        this.operazione = operazione;
    }

    public List<String> getAmbiti() {
        return ambiti;
    }

    public void setAmbiti(List<String> ambiti) {
        this.ambiti = ambiti;
    }

    public List<String> getTipi() {
        return tipi;
    }

    public void setTipi(List<String> tipi) {
        this.tipi = tipi;
    }
}
