package mena.gov.bf.data.importPPM;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import mena.gov.bf.domain.BesoinLigneBudgetaire;
import mena.gov.bf.domain.ExerciceBudgetaire;
import mena.gov.bf.domain.LigneBudgetaire;
import mena.gov.bf.domain.UniteAdministrative;
import mena.gov.bf.repository.BesoinLigneBudgetaireRepository;
import mena.gov.bf.repository.ExerciceBudgetaireRepository;
import mena.gov.bf.repository.LigneBudgetaireRepository;
import mena.gov.bf.repository.UniteAdministrativeRepository;
import mena.gov.bf.service.ExerciceBudgetaireService;
import mena.gov.bf.service.dto.ExerciceBudgetaireDTO;
import mena.gov.bf.service.mapper.ExerciceBudgetaireMapper;
import mena.gov.bf.service.mapper.UniteAdministrativeMapper;
import mena.gov.bf.utils.HandlerConstant;
import mena.gov.bf.utils.ReportService;

@Service
public class ImportBudgetService {
    private static final Logger log = LoggerFactory.getLogger(ImportBudgetService.class);
    private final LigneBudgetaireRepository ligneBudgetaireRepository;
    private final ExerciceBudgetaireService exerciceBudgetaireService;
    private final UniteAdministrativeRepository administrativeRepository;
    private final UniteAdministrativeMapper administrativeMapper;
    private final ExerciceBudgetaireRepository exerciceBudgetaireRepository;
    BesoinLigneBudgetaireRepository besoinLigneBudgetaireRepository;
    private final ExerciceBudgetaireMapper exerciceBudgetaireMapper;
    private final ImportPpmService importPpmService;
    @Autowired
    private ApplicationContext applicationContext;
    private final ReportService reportService;

    public ImportBudgetService(LigneBudgetaireRepository ligneBudgetaireRepository, ExerciceBudgetaireService exerciceBudgetaireService, UniteAdministrativeRepository administrativeRepository, UniteAdministrativeMapper administrativeMapper, ExerciceBudgetaireRepository exerciceBudgetaireRepository, ExerciceBudgetaireMapper exerciceBudgetaireMapper, ImportPpmService importPpmService, ReportService reportService) {
        this.ligneBudgetaireRepository = ligneBudgetaireRepository;
        this.exerciceBudgetaireService = exerciceBudgetaireService;
        this.administrativeRepository = administrativeRepository;
        this.administrativeMapper = administrativeMapper;
        this.exerciceBudgetaireRepository = exerciceBudgetaireRepository;
        this.exerciceBudgetaireMapper = exerciceBudgetaireMapper;
        this.importPpmService = importPpmService;
        this.reportService = reportService;
    }

    
    /*
     * Importation du Budget
     */
    @Transactional
    public void saveFileBudget(MultipartFile file, Long anneeId, Long uniteAdministrativeId, Boolean update) {
        if (update) {
            this.deleteAll(anneeId);
        }

        System.out.println("================================ Début ===========================");
        File file1;
        try {
            file1 = importPpmService.createFile(file.getInputStream(),
                    new File("./src/main/resources/imports/" + file.getOriginalFilename()));
            System.out.println("=========================" + file1.getName() + "====================");
            try {
                saveValueToLigneBudgetaireTable(XSSFWorkbookFactory.createWorkbook(file1, true), anneeId, uniteAdministrativeId);
            } catch (InvalidFormatException e) {
                // TODO Auto-generated catch block
                System.out.println("============== Echec de transfert de données ==============");
                e.printStackTrace();
            }
            System.out.println("============== Transfert OK ===================");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("============== Echec de copie de fichier ==============");
            e.printStackTrace();
        }

    }

