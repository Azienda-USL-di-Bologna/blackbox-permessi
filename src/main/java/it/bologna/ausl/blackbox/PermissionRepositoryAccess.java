package it.bologna.ausl.blackbox;

import com.fasterxml.jackson.core.type.TypeReference;
import it.bologna.ausl.internauta.utils.bds.types.EntitaStoredProcedure;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.bologna.ausl.blackbox.exceptions.BlackBoxPermissionException;
import it.bologna.ausl.blackbox.repositories.PermessoRepository;
import it.bologna.ausl.internauta.utils.bds.types.PermessoEntitaStoredProcedure;
import it.bologna.ausl.blackbox.utils.UtilityFunctions;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author gus
 */
@Component
public class PermissionRepositoryAccess {
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Autowired
    PermessoRepository permessoRepository;
    
    /**
     * 
     * @param soggetti
     * @param predicati
     * @param oggetti
     * @param ambito
     * @return
     * @throws BlackBoxPermissionException 
     */
    public Boolean entityHasPermission (List<EntitaStoredProcedure> soggetti, List<String> predicati, List<EntitaStoredProcedure> oggetti, String ambito) throws BlackBoxPermissionException {
        String soggettiJsonString;
        String oggettiJsonString = null;
        String predicatiArrayString;
        
        try {
            soggettiJsonString = objectMapper.writeValueAsString(soggetti);
            if (oggetti != null)
                oggettiJsonString = objectMapper.writeValueAsString(oggetti);
            predicatiArrayString = UtilityFunctions.getArrayString(objectMapper, predicati);   
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella creazione dei parametri per la chiamata della stored procedure", ex);
        }
        
        try {
            return permessoRepository.entityHasPermission(soggettiJsonString, predicatiArrayString, oggettiJsonString, ambito);
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
        }
    }
    
    
    /**
     *
     * @param soggetto
     * @param oggetti
     * @param ambiti
     * @param tipi
     * @return
     * @throws BlackBoxPermissionException
     */
    public List<String> getPredicatiEntita(EntitaStoredProcedure soggetto, List<EntitaStoredProcedure> oggetti, List<String> ambiti, List<String> tipi) throws BlackBoxPermissionException {
        String soggettoJsonString;
        String oggettiJsonString = null;
        String ambitiArrayString = null;
        String tipiArrayString = null;
        
        try {
            soggettoJsonString = objectMapper.writeValueAsString(soggetto);
            if (oggetti != null)
                oggettiJsonString = objectMapper.writeValueAsString(oggetti);
            if (ambiti != null)
                ambitiArrayString = UtilityFunctions.getArrayString(objectMapper, ambiti);
            if (tipi != null)
                tipiArrayString = UtilityFunctions.getArrayString(objectMapper, tipi);   
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella creazione dei parametri per la chiamata della stored procedure", ex);
        }
        
        try {
            String predicatiEntita = permessoRepository.getPredicatiEntita(soggettoJsonString, oggettiJsonString, ambitiArrayString, tipiArrayString);
            return predicatiEntita != null ? objectMapper.readValue(predicatiEntita, List.class) : null;
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
        }
    }
    
    /**
     * 
     * @param oggetti
     * @param predicati
     * @param ambiti
     * @param tipi
     * @param dammiSoggettiPropagati
     * @return
     * @throws BlackBoxPermissionException 
     */
    public List<PermessoEntitaStoredProcedure> getSubjectsWithPermissionsOnObjects(List<EntitaStoredProcedure> oggetti, List<String> predicati, List<String> ambiti, List<String> tipi, Boolean dammiSoggettiPropagati) throws BlackBoxPermissionException {
       
        String oggettiJsonString = null;
        String predicatiArrayString;
        String ambitiArrayString;
        String tipiArrayString;
        
        try {
            oggettiJsonString = objectMapper.writeValueAsString(oggetti);
            predicatiArrayString = UtilityFunctions.getArrayString(objectMapper, predicati);   
            ambitiArrayString = UtilityFunctions.getArrayString(objectMapper, ambiti);   
            tipiArrayString = UtilityFunctions.getArrayString(objectMapper, tipi);   
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella creazione dei parametri per la chiamata della stored procedure", ex);
        }
        
        try {
            String res = permessoRepository.getSubjectsWithPermissionsOnObjects(oggettiJsonString, predicatiArrayString, ambitiArrayString, tipiArrayString, dammiSoggettiPropagati);
            if (res != null)
                return objectMapper.readValue(res, new TypeReference<List<PermessoEntitaStoredProcedure>>(){});
            else
                return null;
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
        }
    }
    
