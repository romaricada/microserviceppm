package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.ModePassationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ModePassation} and its DTO {@link ModePassationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ModePassationMapper extends EntityMapper<ModePassationDTO, ModePassation> {


    @Mapping(target = "activites", ignore = true)
    @Mapping(target = "removeActivites", ignore = true)
    @Mapping(target = "referentielDelais", ignore = true)
    @Mapping(target = "removeReferentielDelais", ignore = true)
    ModePassation toEntity(ModePassationDTO modePassationDTO);

    default ModePassation fromId(Long id) {
        if (id == null) {
            return null;
        }
        ModePassation modePassation = new ModePassation();
        modePassation.setId(id);
        return modePassation;
    }
}
