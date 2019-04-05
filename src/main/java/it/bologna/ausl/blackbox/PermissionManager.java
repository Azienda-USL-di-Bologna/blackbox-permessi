package it.bologna.ausl.blackbox;

import it.bologna.ausl.internauta.utils.bds.types.EntitaStoredProcedure;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.bologna.ausl.blackbox.exceptions.BlackBoxPermissionException;
import it.bologna.ausl.blackbox.repositories.PermessoRepository;
import it.bologna.ausl.internauta.utils.bds.types.CategoriaPermessiStoredProcedure;
import it.bologna.ausl.internauta.utils.bds.types.PermessoEntitaStoredProcedure;
import it.bologna.ausl.internauta.utils.bds.types.PermessoStoredProcedure;
import it.bologna.ausl.blackbox.utils.UtilityFunctions;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author gdm
 */
@Component
public class PermissionManager {
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired(required = false)
    PermessoRepository permessoRepository;
    
    @Autowired
    PermissionRepositoryAccess permissionRepositoryAccess;
    
    /**
     * Torna true se l'entità passata ha il permesso sul predicato passato.
     * @param entitySoggetto
     * @param predicato
     * @return
     * @throws BlackBoxPermissionException 
     */
    public Boolean hasPermission(Object entitySoggetto, String predicato) throws BlackBoxPermissionException {
        return hasPermission(entitySoggetto, predicato, null, null);
    }
    