    /**
     *
     * @param soggetto
     * @param oggetto
     * @param predicato
     * @param originePermesso
     * @param idPermessoPadre
     * @param propagaSoggetto
     * @param propagaOggetto
     * @param ambito
     * @param tipo
     * @param idPermessoBloccato
     * @throws BlackBoxPermissionException
     */
    public void insertSimplePermission(EntitaStoredProcedure soggetto, EntitaStoredProcedure oggetto, String predicato, String originePermesso, Integer idPermessoPadre, Boolean propagaSoggetto, Boolean propagaOggetto, String ambito, String tipo, Integer idPermessoBloccato) throws BlackBoxPermissionException {
        String soggettoJsonString = null;
        String oggettoJsonString = null;
        try {
            soggettoJsonString = objectMapper.writeValueAsString(soggetto);
            if (oggetto != null)
                oggettoJsonString = objectMapper.writeValueAsString(oggetto);
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella creazione dei parametri per la chiamata della stored procedure", ex);
        }
        
        try {
            permessoRepository.insertSimplePermission(soggettoJsonString, oggettoJsonString, predicato, originePermesso, idPermessoPadre, propagaSoggetto, propagaOggetto, ambito, tipo, idPermessoBloccato);
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
        }
    }
    
    /**
     *
     * @param soggetto
     * @param oggetto
     * @param predicato
     * @param originePermesso
     * @param idPermessoPadre
     * @param propagaSoggetto
     * @param propagaOggetto
     * @param ambito
     * @param tipo
     * @param idPermessoBloccato
     * @throws BlackBoxPermissionException
     */
    public void deletePermission(EntitaStoredProcedure soggetto, EntitaStoredProcedure oggetto, String predicato, String originePermesso, Integer idPermessoPadre, Boolean propagaSoggetto, Boolean propagaOggetto, String ambito, String tipo, Integer idPermessoBloccato) throws BlackBoxPermissionException {
        String soggettoJsonString = null;
        String oggettoJsonString = null;
        try {
            soggettoJsonString = objectMapper.writeValueAsString(soggetto);
            if (oggetto != null)
                oggettoJsonString = objectMapper.writeValueAsString(oggetto);
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella creazione dei parametri per la chiamata della stored procedure", ex);
        }
        
        try {
            permessoRepository.deletePermission(soggettoJsonString, oggettoJsonString, predicato, originePermesso, idPermessoPadre, propagaSoggetto, propagaOggetto, ambito, tipo, idPermessoBloccato);
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
        }
    }
    
    /**
     *
     * @param permessoEntitaStoredProcedure
     * @throws BlackBoxPermissionException
     */
    public void managePermissions(List<PermessoEntitaStoredProcedure> permessoEntitaStoredProcedure) throws BlackBoxPermissionException {
        String permessoEntitaStoredProcedureJsonString = null;
        try {
            permessoEntitaStoredProcedureJsonString = objectMapper.writeValueAsString(permessoEntitaStoredProcedure);
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella creazione dei parametri per la chiamata della stored procedure", ex);
        }
        System.out.println(permessoEntitaStoredProcedureJsonString);
        try {
            permessoRepository.managePermissions(permessoEntitaStoredProcedureJsonString);
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
        }
    }
    
    /**
     *
     * @param soggetto
     * @param predicati
     * @param ambiti
     * @param tipi
     * @param dammiPermessiVirtuali
     * @return
     * @throws BlackBoxPermissionException
     */
    public List<PermessoEntitaStoredProcedure> getPermissionsOfSubject(EntitaStoredProcedure soggetto, List<String> predicati, List<String> ambiti, List<String> tipi, Boolean dammiPermessiVirtuali) throws BlackBoxPermissionException {
        String soggettoString;
        String predicatiArrayString;
        String ambitiArrayString;
        String tipiArrayString;
        
        try {
            soggettoString = objectMapper.writeValueAsString(soggetto);
            predicatiArrayString = UtilityFunctions.getArrayString(objectMapper, predicati);   
            ambitiArrayString = UtilityFunctions.getArrayString(objectMapper, ambiti);   
            tipiArrayString = UtilityFunctions.getArrayString(objectMapper, tipi);   
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella creazione dei parametri per la chiamata della stored procedure", ex);
        }
        
        try {
            String res = permessoRepository.getPermissionsOfSubject(soggettoString, predicatiArrayString, ambitiArrayString, tipiArrayString, dammiPermessiVirtuali);
            if (res != null)
                return objectMapper.readValue(res, new TypeReference<List<PermessoEntitaStoredProcedure>>(){});
            else
                return null;
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
        }
    }
}
