package it.bologna.ausl.blackbox.repositories;

import it.bologna.ausl.model.entities.permessi.QTipoEntita;
import it.bologna.ausl.model.entities.permessi.TipoEntita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author gdm
 */
@RepositoryRestResource(collectionResourceRel = "tipoentita", path = "tipoentita", exported = false)
public interface TipoEntitaRepository extends JpaRepository<TipoEntita, Integer>, QuerydslPredicateExecutor<QTipoEntita> {
}
