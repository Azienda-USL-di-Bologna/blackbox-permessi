package it.bologna.ausl.blackbox.test.repositories;

import it.bologna.ausl.model.entities.baborg.Pec;
import it.bologna.ausl.model.entities.baborg.QPec;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * per convenzione nostra, collectionResourceRel e path devono avere lo stesso
 * nome tutto in minuscolo
 */
@RepositoryRestResource(collectionResourceRel = "pec", path = "pec", exported = false)
public interface PecRepository extends JpaRepository<Pec, Integer>, QuerydslPredicateExecutor<QPec> {
}
