package nl.abn.recipes.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import nl.abn.recipes.dto.RecipeDto;
import nl.abn.recipes.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecipeDto>> getAllRecipes() {
        final List<RecipeDto> recipes = recipeService.getAllRecipes();

        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/vegetarian")
    public ResponseEntity<List<RecipeDto>> getAllVegetarianRecipes() {

        final List<RecipeDto> recipes = recipeService.getAllVegetarianRecipes();

        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeDto>> getSearchResults(
        @RequestParam String query
    ) {

        if (query.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        final List<RecipeDto> searchRecipes = recipeService.searchRecipe(query);

        return new ResponseEntity<>(searchRecipes, HttpStatus.OK);
    }

    @GetMapping("/search-detail")
    public ResponseEntity<List<RecipeDto>> getFilteredRecipes(
        @RequestParam(defaultValue = "false") boolean vegetarian,
        @RequestParam(defaultValue = "0") int nrOfServings,
        @RequestParam(defaultValue = "") String includedIngredient,
        @RequestParam(defaultValue = "Nothing") String excludedIngredient,
        @RequestParam(defaultValue = "") String instruction
    ) {

        final List<RecipeDto> searchRecipes = recipeService.getReceiptsWithDetailSearch(
            vegetarian,
            nrOfServings,
            includedIngredient,
            excludedIngredient,
            instruction
        );

        return new ResponseEntity<>(searchRecipes, HttpStatus.OK);
    }
}
