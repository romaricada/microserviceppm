package mena.gov.bf.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import mena.gov.bf.domain.BesoinLigneBudgetaire;

import java.util.List;
import java.util.Set;

/**
 * Spring Data repository for the BesoinLigneBudgetaire entity.
 */
//@SuppressWarnings("unused")
@Repository
public interface BesoinLigneBudgetaireRepository extends JpaRepository<BesoinLigneBudgetaire, Long> {

    List<BesoinLigneBudgetaire> findBesoinLigneBudgetaireByBesoinIdAndLigneBudgetId(Long besoinId, Long ligneBudgetId);

    List<BesoinLigneBudgetaire> findAllByActiviteIdAndDeletedIsFalse(Long idActivite);

    List<BesoinLigneBudgetaire> findAllByBesoinIdAndDeletedIsFalse(Long idBesoin);

    List<BesoinLigneBudgetaire> findAllByBesoin_IdAndDeletedIsFalse(Long besoinId);

    List<BesoinLigneBudgetaire> findAllByLigneBudgetIdAndDeletedIsFalse(Long ligneId);

    List<BesoinLigneBudgetaire> findBesoinLigneBudgetaireByDeletedIsFalse();
}
