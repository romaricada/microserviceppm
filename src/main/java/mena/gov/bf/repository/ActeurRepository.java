package mena.gov.bf.repository;

import mena.gov.bf.domain.Acteur;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Acteur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActeurRepository extends JpaRepository<Acteur, Long> {

    Acteur findActeurByUserId(Long userId);
}
