package mena.gov.bf.repository;


import mena.gov.bf.domain.CalculDelai;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the CalculDelai entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CalculDelaiRepository extends JpaRepository<CalculDelai, Long> {

    List<CalculDelai> findCalculDelaiByLibelleAndDeletedIsFalse(String libelle);


    CalculDelai findFirstByLibelleAndDeletedIsFalse(String libelle);

    List<CalculDelai> findAllByModePassation_IdAndLibelleAndDeletedIsFalse(Long modePassationId, String libelle);
    List<CalculDelai> findAllByModePassation_IdAndDeletedIsFalse(Long modePassationId);

}
