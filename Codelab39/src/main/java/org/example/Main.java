package org.example;

import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.config.HibernateConfig;
import org.example.controller.PoemController;
import org.example.daos.PoemDAO;


public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("poeams");
        EntityManager em = emf.createEntityManager();

        PoemDAO poemDAO = new PoemDAO(emf);
        PoemController poemController = new PoemController(poemDAO);

        Javalin app = Javalin.create().start(7777);

        app.get("/poems", poemController::getAllPoems);
        app.get("/poem/{id}", poemController::getSpecificPoem);
        app.post("/poem", poemController::createPoem);
        app.put("/poem/{id}", poemController::updatePoem);
        app.delete("/poem/{id}", poemController::deletePoem);
    }
}