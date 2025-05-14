package com.jpa1prueba.existdbmodule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Database;

@Configuration
public class ExistDBConfig {

    @Value("${exist.uri:xmldb:exist://localhost:8080/exist/xmlrpc}")
    private String existUri;
    
    @Value("${exist.user:admin}")
    private String user;
    
    @Value("${exist.password:admin}")
    private String password;

    @Bean
    public Database initializeDatabase() throws Exception {
        Class<?> cl = Class.forName("org.exist.xmldb.DatabaseImpl");
        Database database = (Database) cl.getDeclaredConstructor().newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        return database;
    }
}