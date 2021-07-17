package mena.gov.bf.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import mena.gov.bf.service.CalculDelaiService;
import mena.gov.bf.service.dto.CalculDelaiDTO;
import mena.gov.bf.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing {@link mena.gov.bf.domain.ReferentielDelai}.
 */
@RestController
@RequestMapping("/api")
public class CalculDelaiResource {

    private final Logger log = LoggerFactory.getLogger( CalculDelaiResource.class );

    private static final String ENTITY_NAME = "microserviceppmCalculDelais";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CalculDelaiService calculDelaiService;

    public CalculDelaiResource(CalculDelaiService calculDelaiService) {
        this.calculDelaiService = calculDelaiService;
    }

    /**
     * {@code POST  /referentiel-delais} : Create a new referentielDelai.
     *
     * @param calculDelaiDTO the referentielDelaiDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new referentielDelaiDTO, or with status {@code 400 (Bad Request)} if the referentielDelai has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/calcul-delais/save")
    public ResponseEntity<List<CalculDelaiDTO>> createCalculDelai(@RequestBody CalculDelaiDTO calculDelaiDTO) throws URISyntaxException {
        log.debug( "REST request to save calculDelaiDTO : {}", calculDelaiDTO );
        if (calculDelaiDTO.getId() != null) {
            throw new BadRequestAlertException( "A new calculDelaiDTO cannot already have an ID", ENTITY_NAME, "idexists" );
        }
        return ResponseEntity.ok(calculDelaiService.save(calculDelaiDTO));
    }

    @PutMapping("/calcul-delais/modifier")
    public ResponseEntity<List<CalculDelaiDTO>> modifierCalculDelai(@RequestBody CalculDelaiDTO calculDelaiDTO) throws URISyntaxException {
        log.debug( "REST request to save calculDelaiDTO : {}", calculDelaiDTO );
        if (calculDelaiDTO.getId() == null) {
            throw new BadRequestAlertException( "A new calculDelaiDTO cannot already have an ID", ENTITY_NAME, "idexists" );
        }
        return ResponseEntity.ok(calculDelaiService.save(calculDelaiDTO));
    }


    @PutMapping("/calcul-delais")
    public ResponseEntity<CalculDelaiDTO> updateCalculDelai(@Valid @RequestBody CalculDelaiDTO calculDelaiDTO) {
        log.debug( "REST request to update calculDelaiDTO : {}", calculDelaiDTO );
        if (calculDelaiDTO.getId() == null) {
            throw new BadRequestAlertException( "Invalid id", ENTITY_NAME, "idnull" );
        }
        CalculDelaiDTO result = calculDelaiService.modifier(calculDelaiDTO);
        return ResponseEntity.ok()
            .headers( HeaderUtil.createEntityUpdateAlert( applicationName, true, ENTITY_NAME, calculDelaiDTO.getId().toString() ) )
            .body( result );
    }

    @GetMapping("/calcul-delais/get-by-libelle")
    public ResponseEntity<List<CalculDelaiDTO>> getCalculDealiByLibelle(@RequestParam String libelle) {
        return ResponseEntity.ok(calculDelaiService.getCalculDealiByLibelle(libelle));
    }
    @GetMapping("/calcul-delais/get-by-date")
    public ResponseEntity<CalculDelaiDTO> getEtapesByDate(@RequestParam String libelle) {
        return ResponseEntity.ok(calculDelaiService.getEtapesByDate(libelle));
    }
    /*@GetMapping("/mode-passations/find-all")
    public ResponseEntity<List<ModePassationDTO>> findAllReferencielByModePassation() {
        return ResponseEntity.ok(  ).body( modePassationService.findAllReferencielByModePassation() );
    }*/

    @GetMapping("/calcul-delais")
    public ResponseEntity<List<CalculDelaiDTO>> getAllCalculDelai() {
        log.debug( "REST request to get a page of calculDelaiDTO" );


        return ResponseEntity.ok(calculDelaiService.getAll());
    }

    @PutMapping("/calcul-delais/updateListe")
    public ResponseEntity<List<CalculDelaiDTO>> updateAall(@RequestBody List<CalculDelaiDTO> calculDelaiDTOS){
        return ResponseEntity.ok(calculDelaiService.updateAall(calculDelaiDTOS));
    }

    @PutMapping("/calcul-delais/update")
    public ResponseEntity<List<CalculDelaiDTO>> updateall(@Valid @RequestBody CalculDelaiDTO calculDelaiDTOS){
        return ResponseEntity.ok(calculDelaiService.updateall(calculDelaiDTOS));
    }

    @GetMapping("/calcul-delais/get-by-modepassation")
    public ResponseEntity<List<CalculDelaiDTO>> getModePassation(@RequestParam Long modePassationId) {
        return ResponseEntity.ok(calculDelaiService.getModePassation(modePassationId));
    }

    @GetMapping("/calcul-delais/get-by-modepassationdelai")
    public ResponseEntity<List<CalculDelaiDTO>> getModePassationAndDelaiCalcul(@RequestParam Long modePassationId, @RequestParam String libelle) {
        return ResponseEntity.ok(calculDelaiService.getModePassationAndDelaiCalcul(modePassationId,libelle));
    }

}
