package mena.gov.bf.data.exportPPM;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import mena.gov.bf.domain.ReferentielDelai;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExportPpmService1 extends AbstractXlsView {
    @Transactional
    public void buildExcelDocument(final Map<String, Object> model, final Workbook workbook,
            final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        // change the file name
        response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");
        @SuppressWarnings("unchecked")
        final List<ReferentielDelai> refs = (List<ReferentielDelai>) model.get("refs");

        // create excel xls sheet
        final Sheet sheet = workbook.createSheet("PPM MENA-PLN");
        sheet.setDefaultColumnWidth(30);

        // create style for header cells
        final CellStyle style = workbook.createCellStyle();
        final Font font = workbook.createFont();
        font.setFontName("Arial");
        // style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        font.setBold(true);
        // font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);

        // create header row
        final Row header = sheet.createRow(0);
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

        int rowCount = 1;

        for (final ReferentielDelai ref : refs) {
            final Row refRow = sheet.createRow(rowCount++);
            refRow.createCell(0).setCellValue(ref.getId());
            //refRow.createCell(1).setCellValue(ref.getNorme());
            refRow.createCell(2).setCellValue(ref.getObservation());
            refRow.createCell(3).setCellValue(ref.getId());
            //refRow.createCell(4).setCellValue(ref.getNorme());
            refRow.createCell(5).setCellValue(ref.getObservation());
            refRow.createCell(6).setCellValue(ref.getId());
            //refRow.createCell(7).setCellValue(ref.getNorme());
            refRow.createCell(8).setCellValue(ref.getObservation());
            refRow.createCell(9).setCellValue(ref.getId());
            //refRow.createCell(10).setCellValue(ref.getNorme());
            refRow.createCell(11).setCellValue(ref.getObservation());
            refRow.createCell(12).setCellValue(ref.getId());
            //refRow.createCell(13).setCellValue(ref.getNorme());
            refRow.createCell(14).setCellValue(ref.getObservation());
            refRow.createCell(15).setCellValue(ref.getId());

        }

    }
}
