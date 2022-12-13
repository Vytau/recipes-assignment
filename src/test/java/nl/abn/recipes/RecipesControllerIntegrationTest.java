package nl.abn.recipes;


import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import nl.abn.recipes.controller.RecipeController;
import nl.abn.recipes.dto.IngredientDto;
import nl.abn.recipes.dto.PreparationStepDto;
import nl.abn.recipes.dto.RecipeDto;
import nl.abn.recipes.service.RecipeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RecipeController.class)
//@WithMockUser
public class RecipesControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @SneakyThrows
    @Test
    public void testSearchRequestWithInput_expectedOneRecipe() {

        final String searchQuery = "Cheese";
        final List<RecipeDto> recipeDtos = initRecipeList();
        final String apiResult = "[{\"id\":1,\"name\":\"Lasagne\",\"nrOfServings\":2,\"vegetarian\":false,\"ingredientList\":[{\"id\":1,\"name\":\"Tomato\",\"grams\":100},{\"id\":2,\"name\":\"Onion\",\"grams\":50},{\"id\":3,\"name\":\"Cheese\",\"grams\":200},{\"id\":4,\"name\":\"Beef\",\"grams\":500},{\"id\":5,\"name\":\"Lasagne noodles\",\"grams\":150}],\"preparationStepList\":[{\"id\":1,\"description\":\"Stir fry in pan beef and onion\"},{\"id\":2,\"description\":\"Put everything in the oven\"},{\"id\":3,\"description\":\"Bake 30 min in the oven\"}]}]";

        Mockito.when(recipeService.searchRecipe(searchQuery)).thenReturn(recipeDtos);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/recipe/search?query=" + searchQuery);

        MvcResult mockResponse = mockMvc.perform(requestBuilder).andReturn();

        Assertions.assertEquals(HttpStatus.OK.value(), mockResponse.getResponse().getStatus());
        JSONAssert.assertEquals(apiResult, mockResponse.getResponse().getContentAsString(), false);
    }

    @SneakyThrows
    @Test
    public void testSearchApiWithEmptyParameter_expectedBadRequestResponse() {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .get("/api/recipe/search?query=");

        MvcResult mockResponse = mockMvc.perform(requestBuilder).andReturn();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), mockResponse.getResponse().getStatus());
    }


    private List<RecipeDto> initRecipeList() {

        List<RecipeDto> recipeDtoList = new ArrayList<>();

        RecipeDto recipe1 = RecipeDto
            .builder()
            .id(1L)
            .name("Lasagne")
            .vegetarian(false)
            .nrOfServings(2)
            .ingredientList(new ArrayList<>())
            .preparationStepList(new ArrayList<>())
            .build();

        recipe1.getIngredientList().add(createIngredient(1, "Tomato", 100));
        recipe1.getIngredientList().add(createIngredient(2, "Onion", 50));
        recipe1.getIngredientList().add(createIngredient(3, "Cheese", 200));
        recipe1.getIngredientList().add(createIngredient(4, "Beef", 500));
        recipe1.getIngredientList().add(createIngredient(5, "Lasagne noodles", 150));

        recipe1.getPreparationStepList().add(createPreparationStep(1, "Stir fry in pan beef and onion"));
        recipe1.getPreparationStepList().add(createPreparationStep(2, "Put everything in the oven"));
        recipe1.getPreparationStepList().add(createPreparationStep(3, "Bake 30 min in the oven"));

        recipeDtoList.add(recipe1);

        return recipeDtoList;
    }

    private IngredientDto createIngredient(long id, String name, int grams) {

        return IngredientDto
            .builder()
            .id(id)
            .name(name)
            .grams(grams)
            .build();
    }

    private PreparationStepDto createPreparationStep(long id, String description) {

        return PreparationStepDto
            .builder()
            .id(id)
            .description(description)
            .build();
    }

}
