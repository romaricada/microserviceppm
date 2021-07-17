package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.TimbreDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Timbre} and its DTO {@link TimbreDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TimbreMapper extends EntityMapper<TimbreDTO, Timbre> {



    default Timbre fromId(Long id) {
        if (id == null) {
            return null;
        }
        Timbre timbre = new Timbre();
        timbre.setId(id);
        return timbre;
    }
}
