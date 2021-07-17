package mena.gov.bf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.UniteAdministrative} entity.
 */
public class UniteAdministrativeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private String abbreviation;

    private String adresse;

    private String contact;

    private String identiteResponsable;

    private Boolean deleted;

    
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIdentiteResponsable() {
        return identiteResponsable;
    }

    public void setIdentiteResponsable(String identiteResponsable) {
        this.identiteResponsable = identiteResponsable;
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

        UniteAdministrativeDTO uniteAdministrativeDTO = (UniteAdministrativeDTO) o;
        if (uniteAdministrativeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uniteAdministrativeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UniteAdministrativeDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", abbreviation='" + getAbbreviation() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", contact='" + getContact() + "'" +
            ", identiteResponsable='" + getIdentiteResponsable() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
