package mena.gov.bf.web.rest;

import mena.gov.bf.service.ReferentielDelaiService;
import mena.gov.bf.service.dto.DateCalcule;
import mena.gov.bf.service.dto.EtapeDTO;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.ReferentielDelaiDTO;

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

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

/**
 * REST controller for managing {@link mena.gov.bf.domain.ReferentielDelai}.
 */
@RestController
@RequestMapping("/api")
public class ReferentielDelaiResource {

    private final Logger log = LoggerFactory.getLogger( ReferentielDelaiResource.class );

    private static final String ENTITY_NAME = "microserviceppmReferentielDelai";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReferentielDelaiService referentielDelaiService;

    public ReferentielDelaiResource(ReferentielDelaiService referentielDelaiService) {
        this.referentielDelaiService = referentielDelaiService;
    }

    /**
     * {@code POST  /referentiel-delais} : Create a new referentielDelai.
     *
     * @param referentielDelaiDTO the referentielDelaiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new referentielDelaiDTO, or with status {@code 400 (Bad Request)} if the referentielDelai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/referentiel-delais")
    public ResponseEntity<ReferentielDelaiDTO> createReferentielDelai(@RequestBody ReferentielDelaiDTO referentielDelaiDTO) throws URISyntaxException {
        log.debug( "REST request to save ReferentielDelai : {}", referentielDelaiDTO );
        if (referentielDelaiDTO.getId() != null) {
            throw new BadRequestAlertException( "A new referentielDelai cannot already have an ID", ENTITY_NAME, "idexists" );
        }
        ReferentielDelaiDTO result = referentielDelaiService.save( referentielDelaiDTO );
        return ResponseEntity.created( new URI( "/api/referentiel-delais/" + result.getId() ) )
            .headers( HeaderUtil.createEntityCreationAlert( applicationName, true, ENTITY_NAME, result.getId().toString() ) )
            .body( result );
    }

    /**
     * {@code PUT  /referentiel-delais} : Updates an existing referentielDelai.
     *
     * @param referentielDelaiDTO the referentielDelaiDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated referentielDelaiDTO,
     * or with status {@code 400 (Bad Request)} if the referentielDelaiDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the referentielDelaiDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/referentiel-delais")
    public ResponseEntity<ReferentielDelaiDTO> updateReferentielDelai(@RequestBody ReferentielDelaiDTO referentielDelaiDTO) throws URISyntaxException {
        log.debug( "REST request to update ReferentielDelai : {}", referentielDelaiDTO );
        if (referentielDelaiDTO.getId() == null) {
            throw new BadRequestAlertException( "Invalid id", ENTITY_NAME, "idnull" );
        }
        ReferentielDelaiDTO result = referentielDelaiService.save( referentielDelaiDTO );
        return ResponseEntity.ok()
            .headers( HeaderUtil.createEntityUpdateAlert( applicationName, true, ENTITY_NAME, referentielDelaiDTO.getId().toString() ) )
            .body( result );
    }

    /**
     * {@code GET  /referentiel-delais} : get all the referentielDelais.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of referentielDelais in body.
     */
    @GetMapping("/referentiel-delais")
    public ResponseEntity<List<ReferentielDelaiDTO>> getAllReferentielDelais(Pageable pageable) {
        log.debug( "REST request to get a page of ReferentielDelais" );
        Page<ReferentielDelaiDTO> page = referentielDelaiService.findAll( pageable );
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders( ServletUriComponentsBuilder.fromCurrentRequest(), page );
        return ResponseEntity.ok().headers( headers ).body( page.getContent() );
    }

