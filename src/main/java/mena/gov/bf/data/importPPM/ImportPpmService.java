package mena.gov.bf.data.importPPM;

import mena.gov.bf.domain.*;
import mena.gov.bf.domain.enumeration.AeCp;
import mena.gov.bf.domain.enumeration.EtatMarche;
import mena.gov.bf.domain.enumeration.Niveau;
import mena.gov.bf.repository.*;
import mena.gov.bf.service.ActiviteService;
import mena.gov.bf.service.ExerciceBudgetaireService;
import mena.gov.bf.service.dto.ActiviteDTO;
import mena.gov.bf.service.mapper.ActiviteMapper;
import mena.gov.bf.service.mapper.ExerciceBudgetaireMapper;
import mena.gov.bf.utils.Constants;
import mena.gov.bf.utils.HandlerConstant;
import mena.gov.bf.utils.ReportService;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
@Service
public class ImportPpmService {
    private static final Logger log = LoggerFactory.getLogger(ImportPpmService.class);
    private final PPMRepository ppmRepository;
    private final LigneBudgetaireRepository ligneBudgetaireRepository;
    private final ExerciceBudgetaireRepository exerciceBudgetaireRepository;
    private final ExerciceBudgetaireService exerciceBudgetaireService;
    private final ExerciceBudgetaireMapper exerciceBudgetaireMapper;
    private final ActiviteRepository activiteRepository;
    private final ActiviteService activiteService;
    private final ActiviteMapper activiteMapper;
    private final PpmActiviteRepository ppmActiviteRepository;
    private final NaturePrestationRepository naturePrestationRepository;
    private final SourceFinancementRepository sourceFinancementRepository;
    private final ModePassationRepository modePassationRepository;
    private final BesoinRepository besoinRepository;
    private final BesoinLigneBudgetaireRepository besoinLigneBudgetaireRepository;
    private final UniteAdministrativeRepository uniteAdministrativeRepository;
    private final TimbreRepository timbreRepository;
    @Autowired
    private ApplicationContext applicationContext;
    private final ReportService reportService;

    public ImportPpmService(PPMRepository ppmRepository, LigneBudgetaireRepository ligneBudgetaireRepository,
            ExerciceBudgetaireRepository exerciceBudgetaireRepository,
            ExerciceBudgetaireService exerciceBudgetaireService, ExerciceBudgetaireMapper exerciceBudgetaireMapper,
            ActiviteRepository activiteRepository, ActiviteService activiteService, ActiviteMapper activiteMapper,
            PpmActiviteRepository ppmActiviteRepository, NaturePrestationRepository naturePrestationRepository,
            SourceFinancementRepository sourceFinancementRepository, ModePassationRepository modePassationRepository,
            BesoinRepository besoinRepository, BesoinLigneBudgetaireRepository besoinLigneBudgetaireRepository,
            UniteAdministrativeRepository uniteAdministrativeRepository, TimbreRepository timbreRepository,
            ReportService reportService) {
        this.ppmRepository = ppmRepository;
        this.ligneBudgetaireRepository = ligneBudgetaireRepository;
        this.exerciceBudgetaireRepository = exerciceBudgetaireRepository;
        this.exerciceBudgetaireService = exerciceBudgetaireService;
        this.exerciceBudgetaireMapper = exerciceBudgetaireMapper;
        this.activiteRepository = activiteRepository;
        this.activiteService = activiteService;
        this.activiteMapper = activiteMapper;
        this.ppmActiviteRepository = ppmActiviteRepository;
        this.naturePrestationRepository = naturePrestationRepository;
        this.sourceFinancementRepository = sourceFinancementRepository;
        this.modePassationRepository = modePassationRepository;
        this.besoinRepository = besoinRepository;
        this.besoinLigneBudgetaireRepository = besoinLigneBudgetaireRepository;
        this.uniteAdministrativeRepository = uniteAdministrativeRepository;
        this.timbreRepository = timbreRepository;
        this.reportService = reportService;
    }

