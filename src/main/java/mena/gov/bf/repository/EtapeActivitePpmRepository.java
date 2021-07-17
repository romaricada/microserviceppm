package mena.gov.bf.repository;

import mena.gov.bf.domain.EtapeActivitePpm;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data  repository for the EtapeActivitePpm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EtapeActivitePpmRepository extends JpaRepository<EtapeActivitePpm, Long> {
    List<EtapeActivitePpm> findAllByDeletedIsFalse();
    List<EtapeActivitePpm> findEtapeActivitePpmByPpmActiviteIdAndDeletedIsFalse(Long ppmActiviteId);

    // List<EtapeActivitePpm> findEtapeActivitePpmByPpmActiviteIdAndDeletedIsFalse(Long ppmActiviteId);

    List<EtapeActivitePpm> findEtapeActivitePpmByDeletedIsFalse();

    //List<EtapeActivitePpm> findEtapeActivitePpmByDebutIsAfterDateDebutAnAndFinIsBeforeDateFin(LocalDate dateDebut, LocalDate dateFin);
}
