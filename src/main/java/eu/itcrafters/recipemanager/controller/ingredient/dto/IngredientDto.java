package eu.itcrafters.recipemanager.controller.ingredient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto implements Serializable {
    private String ingredientName;
    private String quantity;
}
