package it.bologna.ausl.blackbox.test;

import it.bologna.ausl.blackbox.PermissionManager;
import it.bologna.ausl.blackbox.exceptions.BlackBoxPermissionException;
import it.bologna.ausl.blackbox.test.repositories.UtenteRepository;
import it.bologna.ausl.model.entities.baborg.Utente;
import java.util.List;
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
}