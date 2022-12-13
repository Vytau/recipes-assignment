package nl.abn.recipes.component;

import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import nl.abn.recipes.entity.Ingredient;
import nl.abn.recipes.entity.PreparationStep;
import nl.abn.recipes.entity.Recipe;
import nl.abn.recipes.repository.IngredientRepository;
import nl.abn.recipes.repository.PreparationStepRepository;
import nl.abn.recipes.repository.RecipeRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataLoader {

    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final PreparationStepRepository preparationStepRepository;

    @PostConstruct
    public void dataInit() {

        /////////////
        // Lasagne //
        /////////////
        Recipe recipe1 = new Recipe();
        recipe1.setName("Lasagne");
        recipe1.setVegetarian(false);
        recipe1.setNrOfServings(2);

        recipeRepository.save(recipe1);

        ingredientRepository.save(createIngredient("Tomato", 100, recipe1));
        ingredientRepository.save(createIngredient("Onion", 50, recipe1));
        ingredientRepository.save(createIngredient("Cheese", 200, recipe1));
        ingredientRepository.save(createIngredient("Beef", 500, recipe1));
        ingredientRepository.save(createIngredient("Lasagne noodles", 150, recipe1));

        preparationStepRepository.save(createPreparationStep("Stir fry in pan beef and onion", recipe1));
        preparationStepRepository.save(createPreparationStep("Put everything in the oven", recipe1));
        preparationStepRepository.save(createPreparationStep("Bake 30 min in the oven", recipe1));

        /////////////
        // Risotto //
        /////////////
        Recipe recipe2 = new Recipe();
        recipe2.setName("Risotto");
        recipe2.setVegetarian(true);
        recipe2.setNrOfServings(4);

        recipeRepository.save(recipe2);

        ingredientRepository.save(createIngredient("Rice", 500, recipe2));
        ingredientRepository.save(createIngredient("Mushroom", 100, recipe2));
        ingredientRepository.save(createIngredient("Cheese", 150, recipe2));
        ingredientRepository.save(createIngredient("Vine", 50, recipe2));

        preparationStepRepository.save(createPreparationStep("Put rice into pan", recipe2));
        preparationStepRepository.save(createPreparationStep("Put vine into pan", recipe2));
        preparationStepRepository.save(createPreparationStep("Add water every 5min for 30min", recipe2));
        preparationStepRepository.save(createPreparationStep("Add mushroom", recipe2));

        /////////////////
        // Cesar salad //
        /////////////////
        Recipe recipe3 = new Recipe();
        recipe3.setName("Caesar Salad");
        recipe3.setVegetarian(true);
        recipe3.setNrOfServings(5);

        recipeRepository.save(recipe3);

        ingredientRepository.save(createIngredient("Lettuce", 500, recipe3));
        ingredientRepository.save(createIngredient("Parmesan cheese", 100, recipe3));
        ingredientRepository.save(createIngredient("Dressing", 100, recipe3));

        preparationStepRepository.save(createPreparationStep("Cut lettuce", recipe3));
        preparationStepRepository.save(createPreparationStep("Mix everything", recipe3));

        ////////////////
        // Thai Curry //
        ////////////////
        Recipe recipe4 = new Recipe();
        recipe4.setName("Thai Curry");
        recipe4.setVegetarian(false);
        recipe4.setNrOfServings(6);

        recipeRepository.save(recipe4);

        ingredientRepository.save(createIngredient("Paprika", 200, recipe4));
        ingredientRepository.save(createIngredient("Rice", 250, recipe4));
        ingredientRepository.save(createIngredient("Chicken", 500, recipe4));
        ingredientRepository.save(createIngredient("Coconut milk", 100, recipe4));
        ingredientRepository.save(createIngredient("Red curry paste", 50, recipe4));

        preparationStepRepository.save(createPreparationStep("Cut paprika and chicken", recipe4));
        preparationStepRepository.save(createPreparationStep("Put everything in the pan", recipe4));
        preparationStepRepository.save(createPreparationStep("Stir fry and later add coconut milk", recipe4));
        preparationStepRepository.save(createPreparationStep("Cook rice in the rice cooker", recipe4));
    }

    private Ingredient createIngredient(String name, long grams, Recipe recipe) {

        Ingredient ingredient = new Ingredient();

        ingredient.setName(name);
        ingredient.setGrams(grams);
        ingredient.setRecipe(recipe);

        return ingredient;
    }

    private PreparationStep createPreparationStep(String description, Recipe recipe) {

        PreparationStep preparation = new PreparationStep();

        preparation.setDescription(description);
        preparation.setRecipe(recipe);

        return preparation;
    }
}
