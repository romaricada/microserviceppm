package mena.gov.bf.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Timbre.
 */
@Entity
@Table(name = "timbre")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Timbre extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "sigle")
    private String sigle;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "pays")
    private String pays;

    @Column(name = "devise")
    private String devise;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "logo_content_type")
    private String logoContentType;

    @Column(name = "identite_ministre")
    private String identiteMinistre;

    @Column(name = "titre_ministre")
    private String titreMinistre;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Timbre code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSigle() {
        return sigle;
    }

    public Timbre sigle(String sigle) {
        this.sigle = sigle;
        return this;
    }

    public void setSigle(String sigle) {
        this.sigle = sigle;
    }

    public String getLibelle() {
        return libelle;
    }

    public Timbre libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getPays() {
        return pays;
    }

    public Timbre pays(String pays) {
        this.pays = pays;
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getDevise() {
        return devise;
    }

    public Timbre devise(String devise) {
        this.devise = devise;
        return this;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public byte[] getLogo() {
        return logo;
    }

    public Timbre logo(byte[] logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public Timbre logoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
        return this;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public String getIdentiteMinistre() {
        return identiteMinistre;
    }

    public Timbre identiteMinistre(String identiteMinistre) {
        this.identiteMinistre = identiteMinistre;
        return this;
    }

    public void setIdentiteMinistre(String identiteMinistre) {
        this.identiteMinistre = identiteMinistre;
    }

    public String getTitreMinistre() {
        return titreMinistre;
    }

    public Timbre titreMinistre(String titreMinistre) {
        this.titreMinistre = titreMinistre;
        return this;
    }

    public void setTitreMinistre(String titreMinistre) {
        this.titreMinistre = titreMinistre;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Timbre)) {
            return false;
        }
        return id != null && id.equals(((Timbre) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Timbre{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", sigle='" + getSigle() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", pays='" + getPays() + "'" +
            ", devise='" + getDevise() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logoContentType='" + getLogoContentType() + "'" +
            ", identiteMinistre='" + getIdentiteMinistre() + "'" +
            ", titreMinistre='" + getTitreMinistre() + "'" +
            "}";
    }
}
