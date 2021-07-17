package mena.gov.bf.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.JourFerier} entity.
 */
public class JourFerierDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;

    private Boolean deleted;

    private LocalDate jour;

    private Long exerciceId;

    private Integer anneeExercice;

    
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


    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


    public Integer getAnneeExercice() {
        return anneeExercice;
    }

    public void setAnneeExercice(Integer anneeExercice) {
        this.anneeExercice = anneeExercice;
    }

    public JourFerierDTO(LocalDate jour) {
        this.jour = jour;
    }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public Long getExerciceId() {
        return exerciceId;
    }

    public void setExerciceId(Long exerciceId) {
        this.exerciceId = exerciceId;
    }

    public JourFerierDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JourFerierDTO besoinDTO = (JourFerierDTO) o;
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
        return "JourFerierDTO{" +
            "id=" + id +
            ", libelle='" + libelle + '\'' +
            ", deleted=" + deleted +
            ", jour=" + jour +
            ", exerciceId=" + exerciceId +
            ", anneeExercice=" + anneeExercice +
            '}';
    }
}
