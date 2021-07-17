package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.ReferentielDelaiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReferentielDelai} and its DTO {@link ReferentielDelaiDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtapeMapper.class, ActeurMapper.class, ModePassationMapper.class})
public interface ReferentielDelaiMapper extends EntityMapper<ReferentielDelaiDTO, ReferentielDelai> {

    @Mapping(source = "etape", target = "etape")
    @Mapping(source = "acteur", target = "acteur")
    @Mapping(source = "modePassation", target = "modePassation")
    @Mapping(source = "normeReference", target = "normeReference")

    ReferentielDelaiDTO toDto(ReferentielDelai referentielDelai);

    @Mapping(source = "etape", target = "etape")
    @Mapping(source = "acteur", target = "acteur")
    @Mapping(source = "modePassation", target = "modePassation")
    @Mapping(source = "normeReference", target = "normeReference")
    ReferentielDelai toEntity(ReferentielDelaiDTO referentielDelaiDTO);

    default ReferentielDelai fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReferentielDelai referentielDelai = new ReferentielDelai();
        referentielDelai.setId(id);
        return referentielDelai;
    }
}
