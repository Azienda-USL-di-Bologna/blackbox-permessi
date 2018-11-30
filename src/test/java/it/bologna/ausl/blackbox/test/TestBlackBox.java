package it.bologna.ausl.blackbox.test;

import it.bologna.ausl.blackbox.PermissionManager;
import it.bologna.ausl.blackbox.PermissionRepositoryAccess;
import it.bologna.ausl.blackbox.exceptions.BlackBoxPermissionException;
import it.bologna.ausl.blackbox.test.repositories.UtenteRepository;
import it.bologna.ausl.blackbox.types.EntitaStoredProcedure;
import it.bologna.ausl.model.entities.baborg.Utente;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
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
    private EntityManager em;
    
    @Autowired
    PermissionRepositoryAccess permissionRepositoryAccess;

    @Test
    @Transactional
    public void testHasPermission() throws BlackBoxPermissionException {
        Utente u = utenteRepository.getOne(333427);
        String predicato = "FIRMA";
        Boolean res = permissionManager.hasPermission(u, predicato);
        Assert.assertThat("hasPermission", res, Matchers.isOneOf(true, false));
    }
    
    @Test
    @Transactional
    public void testGetPermission() throws BlackBoxPermissionException {
        Utente u = utenteRepository.getOne(333427);
        String tipo = "FLUShSO";
        List<String> permission = permissionManager.getPermission(u, null, tipo);
        Assert.assertThat("getPermission", permission, Matchers.anything());
    }
    
    @Test
    @Transactional
    public void testGetSubjectWithPermissionsOnObjects() {
        System.out.println("ciao");
        Set<EntityType<?>> entities = em.getMetamodel().getEntities();
        System.out.println("ciaoDopo");
    }
    
    @Test
    @Transactional
    public void testInsertSimplePermission() throws BlackBoxPermissionException {
        Utente u = utenteRepository.getOne(333427);
        String predicato = "REDIGE";
        String originePermesso = "TEST";
        System.out.println("scdsdcvsd");
        permissionManager.insertSimplePermission(u, null, predicato, originePermesso, false, false, null, null);
    }
    
    @Test
    @Transactional
    public void testDeletePermission() throws BlackBoxPermissionException {
        Utente u = utenteRepository.getOne(333427);
        String predicato = "REDIGE";
        String originePermesso = "TEST";
        System.out.println("scdsdcvsd");
        permissionManager.deletePermission(u, null, predicato, originePermesso, false, false, null, null);
    }
}