package mena.gov.bf.data.importPPMDeconcentre;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ImportPpmDeconcentreResource {
    private static final Logger log = LoggerFactory.getLogger( ImportPpmDeconcentreResource.class );
    private final ImportPpmDeconcentreService importPpmDeconcentreService;

    public ImportPpmDeconcentreResource(ImportPpmDeconcentreService importPpmDeconcentreService) {
        this.importPpmDeconcentreService = importPpmDeconcentreService;
    }

    /* @GetMapping("/export-model")
    public void exportModelPPMDeconcentre(HttpServletResponse response) {
        importPpmDeconcentreService.exportPpmDeconcentreModel( response );
    } */

    /* @PostMapping("/import-data")
    public ResponseEntity<Void> importDataPPM(@RequestParam(name = "id") Long id, @RequestParam(name = "file") MultipartFile file,@RequestParam(name = "reference") String reference, @RequestParam(name = "update") boolean update) {
        importPpmService.saveFilesPPM( file, id,reference, update );
        return ResponseEntity.noContent().build();
    } */

    @PostMapping("/import-ppm-deconcentre-data")
    public ResponseEntity<Void> importDataPPMDeconcentre(@RequestParam(name = "id") Long id, @RequestParam(name = "file") MultipartFile file,@RequestParam(name = "reference") String reference, @RequestParam(name = "update") boolean update) {
        importPpmDeconcentreService.saveFilesPPMDeconcentre( file, id,reference, update );
        return ResponseEntity.noContent().build();
    }
}
