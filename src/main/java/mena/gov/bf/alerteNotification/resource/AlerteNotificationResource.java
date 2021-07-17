package mena.gov.bf.alerteNotification.resource;

import mena.gov.bf.alerteNotification.entity.Account;
import mena.gov.bf.alerteNotification.service.AlerteNotificationService;
import mena.gov.bf.service.dto.EtapeActivitePpmDTO;
import mena.gov.bf.service.dto.PpmActiviteDTO;
import mena.gov.bf.service.dto.UserNotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlerteNotificationResource {

    @Autowired
    AlerteNotificationService alerteNotificationService;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @GetMapping("/alerte-notifications/curent-task")
    public ResponseEntity<List<Integer>> getCurentTask() {

        return ResponseEntity.ok(alerteNotificationService.getStatutEtapePPMActivite());
    }

    @GetMapping("/activites/all-by-ppm")
    public ResponseEntity<List<PpmActiviteDTO>> findAllByPPM(@RequestParam Long ppmId) {
        return ResponseEntity.ok().body(alerteNotificationService.findActivitesByPPM(ppmId));
    }

    @PutMapping("/alerte-notifications/all-not-visited")
    public ResponseEntity<List<UserNotificationDTO>> findAllNotVisited(@RequestBody Account account) {
        return ResponseEntity.ok().body(alerteNotificationService.getAllEtapeAndTaskNotVisited(account));
    }
}
