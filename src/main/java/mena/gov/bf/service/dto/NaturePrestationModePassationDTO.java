package mena.gov.bf.service.dto;

import mena.gov.bf.domain.ModePassation;

import java.io.Serializable;
import java.util.Objects;

public class NaturePrestationModePassationDTO implements Serializable {

    private Long id;

    private Double montantMin;

    private Double montantMax;

    private Long modePassationId;

    private Long naturePrestationId;

    private NaturePrestationDTO naturePrestation;

    private ModePassation modePassation;

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

    public Long getModePassationId() {
        return modePassationId;
    }

    public void setModePassationId(Long modePassationId) {
        this.modePassationId = modePassationId;
    }

    public Long getNaturePrestationId() {
        return naturePrestationId;
    }

    public void setNaturePrestationId(Long naturePrestationId) {
        this.naturePrestationId = naturePrestationId;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    private String libelleNaturePrestation;

    private String libelleModePassation;

    public String getLibelleNaturePrestation() {
        return libelleNaturePrestation;
    }

    public void setLibelleNaturePrestation(String libelleNaturePrestation) {
        this.libelleNaturePrestation = libelleNaturePrestation;
    }

    public String getLibelleModePassation() {
        return libelleModePassation;
    }

    public void setLibelleModePassation(String libelleModePassation) {
        this.libelleModePassation = libelleModePassation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NaturePrestationModePassationDTO naturePrestationModePassationDTO = (NaturePrestationModePassationDTO) o;
        if (naturePrestationModePassationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), naturePrestationModePassationDTO.getId());
    }

    public NaturePrestationDTO getNaturePrestation() {
        return naturePrestation;
    }

    public void setNaturePrestation(NaturePrestationDTO naturePrestation) {
        this.naturePrestation = naturePrestation;
    }

    public ModePassation getModePassation() {
        return modePassation;
    }

    public void setModePassation(ModePassation modePassation) {
        this.modePassation = modePassation;
    }

    @Override
    public String toString() {
        return "NaturePrestationModePassationDTO [deleted=" + deleted + ", id=" + id + ", libelleModePassation="
                + libelleModePassation + ", libelleNaturePrestation=" + libelleNaturePrestation + ", modePassation="
                + modePassation + ", modePassationId=" + modePassationId + ", montantMax=" + montantMax
                + ", montantMin=" + montantMin + ", naturePrestation=" + naturePrestation + ", naturePrestationId="
                + naturePrestationId + "]";
    }

}
