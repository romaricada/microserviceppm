package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.*;
import mena.gov.bf.service.dto.ActeurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Acteur} and its DTO {@link ActeurDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ActeurMapper extends EntityMapper<ActeurDTO, Acteur> {

    /**
     *
     * @param acteur
     * @return
     */
    @Mapping(source = "userId", target = "userId")
    @Override
    ActeurDTO toDto(Acteur acteur);

    /**
     *
     * @param acteurDTO
     * @return
     */
    @Mapping(target = "referentielDelais", ignore = true)
    @Mapping(target = "removeReferentielDelais", ignore = true)
    @Mapping(source = "userId", target = "userId")
    @Override
    Acteur toEntity(ActeurDTO acteurDTO);

    default Acteur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Acteur acteur = new Acteur();
        acteur.setId(id);
        return acteur;
    }
}
