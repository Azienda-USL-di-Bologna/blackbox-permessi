package it.bologna.ausl.blackbox.types;

import java.util.List;

/**
 *
 * @author Guido
 */
public class PermessoEntitaStoredProcedure {
    
    private EntitaStoredProcedure soggetto;
    private EntitaStoredProcedure oggetto;
    private List<CategoriaPermessiStoredProcedure> categorie;

    public PermessoEntitaStoredProcedure() {
    }

    public PermessoEntitaStoredProcedure(EntitaStoredProcedure soggetto, EntitaStoredProcedure oggetto, List<CategoriaPermessiStoredProcedure> categorie) {
        this.soggetto = soggetto;
        this.oggetto = oggetto;
        this.categorie = categorie;
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

    public List<CategoriaPermessiStoredProcedure> getCategorie() {
        return categorie;
    }

    public void setCategorie(List<CategoriaPermessiStoredProcedure> categorie) {
        this.categorie = categorie;
    }
}
