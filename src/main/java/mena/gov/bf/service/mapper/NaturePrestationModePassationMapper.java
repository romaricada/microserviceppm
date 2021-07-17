package mena.gov.bf.service.mapper;


import mena.gov.bf.domain.BesoinLigneBudgetaire;
import mena.gov.bf.domain.NaturePrestationModePassation;
import mena.gov.bf.service.dto.BesoinLigneBudgetaireDTO;
import mena.gov.bf.service.dto.NaturePrestationModePassationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link BesoinLigneBudgetaire} and its DTO {@link BesoinLigneBudgetaireDTO}.
 */
@Mapper(componentModel = "spring", uses = {NaturePrestationMapper.class, ModePassationMapper.class})
public interface NaturePrestationModePassationMapper extends EntityMapper<BesoinLigneBudgetaireDTO, BesoinLigneBudgetaire> {

    @Mapping(source = "modePassation.id", target = "modePassationId")
    @Mapping(source = "naturePrestation.id", target = "naturePrestationId")
    @Mapping(source = "naturePrestation.libelle", target = "libelleNaturePrestation")
    @Mapping(source = "modePassation.libellePassation", target = "libelleModePassation")
    NaturePrestationModePassationDTO toDto(NaturePrestationModePassation naturePrestationModePassation);

    @Mapping(source = "modePassationId", target = "modePassation")
    @Mapping(source = "naturePrestationId", target = "naturePrestation")
    NaturePrestationModePassation toEntity(NaturePrestationModePassationDTO naturePrestationModePassationDTO);

    default NaturePrestationModePassation fromId(Long id) {
        if (id == null) {
            return null;
        }
        NaturePrestationModePassation naturePrestationModePassation = new NaturePrestationModePassation();
        naturePrestationModePassation.setId(id);
        return naturePrestationModePassation;
    }
}
