package it.bologna.ausl.blackbox;

import com.fasterxml.jackson.core.type.TypeReference;
import it.bologna.ausl.internauta.utils.bds.types.EntitaStoredProcedure;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.bologna.ausl.blackbox.exceptions.BlackBoxPermissionException;
import it.bologna.ausl.blackbox.repositories.PermessoRepository;
import it.bologna.ausl.blackbox.utils.BlackBoxConstants.Direzione;
import it.bologna.ausl.internauta.utils.bds.types.PermessoEntitaStoredProcedure;
import it.bologna.ausl.blackbox.utils.UtilityFunctions;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gus
 */
@Service
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
    public Boolean entityHasPermission(List<EntitaStoredProcedure> soggetti, List<String> predicati, List<EntitaStoredProcedure> oggetti, String ambito) throws BlackBoxPermissionException {
        String soggettiJsonString;
        String oggettiJsonString = null;
        String predicatiArrayString;

        try {
            soggettiJsonString = objectMapper.writeValueAsString(soggetti);
            if (oggetti != null) {
                oggettiJsonString = objectMapper.writeValueAsString(oggetti);
            }
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
            if (oggetti != null) {
                oggettiJsonString = objectMapper.writeValueAsString(oggetti);
            }
            if (ambiti != null) {
                ambitiArrayString = UtilityFunctions.getArrayString(objectMapper, ambiti);
            }
            if (tipi != null) {
                tipiArrayString = UtilityFunctions.getArrayString(objectMapper, tipi);
            }
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
    public List<PermessoEntitaStoredProcedure> getSubjectsWithPermissionsOnObjects(List<EntitaStoredProcedure> oggetti, List<String> predicati, List<String> ambiti, List<String> tipi, Boolean dammiSoggettiPropagati, Boolean dammiOggettiPropagati) throws BlackBoxPermissionException {

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
            String res = permessoRepository.getSubjectsWithPermissionsOnObjects(oggettiJsonString, predicatiArrayString, ambitiArrayString, tipiArrayString, dammiSoggettiPropagati, dammiOggettiPropagati);
            if (res != null) {
                return objectMapper.readValue(res, new TypeReference<List<PermessoEntitaStoredProcedure>>() {
                });
            } else {
                return null;
            }
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
            if (oggetto != null) {
                oggettoJsonString = objectMapper.writeValueAsString(oggetto);
            }
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
            if (oggetto != null) {
                oggettoJsonString = objectMapper.writeValueAsString(oggetto);
            }
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
     * @param dataDiLavoro se si passa null, verrà usata la data odierna
     * @throws BlackBoxPermissionException
     */
    public void managePermissions(List<PermessoEntitaStoredProcedure> permessoEntitaStoredProcedure, LocalDate dataDiLavoro) throws BlackBoxPermissionException {
        String permessoEntitaStoredProcedureJsonString = null;
        String dataDiLavoroString = null;
        try {
            permessoEntitaStoredProcedureJsonString = objectMapper.writeValueAsString(permessoEntitaStoredProcedure);
            if (dataDiLavoro != null) {
                dataDiLavoroString = UtilityFunctions.getLocalDateString(dataDiLavoro);
            }
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella creazione dei parametri per la chiamata della stored procedure", ex);
        }
//        System.out.println(permessoEntitaStoredProcedureJsonString);
        try {
            permessoRepository.managePermissions(permessoEntitaStoredProcedureJsonString, dataDiLavoroString);
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
        }
    }

//    public List<PermessoEntitaStoredProcedure> getPermissionsOfSubject(EntitaStoredProcedure soggetto,
//            List<EntitaStoredProcedure> oggetti, List<String> predicati,
//            List<String> ambiti, List<String> tipi, Boolean dammiPermessiVirtuali,
//            LocalDate dataPermesso, Boolean estraiStorico) throws BlackBoxPermissionException {
//        String soggettoString;
//        String oggettiString = null;
//        String predicatiArrayString;
//        String ambitiArrayString;
//        String tipiArrayString;
//        String dataPermessoString = null;
//
//        try {
//            soggettoString = objectMapper.writeValueAsString(soggetto);
//            if (oggetti != null) {
//                oggettiString = objectMapper.writeValueAsString(oggetti);
////                System.out.println("*********OGGETTI STRING\n" + oggettiString);
//            }
//
//            predicatiArrayString = UtilityFunctions.getArrayString(objectMapper, predicati);
//            ambitiArrayString = UtilityFunctions.getArrayString(objectMapper, ambiti);
//            tipiArrayString = UtilityFunctions.getArrayString(objectMapper, tipi);
//            if (dataPermesso != null) {
//                dataPermessoString = UtilityFunctions.getLocalDateString(dataPermesso);
//            }
//        } catch (Exception ex) {
//            throw new BlackBoxPermissionException("errore nella creazione dei parametri per la chiamata della stored procedure", ex);
//        }
//
//        try {
//            String res = permessoRepository.getPermissionsOfSubject(soggettoString, oggettiString, predicatiArrayString, ambitiArrayString, tipiArrayString, dammiPermessiVirtuali, dataPermessoString, estraiStorico);
//            if (res != null) {
//                return objectMapper.readValue(res, new TypeReference<List<PermessoEntitaStoredProcedure>>() {
//                });
//            } else {
//                return null;
//            }
//        } catch (Exception ex) {
//            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
//        }
//    }
    public List<PermessoEntitaStoredProcedure> getPermissionsOfSubjectAdvanced(
            EntitaStoredProcedure soggetto,
            List<EntitaStoredProcedure> oggetti,
            List<String> predicati,
            List<String> ambiti,
            List<String> tipi,
            Boolean dammiPermessiVirtuali,
            LocalDate dataPermessoInizio,
            LocalDate dataPermessoFine,
            Direzione direzione) throws BlackBoxPermissionException {
        return getPermissionsOfSubjectAdvanced(soggetto, oggetti, predicati, ambiti, tipi, dammiPermessiVirtuali, dataPermessoInizio, dataPermessoFine, null, direzione);
    }

    public List<PermessoEntitaStoredProcedure> getPermissionsOfSubjectAdvanced(
            EntitaStoredProcedure soggetto,
            List<EntitaStoredProcedure> oggetti,
            List<String> predicati,
            List<String> ambiti,
            List<String> tipi,
            Boolean dammiPermessiVirtuali,
            LocalDate dataPermessoInizio,
            LocalDate dataPermessoFine,
            List<EntitaStoredProcedure> soggettiVirtuali,
            Direzione direzione) throws BlackBoxPermissionException {

        String soggettoString;
        String oggettiString = null;
        String soggettiVirtualiString = null;
        String predicatiArrayString;
        String ambitiArrayString;
        String tipiArrayString;
        String dataPermessoInizioString = null;
        String dataPermessoFineString = null;

        try {
            soggettoString = objectMapper.writeValueAsString(soggetto);
            if (oggetti != null) {
                oggettiString = objectMapper.writeValueAsString(oggetti);
            }

            if (soggettiVirtuali != null) {
                soggettiVirtualiString = objectMapper.writeValueAsString(soggettiVirtuali);
            }

            predicatiArrayString = UtilityFunctions.getArrayString(objectMapper, predicati);
            ambitiArrayString = UtilityFunctions.getArrayString(objectMapper, ambiti);
            tipiArrayString = UtilityFunctions.getArrayString(objectMapper, tipi);
            if (dataPermessoInizio != null) {
                dataPermessoInizioString = UtilityFunctions.getLocalDateString(dataPermessoInizio);
            }
            if (dataPermessoFine != null) {
                dataPermessoFineString = UtilityFunctions.getLocalDateString(dataPermessoFine);
            }
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella creazione dei parametri per la chiamata della stored procedure", ex);
        }

        try {
            String res = permessoRepository.getPermissionsOfSubjectAdvanced(soggettoString, oggettiString, predicatiArrayString, ambitiArrayString, tipiArrayString, dammiPermessiVirtuali, dataPermessoInizioString, dataPermessoFineString, direzione.toString(), soggettiVirtualiString);
            if (res != null) {
                return objectMapper.readValue(res, new TypeReference<List<PermessoEntitaStoredProcedure>>() {
                });
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
        }
    }

    public List<PermessoEntitaStoredProcedure> getPermissionsByPredicate(
            List<String> predicati,
            List<String> ambiti,
            List<String> tipi,
            List<EntitaStoredProcedure> gruppiSoggetto,
            List<EntitaStoredProcedure> gruppiOggetto) throws BlackBoxPermissionException {

        String predicatiArrayString;
        String ambitiArrayString;
        String tipiArrayString;
        String gruppiSoggettoString = null;
        String gruppiOggettoString = null;

        try {

            predicatiArrayString = UtilityFunctions.getArrayString(objectMapper, predicati);
            ambitiArrayString = UtilityFunctions.getArrayString(objectMapper, ambiti);
            tipiArrayString = UtilityFunctions.getArrayString(objectMapper, tipi);
            if (gruppiSoggetto != null) {
                gruppiSoggettoString = objectMapper.writeValueAsString(gruppiSoggetto);
            }
            if (gruppiOggetto != null) {
                gruppiOggettoString = objectMapper.writeValueAsString(gruppiOggetto);
            }
            
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella creazione dei parametri per la chiamata della stored procedure", ex);
        }

        try {
            String res = permessoRepository.getPermissionsByPredicate(
                    predicatiArrayString, 
                    ambitiArrayString, 
                    tipiArrayString, 
                    gruppiSoggettoString,
                    gruppiOggettoString);

            if (res != null) {
                return objectMapper.readValue(res, new TypeReference<List<PermessoEntitaStoredProcedure>>() {
                });
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new BlackBoxPermissionException("errore nella chiamata alla store procedure", ex);
        }
    }

    public List<PermessoEntitaStoredProcedure> getPermissionsOfSubjectPastTillDate(
            EntitaStoredProcedure soggetto,
            List<EntitaStoredProcedure> oggetti,
            List<String> predicati,
            List<String> ambiti,
            List<String> tipi,
            Boolean dammiPermessiVirtuali,
            LocalDate dataPermessoInizio,
            LocalDate dataPermessoFine
    ) throws BlackBoxPermissionException {
        return getPermissionsOfSubjectAdvanced(soggetto, oggetti, predicati, ambiti, tipi, dammiPermessiVirtuali, dataPermessoInizio, dataPermessoFine, Direzione.PASSATO);
    }

    public List<PermessoEntitaStoredProcedure> getPermissionsOfSubjectFutureFromDate(
            EntitaStoredProcedure soggetto,
            List<EntitaStoredProcedure> oggetti,
            List<String> predicati,
            List<String> ambiti,
            List<String> tipi,
            Boolean dammiPermessiVirtuali,
            LocalDate dataPermessoInizio,
            LocalDate dataPermessoFine
    ) throws BlackBoxPermissionException {
        return getPermissionsOfSubjectAdvanced(soggetto, oggetti, predicati, ambiti, tipi, dammiPermessiVirtuali, dataPermessoInizio, dataPermessoFine, Direzione.FUTURO);
    }

    public List<PermessoEntitaStoredProcedure> getPermissionsOfSubjectActualFromDate(
            EntitaStoredProcedure soggetto,
            List<EntitaStoredProcedure> oggetti,
            List<String> predicati,
            List<String> ambiti,
            List<String> tipi,
            Boolean dammiPermessiVirtuali,
            LocalDate dataPermessoInizio
    ) throws BlackBoxPermissionException {
        return getPermissionsOfSubjectAdvanced(soggetto, oggetti, predicati, ambiti, tipi, dammiPermessiVirtuali, dataPermessoInizio, null, Direzione.PRESENTE);
    }
}
