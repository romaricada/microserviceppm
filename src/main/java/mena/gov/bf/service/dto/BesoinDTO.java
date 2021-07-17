package mena.gov.bf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.Besoin} entity.
 */
public class BesoinDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;

    private Integer quantite;

    private Boolean deleted;

    private Boolean used;

    private Integer nombreLot;

    private Double montantEstime;

    private Long exerciceId;

    private Long uniteAdministrativeId;

    private Long naturePrestationId;

    private List<LigneBudgetaireDTO> ligneBudgetaires = new ArrayList<>();

    private List<BesoinLigneBudgetaireDTO> besoinLignes = new ArrayList<>();

    private Integer anneeExercice;

    private String libelleUniteAdministrative;

    private String libellenaturePrestation;

    @NotNull
    private LocalDate dateDebut;

    @NotNull
    private LocalDate dateFin;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
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

    public void setExerciceId(Long exerciceBudgetaireId) {
        this.exerciceId = exerciceBudgetaireId;
    }

    public Long getUniteAdministrativeId() {
        return uniteAdministrativeId;
    }

    public void setUniteAdministrativeId(Long uniteAdministrativeId) {
        this.uniteAdministrativeId = uniteAdministrativeId;
    }

    public Long getNaturePrestationId() {
        return naturePrestationId;
    }

    public void setNaturePrestationId(Long naturePrestationId) {
        this.naturePrestationId = naturePrestationId;
    }

    public List<LigneBudgetaireDTO> getLigneBudgetaires() {
        return ligneBudgetaires;
    }

    public void setLigneBudgetaires(List<LigneBudgetaireDTO> ligneBudgetaires) {
        this.ligneBudgetaires = ligneBudgetaires;
    }

    public Integer getAnneeExercice() {
        return anneeExercice;
    }

    public void setAnneeExercice(Integer anneeExercice) {
        this.anneeExercice = anneeExercice;
    }

    public String getLibelleUniteAdministrative() {
        return libelleUniteAdministrative;
    }

    public void setLibelleUniteAdministrative(String libelleUniteAdministrative) {
        this.libelleUniteAdministrative = libelleUniteAdministrative;
    }

    public String getLibellenaturePrestation() {
        return libellenaturePrestation;
    }

    public void setLibellenaturePrestation(String libellenaturePrestation) {
        this.libellenaturePrestation = libellenaturePrestation;
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

    public Boolean isUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Integer getNombreLot() {
        return nombreLot;
    }

    public void setNombreLot(Integer nombreLot) {
        this.nombreLot = nombreLot;
    }

    public Double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(Double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public List<BesoinLigneBudgetaireDTO> getBesoinLignes() {
        return besoinLignes;
    }

    public void setBesoinLignes(List<BesoinLigneBudgetaireDTO> besoinLignes) {
        this.besoinLignes = besoinLignes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BesoinDTO besoinDTO = (BesoinDTO) o;
        if (besoinDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), besoinDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BesoinDTO{" +
            "id=" + id +
            ", libelle='" + libelle + '\'' +
            ", quantite=" + quantite +
            ", deleted=" + deleted +
            ", used=" + used +
            ", nombreLot=" + nombreLot +
            ", montantEstime=" + montantEstime +
            ", exerciceId=" + exerciceId +
            ", uniteAdministrativeId=" + uniteAdministrativeId +
            ", naturePrestationId=" + naturePrestationId +
            ", ligneBudgetaires=" + ligneBudgetaires +
            ", besoinLignes=" + besoinLignes +
            ", anneeExercice=" + anneeExercice +
            ", libelleUniteAdministrative='" + libelleUniteAdministrative + '\'' +
            ", libellenaturePrestation='" + libellenaturePrestation + '\'' +
            ", dateDebut=" + dateDebut +
            ", dateFin=" + dateFin +
            '}';
    }
}
