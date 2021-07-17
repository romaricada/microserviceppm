package mena.gov.bf.service;

import mena.gov.bf.domain.Activite;
import mena.gov.bf.domain.EtapeActivitePpm;
import mena.gov.bf.repository.ActiviteRepository;
import mena.gov.bf.repository.EtapeActivitePpmRepository;
import mena.gov.bf.service.dto.EtapeActivitePpmDTO;
import mena.gov.bf.service.mapper.EtapeActivitePpmMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link EtapeActivitePpm}.
 */
@Service
@Transactional
public class EtapeActivitePpmService {

    private final Logger log = LoggerFactory.getLogger(EtapeActivitePpmService.class);

    private final EtapeActivitePpmRepository etapeActivitePpmRepository;

    private final EtapeActivitePpmMapper etapeActivitePpmMapper;
    @Autowired
    ActiviteRepository activiteRepository;

    public EtapeActivitePpmService(EtapeActivitePpmRepository etapeActivitePpmRepository, EtapeActivitePpmMapper etapeActivitePpmMapper) {
        this.etapeActivitePpmRepository = etapeActivitePpmRepository;
        this.etapeActivitePpmMapper = etapeActivitePpmMapper;
    }

    /**
     * Save a etapeActivitePpm.
     *
     * @param etapeActivitePpmDTO the entity to save.
     * @return the persisted entity.
     */
    public EtapeActivitePpmDTO save(EtapeActivitePpmDTO etapeActivitePpmDTO) {
        log.debug("Request to save EtapeActivitePpm : {}", etapeActivitePpmDTO);
        EtapeActivitePpm etapeActivitePpm = etapeActivitePpmMapper.toEntity(etapeActivitePpmDTO);
        etapeActivitePpm = etapeActivitePpmRepository.save(etapeActivitePpm);
        return etapeActivitePpmMapper.toDto(etapeActivitePpm);
    }

    /**
     * Get all the etapeActivitePpms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EtapeActivitePpmDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EtapeActivitePpms");
        return etapeActivitePpmRepository.findAll(pageable)
            .map(etapeActivitePpmMapper::toDto);
    }

    /**
     * Get one etapeActivitePpm by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EtapeActivitePpmDTO> findOne(Long id) {
        log.debug("Request to get EtapeActivitePpm : {}", id);
        return etapeActivitePpmRepository.findById(id)
            .map(etapeActivitePpmMapper::toDto);
    }

    /**
     * Delete the etapeActivitePpm by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EtapeActivitePpm : {}", id);
        etapeActivitePpmRepository.deleteById(id);
    }

    public List<EtapeActivitePpmDTO> findEtaActivitePPMbyActivite(Long activiteId) {
        Activite activite = activiteRepository.getOne(activiteId);
        return etapeActivitePpmRepository.findAllByDeletedIsFalse()
            .stream().filter(etapeActivitePpm ->
                etapeActivitePpm.getPpmActivite() != null && etapeActivitePpm.getPpmActivite().getActivite() != null
                    && etapeActivitePpm.getPpmActivite().getActivite().getId().equals(activite.getId()))
            .map(etapeActivitePpmMapper::toDto).collect(Collectors.toList());
    }
}
