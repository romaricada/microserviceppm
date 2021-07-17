package mena.gov.bf.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import mena.gov.bf.domain.enumeration.TypeRelationPartenaire;

/**
 * A SourceFinancement.
 */
@Entity
@Table(name = "source_financement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SourceFinancement extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @NotNull
    @Column(name = "code_pays", nullable = false)
    private String codePays;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeRelationPartenaire type;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToMany(mappedBy = "sourceFinancement")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PpmActivite> ppmActivites = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public SourceFinancement code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public SourceFinancement libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCodePays() {
        return codePays;
    }

    public SourceFinancement codePays(String codePays) {
        this.codePays = codePays;
        return this;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getDescription() {
        return description;
    }

    public SourceFinancement description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeRelationPartenaire getType() {
        return type;
    }

    public SourceFinancement type(TypeRelationPartenaire type) {
        this.type = type;
        return this;
    }

    public void setType(TypeRelationPartenaire type) {
        this.type = type;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public SourceFinancement deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<PpmActivite> getPpmActivites() {
        return ppmActivites;
    }

    public SourceFinancement ppmActivites(Set<PpmActivite> ppmActivites) {
        this.ppmActivites = ppmActivites;
        return this;
    }

    public SourceFinancement addPpmActivites(PpmActivite ppmActivite) {
        this.ppmActivites.add(ppmActivite);
        ppmActivite.setSourceFinancement(this);
        return this;
    }

    public SourceFinancement removePpmActivites(PpmActivite ppmActivite) {
        this.ppmActivites.remove(ppmActivite);
        ppmActivite.setSourceFinancement(null);
        return this;
    }

    public void setPpmActivites(Set<PpmActivite> ppmActivites) {
        this.ppmActivites = ppmActivites;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SourceFinancement)) {
            return false;
        }
        return id != null && id.equals(((SourceFinancement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SourceFinancement{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", libelle='" + getLibelle() + "'" +
            ", codePays='" + getCodePays() + "'" +
            ", description='" + getDescription() + "'" +
            ", type='" + getType() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
