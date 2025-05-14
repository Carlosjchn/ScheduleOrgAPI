package com.jpa1prueba.existdbmodule.repository;


import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;

import java.util.Map;

@Repository
public class XmlDbRepository {
    
    private static final String URI = "xmldb:exist://localhost:8081/exist/xmlrpc/db/";
    private static final String USER = "admin";
    private static final String PASSWORD = "";  // cadena vacía para configuración predeterminada
    private static final String COLLECTION = "horariosApi";
    
    @PostConstruct
    public void init() {
        try {
            // Registrar el controlador de la base de datos
            String driver = "org.exist.xmldb.DatabaseImpl";
            Class<?> cl = Class.forName(driver);
            Database database = (Database) cl.getDeclaredConstructor().newInstance();
            DatabaseManager.registerDatabase(database);
            
            // Probar la conexión
            System.out.println("Probando conexión a ExistDB en: " + URI);
            Collection col = DatabaseManager.getCollection(URI + COLLECTION, USER, PASSWORD);
            if (col != null) {
                System.out.println("Conexión exitosa a la colección ExistDB: " + COLLECTION);
                col.close();
            } else {
                System.out.println("Colección no encontrada, intentando crearla...");
                createCollection();
            }
        } catch (Exception e) {
            System.err.println("Error al inicializar la conexión a ExistDB: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void createCollection() throws Exception {
        Collection root = DatabaseManager.getCollection(URI.substring(0, URI.length() - 1), USER, PASSWORD);
        if (root != null) {
            org.xmldb.api.modules.CollectionManagementService mgtService = 
                (org.xmldb.api.modules.CollectionManagementService) root.getService(
                    "CollectionManagementService", "1.0");
            mgtService.createCollection(COLLECTION);
            System.out.println("Colección creada: " + COLLECTION);
            root.close();
        }
    }
    
    private XPathQueryService obtenerServicioXPath() throws Exception {
        String driver = "org.exist.xmldb.DatabaseImpl";
        Class<?> cl = Class.forName(driver);
        Database database = (Database) cl.getDeclaredConstructor().newInstance();
        DatabaseManager.registerDatabase(database);
        Collection col = DatabaseManager.getCollection(URI + COLLECTION, USER, PASSWORD);
        XPathQueryService service = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        service.setProperty("encoding", "UTF-8");
        return service;
    }

    private Collection getCollection() throws XMLDBException {
        try {
            return DatabaseManager.getCollection(URI + COLLECTION, USER, PASSWORD);
        } catch (XMLDBException e) {
            System.err.println("Error al obtener la colección: " + e.getMessage());
            throw e;
        }
    }

    public void saveDocument(String documentId, String content) throws XMLDBException {
        Collection collection = getCollection();
        XMLResource document = (XMLResource) collection.createResource(documentId, XMLResource.RESOURCE_TYPE);
        document.setContent(content);
        collection.storeResource(document);
        collection.close();
    }

    public String getDocument(String documentId) throws XMLDBException {
        Collection collection = getCollection();
        XMLResource document = (XMLResource) collection.getResource(documentId);
        String result = document != null ? document.getContent().toString() : null;
        collection.close();
        return result;
    }

    public Map<String, String> getAllDocuments() throws XMLDBException {
        Collection collection = getCollection();
        Map<String, String> documents = new HashMap<>();
        
        String[] resources = collection.listResources();
        for (String resourceId : resources) {
            XMLResource document = (XMLResource) collection.getResource(resourceId);
            documents.put(resourceId, document.getContent().toString());
        }
        
        collection.close();
        return documents;
    }

    public void clearCollection() throws XMLDBException {
        Collection collection = getCollection();
        String[] resources = collection.listResources();
        for (String resourceId : resources) {
            XMLResource document = (XMLResource) collection.getResource(resourceId);
            collection.removeResource(document);
        }
        collection.close();
    }
    
    // Nuevo método para ejecutar consultas XPath
    public Map<String, String> executeXPathQuery(String xpathQuery) throws Exception {
        XPathQueryService service = obtenerServicioXPath();
        org.xmldb.api.base.ResourceSet result = service.query(xpathQuery);
        
        Map<String, String> results = new HashMap<>();
        org.xmldb.api.base.ResourceIterator iterator = result.getIterator();
        
        while (iterator.hasMoreResources()) {
            XMLResource resource = (XMLResource) iterator.nextResource();
            results.put(resource.getDocumentId(), resource.getContent().toString());
        }
        
        return results;
    }
}