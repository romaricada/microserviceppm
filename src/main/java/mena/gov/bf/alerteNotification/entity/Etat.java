package mena.gov.bf.alerteNotification.entity;

import java.io.Serializable;

public class Etat implements Serializable {

    private String statut;
    private Long total;
    private Double taux;

    public Etat(String statut, Long total, Double taux) {
        this.statut = statut;
        this.total = total;
        this.taux = taux;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Double getTaux() {
        return taux;
    }

    public void setTaux(Double taux) {
        this.taux = taux;
    }
}
