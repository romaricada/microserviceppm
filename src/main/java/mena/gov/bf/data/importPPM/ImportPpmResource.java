package mena.gov.bf.data.importPPM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ImportPpmResource {
    private static final Logger log = LoggerFactory.getLogger( ImportPpmResource.class );
    private final ImportPpmService importPpmService;

    public ImportPpmResource(ImportPpmService importPpmService) {
        this.importPpmService = importPpmService;
    }

    @GetMapping("/export-model")
    public void exportModelPPM(HttpServletResponse response) {
        importPpmService.exportPpmModel( response );
    }

    @PostMapping("/import-data")
    public ResponseEntity<Void> importDataPPM(@RequestParam(name = "id") Long id, @RequestParam(name = "file") MultipartFile file,@RequestParam(name = "reference") String reference, @RequestParam(name = "update") boolean update) {
        importPpmService.saveFilesPPM( file, id,reference, update );
        return ResponseEntity.noContent().build();
    }
}
