package ru.bot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private UUID recipeId;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @Column(name = "chat_id")
    private Chat chat;
}
