package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.EtapeActivitePpmDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtapeActivitePpm} and its DTO {@link EtapeActivitePpmDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtapeMapper.class, PpmActiviteMapper.class})
public interface EtapeActivitePpmMapper extends EntityMapper<EtapeActivitePpmDTO, EtapeActivitePpm> {

    @Mapping(source = "etape.id", target = "etapeId")
    @Mapping(source = "etape", target = "etape")
    @Mapping(source = "ppmActivite.id", target = "ppmActiviteId")
    @Mapping(source = "ppmActivite", target = "ppmActivite")
    EtapeActivitePpmDTO toDto(EtapeActivitePpm etapeActivitePpm);

    @Mapping(source = "etapeId", target = "etape")
    @Mapping(source = "ppmActiviteId", target = "ppmActivite")
    EtapeActivitePpm toEntity(EtapeActivitePpmDTO etapeActivitePpmDTO);

    default EtapeActivitePpm fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtapeActivitePpm etapeActivitePpm = new EtapeActivitePpm();
        etapeActivitePpm.setId(id);
        return etapeActivitePpm;
    }
}
