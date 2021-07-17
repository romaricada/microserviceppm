package mena.gov.bf.alerteNotification.entity;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class DelaiMessageDTO implements Serializable {

    private Long id;

    private Integer tempsAlerte;

    @NotNull
    private TypeMessage typeMessage;

    private String message;

    @NotNull
    private Boolean deleted;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTempsAlerte() {
        return tempsAlerte;
    }

    public void setTempsAlerte(Integer tempsAlerte) {
        this.tempsAlerte = tempsAlerte;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DelaiMessageDTO delaiMessageDTO = (DelaiMessageDTO) o;
        if (delaiMessageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), delaiMessageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DelaiMessageDTO{" +
            "id=" + getId() +
            ", tempsAlerte=" + getTempsAlerte() +
            ", typeMessage='" + getTypeMessage() + "'" +
            ", message='" + getMessage() + "'" +
            ", deleted='" + isDeleted() + "'" +
            "}";
    }
}
