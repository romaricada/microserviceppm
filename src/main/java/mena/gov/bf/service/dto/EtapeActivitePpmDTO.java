package mena.gov.bf.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link mena.gov.bf.domain.EtapeActivitePpm} entity.
 */
public class EtapeActivitePpmDTO implements Serializable {
    
    private Long id;

    private LocalDate dateEtape;

    private Boolean deleted;

    private LocalDate dateReelle;

    private LocalDate debut;

    private LocalDate fin;

    private Long etapeId;

    // private String libelleEtape;

    private Long ppmActiviteId;

    // private String libelleActivite;

    private Boolean visited;

    private String statut;

    private Integer delais;

    private String tootip;



    private EtapeDTO etape = new EtapeDTO();
    private PpmActiviteDTO ppmActivite = new PpmActiviteDTO();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEtape() {
        return dateEtape;
    }

    public void setDateEtape(LocalDate dateEtape) {
        this.dateEtape = dateEtape;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getEtapeId() {
        return etapeId;
    }

    public void setEtapeId(Long etapeId) {
        this.etapeId = etapeId;
    }

    public Long getPpmActiviteId() {
        return ppmActiviteId;
    }

    public void setPpmActiviteId(Long ppmActiviteId) {
        this.ppmActiviteId = ppmActiviteId;
    }

    public LocalDate getDateReelle() {
        return dateReelle;
    }

    public void setDateReelle(LocalDate dateReelle) {
        this.dateReelle = dateReelle;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Boolean isVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Integer getDelais() {
        return delais;
    }

    public void setDelais(Integer delais) {
        this.delais = delais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtapeActivitePpmDTO etapeActivitePpmDTO = (EtapeActivitePpmDTO) o;
        if (etapeActivitePpmDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etapeActivitePpmDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtapeActivitePpmDTO{" +
            "id=" + getId() +
            ", dateEtape='" + getDateEtape() + "'" +
            ", deleted='" + isDeleted() + "'" +
            ", etapeId=" + getEtapeId() +
            ", ppmActiviteId=" + getPpmActiviteId() +
            "}";
    }


    public EtapeDTO getEtape() {
        return etape;
    }

    public void setEtape(EtapeDTO etape) {
        this.etape = etape;
    }

    public PpmActiviteDTO getPpmActivite() {
        return ppmActivite;
    }

    public void setPpmActivite(PpmActiviteDTO ppmActivite) {
        this.ppmActivite = ppmActivite;
    }

    public String getTootip() {
        return tootip;
    }

    public void setTootip(String tootip) {
        this.tootip = tootip;
    }
}
