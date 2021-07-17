package mena.gov.bf.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A NaturePrestation.
 */
@Entity
@Table(name = "nature_prestation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NaturePrestation extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "naturePrestation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Activite> activites = new HashSet<>();

    @OneToMany(mappedBy = "naturePrestation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NaturePrestationModePassation> naturePrestationModePassations = new HashSet<>();

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

    public NaturePrestation code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public NaturePrestation libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public NaturePrestation deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Activite> getActivites() {
        return activites;
    }

    public NaturePrestation activites(Set<Activite> activites) {
        this.activites = activites;
        return this;
    }

    public NaturePrestation addActivites(Activite activite) {
        this.activites.add(activite);
        activite.setNaturePrestation(this);
        return this;
    }

    public NaturePrestation removeActivites(Activite activite) {
        this.activites.remove(activite);
        activite.setNaturePrestation(null);
        return this;
    }

    public void setActivites(Set<Activite> activites) {
        this.activites = activites;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NaturePrestation)) {
            return false;
        }
        return id != null && id.equals(((NaturePrestation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NaturePrestation{"
                + "id=" + getId()
                + ", code='" + getCode() + "'"
                + ", libelle='" + getLibelle() + "'"
                + ", deleted='" + isDeleted() + "'"
                + "}";
    }
}
