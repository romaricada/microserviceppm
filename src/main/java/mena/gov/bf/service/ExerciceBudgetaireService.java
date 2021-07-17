package mena.gov.bf.service;

import mena.gov.bf.domain.ExerciceBudgetaire;
import mena.gov.bf.repository.ExerciceBudgetaireRepository;
import mena.gov.bf.service.dto.ExerciceBudgetaireDTO;
import mena.gov.bf.service.mapper.ExerciceBudgetaireMapper;
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
 * Service Implementation for managing {@link ExerciceBudgetaire}.
 */
@Service
@Transactional
public class ExerciceBudgetaireService {

    private final Logger log = LoggerFactory.getLogger(ExerciceBudgetaireService.class);

    private final ExerciceBudgetaireRepository exerciceBudgetaireRepository;

    private final ExerciceBudgetaireMapper exerciceBudgetaireMapper;

    public ExerciceBudgetaireService(ExerciceBudgetaireRepository exerciceBudgetaireRepository,
            ExerciceBudgetaireMapper exerciceBudgetaireMapper) {
        this.exerciceBudgetaireRepository = exerciceBudgetaireRepository;
        this.exerciceBudgetaireMapper = exerciceBudgetaireMapper;
    }

    /**
     * Save a exerciceBudgetaire.
     *
     * @param exerciceBudgetaireDTO the entity to save.
     * @return the persisted entity.
     */
    public ExerciceBudgetaireDTO save(ExerciceBudgetaireDTO exerciceBudgetaireDTO) {
        log.debug("Request to save ExerciceBudgetaire : {}", exerciceBudgetaireDTO);
        ExerciceBudgetaire exerciceBudgetaire = exerciceBudgetaireMapper.toEntity(exerciceBudgetaireDTO);
        exerciceBudgetaire = exerciceBudgetaireRepository.save(exerciceBudgetaire);
        return exerciceBudgetaireMapper.toDto(exerciceBudgetaire);
    }

    /**
     * Get all the exerciceBudgetaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ExerciceBudgetaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ExerciceBudgetaires");
        List<ExerciceBudgetaireDTO> exerciceBudgetaireDTOS = exerciceBudgetaireRepository.findAll()
            .stream()
            .map(exerciceBudgetaireMapper::toDto)
            .filter(exerciceBudgetaireDTO -> exerciceBudgetaireDTO.isDeleted() != null && !exerciceBudgetaireDTO.isDeleted())
            .collect(Collectors.toList());

        return new PageImpl<>(exerciceBudgetaireDTOS, pageable, exerciceBudgetaireDTOS.size());
    }

    /**
     * Get one exerciceBudgetaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ExerciceBudgetaireDTO> findOne(Long id) {
        log.debug("Request to get ExerciceBudgetaire : {}", id);
        return exerciceBudgetaireRepository.findById(id).map(exerciceBudgetaireMapper::toDto);
    }

    /**
     * Delete the exerciceBudgetaire by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ExerciceBudgetaire : {}", id);
        exerciceBudgetaireRepository.deleteById(id);
    }

    public ExerciceBudgetaireDTO activateExercice(ExerciceBudgetaireDTO exerciceBudgetaireDTO) {
        ExerciceBudgetaire exerciceBudgetaire = exerciceBudgetaireMapper.toEntity(exerciceBudgetaireDTO);
        updateExercie(exerciceBudgetaire);
        exerciceBudgetaire = exerciceBudgetaireRepository.save(exerciceBudgetaire);
        return exerciceBudgetaireMapper.toDto(exerciceBudgetaire);
    }

    public void updateExercie(ExerciceBudgetaire exerciceBudgetaire) {
        if (exerciceBudgetaire.isActive()) {
            this.exerciceBudgetaireRepository.saveAll(exerciceBudgetaireRepository.findAll().stream()
                    .peek(exerciceBudgetaire1 -> exerciceBudgetaire1.setActive(false)).collect(Collectors.toList()));
        }
    }

    public List<ExerciceBudgetaireDTO> findAllWithoutPagination() {
        return exerciceBudgetaireRepository.findAll().stream()
                .filter(vale -> vale.isDeleted() != null && !vale.isDeleted()).map(exerciceBudgetaireMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<ExerciceBudgetaireDTO> findCurrentExercice() {

        return exerciceBudgetaireRepository.findTopByActiveIsTrue() != null ?
            Optional.of(exerciceBudgetaireRepository.findTopByActiveIsTrue()).map(exerciceBudgetaireMapper::toDto)
            : Optional.of(new ExerciceBudgetaireDTO());

    }

    public ExerciceBudgetaireDTO activateElaborerPPM(ExerciceBudgetaireDTO exerciceBudgetaireDTO) {
        List<ExerciceBudgetaire> exerciceBudgetaires = exerciceBudgetaireRepository.findExerciceBudgetaireByElaborerIsTrue();
        if (!exerciceBudgetaires.isEmpty()) {
            exerciceBudgetaires.forEach(exerciceBudgetaire -> {
                exerciceBudgetaire.setElaborer(false);
            });
            exerciceBudgetaireRepository.saveAll(exerciceBudgetaires);
        }
        return exerciceBudgetaireMapper.toDto(exerciceBudgetaireRepository.save(exerciceBudgetaireMapper.toEntity(exerciceBudgetaireDTO)));

    }

    public ExerciceBudgetaireDTO finfExerciceByElaborerPPMIstrue() {
        return exerciceBudgetaireMapper.toDto(exerciceBudgetaireRepository.findTop1ByElaborerIsTrue());
    }
    public List<ExerciceBudgetaireDTO> updateAall(List<ExerciceBudgetaireDTO> exerciceBudgetaireDTOS){
        exerciceBudgetaireDTOS.forEach(exerciceBudgetaireDTO -> {
            exerciceBudgetaireDTO.setDeleted(true);
        });
        exerciceBudgetaireRepository.saveAll(exerciceBudgetaireDTOS.stream().map(exerciceBudgetaireMapper::toEntity).collect(Collectors.toList()));
        List<ExerciceBudgetaireDTO> exerciceBudgetaireDTOS1=exerciceBudgetaireRepository.findAll().stream().map(exerciceBudgetaireMapper::toDto).filter(exerciceBudgetaireDTO ->
            exerciceBudgetaireDTO.isDeleted()!=null && !exerciceBudgetaireDTO.isDeleted()).collect(Collectors.toList());
        return exerciceBudgetaireDTOS;
    }
}
