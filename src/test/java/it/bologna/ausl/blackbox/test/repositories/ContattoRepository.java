package it.bologna.ausl.blackbox.test.repositories;

import it.bologna.ausl.model.entities.rubrica.Contatto;
import it.bologna.ausl.model.entities.rubrica.QContatto;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * per convenzione nostra, collectionResourceRel e path devono avere lo stesso
 * nome tutto in minuscolo
 */
@RepositoryRestResource(collectionResourceRel = "contatto", path = "contatto", exported = false)
public interface ContattoRepository extends JpaRepository<Contatto, Integer>, QuerydslPredicateExecutor<Contatto> {
}
