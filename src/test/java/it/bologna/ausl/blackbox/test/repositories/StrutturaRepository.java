package it.bologna.ausl.blackbox.test.repositories;

import it.bologna.ausl.model.entities.baborg.QStruttura;
import it.bologna.ausl.model.entities.baborg.Struttura;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * per convenzione nostra, collectionResourceRel e path devono avere lo stesso
 * nome tutto in minuscolo
 */
@RepositoryRestResource(collectionResourceRel = "struttura", path = "struttura", exported = false)
public interface StrutturaRepository extends JpaRepository<Struttura, Integer>, QuerydslPredicateExecutor<QStruttura> {
}
