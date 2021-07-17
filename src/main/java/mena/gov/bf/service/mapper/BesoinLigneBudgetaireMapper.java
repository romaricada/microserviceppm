package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.BesoinLigneBudgetaireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link BesoinLigneBudgetaire} and its DTO {@link BesoinLigneBudgetaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {LigneBudgetaireMapper.class, BesoinMapper.class, ActiviteMapper.class})
public interface BesoinLigneBudgetaireMapper extends EntityMapper<BesoinLigneBudgetaireDTO, BesoinLigneBudgetaire> {

    @Mapping(source = "ligneBudget.id", target = "ligneBudgetId")
    @Mapping(source = "besoin.id", target = "besoinId")
    @Mapping(source = "ligneBudget.budget", target = "budget")
    @Mapping(source = "ligneBudget.ligneCredit", target = "ligneCredit")
    @Mapping(source = "activite.id", target = "activiteId")
    @Mapping(source = "ligneBudget.dotCorCP", target = "dotCorCP")
    @Mapping(source = "ligneBudget.dotCorAE", target = "dotCorAE")
    BesoinLigneBudgetaireDTO toDto(BesoinLigneBudgetaire besoinLigneBudgetaire);

    @Mapping(source = "ligneBudgetId", target = "ligneBudget")
    @Mapping(source = "besoinId", target = "besoin")
    @Mapping(source = "activiteId", target = "activite")
    BesoinLigneBudgetaire toEntity(BesoinLigneBudgetaireDTO besoinLigneBudgetaireDTO);

    default BesoinLigneBudgetaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        BesoinLigneBudgetaire besoinLigneBudgetaire = new BesoinLigneBudgetaire();
        besoinLigneBudgetaire.setId(id);
        return besoinLigneBudgetaire;
    }
}
