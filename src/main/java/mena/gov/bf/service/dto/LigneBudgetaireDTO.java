package mena.gov.bf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.LigneBudgetaire} entity.
 */
public class LigneBudgetaireDTO implements Serializable {

    private Long id;

    @NotNull
    private String budget;

    private String ligneCredit;

    @NotNull
    private String section;

    @NotNull
    private String programme;

    @NotNull
    private String action;

    @NotNull
    private String chapitre;

    @NotNull
    private String activite;

    @NotNull
    private String article;

    @NotNull
    private String paragraphe;

    private Double dotInitAE;

    private Double dotInitCP;

    private Double dotCorAERestant;

    private Double dotCorCPRestant;

    private Double dotCorAE;

    private Double dotCorCP;

    private Double engage;

    private Double engageCF;

    private Double liquide;

    private Double ordonne;

    private Double vbp;

    private Double ecp;

    private Boolean deleted;

    private Long exerciceId;
    
    private Long uniteAdministrativeId;

    private Double montantEstime;

    private Integer annee;

    private BesoinLigneBudgetaireDTO besoinLigneBuget;



    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getLigneCredit() {
        return ligneCredit;
    }

    public void setLigneCredit(String ligneCredit) {
        this.ligneCredit = ligneCredit;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getExerciceId() {
        return exerciceId;
    }

    public void setExerciceId(Long exerciceId) {
        this.exerciceId = exerciceId;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getChapitre() {
        return chapitre;
    }

    public void setChapitre(String chapitre) {
        this.chapitre = chapitre;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getParagraphe() {
        return paragraphe;
    }

    public void setParagraphe(String paragraphe) {
        this.paragraphe = paragraphe;
    }

    public Double getDotInitAE() {
        return dotInitAE;
    }

    public void setDotInitAE(Double dotInitAE) {
        this.dotInitAE = dotInitAE;
    }

    public Double getDotInitCP() {
        return dotInitCP;
    }

    public void setDotInitCP(Double dotInitCP) {
        this.dotInitCP = dotInitCP;
    }

    public Double getDotCorAE() {
        return dotCorAE;
    }

    public void setDotCorAE(Double dotCorAE) {
        this.dotCorAE = dotCorAE;
    }

    public Double getDotCorCP() {
        return dotCorCP;
    }

    public void setDotCorCP(Double dotCorCP) {
        this.dotCorCP = dotCorCP;
    }

    public Double getEngage() {
        return engage;
    }

    public void setEngage(Double engage) {
        this.engage = engage;
    }

    public Double getEngageCF() {
        return engageCF;
    }

    public void setEngageCF(Double engageCF) {
        this.engageCF = engageCF;
    }

    public Double getLiquide() {
        return liquide;
    }

    public void setLiquide(Double liquide) {
        this.liquide = liquide;
    }

    public Double getOrdonne() {
        return ordonne;
    }

    public void setOrdonne(Double ordonne) {
        this.ordonne = ordonne;
    }

    public Double getVbp() {
        return vbp;
    }

    public void setVbp(Double vbp) {
        this.vbp = vbp;
    }

    public Double getEcp() {
        return ecp;
    }

    public void setEcp(Double ecp) {
        this.ecp = ecp;
    }

    public BesoinLigneBudgetaireDTO getBesoinLigneBuget() {
        return besoinLigneBuget;
    }

    public void setBesoinLigneBuget(BesoinLigneBudgetaireDTO besoinLigneBuget) {
        this.besoinLigneBuget = besoinLigneBuget;
    }

    public Double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(Double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public Long getUniteAdministrativeId() {
        return uniteAdministrativeId;
    }

    public void setUniteAdministrativeId(Long uniteAdministrativeId) {
        this.uniteAdministrativeId = uniteAdministrativeId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LigneBudgetaireDTO that = (LigneBudgetaireDTO) o;
        return
            Objects.equals(programme, that.programme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programme);
    }

    @Override
    public String toString() {
        return "LigneBudgetaireDTO{" + "id=" + id + ", budget=" + budget + ", ligneCredit=" + ligneCredit + ", section=" + section + ", programme=" + programme + ", action=" + action + ", chapitre=" + chapitre + ", activite=" + activite + ", article=" + article + ", paragraphe=" + paragraphe + ", dotInitAE=" + dotInitAE + ", dotInitCP=" + dotInitCP + ", dotCorAE=" + dotCorAE + ", dotCorCP=" + dotCorCP + ", engage=" + engage + ", engageCF=" + engageCF + ", liquide=" + liquide + ", ordonne=" + ordonne + ", vbp=" + vbp + ", ecp=" + ecp + ", deleted=" + deleted + ", exerciceId=" + exerciceId + ", uniteAdministrativeId=" + uniteAdministrativeId + ", montantEstime=" + montantEstime + ", annee=" + annee + ", besoinLigneBuget=" + besoinLigneBuget + '}';
    }


    /**
     * Sets new dotCorCPRestant.
     *
     * @param dotCorCPRestant New value of dotCorCPRestant.
     */
    public void setDotCorCPRestant(Double dotCorCPRestant) {
        this.dotCorCPRestant = dotCorCPRestant;
    }

    /**
     * Sets new dotCorAERestant.
     *
     * @param dotCorAERestant New value of dotCorAERestant.
     */
    public void setDotCorAERestant(Double dotCorAERestant) {
        this.dotCorAERestant = dotCorAERestant;
    }

    /**
     * Gets dotCorCPRestant.
     *
     * @return Value of dotCorCPRestant.
     */
    public Double getDotCorCPRestant() {
        return dotCorCPRestant;
    }

    /**
     * Gets dotCorAERestant.
     *
     * @return Value of dotCorAERestant.
     */
    public Double getDotCorAERestant() {
        return dotCorAERestant;
    }
}
