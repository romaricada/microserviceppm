package mena.gov.bf.service.mapper;

import mena.gov.bf.domain.CalculDelai;
import mena.gov.bf.service.dto.CalculDelaiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EtapeMapper.class})
public interface CalculDelaiMapper extends EntityMapper<CalculDelaiDTO, CalculDelai>  {

    @Mapping(source = "etape", target = "etape")
    @Mapping(source = "modePassation", target = "modePassation")
    CalculDelaiDTO toDto(CalculDelai calculDelai);

    @Mapping(source = "etape", target = "etape")
    @Mapping(source = "modePassation", target = "modePassation")
    CalculDelai toEntity(CalculDelaiDTO calculDelaiDTO);

    default CalculDelai fromId(Long id) {
        if (id == null) {
            return null;
        }
        CalculDelai calculDelai = new CalculDelai();
        calculDelai.setId(id);
        return calculDelai;
    }
}
