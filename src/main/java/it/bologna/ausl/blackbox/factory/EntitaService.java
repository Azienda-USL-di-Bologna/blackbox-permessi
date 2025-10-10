package it.bologna.ausl.blackbox.factory;

import it.bologna.ausl.internauta.model.bds.types.EntitaStoredProcedure;
import it.bologna.ausl.model.entities.permessi.Entita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.stereotype.Service;

/**
 *
 * @author Top
 */
@Service
public class EntitaService {

    private final EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    public EntitaService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private Class<?> resolveEntityClass(EntitaStoredProcedure esp) {
        String schema = esp.getSchema();
        String table = esp.getTable();

        return emf.getMetamodel().getEntities().stream()
            .map(EntityType::getJavaType)
            .filter(clazz -> {
                Table t = clazz.getAnnotation(Table.class);
                return t != null
                    && schema.equalsIgnoreCase(t.schema())
                    && table.equalsIgnoreCase(t.name());
            })
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
            "Nessuna entity trovata per " + schema + "." + table
        ));
    }

    public Object loadEntity(EntitaStoredProcedure esp) {
        Class<?> entityClass = resolveEntityClass(esp);
        return em.find(entityClass, esp.getIdProvenienza());
    }
}
