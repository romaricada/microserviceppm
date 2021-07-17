package mena.gov.bf.data.exportPPM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import mena.gov.bf.data.importPPM.ImportPpmResource;

@RestController
@RequestMapping("/api")
public class ExportPpmResource {
    private static final Logger log = LoggerFactory.getLogger(ImportPpmResource.class);
    private final ExportPpmService exportPpmService;

    public ExportPpmResource(ExportPpmService exportPpmService) {
        this.exportPpmService = exportPpmService;
    }

    @GetMapping("/export-ppm")
    public void exportPPM(HttpServletResponse response, @RequestParam(name = "idAnnee") Long idAnnee) {
        exportPpmService.exportPPM(response, idAnnee);
    }

    // @GetMapping("/export-ppm-pdf")
    /*
     * public void exportPPMToPdf(@RequestParam(name = "idAnnee") Long idAnnee) {
     * exportPpmService.exportPPMToPdf(idAnnee); }
     */

    @GetMapping("/export-ppm-pdf")
    public ResponseEntity<InputStreamResource> exportPPMToPdf(@RequestParam(name = "idAnnee") Long idAnnee)
            throws IOException {

        ByteArrayInputStream bis = exportPpmService.exportPPMToPdf(idAnnee);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ppm_mena_pdf.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
