package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.ActiviteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Activite} and its DTO {@link ActiviteDTO}.
 */
@Mapper(componentModel = "spring", uses = { ModePassationMapper.class, NaturePrestationMapper.class })
public interface ActiviteMapper extends EntityMapper<ActiviteDTO, Activite> {

    @Mapping(source = "passation.id", target = "passationId")
    @Mapping(source = "passation.libellePassation", target = "modePassationLibelle")
    @Mapping(source = "naturePrestation.id", target = "naturePrestationId")
    @Mapping(source = "reported", target = "reported")
    @Mapping(source = "naturePrestation.libelle", target = "naturePrestationLibelle")

    ActiviteDTO toDto(Activite activite);

    // @Mapping(target = "besoinLignes", ignore = true)
    @Mapping(target = "removeBesoinLignes", ignore = true)
    @Mapping(target = "ppmActivites", ignore = true)
    @Mapping(target = "removePpmActivites", ignore = true)
    @Mapping(source = "passationId", target = "passation")
    @Mapping(source = "naturePrestationId", target = "naturePrestation")
    Activite toEntity(ActiviteDTO activiteDTO);

    default Activite fromId(Long id) {
        if (id == null) {
            return null;
        }
        Activite activite = new Activite();
        activite.setId(id);
        return activite;
    }
}
