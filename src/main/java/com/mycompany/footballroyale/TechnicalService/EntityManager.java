/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.TechnicalService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
/**
 *
 * @author 39327
 */
public class EntityManager {
 

;


    private static EntityManager instance;
    private final SessionFactory sessionFactory;

    /**
     * Private constructor for implement the singleton
     */
    private EntityManager() {
        this.sessionFactory = HibernateService.getInstance().getSessionFactory();
    }

    /**
     * Public method to take the instance of the EntityManager class
     * @return      the instance of the class (Singleton Pattern)
     */
    public static EntityManager getInstance() {
        if (instance == null) {
            instance = new EntityManager();
        }
        return instance;
    }


    /**
     * Method to save a new object in the db
     * @param entity
     * @param <T>
     */
    public <T> void save(T entity) {
        Transaction tx = null;
        try(Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to update an existing object in the db
     * @param entity
     * @param <T>
     */
    public <T> void update(T entity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Method that returns an object, found by the id and the class type
     * @param entityType
     * @param id
     * @return              the specified object from the id
     * @param <T>
     */
    public <T> T findById(Class<T> entityType, Long id) {
        T result = null;
        try (Session session = sessionFactory.openSession()) {
            result = session.get(entityType, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method that returns an object, found by a given attribute and its value.
     * @param entityType The class type of the entity.
     * @param attribute The name of the attribute to search by.
     * @param attributeValue The value of the attribute.
     * @return The specified object matching the attribute, or null if not found.
     * @param <T> The entity type.
     */
    public <T> T findByAttribute(Class<T> entityType, String attribute, Object attributeValue) {
        T result = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM " + entityType.getSimpleName() + " a WHERE a." + attribute + " = :attributeValue";
            Query query = session.createQuery(hql, entityType);
            query.setParameter("attributeValue", attributeValue);
            result = (T) query.getSingleResult();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
 
    public <T> List<T> findListByAttribute(Class<T> entityType, String attribute, Object attributeValue) {
        List<T> results = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
           String hql = "FROM " + entityType.getName() + " a WHERE a." + attribute + " = :attributeValue";
           results = session.createQuery(hql, entityType)
                        .setParameter("attributeValue", attributeValue)
                        .getResultList(); // <--- Fondamentale per avere una lista
    } catch (Exception e) {
        System.err.println("Errore durante la findListByAttribute");
        e.printStackTrace();
    }
    return results;
}

    /**
     * Method that returns all the objects from a table in the db, so all the objects from a certain class
     * @param entityType
     * @return              all the objects from a table
     * @param <T>
     */
    public <T> List<T> findAll(Class<T> entityType) {
        List<T> result = null;
        try (Session session = sessionFactory.openSession()) {
            result = session.createQuery("FROM " + entityType.getSimpleName(), entityType).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public <T> List<T> findRandomElements(Class<T> entityType, int maxElements) {
        List<T> result = null;
        try (Session session = sessionFactory.openSession()) {
            result = session.createQuery("FROM " + entityType.getSimpleName() + " ORDER BY FUNCTION('RAND')", entityType).setMaxResults(maxElements).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

