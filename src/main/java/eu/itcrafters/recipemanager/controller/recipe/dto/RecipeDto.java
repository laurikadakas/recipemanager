package eu.itcrafters.recipemanager.controller.recipe.dto;

import eu.itcrafters.recipemanager.controller.ingredient.dto.IngredientDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link eu.itcrafters.recipemanager.persistence.recipe.Recipe}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto implements Serializable {
    @NotNull
    @Size(max = 50)
    private String recipeName;
    @NotNull
    private String cuisineTypeName;
    @NotNull
    private List<IngredientDto> ingredientList;
    @NotNull
    private String instructionText;
    @NotNull
    private LocalDate dateAdded;
    @NotNull
    @Size(max = 255)
    private String description;
}