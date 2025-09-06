package eu.itcrafters.recipemanager.persistence.ingredient;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @Query("select (count(i) > 0) from Ingredient i where upper(i.ingredientName) = upper(?1)")
    boolean existsByIngredientName(String ingredientName);

    @NotNull
    Ingredient findByIngredientName(String ingredientName);
}
