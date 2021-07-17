package mena.gov.bf.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import mena.gov.bf.service.JourFerierService;
import mena.gov.bf.service.dto.JourFerierDTO;
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
 * REST controller for managing {@link mena.gov.bf.domain.Acteur}.
 */
@RestController
@RequestMapping("/api")
public class JourFerierResource {

    private final Logger log = LoggerFactory.getLogger(JourFerierResource.class);

    private static final String ENTITY_NAME = "microserviceppmJourFerier";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JourFerierService jourFerierService;

    public JourFerierResource(JourFerierService jourFerierService) {
        this.jourFerierService = jourFerierService;
    }

    /**
     * {@code POST  /acteurs} : Create a new acteur.
     *
     * @param jourFerierDTO the acteurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new JourFerier, or with status {@code 400 (Bad Request)} if the acteur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jour-feriers")
    public ResponseEntity<JourFerierDTO> createJourFerier(@Valid @RequestBody JourFerierDTO jourFerierDTO) throws URISyntaxException {
        log.debug("REST request to save JourFerier : {}", jourFerierDTO);
        if (jourFerierDTO.getId() != null) {
            throw new BadRequestAlertException("A new acteur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JourFerierDTO result = jourFerierService.save(jourFerierDTO);
        return ResponseEntity.created(new URI("/api/jour-feriers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /acteurs} : Updates an existing acteur.
     *
     * @param jourFerierDTO the acteurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated acteurDTO,
     * or with status {@code 400 (Bad Request)} if the acteurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the acteurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jour-feriers")
    public ResponseEntity<JourFerierDTO> updateJourFerier(@Valid @RequestBody JourFerierDTO jourFerierDTO) throws URISyntaxException {
        log.debug("REST request to update JourFerier : {}", jourFerierDTO);
        if (jourFerierDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        JourFerierDTO result = jourFerierService.save(jourFerierDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jourFerierDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /acteurs} : get all the acteurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of acteurs in body.
     */
    @GetMapping("/jour-feriers")
    public ResponseEntity<List<JourFerierDTO>> getAllJourFerier(Pageable pageable) {
        log.debug("REST request to get a page of Jour férier");
        Page<JourFerierDTO> page = jourFerierService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /acteurs/:id} : get the "id" acteur.
     *
     * @param id the id of the acteurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the acteurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jour-feriers/{id}")
    public ResponseEntity<JourFerierDTO> getJourFerier(@PathVariable Long id) {
        log.debug("REST request to get JourFerier : {}", id);
        Optional<JourFerierDTO> acteurDTO = jourFerierService.findOne(id);
        return ResponseUtil.wrapOrNotFound(acteurDTO);
    }

    /**
     * {@code DELETE  /acteurs/:id} : delete the "id" acteur.
     *
     * @param id the id of the acteurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jour-feriers/{id}")
    public ResponseEntity<Void> deleteJourFerier(@PathVariable Long id) {
        log.debug("REST request to delete JourFerier : {}", id);
        jourFerierService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PutMapping("/jour-feriers/updateListe")
    public ResponseEntity<List<JourFerierDTO>> updateAall(@RequestBody List<JourFerierDTO> jourFerierDTOS){
        return ResponseEntity.ok(jourFerierService.updateAall(jourFerierDTOS));
    }
}
