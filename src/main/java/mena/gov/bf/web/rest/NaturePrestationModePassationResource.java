package mena.gov.bf.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import mena.gov.bf.service.NaturePrestationModePassationService;
import mena.gov.bf.service.dto.ModePassationDTO;
import mena.gov.bf.service.dto.NaturePrestationModePassationDTO;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mena.gov.bf.domain.BesoinLigneBudgetaire}.
 */
@RestController
@RequestMapping("/api")
public class NaturePrestationModePassationResource {

    private final Logger log = LoggerFactory.getLogger(NaturePrestationModePassationResource.class);

    private static final String ENTITY_NAME = "microserviceppmBesoinLigneBudgetaire";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NaturePrestationModePassationService naturePrestationModePassationService;

    public NaturePrestationModePassationResource(NaturePrestationModePassationService naturePrestationModePassationService) {
        this.naturePrestationModePassationService = naturePrestationModePassationService;
    }

    /**
     * {@code POST  /besoin-ligne-budgetaires} : Create a new besoinLigneBudgetaire.
     *
     * @param naturePrestationModePassationDTO the besoinLigneBudgetaireDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new besoinLigneBudgetaireDTO, or with status {@code 400 (Bad Request)} if the besoinLigneBudgetaire has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nature-prestation-mode-passations")
    public ResponseEntity<NaturePrestationModePassationDTO> createNaturePrestationModePassation(@RequestBody NaturePrestationModePassationDTO naturePrestationModePassationDTO) throws URISyntaxException {
        log.debug("REST request to save naturePrestationModePassationDTO : {}", naturePrestationModePassationDTO);
        if (naturePrestationModePassationDTO.getId() != null) {
            throw new BadRequestAlertException("A new naturePrestationModePassation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NaturePrestationModePassationDTO result = naturePrestationModePassationService.save(naturePrestationModePassationDTO);
        return ResponseEntity.created(new URI("/api/nature-prestation-mode-passations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /besoin-ligne-budgetaires} : Updates an existing besoinLigneBudgetaire.
     *
     * @param naturePrestationModePassationDTO the besoinLigneBudgetaireDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated besoinLigneBudgetaireDTO,
     * or with status {@code 400 (Bad Request)} if the besoinLigneBudgetaireDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the besoinLigneBudgetaireDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nature-prestation-mode-passations")
    public ResponseEntity<NaturePrestationModePassationDTO> updateNaturePrestationModePassation(@RequestBody NaturePrestationModePassationDTO naturePrestationModePassationDTO) throws URISyntaxException {
        log.debug("REST request to update naturePrestationModePassation : {}", naturePrestationModePassationDTO);
        if (naturePrestationModePassationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NaturePrestationModePassationDTO result = naturePrestationModePassationService.save(naturePrestationModePassationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, naturePrestationModePassationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /besoin-ligne-budgetaires} : get all the besoinLigneBudgetaires.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of besoinLigneBudgetaires in body.
     */
    @GetMapping("/nature-prestation-mode-passations")
    public ResponseEntity<List<NaturePrestationModePassationDTO>> getAllNaturePrestationModePassations(Pageable pageable) {
        log.debug("REST request to get a page of naturePrestationModePassations");
        Page<NaturePrestationModePassationDTO> page = naturePrestationModePassationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /besoin-ligne-budgetaires/:id} : get the "id" besoinLigneBudgetaire.
     *
     * @param id the id of the besoinLigneBudgetaireDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the besoinLigneBudgetaireDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nature-prestation-mode-passations/{id}")
    public ResponseEntity<NaturePrestationModePassationDTO> getNaturePrestationModePassation(@PathVariable Long id) {
        log.debug("REST request to get NaturePrestationModePassationDTO : {}", id);
        Optional<NaturePrestationModePassationDTO> besoinLigneBudgetaireDTO = naturePrestationModePassationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(besoinLigneBudgetaireDTO);
    }

    /**
     * {@code DELETE  /besoin-ligne-budgetaires/:id} : delete the "id" besoinLigneBudgetaire.
     *
     * @param id the id of the besoinLigneBudgetaireDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nature-prestation-mode-passations/{id}")
    public ResponseEntity<Void> deleteNaturePrestationModePassation(@PathVariable Long id) {
        log.debug("REST request to delete NaturePrestationModePassationDTO : {}", id);
        naturePrestationModePassationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/nature-prestation-mode-passations/get-mode-passation-by-montan-and-nature")
    public ResponseEntity<ModePassationDTO> getAllNaturePrestationModePassations(@RequestParam Long naturePrestationId, @RequestParam Double montant) {
        return ResponseEntity.ok(naturePrestationModePassationService.findModePassationByNaturePrestationAndMontant(naturePrestationId, montant));
    }

    @GetMapping("/nature-prestation-mode-passations/verif")
    public ResponseEntity<Boolean> verifyNaturePrestationModePassation(@RequestParam Long naturePrestationId, @RequestParam Long modePassationId) {
        log.debug("REST request to verfy NaturePrestationModePassation : {}", naturePrestationId, modePassationId);
        return ResponseEntity.ok().body(naturePrestationModePassationService.naturePrestationModePassationIsExist(naturePrestationId, modePassationId));
    }

    @GetMapping("/nature-prestation-mode-passations/interval")
    public ResponseEntity<Boolean> chevauchementNaturePrestationModePassation(@RequestParam Double montantMin, @RequestParam Double montantMax) {
        return ResponseEntity.ok().body(naturePrestationModePassationService.naturePrestationModePassationIsOverlap(montantMin, montantMax));
    }

    @PutMapping("/nature-prestation-mode-passations/updateListe")
    public ResponseEntity<List<NaturePrestationModePassationDTO>> updateAll(@RequestBody List<NaturePrestationModePassationDTO> naturePrestationModePassationDTOS){
        return ResponseEntity.ok(naturePrestationModePassationService.updateAll(naturePrestationModePassationDTOS));
    }


}
