package mena.gov.bf.service;

import mena.gov.bf.domain.ReferentielDelai;
import mena.gov.bf.repository.*;
import mena.gov.bf.service.dto.NormeReferenceDTO;
import mena.gov.bf.service.dto.ReferentielDelaiDTO;
import mena.gov.bf.service.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ReferentielDelai}.
 */
@Service
@Transactional
public class NormeReferenceService {

    @Autowired
    NormeReferenceRepository normeReferenceRepository;

    @Autowired
    NormeReferenceMapper normeReferenceMapper;

    @Autowired
    ReferentielDelaiRepository referentielDelaiRepository;

    @Autowired
    ReferentielDelaiMapper referentielDelaiMapper;

    private final Logger log = LoggerFactory.getLogger(NormeReferenceService.class);

    public NormeReferenceDTO save(NormeReferenceDTO normeReferenceDTO) {
        if (normeReferenceDTO.getId() != null) {
            log.debug("requeste de update   ---   {}   ---", normeReferenceDTO);
        } else {
            log.debug("requeste de save   ---   {}   ---", normeReferenceDTO);
        }
        return normeReferenceMapper.toDto(normeReferenceRepository.save(normeReferenceMapper.toEntity(normeReferenceDTO)));
    }

    public List<NormeReferenceDTO> getAll() {

        List<ReferentielDelaiDTO> referentielDelaiDTOS = referentielDelaiRepository.findAll().stream().map(referentielDelaiMapper::toDto).collect(Collectors.toList());

        List<NormeReferenceDTO> normeReferenceDTOS = normeReferenceMapper.toDto(normeReferenceRepository.findAll()
                .stream()
                .filter(normeReference -> normeReference.isDeleted() != null && !normeReference.isDeleted())
                .collect(Collectors.toList()));

        if (!normeReferenceDTOS.isEmpty()) {
            normeReferenceDTOS.forEach(normeReferenceDTO -> {
                normeReferenceDTO.setReferentielDelais(
                        referentielDelaiDTOS.stream().
                                filter(referentielDelaiDTO -> referentielDelaiDTO.getNormeReference() != null
                                && referentielDelaiDTO.getNormeReference().getId() != null
                                && referentielDelaiDTO.getNormeReference().getId().equals(normeReferenceDTO.getId())
                                )
                                .collect(Collectors.toList())
                );
            });
        }
        return normeReferenceDTOS;
    }

}