    public Boolean hasPermission(Object entitySoggetto, String predicato, Object entityOggetto, String ambito) throws BlackBoxPermissionException {
        if (entitySoggetto == null)
            throw new BlackBoxPermissionException("il parametro entitySoggetto non può essere null");
            
        if (!StringUtils.hasText(predicato))
            throw new BlackBoxPermissionException("il parametro predicato non può essere null o vuoto");
            
        Table soggettoTableAnnotation;
        Table oggettoTableAnnotation = null;
        try {
            soggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(entitySoggetto.getClass(), Table.class);
            if (entityOggetto != null) {
                oggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(entityOggetto.getClass(), Table.class);
            }
        }
        catch (ClassNotFoundException ex) {
            // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlackBoxPermissionException(ex);
        }
        
        if (soggettoTableAnnotation == null) 
            throw new BlackBoxPermissionException("l'entità soggetto passata non ha l'annotazione Table");
        
        if (oggettoTableAnnotation == null && entityOggetto != null) 
            throw new BlackBoxPermissionException("l'entità oggetto passata non ha l'annotazione Table");
        
        List<EntitaStoredProcedure> soggetti = new ArrayList();
        try {
            soggetti.add(new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(entitySoggetto), soggettoTableAnnotation.schema(), soggettoTableAnnotation.name()));
        } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlackBoxPermissionException("errore nella creazione del soggetto", ex);
        }
        
        List<EntitaStoredProcedure> oggetti = null;
        if (entityOggetto != null) {
            oggetti = new ArrayList();
            try {
                oggetti.add(new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(entityOggetto), oggettoTableAnnotation.schema(), oggettoTableAnnotation.name()));
            } catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
                throw new BlackBoxPermissionException("errore nella creazione del soggetto", ex);
            }
        }
        
        List<String> predicati = new ArrayList<>();
        predicati.add(predicato);

        return permissionRepositoryAccess.entityHasPermission(soggetti, predicati, oggetti, ambito);
    }
    
    public List<String> getPermission(Object entitySoggetto, List<String> ambiti, String tipo) throws BlackBoxPermissionException {
        List<String> tipi = new ArrayList<>();
        tipi.add(tipo);
        return getPermission(entitySoggetto, ambiti, tipi);
    }
    
    public List<String> getPermission(Object entitySoggetto, List<String> ambiti, List<String> tipi) throws BlackBoxPermissionException {
        return getPermission(entitySoggetto, null, ambiti, tipi);
    }
    
    public List<String> getPermission(Object entitySoggetto, List<Object> entitiesOggetto, List<String> ambiti, List<String> tipi) throws BlackBoxPermissionException {
        if (entitySoggetto == null) {
            throw new BlackBoxPermissionException("il parametro entitySoggetto non può essere null");
        }
        
        Table soggettoTableAnnotation;
        try {
            soggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(entitySoggetto.getClass(), Table.class);
        } catch (ClassNotFoundException ex) {
            // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlackBoxPermissionException(ex);
        }
        
        if (soggettoTableAnnotation == null) 
            throw new BlackBoxPermissionException("l'entità soggetto passata non ha l'annotazione Table");
        
        EntitaStoredProcedure soggetto;
        try {
            soggetto = new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(entitySoggetto), soggettoTableAnnotation.schema(), soggettoTableAnnotation.name());
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
            // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlackBoxPermissionException("errore nella creazione del soggetto", ex);
        }
        
        
        List<EntitaStoredProcedure> oggetti = null;
        if (entitiesOggetto != null) {
            oggetti = new ArrayList();
            for (Object o : entitiesOggetto) {
                Table oggettoTableAnnotation;
                try {
                    oggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(o.getClass(), Table.class);
                    oggetti.add(new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(o), oggettoTableAnnotation.schema(), oggettoTableAnnotation.name()));
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
                    throw new BlackBoxPermissionException("errore nella creazione del soggetto", ex);
                }
            }
        }
        
        return permissionRepositoryAccess.getPredicatiEntita(soggetto, oggetti, ambiti, tipi);
    }
    
    public void insertSimplePermission(Object entitySoggetto, Object entityOggetto, String predicato, String originePermesso, Boolean propagaSoggetto, Boolean propagaOggetto, String ambito, String tipo) throws BlackBoxPermissionException {
        if (entitySoggetto == null) {
            throw new BlackBoxPermissionException("il parametro entitySoggetto non può essere null");
        }
        
        if (ambito == null) {
            throw new BlackBoxPermissionException("il parametro ambito non può essere null");
        }
        
        if (tipo == null) {
            throw new BlackBoxPermissionException("il parametro tipo non può essere null");
        }
        
        Table soggettoTableAnnotation;
        try {
            soggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(entitySoggetto.getClass(), Table.class);
        } catch (ClassNotFoundException ex) {
            // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlackBoxPermissionException(ex);
        }
        
        if (soggettoTableAnnotation == null) 
            throw new BlackBoxPermissionException("l'entità soggetto passata non ha l'annotazione Table");
        
        EntitaStoredProcedure soggetto;
        try {
            soggetto = new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(entitySoggetto), soggettoTableAnnotation.schema(), soggettoTableAnnotation.name());
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
            // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlackBoxPermissionException("errore nella creazione del soggetto", ex);
        }
        
        EntitaStoredProcedure oggetto = null;
        if (entityOggetto != null) {
            Table oggettoTableAnnotation;
            try {
                oggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(entityOggetto.getClass(), Table.class);
            } catch (ClassNotFoundException ex) {
                // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
                throw new BlackBoxPermissionException(ex);
            }

            if (oggettoTableAnnotation == null) 
                throw new BlackBoxPermissionException("l'entità soggetto passata non ha l'annotazione Table");

            try {
                oggetto = new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(entityOggetto), oggettoTableAnnotation.schema(), oggettoTableAnnotation.name());
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
                // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
                throw new BlackBoxPermissionException("errore nella creazione del soggetto", ex);
            }
        }
        permissionRepositoryAccess.insertSimplePermission(soggetto, oggetto, predicato, originePermesso, null, propagaSoggetto, propagaOggetto, ambito, tipo, null);
    }
    
    public List<PermessoEntitaStoredProcedure> getSubjectsWithPermissionsOnObject(Object entityOggetto, List<String> predicati, List<String> ambiti, List<String> tipi, Boolean dammiSoggettiPropagati) throws BlackBoxPermissionException{
        if(entityOggetto == null)
            throw new BlackBoxPermissionException("entità oggetto non passata");
        else
            return getSubjectsWithPermissionsOnObject(Arrays.asList(new Object[] {entityOggetto}), predicati, ambiti, tipi, dammiSoggettiPropagati);
    }
    
    public List<PermessoEntitaStoredProcedure> getSubjectsWithPermissionsOnObject(List<Object> entitiesOggetto, List<String> predicati, List<String> ambiti, List<String> tipi, Boolean dammiSoggettiPropagati) throws BlackBoxPermissionException {
        if(entitiesOggetto == null || entitiesOggetto.isEmpty()) {
            throw new BlackBoxPermissionException("deve essere pasasta almeno un'entità oggetto");
        }

        List<EntitaStoredProcedure> oggetti = new ArrayList();
        for (Object o : entitiesOggetto) {
            Table oggettoTableAnnotation;
            try {
                oggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(o.getClass(), Table.class);
                oggetti.add(new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(o), oggettoTableAnnotation.schema(), oggettoTableAnnotation.name()));
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
                throw new BlackBoxPermissionException("errore nella creazione dell'oggetto", ex);
            }
        }
        return permissionRepositoryAccess.getSubjectsWithPermissionsOnObjects(oggetti, predicati, ambiti, tipi, dammiSoggettiPropagati);
    }
    
    /**
     * TODO: deve tornare una lista contenente gli oggetti entità a partire dalla lista di EntitaStoredProcedure passata creando una query tramite EntityManager
     * dovrà fare il minor numerop di query possibile, ad esempio raggruppando le entità per tipo e poi facendo una query "where id in..."
     * @param entitaStoredProcedure
     * @return 
     */
    public Object getEntityFromEntitaStoredProcedure(List<EntitaStoredProcedure> entitaStoredProcedure) {
        return null;
    }
    
    /**
     * TODO: deve tornare l'oggetto l'entità a partire dall'EntitaStoredProcedure passata creando una query tramite EntityManager
     * @param entitaStoredProcedure
     * @return 
     */
    public Object getEntityFromEntitaStoredProcedure(EntitaStoredProcedure entitaStoredProcedure) {
        return null;
    }
    
    
    public void deletePermission(Object entitySoggetto, Object entityOggetto, String predicato, String originePermesso, Boolean propagaSoggetto, Boolean propagaOggetto, String ambito, String tipo) throws BlackBoxPermissionException {
        if (entitySoggetto == null) {
            throw new BlackBoxPermissionException("il parametro entitySoggetto non può essere null");
        }
        
        if (ambito == null) {
            throw new BlackBoxPermissionException("il parametro ambito non può essere null");
        }
        
        if (tipo == null) {
            throw new BlackBoxPermissionException("il parametro tipo non può essere null");
        }
        
        Table soggettoTableAnnotation;
        try {
            soggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(entitySoggetto.getClass(), Table.class);
        } catch (ClassNotFoundException ex) {
            // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlackBoxPermissionException(ex);
        }
        
        if (soggettoTableAnnotation == null) 
            throw new BlackBoxPermissionException("l'entità soggetto passata non ha l'annotazione Table");
        
        EntitaStoredProcedure soggetto;
        try {
            soggetto = new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(entitySoggetto), soggettoTableAnnotation.schema(), soggettoTableAnnotation.name());
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
            // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlackBoxPermissionException("errore nella creazione del soggetto", ex);
        }
        
        EntitaStoredProcedure oggetto = null;
        if (entityOggetto != null) {
            Table oggettoTableAnnotation;
            try {
                oggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(entityOggetto.getClass(), Table.class);
            } catch (ClassNotFoundException ex) {
                // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
                throw new BlackBoxPermissionException(ex);
            }

            if (oggettoTableAnnotation == null) 
                throw new BlackBoxPermissionException("l'entità soggetto passata non ha l'annotazione Table");

            try {
                oggetto = new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(entityOggetto), oggettoTableAnnotation.schema(), oggettoTableAnnotation.name());
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
                // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
                throw new BlackBoxPermissionException("errore nella creazione del soggetto", ex);
            }
        }
        permissionRepositoryAccess.deletePermission(soggetto, oggetto, predicato, originePermesso, null, propagaSoggetto, propagaOggetto, ambito, tipo, null);
    }
    
    public List<PermessoEntitaStoredProcedure> getPermissionsOfSubject(Object entitySoggetto, List<String> predicati, List<String> ambiti, List<String> tipi, Boolean dammiSoggettiPropagati) throws BlackBoxPermissionException {
        if(entitySoggetto == null) {
            throw new BlackBoxPermissionException("il soggetto è obbligatorio");
        }

        EntitaStoredProcedure soggetto =null;
        Table soggettoTableAnnotation;
        try {
            soggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(entitySoggetto.getClass(), Table.class);
            soggetto = new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(entitySoggetto), soggettoTableAnnotation.schema(), soggettoTableAnnotation.name());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            // Logger.getLogger(PermissionManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new BlackBoxPermissionException("errore nella creazione del soggetto", ex);
        }
        
        return permissionRepositoryAccess.getPermissionsOfSubject(soggetto, predicati, ambiti, tipi, dammiSoggettiPropagati);
    }
    
    
    /**
     * Metodo semplificato per chiamare la managePermissions.
     * 
     * @param entitySoggetto
     * @param entityOggetto può essere null
     * @param ambito
     * @param tipo
     * @param permessi
     * @throws BlackBoxPermissionException 
     */
    public void managePermissions(Object entitySoggetto, Object entityOggetto, String ambito, String tipo, List<PermessoStoredProcedure> permessi) throws BlackBoxPermissionException {
        if(entitySoggetto == null) {
            throw new BlackBoxPermissionException("il soggetto è obbligatorio");
        }

        EntitaStoredProcedure soggetto = null;
        Table soggettoTableAnnotation;
        try {
            soggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(entitySoggetto.getClass(), Table.class);
            soggetto = new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(entitySoggetto), soggettoTableAnnotation.schema(), soggettoTableAnnotation.name());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new BlackBoxPermissionException("errore nella creazione del soggetto", ex);
        }
        
        EntitaStoredProcedure oggetto = null;
        
        if(entityOggetto != null) {
            
            Table oggettoTableAnnotation;
            try {
                oggettoTableAnnotation = UtilityFunctions.getFirstAnnotationOverEntity(entityOggetto.getClass(), Table.class);
                oggetto = new EntitaStoredProcedure((Integer) UtilityFunctions.getPkValue(entityOggetto), oggettoTableAnnotation.schema(), oggettoTableAnnotation.name());
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new BlackBoxPermissionException("errore nella creazione del soggetto", ex);
            }
        }
        
        CategoriaPermessiStoredProcedure categoria = new CategoriaPermessiStoredProcedure(ambito, tipo, permessi);
        PermessoEntitaStoredProcedure permessoEntitaStoredProcedure = new PermessoEntitaStoredProcedure(soggetto, oggetto, Arrays.asList(new CategoriaPermessiStoredProcedure[]{categoria}));

        for (PermessoStoredProcedure p : permessi) {
            if (p.getPropagaOggetto() == null) {
                p.setPropagaOggetto(false);
            }

            if (p.getPropagaSoggetto() == null) {
                p.setPropagaSoggetto(false);
            }
        }
        
        
        permissionRepositoryAccess.managePermissions(Arrays.asList(new PermessoEntitaStoredProcedure[]{permessoEntitaStoredProcedure}));
    }
}
