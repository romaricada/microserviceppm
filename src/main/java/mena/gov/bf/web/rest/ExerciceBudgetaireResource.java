package mena.gov.bf.web.rest;

import mena.gov.bf.service.ExerciceBudgetaireService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.ExerciceBudgetaireDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mena.gov.bf.domain.ExerciceBudgetaire}.
 */
@RestController
@RequestMapping("/api")
public class ExerciceBudgetaireResource {

    private final Logger log = LoggerFactory.getLogger(ExerciceBudgetaireResource.class);

    private static final String ENTITY_NAME = "microserviceppmExerciceBudgetaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ExerciceBudgetaireService exerciceBudgetaireService;

    public ExerciceBudgetaireResource(ExerciceBudgetaireService exerciceBudgetaireService) {
        this.exerciceBudgetaireService = exerciceBudgetaireService;
    }

    /**
     * {@code POST  /exercice-budgetaires} : Create a new exerciceBudgetaire.
     *
     * @param exerciceBudgetaireDTO the exerciceBudgetaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new exerciceBudgetaireDTO, or with status {@code 400 (Bad Request)} if the exerciceBudgetaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/exercice-budgetaires")
    public ResponseEntity<ExerciceBudgetaireDTO> createExerciceBudgetaire(@Valid @RequestBody ExerciceBudgetaireDTO exerciceBudgetaireDTO) throws URISyntaxException {
        log.debug("REST request to save ExerciceBudgetaire : {}", exerciceBudgetaireDTO);
        if (exerciceBudgetaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new exerciceBudgetaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ExerciceBudgetaireDTO result = exerciceBudgetaireService.save(exerciceBudgetaireDTO);
        return ResponseEntity.created(new URI("/api/exercice-budgetaires/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /exercice-budgetaires} : Updates an existing exerciceBudgetaire.
     *
     * @param exerciceBudgetaireDTO the exerciceBudgetaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated exerciceBudgetaireDTO,
     * or with status {@code 400 (Bad Request)} if the exerciceBudgetaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the exerciceBudgetaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/exercice-budgetaires")
    public ResponseEntity<ExerciceBudgetaireDTO> updateExerciceBudgetaire(@Valid @RequestBody ExerciceBudgetaireDTO exerciceBudgetaireDTO) throws URISyntaxException {
        log.debug("REST request to update ExerciceBudgetaire : {}", exerciceBudgetaireDTO);
        if (exerciceBudgetaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ExerciceBudgetaireDTO result = exerciceBudgetaireService.save(exerciceBudgetaireDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, exerciceBudgetaireDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /exercice-budgetaires} : get all the exerciceBudgetaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of exerciceBudgetaires in body.
     */
    @GetMapping("/exercice-budgetaires")
    public ResponseEntity<List<ExerciceBudgetaireDTO>> getAllExerciceBudgetaires(Pageable pageable) {
        log.debug("REST request to get a page of ExerciceBudgetaires");
        Page<ExerciceBudgetaireDTO> page = exerciceBudgetaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /exercice-budgetaires/:id} : get the "id" exerciceBudgetaire.
     *
     * @param id the id of the exerciceBudgetaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the exerciceBudgetaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/exercice-budgetaires/{id}")
    public ResponseEntity<ExerciceBudgetaireDTO> getExerciceBudgetaire(@PathVariable Long id) {
        log.debug("REST request to get ExerciceBudgetaire : {}", id);
        Optional<ExerciceBudgetaireDTO> exerciceBudgetaireDTO = exerciceBudgetaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(exerciceBudgetaireDTO);
    }

    /**
     * {@code DELETE  /exercice-budgetaires/:id} : delete the "id" exerciceBudgetaire.
     *
     * @param id the id of the exerciceBudgetaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/exercice-budgetaires/{id}")
    public ResponseEntity<Void> deleteExerciceBudgetaire(@PathVariable Long id) {
        log.debug("REST request to delete ExerciceBudgetaire : {}", id);
        exerciceBudgetaireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/exercice-budgetaires/no-page")
    ResponseEntity<List<ExerciceBudgetaireDTO>> findAllWithoutPagination(){
        return ResponseEntity.ok(  ).body( exerciceBudgetaireService.findAllWithoutPagination() );
    }

    @PutMapping("/exercice-budgetaires/activate")
    public ResponseEntity<ExerciceBudgetaireDTO> activateExercice(@Valid @RequestBody ExerciceBudgetaireDTO exerciceDTO) {
        ExerciceBudgetaireDTO result = exerciceBudgetaireService.activateExercice(exerciceDTO);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/exercice-budgetaires/elaborer-ppm")
    public ResponseEntity<ExerciceBudgetaireDTO> activateElaborerPPM(@Valid @RequestBody ExerciceBudgetaireDTO exerciceDTO) {
        ExerciceBudgetaireDTO result = exerciceBudgetaireService.activateElaborerPPM(exerciceDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/exercice-budgetaires/current-exercice")
    public ResponseEntity<ExerciceBudgetaireDTO> findCurrentExercice() {
        Optional<ExerciceBudgetaireDTO> exerciceBudgetaireDTO = exerciceBudgetaireService.findCurrentExercice();
        return ResponseUtil.wrapOrNotFound(exerciceBudgetaireDTO);
    }

    @GetMapping("/exercice-budgetaires/current-exercice-elaborer")
    public  ResponseEntity<ExerciceBudgetaireDTO> finfExerciceByElaborerPPMIstrue() {
        return ResponseEntity.ok(exerciceBudgetaireService.finfExerciceByElaborerPPMIstrue());
    }
    @PutMapping("/exercicebudgetaire/updateListe")
    public ResponseEntity<List<ExerciceBudgetaireDTO>> updateAall(@RequestBody List<ExerciceBudgetaireDTO> exerciceBudgetaireDTOS){
        return ResponseEntity.ok(exerciceBudgetaireService.updateAall(exerciceBudgetaireDTOS));
    }
}
