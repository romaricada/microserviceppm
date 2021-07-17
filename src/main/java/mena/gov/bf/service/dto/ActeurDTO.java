package mena.gov.bf.service.dto;

import mena.gov.bf.alerteNotification.entity.Account;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.Acteur} entity.
 */
public class ActeurDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String libelle;

    private Boolean deleted;

    private Long userId;

    private Account user;

    private String mail;

    private String contact;

    
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

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ActeurDTO acteurDTO = (ActeurDTO) o;
        if (acteurDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), acteurDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ActeurDTO{" +
            "id=" + id +
            ", libelle='" + libelle + '\'' +
            ", deleted=" + deleted +
            ", userId=" + userId +
            ", user=" + user +
            ", mail='" + mail + '\'' +
            ", contact='" + contact + '\'' +
            '}';
    }
}
