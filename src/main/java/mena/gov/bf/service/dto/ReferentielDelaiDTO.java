package mena.gov.bf.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.ReferentielDelai} entity.
 */
public class ReferentielDelaiDTO implements Serializable {

    private Long id;

    private String observation;

    private Boolean deleted;

    private LocalDate debut;

    private LocalDate fin;

    private EtapeDTO etape;

    private ActeurDTO acteur;

    private ModePassationDTO modePassation;

    private Long modePassationId;

    private NormeReferenceDTO normeReference;

    private List<EtapeDTO> etapes = new ArrayList<>();

    private String libelleDate;

    private LocalDate date;

    private Integer duration;
    private Integer progress;

    public Long getModePassationId() {
        return modePassationId;
    }

    public void setModePassationId(Long modePassationId) {
        this.modePassationId = modePassationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }


    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public EtapeDTO getEtape() {
        return etape;
    }

    public void setEtape(EtapeDTO etape) {
        this.etape = etape;
    }

    public ActeurDTO getActeur() {
        return acteur;
    }

    public void setActeur(ActeurDTO acteur) {
        this.acteur = acteur;
    }

    public ModePassationDTO getModePassation() {
        return modePassation;
    }

    public void setModePassation(ModePassationDTO modePassation) {
        this.modePassation = modePassation;
    }

    public List<EtapeDTO> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<EtapeDTO> etapes) {
        this.etapes = etapes;
    }

    public String getLibelleDate() {
        return libelleDate;
    }

    public void setLibelleDate(String libelleDate) {
        this.libelleDate = libelleDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReferentielDelaiDTO referentielDelaiDTO = (ReferentielDelaiDTO) o;
        if (referentielDelaiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), referentielDelaiDTO.getId());
    }

    public NormeReferenceDTO getNormeReference() {
        return normeReference;
    }

    public void setNormeReference(NormeReferenceDTO normeReference) {
        this.normeReference = normeReference;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReferentielDelaiDTO{" +
            "id=" + id +
            ", observation='" + observation + '\'' +
            ", deleted=" + deleted +
            ", debut=" + debut +
            ", fin=" + fin +
            ", etape=" + etape +
            ", acteur=" + acteur +
            ", modePassation=" + modePassation +
            ", normeReference=" + normeReference +
            ", etapes=" + etapes +
            ", libelleDate='" + libelleDate + '\'' +
            ", date=" + date +
            ", duration=" + duration +
            ", progress=" + progress +
            '}';
    }
}
