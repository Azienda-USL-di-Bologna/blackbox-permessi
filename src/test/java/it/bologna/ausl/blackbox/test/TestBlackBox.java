package it.bologna.ausl.blackbox.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.bologna.ausl.blackbox.PermissionManager;
import it.bologna.ausl.blackbox.PermissionRepositoryAccess;
import it.bologna.ausl.blackbox.exceptions.BlackBoxPermissionException;
import it.bologna.ausl.blackbox.test.repositories.PecRepository;
import it.bologna.ausl.blackbox.test.repositories.StrutturaRepository;
import it.bologna.ausl.blackbox.test.repositories.UtenteRepository;
import it.bologna.ausl.internauta.utils.bds.types.CategoriaPermessiStoredProcedure;
import it.bologna.ausl.internauta.utils.bds.types.EntitaStoredProcedure;
import it.bologna.ausl.internauta.utils.bds.types.PermessoEntitaStoredProcedure;
import it.bologna.ausl.internauta.utils.bds.types.PermessoStoredProcedure;
import it.bologna.ausl.model.entities.baborg.Struttura;
import it.bologna.ausl.model.entities.baborg.Utente;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlackBoxApplication.class)
public class TestBlackBox {

    @Autowired
    private PermissionManager permissionManager;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PecRepository pecRepository;

    @Autowired
    private StrutturaRepository strutturaRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    PermissionRepositoryAccess permissionRepositoryAccess;

    @Autowired
    ObjectMapper objectMapper;

//    @Test
//    @Transactional
//    public void testHasPermission() throws BlackBoxPermissionException {
//        Utente u = utenteRepository.getOne(333427);
//        String predicato = "FIRMA";
//        Boolean res = permissionManager.hasPermission(u, predicato);
//        Assert.assertThat("hasPermission", res, Matchers.isOneOf(true, false));
//    }
    @Test
    @Transactional
    public void testGetPermission() throws BlackBoxPermissionException {
        Utente u = utenteRepository.getOne(333427);
        String tipo = "FLUSSO";
        List<String> permission = permissionManager.getPermission(u, null, tipo);
        Assert.assertThat("getPermission", permission, Matchers.anything());
    }

    @Test
    @Transactional
    public void testGetSubjectWithPermissionsOnObjects() throws BlackBoxPermissionException {
        Struttura s = strutturaRepository.getOne(26901);
        String predicato = "REDIGE";
        List<PermessoEntitaStoredProcedure> res = permissionManager.getSubjectsWithPermissionsOnObject(s, Arrays.asList(new String[]{predicato}), null, null, true);
        Assert.assertThat("getPermission", res, Matchers.anything());
    }

    @Test
    @Transactional
    public void testInsertSimplePermission() throws BlackBoxPermissionException {
        Utente u = utenteRepository.getOne(333427);
        String predicato = "REDIGE";
        String originePermesso = "TEST";
        permissionManager.insertSimplePermission(u, null, predicato, originePermesso, false, false, "TEST", "TEST");
//        Assert.assertThat("getPermission", res, Matchers.anything());
    }

    @Test
    @Transactional
    public void testDeletePermission() throws BlackBoxPermissionException {
        Utente u = utenteRepository.getOne(333427);
        String predicato = "REDIGE";
        String originePermesso = "TEST";
        permissionManager.deletePermission(u, null, predicato, originePermesso, false, false, "TEST", "TEST");
    }

