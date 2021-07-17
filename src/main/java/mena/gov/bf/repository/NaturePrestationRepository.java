package mena.gov.bf.repository;

import mena.gov.bf.domain.NaturePrestation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the NaturePrestation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NaturePrestationRepository extends JpaRepository<NaturePrestation, Long> {
  //  List<NaturePrestation> fi(String libelle);
    List<NaturePrestation> findAllByDeletedIsFalse();
}

