package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mena.gov.bf.domain.enumeration.EtatMarche;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Activite.
 */
@Entity
@Table(name = "activite")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Activite extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code_ligne_plan", nullable = false)
    private String codeLignePlan;

    @NotNull
    @Column(name = "nom_activite", nullable = false)
    private String nomActivite;

    @Column(name = "gestionnaire_credit")
    private String gestionnaireCredit;

    @Column(name = "etat_marche")
    @Enumerated(EnumType.STRING)
    private EtatMarche etatMarche;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "reported")
    private Boolean reported;

    @OneToMany(mappedBy = "activite")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BesoinLigneBudgetaire> besoinLignes = new HashSet<>();

    @OneToMany(mappedBy = "activite")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PpmActivite> ppmActivites = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("activites")
    private ModePassation passation;

    @ManyToOne
    @JsonIgnoreProperties("activites")
    private NaturePrestation naturePrestation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeLignePlan() {
        return codeLignePlan;
    }

    public Activite codeLignePlan(String codeLignePlan) {
        this.codeLignePlan = codeLignePlan;
        return this;
    }

    /**
     * Getter for property 'reported'.
     *
     * @return Value for property 'reported'.
     */
    public Boolean getReported() {
        return reported;
    }

    /**
     * Setter for property 'reported'.
     *
     * @param reported Value to set for property 'reported'.
     */
    public void setReported(Boolean reported) {
        this.reported = reported;
    }

    public void setCodeLignePlan(String codeLignePlan) {
        this.codeLignePlan = codeLignePlan;
    }

    public String getGestionnaireCredit() {
        return gestionnaireCredit;
    }

    public Activite gestionnaireCredit(String gestionnaireCredit) {
        this.gestionnaireCredit = gestionnaireCredit;
        return this;
    }

    public void setGestionnaireCredit(String gestionnaireCredit) {
        this.gestionnaireCredit = gestionnaireCredit;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Activite deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<BesoinLigneBudgetaire> getBesoinLignes() {
        return besoinLignes;
    }

    public Activite besoinLignes(Set<BesoinLigneBudgetaire> besoinLigneBudgetaires) {
        this.besoinLignes = besoinLigneBudgetaires;
        return this;
    }

    public Activite addBesoinLignes(BesoinLigneBudgetaire besoinLigneBudgetaire) {
        this.besoinLignes.add(besoinLigneBudgetaire);
        besoinLigneBudgetaire.setActivite(this);
        return this;
    }

    public Activite removeBesoinLignes(BesoinLigneBudgetaire besoinLigneBudgetaire) {
        this.besoinLignes.remove(besoinLigneBudgetaire);
        besoinLigneBudgetaire.setActivite(null);
        return this;
    }

    public void setBesoinLignes(Set<BesoinLigneBudgetaire> besoinLigneBudgetaires) {
        this.besoinLignes = besoinLigneBudgetaires;
    }

    public Set<PpmActivite> getPpmActivites() {
        return ppmActivites;
    }

    public Activite ppmActivites(Set<PpmActivite> ppmActivites) {
        this.ppmActivites = ppmActivites;
        return this;
    }

    public Activite addPpmActivites(PpmActivite ppmActivite) {
        this.ppmActivites.add(ppmActivite);
        ppmActivite.setActivite(this);
        return this;
    }

    public Activite removePpmActivites(PpmActivite ppmActivite) {
        this.ppmActivites.remove(ppmActivite);
        ppmActivite.setActivite(null);
        return this;
    }

    public void setPpmActivites(Set<PpmActivite> ppmActivites) {
        this.ppmActivites = ppmActivites;
    }

    public ModePassation getPassation() {
        return passation;
    }

    public Activite passation(ModePassation modePassation) {
        this.passation = modePassation;
        return this;
    }

    public void setPassation(ModePassation modePassation) {
        this.passation = modePassation;
    }

    public NaturePrestation getNaturePrestation() {
        return naturePrestation;
    }

    public Activite naturePrestation(NaturePrestation naturePrestation) {
        this.naturePrestation = naturePrestation;
        return this;
    }

    public EtatMarche getEtatMarche() {
        return etatMarche;
    }

    public void setEtatMarche(EtatMarche etatMarche) {
        this.etatMarche = etatMarche;
    }

    public void setNaturePrestation(NaturePrestation naturePrestation) {
        this.naturePrestation = naturePrestation;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Activite)) {
            return false;
        }
        return id != null && id.equals(((Activite) o).id);
    }

    @Override
    public String toString() {
        return "Activite{" +
            "id=" + id +
            ", codeLignePlan='" + codeLignePlan + '\'' +
            ", nomActivite='" + nomActivite + '\'' +
            ", gestionnaireCredit='" + gestionnaireCredit + '\'' +
            ", etatMarche=" + etatMarche +
            ", deleted=" + deleted +
            ", reported=" + reported +
            ", besoinLignes=" + besoinLignes +
            ", ppmActivites=" + ppmActivites +
            ", passation=" + passation +
            ", naturePrestation=" + naturePrestation +
            '}';
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
