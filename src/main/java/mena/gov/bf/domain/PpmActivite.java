package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mena.gov.bf.domain.enumeration.Niveau;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A PpmActivite.
 */
@Entity
@Table(name = "ppm_activite")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PpmActivite extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;


    @Column(name = "montant_depense_engage_non_liquide", nullable = false)
    private Double montantDepenseEngageNonLiquide;

    @NotNull
    @Column(name = "credit_disponible", nullable = false)
    private Double creditDisponible;

    @NotNull
    @Column(name = "periode_lancement_appel", nullable = false)
    private LocalDate periodeLancementAppel;

    @NotNull
    @Column(name = "periode_remise_offre", nullable = false)
    private LocalDate periodeRemiseOffre;

    @NotNull
    @Column(name = "temps_necessaire_evaluation_offre", nullable = false)
    private Integer tempsNecessaireEvaluationOffre;

    @NotNull
    @Column(name = "date_problable_demarage_prestation", nullable = false)
    private LocalDate dateProblableDemaragePrestation;

    @NotNull
    @Column(name = "delai_execution_prevu", nullable = false)
    private Integer delaiExecutionPrevu;

    @NotNull
    @Column(name = "date_buttoire", nullable = false)
    private LocalDate dateButtoire;

    @Column(name = "niveau")
    @Enumerated(EnumType.STRING)
    private Niveau niveau;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "report")
    private Boolean report;

    @Column(name = "id_exercice")
    private Long exerciceId;

    @OneToMany(mappedBy = "ppmActivite")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EtapeActivitePpm> etapeActivitePpms = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("ppmActivites")
    private PPM ppm;

    @ManyToOne
    @JsonIgnoreProperties("ppmActivites")
    private Activite activite;

    @ManyToOne
    @JsonIgnoreProperties("ppmActivites")
    private SourceFinancement sourceFinancement;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
    }

    public Double getMontantDepenseEngageNonLiquide() {
        return montantDepenseEngageNonLiquide;
    }

    public PpmActivite montantDepenseEngageNonLiquide(Double montantDepenseEngageNonLiquide) {
        this.montantDepenseEngageNonLiquide = montantDepenseEngageNonLiquide;
        return this;
    }

    public void setMontantDepenseEngageNonLiquide(Double montantDepenseEngageNonLiquide) {
        this.montantDepenseEngageNonLiquide = montantDepenseEngageNonLiquide;
    }

    public Double getCreditDisponible() {
        return creditDisponible;
    }

    public PpmActivite creditDisponible(Double creditDisponible) {
        this.creditDisponible = creditDisponible;
        return this;
    }

    public void setCreditDisponible(Double creditDisponible) {
        this.creditDisponible = creditDisponible;
    }

    public LocalDate getPeriodeLancementAppel() {
        return periodeLancementAppel;
    }

    public PpmActivite periodeLancementAppel(LocalDate periodeLancementAppel) {
        this.periodeLancementAppel = periodeLancementAppel;
        return this;
    }

    public void setPeriodeLancementAppel(LocalDate periodeLancementAppel) {
        this.periodeLancementAppel = periodeLancementAppel;
    }

    public LocalDate getPeriodeRemiseOffre() {
        return periodeRemiseOffre;
    }

    public PpmActivite periodeRemiseOffre(LocalDate periodeRemiseOffre) {
        this.periodeRemiseOffre = periodeRemiseOffre;
        return this;
    }

    public void setPeriodeRemiseOffre(LocalDate periodeRemiseOffre) {
        this.periodeRemiseOffre = periodeRemiseOffre;
    }

    public Integer getTempsNecessaireEvaluationOffre() {
        return tempsNecessaireEvaluationOffre;
    }

    public PpmActivite tempsNecessaireEvaluationOffre(Integer tempsNecessaireEvaluationOffre) {
        this.tempsNecessaireEvaluationOffre = tempsNecessaireEvaluationOffre;
        return this;
    }

    public void setTempsNecessaireEvaluationOffre(Integer tempsNecessaireEvaluationOffre) {
        this.tempsNecessaireEvaluationOffre = tempsNecessaireEvaluationOffre;
    }

    public LocalDate getDateProblableDemaragePrestation() {
        return dateProblableDemaragePrestation;
    }

    public PpmActivite dateProblableDemaragePrestation(LocalDate dateProblableDemaragePrestation) {
        this.dateProblableDemaragePrestation = dateProblableDemaragePrestation;
        return this;
    }

    public void setDateProblableDemaragePrestation(LocalDate dateProblableDemaragePrestation) {
        this.dateProblableDemaragePrestation = dateProblableDemaragePrestation;
    }

    public Integer getDelaiExecutionPrevu() {
        return delaiExecutionPrevu;
    }

    public PpmActivite delaiExecutionPrevu(Integer delaiExecutionPrevu) {
        this.delaiExecutionPrevu = delaiExecutionPrevu;
        return this;
    }

    public void setDelaiExecutionPrevu(Integer delaiExecutionPrevu) {
        this.delaiExecutionPrevu = delaiExecutionPrevu;
    }

    public LocalDate getDateButtoire() {
        return dateButtoire;
    }

    public PpmActivite dateButtoire(LocalDate dateButtoire) {
        this.dateButtoire = dateButtoire;
        return this;
    }

    public void setDateButtoire(LocalDate dateButtoire) {
        this.dateButtoire = dateButtoire;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public PpmActivite deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<EtapeActivitePpm> getEtapeActivitePpms() {
        return etapeActivitePpms;
    }

    public PpmActivite etapeActivitePpms(Set<EtapeActivitePpm> etapeActivitePpms) {
        this.etapeActivitePpms = etapeActivitePpms;
        return this;
    }

    public PpmActivite addEtapeActivitePpms(EtapeActivitePpm etapeActivitePpm) {
        this.etapeActivitePpms.add(etapeActivitePpm);
        etapeActivitePpm.setPpmActivite(this);
        return this;
    }

    public PpmActivite removeEtapeActivitePpms(EtapeActivitePpm etapeActivitePpm) {
        this.etapeActivitePpms.remove(etapeActivitePpm);
        etapeActivitePpm.setPpmActivite(null);
        return this;
    }

    public void setEtapeActivitePpms(Set<EtapeActivitePpm> etapeActivitePpms) {
        this.etapeActivitePpms = etapeActivitePpms;
    }

    public PPM getPpm() {
        return ppm;
    }

    public PpmActivite ppm(PPM pPM) {
        this.ppm = pPM;
        return this;
    }

    public void setPpm(PPM pPM) {
        this.ppm = pPM;
    }

    public Activite getActivite() {
        return activite;
    }

    public PpmActivite activite(Activite activite) {
        this.activite = activite;
        return this;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public SourceFinancement getSourceFinancement() {
        return sourceFinancement;
    }

    public PpmActivite sourceFinancement(SourceFinancement sourceFinancement) {
        this.sourceFinancement = sourceFinancement;
        return this;
    }

    public void setSourceFinancement(SourceFinancement sourceFinancement) {
        this.sourceFinancement = sourceFinancement;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Long getExerciceId() {
        return exerciceId;
    }

    public void setExerciceId(Long exerciceId) {
        this.exerciceId = exerciceId;
    }

// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PpmActivite)) {
            return false;
        }
        return id != null && id.equals(((PpmActivite) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PpmActivite{" +
            "id=" + id +
            ", montantDepenseEngageNonLiquide=" + montantDepenseEngageNonLiquide +
            ", creditDisponible=" + creditDisponible +
            ", periodeLancementAppel=" + periodeLancementAppel +
            ", periodeRemiseOffre=" + periodeRemiseOffre +
            ", tempsNecessaireEvaluationOffre=" + tempsNecessaireEvaluationOffre +
            ", dateProblableDemaragePrestation=" + dateProblableDemaragePrestation +
            ", delaiExecutionPrevu=" + delaiExecutionPrevu +
            ", dateButtoire=" + dateButtoire +
            ", niveau=" + niveau +
            ", deleted=" + deleted +
            ", report=" + report +
            ", exerciceId=" + exerciceId +
            '}';
    }
}
