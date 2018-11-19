package it.bologna.ausl.blackbox.test.repositories;

import it.bologna.ausl.model.entities.baborg.QUtente;
import it.bologna.ausl.model.entities.baborg.Utente;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * per convenzione nostra, collectionResourceRel e path devono avere lo stesso
 * nome tutto in minuscolo
 */
@RepositoryRestResource(collectionResourceRel = "utente", path = "utente", exported = false)
public interface UtenteRepository extends JpaRepository<Utente, Integer>, QuerydslPredicateExecutor<QUtente> {
}
