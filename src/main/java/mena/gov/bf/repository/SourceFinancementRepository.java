package mena.gov.bf.repository;

import mena.gov.bf.domain.SourceFinancement;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the SourceFinancement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SourceFinancementRepository extends JpaRepository<SourceFinancement, Long> {
    List<SourceFinancement> findAllByDeletedIsFalse();
}
