package mena.gov.bf.repository;

import mena.gov.bf.domain.Activite;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Activite entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {

    Activite findTop1ByOrderByCodeLignePlanDesc();

}
