package mena.gov.bf.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A UniteAdministrative.
 */
@Entity
@Table(name = "unite_administrative")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UniteAdministrative extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @NotNull
    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "contact")
    private String contact;

    @Column(name = "identite_responsable")
    private String identiteResponsable;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "uniteAdministrative")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Besoin> besoins = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public UniteAdministrative libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public UniteAdministrative abbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
        return this;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAdresse() {
        return adresse;
    }

    public UniteAdministrative adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact() {
        return contact;
    }

    public UniteAdministrative contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIdentiteResponsable() {
        return identiteResponsable;
    }

    public UniteAdministrative identiteResponsable(String identiteResponsable) {
        this.identiteResponsable = identiteResponsable;
        return this;
    }

    public void setIdentiteResponsable(String identiteResponsable) {
        this.identiteResponsable = identiteResponsable;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public UniteAdministrative deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Besoin> getBesoins() {
        return besoins;
    }

    public UniteAdministrative besoins(Set<Besoin> besoins) {
        this.besoins = besoins;
        return this;
    }

    public UniteAdministrative addBesoins(Besoin besoin) {
        this.besoins.add(besoin);
        besoin.setUniteAdministrative(this);
        return this;
    }

    public UniteAdministrative removeBesoins(Besoin besoin) {
        this.besoins.remove(besoin);
        besoin.setUniteAdministrative(null);
        return this;
    }

    public void setBesoins(Set<Besoin> besoins) {
        this.besoins = besoins;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UniteAdministrative)) {
            return false;
        }
        return id != null && id.equals(((UniteAdministrative) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UniteAdministrative{" +
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
