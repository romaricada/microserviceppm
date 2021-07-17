package mena.gov.bf.data.importPPM;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ImportBudgetResource {
    private static final Logger log = LoggerFactory.getLogger(ImportPpmResource.class);
    private final ImportBudgetService importBudgetService;

    public ImportBudgetResource(ImportBudgetService importBudgetService) {
        this.importBudgetService = importBudgetService;
    }

    /**
     * Importation du budget
     * 
     * @param id
     * @param file
     * @param update
     * @return
     */
    @PostMapping("/import-budget")
    public ResponseEntity<Void> importDataBudget(@RequestParam(name = "id") Long id, @RequestParam(name = "uniteAdministrativeId") Long uniteAdministrativeId, @RequestParam(name = "file") MultipartFile file, @RequestParam(name = "update") boolean update) {
        importBudgetService.saveFileBudget(file, id, uniteAdministrativeId, update);
        return ResponseEntity.noContent().build();
    }

    /**
     * Exportation du model de budget
     * 
     * @param response
     */
    @GetMapping("/export-model-budget")
    public void exportModelBudget(HttpServletResponse response) {
        importBudgetService.exportBudgetModel(response);
    }

}
