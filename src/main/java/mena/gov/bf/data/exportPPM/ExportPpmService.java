package mena.gov.bf.data.exportPPM;

import mena.gov.bf.domain.BesoinLigneBudgetaire;
import mena.gov.bf.repository.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import mena.gov.bf.utils.HandlerConstant;
import mena.gov.bf.domain.PpmActivite;
import mena.gov.bf.domain.enumeration.Niveau;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.fonts.*;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontStyle;
import com.itextpdf.text.api.Spaceable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfCell;

//import com.zetcode.bean.City;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.logging.Level;
//import java.util.logging.Logger;

@Service
public class ExportPpmService {

    private static final Logger log = LoggerFactory.getLogger(ExportPpmService.class);
    private final PpmActiviteRepository ppmActiviteRepository;

    public ExportPpmService(final PpmActiviteRepository ppmActiviteRepository) {
        this.ppmActiviteRepository = ppmActiviteRepository;
    }

    @Transactional
    public void exportPPM(final HttpServletResponse response, final Long idAnnee) {
        // ExerciceBudgetaire exerciceBudgetaire = exerciceBudgetaireRepository.getOne(
        // idExercice );
        final String fileName = "ppm-mena-pln";
        this.createExportExcelPPM(fileName, response, idAnnee);

    }

    public List<PpmActivite> getListePpmActivites(final Long idAnnee) {
        List<PpmActivite> activites = ppmActiviteRepository.findAll().stream()
                .filter(vale -> vale.getPpm().getIdExercice() != null && vale.getPpm().getIdExercice().equals(idAnnee)
                        && vale.getNiveau().equals(Niveau.CENTRAL) && vale.isDeleted() != null && !vale.isDeleted())
                .collect(Collectors.toList());
        return activites;
    }

    public Set<BesoinLigneBudgetaire> getBesoinsLigneBudgetaires(PpmActivite activite) {
        Set<BesoinLigneBudgetaire> besoins = activite.getActivite().getBesoinLignes().stream()
                .filter(x -> !x.isDeleted()).collect(Collectors.toSet());
        return besoins;
    }

    private void createExportExcelPPM(final String fileName, final HttpServletResponse response, final Long annee) {
        // Long annee =(long) 1001;
        final XSSFWorkbook workbook = createWorkbook(fileName, annee);
        buildExcelResponse(workbook, fileName, response);

    }

    private XSSFWorkbook createWorkbook(final String fileName, final Long idAnnee) {
        final XSSFWorkbook workbook = new XSSFWorkbook();
        final Sheet sheet = workbook.createSheet(fileName.replaceAll("-", " "));
        final List<PpmActivite> activites = getListePpmActivites(idAnnee);

        createHeaders(workbook);
        createRow(activites, sheet);
        appliquerStyleCellule(sheet);
        styleHeader(sheet);
        return workbook;
    }

    private void createHeaders(final XSSFWorkbook workbook) {
        final Row header = workbook.getSheetAt(0).createRow(0);
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
        header.createCell(14).setCellValue("Date buttoir");
        header.createCell(15).setCellValue("Gestionnaire de crédit");

    }

