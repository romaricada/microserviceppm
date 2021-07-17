package mena.gov.bf.repository;

import mena.gov.bf.domain.PPM;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the PPM entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PPMRepository extends JpaRepository<PPM, Long> {
    Optional<PPM> findTop1ByIdExercice(Long id);
}
