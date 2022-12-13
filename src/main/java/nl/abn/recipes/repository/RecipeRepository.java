package nl.abn.recipes.repository;

import java.util.List;
import nl.abn.recipes.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByVegetarian(@NonNull boolean vegetarian);

    @Query("""
        select distinct r
        from Recipe r left join r.ingredientList ingredientList left join r.preparationStepList preparationStepList
        where
            upper(ingredientList.name) like upper(concat('%', ?1, '%'))
        or
            upper(preparationStepList.description) like upper(concat('%', ?1, '%'))
        """)
    List<Recipe> findRecipesByIngredientAndPreparation(String ingredient);

    @Query("""
        select distinct r
        from Recipe r inner join r.ingredientList ingredientList inner join r.preparationStepList preparationStepList
        where
            upper(ingredientList.name) like upper(concat('%', ?1, '%'))
        and
            upper(ingredientList.name) not like upper(concat('%', ?2, '%'))
        and
            r.nrOfServings = ?3
        and
            r.vegetarian = ?4
        and
            upper(preparationStepList.description) like upper(concat('%', ?5, '%'))
        """)
    List<Recipe> findRecipesByDetailSearch(
        String containIngredient,
        String notContainIngredient,
        int nrOfServings,
        boolean vegetarian,
        String description
    );
}