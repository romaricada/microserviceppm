package mena.gov.bf.web.rest;

import mena.gov.bf.service.EtapeService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.EtapeDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Etape}.
 */
@RestController
@RequestMapping("/api")
public class EtapeResource {

    private final Logger log = LoggerFactory.getLogger(EtapeResource.class);

    private static final String ENTITY_NAME = "microserviceppmEtape";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EtapeService etapeService;

    public EtapeResource(EtapeService etapeService) {
        this.etapeService = etapeService;
    }

    /**
     * {@code POST  /etapes} : Create a new etape.
     *
     * @param etapeDTO the etapeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new etapeDTO, or with status {@code 400 (Bad Request)} if the etape has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/etapes")
    public ResponseEntity<EtapeDTO> createEtape(@Valid @RequestBody EtapeDTO etapeDTO) throws URISyntaxException {
        log.debug("REST request to save Etape : {}", etapeDTO);
        if (etapeDTO.getId() != null) {
            throw new BadRequestAlertException("A new etape cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EtapeDTO result = etapeService.save(etapeDTO);
        return ResponseEntity.created(new URI("/api/etapes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /etapes} : Updates an existing etape.
     *
     * @param etapeDTO the etapeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated etapeDTO,
     * or with status {@code 400 (Bad Request)} if the etapeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the etapeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */

    @PutMapping("/etapes")
    public ResponseEntity<EtapeDTO> updateEtape(@Valid @RequestBody EtapeDTO etapeDTO) throws URISyntaxException {
        log.debug("REST request to update Etape : {}", etapeDTO);
        if (etapeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EtapeDTO result = etapeService.save(etapeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, etapeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /etapes} : get all the etapes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of etapes in body.
     */
    @GetMapping("/etapes")
    public ResponseEntity<List<EtapeDTO>> getAllEtapes(Pageable pageable) {
        log.debug("REST request to get a page of Etapes");
        Page<EtapeDTO> page = etapeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    /**
     * {@code GET  /etapes/:id} : get the "id" etape.
     *
     * @param id the id of the etapeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the etapeDTO, or with status {@code 404 (Not Found)}.
     */

    @GetMapping("/etapes/{id}")
    public ResponseEntity<EtapeDTO> getEtape(@PathVariable Long id) {
        log.debug("REST request to get Etape : {}", id);
        Optional<EtapeDTO> etapeDTO = etapeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(etapeDTO);
    }

    /**
     * {@code DELETE  /etapes/:id} : delete the "id" etape.
     *
     * @param id the id of the etapeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */

    @DeleteMapping("/etapes/{id}")
    public ResponseEntity<Void> deleteEtape(@PathVariable Long id) {
        log.debug("REST request to delete Etape : {}", id);
        etapeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PutMapping("/etape/updateListe")
    public ResponseEntity<List<EtapeDTO>> updateAall(@RequestBody List<EtapeDTO> etapeDTOS){
        return ResponseEntity.ok(etapeService.updateAall(etapeDTOS));
    }

    @GetMapping("/etapes/find-mode")
    public ResponseEntity<List<EtapeDTO>> findEyapeByModePassation(@RequestParam Long modePassationId) {
        return ResponseEntity.ok(etapeService.findEyapeByModePassation(modePassationId));
    }
}
