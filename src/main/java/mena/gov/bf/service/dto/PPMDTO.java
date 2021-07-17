package mena.gov.bf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.PPM} entity.
 */
public class PPMDTO implements Serializable {

    private Long id;

    @NotNull
    private String libellePpm;

    @NotNull
    private String referencePlan;

    private Boolean valid;

    private Double montantEstime;

    private Long idExercice;

    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibellePpm() {
        return libellePpm;
    }

    public void setLibellePpm(String libellePpm) {
        this.libellePpm = libellePpm;
    }

    public String getReferencePlan() {
        return referencePlan;
    }

    public void setReferencePlan(String referencePlan) {
        this.referencePlan = referencePlan;
    }

    public Boolean isValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(Long idExercice) {
        this.idExercice = idExercice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PPMDTO pPMDTO = (PPMDTO) o;
        if (pPMDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pPMDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PPMDTO{" +
            "id=" + getId() +
            ", libellePpm='" + getLibellePpm() + "'" +
            ", referencePlan='" + getReferencePlan() + "'" +
            ", valid='" + isValid() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }

    /**
     * Gets montantEstime.
     *
     * @return Value of montantEstime.
     */
    public Double getMontantEstime() {
        return montantEstime;
    }

    /**
     * Sets new montantEstime.
     *
     * @param montantEstime New value of montantEstime.
     */
    public void setMontantEstime(Double montantEstime) {
        this.montantEstime = montantEstime;
    }
}
