package eu.itcrafters.recipemanager.persistence.recipe;

import eu.itcrafters.recipemanager.controller.recipe.dto.RecipeDto;
import eu.itcrafters.recipemanager.controller.recipe.dto.RecipeInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecipeMapper {

    @Mapping(source = "recipeName", target = "recipeName")
    @Mapping(source = "cuisineType.name", target = "cuisineTypeName")
    @Mapping(source = "instruction.text", target = "instructionText")
    @Mapping(source = "dateAdded", target = "dateAdded")
    @Mapping(source = "description", target = "description")
    RecipeDto toRecipeDto(Recipe recipe);

    @InheritConfiguration(name = "toRecipeDto")
    @Mapping(source = "id", target = "recipeId")
    RecipeInfo toRecipeInfo(Recipe recipe);

    List<RecipeInfo> toRecipeInfos(List<Recipe> recipes);
}