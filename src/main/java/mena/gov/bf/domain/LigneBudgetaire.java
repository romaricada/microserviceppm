package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A LigneBudgetaire.
 */
@Entity
@Table(name = "ligne_budgetaire")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LigneBudgetaire extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "budget", nullable = false)
    private String budget;

    @Column(name = "ligne_credit", nullable = false)
    private String ligneCredit;

    @Column(name = "section", nullable = false)
    private String section;

    @Column(name = "programme", nullable = false)
    private String programme;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "chapitre", nullable = false)
    private String chapitre;

    @Column(name = "activite", nullable = false)
    private String activite;

    @Column(name = "article", nullable = false)
    private String article;

    @Column(name = "paragraphe", nullable = false)
    private String paragraphe;

    @Column(name = "dot_init_ae")
    private Double dotInitAE;

    @Column(name = "dot_init_cp")
    private Double dotInitCP;

    @Column(name = "dot_cor_ae")
    private Double dotCorAE;

    @Column(name = "dot_cor_cp")
    private Double dotCorCP;

    /*@Column(name = "dot_cor_ae_restant")
    private Double dotCorAERestant;

    @Column(name = "dot_cor_cp_restant")
    private Double dotCorCPRestant;*/

    @Column(name = "engage")
    private Double engage;

    @Column(name = "engage_cf")
    private Double engageCF;

    @Column(name = "liquide")
    private Double liquide;

    @Column(name = "ordonne")
    private Double ordonne;

    @Column(name = "vbp")
    private Double vbp;

    @Column(name = "ecp")
    private Double ecp;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne
    @JsonIgnoreProperties("besoins")
    private ExerciceBudgetaire exercice;

    @ManyToOne
    @JsonIgnoreProperties("unite_admin")
    private UniteAdministrative uniteAdministrative;

    @OneToMany(mappedBy = "ligneBudget")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BesoinLigneBudgetaire> besoins = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not
    // remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBudget() {
        return budget;
    }

    public LigneBudgetaire budget(String budget) {
        this.budget = budget;
        return this;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getLigneCredit() {
        return ligneCredit;
    }

    public LigneBudgetaire ligneCredit(String ligneCredit) {
        this.ligneCredit = ligneCredit;
        return this;
    }

    public void setLigneCredit(String ligneCredit) {
        this.ligneCredit = ligneCredit;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public LigneBudgetaire deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<BesoinLigneBudgetaire> getBesoins() {
        return besoins;
    }

    public LigneBudgetaire besoins(Set<BesoinLigneBudgetaire> besoinLigneBudgetaires) {
        this.besoins = besoinLigneBudgetaires;
        return this;
    }

    public LigneBudgetaire addBesoins(BesoinLigneBudgetaire besoinLigneBudgetaire) {
        this.besoins.add(besoinLigneBudgetaire);
        besoinLigneBudgetaire.setLigneBudget(this);
        return this;
    }

    public LigneBudgetaire removeBesoins(BesoinLigneBudgetaire besoinLigneBudgetaire) {
        this.besoins.remove(besoinLigneBudgetaire);
        besoinLigneBudgetaire.setLigneBudget(null);
        return this;
    }

    public void setBesoins(Set<BesoinLigneBudgetaire> besoinLigneBudgetaires) {
        this.besoins = besoinLigneBudgetaires;
    }

    public ExerciceBudgetaire getExercice() {
        return exercice;
    }

    public void setExercice(ExerciceBudgetaire exercice) {
        this.exercice = exercice;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getChapitre() {
        return chapitre;
    }

    public void setChapitre(String chapitre) {
        this.chapitre = chapitre;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getParagraphe() {
        return paragraphe;
    }

    public void setParagraphe(String paragraphe) {
        this.paragraphe = paragraphe;
    }

    public Double getDotInitAE() {
        return dotInitAE;
    }

    public void setDotInitAE(Double dotInitAE) {
        this.dotInitAE = dotInitAE;
    }

    public Double getDotInitCP() {
        return dotInitCP;
    }

    public void setDotInitCP(Double dotInitCP) {
        this.dotInitCP = dotInitCP;
    }

    public Double getDotCorAE() {
        return dotCorAE;
    }

    public void setDotCorAE(Double dotCorAE) {
        this.dotCorAE = dotCorAE;
    }

    public Double getDotCorCP() {
        return dotCorCP;
    }

    public void setDotCorCP(Double dotCorCP) {
        this.dotCorCP = dotCorCP;
    }

    public Double getEngage() {
        return engage;
    }

    public void setEngage(Double engage) {
        this.engage = engage;
    }

    public Double getEngageCF() {
        return engageCF;
    }

    public void setEngageCF(Double engageCF) {
        this.engageCF = engageCF;
    }

    public Double getLiquide() {
        return liquide;
    }

    public void setLiquide(Double liquide) {
        this.liquide = liquide;
    }

    public Double getOrdonne() {
        return ordonne;
    }

    public void setOrdonne(Double ordonne) {
        this.ordonne = ordonne;
    }

    public Double getVbp() {
        return vbp;
    }

    public void setVbp(Double vbp) {
        this.vbp = vbp;
    }

    public Double getEcp() {
        return ecp;
    }

    public void setEcp(Double ecp) {
        this.ecp = ecp;
    }

    public UniteAdministrative getUniteAdministrative() {
        return uniteAdministrative;
    }

    public void setUniteAdministrative(UniteAdministrative uniteAdministrative) {
        this.uniteAdministrative = uniteAdministrative;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here, do not remove
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneBudgetaire)) {
            return false;
        }
        return id != null && id.equals(((LigneBudgetaire) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LigneBudgetaire{"
                + "id=" + id
                + ", budget='" + budget + '\''
                + ", ligneCredit='" + ligneCredit + '\''
                + ", section='" + section + '\''
                + ", programme='" + programme + '\''
                + ", action='" + action + '\''
                + ", chapitre='" + chapitre + '\''
                + ", activite='" + activite + '\''
                + ", article='" + article + '\''
                + ", paragraphe='" + paragraphe + '\''
                + ", dotInitAE=" + dotInitAE
                + ", dotInitCP=" + dotInitCP
                + ", dotCorAE=" + dotCorAE
                + ", dotCorCP=" + dotCorCP
                + ", engage=" + engage
                + ", engageCF=" + engageCF
                + ", liquide=" + liquide
                + ", ordonne=" + ordonne
                + ", vbp=" + vbp
                + ", ecp=" + ecp
                + ", deleted=" + deleted
                + ", exercice=" + exercice
                + ", uniteAdministrative=" + uniteAdministrative
                + ", besoins=" + besoins
                + '}';
    }

   /* *//**
     * Gets dotCorCPRestant.
     *
     * @return Value of dotCorCPRestant.
     *//*
    public Double getDotCorCPRestant() {
        return dotCorCPRestant;
    }

    *//**
     * Sets new dotCorCPRestant.
     *
     * @param dotCorCPRestant New value of dotCorCPRestant.
     *//*
    public void setDotCorCPRestant(Double dotCorCPRestant) {
        this.dotCorCPRestant = dotCorCPRestant;
    }

    *//**
     * Sets new dotCorAERestant.
     *
     * @param dotCorAERestant New value of dotCorAERestant.
     *//*
    public void setDotCorAERestant(Double dotCorAERestant) {
        this.dotCorAERestant = dotCorAERestant;
    }

    *//**
     * Gets dotCorAERestant.
     *
     * @return Value of dotCorAERestant.
     *//*
    public Double getDotCorAERestant() {
        return dotCorAERestant;
    }*/
}
