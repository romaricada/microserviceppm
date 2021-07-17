package mena.gov.bf.web.rest;

import mena.gov.bf.service.ModePassationService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.ModePassationDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.ModePassation}.
 */
@RestController
@RequestMapping("/api")
public class ModePassationResource {

    private final Logger log = LoggerFactory.getLogger(ModePassationResource.class);

    private static final String ENTITY_NAME = "microserviceppmModePassation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModePassationService modePassationService;

    public ModePassationResource(ModePassationService modePassationService) {
        this.modePassationService = modePassationService;
    }

    /**
     * {@code POST  /mode-passations} : Create a new modePassation.
     *
     * @param modePassationDTO the modePassationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modePassationDTO, or with status {@code 400 (Bad Request)} if the modePassation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mode-passations")
    public ResponseEntity<ModePassationDTO> createModePassation(@Valid @RequestBody ModePassationDTO modePassationDTO) throws URISyntaxException {
        log.debug("REST request to save ModePassation : {}", modePassationDTO);
        if (modePassationDTO.getId() != null) {
            throw new BadRequestAlertException("A new modePassation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModePassationDTO result = modePassationService.save(modePassationDTO);
        return ResponseEntity.created(new URI("/api/mode-passations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mode-passations} : Updates an existing modePassation.
     *
     * @param modePassationDTO the modePassationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modePassationDTO,
     * or with status {@code 400 (Bad Request)} if the modePassationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modePassationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mode-passations")
    public ResponseEntity<ModePassationDTO> updateModePassation(@Valid @RequestBody ModePassationDTO modePassationDTO) throws URISyntaxException {
        log.debug("REST request to update ModePassation : {}", modePassationDTO);
        if (modePassationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModePassationDTO result = modePassationService.save(modePassationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modePassationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mode-passations} : get all the modePassations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modePassations in body.
     */
    @GetMapping("/mode-passations")
    public ResponseEntity<List<ModePassationDTO>> getAllModePassations(Pageable pageable) {
        log.debug("REST request to get a page of ModePassations");
        Page<ModePassationDTO> page = modePassationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /mode-passations/:id} : get the "id" modePassation.
     *
     * @param id the id of the modePassationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modePassationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mode-passations/{id}")
    public ResponseEntity<ModePassationDTO> getModePassation(@PathVariable Long id) {
        log.debug("REST request to get ModePassation : {}", id);
        Optional<ModePassationDTO> modePassationDTO = modePassationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modePassationDTO);
    }

    /**
     * {@code DELETE  /mode-passations/:id} : delete the "id" modePassation.
     *
     * @param id the id of the modePassationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mode-passations/{id}")
    public ResponseEntity<Void> deleteModePassation(@PathVariable Long id) {
        log.debug("REST request to delete ModePassation : {}", id);
        modePassationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
    @PutMapping("/mode-passations/updateListe")
    public ResponseEntity<List<ModePassationDTO>> updateAall(@RequestBody List<ModePassationDTO> modePassationDTOS){
        return ResponseEntity.ok(modePassationService.updateAall(modePassationDTOS));
    }

    @GetMapping("/mode-passations/find-all")
    public ResponseEntity<List<ModePassationDTO>> findAllReferencielByModePassation() {
        return ResponseEntity.ok(  ).body( modePassationService.findAllReferencielByModePassation() );
    }
}
