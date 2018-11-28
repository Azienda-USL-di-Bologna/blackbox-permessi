package it.bologna.ausl.blackbox.types;

/**
 *
 * @author Guido
 */
public class PermessoEntitaStoredProcedure {
    
    private EntitaStoredProcedure soggetto;
    private EntitaStoredProcedure oggetto;
    private PermessoStoredProcedure permesso;

    public PermessoEntitaStoredProcedure() {
    }

    public PermessoEntitaStoredProcedure(EntitaStoredProcedure soggetto, EntitaStoredProcedure oggetto, PermessoStoredProcedure permesso) {
        this.soggetto = soggetto;
        this.oggetto = oggetto;
        this.permesso = permesso;
    }

    
    public EntitaStoredProcedure getSoggetto() {
        return soggetto;
    }

    public void setSoggetto(EntitaStoredProcedure soggetto) {
        this.soggetto = soggetto;
    }

    
    public EntitaStoredProcedure getOggetto() {
        return oggetto;
    }

    public void setOggetto(EntitaStoredProcedure oggetto) {
        this.oggetto = oggetto;
    }
      

    public PermessoStoredProcedure getPermesso() {
        return permesso;
    }

    public void setPermesso(PermessoStoredProcedure permesso) {
        this.permesso = permesso;
    }
    
}
