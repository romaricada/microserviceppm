package mena.gov.bf.web.rest;

import mena.gov.bf.service.BesoinService;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import mena.gov.bf.service.dto.BesoinDTO;

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
 * REST controller for managing {@link mena.gov.bf.domain.Besoin}.
 */
@RestController
@RequestMapping("/api")
public class BesoinResource {

    private final Logger log = LoggerFactory.getLogger( BesoinResource.class );

    private static final String ENTITY_NAME = "microserviceppmBesoin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BesoinService besoinService;

    public BesoinResource(BesoinService besoinService) {
        this.besoinService = besoinService;
    }

    /**
     * {@code POST  /besoins} : Create a new besoin.
     *
     * @param besoinDTO the besoinDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new besoinDTO, or with status {@code 400 (Bad Request)} if the besoin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/besoins")
    public ResponseEntity<BesoinDTO> createBesoin(@Valid @RequestBody BesoinDTO besoinDTO) throws URISyntaxException {
        log.debug( "REST request to save Besoin : {}", besoinDTO );
        if (besoinDTO.getId() != null) {
            throw new BadRequestAlertException( "A new besoin cannot already have an ID", ENTITY_NAME, "idexists" );
        }
        BesoinDTO result = besoinService.save( besoinDTO );
        return ResponseEntity.created( new URI( "/api/besoins/" + result.getId() ) )
            .headers( HeaderUtil.createEntityCreationAlert( applicationName, true, ENTITY_NAME, result.getId().toString() ) )
            .body( result );
    }

    /**
     * {@code PUT  /besoins} : Updates an existing besoin.
     *
     * @param besoinDTO the besoinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated besoinDTO,
     * or with status {@code 400 (Bad Request)} if the besoinDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the besoinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/besoins")
    public ResponseEntity<BesoinDTO> updateBesoin(@Valid @RequestBody BesoinDTO besoinDTO) throws URISyntaxException {
        log.debug( "REST request to update Besoin : {}", besoinDTO );
        if (besoinDTO.getId() == null) {
            throw new BadRequestAlertException( "Invalid id", ENTITY_NAME, "idnull" );
        }
        BesoinDTO result = besoinService.save( besoinDTO );
        return ResponseEntity.ok()
            .headers( HeaderUtil.createEntityUpdateAlert( applicationName, true, ENTITY_NAME, besoinDTO.getId().toString() ) )
            .body( result );
    }

    /**
     * {@code GET  /besoins} : get all the besoins.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of besoins in body.
     */
    @GetMapping("/besoins")
    public ResponseEntity<List<BesoinDTO>> getAllBesoins(Pageable pageable) {
        log.debug( "REST request to get a page of Besoins" );
        Page<BesoinDTO> page = besoinService.findAll( pageable );
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders( ServletUriComponentsBuilder.fromCurrentRequest(), page );
        return ResponseEntity.ok().headers( headers ).body( page.getContent() );
    }

    /**
     * {@code GET  /besoins/:id} : get the "id" besoin.
     *
     * @param id the id of the besoinDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the besoinDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/besoins/{id}")
    public ResponseEntity<BesoinDTO> getBesoin(@PathVariable Long id) {
        log.debug( "REST request to get Besoin : {}", id );
        Optional<BesoinDTO> besoinDTO = besoinService.findOne( id );
        return ResponseUtil.wrapOrNotFound( besoinDTO );
    }

    /**
     * {@code DELETE  /besoins/:id} : delete the "id" besoin.
     *
     * @param id the id of the besoinDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/besoins/{id}")
    public ResponseEntity<Void> deleteBesoin(@PathVariable Long id) {
        log.debug( "REST request to delete Besoin : {}", id );
        besoinService.delete( id );
        return ResponseEntity.noContent().headers( HeaderUtil.createEntityDeletionAlert( applicationName, true, ENTITY_NAME, id.toString() ) ).build();
    }

    @PutMapping("/besoins/delete-all")
    public ResponseEntity<List<BesoinDTO>> updateAll(@RequestBody List<BesoinDTO> besoinDTOs) throws URISyntaxException {
        log.debug( "REST request to update all Besoins : {}", besoinDTOs.size() );

        return ResponseEntity.ok( besoinService.updateAll( besoinDTOs ) );

    }

    @GetMapping("/besoins/all-by-exercice-and-direction")
    public ResponseEntity<List<BesoinDTO>> findAllByDirectionAndExercice(@RequestParam(name = "exerciceId") Long exerciceId, @RequestParam(name = "directionId") Long directionId, Pageable pageable) {
        Page<BesoinDTO> page = besoinService.findAllByDirectionAndExercice( exerciceId, directionId, pageable );
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders( ServletUriComponentsBuilder.fromCurrentRequest(), page );
        return ResponseEntity.ok().headers( headers ).body( page.getContent() );
    }

    @GetMapping("/besoins/all-by-nature-prestation")
    public ResponseEntity<List<BesoinDTO>> findAllByNaturePrestation(@RequestParam Long naturePrestationId, @RequestParam Long exerciceId) {

        return ResponseEntity.ok(besoinService.findBesoinsByNaturePrestationId(naturePrestationId, exerciceId));
    }

    @GetMapping("/besoins/all-by-exercice")
    public ResponseEntity<List<BesoinDTO>> findAllByExercice(@RequestParam Long exerciceId) {

        return ResponseEntity.ok(besoinService.findBesoinsByExerciceId(exerciceId));
    }

    @GetMapping("/besoins/all-by-exercice-update")
    public ResponseEntity<List<BesoinDTO>> findAllByExerciceupdate(@RequestParam Long exerciceId) {
        return ResponseEntity.ok(besoinService.findBesoinsByExerciceUpdate(exerciceId));
    }

    @GetMapping("/besoins/delete-possible")
    public ResponseEntity<String> deletedConfirm(@RequestBody List<BesoinDTO> besoinDTOs) {

        return ResponseEntity.ok(besoinService.deletedConfirm(besoinDTOs));
    }

    @GetMapping("/besoins/montant-restant")
    public double getMontantEngagement(Long ligneId, Long besoinId) {
        return besoinService.montantRestant(ligneId,besoinId);
    }

    @GetMapping("/besoins/used-false")
    public ResponseEntity<List<BesoinDTO>> deletedConfirm(@RequestParam Long exerciceId) {

        return ResponseEntity.ok(besoinService.findBesoinsByExerciceIdAndUsed(exerciceId));
    }

    @GetMapping("/besoins/montant-dotCor-restant")
    public ResponseEntity<List<Double>> montantRestantdotationAE(@RequestParam Long ligneBudgetId) {
        return ResponseEntity.ok(besoinService.montantRestantdotationAE(ligneBudgetId));
    }

    @GetMapping("/besoins/montant-dotCorCP-restant")
    public ResponseEntity<List<Double>> montantRestantdotationCP(@RequestParam Long ligneBudgetId) {
        return ResponseEntity.ok(besoinService.montantRestantdotationCP(ligneBudgetId));
    }
}
