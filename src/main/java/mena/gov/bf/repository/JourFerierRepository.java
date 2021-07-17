package mena.gov.bf.repository;


import mena.gov.bf.domain.JourFerier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Acteur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JourFerierRepository extends JpaRepository<JourFerier, Long> {

    List<JourFerier> findAllByExercice_IdAndDeletedIsFalse(Long exerciceId);
}
