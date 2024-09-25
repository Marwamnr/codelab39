package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.example.Enum.Type;


@Entity
@Data
public class Poem{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Type type; // Field to store the type of the poem
    private String poem;
    private String author;
    private String title;

    public Poem(Type type, String poem, String author, String title) {
        this.type = type;
        this.poem = poem;
        this.author = author;
        this.title = title;
    }

    public Poem() {

    }
}