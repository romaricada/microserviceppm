package mena.gov.bf.service;

import mena.gov.bf.domain.ExerciceBudgetaire;
import mena.gov.bf.domain.PPM;
import mena.gov.bf.repository.ExerciceBudgetaireRepository;
import mena.gov.bf.repository.PPMRepository;
import mena.gov.bf.repository.PpmActiviteRepository;
import mena.gov.bf.service.dto.PPMDTO;
import mena.gov.bf.service.dto.PpmActiviteDTO;
import mena.gov.bf.service.mapper.PPMMapper;
import mena.gov.bf.service.mapper.PpmActiviteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PPM}.
 */
@Service
@Transactional
public class PPMService {

    private final Logger log = LoggerFactory.getLogger(PPMService.class);

    private final PPMRepository pPMRepository;

    private final PPMMapper pPMMapper;

    private final PpmActiviteRepository ppmActiviteRepository;

    private final PpmActiviteMapper ppmActiviteMapper;

    @Autowired
    ExerciceBudgetaireRepository exerciceBudgetaireRepository;

    public PPMService(PPMRepository pPMRepository, PPMMapper pPMMapper, PpmActiviteRepository ppmActiviteRepository, PpmActiviteMapper ppmActiviteMapper, ExerciceBudgetaireRepository exerciceBudgetaireRepository) {
        this.pPMRepository = pPMRepository;
        this.pPMMapper = pPMMapper;
        this.ppmActiviteRepository = ppmActiviteRepository;
        this.ppmActiviteMapper = ppmActiviteMapper;
        this.exerciceBudgetaireRepository = exerciceBudgetaireRepository;
    }

    /**
     * Save a pPM.
     *
     * @param pPMDTO the entity to save.
     * @return the persisted entity.
     */
    public PPMDTO save(PPMDTO pPMDTO) {
        log.debug("Request to save PPM : {}", pPMDTO);

        ExerciceBudgetaire exerciceBudgetaire = exerciceBudgetaireRepository.findTop1ByOrderByAnneeDesc();

        Optional<PPM> ppmList = pPMRepository.findTop1ByIdExercice(exerciceBudgetaire.getId());
        if (ppmList.isPresent()) {
            PPM pPM = pPMMapper.toEntity(pPMDTO);
            pPM = pPMRepository.save(pPM);
            return pPMMapper.toDto(pPM);
        } else {
            return null;
        }

    }

    /**
     * Get all the pPMS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PPMDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PPMS");
        return pPMRepository.findAll(pageable)
            .map(pPMMapper::toDto);
    }

    /**
     * Get one pPM by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PPMDTO> findOne(Long id) {
        log.debug("Request to get PPM : {}", id);
        return pPMRepository.findById(id)
            .map(pPMMapper::toDto);
    }

    /**
     * Delete the pPM by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PPM : {}", id);
        pPMRepository.deleteById(id);
    }

    public List<PPMDTO> findAllPpm() {
        return pPMRepository.findAll().stream().map( pPMMapper::toDto ).collect( Collectors.toList());
    }

    public PPMDTO findPpmByExercice(Long exerciceId) {

        Optional<PPM> ppm = pPMRepository.findTop1ByIdExercice(exerciceId);

        return ppm.map(pPMMapper::toDto).orElse(null);
    }

    public PPMDTO generayeCode() {
        ExerciceBudgetaire exerciceBudgetaire = exerciceBudgetaireRepository.findTop1ByElaborerIsTrue();

        PPMDTO ppmdto = new PPMDTO();

        if (exerciceBudgetaire != null) {

            Optional<PPM> ppm = pPMRepository.findTop1ByIdExercice(exerciceBudgetaire.getId());
            if (ppm.isPresent()) {

                log.debug("-------------        {}      --------------", ppm.get());

                // String[] code = ppm.get().getReferencePlan().split("/");

                ppmdto = pPMMapper.toDto(ppm.get());
            } else {
                ppmdto.setReferencePlan(exerciceBudgetaire.getAnnee() + ".23/1");
            }
        }
        return ppmdto;
    }

    public String deleteAll(List<PPMDTO> ppmdtos) {

        AtomicReference<String> delete =  null;

        List<PpmActiviteDTO> ppmActiviteDTOs = ppmActiviteRepository.findAll().stream().map(ppmActiviteMapper::toDto).collect(Collectors.toList());

        ppmdtos.forEach(ppmActiviteDTO -> {
            if (ppmdtos.contains(ppmActiviteDTO)){
                delete.set("NON");
            }
        });

        if (delete.get().equals("OK")) {
            ppmdtos.forEach(activiteDTO -> activiteDTO.setDeleted(true));
            pPMRepository.saveAll(pPMMapper.toEntity(ppmdtos));
        }

        return delete.get();

    }

}