    private void createRow(final List<PpmActivite> activites, final Sheet sheet) {
        // Style de la feuille
        final Font font = sheet.getWorkbook().createFont();
        font.setFontName("Arial");

        final CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setBorderTop(BorderStyle.MEDIUM);

        int rowCount = 1;

        for (final PpmActivite activite : activites) {
            final Set<BesoinLigneBudgetaire> besoins = getBesoinsLigneBudgetaires(activite);
            final int numDebutMergeRow = rowCount;
            Double somme = 0.0;
            // SimpleDateFormat datetemp = new SimpleDateFormat("yyyy-MM-dd");
            for (final BesoinLigneBudgetaire besoin : besoins) {
                final Row refRow = sheet.createRow(rowCount);

                refRow.createCell(0).setCellValue(activite.getActivite().getCodeLignePlan());
                refRow.createCell(1).setCellValue(besoin.getLigneBudget().getBudget());
                refRow.createCell(2).setCellValue(besoin.getLigneBudget().getLigneCredit());
                // refRow.createCell(3).setCellValue(besoin.getAecp().toString());
                final String montantEstime = String.format("%,.0f", besoin.getMontantEstime());
                refRow.createCell(4).setCellValue(montantEstime);
                final String montantEngage = String.format("%,.0f", activite.getMontantDepenseEngageNonLiquide());
                refRow.createCell(5).setCellValue(montantEngage);
                final String creditDisponible = String.format("%,.0f", activite.getCreditDisponible());
                refRow.createCell(6).setCellValue(creditDisponible);
                refRow.createCell(7).setCellValue(activite.getActivite().getNaturePrestation().getLibelle());
                if (activite.getActivite().getPassation().getLibellePassation() != null) {
                    refRow.createCell(8).setCellValue(activite.getActivite().getPassation().getLibellePassation());
                } else {
                    refRow.createCell(8).setCellValue("Appel Offre");
                }
                refRow.createCell(9).setCellValue(activite.getPeriodeLancementAppel().toString());
                refRow.createCell(10).setCellValue(activite.getPeriodeRemiseOffre().toString());
                refRow.createCell(11).setCellValue(activite.getTempsNecessaireEvaluationOffre());
                refRow.createCell(12).setCellValue(activite.getDateProblableDemaragePrestation().toString());
                refRow.createCell(13).setCellValue(activite.getDelaiExecutionPrevu());
                refRow.createCell(14).setCellValue(activite.getDateButtoire().toString());
                refRow.createCell(15).setCellValue(activite.getActivite().getGestionnaireCredit());
                rowCount++;
                somme += besoin.getMontantEstime();
            }
            final Row refRowTotal = sheet.createRow(rowCount);
            // somme=somme
            final String total = String.format("Total: %,.0f", somme);
            final Cell cellTotal = refRowTotal.createCell(1);
            cellTotal.setCellValue(total);
            styleTotal(cellTotal);
            final int numFinMergeRow = rowCount;
            // if (besoins.size() > 1) {
            fusionnerCellules(numDebutMergeRow, numFinMergeRow, sheet);
            // }

            rowCount++;
        }
    }

    private void buildExcelResponse(XSSFWorkbook workbook, String fileName, HttpServletResponse response) {
        File file = getFile(fileName, ".xlsx");
        HandlerConstant.buildResponse(workbook, response, file);
    }

    private File getFile(final String fileName, final String extension) {
        return new File("./src/main/resources/imports/", fileName.concat(extension));
    }

    private void fusionnerCellules(final int firsRow, final int lastRow, final Sheet sheet) {

        final int colonnes[] = { 0, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
        for (int i = 0; i < colonnes.length; i++) {
            final CellRangeAddress mergeCell1 = new CellRangeAddress(firsRow, lastRow, colonnes[i], colonnes[i]);
            sheet.addMergedRegion(mergeCell1);
            mergedCellBorder(mergeCell1, sheet);
        }
        final CellRangeAddress mergeCell2 = new CellRangeAddress(lastRow, lastRow, 1, 4);
        sheet.addMergedRegion(mergeCell2);
        mergedCellBorder(mergeCell2, sheet);
    }

    private void appliquerStyleCellule(final Sheet sheet) {
        final CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderRight(BorderStyle.MEDIUM);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.MEDIUM);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setWrapText(true);
        for (final Row row : sheet) {
            for (final Cell cell : row) {
                cell.setCellStyle(style);
                ;
            }
            for (final Cell cell : row) {

            }
        }

    }

