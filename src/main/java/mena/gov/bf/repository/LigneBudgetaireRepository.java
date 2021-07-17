package mena.gov.bf.repository;

import mena.gov.bf.domain.LigneBudgetaire;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the LigneBudgetaire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LigneBudgetaireRepository extends JpaRepository<LigneBudgetaire, Long> {
    List<LigneBudgetaire> findAllByExerciceId (Long exerciceId);
    List<LigneBudgetaire> findLigneBudgetairesByExerciceIdAndDeletedIsFalse(Long exerciceId);
    List<LigneBudgetaire> findLigneBudgetaireByDeletedIsFalse();
    List<LigneBudgetaire> findAllByDeletedIsFalse();

}
