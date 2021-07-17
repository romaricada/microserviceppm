package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ReferentielDelai.
 */
@Entity
@Table(name = "referentiel_delai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReferentielDelai extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "observation")
    private String observation;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne
    @JsonIgnoreProperties("referentielDelais")
    private Etape etape;

    @ManyToOne
    @JsonIgnoreProperties("referentielDelais")
    private Acteur acteur;

    @ManyToOne
    @JsonIgnoreProperties("referentielDelais")
    private ModePassation modePassation;

    @ManyToOne
    @JsonIgnoreProperties("referentielDelais")
    private NormeReference normeReference;


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservation() {
        return observation;
    }

    public ReferentielDelai observation(String observation) {
        this.observation = observation;
        return this;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public ReferentielDelai deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Etape getEtape() {
        return etape;
    }

    public ReferentielDelai etape(Etape etape) {
        this.etape = etape;
        return this;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    public Acteur getActeur() {
        return acteur;
    }

    public ReferentielDelai acteur(Acteur acteur) {
        this.acteur = acteur;
        return this;
    }

    public void setActeur(Acteur acteur) {
        this.acteur = acteur;
    }

    public ModePassation getModePassation() {
        return modePassation;
    }

    public ReferentielDelai modePassation(ModePassation modePassation) {
        this.modePassation = modePassation;
        return this;
    }

    public void setModePassation(ModePassation modePassation) {
        this.modePassation = modePassation;
    }


    public NormeReference getNormeReference() {
        return normeReference;
    }

    public void setNormeReference(NormeReference normeReference) {
        this.normeReference = normeReference;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReferentielDelai)) {
            return false;
        }
        return id != null && id.equals(((ReferentielDelai) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ReferentielDelai{" +
            "id=" + id +
            ", observation='" + observation + '\'' +
            ", deleted=" + deleted +
            ", etape=" + etape +
            ", acteur=" + acteur +
            ", modePassation=" + modePassation +
            ", normeReference=" + normeReference +
            '}';
    }
}
