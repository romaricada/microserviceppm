package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Etape.
 */
@Entity
@Table(name = "etape")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Etape extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @NotNull
    @Column(name = "ordre", nullable = false)
    private Integer ordre;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "etape")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ReferentielDelai> referentielDelais = new HashSet<>();

    @OneToMany(mappedBy = "etape")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EtapeActivitePpm> etapeActivitePpms = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("etapes")
    private ModePassation modePassation;

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

    public Etape libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Etape deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<ReferentielDelai> getReferentielDelais() {
        return referentielDelais;
    }

    public Etape referentielDelais(Set<ReferentielDelai> referentielDelais) {
        this.referentielDelais = referentielDelais;
        return this;
    }

    public Etape addReferentielDelais(ReferentielDelai referentielDelai) {
        this.referentielDelais.add(referentielDelai);
        referentielDelai.setEtape(this);
        return this;
    }

    /**
     * Getter for property 'ordre'.
     *
     * @return Value for property 'ordre'.
     */
    public Integer getOrdre() {
        return ordre;
    }

    /**
     * Setter for property 'ordre'.
     *
     * @param ordre Value to set for property 'ordre'.
     */
    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    public Etape removeReferentielDelais(ReferentielDelai referentielDelai) {
        this.referentielDelais.remove(referentielDelai);
        referentielDelai.setEtape(null);
        return this;
    }

    public void setReferentielDelais(Set<ReferentielDelai> referentielDelais) {
        this.referentielDelais = referentielDelais;
    }

    public Set<EtapeActivitePpm> getEtapeActivitePpms() {
        return etapeActivitePpms;
    }

    public Etape etapeActivitePpms(Set<EtapeActivitePpm> etapeActivitePpms) {
        this.etapeActivitePpms = etapeActivitePpms;
        return this;
    }

    public Etape addEtapeActivitePpms(EtapeActivitePpm etapeActivitePpm) {
        this.etapeActivitePpms.add(etapeActivitePpm);
        etapeActivitePpm.setEtape(this);
        return this;
    }

    public Etape removeEtapeActivitePpms(EtapeActivitePpm etapeActivitePpm) {
        this.etapeActivitePpms.remove(etapeActivitePpm);
        etapeActivitePpm.setEtape(null);
        return this;
    }

    public void setEtapeActivitePpms(Set<EtapeActivitePpm> etapeActivitePpms) {
        this.etapeActivitePpms = etapeActivitePpms;
    }

    public ModePassation getModePassation() {
        return modePassation;
    }

    public void setModePassation(ModePassation modePassation) {
        this.modePassation = modePassation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Etape)) {
            return false;
        }
        return id != null && id.equals(((Etape) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Etape{"
                + "id=" + getId()
                + ", libelle='" + getLibelle() + "'"
                + ", deleted='" + isDeleted() + "'"
                + "}";
    }
}
