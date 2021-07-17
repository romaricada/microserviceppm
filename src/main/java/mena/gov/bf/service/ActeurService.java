package mena.gov.bf.service;

import mena.gov.bf.domain.Acteur;
import mena.gov.bf.repository.ActeurRepository;
import mena.gov.bf.service.dto.ActeurDTO;
import mena.gov.bf.service.mapper.ActeurMapper;
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
 * Service Implementation for managing {@link Acteur}.
 */
@Service
@Transactional
public class ActeurService {

    private final Logger log = LoggerFactory.getLogger(ActeurService.class);

    private final ActeurRepository acteurRepository;

    private final ActeurMapper acteurMapper;

    public ActeurService(ActeurRepository acteurRepository, ActeurMapper acteurMapper) {
        this.acteurRepository = acteurRepository;
        this.acteurMapper = acteurMapper;
    }

    /**
     * Save a acteur.
     *
     * @param acteurDTO the entity to save.
     * @return the persisted entity.
     */
    public ActeurDTO save(ActeurDTO acteurDTO) {
        log.debug("Request to save Acteur :::::::::::::::        {}     ::::::::::::::::::", acteurDTO);
        Acteur acteur = acteurMapper.toEntity(acteurDTO);
        acteur.setUserId(acteurDTO.getUserId());
        acteur = acteurRepository.save(acteur);
        return acteurMapper.toDto(acteur);
    }

    /**
     * Get all the acteurs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ActeurDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Acteurs");
        List<ActeurDTO> acteurDTOS = acteurRepository.findAll()
            .stream()
            .map(acteurMapper::toDto)
            .filter(acteurDTO -> acteurDTO.isDeleted() != null && !acteurDTO.isDeleted())
            .collect(Collectors.toList());

        return new PageImpl<>(acteurDTOS, pageable, acteurDTOS.size());
    }

    /**
     * Get one acteur by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ActeurDTO> findOne(Long id) {
        log.debug("Request to get Acteur : {}", id);
        return acteurRepository.findById(id)
            .map(acteurMapper::toDto);
    }

    /**
     * Delete the acteur by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Acteur : {}", id);
        acteurRepository.deleteById(id);
    }
    public List<ActeurDTO> updateAall(List<ActeurDTO> acteurDTOS){
        acteurDTOS.forEach(acteurDTO -> {
            acteurDTO.setDeleted(true);
        });
        acteurRepository.saveAll(acteurDTOS.stream().map(acteurMapper::toEntity).collect(Collectors.toList()));
        List<ActeurDTO> acteurDTOS1=acteurRepository.findAll().stream().map(acteurMapper::toDto).filter(acteurDTO ->
            acteurDTO.isDeleted()!=null && !acteurDTO.isDeleted()).collect(Collectors.toList());
        return acteurDTOS;
    }
}
