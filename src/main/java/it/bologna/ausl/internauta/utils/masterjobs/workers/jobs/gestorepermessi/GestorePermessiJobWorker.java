package it.bologna.ausl.internauta.utils.masterjobs.workers.jobs.gestorepermessi;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import it.bologna.ausl.internauta.utils.masterjobs.annotations.MasterjobsWorker;
import it.bologna.ausl.internauta.utils.masterjobs.exceptions.MasterjobsWorkerException;
import it.bologna.ausl.internauta.utils.masterjobs.workers.jobs.JobWorker;
import it.bologna.ausl.internauta.utils.masterjobs.workers.jobs.JobWorkerResult;
import it.bologna.ausl.model.entities.permessi.QEntita;
import it.bologna.ausl.model.entities.permessi.QPermesso;
import it.bologna.ausl.model.entities.permessi.QTipoEntita;
import java.time.ZonedDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gdm
 */
@MasterjobsWorker
public class GestorePermessiJobWorker extends JobWorker<GestorePermessiJobWorkerData, JobWorkerResult> {
    
    private static final Logger log = LoggerFactory.getLogger(GestorePermessiJobWorkerData.class);
    
    private JPAQueryFactory queryFactory;
    private final QEntita qEntita = QEntita.entita;
    private final QTipoEntita qTipoEntita = QTipoEntita.tipoEntita;
    private final QPermesso qPermesso = QPermesso.permesso;
    private final ZonedDateTime now = ZonedDateTime.now();
    
    @Override
    public String getName() {
        return this.getClass().getSimpleName();        
    }
    
    @Override
    public JobWorkerResult doRealWork() throws MasterjobsWorkerException {
        queryFactory = new JPAQueryFactory(entityManager);
        GestorePermessiJobWorkerData workerData = getWorkerData();
        BooleanExpression filtroPermessi = getFiltroPermessi();

        if (workerData.getOperazione() == GestorePermessiJobWorkerData.Operazione.SPEGNI) {
            queryFactory
                .update(qPermesso)
                .set(qPermesso.attivoAl, now)
                .set(qPermesso.spentoDa, getName())
                .where(filtroPermessi)
                .execute();
        } else {
            filtroPermessi = filtroPermessi.and(qPermesso.spentoDa.eq(getName()));
            queryFactory
                .update(qPermesso)
                .setNull(qPermesso.attivoAl)
                .where(filtroPermessi)
                .execute();
        }
        return null;
    }
    
    private Integer getIdEntitaPermesso(GestorePermessiJobWorkerData.EntitaPermesso entitaPermesso) {
        Integer id = queryFactory
            .select(qEntita.id)
            .from(qEntita).join(qTipoEntita).on(qEntita.idTipoEntita.id.eq(qTipoEntita.id))
            .where(
                qEntita.idProvenienza.eq(entitaPermesso.getIdProvenienza()
                ).and(
                        qTipoEntita.targetSchema.eq(entitaPermesso.getSchema())
                ).and(
                        qTipoEntita.targetTable.eq(entitaPermesso.getTable())
                )
            )
            .fetchOne();
        return id;
    }
    
    private BooleanExpression getFiltroPermessi() {
        
        GestorePermessiJobWorkerData workerData = getWorkerData();
        GestorePermessiJobWorkerData.EntitaPermesso soggetto = workerData.getSoggetto();
        GestorePermessiJobWorkerData.EntitaPermesso entitaVeicolante = workerData.getEntitaVeicolante();
        List<String> ambiti = workerData.getAmbiti();
        List<String> tipi = workerData.getTipi();
        Integer idEntitaSoggetto = getIdEntitaPermesso(soggetto);
        Integer idEntitaVeicolante = entitaVeicolante != null? getIdEntitaPermesso(entitaVeicolante): null;
        
        BooleanExpression filter = 
                qPermesso.idSoggetto.id.eq(idEntitaSoggetto);
        if (entitaVeicolante != null) {
            filter = filter.and(qPermesso.idEntitaVeicolante.id.eq(idEntitaVeicolante));
        }
        if (ambiti != null && !ambiti.isEmpty()) {
            filter = filter.and(qPermesso.ambito.in(ambiti));
        }
        if (tipi != null && !tipi.isEmpty()) {
            filter = filter.and(qPermesso.tipo.in(tipi));
        }
        return filter;
    }
    
}
