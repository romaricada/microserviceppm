package mena.gov.bf.repository;

import mena.gov.bf.domain.Timbre;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Timbre entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TimbreRepository extends JpaRepository<Timbre, Long> {
}
