package mena.gov.bf.service;


import mena.gov.bf.domain.JourFerier;
import mena.gov.bf.repository.JourFerierRepository;
import mena.gov.bf.service.dto.JourFerierDTO;
import mena.gov.bf.service.mapper.JourFerierMapper;
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
 * Service Implementation for managing {@link JourFerier}.
 */
@Service
@Transactional
public class JourFerierService {

    private final Logger log = LoggerFactory.getLogger(JourFerierService.class);

    private final JourFerierRepository jourFerierRepository;

    private final JourFerierMapper jourFerierMapper;

    public JourFerierService(JourFerierRepository jourFerierRepository, JourFerierMapper jourFerierMapper) {
        this.jourFerierRepository = jourFerierRepository;
        this.jourFerierMapper = jourFerierMapper;
    }

    /**
     * Save a acteur.
     *
     * @param jourFerierDTO the entity to save.
     * @return the persisted entity.
     */
    public JourFerierDTO save(JourFerierDTO jourFerierDTO) {
        log.debug("Request to save JourFérier : {}", jourFerierDTO);
        JourFerier jourFerier = jourFerierMapper.toEntity(jourFerierDTO);
        jourFerier = jourFerierRepository.save(jourFerier);
        return jourFerierMapper.toDto(jourFerier);
    }

    /**
     * Get all the acteurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<JourFerierDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Jour fériers");
        List<JourFerierDTO> jourFerierDTOS = jourFerierRepository.findAll()
            .stream()
            .map(jourFerierMapper::toDto)
            .filter(jourFerierDTO -> jourFerierDTO.isDeleted() != null && !jourFerierDTO.isDeleted())
            .collect(Collectors.toList());

        return new PageImpl<>(jourFerierDTOS, pageable, jourFerierDTOS.size());
    }

    /**
     * Get one acteur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JourFerierDTO> findOne(Long id) {
        log.debug("Request to get JourFerierDTO : {}", id);
        return jourFerierRepository.findById(id)
            .map(jourFerierMapper::toDto);
    }

    /**
     * Delete the acteur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete JourFerierDTO : {}", id);
        jourFerierRepository.deleteById(id);
    }
    public List<JourFerierDTO> updateAall(List<JourFerierDTO> jourFerierDTOS){
        jourFerierDTOS.forEach(jourFerierDTO -> {
            jourFerierDTO.setDeleted(true);
        });
        jourFerierRepository.saveAll(jourFerierDTOS.stream().map(jourFerierMapper::toEntity).collect(Collectors.toList()));
        List<JourFerierDTO> jourFerierDTOS1 =jourFerierRepository.findAll().stream().map(jourFerierMapper::toDto).filter(jourFerierDTO ->
            jourFerierDTO.isDeleted()!=null && !jourFerierDTO.isDeleted()).collect(Collectors.toList());
        return jourFerierDTOS;
    }
}
