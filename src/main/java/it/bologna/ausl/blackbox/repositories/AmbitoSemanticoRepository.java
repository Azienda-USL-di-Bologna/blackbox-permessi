package it.bologna.ausl.blackbox.repositories;

import it.bologna.ausl.model.entities.permessi.AmbitoSemantico;
import it.bologna.ausl.model.entities.permessi.QAmbitoSemantico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ambitosemantico", path = "ambitosemantico", exported = false)
public interface AmbitoSemanticoRepository extends JpaRepository<AmbitoSemantico, Integer>, QuerydslPredicateExecutor<QAmbitoSemantico> {
}
