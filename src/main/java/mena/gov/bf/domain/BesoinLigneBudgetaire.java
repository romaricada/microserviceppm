package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * A BesoinLigneBudgetaire.
 */
@Entity
@Table(name = "besoin_ligne_budgetaire")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BesoinLigneBudgetaire extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "aecp", nullable = false)
    private Boolean aecp;

    @Column(name = "montant_estime")
    private Double montantEstime;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne
    @JsonIgnoreProperties("besoins")
    private LigneBudgetaire ligneBudget;

    @ManyToOne
    @JsonIgnoreProperties("besoinLignes")
    private Besoin besoin;

    @ManyToOne
    @JsonIgnoreProperties("besoinLignes")
    private Activite activite;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAecp() {
        return aecp;
    }

    public void setAecp(Boolean aecp) {
        this.aecp = aecp;
    }

    public Double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(Double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public LigneBudgetaire getLigneBudget() {
        return ligneBudget;
    }

    public void setLigneBudget(LigneBudgetaire ligneBudget) {
        this.ligneBudget = ligneBudget;
    }

    public Besoin getBesoin() {
        return besoin;
    }

    public void setBesoin(Besoin besoin) {
        this.besoin = besoin;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public BesoinLigneBudgetaire deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BesoinLigneBudgetaire)) {
            return false;
        }
        return id != null && id.equals(((BesoinLigneBudgetaire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BesoinLigneBudgetaire{" +
            "id=" + id +
            ", aecp=" + aecp +
            ", montantEstime=" + montantEstime +
            ", deleted=" + deleted +
            ", ligneBudget=" + ligneBudget +
            ", besoin=" + besoin +
            ", activite=" + activite +
            '}';
    }
}
