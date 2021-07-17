package mena.gov.bf.repository;

import mena.gov.bf.domain.ModePassation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ModePassation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModePassationRepository extends JpaRepository<ModePassation, Long> {
    Optional<ModePassation> findTop1ByLibellePassation(String libelle);
    List<ModePassation> findAllByDeletedIsFalse();
}
