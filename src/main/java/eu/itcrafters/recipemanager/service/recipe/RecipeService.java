package eu.itcrafters.recipemanager.service.recipe;

import eu.itcrafters.recipemanager.controller.ingredient.dto.IngredientDto;
import eu.itcrafters.recipemanager.controller.recipe.dto.RecipeDto;
import eu.itcrafters.recipemanager.controller.recipe.dto.RecipeInfo;
import eu.itcrafters.recipemanager.infrastructure.rest.error.Error;
import eu.itcrafters.recipemanager.infrastructure.rest.exception.DataNotFoundException;
import eu.itcrafters.recipemanager.persistence.cuisinetype.CuisineType;
import eu.itcrafters.recipemanager.persistence.cuisinetype.CuisineTypeRepository;
import eu.itcrafters.recipemanager.persistence.ingredient.Ingredient;
import eu.itcrafters.recipemanager.persistence.ingredient.IngredientRepository;
import eu.itcrafters.recipemanager.persistence.instruction.Instruction;
import eu.itcrafters.recipemanager.persistence.instruction.InstructionRepository;
import eu.itcrafters.recipemanager.persistence.recipe.Recipe;
import eu.itcrafters.recipemanager.persistence.recipe.RecipeMapper;
import eu.itcrafters.recipemanager.persistence.recipe.RecipeRepository;
import eu.itcrafters.recipemanager.persistence.recipeingredient.RecipeIngredient;
import eu.itcrafters.recipemanager.persistence.recipeingredient.RecipeIngredientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;
    private final CuisineTypeRepository cuisineTypeRepository;
    private final InstructionRepository instructionRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Transactional
    public void addRecipe(RecipeDto recipeDto) {
        CuisineType cuisineType = getValidCuisineType(recipeDto.getCuisineTypeName());

        Instruction instruction = new Instruction();
        instruction.setText(recipeDto.getInstructionText());
        instructionRepository.save(instruction);

        Recipe recipe = recipeMapper.toRecipe(recipeDto);
        recipe.setCuisineType(cuisineType);
        recipe.setInstruction(instruction);
        recipeRepository.save(recipe);

        saveRecipeIngredientsFromIngredientList(recipe, recipeDto.getIngredientList());
    }

    public RecipeDto findRecipe(int recipeId) {
        Recipe recipe = getValidRecipe(recipeId);

        RecipeDto recipeDto = recipeMapper.toRecipeDto(recipe);
        addIngredientsToRecipeDto(recipeDto, recipeId);
        return recipeDto;
    }

    public List<RecipeInfo> findAllRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        List<RecipeInfo> recipeInfos = recipeMapper.toRecipeInfos(recipes);

        for (RecipeInfo recipeInfo : recipeInfos) {
            recipeInfo.setIngredientList(findRecipeIngredients(recipeInfo.getRecipeId()));
        }
        return recipeInfos;
    }

    @Transactional
    public void updateRecipe(int recipeId, RecipeDto recipeDto) {
        Recipe recipe = getValidRecipe(recipeId);
        CuisineType cuisineType = getValidCuisineType(recipeDto.getCuisineTypeName());
        recipeMapper.updateRecipe(recipeDto, recipe);
        recipe.setCuisineType(cuisineType);

        Instruction instruction = new Instruction();
        instruction.setText(recipeDto.getInstructionText());
        instructionRepository.save(instruction);
        recipe.setInstruction(instruction);

        recipeRepository.save(recipe);
        resetRecipeIngredients(recipeId);
        saveRecipeIngredientsFromIngredientList(recipe, recipeDto.getIngredientList());
    }

    @Transactional
    public void deleteRecipe(Integer recipeId) {
        Recipe recipe = getValidRecipe(recipeId);
        recipeRepository.deleteById(recipe.getId());
    }

    private CuisineType getValidCuisineType(String cuisineTypeName) {
        return cuisineTypeRepository.findCuisineTypeBy(cuisineTypeName)
                .orElseThrow(() -> new DataNotFoundException(Error.NO_CUISINE_TYPE_EXISTS.getMessage()));
    }

    private Recipe getValidRecipe(int recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(
                () -> new DataNotFoundException(Error.NO_RECIPE_EXISTS.getMessage()));
    }

    private void addIngredientsToRecipeDto(RecipeDto recipeDto, int recipeId) {
        recipeDto.setIngredientList(findRecipeIngredients(recipeId));
    }

    private List<IngredientDto> findRecipeIngredients(int recipeId) {
        List<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findAll();
        List<IngredientDto> ingredients = new ArrayList<>();

        for (RecipeIngredient recipeIngredient : recipeIngredients) {
            if (recipeIngredient.getRecipe().getId().equals(recipeId)) {
                IngredientDto ingredientDto = new IngredientDto();
                ingredientDto.setIngredientName(recipeIngredient.getIngredient().getIngredientName());
                ingredientDto.setQuantity(recipeIngredient.getQuantity());
                ingredients.add(ingredientDto);
            }
        }
        return ingredients;
    }

    private void saveRecipeIngredientsFromIngredientList(Recipe recipe, List<IngredientDto> ingredientList) {
        for (IngredientDto ingredientDto : ingredientList) {
            Ingredient ingredient = new Ingredient();
            ingredient.setIngredientName(ingredientDto.getIngredientName());
            if (!ingredientRepository.existsByIngredientName(ingredientDto.getIngredientName())) {
                ingredientRepository.save(ingredient);
            }

            RecipeIngredient recipeIngredient = new RecipeIngredient();
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredientRepository.findByIngredientName(ingredientDto.getIngredientName()));
            recipeIngredient.setQuantity(ingredientDto.getQuantity());

            recipeIngredientRepository.save(recipeIngredient);
        }
    }

    private void resetRecipeIngredients(int recipeId) {
        recipeIngredientRepository.deleteAllByRecipeId(recipeId);
    }
}
