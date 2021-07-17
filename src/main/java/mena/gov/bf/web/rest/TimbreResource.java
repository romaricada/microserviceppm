package mena.gov.bf.web.rest;

import mena.gov.bf.service.TimbreService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.TimbreDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Timbre}.
 */
@RestController
@RequestMapping("/api")
public class TimbreResource {

    private final Logger log = LoggerFactory.getLogger(TimbreResource.class);

    private static final String ENTITY_NAME = "microserviceppmTimbre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TimbreService timbreService;

    public TimbreResource(TimbreService timbreService) {
        this.timbreService = timbreService;
    }

    /**
     * {@code POST  /timbres} : Create a new timbre.
     *
     * @param timbreDTO the timbreDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new timbreDTO, or with status {@code 400 (Bad Request)} if the timbre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/timbres/save")
    public ResponseEntity<TimbreDTO> createTimbre(@RequestBody TimbreDTO timbreDTO) throws URISyntaxException {
        log.debug("REST request to save Timbre : {}", timbreDTO);
        if (timbreDTO.getId() != null) {
            throw new BadRequestAlertException("A new timbre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TimbreDTO result = timbreService.save(timbreDTO);
        return ResponseEntity.created(new URI("/api/timbres/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /timbres} : Updates an existing timbre.
     *
     * @param timbreDTO the timbreDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated timbreDTO,
     * or with status {@code 400 (Bad Request)} if the timbreDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the timbreDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/timbres")
    public ResponseEntity<TimbreDTO> updateTimbre(@RequestBody TimbreDTO timbreDTO) throws URISyntaxException {
        log.debug("REST request to update Timbre : {}", timbreDTO);
        if (timbreDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TimbreDTO result = timbreService.save(timbreDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, timbreDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /timbres} : get all the timbres.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of timbres in body.
     */
    @GetMapping("/timbres")
    public ResponseEntity<List<TimbreDTO>> getAllTimbres(Pageable pageable) {
        log.debug("REST request to get a page of Timbres");
        Page<TimbreDTO> page = timbreService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /timbres/:id} : get the "id" timbre.
     *
     * @param id the id of the timbreDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the timbreDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/timbres/{id}")
    public ResponseEntity<TimbreDTO> getTimbre(@PathVariable Long id) {
        log.debug("REST request to get Timbre : {}", id);
        Optional<TimbreDTO> timbreDTO = timbreService.findOne(id);
        return ResponseUtil.wrapOrNotFound(timbreDTO);
    }

    @GetMapping("/timbres/timbres")
    public TimbreDTO getTimbres() {
       return timbreService.findTimbre();

    }

    /**
     * {@code DELETE  /timbres/:id} : delete the "id" timbre.
     *
     * @param id the id of the timbreDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/timbres/{id}")
    public ResponseEntity<Void> deleteTimbre(@PathVariable Long id) {
        log.debug("REST request to delete Timbre : {}", id);
        timbreService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
