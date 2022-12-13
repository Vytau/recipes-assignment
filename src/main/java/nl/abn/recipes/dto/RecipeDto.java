package nl.abn.recipes.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeDto {

    private Long id;
    private String name;
    private int nrOfServings;
    private boolean vegetarian;
    private List<IngredientDto> ingredientList;
    private List<PreparationStepDto> preparationStepList;
}