    @Test
    @Transactional
    public void testManagePermissions() throws BlackBoxPermissionException {
        PermessoStoredProcedure permesso = new PermessoStoredProcedure("ELIMINA", false, false, "testorigin", null, LocalDateTime.now(), null);
        List<PermessoStoredProcedure> permessi = new ArrayList();
        permessi.add(permesso);
        CategoriaPermessiStoredProcedure categoria = new CategoriaPermessiStoredProcedure("PECG", "PEC", permessi);
        List<CategoriaPermessiStoredProcedure> categorie = new ArrayList();
        categorie.add(categoria);
        EntitaStoredProcedure soggetto = new EntitaStoredProcedure(333427, "baborg", "utenti");
        PermessoEntitaStoredProcedure permessoEntita = new PermessoEntitaStoredProcedure(soggetto, soggetto, categorie);
        List<PermessoEntitaStoredProcedure> lista = new ArrayList();
        lista.add(permessoEntita);
        permissionRepositoryAccess.managePermissions(lista, null);
    }

    @Test
    @Transactional
    public void testGetPermissionsOfSubjectAllTime() throws BlackBoxPermissionException {
        //Struttura s = strutturaRepository.getOne(26901);
        EntitaStoredProcedure soggetto = new EntitaStoredProcedure(27296, "baborg", "strutture");
//        EntitaStoredProcedure soggetto = new EntitaStoredProcedure(27294, "baborg", "strutture");
//        EntitaStoredProcedure soggetto = new EntitaStoredProcedure(27286, "baborg", "strutture");
        String predicato = "SPEDISCE";
        List<PermessoEntitaStoredProcedure> res = permissionRepositoryAccess.getPermissionsOfSubjectPastTillDate(soggetto, null, Arrays.asList(new String[]{predicato}), null, null, true, null, null);
        List<PermessoEntitaStoredProcedure> res2;
        res2 = permissionRepositoryAccess.getPermissionsOfSubjectActualFromDate(soggetto, null, Arrays.asList(new String[]{predicato}), null, null, true, null);
        List<PermessoEntitaStoredProcedure> res3 = permissionRepositoryAccess.getPermissionsOfSubjectFutureFromDate(soggetto, null, Arrays.asList(new String[]{predicato}), null, null, true, null,null);
        Assert.assertThat("PermissionsOfSubjectPastFromDate", res, Matchers.anything());
        Assert.assertThat("PermissionsOfSubjectActualFromDate", res2, Matchers.anything());
        Assert.assertThat("PermissionsOfSubjectFutureFromDate", res3, Matchers.anything());
    }

    @Test
    @Transactional
    public void testGetPermissionsOfSubjectConData() throws BlackBoxPermissionException {
        //Struttura s = strutturaRepository.getOne(26901);
        EntitaStoredProcedure soggetto = new EntitaStoredProcedure(27296, "baborg", "strutture");
//        EntitaStoredProcedure soggetto = new EntitaStoredProcedure(27294, "baborg", "strutture");
//        EntitaStoredProcedure soggetto = new EntitaStoredProcedure(27286, "baborg", "strutture");
        String predicato = "SPEDISCE";
        LocalDate now = LocalDate.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        String dateString = now.format(formatter);
        List<PermessoEntitaStoredProcedure> res = permissionRepositoryAccess.getPermissionsOfSubject(soggetto, null, Arrays.asList(new String[]{predicato}), null, null, true, now, null);
        Assert.assertThat("GetPermissionsOfSubject", res, Matchers.anything());
    }

    @Test
    @Transactional
    public void testGetPermissionOfSubjectConOggetto() throws BlackBoxPermissionException, JsonProcessingException {
        /*id utente: 294714
        id struttura: 31276*/
        EntitaStoredProcedure soggetto = new EntitaStoredProcedure(294714, "baborg", "utenti");
        List<EntitaStoredProcedure> oggetti = new ArrayList<>();
        oggetti.add(new EntitaStoredProcedure(31276, "baborg", "strutture"));
        List<PermessoEntitaStoredProcedure> res = permissionRepositoryAccess.getPermissionsOfSubject(soggetto, oggetti,
                null, null, null, true, null, false);
        System.out.println("RES\n" + objectMapper.writeValueAsString(res));
        Assert.assertThat("GetPermissionsOfSubject", res, Matchers.anything());
    }
}
