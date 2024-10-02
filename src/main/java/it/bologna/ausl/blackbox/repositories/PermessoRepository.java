package it.bologna.ausl.blackbox.repositories;

import it.bologna.ausl.model.entities.permessi.Permesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author gdm
 */
@RepositoryRestResource(collectionResourceRel = "permesso", path = "permesso", exported = false)
public interface PermessoRepository extends JpaRepository<Permesso, Integer>, QuerydslPredicateExecutor<Permesso> {

    @Query(value = "select permessi.entity_has_permission(?1, ?2, ?3, ?4)", nativeQuery = true)
//    @Procedure("permessi.entity_has_permission")
    public Boolean entityHasPermission(
            @Param("soggetti") String soggetti,
            @Param("predicati") String predicati,
            @Param("oggetti") String oggetti,
            @Param("ambito") String ambito
    );

    @Query(value = "select permessi.get_predicati_entita(?1, ?2, ?3, ?4)", nativeQuery = true)
//    @Procedure("permessi.get_predicati_entita")
    public String getPredicatiEntita(
            @Param("soggetto") String soggetto,
            @Param("oggetti") String oggetti,
            @Param("ambiti") String ambiti,
            @Param("tipi") String tipi
    );

    @Query(value = "select permessi.get_subjects_with_permissions_on_objects(?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
//    @Procedure("permessi.get_subjects_with_permissions_on_objects")
    public String getSubjectsWithPermissionsOnObjects(
            @Param("oggetti") String oggetti,
            @Param("predicati") String predicati,
            @Param("ambiti") String ambiti,
            @Param("tipi") String tipi,
            @Param("dammi_soggetti_propagati") Boolean dammiSoggettiPropagati,
            @Param("dammi_oggetti_propagati") Boolean dammiOggettiPropagati
    );

    @Query(value = "select permessi.insert_simple_permission(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
//    @Procedure("permessi.insert_simple_permission")
    public Boolean insertSimplePermission(
            @Param("soggetto") String soggetto,
            @Param("oggetto") String oggetto,
            @Param("predicato") String predicato,
            @Param("origine_permesso") String originePermesso,
            @Param("id_permesso_padre") Integer idPermessoPadre,
            @Param("propaga_soggetto") Boolean propagaSoggetto,
            @Param("propaga_oggetto") Boolean propagaOggetto,
            @Param("ambito") String ambito,
            @Param("tipo") String tipo,
            @Param("id_permesso_bloccato") Integer idPermessoBloccato
    );

    @Query(value = "select permessi.delete_permission(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
//    @Procedure("permessi.delete_permission")
    public Boolean deletePermission(
            @Param("soggetto") String soggetto,
            @Param("oggetto") String oggetto,
            @Param("predicato") String predicato,
            @Param("origine_permesso") String originePermesso,
            @Param("id_permesso_padre") Integer idPermessoPadre,
            @Param("propaga_soggetto") Boolean propagaSoggetto,
            @Param("propaga_oggetto") Boolean propagaOggetto,
            @Param("ambito") String ambito,
            @Param("tipo") String tipo,
            @Param("id_permesso_bloccato") Integer idPermessoBloccato
    );

    @Query(value = "select permessi.manage_permissions(?1, ?2)", nativeQuery = true)
//    @Procedure("permessi.manage_permissions")
    public String managePermissions(
            @Param("in_entities") String in_entities,
            @Param("p_data_di_lavoro") String dataDiLavoro
    );

    @Query(value = "select permessi.get_permissions_of_subject_actual_from_date(?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
//    @Procedure("permessi.get_permissions_of_subject_actual_from_date")
    public String getPermissionsOfSubjectActualFromDate(
            @Param("soggetto") String soggetto,
            @Param("oggetti") String oggetti,
            @Param("predicati") String predicati,
            @Param("ambiti") String ambiti,
            @Param("tipi") String tipi,
            @Param("dammi_permessi_virtuali") Boolean dammiPermessiVirtuali,
            @Param("p_data_permesso_inizio") String dataPermessoInizio
    );

    @Query(value = "select permessi.get_predicati_entita(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
//    @Procedure("permessi.get_predicati_entita")
    public String getPermissionsOfSubjectPastTillDate(
            @Param("soggetto") String soggetto,
            @Param("oggetti") String oggetti,
            @Param("predicati") String predicati,
            @Param("ambiti") String ambiti,
            @Param("tipi") String tipi,
            @Param("dammi_permessi_virtuali") Boolean dammiPermessiVirtuali,
            @Param("p_data_permesso_inizio") String dataPermessoInizio,
            @Param("p_data_permesso_fine") String dataPermessoFine
    );

    @Query(value = "select permessi.get_permissions_of_subject_furure_from_date(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
//    @Procedure("permessi.get_permissions_of_subject_furure_from_date")
    public String getPermissionsOfSubjectFuruteFromDate(
            @Param("soggetto") String soggetto,
            @Param("oggetti") String oggetti,
            @Param("predicati") String predicati,
            @Param("ambiti") String ambiti,
            @Param("tipi") String tipi,
            @Param("dammi_permessi_virtuali") Boolean dammiPermessiVirtuali,
            @Param("p_data_permesso_inizio") String dataPermessoInizio,
            @Param("p_data_permesso_fine") String dataPermessoFine
    );

    @Query(value= "select permessi.get_permissions_of_subject_advanced(?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
//    @Procedure("permessi.get_permissions_of_subject_advanced")
    public String getPermissionsOfSubjectAdvanced(
            @Param("soggetto") String soggetto,
            @Param("oggetti") String oggetti,
            @Param("predicati") String predicati,
            @Param("ambiti") String ambiti,
            @Param("tipi") String tipi,
            @Param("dammi_permessi_virtuali") Boolean dammiPermessiVirtuali,
            @Param("p_data_permesso_inizio") String dataPermessoInizio,
            @Param("p_data_permesso_fine") String dataPermessoFine,
            @Param("direzione") String direzione,
            @Param("soggetti_virtuali") String soggettiVirtuali
    );

    @Query(value = "select permessi.get_permissions_by_predicate(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
//    @Procedure("permessi.get_permissions_by_predicate")
    public String getPermissionsByPredicate(
            @Param("predicati") String predicati,
            @Param("ambiti") String ambiti,
            @Param("tipi") String tipi,
            @Param("gruppi_soggetto") String gruppiSoggetto,
            @Param("gruppi_oggetto") String gruppiOggetto
    );

//    @Procedure("permessi.spegni_permessi_veicolati_invalidi")
    @Query(value= "select permessi.spegni_permessi_veicolati_invalidi()", nativeQuery = true)
    public void spegniPermessiVeicolatiInvalidi();
}
