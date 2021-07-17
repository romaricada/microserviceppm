package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.NormeReference;
import mena.gov.bf.service.dto.NormeReferenceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface NormeReferenceMapper extends EntityMapper<NormeReferenceDTO, NormeReference>  {

    default NormeReference fromId(Long id) {
        if (id == null) {
            return null;
        }
        NormeReference normeReference = new NormeReference();
        normeReference.setId(id);
        return normeReference;
    }
}
