package mena.gov.bf.api.proxies;

import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import mena.gov.bf.service.ActiviteService;
import mena.gov.bf.service.ExerciceBudgetaireService;
import mena.gov.bf.service.PPMService;
import mena.gov.bf.service.PpmActiviteService;
import mena.gov.bf.service.dto.ActiviteDTO;
import mena.gov.bf.service.dto.ExerciceBudgetaireDTO;
import mena.gov.bf.service.dto.PPMDTO;
import mena.gov.bf.service.dto.PpmActiviteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-externe")
public class HTTPResource {
    private final Logger log = LoggerFactory.getLogger(HTTPResource.class);
    private final ExerciceBudgetaireService exerciceBudgetaireService;
    private final ActiviteService activiteService;
    private final PPMService ppmService;
    private final PpmActiviteService ppmActiviteService;

    public HTTPResource(ExerciceBudgetaireService exerciceBudgetaireService, ActiviteService activiteService, PPMService ppmService, PpmActiviteService ppmActiviteService) {
        this.exerciceBudgetaireService = exerciceBudgetaireService;
        this.activiteService = activiteService;
        this.ppmService = ppmService;
        this.ppmActiviteService = ppmActiviteService;
    }

    @GetMapping("/activites")
    public ResponseEntity<List<ActiviteDTO>> getAllActivites(Pageable pageable) {
        log.debug("REST request to get a page of Activites");
        Page<ActiviteDTO> page = activiteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil
            .generatePaginationHttpHeaders( ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/activites/{id}")
    public ResponseEntity<ActiviteDTO> getActivite(@PathVariable Long id) {
        log.debug("REST request to get Activite : {}", id);
        Optional<ActiviteDTO> activiteDTO = activiteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(activiteDTO);
    }

    @GetMapping("/activites/all-by-annee")
    public ResponseEntity<List<ActiviteDTO>> findAllByExercice(@RequestParam(name = "idAnnee") Long idAnnee) {
        return ResponseEntity.ok().body(activiteService.findAllActiviteByAnneBudgetaire(idAnnee));
    }


    @GetMapping("/exercice-budgetaires/{id}")
    public ResponseEntity<ExerciceBudgetaireDTO> getExerciceBudgetaire(@PathVariable Long id) {
        log.debug("REST request to get ExerciceBudgetaire : {}", id);
        Optional<ExerciceBudgetaireDTO> exerciceBudgetaireDTO = exerciceBudgetaireService.findOne(id);
        return ResponseUtil.wrapOrNotFound(exerciceBudgetaireDTO);
    }

    @GetMapping("/exercice-budgetaires/current-exercice")
    public ResponseEntity<ExerciceBudgetaireDTO> findCurrentExercice() {
        Optional<ExerciceBudgetaireDTO> exerciceBudgetaireDTO = exerciceBudgetaireService.findCurrentExercice();
        return ResponseUtil.wrapOrNotFound(exerciceBudgetaireDTO);
    }

    @GetMapping("/ppms/{id}")
    public ResponseEntity<PPMDTO> getPPM(@PathVariable Long id) {
        log.debug("REST request to get PPM : {}", id);
        Optional<PPMDTO> pPMDTO = ppmService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pPMDTO);
    }

    @GetMapping("/ppm-activites/{id}")
    public ResponseEntity<PpmActiviteDTO> getPpmActivite(@PathVariable Long id) {
        log.debug("REST request to get PpmActivite : {}", id);
        Optional<PpmActiviteDTO> ppmActiviteDTO = ppmActiviteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ppmActiviteDTO);
    }
}
