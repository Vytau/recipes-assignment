package nl.abn.recipes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PreparationStepDto {

    private Long id;
    private String description;
}
