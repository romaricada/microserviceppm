package mena.gov.bf.web.rest;

import mena.gov.bf.service.BesoinLigneBudgetaireService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.BesoinLigneBudgetaireDTO;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing
 * {@link mena.gov.bf.domain.BesoinLigneBudgetaire}.
 */
@RestController
@RequestMapping("/api")
public class BesoinLigneBudgetaireResource {

    private final Logger log = LoggerFactory.getLogger(BesoinLigneBudgetaireResource.class);

    private static final String ENTITY_NAME = "microserviceppmBesoinLigneBudgetaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BesoinLigneBudgetaireService besoinLigneBudgetaireService;

    public BesoinLigneBudgetaireResource(BesoinLigneBudgetaireService besoinLigneBudgetaireService) {
        this.besoinLigneBudgetaireService = besoinLigneBudgetaireService;
    }

    /**
     * {@code POST  /besoin-ligne-budgetaires} : Create a new
     * besoinLigneBudgetaire.
     *
     * @param besoinLigneBudgetaireDTO the besoinLigneBudgetaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and
     * with body the new besoinLigneBudgetaireDTO, or with status
     * {@code 400 (Bad Request)} if the besoinLigneBudgetaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/besoin-ligne-budgetaires")
    public ResponseEntity<BesoinLigneBudgetaireDTO> createBesoinLigneBudgetaire(@RequestBody BesoinLigneBudgetaireDTO besoinLigneBudgetaireDTO) throws URISyntaxException {
        log.debug("REST request to save BesoinLigneBudgetaire : {}", besoinLigneBudgetaireDTO);
        if (besoinLigneBudgetaireDTO.getId() != null) {
            throw new BadRequestAlertException("A new besoinLigneBudgetaire cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BesoinLigneBudgetaireDTO result = besoinLigneBudgetaireService.save(besoinLigneBudgetaireDTO);
        return ResponseEntity.created(new URI("/api/besoin-ligne-budgetaires/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /besoin-ligne-budgetaires} : Updates an existing
     * besoinLigneBudgetaire.
     *
     * @param besoinLigneBudgetaireDTO the besoinLigneBudgetaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with
     * body the updated besoinLigneBudgetaireDTO, or with status
     * {@code 400 (Bad Request)} if the besoinLigneBudgetaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the
     * besoinLigneBudgetaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/besoin-ligne-budgetaires")
    public ResponseEntity<BesoinLigneBudgetaireDTO> updateBesoinLigneBudgetaire(@RequestBody BesoinLigneBudgetaireDTO besoinLigneBudgetaireDTO) throws URISyntaxException {
        log.debug("REST request to update BesoinLigneBudgetaire : {}", besoinLigneBudgetaireDTO);
        if (besoinLigneBudgetaireDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BesoinLigneBudgetaireDTO result = besoinLigneBudgetaireService.save(besoinLigneBudgetaireDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, besoinLigneBudgetaireDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /besoin-ligne-budgetaires} : get all the
     * besoinLigneBudgetaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the
     * list of besoinLigneBudgetaires in body.
     */
    @GetMapping("/besoin-ligne-budgetaires")
    public ResponseEntity<List<BesoinLigneBudgetaireDTO>> getAllBesoinLigneBudgetaires(Pageable pageable) {
        log.debug("REST request to get a page of BesoinLigneBudgetaires");
        Page<BesoinLigneBudgetaireDTO> page = besoinLigneBudgetaireService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /besoin-ligne-budgetaires/:id} : get the "id"
     * besoinLigneBudgetaire.
     *
     * @param id the id of the besoinLigneBudgetaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with
     * body the besoinLigneBudgetaireDTO, or with status
     * {@code 404 (Not Found)}.
     */
    @GetMapping("/besoin-ligne-budgetaires/{id}")
    public ResponseEntity<BesoinLigneBudgetaireDTO> getBesoinLigneBudgetaire(@PathVariable Long id) {
        log.debug("REST request to get BesoinLigneBudgetaire : {}", id);
        Optional<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTO = besoinLigneBudgetaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(besoinLigneBudgetaireDTO);
    }

    /**
     * {@code DELETE  /besoin-ligne-budgetaires/:id} : delete the "id"
     * besoinLigneBudgetaire.
     *
     * @param id the id of the besoinLigneBudgetaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/besoin-ligne-budgetaires/{id}")
    public ResponseEntity<Void> deleteBesoinLigneBudgetaire(@PathVariable Long id) {
        log.debug("REST request to delete BesoinLigneBudgetaire : {}", id);
        besoinLigneBudgetaireService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/besoin-ligne-budgetaires/all-by-direction-and-exercice")
    ResponseEntity<List<BesoinLigneBudgetaireDTO>> findAllBesoinLigneBudgetaire(Pageable pageable, @RequestParam(name = "exerciceId") Long exerciceId, @RequestParam(name = "directionId") Long directionId) {
        Page<BesoinLigneBudgetaireDTO> page = besoinLigneBudgetaireService.findAllBesoinLigneBudgetaire(pageable, exerciceId, directionId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/besoin-ligne-budgetaires/all-besoinLigneBudgetaire-and-activite")
    public ResponseEntity<List<BesoinLigneBudgetaireDTO>> findAllBesoinLigneBudgetaireByActivite(@RequestParam(name = "activiteId") Long activiteId) {
        return ResponseEntity.ok().body(besoinLigneBudgetaireService.findBesoinLigneBudgetaireByActivite(activiteId));

    }

    @GetMapping("/besoin-ligne-budgetaires/all-besoinLigneBudgetaire-by-ligne-budgetaire")
    public ResponseEntity<List<BesoinLigneBudgetaireDTO>> findAllByLigneBudgetaire(@RequestParam(name = "ligneBudgetaireId") Long ligneBudgetaireId) {
        return ResponseEntity.ok().body(besoinLigneBudgetaireService.findAllByLigneBudgetaire(ligneBudgetaireId));

    }

    @GetMapping("/besoin-ligne-budgetaires/all-besoin-by-activite-and-ligne")
    public ResponseEntity<List<BesoinLigneBudgetaireDTO>> findAllBesoinByActiviteAndLigne(@RequestParam(name = "activiteId") Long activiteId, @RequestParam(name = "ligneId") Long ligneId) {
        return ResponseEntity.ok().body(besoinLigneBudgetaireService.findBesoinByActiviteAndLigne(activiteId, ligneId));

    }

    @PutMapping("/besoin-ligne-budgetaires/all-besoin-ligne-budgetaire-by-contrat")
        public ResponseEntity<List<BesoinLigneBudgetaireDTO>> findBesoinLigneBudgetaireByContratId(@RequestParam(name = "blblId") List<Long> blbId){
        return ResponseEntity.ok().body(besoinLigneBudgetaireService.findAllBesoinLigneBudgetaireByContrat(blbId));
    }

}
