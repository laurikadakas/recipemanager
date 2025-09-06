package eu.itcrafters.recipemanager.controller.ingredient.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto implements Serializable {
    @NotNull
    private String ingredientName;
    @NotNull
    private String quantity;
}
