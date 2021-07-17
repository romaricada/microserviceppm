package mena.gov.bf.web.rest;

import mena.gov.bf.service.ActeurService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.ActeurDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Acteur}.
 */
@RestController
@RequestMapping("/api")
public class ActeurResource {

    private final Logger log = LoggerFactory.getLogger(ActeurResource.class);

    private static final String ENTITY_NAME = "microserviceppmActeur";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ActeurService acteurService;

    public ActeurResource(ActeurService acteurService) {
        this.acteurService = acteurService;
    }

    /**
     * {@code POST  /acteurs} : Create a new acteur.
     *
     * @param acteurDTO the acteurDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new acteurDTO, or with status {@code 400 (Bad Request)} if the acteur has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/acteurs")
    public ResponseEntity<ActeurDTO> createActeur(@Valid @RequestBody ActeurDTO acteurDTO) throws URISyntaxException {
        log.debug("REST request to save Acteur ------------------     {}      ------------------", acteurDTO);
        if (acteurDTO.getId() != null) {
            throw new BadRequestAlertException("A new acteur cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ActeurDTO result = acteurService.save(acteurDTO);
        return ResponseEntity.created(new URI("/api/acteurs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /acteurs} : Updates an existing acteur.
     *
     * @param acteurDTO the acteurDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated acteurDTO,
     * or with status {@code 400 (Bad Request)} if the acteurDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the acteurDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/acteurs")
    public ResponseEntity<ActeurDTO> updateActeur(@Valid @RequestBody ActeurDTO acteurDTO) throws URISyntaxException {
        log.debug("REST request to update Acteur ==============      {}     ==============", acteurDTO);
        if (acteurDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ActeurDTO result = acteurService.save(acteurDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, acteurDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /acteurs} : get all the acteurs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of acteurs in body.
     */
    @GetMapping("/acteurs")
    public ResponseEntity<List<ActeurDTO>> getAllActeurs(Pageable pageable) {
        log.debug("REST request to get a page of Acteurs");
        Page<ActeurDTO> page = acteurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /acteurs/:id} : get the "id" acteur.
     *
     * @param id the id of the acteurDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the acteurDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/acteurs/{id}")
    public ResponseEntity<ActeurDTO> getActeur(@PathVariable Long id) {
        log.debug("REST request to get Acteur : {}", id);
        Optional<ActeurDTO> acteurDTO = acteurService.findOne(id);
        return ResponseUtil.wrapOrNotFound(acteurDTO);
    }

    /**
     * {@code DELETE  /acteurs/:id} : delete the "id" acteur.
     *
     * @param id the id of the acteurDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/acteurs/{id}")
    public ResponseEntity<Void> deleteActeur(@PathVariable Long id) {
        log.debug("REST request to delete Acteur : {}", id);
        acteurService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PutMapping("/acteur/updateListe")
    public ResponseEntity<List<ActeurDTO>> updateAall(@RequestBody List<ActeurDTO> acteurDTOS){
        return ResponseEntity.ok(acteurService.updateAall(acteurDTOS));
    }
}
