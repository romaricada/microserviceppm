package mena.gov.bf.repository;

import mena.gov.bf.domain.PpmActivite;
import mena.gov.bf.domain.enumeration.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Spring Data  repository for the PpmActivite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PpmActiviteRepository extends JpaRepository<PpmActivite, Long> {
    Set<PpmActivite> findAllByActiviteIdAndDeletedIsFalse(Long id);
    Optional<PpmActivite> findTopByActiviteId(Long id);
    PpmActivite findByActiviteIdAndDeletedIsFalse(Long activiteId);
    PpmActivite findByActiviteIdAndDeletedIsFalseAndNiveauEquals (Long activiteId, Niveau niveau);

    List<PpmActivite> findPpmActiviteByPpmIdAndDeletedIsFalse(Long ppmId);

    List<PpmActivite> findPpmActiviteByDeletedIsFalse();

}
