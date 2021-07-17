package mena.gov.bf.repository;

import mena.gov.bf.domain.ReferentielDelai;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ReferentielDelai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReferentielDelaiRepository extends JpaRepository<ReferentielDelai, Long> {

    List<ReferentielDelai> findAllByModePassation_IdAndDeletedIsFalse(Long modePassationId);

    ReferentielDelai findByEtape_IdAndDeletedIsFalse(Long etapeId);

    List<ReferentielDelai> findReferentielDelaiByModePassationIdAndDeletedIsFalse(Long id);

    ReferentielDelai findReferentielDelaiByEtapeIdAndModePassationIdAndDeletedIsFalse(Long etapeId, Long modeId);

    List<ReferentielDelai> findReferentielDelaiByActeurIdAndDeletedIsFalse(Long acteurId);

}
