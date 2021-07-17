package mena.gov.bf.service;

import mena.gov.bf.domain.Etape;
import mena.gov.bf.repository.EtapeRepository;
import mena.gov.bf.service.dto.EtapeDTO;
import mena.gov.bf.service.mapper.EtapeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Etape}.
 */
@Service
@Transactional
public class EtapeService {

    private final Logger log = LoggerFactory.getLogger(EtapeService.class);

    private final EtapeRepository etapeRepository;

    private final EtapeMapper etapeMapper;

    public EtapeService(EtapeRepository etapeRepository, EtapeMapper etapeMapper) {
        this.etapeRepository = etapeRepository;
        this.etapeMapper = etapeMapper;
    }

    /**
     * Save a etape.
     *
     * @param etapeDTO the entity to save.
     * @return the persisted entity.
     */
    public EtapeDTO save(EtapeDTO etapeDTO) {
        log.debug("Request to save Etape : {}", etapeDTO);
        Etape etape = etapeMapper.toEntity(etapeDTO);
        etape = etapeRepository.save(etape);
        return etapeMapper.toDto(etape);
    }

    /**
     * Get all the etapes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<EtapeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Etapes");
        List<EtapeDTO> etapeDTOS = etapeRepository.findAll()
            .stream()
            .map(etapeMapper::toDto)
            .filter(etapeDTO -> etapeDTO.isDeleted() != null && !etapeDTO.isDeleted())
            .collect(Collectors.toList());

        return new PageImpl<>(etapeDTOS, pageable, etapeDTOS.size());
    }

    /**
     * Get one etape by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EtapeDTO> findOne(Long id) {
        log.debug("Request to get Etape : {}", id);
        return etapeRepository.findById(id)
            .map(etapeMapper::toDto);
    }

    /**
     * Delete the etape by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Etape : {}", id);
        etapeRepository.deleteById(id);
    }
    public List<EtapeDTO> updateAall(List<EtapeDTO> etapeDTOS){
        etapeDTOS.forEach(etapeDTO -> {
            etapeDTO.setDeleted(true);
        });
        etapeRepository.saveAll(etapeDTOS.stream().map(etapeMapper::toEntity).collect(Collectors.toList()));
        List<EtapeDTO> etapeDTOS1=etapeRepository.findAll().stream().map(etapeMapper::toDto).filter(etapeDTO ->
            etapeDTO.isDeleted()!=null && !etapeDTO.isDeleted()).collect(Collectors.toList());
        return etapeDTOS;
    }

    public List<EtapeDTO> findEyapeByModePassation(Long modePassationId) {
        return etapeMapper.toDto(etapeRepository.findAllByModePassation_IdAndDeletedIsFalse(modePassationId));
    }
}
