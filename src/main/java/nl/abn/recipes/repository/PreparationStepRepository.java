package nl.abn.recipes.repository;

import nl.abn.recipes.entity.PreparationStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreparationStepRepository extends JpaRepository<PreparationStep, Long> {
}