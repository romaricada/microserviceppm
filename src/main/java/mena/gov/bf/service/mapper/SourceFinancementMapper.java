package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.SourceFinancementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SourceFinancement} and its DTO {@link SourceFinancementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SourceFinancementMapper extends EntityMapper<SourceFinancementDTO, SourceFinancement> {


    @Mapping(target = "ppmActivites", ignore = true)
    @Mapping(target = "removePpmActivites", ignore = true)
    SourceFinancement toEntity(SourceFinancementDTO sourceFinancementDTO);

    default SourceFinancement fromId(Long id) {
        if (id == null) {
            return null;
        }
        SourceFinancement sourceFinancement = new SourceFinancement();
        sourceFinancement.setId(id);
        return sourceFinancement;
    }
}
