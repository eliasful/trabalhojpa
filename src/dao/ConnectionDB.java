package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class ConnectionDB {
    
    private static EntityManagerFactory entityManagerFactory;         
    
    public void connect() {
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()){
            entityManagerFactory = Persistence.createEntityManagerFactory("TrabalhoJPAPU");
        }                       
    }       
            
    public void disconnect() {
        entityManagerFactory.close();
    }   
    
    public static EntityManagerFactory getEntityManagerFactory(){
        return entityManagerFactory;
    }
    
    public static EntityManager newEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
}