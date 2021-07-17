package mena.gov.bf.web.rest;

import mena.gov.bf.service.NaturePrestationService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.NaturePrestationDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.NaturePrestation}.
 */
@RestController
@RequestMapping("/api")
public class NaturePrestationResource {

    private final Logger log = LoggerFactory.getLogger(NaturePrestationResource.class);

    private static final String ENTITY_NAME = "microserviceppmNaturePrestation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NaturePrestationService naturePrestationService;

    public NaturePrestationResource(NaturePrestationService naturePrestationService) {
        this.naturePrestationService = naturePrestationService;
    }

    /**
     * {@code POST  /nature-prestations} : Create a new naturePrestation.
     *
     * @param naturePrestationDTO the naturePrestationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new naturePrestationDTO, or with status
     *         {@code 400 (Bad Request)} if the naturePrestation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nature-prestations")
    public ResponseEntity<NaturePrestationDTO> createNaturePrestation(
            @Valid @RequestBody NaturePrestationDTO naturePrestationDTO) throws URISyntaxException {
        log.debug("REST request to save NaturePrestation : {}", naturePrestationDTO);
        if (naturePrestationDTO.getId() != null) {
            throw new BadRequestAlertException("A new naturePrestation cannot already have an ID", ENTITY_NAME,
                    "idexists");
        }
        NaturePrestationDTO result = naturePrestationService.save(naturePrestationDTO);
        return ResponseEntity
                .created(new URI("/api/nature-prestations/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /nature-prestations} : Updates an existing naturePrestation.
     *
     * @param naturePrestationDTO the naturePrestationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated naturePrestationDTO, or with status
     *         {@code 400 (Bad Request)} if the naturePrestationDTO is not valid, or
     *         with status {@code 500 (Internal Server Error)} if the
     *         naturePrestationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nature-prestations")
    public ResponseEntity<NaturePrestationDTO> updateNaturePrestation(
            @Valid @RequestBody NaturePrestationDTO naturePrestationDTO) throws URISyntaxException {
        log.debug("REST request to update NaturePrestation : {}", naturePrestationDTO);
        if (naturePrestationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NaturePrestationDTO result = naturePrestationService.save(naturePrestationDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME,
                naturePrestationDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /nature-prestations} : get all the naturePrestations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of naturePrestations in body.
     */
    @GetMapping("/nature-prestations")
    public ResponseEntity<List<NaturePrestationDTO>> getAllNaturePrestations(Pageable pageable) {
        log.debug("REST request to get a page of NaturePrestations");
        Page<NaturePrestationDTO> page = naturePrestationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nature-prestations/:id} : get the "id" naturePrestation.
     *
     * @param id the id of the naturePrestationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the naturePrestationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nature-prestations/{id}")
    public ResponseEntity<NaturePrestationDTO> getNaturePrestation(@PathVariable Long id) {
        log.debug("REST request to get NaturePrestation : {}", id);
        Optional<NaturePrestationDTO> naturePrestationDTO = naturePrestationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(naturePrestationDTO);
    }

    /**
     * {@code DELETE  /nature-prestations/:id} : delete the "id" naturePrestation.
     *
     * @param id the id of the naturePrestationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @PutMapping("/nature-prestations/delete")
    public ResponseEntity<Void> deleteNaturePrestation(@Valid @RequestBody NaturePrestationDTO naturePrestationDTO) {
        log.debug("REST request to delete NaturePrestation : {}", naturePrestationDTO);
        naturePrestationService.deleteNature(naturePrestationDTO);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, naturePrestationDTO.getId().toString()))
                .build();
    }

    @PutMapping("/nature-prestations/updateListe")
    public ResponseEntity<List<NaturePrestationDTO>> updateAall(
            @RequestBody List<NaturePrestationDTO> naturePrestationDTOS) {
        return ResponseEntity.ok(naturePrestationService.updateAall(naturePrestationDTOS));
    }

    @GetMapping("/nature-prestations/find-all")
    public ResponseEntity<List<NaturePrestationDTO>> findAllNaturePrestation() {
        return ResponseEntity.ok().body(naturePrestationService.findAllNaturePrestation());
    }

    @GetMapping("/nature-prestations/find-all-list")
    public ResponseEntity<List<NaturePrestationDTO>> findAllNaturePrestationList() {
        return ResponseEntity.ok().body(naturePrestationService.findAllNaturePrestationList());
    }
}
