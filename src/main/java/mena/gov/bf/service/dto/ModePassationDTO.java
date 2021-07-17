package mena.gov.bf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.ModePassation} entity.
 */
public class ModePassationDTO implements Serializable {

    private Long id;

    @NotNull
    private String libellePassation;

    private String abreviation;

    private Boolean deleted;

    private Integer delais;

    private List<ReferentielDelaiDTO> referenciels = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibellePassation() {
        return libellePassation;
    }

    public void setLibellePassation(String libellePassation) {
        this.libellePassation = libellePassation;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public List<ReferentielDelaiDTO> getReferenciels() {
        return referenciels;
    }

    public void setReferenciels(List<ReferentielDelaiDTO> referenciels) {
        this.referenciels = referenciels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModePassationDTO modePassationDTO = (ModePassationDTO) o;
        if (modePassationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), modePassationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ModePassationDTO{" +
            "id=" + getId() +
            ", libellePassation='" + getLibellePassation() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }

    /**
     * Sets new delais.
     *
     * @param delais New value of delais.
     */
    public void setDelais(Integer delais) {
        this.delais = delais;
    }

    /**
     * Gets delais.
     *
     * @return Value of delais.
     */
    public Integer getDelais() {
        return delais;
    }
}
