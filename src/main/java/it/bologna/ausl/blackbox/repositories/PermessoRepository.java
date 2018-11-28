package it.bologna.ausl.blackbox.repositories;

import it.bologna.ausl.model.entities.permessi.Permesso;
import it.bologna.ausl.model.entities.permessi.QPermesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author gdm
 */
@RepositoryRestResource(collectionResourceRel = "permesso", path = "permesso", exported = false)
public interface PermessoRepository extends JpaRepository<Permesso, Integer>, QuerydslPredicateExecutor<QPermesso> {
    @Procedure("permessi.entity_has_permission")
    public Boolean entityHasPermission(
        @Param("soggetti") String soggetti, 
        @Param("predicati") String predicati, 
        @Param("oggetti") String oggetti, 
        @Param("ambito") String ambito
    );
    
    @Procedure("permessi.get_predicati_entita")
    public String getPredicatiEntita(
        @Param("soggetto") String soggetto,
        @Param("oggetti") String oggetti,
        @Param("ambiti") String ambiti,
        @Param("tipi") String tipi
    );
    
    @Procedure("permessi.get_subjects_with_permissions_on_objects")
    public String getSubjectsWithPermissionsOnObjects(
        @Param("oggetti") String oggetti,
        @Param("predicati") String predicati,
        @Param("ambiti") String ambiti,
        @Param("tipi") String tipi,
        @Param("dammi_soggetti_propagati") Boolean dammiSoggettiPropagati        
    );

//    @Query(nativeQuery = true, 
//            value = "select entity_has_permission from permessi.entity_has_permission("
//                    + ":soggetti\\:\\:text\\:\\:json, "
//                    + ":#{@permissionManager.getArrayString(#predicati)}\\:\\:text\\:\\:text[], "
//                    + ":oggetti\\:\\:text\\:\\:json, "
////                    + "null ,"
//                    + ":ambito\\:\\:text"
//                    + ")")
//    public Boolean entityHasPermission(
//            @Param("soggetti") String soggetti, 
//            @Param("predicati") String[] predicati, 
//            @Param("oggetti") String oggetti,
//            @Param("ambito") String ambito);
}
