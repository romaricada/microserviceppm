package mena.gov.bf.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "norme_reference")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NormeReference extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "norme")
    private Integer norme;

    @Column(name = "referentiel")
    private Integer referentiel;

    @Column(name = "norme_min")
    private Integer normeMin;

    @Column(name = "referentiel_min")
    private Integer referentielMin;

    @Column(name = "norme_max")
    private Integer normeMax;

    @Column(name = "referentiel_max")
    private Integer referentielMax;

    @Column(name = "norme_ouvrable")
    private Boolean normeOuvrable;

    @Column(name = "referentiel_ouvrable")
    private Boolean referentielOuvrable;

    @Column(name = "intervalle")
    private Boolean intervalle;

    @Column(name = "deleted")
    private Boolean deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNorme() {
        return norme;
    }

    public void setNorme(Integer norme) {
        this.norme = norme;
    }

    public Integer getReferentiel() {
        return referentiel;
    }

    public void setReferentiel(Integer referentiel) {
        this.referentiel = referentiel;
    }

    public Integer getNormeMin() {
        return normeMin;
    }

    public void setNormeMin(Integer normeMin) {
        this.normeMin = normeMin;
    }

    public Integer getReferentielMin() {
        return referentielMin;
    }

    public void setReferentielMin(Integer referentielMin) {
        this.referentielMin = referentielMin;
    }

    public Integer getNormeMax() {
        return normeMax;
    }

    public void setNormeMax(Integer normeMax) {
        this.normeMax = normeMax;
    }

    public Integer getReferentielMax() {
        return referentielMax;
    }

    public void setReferentielMax(Integer referentielMax) {
        this.referentielMax = referentielMax;
    }

    public Boolean isNormeOuvrable() {
        return normeOuvrable;
    }

    public void setNormeOuvrable(Boolean normeOuvrable) {
        this.normeOuvrable = normeOuvrable;
    }

    public Boolean isReferentielOuvrable() {
        return referentielOuvrable;
    }

    public void setReferentielOuvrable(Boolean referentielOuvrable) {
        this.referentielOuvrable = referentielOuvrable;
    }

    public Boolean isIntervalle() {
        return intervalle;
    }

    public void setIntervalle(Boolean intervalle) {
        this.intervalle = intervalle;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NormeReference)) return false;
        NormeReference that = (NormeReference) o;
        return getId().equals(that.getId()) &&
            Objects.equals(getNorme(), that.getNorme()) &&
            Objects.equals(getReferentiel(), that.getReferentiel()) &&
            Objects.equals(getNormeMin(), that.getNormeMin()) &&
            Objects.equals(getReferentielMin(), that.getReferentielMin()) &&
            Objects.equals(getNormeMax(), that.getNormeMax()) &&
            Objects.equals(getReferentielMax(), that.getReferentielMax()) &&
            Objects.equals(normeOuvrable, that.normeOuvrable) &&
            Objects.equals(referentielOuvrable, that.referentielOuvrable) &&
            Objects.equals(intervalle, that.intervalle) &&
            Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNorme(), getReferentiel(), getNormeMin(), getReferentielMin(), getNormeMax(), getReferentielMax(), normeOuvrable, referentielOuvrable, intervalle, deleted);
    }

    @Override
    public String toString() {
        return "NormeReference{" +
            "id=" + id +
            ", norme=" + norme +
            ", referentiel=" + referentiel +
            ", normeMin=" + normeMin +
            ", referentielMin=" + referentielMin +
            ", normeMax=" + normeMax +
            ", referentielMax=" + referentielMax +
            ", normeOuvrable=" + normeOuvrable +
            ", referentielOuvrable=" + referentielOuvrable +
            ", intervalle=" + intervalle +
            ", deleted=" + deleted +
            '}';
    }
}
