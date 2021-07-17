package mena.gov.bf.repository;

import mena.gov.bf.domain.ExerciceBudgetaire;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ExerciceBudgetaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExerciceBudgetaireRepository extends JpaRepository<ExerciceBudgetaire, Long> {
    Page<ExerciceBudgetaire> findAllByDeletedIsFalse(Pageable pageable);
    ExerciceBudgetaire findTopByActiveIsTrue();
    ExerciceBudgetaire findTop1ByOrderByAnneeDesc();

    List<ExerciceBudgetaire> findExerciceBudgetaireByElaborerIsTrue();

    ExerciceBudgetaire findTop1ByElaborerIsTrue();
}
