package it.bologna.ausl.blackbox.repositories;

import it.bologna.ausl.model.entities.permessi.AmbitoSemantico;
import it.bologna.ausl.model.entities.permessi.QAmbitoSemantico;
import it.nextsw.common.repositories.NextSdrQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@Component("AmbitoSemanticoBlackBox")
@RepositoryRestResource(collectionResourceRel = "ambitosemantico", path = "ambitosemantico", exported = false)
public interface AmbitoSemanticoRepository extends JpaRepository<AmbitoSemantico, Integer>, NextSdrQueryDslRepository<AmbitoSemantico, Integer, QAmbitoSemantico> {
}
