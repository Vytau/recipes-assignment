package nl.abn.recipes;

import java.util.List;
import nl.abn.recipes.dto.RecipeDto;
import nl.abn.recipes.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class RecipesApplicationTests {

	@Autowired
	private RecipeService recipeService;

	@Test
	public void testGettingAllRecipes() {

		List<RecipeDto> allRecipes = recipeService.getAllRecipes();

		Assertions.assertNotNull(allRecipes);
	}

	@Test
	public void testGettingOnlyVegetarianResults() {

		final List<RecipeDto> vegRecipes = recipeService.getAllVegetarianRecipes();

		Assertions.assertNotNull(vegRecipes);
		Assertions.assertTrue(vegRecipes.size() > 0);
		Assertions.assertTrue(vegRecipes.stream().allMatch(RecipeDto::isVegetarian));
		Assertions.assertNotNull(vegRecipes.get(0).getIngredientList());
		Assertions.assertNotNull(vegRecipes.get(0).getPreparationStepList());
	}

	@Test
	public void testSearchQueryForPreparationTool() {

		final String searchInput = "oven";

		final List<RecipeDto> queryRecipes = recipeService.searchRecipe(searchInput);

		Assertions.assertNotNull(queryRecipes);
		Assertions.assertTrue(queryRecipes.size() > 0);
		for (RecipeDto recipe: queryRecipes) {
			Assertions.assertTrue(recipe.getPreparationStepList().size() > 0);
			Assertions.assertTrue(recipe.getPreparationStepList()
				.stream()
				.anyMatch(preparationStep -> preparationStep.getDescription().toLowerCase().contains(searchInput.toLowerCase()))
			);
		}
	}

	@Test
	public void testSearchQueryForIngredient() {

		final String searchInput = "rice";

		final List<RecipeDto> queryRecipes = recipeService.searchRecipe(searchInput);

		Assertions.assertNotNull(queryRecipes);
		Assertions.assertTrue(queryRecipes.size() > 0);
		for (RecipeDto recipe: queryRecipes) {
			Assertions.assertTrue(recipe.getPreparationStepList().size() > 0);
			Assertions.assertTrue(recipe.getIngredientList()
				.stream()
				.anyMatch(ingredient -> ingredient.getName().toLowerCase().contains(searchInput.toLowerCase()))
			);
		}
	}

	@Test
	public void testDetailSearchResult() {

		final boolean vegetarian = false;
		final int nrOfServings = 2;
		final String includedIngredient = "Cheese";
		final String excludedIngredient = "Potato";
		final String instruction = "oven";

		final List<RecipeDto> detailSearchRecipes = recipeService.getReceiptsWithDetailSearch(
			vegetarian,
			nrOfServings,
			includedIngredient,
			excludedIngredient,
			instruction
		);

		Assertions.assertNotNull(detailSearchRecipes);
		Assertions.assertTrue(detailSearchRecipes.size() > 0);
		Assertions.assertTrue(detailSearchRecipes.stream().allMatch(recipeDto -> recipeDto.isVegetarian() == vegetarian));
		Assertions.assertTrue(detailSearchRecipes.stream().allMatch(recipeDto -> recipeDto.getNrOfServings() == nrOfServings));
	}

	@Test
	public void testExceptionScenario() {

	}

}
