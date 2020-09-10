package it.bologna.ausl.blackbox.test.repositories;

import it.bologna.ausl.model.entities.baborg.Persona;
import it.bologna.ausl.model.entities.baborg.QPersona;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * per convenzione nostra, collectionResourceRel e path devono avere lo stesso
 * nome tutto in minuscolo
 */
@RepositoryRestResource(collectionResourceRel = "persona", path = "persona", exported = false)
public interface PersonaRepository extends JpaRepository<Persona, Integer>, QuerydslPredicateExecutor<Persona> {
}
