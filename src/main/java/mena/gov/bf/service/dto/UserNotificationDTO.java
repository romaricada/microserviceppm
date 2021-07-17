package mena.gov.bf.service.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class UserNotificationDTO<T> implements Serializable {

    private Long id;
    private Boolean visited;
    private LocalDate jour;
    private Boolean deleted;
    private Long userId;
    private Long tacheEtapeId;
    private T tacheEtape;
    private String typeTache;

    /*private Long etapeExecutionId;
    private EtapeExecution etapeExecution;
    private Long etapeActivitePpmId;
    private EtapeActivitePpmDTO etapeActivitePpm;*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVisited() {
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

    public Boolean getDeleted() {
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

    public T getTacheEtape() {
        return tacheEtape;
    }

    public void setTacheEtape(T tacheEtape) {
        this.tacheEtape = tacheEtape;
    }

    public String getTypeTache() {
        return typeTache;
    }

    public void setTypeTache(String typeTache) {
        this.typeTache = typeTache;
    }

    @Override
    public String toString() {
        return "UserNotificationDTO{"
                + "id=" + id
                + ", visited=" + visited
                + ", jour=" + jour
                + ", deleted=" + deleted
                + ", userId=" + userId
                + ", tacheEtapeId=" + tacheEtapeId
                + ", tacheEtape=" + tacheEtape
                + '}';
    }
}