    private void saveValueToLigneBudgetaireTable(XSSFWorkbook workbook, Long anneeId, Long uniteAdministrativeId) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        ExerciceBudgetaire exercice = exerciceBudgetaireMapper.toEntity(exerciceBudgetaireService.findOne(anneeId).get());
        UniteAdministrative administrative=administrativeRepository.findById(uniteAdministrativeId).get();
        List<LigneBudgetaire> listLigne = new ArrayList<>();
        for (Row row : sheet) {
            if (row.getRowNum() > 0) {
                LigneBudgetaire ligne = createLigneBudgetaireRow(row, exercice, administrative);
                if (!isLigneBudgetairePresent(row))
                    listLigne.add(ligne);
            }
        }
        ligneBudgetaireRepository.saveAll(listLigne);
    }

    /**
     * Création d'une ligne budgétaire
     * 
     * @param row
     * @param exerciceBudgetaire
     * @return
     */
    private LigneBudgetaire createLigneBudgetaireRow(Row row, ExerciceBudgetaire exerciceBudgetaire, UniteAdministrative uniteAdministrative) {
        LigneBudgetaire ligneBudgetaire = new LigneBudgetaire();
        String section = String.format("%.0f", row.getCell(0).getNumericCellValue());
        String programme = String.format("%.0f", row.getCell(1).getNumericCellValue());
        String action_budget = String.format("%.0f", row.getCell(2).getNumericCellValue());
        String chapitre = String.format("%.0f", row.getCell(3).getNumericCellValue());
        String activite = String.format("%.0f", row.getCell(4).getNumericCellValue());
        String article = String.format("%.0f", row.getCell(5).getNumericCellValue());
        String paragraphe = String.format("%.0f", row.getCell(6).getNumericCellValue());

        String ligne = "Prg " + programme + " Act " + action_budget + " Actv " + activite + " Chap " + chapitre
                + " Art " + article + " Par " + paragraphe;

        ligneBudgetaire.setBudget("Budget National");
        ligneBudgetaire.setLigneCredit(ligne);
        ligneBudgetaire.setSection(section);
        ligneBudgetaire.setProgramme(programme);
        ligneBudgetaire.setAction(action_budget);
        ligneBudgetaire.setChapitre(chapitre);
        ligneBudgetaire.setActivite(activite);
        ligneBudgetaire.setArticle(article);
        ligneBudgetaire.setParagraphe(paragraphe);
        ligneBudgetaire.setDotInitAE(row.getCell(7).getNumericCellValue());
        ligneBudgetaire.setDotInitCP(row.getCell(8).getNumericCellValue());
        ligneBudgetaire.setDotCorAE(row.getCell(9).getNumericCellValue());
        ligneBudgetaire.setDotCorCP(row.getCell(10).getNumericCellValue());
        ligneBudgetaire.setEngage(row.getCell(11).getNumericCellValue());
        ligneBudgetaire.setEngageCF(row.getCell(12).getNumericCellValue());
        ligneBudgetaire.setLiquide(row.getCell(13).getNumericCellValue());
        ligneBudgetaire.setOrdonne(row.getCell(14).getNumericCellValue());
        ligneBudgetaire.setVbp(row.getCell(15).getNumericCellValue());
        ligneBudgetaire.setEcp(row.getCell(16).getNumericCellValue());
        ligneBudgetaire.setDeleted(false);
        ligneBudgetaire.setExercice(exerciceBudgetaire);
        ligneBudgetaire.setUniteAdministrative(uniteAdministrative);
        return ligneBudgetaire;
    }

    /**
     * Méthode de suppression de toutes les lignes budgétaires d'un exercice ainsi
     * que de ses besoinlignebudgetaire
     * 
     * @param idAnnee
     */
    private void deleteAll(Long idAnnee) {
        ExerciceBudgetaire exercice = exerciceBudgetaireMapper.toEntity(exerciceBudgetaireService.findOne(idAnnee).get());
        List<LigneBudgetaire> listToDelete = ligneBudgetaireRepository.findAll().stream()
                .filter(ligne -> ligne.getExercice().getAnnee().equals(exercice.getAnnee()))
                .collect(Collectors.toList());
        if (!listToDelete.isEmpty()) {
            for (LigneBudgetaire ligne : listToDelete) {
                List<BesoinLigneBudgetaire> besoinLigneBudgetaires = besoinLigneBudgetaireRepository.findAll().stream()
                        .filter(ligneBesoin -> ligneBesoin.getLigneBudget().getId().equals(ligne.getId()))
                        .collect(Collectors.toList());
                if (!besoinLigneBudgetaires.isEmpty())
                    besoinLigneBudgetaireRepository.deleteAll(besoinLigneBudgetaires);
            }
            ligneBudgetaireRepository.deleteAll(listToDelete);
        }
    }

    private Boolean isLigneBudgetairePresent(Row row) {
        Boolean exist = false;

        String section = String.format("%.0f", row.getCell(0).getNumericCellValue());
        String programme = String.format("%.0f", row.getCell(1).getNumericCellValue());
        String action_budget = String.format("%.0f", row.getCell(2).getNumericCellValue());
        String chapitre = String.format("%.0f", row.getCell(3).getNumericCellValue());
        String activite = String.format("%.0f", row.getCell(4).getNumericCellValue());
        String article = String.format("%.0f", row.getCell(5).getNumericCellValue());
        String paragraphe = String.format("%.0f", row.getCell(6).getNumericCellValue());

        Optional<LigneBudgetaire> ligneBudgetExist = ligneBudgetaireRepository.findAll().stream()
                .filter(ligne -> ligne.isDeleted() != null && !ligne.isDeleted() && ligne.getSection().equals(section)
                        && ligne.getProgramme().equals(programme) && ligne.getAction().equals(action_budget)
                        && ligne.getActivite().equals(chapitre) && ligne.getChapitre().equals(activite)
                        && ligne.getArticle().equals(article) && ligne.getParagraphe().equals(paragraphe))
                .findFirst();

        if (ligneBudgetExist.isPresent())
            exist = true;
        else
            exist = false;
        return exist;
    }

    /*
     * ==================== Exportation du model ============================
     */
    @Transactional
    public void exportBudgetModel(HttpServletResponse response) {
        Optional<ExerciceBudgetaire> exerciceBudgetaire = Optional
                .ofNullable(exerciceBudgetaireRepository.findTopByActiveIsTrue());
        String fileName = null;
        fileName = exerciceBudgetaire.map(exerciceBudgetaire1 -> "budget-mena-pln-" + exerciceBudgetaire1.getAnnee())
                .orElse("budget-mena-pln");
        this.createExportExcelModel(fileName, response);
    }

    private void createExportExcelModel(String fileName, HttpServletResponse response) {
        XSSFWorkbook workbook = createWorkbook(fileName);
        createHeaders(workbook);
        /**
         * Definition de la sortei du ficher
         */
        buildExcelResponse(workbook, fileName, response);
    }

    private void createHeaders(XSSFWorkbook workbook) {
        Row header = workbook.getSheetAt(0).createRow(0);
        header.createCell(0).setCellValue("Section");
        header.createCell(1).setCellValue("TypeProgramme");
        header.createCell(2).setCellValue("Action");
        header.createCell(3).setCellValue("Chapitre");
        header.createCell(4).setCellValue("Activite");
        header.createCell(5).setCellValue("Article");
        header.createCell(6).setCellValue("Paragraphe");
        header.createCell(7).setCellValue("Dot Init AE");
        header.createCell(8).setCellValue("Dot init CP");
        header.createCell(9).setCellValue("Dot Cor AE");
        header.createCell(10).setCellValue("Dot Cor CP");
        header.createCell(11).setCellValue("ENG");
        header.createCell(12).setCellValue("ENG_CF");
        header.createCell(13).setCellValue("LIQ");
        header.createCell(14).setCellValue("ORD");
        header.createCell(15).setCellValue("VBP");
        header.createCell(16).setCellValue("ECP");
    }

    private void buildExcelResponse(XSSFWorkbook workbook, String fileName, HttpServletResponse response) {
        File file = getFile(fileName, ".xlsx");
        HandlerConstant.buildResponse(workbook, response, file);
    }

    private File getFile(String fileName, String extension) {
        return new File("./src/main/resources/imports/", fileName.concat(extension));
    }

    private XSSFWorkbook createWorkbook(String fileName) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        workbook.createSheet(fileName.replaceAll("-", ""));
        return workbook;
    }

}
