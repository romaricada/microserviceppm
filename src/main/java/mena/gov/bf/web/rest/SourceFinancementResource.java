package mena.gov.bf.web.rest;

import mena.gov.bf.service.SourceFinancementService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.SourceFinancementDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.SourceFinancement}.
 */
@RestController
@RequestMapping("/api")
public class SourceFinancementResource {

    private final Logger log = LoggerFactory.getLogger(SourceFinancementResource.class);

    private static final String ENTITY_NAME = "microserviceppmSourceFinancement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SourceFinancementService sourceFinancementService;

    public SourceFinancementResource(SourceFinancementService sourceFinancementService) {
        this.sourceFinancementService = sourceFinancementService;
    }

    /**
     * {@code POST  /source-financements} : Create a new sourceFinancement.
     *
     * @param sourceFinancementDTO the sourceFinancementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sourceFinancementDTO, or with status {@code 400 (Bad Request)} if the sourceFinancement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/source-financements")
    public ResponseEntity<SourceFinancementDTO> createSourceFinancement(@Valid @RequestBody SourceFinancementDTO sourceFinancementDTO) throws URISyntaxException {
        log.debug("REST request to save SourceFinancement : {}", sourceFinancementDTO);
        if (sourceFinancementDTO.getId() != null) {
            throw new BadRequestAlertException("A new sourceFinancement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SourceFinancementDTO result = sourceFinancementService.save(sourceFinancementDTO);
        return ResponseEntity.created(new URI("/api/source-financements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /source-financements} : Updates an existing sourceFinancement.
     *
     * @param sourceFinancementDTO the sourceFinancementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sourceFinancementDTO,
     * or with status {@code 400 (Bad Request)} if the sourceFinancementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sourceFinancementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/source-financements")
    public ResponseEntity<SourceFinancementDTO> updateSourceFinancement(@Valid @RequestBody SourceFinancementDTO sourceFinancementDTO) throws URISyntaxException {
        log.debug("REST request to update SourceFinancement : {}", sourceFinancementDTO);
        if (sourceFinancementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SourceFinancementDTO result = sourceFinancementService.save(sourceFinancementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, sourceFinancementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /source-financements} : get all the sourceFinancements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sourceFinancements in body.
     */
    @GetMapping("/source-financements")
    public ResponseEntity<List<SourceFinancementDTO>> getAllSourceFinancements(Pageable pageable) {
        log.debug("REST request to get a page of SourceFinancements");
        Page<SourceFinancementDTO> page = sourceFinancementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /source-financements/:id} : get the "id" sourceFinancement.
     *
     * @param id the id of the sourceFinancementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sourceFinancementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/source-financements/{id}")
    public ResponseEntity<SourceFinancementDTO> getSourceFinancement(@PathVariable Long id) {
        log.debug("REST request to get SourceFinancement : {}", id);
        Optional<SourceFinancementDTO> sourceFinancementDTO = sourceFinancementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(sourceFinancementDTO);
    }

    @GetMapping("/source-financements/findAll-by")
    public ResponseEntity<List<SourceFinancementDTO>> getAllSourceFinancement() {
        return ResponseEntity.ok(sourceFinancementService.findAllListes());
    }

    /**
     * {@code DELETE  /source-financements/:id} : delete the "id" sourceFinancement.
     *
     * @param id the id of the sourceFinancementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/source-financements/{id}")
    public ResponseEntity<Void> deleteSourceFinancement(@PathVariable Long id) {
        log.debug("REST request to delete SourceFinancement : {}", id);
        sourceFinancementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @PutMapping("/source-financements/updateListe")
    public ResponseEntity<List<SourceFinancementDTO>> updateAall(@RequestBody List<SourceFinancementDTO> sourceFinancementDTOS){
        return ResponseEntity.ok(sourceFinancementService.updateAall(sourceFinancementDTOS));
    }
}
