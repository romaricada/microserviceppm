package mena.gov.bf.service;

import mena.gov.bf.domain.Timbre;
import mena.gov.bf.repository.TimbreRepository;
import mena.gov.bf.service.dto.TimbreDTO;
import mena.gov.bf.service.mapper.TimbreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Timbre}.
 */
@Service
@Transactional
public class TimbreService {

    private final Logger log = LoggerFactory.getLogger(TimbreService.class);

    private final TimbreRepository timbreRepository;

    private final TimbreMapper timbreMapper;

    public TimbreService(TimbreRepository timbreRepository, TimbreMapper timbreMapper) {
        this.timbreRepository = timbreRepository;
        this.timbreMapper = timbreMapper;
    }

    /**
     * Save a timbre.
     *
     * @param timbreDTO the entity to save.
     * @return the persisted entity.
     */
    public TimbreDTO save(TimbreDTO timbreDTO) {
        log.debug("Request to save Timbre : {}", timbreDTO);
        Timbre timbre = timbreMapper.toEntity(timbreDTO);
        timbre = timbreRepository.save(timbre);
        return timbreMapper.toDto(timbre);
    }

    /**
     * Get all the timbres.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TimbreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Timbres");
        return timbreRepository.findAll(pageable)
            .map(timbreMapper::toDto);
    }

    /**
     * Get one timbre by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TimbreDTO> findOne(Long id) {
        log.debug("Request to get Timbre : {}", id);
        return timbreRepository.findById(id)
            .map(timbreMapper::toDto);
    }

    /**
     * Delete the timbre by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Timbre : {}", id);
        timbreRepository.deleteById(id);
    }

    public TimbreDTO findTimbre(){
        return (TimbreDTO) timbreRepository.findAll().stream().map(timbreMapper::toDto).collect(Collectors.toList());
    }

}
