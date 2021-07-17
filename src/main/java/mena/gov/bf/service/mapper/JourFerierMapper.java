package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.JourFerier;
import mena.gov.bf.service.dto.JourFerierDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link JourFerier} and its DTO {@link JourFerierDTO}.
 */
@Mapper(componentModel = "spring", uses = {ExerciceBudgetaireMapper.class})
public interface JourFerierMapper extends EntityMapper<JourFerierDTO, JourFerier> {

    @Mapping(source = "exercice.id", target = "exerciceId")
    @Mapping(source = "exercice.annee", target = "anneeExercice")
    JourFerierDTO toDto(JourFerier jourFerier);


    @Mapping(source = "exerciceId", target = "exercice")
    JourFerier toEntity(JourFerierDTO jourFerierDTO);

    default JourFerier fromId(Long id) {
        if (id == null) {
            return null;
        }
        JourFerier jourFerier = new JourFerier();
        jourFerier.setId(id);
        return jourFerier;
    }
}
