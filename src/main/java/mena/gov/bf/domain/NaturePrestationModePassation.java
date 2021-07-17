package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "nature_prestation_mode_passation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NaturePrestationModePassation extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "montant_min", nullable = false)
    private Double montantMin;

    @Column(name = "montant_max", nullable = false)
    private Double montantMax;

    @ManyToOne
    @JsonIgnoreProperties("modePassation")
    private ModePassation modePassation;

    @ManyToOne
    @JsonIgnoreProperties("naturePrestation")
    private NaturePrestation naturePrestation;

    @Column(name = "deleted")
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontantMin() {
        return montantMin;
    }

    public void setMontantMin(Double montantMin) {
        this.montantMin = montantMin;
    }

    public Double getMontantMax() {
        return montantMax;
    }

    public void setMontantMax(Double montantMax) {
        this.montantMax = montantMax;
    }

    public ModePassation getModePassation() {
        return modePassation;
    }

    public void setModePassation(ModePassation modePassation) {
        this.modePassation = modePassation;
    }

    public NaturePrestation getNaturePrestation() {
        return naturePrestation;
    }

    public void setNaturePrestation(NaturePrestation naturePrestation) {
        this.naturePrestation = naturePrestation;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "NaturePrestationModePassation{"
                + "id=" + id
                + ", montantMin=" + montantMin
                + ", montantMax=" + montantMax
                + ", modePassation=" + modePassation
                + ", naturePrestation=" + naturePrestation
                + ", deleted=" + deleted
                + '}';
    }
}
