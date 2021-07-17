package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Besoin.
 */
@Entity
@Table(name = "besoin")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Besoin extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "quantite")
    private Integer quantite;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "used")
    private Boolean used;

    @Column(name = "nombre_lot")
    private Integer nombreLot;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    @Column(name = "montant_estime")
    private Double montantEstime;

    @OneToMany(mappedBy = "besoin")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<BesoinLigneBudgetaire> besoinLignes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("besoins")
    private ExerciceBudgetaire exercice;

    @ManyToOne
    @JsonIgnoreProperties("besoins")
    private UniteAdministrative uniteAdministrative;

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

    public String getLibelle() {
        return libelle;
    }

    public Besoin libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Besoin quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Besoin deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<BesoinLigneBudgetaire> getBesoinLignes() {
        return besoinLignes;
    }

    public Besoin besoinLignes(Set<BesoinLigneBudgetaire> besoinLigneBudgetaires) {
        this.besoinLignes = besoinLigneBudgetaires;
        return this;
    }

    public Besoin addBesoinLignes(BesoinLigneBudgetaire besoinLigneBudgetaire) {
        this.besoinLignes.add(besoinLigneBudgetaire);
        besoinLigneBudgetaire.setBesoin(this);
        return this;
    }

    public Besoin removeBesoinLignes(BesoinLigneBudgetaire besoinLigneBudgetaire) {
        this.besoinLignes.remove(besoinLigneBudgetaire);
        besoinLigneBudgetaire.setBesoin(null);
        return this;
    }

    public void setBesoinLignes(Set<BesoinLigneBudgetaire> besoinLigneBudgetaires) {
        this.besoinLignes = besoinLigneBudgetaires;
    }

    public ExerciceBudgetaire getExercice() {
        return exercice;
    }

    public Besoin exercice(ExerciceBudgetaire exerciceBudgetaire) {
        this.exercice = exerciceBudgetaire;
        return this;
    }

    public void setExercice(ExerciceBudgetaire exerciceBudgetaire) {
        this.exercice = exerciceBudgetaire;
    }

    public UniteAdministrative getUniteAdministrative() {
        return uniteAdministrative;
    }

    public Besoin uniteAdministrative(UniteAdministrative uniteAdministrative) {
        this.uniteAdministrative = uniteAdministrative;
        return this;
    }

    public void setUniteAdministrative(UniteAdministrative uniteAdministrative) {
        this.uniteAdministrative = uniteAdministrative;
    }

    public NaturePrestation getNaturePrestation() {
        return naturePrestation;
    }

    public void setNaturePrestation(NaturePrestation naturePrestation) {
        this.naturePrestation = naturePrestation;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Boolean isUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Integer getNombreLot() {
        return nombreLot;
    }

    public void setNombreLot(Integer nombreLot) {
        this.nombreLot = nombreLot;
    }

    public Double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(Double montantEstime) {
        this.montantEstime = montantEstime;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Besoin)) {
            return false;
        }
        return id != null && id.equals(((Besoin) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Besoin{" +
            "id=" + id +
            ", libelle='" + libelle + '\'' +
            ", quantite=" + quantite +
            ", deleted=" + deleted +
            ", dateDebut=" + dateDebut +
            ", dateFin=" + dateFin +
            ", besoinLignes=" + besoinLignes +
            ", exercice=" + exercice +
            ", uniteAdministrative=" + uniteAdministrative +
            ", naturePrestation=" + naturePrestation +
            ", montantEstime=" + montantEstime +
            '}';
    }

}
