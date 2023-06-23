package com.agencia;

import com.agencia.entities.Comprador;
import com.agencia.jdbc.dao.CompradorDao;
import org.junit.Assert;
import org.junit.Test;

public class TestsCompradorDao {

    Comprador comprador = new Comprador("123.456.778-12", "Georgio Arrascaeta");
    CompradorDao dao = new CompradorDao();

    @Test
    public void testInsertComprador() {
        Assert.assertTrue(dao.save(comprador));
    }

    @Test
    public void testUpdateComprador() {
        comprador.setId(1);
        Assert.assertTrue(dao.update(comprador));
    }

    @Test
    public void testRemoveComprador() {
        Assert.assertTrue(dao.delete(1));
    }

    @Test
    public void testListComprador() {
        Assert.assertEquals(3, dao.findAll().size());
        for (Comprador c : dao.findAll()) {
            System.out.println(c);
        }
    }

    @Test
    public void testFindById() {
        System.out.println(dao.findById(12));
        Assert.assertNotNull(dao.findById(12));
    }

    @Test
    public void findListByNames() {
        String name = "g%";
        for (Comprador cp : dao.findListLikeNames(name)) {
            System.out.println(cp);
        }
        Assert.assertNotNull(dao.findListLikeNames(name));
    }
}
