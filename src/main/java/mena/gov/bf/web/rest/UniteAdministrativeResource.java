package mena.gov.bf.web.rest;

import mena.gov.bf.service.UniteAdministrativeService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.UniteAdministrativeDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link mena.gov.bf.domain.UniteAdministrative}.
 */
@RestController
@RequestMapping("/api")
public class UniteAdministrativeResource {

    private final Logger log = LoggerFactory.getLogger(UniteAdministrativeResource.class);

    private static final String ENTITY_NAME = "microserviceppmUniteAdministrative";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UniteAdministrativeService uniteAdministrativeService;

    public UniteAdministrativeResource(UniteAdministrativeService uniteAdministrativeService) {
        this.uniteAdministrativeService = uniteAdministrativeService;
    }

    /**
     * {@code POST  /unite-administratives} : Create a new uniteAdministrative.
     *
     * @param uniteAdministrativeDTO the uniteAdministrativeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new uniteAdministrativeDTO, or with status {@code 400 (Bad Request)} if the uniteAdministrative has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unite-administratives")
    public ResponseEntity<UniteAdministrativeDTO> createUniteAdministrative(@Valid @RequestBody UniteAdministrativeDTO uniteAdministrativeDTO) throws URISyntaxException {
        log.debug("REST request to save UniteAdministrative : {}", uniteAdministrativeDTO);
        if (uniteAdministrativeDTO.getId() != null) {
            throw new BadRequestAlertException("A new uniteAdministrative cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UniteAdministrativeDTO result = uniteAdministrativeService.save(uniteAdministrativeDTO);
        return ResponseEntity.created(new URI("/api/unite-administratives/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /unite-administratives} : Updates an existing uniteAdministrative.
     *
     * @param uniteAdministrativeDTO the uniteAdministrativeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated uniteAdministrativeDTO,
     * or with status {@code 400 (Bad Request)} if the uniteAdministrativeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the uniteAdministrativeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unite-administratives")
    public ResponseEntity<UniteAdministrativeDTO> updateUniteAdministrative(@Valid @RequestBody UniteAdministrativeDTO uniteAdministrativeDTO) throws URISyntaxException {
        log.debug("REST request to update UniteAdministrative : {}", uniteAdministrativeDTO);
        if (uniteAdministrativeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UniteAdministrativeDTO result = uniteAdministrativeService.save(uniteAdministrativeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, uniteAdministrativeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /unite-administratives} : get all the uniteAdministratives.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of uniteAdministratives in body.
     */
    @GetMapping("/unite-administratives")
    public ResponseEntity<List<UniteAdministrativeDTO>> getAllUniteAdministratives(Pageable pageable) {
        log.debug("REST request to get a page of UniteAdministratives");
        Page<UniteAdministrativeDTO> page = uniteAdministrativeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /unite-administratives/:id} : get the "id" uniteAdministrative.
     *
     * @param id the id of the uniteAdministrativeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the uniteAdministrativeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unite-administratives/{id}")
    public ResponseEntity<UniteAdministrativeDTO> getUniteAdministrative(@PathVariable Long id) {
        log.debug("REST request to get UniteAdministrative : {}", id);
        Optional<UniteAdministrativeDTO> uniteAdministrativeDTO = uniteAdministrativeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(uniteAdministrativeDTO);
    }

    /**
     * {@code DELETE  /unite-administratives/:id} : delete the "id" uniteAdministrative.
     *
     * @param id the id of the uniteAdministrativeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unite-administratives/{id}")
    public ResponseEntity<Void> deleteUniteAdministrative(@PathVariable Long id) {
        log.debug("REST request to delete UniteAdministrative : {}", id);
        uniteAdministrativeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PutMapping("/unite-administratives/updateListe")
    public ResponseEntity<List<UniteAdministrativeDTO>> updateAall(@RequestBody List<UniteAdministrativeDTO> uniteAdministrativeDTOS){
        return ResponseEntity.ok(uniteAdministrativeService.updateAall(uniteAdministrativeDTOS));
    }

    @GetMapping("/unite-administratives/find-all")
    public ResponseEntity<List<UniteAdministrativeDTO>> findAllUniteAdministrative() {
        return ResponseEntity.ok(  ).body( uniteAdministrativeService.findAllUniteAdministrative() );
    }
}
