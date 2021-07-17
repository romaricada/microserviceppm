package mena.gov.bf.bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


public class EtapeExecution implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private Boolean deleted;


    private Long contratId;

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

    public Long getContratId() {
        return contratId;
    }

    public void setContratId(Long contratId) {
        this.contratId = contratId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtapeExecution etapeExecutionDTO = (EtapeExecution) o;
        if (etapeExecutionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etapeExecutionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtapeExecutionDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", contrat=" + getContratId() +
            "}";
    }
}
