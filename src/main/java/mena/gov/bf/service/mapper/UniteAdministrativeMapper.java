package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.UniteAdministrativeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UniteAdministrative} and its DTO {@link UniteAdministrativeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UniteAdministrativeMapper extends EntityMapper<UniteAdministrativeDTO, UniteAdministrative> {


    @Mapping(target = "besoins", ignore = true)
    @Mapping(target = "removeBesoins", ignore = true)
    UniteAdministrative toEntity(UniteAdministrativeDTO uniteAdministrativeDTO);

    default UniteAdministrative fromId(Long id) {
        if (id == null) {
            return null;
        }
        UniteAdministrative uniteAdministrative = new UniteAdministrative();
        uniteAdministrative.setId(id);
        return uniteAdministrative;
    }
}
