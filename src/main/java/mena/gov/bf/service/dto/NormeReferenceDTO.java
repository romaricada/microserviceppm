package mena.gov.bf.service.dto;

import java.util.ArrayList;
import java.util.List;

public class NormeReferenceDTO {

    private Long id;

    private Integer norme;

    private Integer referentiel;

    private Integer normeMin;

    private Integer referentielMin;

    private Integer normeMax;

    private Integer referentielMax;

    private Boolean normeOuvrable;

    private Boolean referentielOuvrable;

    private Boolean deleted;

    private  boolean intervalle;

    private List<ReferentielDelaiDTO> referentielDelais = new ArrayList<>();

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

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isIntervalle() {
        return intervalle;
    }

    public void setIntervalle(boolean intervalle) {
        this.intervalle = intervalle;
    }

    public List<ReferentielDelaiDTO> getReferentielDelais() {
        return referentielDelais;
    }

    public void setReferentielDelais(List<ReferentielDelaiDTO> referentielDelais) {
        this.referentielDelais = referentielDelais;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "NormeReferenceDTO{" +
            "id=" + id +
            ", norme=" + norme +
            ", referentiel=" + referentiel +
            ", normeMin=" + normeMin +
            ", referentielMin=" + referentielMin +
            ", normeMax=" + normeMax +
            ", referentielMax=" + referentielMax +
            ", normeOuvrable=" + normeOuvrable +
            ", referentielOuvrable=" + referentielOuvrable +
            ", deleted=" + deleted +
            ", intervalle=" + intervalle +
            '}';
    }
}
