package mena.gov.bf.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link mena.gov.bf.domain.Timbre} entity.
 */
public class TimbreDTO implements Serializable {
    
    private Long id;

    private String code;

    private String sigle;

    private String libelle;

    private String pays;

    private String devise;

    @Lob
    private byte[] logo;

    private String logoContentType;
    private String identiteMinistre;

    private String titreMinistre;

    
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

    public String getSigle() {
        return sigle;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public String getIdentiteMinistre() {
        return identiteMinistre;
    }

    public void setIdentiteMinistre(String identiteMinistre) {
        this.identiteMinistre = identiteMinistre;
    }

    public String getTitreMinistre() {
        return titreMinistre;
    }

    public void setTitreMinistre(String titreMinistre) {
        this.titreMinistre = titreMinistre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TimbreDTO timbreDTO = (TimbreDTO) o;
        if (timbreDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), timbreDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TimbreDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", sigle='" + getSigle() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", pays='" + getPays() + "'" +
            ", devise='" + getDevise() + "'" +
            ", logo='" + getLogo() + "'" +
            ", identiteMinistre='" + getIdentiteMinistre() + "'" +
            ", titreMinistre='" + getTitreMinistre() + "'" +
            "}";
    }
}
