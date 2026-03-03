package ru.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bot.model.Recipe;
import ru.bot.model.User;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {

}