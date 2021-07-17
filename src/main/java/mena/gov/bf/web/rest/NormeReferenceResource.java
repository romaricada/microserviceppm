package mena.gov.bf.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import mena.gov.bf.service.NormeReferenceService;
import mena.gov.bf.service.dto.NormeReferenceDTO;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing {@link mena.gov.bf.domain.ReferentielDelai}.
 */
@RestController
@RequestMapping("/api")
public class NormeReferenceResource {

    private final Logger log = LoggerFactory.getLogger(NormeReferenceResource.class);

    private static final String ENTITY_NAME = "microserviceppmNormeReference";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NormeReferenceService normeReferenceService;

    public NormeReferenceResource(NormeReferenceService normeReferenceService) {
        this.normeReferenceService = normeReferenceService;
    }

    /**
     * {@code POST  /referentiel-delais} : Create a new referentielDelai.
     *
     * @param normeReferenceDTO the referentielDelaiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and
     * with body the new referentielDelaiDTO, or with status
     * {@code 400 (Bad Request)} if the referentielDelai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/norme-reference")
    public ResponseEntity<NormeReferenceDTO> createReferentielDelai(@RequestBody NormeReferenceDTO normeReferenceDTO) throws URISyntaxException {
        log.debug("REST request to save normeReference : {}", normeReferenceDTO);
        if (normeReferenceDTO.getId() != null) {
            throw new BadRequestAlertException("A new normeReference cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NormeReferenceDTO result = normeReferenceService.save(normeReferenceDTO);
        return ResponseEntity.created(new URI("/api/norme-reference/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /referentiel-delais} : Updates an existing referentielDelai.
     *
     * @param normeReferenceDTO the referentielDelaiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with
     * body the updated referentielDelaiDTO, or with status
     * {@code 400 (Bad Request)} if the referentielDelaiDTO is not valid, or
     * with status {@code 500 (Internal Server Error)} if the
     * referentielDelaiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/norme-reference")
    public ResponseEntity<NormeReferenceDTO> updateReferentielDelai(@RequestBody NormeReferenceDTO normeReferenceDTO) throws URISyntaxException {
        log.debug("REST request to update normeReference : {}", normeReferenceDTO);
        if (normeReferenceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NormeReferenceDTO result = normeReferenceService.save(normeReferenceDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, normeReferenceDTO.getId().toString()))
                .body(result);
    }

    @GetMapping("/norme-reference")
    public ResponseEntity<List<NormeReferenceDTO>> getAllReferentielDelais() {
        log.debug("REST request to get a page of ReferentielDelais");

        return ResponseEntity.ok(normeReferenceService.getAll());
    }

}
