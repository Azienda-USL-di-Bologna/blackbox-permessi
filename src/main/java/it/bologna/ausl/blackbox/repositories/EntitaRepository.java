package it.bologna.ausl.blackbox.repositories;

import it.bologna.ausl.model.entities.permessi.Entita;
import it.bologna.ausl.model.entities.permessi.QEntita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author gdm
 */
@RepositoryRestResource(collectionResourceRel = "entita", path = "entita", exported = false)
public interface EntitaRepository extends JpaRepository<Entita, Integer>, QuerydslPredicateExecutor<Entita> {
}
