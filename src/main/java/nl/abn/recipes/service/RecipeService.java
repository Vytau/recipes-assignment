package nl.abn.recipes.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import nl.abn.recipes.dto.RecipeDto;
import nl.abn.recipes.entity.Recipe;
import nl.abn.recipes.factory.DataFactory;
import nl.abn.recipes.repository.RecipeRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<RecipeDto> getAllVegetarianRecipes() {

        final List<Recipe> recipes = recipeRepository.findByVegetarian(true);

        return recipes
            .stream()
            .map(DataFactory::createRecipeDto)
            .collect(Collectors.toList());
    }

    public List<RecipeDto> searchRecipe(String query) {

        List<Recipe> recipes =
            recipeRepository.findRecipesByIngredientAndPreparation(query);

        return recipes
            .stream()
            .map(DataFactory::createRecipeDto)
            .collect(Collectors.toList());
    }

    public List<RecipeDto> getReceiptsWithDetailSearch(
        boolean vegetarian,
        int nrOfServings,
        String includedIngredient,
        String excludedIngredient,
        String instruction
    ) {

        List<Recipe> recipes =
            recipeRepository.findRecipesByDetailSearch(
                includedIngredient,
                excludedIngredient,
                nrOfServings,
                vegetarian,
                instruction
            );

        return recipes
            .stream()
            .map(DataFactory::createRecipeDto)
            .collect(Collectors.toList());
    }

    public List<RecipeDto> getAllRecipes() {

        List<Recipe> recipes = recipeRepository.findAll();

        return recipes
            .stream()
            .map(DataFactory::createRecipeDto)
            .collect(Collectors.toList());
    }
}
