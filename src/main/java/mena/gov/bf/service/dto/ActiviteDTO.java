package mena.gov.bf.service.dto;

import mena.gov.bf.domain.enumeration.EtatMarche;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;

/**
 * A DTO for the {@link mena.gov.bf.domain.Activite} entity.
 */
public class ActiviteDTO implements Serializable {

    private Long id;

    @NotNull
    private String codeLignePlan;

    private String gestionnaireCredit;

    private EtatMarche etatMarche;

    private Boolean deleted;

    private Boolean reported;

    private Long passationId;

    private String modePassationLibelle;

    private Long naturePrestationId;

    private String naturePrestationLibelle;

    private Set<BesoinLigneBudgetaireDTO> besoinLignes = new HashSet<>();

    private Set<PpmActiviteDTO> ppmActivites = new HashSet<>();

    private PpmActiviteDTO ppmActivite;

    private Double total;

    private List<BesoinDTO> besoins = new ArrayList<>();

    private List<ReferentielDelaiDTO> referentielDelais = new ArrayList<>();

    private PPMDTO ppm;

    private ModePassationDTO modePassation;

    private NaturePrestationDTO naturePrestation;

    private String nomActivite;

    private Double etatAvancement;

    private LigneBudgetaireDTO ligneBudgetaireDTO = new LigneBudgetaireDTO();


    /**
     * Getter for property 'besoinLignes'.
     *
     * @return Value for property 'besoinLignes'.
     */
    public Set<BesoinLigneBudgetaireDTO> getBesoinLignes() {
        return besoinLignes;
    }

    /**
     * Setter for property 'besoinLignes'.
     *
     * @param besoinLignes Value to set for property 'besoinLignes'.
     */
    public void setBesoinLignes(Set<BesoinLigneBudgetaireDTO> besoinLignes) {
        this.besoinLignes = besoinLignes;
    }

    /**
     * Getter for property 'ppmActivites'.
     *
     * @return Value for property 'ppmActivites'.
     */
    public Set<PpmActiviteDTO> getPpmActivites() {
        return ppmActivites;
    }

    /**
     * Setter for property 'ppmActivites'.
     *
     * @param ppmActivites Value to set for property 'ppmActivites'.
     */
    public void setPpmActivites(Set<PpmActiviteDTO> ppmActivites) {
        this.ppmActivites = ppmActivites;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeLignePlan() {
        return codeLignePlan;
    }

    public void setCodeLignePlan(String codeLignePlan) {
        this.codeLignePlan = codeLignePlan;
    }

    public String getGestionnaireCredit() {
        return gestionnaireCredit;
    }

    public void setGestionnaireCredit(String gestionnaireCredit) {
        this.gestionnaireCredit = gestionnaireCredit;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getPassationId() {
        return passationId;
    }

    public void setPassationId(Long modePassationId) {
        this.passationId = modePassationId;
    }

    public Long getNaturePrestationId() {
        return naturePrestationId;
    }

    public void setNaturePrestationId(Long naturePrestationId) {
        this.naturePrestationId = naturePrestationId;
    }

    public String getModePassationLibelle() {
        return modePassationLibelle;
    }

    public void setModePassationLibelle(String modePassationLibelle) {
        this.modePassationLibelle = modePassationLibelle;
    }

    public String getNaturePrestationLibelle() {
        return naturePrestationLibelle;
    }

    public void setNaturePrestationLibelle(String naturePrestationLibelle) {
        this.naturePrestationLibelle = naturePrestationLibelle;
    }

    public PpmActiviteDTO getPpmActivite() {
        return ppmActivite;
    }

    public void setPpmActivite(PpmActiviteDTO ppmActivite) {
        this.ppmActivite = ppmActivite;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public EtatMarche getEtatMarche() {
        return etatMarche;
    }

    public void setEtatMarche(EtatMarche etatMarche) {
        this.etatMarche = etatMarche;
    }

    public List<BesoinDTO> getBesoins() {
        return besoins;
    }

    public void setBesoins(List<BesoinDTO> besoins) {
        this.besoins = besoins;
    }

    public List<ReferentielDelaiDTO> getReferentielDelais() {
        return referentielDelais;
    }

    public void setReferentielDelais(List<ReferentielDelaiDTO> referentielDelais) {
        this.referentielDelais = referentielDelais;
    }

    public PPMDTO getPpm() {
        return ppm;
    }

    public void setPpm(PPMDTO ppm) {
        this.ppm = ppm;
    }

    public ModePassationDTO getModePassation() {
        return modePassation;
    }

    public void setModePassation(ModePassationDTO modePassation) {
        this.modePassation = modePassation;
    }

    public NaturePrestationDTO getNaturePrestation() {
        return naturePrestation;
    }

    public void setNaturePrestation(NaturePrestationDTO naturePrestation) {
        this.naturePrestation = naturePrestation;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public LigneBudgetaireDTO getLigneBudgetaireDTO() { return ligneBudgetaireDTO; }

    public void setLigneBudgetaireDTO(LigneBudgetaireDTO ligneBudgetaireDTO) { this.ligneBudgetaireDTO = ligneBudgetaireDTO; }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActiviteDTO activiteDTO = (ActiviteDTO) o;
        if (activiteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), activiteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ActiviteDTO{" +
            "id=" + id +
            ", codeLignePlan='" + codeLignePlan + '\'' +
            ", gestionnaireCredit='" + gestionnaireCredit + '\'' +
            ", etatMarche=" + etatMarche +
            ", deleted=" + deleted +
            ", reported=" + reported +
            ", passationId=" + passationId +
            ", modePassationLibelle='" + modePassationLibelle + '\'' +
            ", naturePrestationId=" + naturePrestationId +
            ", naturePrestationLibelle='" + naturePrestationLibelle + '\'' +
            ", besoinLignes=" + besoinLignes +
            ", ppmActivites=" + ppmActivites +
            ", ppmActivite=" + ppmActivite +
            ", total=" + total +
            ", besoins=" + besoins +
            ", referentielDelais=" + referentielDelais +
            ", ppm=" + ppm +
            ", nomActivite=" + nomActivite +
            '}';
    }

    public Double getEtatAvancement() {
        return etatAvancement;
    }

    public void setEtatAvancement(Double etatAvancement) {
        this.etatAvancement = etatAvancement;
    }
}
