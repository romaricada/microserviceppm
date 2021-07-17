package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A jourFeriers.
 */
@Entity
@Table(name = "jour_ferier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JourFerier extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "jour")
    private LocalDate jour;

    @ManyToOne
    @JsonIgnoreProperties("jourFeriers")
    private ExerciceBudgetaire exercice;


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

    public JourFerier libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    public Boolean isDeleted() {
        return deleted;
    }

    public JourFerier deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }



    public ExerciceBudgetaire getExercice() {
        return exercice;
    }

    public JourFerier exercice(ExerciceBudgetaire exerciceBudgetaire) {
        this.exercice = exerciceBudgetaire;
        return this;
    }

    public void setExercice(ExerciceBudgetaire exerciceBudgetaire) {
        this.exercice = exerciceBudgetaire;
    }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JourFerier)) {
            return false;
        }
        return id != null && id.equals(((JourFerier) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "JourFerier{" +
            "id=" + id +
            ", libelle='" + libelle + '\'' +
            ", deleted=" + deleted +
            ", jour=" + jour +
            ", exercice=" + exercice +
            '}';
    }
}
