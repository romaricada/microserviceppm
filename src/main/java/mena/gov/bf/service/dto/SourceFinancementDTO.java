package mena.gov.bf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import mena.gov.bf.domain.enumeration.TypeRelationPartenaire;

/**
 * A DTO for the {@link mena.gov.bf.domain.SourceFinancement} entity.
 */
public class SourceFinancementDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String libelle;

    @NotNull
    private String codePays;

    @NotNull
    private String description;

    @NotNull
    private TypeRelationPartenaire type;

    private Boolean deleted;

    
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

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeRelationPartenaire getType() {
        return type;
    }

    public void setType(TypeRelationPartenaire type) {
        this.type = type;
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

        SourceFinancementDTO sourceFinancementDTO = (SourceFinancementDTO) o;
        if (sourceFinancementDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sourceFinancementDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SourceFinancementDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", codePays='" + getCodePays() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
