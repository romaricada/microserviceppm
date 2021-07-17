package mena.gov.bf.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class DateCalcule implements Serializable {

    private String libelle;

    private LocalDate date;

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DateCalcule{" +
            "libelle='" + libelle + '\'' +
            ", date=" + date +
            '}';
    }
}
