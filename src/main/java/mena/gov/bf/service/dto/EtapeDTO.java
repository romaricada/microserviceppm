package mena.gov.bf.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.Etape} entity.
 */
public class EtapeDTO implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    private Long modePassationId;

    private String modePassationLibelle;

    private Boolean deleted;

    @NotNull
    private Integer ordre;

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

    public Long getModePassationId() {
        return modePassationId;
    }

    public void setModePassationId(Long modePassationId) {
        this.modePassationId = modePassationId;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtapeDTO etapeDTO = (EtapeDTO) o;
        if (etapeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etapeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtapeDTO{" +
            "id=" + id +
            ", libelle='" + libelle + '\'' +
            ", modePassationId=" + modePassationId +
            ", modePassationLibelle='" + modePassationLibelle + '\'' +
            ", deleted=" + deleted +
            ", ordre=" + ordre +
            '}';
    }

    public String getModePassationLibelle() {
        return modePassationLibelle;
    }

    public void setModePassationLibelle(String modePassationLibelle) {
        this.modePassationLibelle = modePassationLibelle;
    }
}
