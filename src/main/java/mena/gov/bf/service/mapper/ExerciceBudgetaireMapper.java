package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.ExerciceBudgetaireDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExerciceBudgetaire} and its DTO {@link ExerciceBudgetaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ExerciceBudgetaireMapper extends EntityMapper<ExerciceBudgetaireDTO, ExerciceBudgetaire> {


    @Mapping(target = "besoins", ignore = true)
    @Mapping(target = "removeBesoins", ignore = true)
    ExerciceBudgetaire toEntity(ExerciceBudgetaireDTO exerciceBudgetaireDTO);

    default ExerciceBudgetaire fromId(Long id) {
        if (id == null) {
            return null;
        }
        ExerciceBudgetaire exerciceBudgetaire = new ExerciceBudgetaire();
        exerciceBudgetaire.setId(id);
        return exerciceBudgetaire;
    }
}
