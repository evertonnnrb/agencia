package com.agencia;

import com.agencia.jdbc.ConnectionFactory;
import org.junit.Assert;
import org.junit.Test;

public class TestConnection {

    @Test
    public void testConnection(){
        Assert.assertNotNull(ConnectionFactory.getConnection());
    }

}
