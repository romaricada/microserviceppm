package mena.gov.bf.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CalculDelaiDTO implements Serializable {

    private Long id;

    private String libelle;


    private Boolean deleted;

    private EtapeDTO etape;

    private ModePassationDTO modePassation;

    private List<EtapeDTO> etapes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }


    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public EtapeDTO getEtape() {
        return etape;
    }


    public void setEtape(EtapeDTO etape) {
        this.etape = etape;
    }

    public List<EtapeDTO> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<EtapeDTO> etapes) {
        this.etapes = etapes;
    }

    public ModePassationDTO getModePassation() {
        return modePassation;
    }

    public void setModePassation(ModePassationDTO modePassation) {
        this.modePassation = modePassation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalculDelaiDTO)) return false;
        CalculDelaiDTO that = (CalculDelaiDTO) o;
        return getId().equals(that.getId()) &&
            Objects.equals(getLibelle(), that.getLibelle()) &&
            Objects.equals(deleted, that.deleted) &&
            Objects.equals(getEtape(), that.getEtape());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLibelle(), deleted, getEtape());
    }

    @Override
    public String toString() {
        return "CalculDelaiDTO{" +
            "id=" + id +
            ", libelle='" + libelle + '\'' +
            ", deleted=" + deleted +
            ", etape=" + etape +
            ", etapes=" + etapes +
            '}';
    }
}
