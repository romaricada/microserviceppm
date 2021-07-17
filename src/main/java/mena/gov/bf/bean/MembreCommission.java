package mena.gov.bf.bean;

import mena.gov.bf.bean.enummeration.Poste;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MembreCommission implements Serializable {

    private Long id;

    private Long activiteId;

    private String referenceArrete;

    @NotNull
    private Poste poste;

    @NotNull
    private Boolean deleted;

    private Long membreId;

    private Long typeCommissionId;

    private Long tacheId;

    private Membre membre = new Membre();
    private TypeCommission typeCommission= new TypeCommission();

    private List<Membre> membreses = new ArrayList<>();

    public List<Membre> getMembreses() {
        return membreses;
    }

    public void setMembreses(List<Membre> membreses) {
        this.membreses = membreses;
    }

    public TypeCommission getTypeCommission() {
        return typeCommission;
    }

    public void setTypeCommission(TypeCommission typeCommission) {
        this.typeCommission = typeCommission;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
    }

    public String getReferenceArrete() {
        return referenceArrete;
    }

    public void setReferenceArrete(String referenceArrete) {
        this.referenceArrete = referenceArrete;
    }

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getMembreId() {
        return membreId;
    }

    public void setMembreId(Long membreId) {
        this.membreId = membreId;
    }

    public Long getTypeCommissionId() {
        return typeCommissionId;
    }

    public void setTypeCommissionId(Long typeCommissionId) {
        this.typeCommissionId = typeCommissionId;
    }

    public Long getTacheId() {
        return tacheId;
    }

    public void setTacheId(Long tacheId) {
        this.tacheId = tacheId;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MembreCommission membreCommissionDTO = (MembreCommission) o;
        if (membreCommissionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), membreCommissionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MembreCommissionDTO{" +
            "id=" + getId() +
            ", activiteId=" + getActiviteId() +
            ", referenceArrete='" + getReferenceArrete() + "'" +
            ", poste='" + getPoste() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", membre=" + getMembreId() +
            ", typeCommission=" + getTypeCommissionId() +
            ", tache=" + getTacheId() +
            "}";
    }



}
