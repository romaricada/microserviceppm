package mena.gov.bf.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * A UserNotification.
 */
@Entity
@Table(name = "user_notification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserNotification extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "visited")
    private Boolean visited;

    @Column(name = "jour")
    private LocalDate jour;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "tache_etape_id")
    private Long tacheEtapeId;

    @Column(name = "type_tache")
    private String typeTache;

    /*@Column(name = "etape_execution_id")
    private Long etapeExecutionId;

    @ManyToOne
    @JsonIgnoreProperties("userNotifications")
    private EtapeActivitePpm etapeActivitePpm;*/


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTacheEtapeId() {
        return tacheEtapeId;
    }

    public void setTacheEtapeId(Long tacheEtapeId) {
        this.tacheEtapeId = tacheEtapeId;
    }

    public String getTypeTache() {
        return typeTache;
    }

    public void setTypeTache(String typeTache) {
        this.typeTache = typeTache;
    }

    @Override
    public String toString() {
        return "UserNotification{" +
            "id=" + id +
            ", visited=" + visited +
            ", jour=" + jour +
            ", deleted=" + deleted +
            ", userId=" + userId +
            ", tacheEtapeId=" + tacheEtapeId +
            ", typeTache='" + typeTache + '\'' +
            '}';
    }
}
