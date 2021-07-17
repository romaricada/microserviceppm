package mena.gov.bf.service;

import mena.gov.bf.domain.SourceFinancement;
import mena.gov.bf.repository.SourceFinancementRepository;
import mena.gov.bf.service.dto.SourceFinancementDTO;
import mena.gov.bf.service.mapper.SourceFinancementMapper;
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
 * Service Implementation for managing {@link SourceFinancement}.
 */
@Service
@Transactional
public class SourceFinancementService {

    private final Logger log = LoggerFactory.getLogger(SourceFinancementService.class);

    private final SourceFinancementRepository sourceFinancementRepository;

    private final SourceFinancementMapper sourceFinancementMapper;

    public SourceFinancementService(SourceFinancementRepository sourceFinancementRepository, SourceFinancementMapper sourceFinancementMapper) {
        this.sourceFinancementRepository = sourceFinancementRepository;
        this.sourceFinancementMapper = sourceFinancementMapper;
    }

    /**
     * Save a sourceFinancement.
     *
     * @param sourceFinancementDTO the entity to save.
     * @return the persisted entity.
     */
    public SourceFinancementDTO save(SourceFinancementDTO sourceFinancementDTO) {
        log.debug("Request to save SourceFinancement : {}", sourceFinancementDTO);
        SourceFinancement sourceFinancement = sourceFinancementMapper.toEntity(sourceFinancementDTO);
        sourceFinancement = sourceFinancementRepository.save(sourceFinancement);
        return sourceFinancementMapper.toDto(sourceFinancement);
    }

    /**
     * Get all the sourceFinancements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SourceFinancementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SourceFinancements");
        List<SourceFinancementDTO> sourceFinancementDTOS=sourceFinancementRepository.findAll().stream().map(sourceFinancementMapper::toDto)
            .filter(sourceFinancementDTO -> sourceFinancementDTO.isDeleted()!=null && !sourceFinancementDTO.isDeleted()).collect(Collectors.toList());

        return new PageImpl<>(sourceFinancementDTOS, pageable, sourceFinancementDTOS.size());

    }

    /**
     * Get one sourceFinancement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SourceFinancementDTO> findOne(Long id) {
        log.debug("Request to get SourceFinancement : {}", id);
        return sourceFinancementRepository.findById(id)
            .map(sourceFinancementMapper::toDto);
    }

    /**
     * Delete the sourceFinancement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SourceFinancement : {}", id);
        sourceFinancementRepository.deleteById(id);
    }
    public List<SourceFinancementDTO> updateAall(List<SourceFinancementDTO> sourceFinancementDTOS){
        sourceFinancementDTOS.forEach(sourceFinancementDTO -> {
            sourceFinancementDTO.setDeleted(true);
        });
        sourceFinancementRepository.saveAll(sourceFinancementDTOS.stream().map(sourceFinancementMapper::toEntity).collect(Collectors.toList()));
        List<SourceFinancementDTO> sourceFinancementDTOS1=sourceFinancementRepository.findAll().stream().map(sourceFinancementMapper::toDto).filter(sourceFinancementDTO ->
            sourceFinancementDTO.isDeleted()!=null && !sourceFinancementDTO.isDeleted()).collect(Collectors.toList());
        return sourceFinancementDTOS;
    }

    public List<SourceFinancementDTO> findAllListes(){
        return sourceFinancementRepository.findAllByDeletedIsFalse().stream().map(sourceFinancementMapper::toDto).collect(Collectors.toList());
    }
}
