package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.LigneBudgetaireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LigneBudgetaire} and its DTO
 * {@link LigneBudgetaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {ExerciceBudgetaireMapper.class})
public interface LigneBudgetaireMapper extends EntityMapper<LigneBudgetaireDTO, LigneBudgetaire> {

    @Mapping(source = "exercice.id", target = "exerciceId")
    @Mapping(source = "exercice.annee", target = "annee")
    @Mapping(source = "uniteAdministrative.id", target = "uniteAdministrativeId")
    LigneBudgetaireDTO toDto(LigneBudgetaire ligneBudgetaire);

    @Mapping(target = "besoins", ignore = true)
    @Mapping(target = "removeBesoins", ignore = true)
    @Mapping(source = "exerciceId", target = "exercice")
    LigneBudgetaire toEntity(LigneBudgetaireDTO ligneBudgetaireDTO);

    default LigneBudgetaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        LigneBudgetaire ligneBudgetaire = new LigneBudgetaire();
        ligneBudgetaire.setId(id);
        return ligneBudgetaire;
    }

}
