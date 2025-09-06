package eu.itcrafters.recipemanager.controller.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeInfo extends RecipeDto implements Serializable {
    private Integer recipeId;
}
