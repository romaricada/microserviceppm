package mena.gov.bf.web.rest;

import mena.gov.bf.service.LigneBudgetaireService;
import mena.gov.bf.service.dto.BesoinLigneBudgetaireDTO;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.LigneBudgetaireDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mena.gov.bf.domain.LigneBudgetaire}.
 */
@RestController
@RequestMapping("/api")
public class LigneBudgetaireResource {

    private final Logger log = LoggerFactory.getLogger(LigneBudgetaireResource.class);

    private static final String ENTITY_NAME = "microserviceppmLigneBudgetaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LigneBudgetaireService ligneBudgetaireService;

    public LigneBudgetaireResource(LigneBudgetaireService ligneBudgetaireService) {
        this.ligneBudgetaireService = ligneBudgetaireService;
    }

    /**
     * {@code POST  /ligne-budgetaires} : Create a new ligneBudgetaire.
     *
     * @param ligneBudgetaireDTO the ligneBudgetaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new ligneBudgetaireDTO, or with status
     *         {@code 400 (Bad Request)} if the ligneBudgetaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ligne-budgetaires")
    public ResponseEntity<LigneBudgetaireDTO> createLigneBudgetaire(
            @Valid @RequestBody LigneBudgetaireDTO ligneBudgetaireDTO) throws URISyntaxException {
        log.debug("REST request to save LigneBudgetaire : {}", ligneBudgetaireDTO);
        if (ligneBudgetaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new ligneBudgetaire cannot already have an ID", ENTITY_NAME,
                    "idexists");
        }
        LigneBudgetaireDTO result = ligneBudgetaireService.save(ligneBudgetaireDTO);
        return ResponseEntity
                .created(new URI("/api/ligne-budgetaires/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /ligne-budgetaires} : Updates an existing ligneBudgetaire.
     *
     * @param ligneBudgetaireDTO the ligneBudgetaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated ligneBudgetaireDTO, or with status
     *         {@code 400 (Bad Request)} if the ligneBudgetaireDTO is not valid, or
     *         with status {@code 500 (Internal Server Error)} if the
     *         ligneBudgetaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ligne-budgetaires")
    public ResponseEntity<LigneBudgetaireDTO> updateLigneBudgetaire(
            @Valid @RequestBody LigneBudgetaireDTO ligneBudgetaireDTO) throws URISyntaxException {
        log.debug("REST request to update LigneBudgetaire : {}", ligneBudgetaireDTO);
        if (ligneBudgetaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LigneBudgetaireDTO result = ligneBudgetaireService.save(ligneBudgetaireDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                ligneBudgetaireDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /ligne-budgetaires} : get all the ligneBudgetaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of ligneBudgetaires in body.
     */
    @GetMapping("/ligne-budgetaires")
    public ResponseEntity<List<LigneBudgetaireDTO>> getAllLigneBudgetaires(Pageable pageable) {
        log.debug("REST request to get a page of LigneBudgetaires");
        Page<LigneBudgetaireDTO> page = ligneBudgetaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ligne-budgetaires/:id} : get the "id" ligneBudgetaire.
     *
     * @param id the id of the ligneBudgetaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the ligneBudgetaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ligne-budgetaires/{id}")
    public ResponseEntity<LigneBudgetaireDTO> getLigneBudgetaire(@PathVariable Long id) {
        log.debug("REST request to get LigneBudgetaire : {}", id);
        Optional<LigneBudgetaireDTO> ligneBudgetaireDTO = ligneBudgetaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ligneBudgetaireDTO);
    }

    /**
     * {@code DELETE  /ligne-budgetaires/:id} : delete the "id" ligneBudgetaire.
     *
     * @param id the id of the ligneBudgetaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ligne-budgetaires/{id}")
    public ResponseEntity<Void> deleteLigneBudgetaire(@PathVariable Long id) {
        log.debug("REST request to delete LigneBudgetaire : {}", id);
        ligneBudgetaireService.delete(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                .build();
    }

    @PutMapping("/ligne-budgetaires/update-all")
    public ResponseEntity<List<LigneBudgetaireDTO>> updateAll(
            @Valid @RequestBody List<LigneBudgetaireDTO> ligneBudgetaireDTOs) throws URISyntaxException {
        if (ligneBudgetaireDTOs.isEmpty()) {
            throw new BadRequestAlertException("requette de suppression invalide", ENTITY_NAME, "idnull");
        }
        return new ResponseEntity<>(ligneBudgetaireService.updateAll(ligneBudgetaireDTOs), HttpStatus.OK);
    }

    @GetMapping("/ligne-budgetaires/all-by-annee")
    public ResponseEntity<List<LigneBudgetaireDTO>> findAllByExercice(@RequestParam(name = "idAnnee") Long idAnnee) {
        return ResponseEntity.ok().body(ligneBudgetaireService.findAllByAnnee(idAnnee));
    }

    @GetMapping("/ligne-budgetaires/besoins")
    public ResponseEntity<List<BesoinLigneBudgetaireDTO>> findbesosoinLigne(@RequestParam Long idligne) {
        return ResponseEntity.ok().body(ligneBudgetaireService.findbesosoinLigne(idligne));
    }

    @GetMapping("/ligne-budgetaires/find-all")
    public ResponseEntity<List<LigneBudgetaireDTO>> findAllLigneBudgetaire() {
        return ResponseEntity.ok().body(ligneBudgetaireService.findAllLigneBudgetaire());
    }

    @PutMapping("/ligne-budgetaires/determine-montant")
    public ResponseEntity<Double> determineMontant(List<LigneBudgetaireDTO> ligneBudgetaires) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/ligne-budgetaires/find-allLigne-liste")
    public ResponseEntity<List<LigneBudgetaireDTO>> findAllListe() {
        return ResponseEntity.ok().body(ligneBudgetaireService.findAllListe());
    }

    @GetMapping("/ligne-budgetaires/find-all-programme")
    public ResponseEntity<List<LigneBudgetaireDTO>> findAllProgramme() {
        return ResponseEntity.ok().body(ligneBudgetaireService.findAllProgramme());
    }

}
