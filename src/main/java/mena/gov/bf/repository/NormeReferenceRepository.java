package mena.gov.bf.repository;

import mena.gov.bf.domain.NormeReference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NormeReferenceRepository extends JpaRepository<NormeReference, Long> {

    List<NormeReference> findNormeReferenceByDeletedIsFalse();
}
