package mena.gov.bf.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Acteur.
 */
@Entity
@Table(name = "acteur")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Acteur extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "userid")
    private Long userId;

    @Column(name = "mail")
    private String mail;

    @Column(name = "contact")
    private String contact;

    @OneToMany(mappedBy = "acteur")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ReferentielDelai> referentielDelais = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Acteur libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public Acteur deleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<ReferentielDelai> getReferentielDelais() {
        return referentielDelais;
    }

    public Acteur referentielDelais(Set<ReferentielDelai> referentielDelais) {
        this.referentielDelais = referentielDelais;
        return this;
    }

    public Acteur addReferentielDelais(ReferentielDelai referentielDelai) {
        this.referentielDelais.add(referentielDelai);
        referentielDelai.setActeur(this);
        return this;
    }

    public Acteur removeReferentielDelais(ReferentielDelai referentielDelai) {
        this.referentielDelais.remove(referentielDelai);
        referentielDelai.setActeur(null);
        return this;
    }

    public void setReferentielDelais(Set<ReferentielDelai> referentielDelais) {
        this.referentielDelais = referentielDelais;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Acteur)) {
            return false;
        }
        return id != null && id.equals(((Acteur) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Acteur{" +
            "id=" + id +
            ", libelle='" + libelle + '\'' +
            ", deleted=" + deleted +
            ", userId=" + userId +
            ", mail='" + mail + '\'' +
            ", contact='" + contact + '\'' +
            ", referentielDelais=" + referentielDelais +
            '}';
    }
}
