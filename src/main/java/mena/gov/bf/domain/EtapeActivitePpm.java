package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A EtapeActivitePpm.
 */
@Entity
@Table(name = "etape_activite_ppm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EtapeActivitePpm extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "date_etape")
    private LocalDate dateEtape;

    @Column(name = "date_reelle")
    private LocalDate dateReelle;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "debut")
    private LocalDate debut;

    @Column(name = "fin")
    private LocalDate fin;

    @Column(name = "visited")
    private Boolean visited;

    @ManyToOne
    @JsonIgnoreProperties("etapeActivitePpms")
    private Etape etape;

    @ManyToOne
    @JsonIgnoreProperties("etapeActivitePpms")
    private PpmActivite ppmActivite;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEtape() {
        return dateEtape;
    }

    public EtapeActivitePpm dateEtape(LocalDate dateEtape) {
        this.dateEtape = dateEtape;
        return this;
    }

    public void setDateEtape(LocalDate dateEtape) {
        this.dateEtape = dateEtape;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public EtapeActivitePpm deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Etape getEtape() {
        return etape;
    }

    public EtapeActivitePpm etape(Etape etape) {
        this.etape = etape;
        return this;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    public PpmActivite getPpmActivite() {
        return ppmActivite;
    }

    public EtapeActivitePpm ppmActivite(PpmActivite ppmActivite) {
        this.ppmActivite = ppmActivite;
        return this;
    }

    public void setPpmActivite(PpmActivite ppmActivite) {
        this.ppmActivite = ppmActivite;
    }

    public LocalDate getDateReelle() {
        return dateReelle;
    }

    public void setDateReelle(LocalDate dateReelle) {
        this.dateReelle = dateReelle;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Boolean isVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtapeActivitePpm)) {
            return false;
        }
        return id != null && id.equals(((EtapeActivitePpm) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EtapeActivitePpm{"
                + "id=" + getId()
                + ", dateEtape='" + getDateEtape() + "'"
                + ", deleted='" + isDeleted() + "'"
                + "}";
    }
}
