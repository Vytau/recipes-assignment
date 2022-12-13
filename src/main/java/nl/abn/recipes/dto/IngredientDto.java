package nl.abn.recipes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientDto {

    private Long id;
    private String name;
    private long grams;
}
