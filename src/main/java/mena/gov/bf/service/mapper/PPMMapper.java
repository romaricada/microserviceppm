package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.PPMDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PPM} and its DTO {@link PPMDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PPMMapper extends EntityMapper<PPMDTO, PPM> {


    @Mapping(target = "ppmActivites", ignore = true)
    @Mapping(target = "removePpmActivites", ignore = true)
    PPM toEntity(PPMDTO pPMDTO);

    default PPM fromId(Long id) {
        if (id == null) {
            return null;
        }
        PPM pPM = new PPM();
        pPM.setId(id);
        return pPM;
    }
}
