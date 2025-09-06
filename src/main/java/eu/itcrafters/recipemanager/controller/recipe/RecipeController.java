package eu.itcrafters.recipemanager.controller.recipe;

import eu.itcrafters.recipemanager.controller.recipe.dto.RecipeDto;
import eu.itcrafters.recipemanager.controller.recipe.dto.RecipeInfo;
import eu.itcrafters.recipemanager.infrastructure.rest.error.ApiError;
import eu.itcrafters.recipemanager.service.recipe.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/recipe")
    @Operation(summary = "Add a new recipe", description = "Adds a new recipe, throws an error if stated CuisineType is not found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid request body: payload validation failed",
                    content = @Content(schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "CuisineType does not exist",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public void addRecipe(@RequestBody @Valid RecipeDto recipeDto) {
        recipeService.addRecipe(recipeDto);
    }


    @GetMapping("/recipe/{recipeId}")
    @Operation(summary = "Finds a recipe by ID", description = "Finds a recipe by ID, throws an error if no match is found")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Recipe does not exist",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))
    })
    public RecipeDto findRecipe(@PathVariable int recipeId) {
        return recipeService.findRecipe(recipeId);
    }

    @GetMapping("/recipes")
    @Operation(summary = "Finds all recipes")
    public List<RecipeInfo> findAllRecipes() {
        return recipeService.findAllRecipes();
    }

}