    public void styleHeader(final Sheet sheet) {
        final Row row = sheet.getRow(0);
        final Font font = sheet.getWorkbook().createFont();

        // color
        // Color color = (Color) (new java.awt.Color(224, 224, 224));
        // short palIndex = ((CellStyle) color).getIndex();

        row.setHeight((short) (6 * 256));
        font.setFontHeightInPoints((short) 11);
        font.setFontName("Arial");
        font.setColor(IndexedColors.WHITE.getIndex());
        font.setBold(true);
        font.setItalic(false);

        final CellStyle style = sheet.getWorkbook().createCellStyle();
        style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        // style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setLeftBorderColor(IndexedColors.WHITE.getIndex());
        style.setRightBorderColor(IndexedColors.WHITE.getIndex());
        style.setTopBorderColor(IndexedColors.WHITE.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFont(font);
        style.setWrapText(true);

        for (final Cell cell : row) {
            cell.setCellStyle(style);
        }

        sheet.setColumnWidth(0, 14 * 256);
        sheet.setColumnBreak(2);
        sheet.setColumnWidth(1, 16 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 8 * 256);
        sheet.setColumnWidth(4, 13 * 256);
        sheet.setColumnWidth(5, 13 * 256);
        sheet.setColumnWidth(6, 13 * 256);
        sheet.setColumnWidth(7, 19 * 256);
        sheet.setColumnWidth(8, 19 * 256);
        sheet.setColumnWidth(9, 11 * 256);
        sheet.setColumnWidth(10, 11 * 256);
        sheet.setColumnWidth(11, 11 * 256);
        sheet.setColumnWidth(12, 11 * 256);
        sheet.setColumnWidth(13, 11 * 256);
        sheet.setColumnWidth(14, 11 * 256);
        sheet.setColumnWidth(15, 11 * 256);

    }

    public void styleTotal(final Cell cell) {
        final CellStyle style = cell.getSheet().getWorkbook().createCellStyle();
        final Font font = cell.getSheet().getWorkbook().createFont();
        font.setFontHeightInPoints((short) 12);
        font.setFontName("Arial");
        font.setColor(IndexedColors.GREEN.getIndex());
        font.setBold(true);
        style.setFont(font);
        cell.setCellStyle(style);

    }

    private void mergedCellBorder(final CellRangeAddress address, final Sheet sheet) {
        RegionUtil.setBorderTop(BorderStyle.MEDIUM, address, sheet);
        RegionUtil.setBorderBottom(BorderStyle.MEDIUM, address, sheet);
        RegionUtil.setBorderLeft(BorderStyle.MEDIUM, address, sheet);
        RegionUtil.setBorderRight(BorderStyle.MEDIUM, address, sheet);
        // RegionUtil.
    }

    /**
     * Exportation du PPM en PDF
     *
     * @param idAnnee
     * @return
     */

    @Transactional
    public ByteArrayInputStream exportPPMToPdf(final Long idAnnee) {
        final Document document = new Document();
        document.setPageSize(PageSize.A4.rotate());
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final List<PpmActivite> activites = getListePpmActivites(idAnnee);

        try {
            String entete[] = { "Code Ligne Plan", "Budget", "Ligne crédit", "Montant estimé",
                    "Montant depenses engagées", "Crédit disponible", "Nature des prestations", "Mode de passation",
                    "Période de lancement de l'appel à concurrence", "Période de remise des offres/propositions",
                    "Temps nécessaires à l'évaluation des offres/propositions",
                    "Date probable de démarrage des prestations", "Delai d'exécution prévu (jour)", "Date buttoir",
                    "Gestionnaire de crédit" };

            PdfPTable tableEntete = new PdfPTable(3);
            tableEntete.setWidthPercentage(100);
            tableEntete.setWidths(new int[] { 3, 2, 3 });

            com.itextpdf.text.Font fontEntete = new com.itextpdf.text.Font();
            fontEntete.setFamily("Times New Roman");
            fontEntete.setSize(16);
            fontEntete.setStyle(FontStyle.BOLD.getValue());

            com.itextpdf.text.Font fontTitre1 = new com.itextpdf.text.Font();
            fontTitre1.setFamily("Times New Roman");
            fontTitre1.setSize(30);
            fontTitre1.setStyle(FontStyle.BOLD.getValue());

            com.itextpdf.text.Font fontTitre2 = new com.itextpdf.text.Font();
            fontTitre2.setFamily("Garamond");
            fontTitre2.setSize(14);
            fontTitre2.setStyle(FontStyle.BOLD.getValue());

            PdfPCell cellEntete;

            cellEntete = new PdfPCell(new Phrase(
                    "MINISTERE DE L'EDUCATION NATIONALEET DE L'ALPHABETISATION\n --------------------\nSECRETARIAT GENERAL\n --------------------\nDIRECTION DES MARCHES PUBLICS",
                    fontEntete));
            cellEntete.setVerticalAlignment(Element.ALIGN_TOP);
            cellEntete.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellEntete.setBorder(Rectangle.NO_BORDER);
            tableEntete.addCell(cellEntete);

            cellEntete = new PdfPCell(new Phrase(""));
            cellEntete.setBorder(Rectangle.NO_BORDER);
            tableEntete.addCell(cellEntete);

            cellEntete = new PdfPCell(
                    new Phrase("BURKINA FASO\n --------------\n UNITE - PROGRES - JUSTICE", fontEntete));
            cellEntete.setVerticalAlignment(Element.ALIGN_TOP);
            cellEntete.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellEntete.setBorder(Rectangle.NO_BORDER);
            tableEntete.addCell(cellEntete);

            cellEntete = new PdfPCell(new Phrase(""));
            cellEntete.setFixedHeight(50);
            cellEntete.setBorder(Rectangle.NO_BORDER);
            tableEntete.addCell(cellEntete);
            cellEntete = new PdfPCell(new Phrase(""));
            cellEntete.setBorder(Rectangle.NO_BORDER);
            cellEntete.setFixedHeight(50);
            tableEntete.addCell(cellEntete);
            cellEntete = new PdfPCell(new Phrase(""));
            cellEntete.setBorder(Rectangle.NO_BORDER);
            cellEntete.setFixedHeight(50);
            tableEntete.addCell(cellEntete);

            PdfPTable tableTitre = new PdfPTable(1);
            tableTitre.setWidthPercentage(100);
            tableTitre.setSpacingAfter(2);
            tableTitre.setSpacingBefore(2);

            PdfPCell cellTitre;

            cellTitre = new PdfPCell(new Phrase(
                    "PLAN DE PASSATION DES MARCHES DU MINISTERE DE L'EDUCATION NATIONALE, DE L'ALPHABETISATION ET DE LA PROMOTION DES LANGUES NATIONALES",
                    fontTitre1));
            cellTitre.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTitre.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTitre.setFixedHeight(300);
            // cellTitre.setBorder(Rectangle.NO_BORDER);
            tableTitre.addCell(cellTitre);

            String annee = activites.get(0).getPpm().getReferencePlan();
            cellTitre = new PdfPCell(new Phrase("Gestion : " + annee, fontTitre2));
            cellTitre.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTitre.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTitre.setFixedHeight(25);
            cellTitre.setBorder(Rectangle.NO_BORDER);
            tableTitre.addCell(cellTitre);

            final PdfPTable table = new PdfPTable(entete.length);
            table.setWidthPercentage(100);
            table.setWidths(new int[] { 3, 3, 3, 4, 4, 4, 4, 3, 3, 3, 2, 3, 2, 3, 3 });
            table.setSpacingAfter(1);
            table.setSpacingBefore(1);

            com.itextpdf.text.Font fontH1 = new com.itextpdf.text.Font();
            fontH1.setFamily("Times New Roman");
            fontH1.setSize(7);
            fontH1.setStyle(FontStyle.BOLD.getValue());

            com.itextpdf.text.Font fontH2 = new com.itextpdf.text.Font();
            fontH2.setFamily("Times New Roman");
            fontH2.setSize(7);
            fontH2.setStyle(FontStyle.NORMAL.getValue());

            PdfPCell hcell = null;

           // String reference = activites.get(0).getPpm().getReferencePlan();
            // createRowspanPdfCell(table, hcell, reference, fontH1, 16);

            for (int i = 0; i < entete.length; i++) {
                createPdfCell(table, hcell, entete[i], fontH1);
            }
            table.setHeaderRows(2);
            PdfPCell cell = null;

            Double totaux = 0.0;

            for (PpmActivite activite : activites) {
                Set<BesoinLigneBudgetaire> besoins = getBesoinsLigneBudgetaires(activite);

                int n = 0;
                Double somme = 0.0;

                for (final BesoinLigneBudgetaire besoin : besoins) {
                    if (n == 0) {

                        createRowspanPdfCell(table, cell, activite.getActivite().getCodeLignePlan(), fontH2,
                                besoins.size() + 1);

                        createPdfCell(table, cell, besoin.getLigneBudget().getBudget(), fontH2);

                        createPdfCell(table, cell, besoin.getLigneBudget().getLigneCredit(), fontH2);

                        // createPdfCell(table, cell, besoin.getAecp().toString(), fontH2);

                        final String montantEstime = String.format("%,.0f", besoin.getMontantEstime());
                        createPdfCell(table, cell, montantEstime, fontH2);

                        final String montantEngage = String.format("%,.0f",
                                activite.getMontantDepenseEngageNonLiquide());
                        createRowspanPdfCell(table, cell, montantEngage, fontH2, besoins.size() + 1);

                        final String creditDisponible = String.format("%,.0f", activite.getCreditDisponible(), fontH2);
                        createRowspanPdfCell(table, cell, creditDisponible, fontH2, besoins.size() + 1);

                        createRowspanPdfCell(table, cell, activite.getActivite().getNaturePrestation().getLibelle(),
                                fontH2, besoins.size() + 1);

                        if (activite.getActivite().getPassation().getLibellePassation() != null) {
                            createRowspanPdfCell(table, cell,
                                    activite.getActivite().getPassation().getLibellePassation(), fontH2,
                                    besoins.size() + 1);
                        } else {
                            createRowspanPdfCell(table, cell, "...", fontH2, besoins.size() + 1);
                        }

                        createRowspanPdfCell(table, cell, activite.getPeriodeLancementAppel().toString(), fontH2,
                                besoins.size() + 1);

                        createRowspanPdfCell(table, cell, activite.getPeriodeRemiseOffre().toString(), fontH2,
                                besoins.size() + 1);

                        createRowspanPdfCell(table, cell, activite.getTempsNecessaireEvaluationOffre().toString(),
                                fontH2, besoins.size() + 1);

                        createRowspanPdfCell(table, cell, activite.getDateProblableDemaragePrestation().toString(),
                                fontH2, besoins.size() + 1);

                        createRowspanPdfCell(table, cell, activite.getDelaiExecutionPrevu().toString(), fontH2,
                                besoins.size() + 1);

                       createRowspanPdfCell(table, cell, activite.getDateButtoire().toString(), fontH2,
                                besoins.size() + 1);

                        createRowspanPdfCell(table, cell, activite.getActivite().getGestionnaireCredit(), fontH2,
                                besoins.size() + 1);
                    } else {

                        createPdfCell(table, cell, besoin.getLigneBudget().getBudget(), fontH2);

                        createPdfCell(table, cell, besoin.getLigneBudget().getLigneCredit(), fontH2);

                        // createPdfCell(table, cell, besoin.getAecp().toString(), fontH2);

                        final String montantEstime = String.format("%,.0f", besoin.getMontantEstime());
                        createPdfCell(table, cell, montantEstime, fontH2);
                    }
                    n++;
                    somme += besoin.getMontantEstime();

                }
                final String total = String.format("Total: %,.0f", somme);
                createColspanPdfCell(table, cell, total, fontH1, 4);

                totaux += somme;

            }

            final String totauxMENA = String.format("Total: %,.0f", totaux);
            createColspanPdfCell(table, cell, totauxMENA, fontH1, entete.length);

            PdfWriter.getInstance(document, out);
            document.open();
            document.addTitle("Plan de passation des marchés publics");
            document.add(tableEntete);
            document.add(tableTitre);
            document.add(table);

            document.close();

        } catch (Exception e) {
            // TODO: handle exception
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void createPdfCell(PdfPTable table, PdfPCell cell, String cellContent, com.itextpdf.text.Font font) {
        cell = new PdfPCell(new Phrase(cellContent, font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    private void createRowspanPdfCell(PdfPTable table, PdfPCell cell, String cellContent, com.itextpdf.text.Font font,
            int rowspan) {
        cell = new PdfPCell(new Phrase(cellContent, font));
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setRowspan(rowspan);
        table.addCell(cell);
    }

    private void createColspanPdfCell(PdfPTable table, PdfPCell cell, String cellContent, com.itextpdf.text.Font font,
            int colspan) {
        cell = new PdfPCell(new Phrase(cellContent, font));
        cell.setColspan(colspan);
        table.addCell(cell);
    }

}
