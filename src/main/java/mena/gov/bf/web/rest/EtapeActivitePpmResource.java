package mena.gov.bf.web.rest;

import mena.gov.bf.service.EtapeActivitePpmService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.EtapeActivitePpmDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.EtapeActivitePpm}.
 */
@RestController
@RequestMapping("/api")
public class EtapeActivitePpmResource {

    private final Logger log = LoggerFactory.getLogger(EtapeActivitePpmResource.class);

    private static final String ENTITY_NAME = "microserviceppmEtapeActivitePpm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtapeActivitePpmService etapeActivitePpmService;

    public EtapeActivitePpmResource(EtapeActivitePpmService etapeActivitePpmService) {
        this.etapeActivitePpmService = etapeActivitePpmService;
    }

    /**
     * {@code POST  /etape-activite-ppms} : Create a new etapeActivitePpm.
     *
     * @param etapeActivitePpmDTO the etapeActivitePpmDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and
     * with body the new etapeActivitePpmDTO, or with status
     * {@code 400 (Bad Request)} if the etapeActivitePpm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etape-activite-ppms")
    public ResponseEntity<EtapeActivitePpmDTO> createEtapeActivitePpm(@RequestBody EtapeActivitePpmDTO etapeActivitePpmDTO) throws URISyntaxException {
        log.debug("REST request to save EtapeActivitePpm : {}", etapeActivitePpmDTO);
        if (etapeActivitePpmDTO.getId() != null) {
            throw new BadRequestAlertException("A new etapeActivitePpm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtapeActivitePpmDTO result = etapeActivitePpmService.save(etapeActivitePpmDTO);
        return ResponseEntity.created(new URI("/api/etape-activite-ppms/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /etape-activite-ppms} : Updates an existing etapeActivitePpm.
     *
     * @param etapeActivitePpmDTO the etapeActivitePpmDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with
     * body the updated etapeActivitePpmDTO, or with status
     * {@code 400 (Bad Request)} if the etapeActivitePpmDTO is not valid, or
     * with status {@code 500 (Internal Server Error)} if the
     * etapeActivitePpmDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/etape-activite-ppms")
    public ResponseEntity<EtapeActivitePpmDTO> updateEtapeActivitePpm(@RequestBody EtapeActivitePpmDTO etapeActivitePpmDTO) throws URISyntaxException {
        log.debug("REST request to update EtapeActivitePpm : {}", etapeActivitePpmDTO);
        if (etapeActivitePpmDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtapeActivitePpmDTO result = etapeActivitePpmService.save(etapeActivitePpmDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etapeActivitePpmDTO.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /etape-activite-ppms} : get all the etapeActivitePpms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the
     * list of etapeActivitePpms in body.
     */
    @GetMapping("/etape-activite-ppms")
    public ResponseEntity<List<EtapeActivitePpmDTO>> getAllEtapeActivitePpms(Pageable pageable) {
        log.debug("REST request to get a page of EtapeActivitePpms");
        Page<EtapeActivitePpmDTO> page = etapeActivitePpmService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /etape-activite-ppms/:id} : get the "id" etapeActivitePpm.
     *
     * @param id the id of the etapeActivitePpmDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with
     * body the etapeActivitePpmDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/etape-activite-ppms/{id}")
    public ResponseEntity<EtapeActivitePpmDTO> getEtapeActivitePpm(@PathVariable Long id) {
        log.debug("REST request to get EtapeActivitePpm : {}", id);
        Optional<EtapeActivitePpmDTO> etapeActivitePpmDTO = etapeActivitePpmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etapeActivitePpmDTO);
    }

    /**
     * {@code DELETE  /etape-activite-ppms/:id} : delete the "id"
     * etapeActivitePpm.
     *
     * @param id the id of the etapeActivitePpmDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/etape-activite-ppms/{id}")
    public ResponseEntity<Void> deleteEtapeActivitePpm(@PathVariable Long id) {
        log.debug("REST request to delete EtapeActivitePpm : {}", id);
        etapeActivitePpmService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/etape-activite-ppms/find-all-by-activite")
    public ResponseEntity<List<EtapeActivitePpmDTO>> findAllWithOutPage(@RequestParam Long activiteId) {
        return ResponseEntity.ok(etapeActivitePpmService.findEtaActivitePPMbyActivite(activiteId));
    }
}
