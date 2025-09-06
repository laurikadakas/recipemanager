package eu.itcrafters.recipemanager.infrastructure.rest.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    NO_RECIPE_EXISTS("Recipe does not exist."),
    NO_INGREDIENT_FOUND("No ingredient found."),
    NO_INGREDIENT_LIST_FOUND("No list of ingredients was found."),
    NO_CUISINE_TYPE_EXISTS("Cuisine type does not exist");
    private final String message;
}
