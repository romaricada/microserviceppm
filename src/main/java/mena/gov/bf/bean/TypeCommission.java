package mena.gov.bf.bean;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TypeCommission implements Serializable {

    private Long id;

    @NotNull
    private String libelle;

    @NotNull
    private Boolean deleted;

    private String referenceArrete;

    private Long activiteId;

    private MembreCommission membreCommission;

    private List<Membre> membres = new ArrayList<>();

    private List<MembreCommission> membreCommissions = new ArrayList<>();


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

    public MembreCommission getMembreCommission() {
        return membreCommission;
    }

    public void setMembreCommission(MembreCommission membreCommission) {
        this.membreCommission = membreCommission;
    }

    public List<Membre> getMembres() {
        return membres;
    }

    public void setMembres(List<Membre> membres) {
        this.membres = membres;
    }

    public List<MembreCommission> getMembreCommissions() {
        return membreCommissions;
    }

    public void setMembreCommissions(List<MembreCommission> membreCommissions) {
        this.membreCommissions = membreCommissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeCommission typeCommission = (TypeCommission) o;
        if (typeCommission.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeCommission.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeCommission{" +
            "id=" + id +
            ", libelle='" + libelle + '\'' +
            ", deleted=" + deleted +
            ", membreCommission=" + membreCommission +
            ", membres=" + membres +
            '}';
    }

    public String getReferenceArrete() {
        return referenceArrete;
    }

    public void setReferenceArrete(String referenceArrete) {
        this.referenceArrete = referenceArrete;
    }

    public Long getActiviteId() {
        return activiteId;
    }

    public void setActiviteId(Long activiteId) {
        this.activiteId = activiteId;
    }
}
