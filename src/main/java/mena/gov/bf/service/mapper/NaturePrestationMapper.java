package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.NaturePrestationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link NaturePrestation} and its DTO
 * {@link NaturePrestationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NaturePrestationMapper extends EntityMapper<NaturePrestationDTO, NaturePrestation> {

    @Mapping(target = "activites", ignore = true)
    @Mapping(target = "removeActivites", ignore = true)
    NaturePrestation toEntity(NaturePrestationDTO naturePrestationDTO);

    default NaturePrestation fromId(Long id) {
        if (id == null) {
            return null;
        }
        NaturePrestation naturePrestation = new NaturePrestation();
        naturePrestation.setId(id);
        return naturePrestation;
    }
}
