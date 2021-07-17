package mena.gov.bf.repository;

import mena.gov.bf.domain.Etape;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Etape entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtapeRepository extends JpaRepository<Etape, Long> {

    List<Etape> findAllByModePassation_IdAndDeletedIsFalse(Long modeId);
}
