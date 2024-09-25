package org.example.dtos;

import jakarta.persistence.Entity;
import lombok.Data;
import org.example.Enum.Type;
import org.example.entities.Poem;

@Data
public class PoemDTO {

    private Type type; // Field to store the type of the poem
    private String poem;
    private String Author;
    private String Title;

    public PoemDTO(Poem poem ) {
        this.type = poem.getType();
        this.poem = poem.getPoem();
        this.Author = poem.getAuthor();
        this.Title = poem.getTitle();
    }

    public PoemDTO() {
    }
}
