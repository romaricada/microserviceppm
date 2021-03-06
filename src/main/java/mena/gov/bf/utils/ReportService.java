/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mena.gov.bf.utils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ReportService {
    private static final long serialVersionUID = 1L;
    private String etatCompile; //Fichier compilé de l'état
    private JasperPrint jasperPrint;
    private HashMap parametres; //Paramètres

      @Autowired
    private ServletContext servletContext;

    public String getEtatCompile() {
        return etatCompile;
    }

    public void setEtatCompile(String etatCompile) {
        this.etatCompile = etatCompile;
    }

    public JasperPrint getJasperPrint() {
        return jasperPrint;
    }

    public void setJasperPrint(JasperPrint jasperPrint) {
        this.jasperPrint = jasperPrint;
    }

    public HashMap getParametres() {
        return parametres;
    }

    public void setParametres(HashMap parametres) {
        this.parametres = parametres;
    }

    /**
     *
     * @param etatCompile
     * @param datasource
     * @param parametres
     * @return
     */
     public ResponseEntity<byte[]> createPdfReport(InputStream etatCompile, JRBeanCollectionDataSource datasource, HashMap<String,? super Object> parametres) {
         try {
             if (datasource == null) {
                 jasperPrint = JasperFillManager.fillReport(etatCompile, parametres);
             } else {
                 jasperPrint = JasperFillManager.fillReport(etatCompile, parametres, datasource);
             }

             byte[] contents = JasperExportManager.exportReportToPdf(jasperPrint);
             HttpHeaders headers = new HttpHeaders();
             headers.setContentType( MediaType.parseMediaType("application/pdf"));
             ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
             return response;
         } catch (JRException ex) {
             Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return null;
     }

    public ResponseEntity<byte[]> imprimerPdf(Resource resource, JRBeanCollectionDataSource dataSource, HashMap<String, Object> parametres) {
        try {
            return this.createPdfReport( resource.getInputStream(), dataSource, parametres );
        } catch (IOException e) {
            System.out.println( e.getMessage() );
        }
        return null;
    }
}
