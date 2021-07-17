package mena.gov.bf.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "calcul_delai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CalculDelai extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "deleted")
    private Boolean deleted;

    @ManyToOne
    @JsonIgnoreProperties("calculDelais")
    private Etape etape;

    @ManyToOne
    @JsonIgnoreProperties("calculDelais")
    private ModePassation modePassation;

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

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    public ModePassation getModePassation() {
        return modePassation;
    }

    public void setModePassation(ModePassation modePassation) {
        this.modePassation = modePassation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CalculDelai)) {
            return false;
        }
        CalculDelai that = (CalculDelai) o;
        return getId().equals(that.getId())
                && Objects.equals(getLibelle(), that.getLibelle())
                && Objects.equals(isDeleted(), that.isDeleted())
                && Objects.equals(etape, that.etape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLibelle(), isDeleted(), etape);
    }

    @Override
    public String toString() {
        return "CalculDelai{"
                + "id=" + id
                + ", libelle='" + libelle + '\''
                + ", deleted=" + deleted
                + ", etape=" + etape
                + '}';
    }
}
