package mena.gov.bf.alerteNotification.resource;

import mena.gov.bf.alerteNotification.service.AlerteNotificationService;
import mena.gov.bf.alerteNotification.service.MailService;
import mena.gov.bf.alerteNotification.entity.Account;
import mena.gov.bf.service.dto.UserNotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MailResource {
    @Autowired
    MailService mailService;

    @Autowired
    AlerteNotificationService alerteNotificationService;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @PutMapping("/mails/send")
    public ResponseEntity<List<UserNotificationDTO>> sentMail(@RequestBody Account account) {
        return ResponseEntity.ok(alerteNotificationService.getAllEtapeAndTaskNotVisited(account));
        // return null;
    }
}
