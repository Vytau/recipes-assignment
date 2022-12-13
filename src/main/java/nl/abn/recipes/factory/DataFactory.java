package nl.abn.recipes.factory;

import java.util.stream.Collectors;
import nl.abn.recipes.dto.IngredientDto;
import nl.abn.recipes.dto.PreparationStepDto;
import nl.abn.recipes.dto.RecipeDto;
import nl.abn.recipes.entity.Ingredient;
import nl.abn.recipes.entity.PreparationStep;
import nl.abn.recipes.entity.Recipe;

public class DataFactory {

    public static RecipeDto createRecipeDto(Recipe recipe) {

        RecipeDto recipeDto = RecipeDto
            .builder()
            .id(recipe.getId())
            .name(recipe.getName())
            .vegetarian(recipe.isVegetarian())
            .nrOfServings(recipe.getNrOfServings())
            .ingredientList(recipe.getIngredientList()
                .stream()
                .map(DataFactory::createingredientDto)
                .collect(Collectors.toList()))
            .preparationStepList(recipe.getPreparationStepList()
                .stream()
                .map(DataFactory::createPreparationStepDto)
                .collect(Collectors.toList()))
            .build();

        return recipeDto;
    }

    public static IngredientDto createingredientDto(Ingredient ingredient) {

        IngredientDto ingredientDto = IngredientDto
            .builder()
            .id(ingredient.getId())
            .name(ingredient.getName())
            .grams(ingredient.getGrams())
            .build();

        return ingredientDto;
    }

    public static PreparationStepDto createPreparationStepDto(PreparationStep preparationStep) {

        PreparationStepDto preparationStepDto = PreparationStepDto
            .builder()
            .id(preparationStep.getId())
            .description(preparationStep.getDescription())
            .build();

        return preparationStepDto;
    }
}
