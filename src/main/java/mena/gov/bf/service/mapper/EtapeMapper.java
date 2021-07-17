package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.EtapeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Etape} and its DTO {@link EtapeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ModePassationMapper.class})
public interface EtapeMapper extends EntityMapper<EtapeDTO, Etape> {
    @Mapping(source = "modePassation.id", target = "modePassationId")
    @Mapping(source = "modePassation.libellePassation", target = "modePassationLibelle")
    EtapeDTO toDto(Etape etape);
    @Mapping(source = "modePassationId", target = "modePassation")
    @Mapping(target = "referentielDelais", ignore = true)
    @Mapping(target = "removeReferentielDelais", ignore = true)
    @Mapping(target = "etapeActivitePpms", ignore = true)
    @Mapping(target = "removeEtapeActivitePpms", ignore = true)

    Etape toEntity(EtapeDTO etapeDTO);
    default Etape fromId(Long id) {
        if (id == null) {
            return null;
        }
        Etape etape = new Etape();
        etape.setId(id);
        return etape;
    }
}