    /*
     * ======================================== Exportation du model
     * ====================================================
     */
    @Transactional
    public void exportPpmModel(HttpServletResponse response) {
        Optional<ExerciceBudgetaire> exerciceBudgetaire = Optional
                .ofNullable(exerciceBudgetaireRepository.findTopByActiveIsTrue());
        String fileName = null;
        fileName = exerciceBudgetaire.map(exerciceBudgetaire1 -> "ppm-mena-pln-" + exerciceBudgetaire1.getAnnee())
                .orElse("ppm-mena-pln");
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
        header.createCell(0).setCellValue("Code Ligne Plan");
        header.createCell(1).setCellValue("Budget");
        header.createCell(2).setCellValue("Ligne crédit");
        header.createCell(3).setCellValue("AE/CP");
        header.createCell(4).setCellValue("Montant estimé");
        header.createCell(5).setCellValue("Montant depenses engagées");
        header.createCell(6).setCellValue("Crédit disponible");
        header.createCell(7).setCellValue("Nature des prestations");
        header.createCell(8).setCellValue("Mode de passation");
        header.createCell(9).setCellValue("Période de lancement de l'appel à concurrence");
        header.createCell(10).setCellValue("Période de remise des offres/propositions");
        header.createCell(11).setCellValue("Temps nécessaires à l'évaluation des offres/propositions");
        header.createCell(12).setCellValue("Date probable de démarrage des prestations");
        header.createCell(13).setCellValue("Delai d'exécution prévu (jour)");
        header.createCell(14).setCellValue("Date buttoire");
        header.createCell(15).setCellValue("Gestionnaire de crédit");
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

    /*
     * ======================================== importation du ppm
     * ====================================================
     */
    @Transactional
    public void saveFilesPPM(MultipartFile file, Long anneeId, String reference, boolean update) {
        if (update) {
            this.deleteAll(anneeId);
        }
        try {
            File file1 = createFile(file.getInputStream(),
                    new File("./src/main/resources/imports/" + file.getOriginalFilename()));
            saveExcelEntityValue(XSSFWorkbookFactory.createWorkbook(file1, true), anneeId, reference);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public File createFile(InputStream inputStream, File file) throws IOException {
        FileUtils.copyInputStreamToFile(inputStream, file);
        return file;
    }

    private void saveExcelEntityValue(XSSFWorkbook workbook, Long anneeId, String reference) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        List<CellRangeAddress> cellRangeAddresses = sheet.getMergedRegions();
        UniteAdministrative uniteAdministrativeTemp = new UniteAdministrative();
        ExerciceBudgetaire exercice = exerciceBudgetaireMapper
                .toEntity(exerciceBudgetaireService.findOne(anneeId).get());
        Optional<UniteAdministrative> direction = uniteAdministrativeRepository.findAll().stream()
                .filter(unite -> unite.getAbbreviation().toLowerCase().replaceAll(" ", "").equals("dmp")).findFirst();
        if (direction.isPresent()) {
            uniteAdministrativeTemp = direction.get();
        } else {
            uniteAdministrativeTemp.setLibelle("DMP");
            uniteAdministrativeTemp.abbreviation("DMP");
            uniteAdministrativeTemp.setDeleted(false);
            uniteAdministrativeTemp = uniteAdministrativeRepository.save(uniteAdministrativeTemp);
        }
        /**
         * creation du ppm
         */
        PPM ppm = this.createPPM(exercice, reference);

        Activite activiteTmp = new Activite();

        for (Row row : sheet) {

            if (row.getRowNum() > 0) {
                if (row.getCell(0).getStringCellValue() != "") {
                    NaturePrestation naturePrestation = createNaturePrestation(row.getCell(7).getStringCellValue());
                    ModePassation modePassation = createModePassation(row.getCell(8).getStringCellValue());

                    /**
                     * création des besions
                     */
                    Besoin besoin = this.createBesoin(row, false, null, exercice, naturePrestation,
                            uniteAdministrativeTemp);

                    /**
                     * creation de l'activité
                     */
                    activiteTmp = this.createActivite(row, naturePrestation, modePassation);
                    LigneBudgetaire ligneBudgetaire = this.createLigneBudgetaire(row, exercice);
                    /**
                     * Créataion de besooin ligne budgetaire
                     */

                    this.createBesoinLigneBudgetaire(row, ligneBudgetaire, besoin, activiteTmp);

                    /**
                     *
                     * création des ppm activite liés à une activite
                     */
                    PpmActivite ppmActivite = this.createPPMActivte(row, ppm, activiteTmp);
                    activiteService.saveAllEtapeActivitePPM(activiteMapper.toDto(activiteTmp), ppmActivite);
                } else {
                    int index = row.getRowNum();
                    for (int i = 0; i < cellRangeAddresses.size(); i++) {
                        if (sheet.getMergedRegion(i).isInRange(row.getRowNum(), 1)
                                && sheet.getMergedRegion(i).isInRange(row.getRowNum(), 2)
                                && sheet.getMergedRegion(i).isInRange(row.getRowNum(), 3)) {
                            index = 0;
                        }
                    }
                    if (row.getRowNum() == index) {
                        /**
                         * création des besions
                         */
                        Besoin besoin = this.createBesoin(row, true, activiteTmp, exercice,
                                activiteTmp.getNaturePrestation(), uniteAdministrativeTemp);
                        /**
                         * traitement des lignes budgetaires
                         */
                        LigneBudgetaire ligneBudgetaire = this.createLigneBudgetaire(row, exercice);
                        this.createBesoinLigneBudgetaire(row, ligneBudgetaire, besoin, activiteTmp);
                    }
                }
            }
        }
    }

    private PPM createPPM(ExerciceBudgetaire exercice, String reference) {
        PPM ppm = new PPM();
        ppm.setLibellePpm("PPM-" + exercice.getAnnee());
        ppm.setReferencePlan(reference);
        ppm.setIdExercice(exercice.getId());
        ppm.setValid(true);
        ppm.setDeleted(false);
        ppm = ppmRepository.save(ppm);
        return ppm;
    }

    private NaturePrestation createNaturePrestation(String libelle) {
        String finalLibelle = libelle.toLowerCase().replaceAll(" ", "");
        Optional<NaturePrestation> naturePrestation = naturePrestationRepository.findAll().stream()
                .filter(vale -> vale.getLibelle().toLowerCase().replaceAll(" ", "").equals(finalLibelle)).findFirst();
        if (naturePrestation.isPresent()) {
            return naturePrestation.get();
        } else {
            NaturePrestation naturePrestation1 = new NaturePrestation();
            naturePrestation1.setLibelle(libelle);
            naturePrestation1.setCode("" + libelle.substring(0, 3));
            naturePrestation1 = naturePrestationRepository.save(naturePrestation1);
            return naturePrestation1;
        }
    }

    private Activite createActivite(Row row, NaturePrestation naturePrestation, ModePassation modePassation) {
        Activite activite = new Activite();
        activite.setCodeLignePlan(row.getCell(0).getStringCellValue());
        activite.setNomActivite(row.getCell(7).getStringCellValue());
        activite.setNaturePrestation(naturePrestation);
        activite.setPassation(modePassation);
        activite.setGestionnaireCredit(row.getCell(15).getStringCellValue());
        activite.setReported(false);
        activite.setDeleted(false);
        activite.setEtatMarche(EtatMarche.ATTENTE);
        activite = activiteRepository.save(activite);
        return activite;
    }

    private ModePassation createModePassation(String libelle) {
        String finalLibelle = libelle.toLowerCase().replaceAll(" ", "");
        Optional<ModePassation> modePassation = modePassationRepository.findAll().stream()
                .filter(vale -> vale.getAbreviation().toLowerCase().replaceAll(" ", "").equals(finalLibelle)
                        || vale.getLibellePassation().toLowerCase().replaceAll(" ", "").equals(finalLibelle))
                .findFirst();
        if (modePassation.isPresent()) {
            return modePassation.get();
        } else {
            ModePassation modePassation1 = new ModePassation();
            modePassation1.setLibellePassation(libelle);
            modePassation1.setAbreviation(libelle);
            modePassation1.setDeleted(false);
            modePassation1 = modePassationRepository.save(modePassation1);
            return modePassation1;
        }
    }

    private LigneBudgetaire createLigneBudgetaire(Row row, ExerciceBudgetaire exercice) {
        LigneBudgetaire ligneBudgetaire = new LigneBudgetaire();
        String[] contenu = contenuLigne(row.getCell(2).getStringCellValue()); // Je recherche les nombre dans la
                                                                              // troisième cellule
        Optional<LigneBudgetaire> ligneBudgetExist = ligneBudgetaireRepository.findAll().stream()
                .filter(ligne -> ligne.isDeleted() != null && !ligne.isDeleted() && ligne.getSection().equals("23")
                        && ligne.getProgramme().equals(contenu[0]) && ligne.getAction().equals(contenu[1])
                        && ligne.getActivite().equals(contenu[2]) && ligne.getChapitre().equals(contenu[3])
                        && ligne.getArticle().equals(contenu[4]) && ligne.getParagraphe().equals(contenu[5]))
                .findFirst(); // Je recherche une ligne déjà enregistrée avec les mêmes information que la
                              // troisième cellule
        if (ligneBudgetExist.isPresent()) {
            return ligneBudgetExist.get();
        } else {// Si aucun enregistrement de mêmes informations je construis l'objet et
                // j'enregistre
            ligneBudgetaire.setBudget(row.getCell(1).getStringCellValue());
            ligneBudgetaire.setLigneCredit(row.getCell(2).getStringCellValue());
            ligneBudgetaire.setSection("23");
            ligneBudgetaire.setProgramme(contenu[0]);
            ligneBudgetaire.setAction(contenu[1]);
            ligneBudgetaire.setActivite(contenu[2]);
            ligneBudgetaire.setChapitre(contenu[3]);
            ligneBudgetaire.setArticle(contenu[4]);
            ligneBudgetaire.setParagraphe(contenu[5]);
            ligneBudgetaire.setDeleted(false);
            ligneBudgetaire.setExercice(exercice);
            ligneBudgetaire = ligneBudgetaireRepository.save(ligneBudgetaire);
            return ligneBudgetaire;
        }
    }

    private PpmActivite createPPMActivte(Row row, PPM ppm, Activite activite) {
        PpmActivite ppmActivite = new PpmActivite();
        ppmActivite.setMontantDepenseEngageNonLiquide(row.getCell(5).getNumericCellValue());
        ppmActivite.setCreditDisponible(row.getCell(6).getNumericCellValue());
        ppmActivite.setPeriodeLancementAppel(this.convertDate(row.getCell(9)));
        ppmActivite.setPeriodeRemiseOffre(this.convertDate(row.getCell(10)));
        ppmActivite.setTempsNecessaireEvaluationOffre((int) row.getCell(11).getNumericCellValue());
        ppmActivite.setDateProblableDemaragePrestation(this.convertDate(row.getCell(12)));
        ppmActivite.setDelaiExecutionPrevu((int) row.getCell(13).getNumericCellValue());
        ppmActivite.setDateButtoire(this.convertDate(row.getCell(14)));
        ppmActivite.setActivite(activite);
        ppmActivite.setNiveau(Niveau.CENTRAL);
        ppmActivite.setPpm(ppm);
        ppmActivite.setReport(false);
        ppmActivite.setDeleted(false);
        ppmActiviteRepository.save(ppmActivite);
        return ppmActivite;
        // activiteService.saveAllEtapeActivitePPM(activiteMapper.toDto(activite),
        // ppmActivite);
    }

    private LocalDate convertDate(Cell cell) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.parse(df.format(cell.getDateCellValue()), formatter);
        return localDate;
    }

    private Besoin createBesoin(Row row, Boolean isCompose, Activite activite, ExerciceBudgetaire exerciceBudgetaire,
            NaturePrestation naturePrestation, UniteAdministrative direction) {

        Besoin besoin = new Besoin();
        if (!isCompose) {
            besoin.setExercice(exerciceBudgetaire);
            besoin.setLibelle(row.getCell(7).getStringCellValue());
            besoin.setNaturePrestation(naturePrestation);
            besoin.setUniteAdministrative(direction);
            besoin.setDeleted(false);
        } else {
            besoin.setExercice(exerciceBudgetaire);
            besoin.setLibelle(activite.getNaturePrestation().getLibelle());
            besoin.setNaturePrestation(activite.getNaturePrestation());
            besoin.setUniteAdministrative(direction);
            besoin.setDeleted(false);
        }
        return besoinRepository.save(besoin);
    }

    private void createBesoinLigneBudgetaire(Row row, LigneBudgetaire ligneBudgetaire, Besoin besoin,
            Activite activite) {
        System.out.println(
                "**************************** Début de l'enregistrement de BesoinLigneBudgetaire **********************************");
        BesoinLigneBudgetaire besoinLigneBudgetaire = new BesoinLigneBudgetaire();
        besoinLigneBudgetaire.setLigneBudget(ligneBudgetaire);
        besoinLigneBudgetaire.setBesoin(besoin);
        besoinLigneBudgetaire.setActivite(activite);
        if (row.getCell(3).getStringCellValue().toUpperCase().equals("AE")) {
            besoinLigneBudgetaire.setAecp(true);
        } else {
            besoinLigneBudgetaire.setAecp(false);
        }
        Double montantStime = row.getCell(4).getNumericCellValue();
        besoinLigneBudgetaire.setMontantEstime(Double.valueOf(montantStime));
        besoinLigneBudgetaire.setDeleted(false);
        besoinLigneBudgetaireRepository.save(besoinLigneBudgetaire);
        System.out.println("Section: " + besoinLigneBudgetaire.getLigneBudget().getSection());
        System.out.println("Besoin: " + besoinLigneBudgetaire.getBesoin().getLibelle());
        System.out.println(
                "**************************** Fin de l'enregistrement de BesoinLigneBudgetaire **********************************");

    }

    private void deleteAll(Long idAnnee) {
        Optional<PPM> ppm = ppmRepository.findTop1ByIdExercice(idAnnee);
        if (ppm.isPresent()) {
            List<PpmActivite> ppmActiviteList = ppmActiviteRepository.findAll().stream()
                    .filter(value -> value.getPpm().getIdExercice().equals(idAnnee)).collect(Collectors.toList());
            List<BesoinLigneBudgetaire> besoinLigneBudgetaires = besoinLigneBudgetaireRepository.findAll().stream()
                    .filter(value -> value.getBesoin().getExercice().getId().equals(idAnnee))
                    .collect(Collectors.toList());
            List<Besoin> besoinList = besoinLigneBudgetaires.stream().map(BesoinLigneBudgetaire::getBesoin)
                    .collect(Collectors.toList());
            List<Activite> activiteList = besoinLigneBudgetaires.stream().map(BesoinLigneBudgetaire::getActivite)
                    .collect(Collectors.toList());
            List<LigneBudgetaire> ligneBudgetaires = besoinLigneBudgetaires.stream()
                    .map(BesoinLigneBudgetaire::getLigneBudget).collect(Collectors.toList());
            if (!ppmActiviteList.isEmpty()) {
                ppmActiviteRepository.deleteAll(ppmActiviteList);
            }
            ppmRepository.delete(ppm.get());

            if (!besoinList.isEmpty()) {
                besoinRepository.deleteAll(besoinList);
            }
            if (!ligneBudgetaires.isEmpty()) {
                ligneBudgetaireRepository.deleteAll(ligneBudgetaires);
            }
            if (!besoinLigneBudgetaires.isEmpty()) {
                besoinLigneBudgetaireRepository.deleteAll(besoinLigneBudgetaires);
            }
            if (!activiteList.isEmpty()) {
                activiteRepository.deleteAll(activiteList);
            }
        }
    }

    public ResponseEntity<byte[]> printPPM(Long idAnne) {
        List<ActiviteDTO> activiteDTOList = activiteService.findAllActiviteByAnneBudgetaire(idAnne);
        Optional<Timbre> timbre = timbreRepository.findAll().stream().findFirst();
        ExerciceBudgetaire annee = exerciceBudgetaireMapper.toEntity(exerciceBudgetaireService.findOne(idAnne).get());
        if (!activiteDTOList.isEmpty()) {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(activiteDTOList);
            Resource resource = applicationContext.getResource(Constants.CLASS_PATH + "ppm.jasper");
            HashMap<String, Object> parametres = new HashMap<>();
            parametres.put("ANNEE", annee.getAnnee());

            if (timbre.isPresent()) {
                parametres.put("PAYS", timbre.get().getPays());
                parametres.put("DEVISE", timbre.get().getDevise());
                parametres.put("MINISTERE", timbre.get().getLibelle());
                parametres.put("IDENTITE", timbre.get().getIdentiteMinistre());
                parametres.put("TITRE", timbre.get().getTitreMinistre());
            }
            return reportService.imprimerPdf(resource, dataSource, parametres);

        }
        return null;
    }

    /**
     * Méthode de recherche des nombre dans une chaine de caractères
     * 
     * @param maChaine
     * @return
     */
    public String[] contenuLigne(String maChaine) {
        String[] contenu = new String[6];

        Pattern p = Pattern.compile("\\d+"); // Motif p: Un chiffre, équivalent à : [0-9]
        Matcher m = p.matcher(maChaine); // Recherche du motif p dans la chaine maChaine
        int i = 0;
        while (m.find()) {
            contenu[i] = m.group(); // Sachant, l'ordre de formatage de ma chaine, je récupère chaque élément que je
                                    // range à sa place dans mon tableau
            i++;
        }
        return contenu;

    }
}
