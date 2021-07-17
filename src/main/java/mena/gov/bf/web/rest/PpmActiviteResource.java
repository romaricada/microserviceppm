package mena.gov.bf.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import mena.gov.bf.service.PpmActiviteService;
import mena.gov.bf.service.dto.PpmActiviteDTO;
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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mena.gov.bf.domain.PpmActivite}.
 */
@RestController
@RequestMapping("/api")
public class PpmActiviteResource {

    private final Logger log = LoggerFactory.getLogger(PpmActiviteResource.class);

    private static final String ENTITY_NAME = "microserviceppmPpmActivite";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PpmActiviteService ppmActiviteService;

    public PpmActiviteResource(PpmActiviteService ppmActiviteService) {
        this.ppmActiviteService = ppmActiviteService;
    }

    /**
     * {@code POST  /ppm-activites} : Create a new ppmActivite.
     *
     * @param ppmActiviteDTO the ppmActiviteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ppmActiviteDTO, or with status {@code 400 (Bad Request)} if the ppmActivite has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ppm-activites")
    public ResponseEntity<PpmActiviteDTO> createPpmActivite(@Valid @RequestBody PpmActiviteDTO ppmActiviteDTO) throws URISyntaxException {
        log.debug("REST request to save PpmActivite : {}", ppmActiviteDTO);
        if (ppmActiviteDTO.getId() != null) {
            throw new BadRequestAlertException("A new ppmActivite cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PpmActiviteDTO result = ppmActiviteService.save(ppmActiviteDTO);
        return ResponseEntity.created(new URI("/api/ppm-activites/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ppm-activites} : Updates an existing ppmActivite.
     *
     * @param ppmActiviteDTO the ppmActiviteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ppmActiviteDTO,
     * or with status {@code 400 (Bad Request)} if the ppmActiviteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ppmActiviteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ppm-activites")
    public ResponseEntity<PpmActiviteDTO> updatePpmActivite(@Valid @RequestBody PpmActiviteDTO ppmActiviteDTO) throws URISyntaxException {
        log.debug("REST request to update PpmActivite : {}", ppmActiviteDTO);
        if (ppmActiviteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        PpmActiviteDTO result = ppmActiviteService.save(ppmActiviteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, ppmActiviteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ppm-activites} : get all the ppmActivites.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ppmActivites in body.
     */
    @GetMapping("/ppm-activites")
    public ResponseEntity<List<PpmActiviteDTO>> getAllPpmActivites(Pageable pageable) {
        log.debug("REST request to get a page of PpmActivites");
        Page<PpmActiviteDTO> page = ppmActiviteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ppm-activites/:id} : get the "id" ppmActivite.
     *
     * @param id the id of the ppmActiviteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ppmActiviteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ppm-activites/{id}")
    public ResponseEntity<PpmActiviteDTO> getPpmActivite(@PathVariable Long id) {
        log.debug("REST request to get PpmActivite : {}", id);
        Optional<PpmActiviteDTO> ppmActiviteDTO = ppmActiviteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ppmActiviteDTO);
    }

    /**
     * {@code DELETE  /ppm-activites/:id} : delete the "id" ppmActivite.
     *
     * @param id the id of the ppmActiviteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ppm-activites/{id}")
    public ResponseEntity<Void> deletePpmActivite(@PathVariable Long id) {
        log.debug("REST request to delete PpmActivite : {}", id);
        ppmActiviteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PutMapping("/ppm-activites/retiter-ppmActivite")
    public ResponseEntity<PpmActiviteDTO> deletePpmActiviteReporter(@RequestBody PpmActiviteDTO ppmActivite) {
        log.debug("REST request to delete PpmActivite : {}", ppmActivite);
        return ResponseEntity.ok(ppmActiviteService.retiterUneActiviteReporter(ppmActivite));
    }

    @PostMapping("/ppm-activites/save-report")
    public ResponseEntity<PpmActiviteDTO> saveReport(@RequestBody PpmActiviteDTO ppmActivite){
        return ResponseEntity.ok(ppmActiviteService.saveReport(ppmActivite));
    }
}
