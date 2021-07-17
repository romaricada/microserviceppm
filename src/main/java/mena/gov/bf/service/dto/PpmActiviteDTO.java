package mena.gov.bf.service.dto;

import mena.gov.bf.alerteNotification.entity.Etat;
import mena.gov.bf.domain.enumeration.Niveau;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.PpmActivite} entity.
 */
public class PpmActiviteDTO implements Serializable {

    private Long id;


    private Double montantDepenseEngageNonLiquide;

    @NotNull
    private Double creditDisponible;

    @NotNull
    private LocalDate periodeLancementAppel;

    @NotNull
    private LocalDate periodeRemiseOffre;

    @NotNull
    private Integer tempsNecessaireEvaluationOffre;

    @NotNull
    private LocalDate dateProblableDemaragePrestation;

    @NotNull
    private Integer delaiExecutionPrevu;

    @NotNull
    private LocalDate dateButtoire;

    private Long exerciceId;

    private Niveau niveau;

    private Boolean deleted;

    private Boolean report;

    private Long ppmId;

    private Long activiteId;

    private Long sourceFinancementId;

    private List<Etat> etats;

    private Double tauxRealisation;

    private String libelleActivite;

    private String codeLignePlanActivite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public Double getMontantDepenseEngageNonLiquide() {
        return montantDepenseEngageNonLiquide;
    }

    public void setMontantDepenseEngageNonLiquide(Double montantDepenseEngageNonLiquide) {
        this.montantDepenseEngageNonLiquide = montantDepenseEngageNonLiquide;
    }

    public Double getCreditDisponible() {
        return creditDisponible;
    }

    public void setCreditDisponible(Double creditDisponible) {
        this.creditDisponible = creditDisponible;
    }

    public LocalDate getPeriodeLancementAppel() {
        return periodeLancementAppel;
    }

    public void setPeriodeLancementAppel(LocalDate periodeLancementAppel) {
        this.periodeLancementAppel = periodeLancementAppel;
    }

    public LocalDate getPeriodeRemiseOffre() {
        return periodeRemiseOffre;
    }

    public void setPeriodeRemiseOffre(LocalDate periodeRemiseOffre) {
        this.periodeRemiseOffre = periodeRemiseOffre;
    }

    public Integer getTempsNecessaireEvaluationOffre() {
        return tempsNecessaireEvaluationOffre;
    }

    public void setTempsNecessaireEvaluationOffre(Integer tempsNecessaireEvaluationOffre) {
        this.tempsNecessaireEvaluationOffre = tempsNecessaireEvaluationOffre;
    }

    public LocalDate getDateProblableDemaragePrestation() {
        return dateProblableDemaragePrestation;
    }

    public void setDateProblableDemaragePrestation(LocalDate dateProblableDemaragePrestation) {
        this.dateProblableDemaragePrestation = dateProblableDemaragePrestation;
    }

    public Integer getDelaiExecutionPrevu() {
        return delaiExecutionPrevu;
    }

    public void setDelaiExecutionPrevu(Integer delaiExecutionPrevu) {
        this.delaiExecutionPrevu = delaiExecutionPrevu;
    }

    public LocalDate getDateButtoire() {
        return dateButtoire;
    }

    public void setDateButtoire(LocalDate dateButtoire) {
        this.dateButtoire = dateButtoire;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getPpmId() {
        return ppmId;
    }

    public void setPpmId(Long pPMId) {
        this.ppmId = pPMId;
    }

    public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
    }

    public Long getSourceFinancementId() {
        return sourceFinancementId;
    }

    /**
     * Getter for property 'exerciceId'.
     *
     * @return Value for property 'exerciceId'.
     */
    public Long getExerciceId() {
        return exerciceId;
    }

    /**
     * Setter for property 'exerciceId'.
     *
     * @param exerciceId Value to set for property 'exerciceId'.
     */
    public void setExerciceId(Long exerciceId) {
        this.exerciceId = exerciceId;
    }

    public void setSourceFinancementId(Long sourceFinancementId) {
        this.sourceFinancementId = sourceFinancementId;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public List<Etat> getEtats() {
        return etats;
    }

    public void setEtats(List<Etat> etats) {
        this.etats = etats;
    }

    public Double getTauxRealisation() {
        return tauxRealisation;
    }

    public void setTauxRealisation(Double tauxRealisation) {
        this.tauxRealisation = tauxRealisation;
    }

    public String getLibelleActivite() {
        return libelleActivite;
    }

    public void setLibelleActivite(String libelleActivite) {
        this.libelleActivite = libelleActivite;
    }

    public String getCodeLignePlanActivite() {
        return codeLignePlanActivite;
    }

    public void setCodeLignePlanActivite(String codeLignePlanActivite) {
        this.codeLignePlanActivite = codeLignePlanActivite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PpmActiviteDTO ppmActiviteDTO = (PpmActiviteDTO) o;
        if (ppmActiviteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ppmActiviteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PpmActiviteDTO{" +
            "id=" + getId() +
            ", montantDepenseEngageNonLiquide=" + getMontantDepenseEngageNonLiquide() +
            ", creditDisponible=" + getCreditDisponible() +
            ", periodeLancementAppel='" + getPeriodeLancementAppel() + "'" +
            ", periodeRemiseOffre='" + getPeriodeRemiseOffre() + "'" +
            ", tempsNecessaireEvaluationOffre=" + getTempsNecessaireEvaluationOffre() +
            ", dateProblableDemaragePrestation='" + getDateProblableDemaragePrestation() + "'" +
            ", delaiExecutionPrevu=" + getDelaiExecutionPrevu() +
            ", dateButtoire='" + getDateButtoire() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", ppmId=" + getPpmId() +
            ", activiteId=" + getActiviteId() +
            ", sourceFinancementId=" + getSourceFinancementId() +
            "}";
    }
}