    /**
     * {@code GET  /referentiel-delais/:id} : get the "id" referentielDelai.
     *
     * @param id the id of the referentielDelaiDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the referentielDelaiDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/referentiel-delais/{id}")
    public ResponseEntity<ReferentielDelaiDTO> getReferentielDelai(@PathVariable Long id) {
        log.debug( "REST request to get ReferentielDelai : {}", id );
        Optional<ReferentielDelaiDTO> referentielDelaiDTO = referentielDelaiService.findOne( id );
        return ResponseUtil.wrapOrNotFound( referentielDelaiDTO );
    }

    /**
     * {@code DELETE  /referentiel-delais/:id} : delete the "id" referentielDelai.
     *
     * @param id the id of the referentielDelaiDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/referentiel-delais/{id}")
    public ResponseEntity<Void> deleteReferentielDelai(@PathVariable Long id) {
        log.debug( "REST request to delete ReferentielDelai : {}", id );
        referentielDelaiService.delete( id );
        return ResponseEntity.noContent().headers( HeaderUtil.createEntityDeletionAlert( applicationName, true, ENTITY_NAME, id.toString() ) ).build();
    }

    @PutMapping("/referentiel-delais/update-all")
    public ResponseEntity<List<ReferentielDelaiDTO>> updateAll(@Valid @RequestBody List<ReferentielDelaiDTO> referentielDelaiDTOs) throws URISyntaxException {
        if (referentielDelaiDTOs.isEmpty()) {
            throw new BadRequestAlertException( "requette de suppression invalide", ENTITY_NAME, "idnull" );
        }
        return new ResponseEntity<>( referentielDelaiService.updateAll( referentielDelaiDTOs ), HttpStatus.OK );
    }

    @GetMapping("/referentiel-delais/get-referentiel-delai-by-mode-passationId")
    public ResponseEntity<List<ReferentielDelaiDTO>> findReferentielDelaiByModePassationId(@RequestParam Long modePassationId) {
        return ResponseEntity.ok( referentielDelaiService.findReferentielDelaiByModePassationId( modePassationId ) );
    }

    @PutMapping("/referentiel-delais/get-plan-by-mode-passationId")
    public ResponseEntity<List<ReferentielDelaiDTO>> getReferentielDelaiByModePassationId(@RequestBody ReferentielDelaiDTO referentielDelai) {

        log.debug( ":::::::::: :::::::::::: :::::::::::: referentielDelai {}  :::::::::::: ::::::::::  ::::::::::", referentielDelai );
        return ResponseEntity.ok( referentielDelaiService.calculerDelaisEtape( referentielDelai ) );
        // return null;
    }

    @PostMapping("/referentiel-delais/save-liste")
    public ResponseEntity<Long> saveListe(@Valid @RequestBody List<ReferentielDelaiDTO> referentielDelaiDTOS) {
        return ResponseEntity.ok().body( referentielDelaiService.saveListeReferencielDelai( referentielDelaiDTOS ) );
    }

   /* @PutMapping("/referentiel-delais/modifi-liste")
    public ResponseEntity<ReferentielDelaiDTO> modifiListe(@Valid @RequestBody List<ReferentielDelaiDTO> referentielDelaiDTOS) {
        return ResponseEntity.ok().body( referentielDelaiService.saveListeReferencielDelai( referentielDelaiDTOS ) );
    }*/


    @GetMapping("/referentiel-delais/update-liste")
    public ResponseEntity<Long> removeListeByModePassation( @RequestParam Long idModePassation) {
        return ResponseEntity.ok().body( referentielDelaiService.removeListeByModePassation( idModePassation ) );
    }

    @GetMapping("/referentiel-delais/get-etape-by-mode-passation")
    public ResponseEntity<List<EtapeDTO>> findEtapeByModePassationId(@RequestParam Long modePassationId) {
        return ResponseEntity.ok( referentielDelaiService.findEtapeByModePassationId( modePassationId ) );
    }

    @GetMapping("/referentiel-delais/caluler-delai")
    public ResponseEntity<List<EtapeDTO>> calculDelai(@RequestParam Long modePassationId, @RequestParam Long naturePrestationId, @RequestParam LocalDate date, @RequestParam List<EtapeDTO> etapesSelecteds) {
        return ResponseEntity.ok( referentielDelaiService.findEtapeByModePassationId( modePassationId ) );
    }

    @PutMapping("/referentiel-delais/date")
    public ResponseEntity<List<DateCalcule>> getDateCalcule(@RequestBody List<ReferentielDelaiDTO> referentielDelais) {
        return ResponseEntity.ok(referentielDelaiService.setDateCalcule(referentielDelais));
    }

    @GetMapping("/referentiel-delais/get-etape-by-ppm-activite")
    public ResponseEntity<List<ReferentielDelaiDTO>> findEtapeByPpmActiviteId(@RequestParam Long ppmActiviteId, @RequestParam Long modePassationId) {
        return ResponseEntity.ok( referentielDelaiService.getReferentielDelaisByPpmActivite(ppmActiviteId, modePassationId ));
    }

    /* @GetMapping("/referentiel-delais/get-etape-by-mode-passation")
    public ResponseEntity<List<ReferentielDelaiDTO>> findEtapeById(@RequestParam Long ppmActiviteId, @RequestParam Long modePassationId) {
        return ResponseEntity.ok( referentielDelaiService.getReferentielDelaisByPpmActivite(ppmActiviteId, modePassationId));
    } */

}
