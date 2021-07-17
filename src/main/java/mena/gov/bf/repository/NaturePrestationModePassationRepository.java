package mena.gov.bf.repository;

import mena.gov.bf.domain.NaturePrestationModePassation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NaturePrestationModePassationRepository extends JpaRepository<NaturePrestationModePassation, Long> {

    List<NaturePrestationModePassation> findByNaturePrestation_IdAndDeletedIsFalse(Long naturePrestationId);
    Optional<NaturePrestationModePassation> findTop1ByNaturePrestationIdAndModePassationId(Long naturePrestationId, Long modePassationId);
    List<NaturePrestationModePassation> findAllByNaturePrestationIdAndModePassationId(Long naturePrestationId, Long modePassationId);
    List<NaturePrestationModePassation> findAllByDeletedIsFalse();
}
