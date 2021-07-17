package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.PpmActiviteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PpmActivite} and its DTO {@link PpmActiviteDTO}.
 */
@Mapper(componentModel = "spring", uses = {PPMMapper.class, ActiviteMapper.class, SourceFinancementMapper.class})
public interface PpmActiviteMapper extends EntityMapper<PpmActiviteDTO, PpmActivite> {

    @Mapping(source = "ppm.id", target = "ppmId")
    @Mapping(source = "activite.id", target = "activiteId")
    @Mapping(source = "activite.nomActivite", target = "libelleActivite")
    @Mapping(source = "activite.codeLignePlan", target = "codeLignePlanActivite")
    @Mapping(source = "sourceFinancement.id", target = "sourceFinancementId")
    PpmActiviteDTO toDto(PpmActivite ppmActivite);

    @Mapping(target = "etapeActivitePpms", ignore = true)
    @Mapping(target = "removeEtapeActivitePpms", ignore = true)
    @Mapping(source = "ppmId", target = "ppm")
    @Mapping(source = "activiteId", target = "activite")
    @Mapping(source = "sourceFinancementId", target = "sourceFinancement")
    PpmActivite toEntity(PpmActiviteDTO ppmActiviteDTO);

    default PpmActivite fromId(Long id) {
        if (id == null) {
            return null;
        }
        PpmActivite ppmActivite = new PpmActivite();
        ppmActivite.setId(id);
        return ppmActivite;
    }
}
