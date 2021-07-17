package mena.gov.bf.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import mena.gov.bf.service.PPMService;
import mena.gov.bf.service.dto.PPMDTO;
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
 * REST controller for managing {@link mena.gov.bf.domain.PPM}.
 */
@RestController
@RequestMapping("/api")
public class PPMResource {

    private final Logger log = LoggerFactory.getLogger(PPMResource.class);

    private static final String ENTITY_NAME = "microserviceppmPpm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PPMService pPMService;

    public PPMResource(PPMService pPMService) {
        this.pPMService = pPMService;
    }

    /**
     * {@code POST  /ppms} : Create a new pPM.
     *
     * @param pPMDTO the pPMDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pPMDTO, or with status {@code 400 (Bad Request)} if the pPM has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ppms")
    public ResponseEntity<PPMDTO> createPPM(@Valid @RequestBody PPMDTO pPMDTO) throws URISyntaxException {
        log.debug("REST request to save PPM : {}", pPMDTO);
        if (pPMDTO.getId() != null) {
            throw new BadRequestAlertException("A new pPM cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PPMDTO result = pPMService.save(pPMDTO);
        return ResponseEntity.created(new URI("/api/ppms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ppms} : Updates an existing pPM.
     *
     * @param pPMDTO the pPMDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pPMDTO,
     * or with status {@code 400 (Bad Request)} if the pPMDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pPMDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ppms")
    public ResponseEntity<PPMDTO> updatePPM(@Valid @RequestBody PPMDTO pPMDTO) throws URISyntaxException {
        log.debug("REST request to update PPM : {}", pPMDTO);
        if (pPMDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        PPMDTO result = pPMService.save(pPMDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, pPMDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ppms} : get all the pPMS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pPMS in body.
     */
    @GetMapping("/ppms")
    public ResponseEntity<List<PPMDTO>> getAllPPMS(Pageable pageable) {
        log.debug("REST request to get a page of PPMS");
        Page<PPMDTO> page = pPMService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /ppms/:id} : get the "id" pPM.
     *
     * @param id the id of the pPMDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pPMDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ppms/{id}")
    public ResponseEntity<PPMDTO> getPPM(@PathVariable Long id) {
        log.debug("REST request to get PPM : {}", id);
        Optional<PPMDTO> pPMDTO = pPMService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pPMDTO);
    }

    /**
     * {@code DELETE  /ppms/:id} : delete the "id" pPM.
     *
     * @param id the id of the pPMDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ppms/{id}")
    public ResponseEntity<Void> deletePPM(@PathVariable Long id) {
        log.debug("REST request to delete PPM : {}", id);
        pPMService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/ppms/all")
    public ResponseEntity<List<PPMDTO>> findAllListe() {
        return ResponseEntity.ok(  ).body( pPMService.findAllPpm() );
    }

    @GetMapping("/ppms/by-exercice")
    public ResponseEntity<PPMDTO> findPpmByExercice(@RequestParam Long exerciceId) {

        return ResponseEntity.ok( pPMService.findPpmByExercice(exerciceId) );
    }

    @GetMapping("ppms/generate-code-ppm")
    public ResponseEntity<PPMDTO> generateCodeActivite(){
        return ResponseEntity.ok(pPMService.generayeCode());
    }

    @PutMapping("ppms/delete-all")
    public ResponseEntity<String> deleteteAll(List<PPMDTO> ppmdtos) {
        return ResponseEntity.ok(pPMService.deleteAll(ppmdtos));
    }
}

