package eu.itcrafters.recipemanager.persistence.recipeingredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {

    @Transactional
    @Modifying
    @Query("delete from RecipeIngredient ri where ri.recipe.id = :recipeId")
    void deleteAllByRecipeId(int recipeId);
}
