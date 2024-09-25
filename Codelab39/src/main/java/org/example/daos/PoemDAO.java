package org.example.daos;

import org.example.entities.Poem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PoemDAO {

    private final EntityManagerFactory entityManagerFactory;
    public PoemDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    // Method to retrieve all poems
    public List<Poem> getAllPoem() {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<Poem> poems;
        try {
            TypedQuery<Poem> query = em.createQuery("SELECT p FROM Poem p", Poem.class);
            poems = query.getResultList();
        } finally {
            em.close();
        }
        return poems;
    }

    // Method to create a new poem
    public void createPoem(Poem poem) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin(); // Start transaction
            em.persist(poem); // Persist the poem entity
            transaction.commit(); // Commit the transaction
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback if there's an exception
            }
            throw e; // Rethrow the exception
        } finally {
            em.close(); // Close the EntityManager
        }
    }

    public Poem specificPoem(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Poem poem;
        try {
            poem = em.find(Poem.class, id); // Find poem by its ID
        } finally {
            em.close();
        }
        return poem;
    }


    public void updatePoem(Poem poem) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin(); // Start transaction
            em.merge(poem); // Update the poem (merge)
            transaction.commit(); // Commit the transaction
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback if there's an exception
            }
            throw e; // Rethrow the exception
        } finally {
            em.close(); // Close the EntityManager
        }
    }

    //Sletter et eksisterende digt
    public void deletePoem(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin(); // Start transaction
            Poem poem = em.find(Poem.class, id); // Find the poem to be deleted
            if (poem != null) {
                em.remove(poem); // Remove the poem if it exists
            }
            transaction.commit(); // Commit the transaction
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback if there's an exception
            }
            throw e; // Rethrow the exception
        } finally {
            em.close(); // Close the EntityManager
        }
    }
}