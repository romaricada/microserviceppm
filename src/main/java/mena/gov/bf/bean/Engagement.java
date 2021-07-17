package mena.gov.bf.bean;

import java.time.LocalDate;

public class Engagement {

    private Long id;

    private Double montantEngage;

    private LocalDate date;

    private Long contratId;

    private Long ligneBudgetaireId;

    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontantEngage() {
        return montantEngage;
    }

    public void setMontantEngage(Double montantEngage) {
        this.montantEngage = montantEngage;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getContratId() {
        return contratId;
    }

    public void setContratId(Long contratId) {
        this.contratId = contratId;
    }

    public Long getLigneBudgetaireId() {
        return ligneBudgetaireId;
    }

    public void setLigneBudgetaireId(Long ligneBudgetaireId) {
        this.ligneBudgetaireId = ligneBudgetaireId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
