package mena.gov.bf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.ExerciceBudgetaire} entity.
 */
public class ExerciceBudgetaireDTO implements Serializable {
    
    private Long id;

    @NotNull
    private Integer annee;

    private Boolean deleted;

    private Boolean active;

    private Boolean elaborer;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isElaborer() {
        return elaborer;
    }

    public void setElaborer(Boolean elaborer) {
        this.elaborer = elaborer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExerciceBudgetaireDTO exerciceBudgetaireDTO = (ExerciceBudgetaireDTO) o;
        if (exerciceBudgetaireDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), exerciceBudgetaireDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ExerciceBudgetaireDTO{" +
            "id=" + getId() +
            ", annee=" + getAnnee() +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
