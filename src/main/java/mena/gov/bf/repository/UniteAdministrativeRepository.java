package mena.gov.bf.repository;

import mena.gov.bf.domain.UniteAdministrative;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UniteAdministrative entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UniteAdministrativeRepository extends JpaRepository<UniteAdministrative, Long> {
}
