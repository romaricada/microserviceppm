package mena.gov.bf.repository;

import mena.gov.bf.domain.Besoin;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Besoin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BesoinRepository extends JpaRepository<Besoin, Long> {

    List<Besoin> findAllByNaturePrestationIdAndDeletedIsFalse(Long naturePrestationId);

    List<Besoin> findAllByUsedIsTrueAndDeletedIsFalse();

    List<Besoin> findBesoinByExerciceIdAndUsedIsFalseAndDeletedIsFalse(Long exerciceId);

    List<Besoin> findBesoinByExerciceIdAndDeletedIsFalse(Long exerciceId);
}
