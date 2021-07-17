package mena.gov.bf.bean;

import mena.gov.bf.bean.enummeration.Etat;
import mena.gov.bf.bean.enummeration.TypeDelai;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


public class TacheEtape implements Serializable {

    private Long id;

    private Etat etat;

    private Long etapeActivitePpmId;

    private String etapeLibelle;

    @NotNull
    private Boolean deleted;

    private Long tacheId;

    private Boolean estRealise;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private TypeDelai typeDelai;

    private Long delai;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Long getEtapeActivitePpmId() {
        return etapeActivitePpmId;
    }

    public void setEtapeActivitePpmId(Long etapeActivitePpmId) {
        this.etapeActivitePpmId = etapeActivitePpmId;
    }

    public String getEtapeLibelle() {
        return etapeLibelle;
    }

    public void setEtapeLibelle(String etapeLibelle) {
        this.etapeLibelle = etapeLibelle;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getTacheId() {
        return tacheId;
    }

    public void setTacheId(Long tacheId) {
        this.tacheId = tacheId;
    }

    public Boolean getEstRealise() {
        return estRealise;
    }

    public void setEstRealise(Boolean estRealise) {
        this.estRealise = estRealise;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public TypeDelai getTypeDelai() {
        return typeDelai;
    }

    public void setTypeDelai(TypeDelai typeDelai) {
        this.typeDelai = typeDelai;
    }

    public Long getDelai() {
        return delai;
    }

    public void setDelai(Long delai) {
        this.delai = delai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TacheEtape tacheEtapeDTO = (TacheEtape) o;
        if (tacheEtapeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tacheEtapeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TacheEtape{" +
            "id=" + id +
            ", etat=" + etat +
            ", etapeActivitePpmId=" + etapeActivitePpmId +
            ", etapeLibelle='" + etapeLibelle + '\'' +
            ", deleted=" + deleted +
            ", tacheId=" + tacheId +
            ", estRealise=" + estRealise +
            ", dateDebut=" + dateDebut +
            ", dateFin=" + dateFin +
            ", typeDelai=" + typeDelai +
            ", delai=" + delai +
            '}';
    }
}
