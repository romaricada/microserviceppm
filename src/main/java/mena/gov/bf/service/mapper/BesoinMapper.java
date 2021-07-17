package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.BesoinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Besoin} and its DTO {@link BesoinDTO}.
 */
@Mapper(componentModel = "spring", uses = {ExerciceBudgetaireMapper.class, UniteAdministrativeMapper.class, NaturePrestationMapper.class})
public interface BesoinMapper extends EntityMapper<BesoinDTO, Besoin> {

    @Mapping(source = "exercice.id", target = "exerciceId")
    @Mapping(source = "uniteAdministrative.id", target = "uniteAdministrativeId")
    @Mapping(source = "naturePrestation.id", target = "naturePrestationId")
    @Mapping(source = "exercice.annee", target = "anneeExercice")
    @Mapping(source = "uniteAdministrative.libelle", target = "libelleUniteAdministrative")
    @Mapping(source = "naturePrestation.libelle", target = "libellenaturePrestation")
    BesoinDTO toDto(Besoin besoin);

    @Mapping(target = "besoinLignes", ignore = true)
    @Mapping(target = "removeBesoinLignes", ignore = true)
    @Mapping(source = "exerciceId", target = "exercice")
    @Mapping(source = "uniteAdministrativeId", target = "uniteAdministrative")
    @Mapping(source = "naturePrestationId", target = "naturePrestation")
    Besoin toEntity(BesoinDTO besoinDTO);

    default Besoin fromId(Long id) {
        if (id == null) {
            return null;
        }
        Besoin besoin = new Besoin();
        besoin.setId(id);
        return besoin;
    }
}
