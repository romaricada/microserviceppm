package mena.gov.bf.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A PPM.
 */
@Entity
@Table(name = "ppm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PPM extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle_ppm", nullable = false)
    private String libellePpm;

    @NotNull
    @Column(name = "reference_plan", nullable = false)
    private String referencePlan;

    @Column(name = "montant_estime")
    private Double montantEstime;

    @Column(name = "valid")
    private Boolean valid;

    @Column(name = "id_exercice")
    private Long idExercice;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "ppm")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PpmActivite> ppmActivites = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibellePpm() {
        return libellePpm;
    }

    public PPM libellePpm(String libellePpm) {
        this.libellePpm = libellePpm;
        return this;
    }

    public void setLibellePpm(String libellePpm) {
        this.libellePpm = libellePpm;
    }

    public String getReferencePlan() {
        return referencePlan;
    }

    public PPM referencePlan(String referencePlan) {
        this.referencePlan = referencePlan;
        return this;
    }

    public void setReferencePlan(String referencePlan) {
        this.referencePlan = referencePlan;
    }

    public Boolean isValid() {
        return valid;
    }

    public PPM valid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public PPM deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<PpmActivite> getPpmActivites() {
        return ppmActivites;
    }

    public PPM ppmActivites(Set<PpmActivite> ppmActivites) {
        this.ppmActivites = ppmActivites;
        return this;
    }

    public PPM addPpmActivites(PpmActivite ppmActivite) {
        this.ppmActivites.add(ppmActivite);
        ppmActivite.setPpm(this);
        return this;
    }

    public PPM removePpmActivites(PpmActivite ppmActivite) {
        this.ppmActivites.remove(ppmActivite);
        ppmActivite.setPpm(null);
        return this;
    }

    public void setPpmActivites(Set<PpmActivite> ppmActivites) {
        this.ppmActivites = ppmActivites;
    }

    public Long getIdExercice() {
        return idExercice;
    }

    public void setIdExercice(Long idExercice) {
        this.idExercice = idExercice;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PPM)) {
            return false;
        }
        return id != null && id.equals(((PPM) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PPM{"
                + "id=" + getId()
                + ", libellePpm='" + getLibellePpm() + "'"
                + ", referencePlan='" + getReferencePlan() + "'"
                + ", valid='" + isValid() + "'"
                + ", deleted='" + isDeleted() + "'"
                + "}";
    }

    /**
     * Sets new montantEstime.
     *
     * @param montantEstime New value of montantEstime.
     */
    public void setMontantEstime(Double montantEstime) {
        this.montantEstime = montantEstime;
    }

    /**
     * Gets montantEstime.
     *
     * @return Value of montantEstime.
     */
    public Double getMontantEstime() {
        return montantEstime;
    }
}
