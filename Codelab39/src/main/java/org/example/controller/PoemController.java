package org.example.controller;

import org.example.entities.Poem;
import io.javalin.http.Context;
import org.example.daos.PoemDAO;

import java.util.List;

public class PoemController {
    private final PoemDAO poemDAO;

    public PoemController(PoemDAO poemDAO) {
        this.poemDAO = poemDAO;
    }

    // Handle GET /poems
    public void getAllPoems(Context ctx) {
        List<Poem> poems = poemDAO.getAllPoem();
        ctx.json(poems);
    }


    public void getSpecificPoem(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Poem poem = poemDAO.specificPoem(id);
        if (poem != null) {
            ctx.json(poem);
        } else {
            ctx.status(404).result("Poem not found");
        }
    }

    public void createPoem(Context ctx) {
        Poem poem = ctx.bodyAsClass(Poem.class); // Parse JSON body into Poem object
        poemDAO.createPoem(poem); // Save the poem to the database
        ctx.status(201).json(poem); // Return the created poem with 201 status
    }

    public void updatePoem(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Poem existingPoem = poemDAO.specificPoem(id); // Find the existing poem by ID
        if (existingPoem != null) {
            Poem updatedPoem = ctx.bodyAsClass(Poem.class); // Parse JSON body into updated Poem object
            updatedPoem.setId((long) id); // Ensure the ID remains the same
            poemDAO.updatePoem(updatedPoem); // Update the poem in the database
            ctx.json(updatedPoem); // Return the updated poem
        } else {
            ctx.status(404).result("Poem not found");
        }
    }

    public void deletePoem(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Poem poem = poemDAO.specificPoem(id); // Find the poem to delete
        if (poem != null) {
            poemDAO.deletePoem(id); // Delete the poem
            ctx.status(204).result("Poem deleted successfully");
        } else {
            ctx.status(404).result("Poem not found");
        }
    }

}