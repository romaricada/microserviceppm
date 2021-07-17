package mena.gov.bf.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A ExerciceBudgetaire.
 */
@Entity
@Table(name = "exercice_budgetaire")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExerciceBudgetaire extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "annee", nullable = false)
    private Integer annee;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "exercice")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Besoin> besoins = new HashSet<>();

    @Column(name = "active")
    private Boolean active;

    @Column(name = "elaborer")
    private Boolean elaborer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAnnee() {
        return annee;
    }

    public ExerciceBudgetaire annee(Integer annee) {
        this.annee = annee;
        return this;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public ExerciceBudgetaire deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<Besoin> getBesoins() {
        return besoins;
    }

    public ExerciceBudgetaire besoins(Set<Besoin> besoins) {
        this.besoins = besoins;
        return this;
    }

    public ExerciceBudgetaire addBesoins(Besoin besoin) {
        this.besoins.add(besoin);
        besoin.setExercice(this);
        return this;
    }

    public ExerciceBudgetaire removeBesoins(Besoin besoin) {
        this.besoins.remove(besoin);
        besoin.setExercice(null);
        return this;
    }

    public Boolean isElaborer() {
        return elaborer;
    }

    public void setElaborer(Boolean elaborer) {
        this.elaborer = elaborer;
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
        if (!(o instanceof ExerciceBudgetaire)) {
            return false;
        }
        return id != null && id.equals(((ExerciceBudgetaire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ExerciceBudgetaire{"
                + "id=" + id
                + ", annee=" + annee
                + ", deleted=" + deleted
                + ", besoins=" + besoins
                + ", active=" + active
                + ", elaborer=" + elaborer
                + '}';
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public ExerciceBudgetaire active(Boolean active) {
        this.active = active;
        return this;
    }

}
