/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.bologna.ausl.blackbox.factory;

/**
 *
 * @author Top
 */
import it.bologna.ausl.model.entities.permessi.Entita;
import it.bologna.ausl.model.entities.permessi.TipoEntita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.Table;
import org.springframework.stereotype.Service;

@Service
public class EntitaFactory {

    private final EntityManagerFactory emf;

    public EntitaFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Class<?> resolveEntityClass(TipoEntita tipoEntita) {
        String schema = tipoEntita.getTargetSchema();
        String table = tipoEntita.getTargetTable();

        for (EntityType<?> entityType : emf.getMetamodel().getEntities()) {
            Class<?> javaType = entityType.getJavaType();
            Table tableAnnotation = javaType.getAnnotation(Table.class);

            if (tableAnnotation != null) {
                String annSchema = tableAnnotation.schema();
                String annTable = tableAnnotation.name();

                if (schema.equalsIgnoreCase(annSchema) && table.equalsIgnoreCase(annTable)) {
                    return javaType; // esempio: Utente.class
                }
            }
        }

        throw new IllegalArgumentException(
            "Nessuna entity trovata per schema=" + schema + " e tabella=" + table
        );
    }

    public <T> T loadEntity(Entita entita) {
        Class<?> entityClass = resolveEntityClass(entita.getIdTipoEntita());
        EntityManager em = emf.createEntityManager();
        return (T) em.find(entityClass, entita.getIdProvenienza());
    }
}
