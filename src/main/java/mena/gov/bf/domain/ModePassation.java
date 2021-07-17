package mena.gov.bf.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ModePassation.
 */
@Entity
@Table(name = "mode_passation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ModePassation extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_passation", nullable = false)
    private String libellePassation;

    @Column(name = "abreviation")
    private String abreviation;

    @Column(name = "delais")
    private Integer delais;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "passation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Activite> activites = new HashSet<>();

    @OneToMany(mappedBy = "modePassation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ReferentielDelai> referentielDelais = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibellePassation() {
        return libellePassation;
    }

    public ModePassation libellePassation(String libellePassation) {
        this.libellePassation = libellePassation;
        return this;
    }

    public void setLibellePassation(String libellePassation) {
        this.libellePassation = libellePassation;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public ModePassation deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Activite> getActivites() {
        return activites;
    }

    public ModePassation activites(Set<Activite> activites) {
        this.activites = activites;
        return this;
    }

    public ModePassation addActivites(Activite activite) {
        this.activites.add(activite);
        activite.setPassation(this);
        return this;
    }

    public ModePassation removeActivites(Activite activite) {
        this.activites.remove(activite);
        activite.setPassation(null);
        return this;
    }

    public void setActivites(Set<Activite> activites) {
        this.activites = activites;
    }

    public Set<ReferentielDelai> getReferentielDelais() {
        return referentielDelais;
    }

    public ModePassation referentielDelais(Set<ReferentielDelai> referentielDelais) {
        this.referentielDelais = referentielDelais;
        return this;
    }

    public ModePassation addReferentielDelais(ReferentielDelai referentielDelai) {
        this.referentielDelais.add(referentielDelai);
        referentielDelai.setModePassation(this);
        return this;
    }

    public ModePassation removeReferentielDelais(ReferentielDelai referentielDelai) {
        this.referentielDelais.remove(referentielDelai);
        referentielDelai.setModePassation(null);
        return this;
    }

    public void setReferentielDelais(Set<ReferentielDelai> referentielDelais) {
        this.referentielDelais = referentielDelais;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ModePassation)) {
            return false;
        }
        return id != null && id.equals(((ModePassation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ModePassation{" +
            "id=" + getId() +
            ", libellePassation='" + getLibellePassation() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }

    /**
     * Gets delais.
     *
     * @return Value of delais.
     */
    public Integer getDelais() {
        return delais;
    }

    /**
     * Sets new delais.
     *
     * @param delais New value of delais.
     */
    public void setDelais(Integer delais) {
        this.delais = delais;
    }
}
