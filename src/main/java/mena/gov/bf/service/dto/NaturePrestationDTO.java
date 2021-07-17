package mena.gov.bf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.NaturePrestation} entity.
 */
public class NaturePrestationDTO implements Serializable {

    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle; 

    private Boolean deleted;

    private List<NaturePrestationModePassationDTO> naturePrestationModePassations = new ArrayList<>();

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NaturePrestationDTO naturePrestationDTO = (NaturePrestationDTO) o;
        if (naturePrestationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), naturePrestationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NaturePrestationDTO{" + "id=" + getId() + ", code='" + getCode() + "'" + ", libelle='" + getLibelle()
                + "'" +", natModePre='" + getNaturePrestationModePassations() + ", deleted='" + isDeleted() + "'" + "}";
    }

    public List<NaturePrestationModePassationDTO> getNaturePrestationModePassations() {
        return naturePrestationModePassations;
    }

    public void setNaturePrestationModePassations(
            List<NaturePrestationModePassationDTO> naturePrestationModePassations) {
        this.naturePrestationModePassations = naturePrestationModePassations;
    }
}
