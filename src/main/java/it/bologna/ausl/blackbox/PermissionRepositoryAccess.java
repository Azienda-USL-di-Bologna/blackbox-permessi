package it.bologna.ausl.blackbox;

import it.bologna.ausl.blackbox.types.EntitaStoredProcedure;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.bologna.ausl.blackbox.exceptions.BlackBoxPermissionException;
import it.bologna.ausl.blackbox.repositories.PermessoRepository;
import it.bologna.ausl.blackbox.types.PermessoEntitaStoredProcedure;
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
    public PermessoEntitaStoredProcedure getSubjectsWithPermissionsOnObjects(List<EntitaStoredProcedure> oggetti, List<String> predicati, List<String> ambiti, List<String> tipi, Boolean dammiSoggettiPropagati) throws BlackBoxPermissionException {
       
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
            return objectMapper.readValue(res, PermessoEntitaStoredProcedure.class);
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
        }
    }
}
