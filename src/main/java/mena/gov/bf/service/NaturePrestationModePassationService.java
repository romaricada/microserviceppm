package mena.gov.bf.service;

import mena.gov.bf.domain.BesoinLigneBudgetaire;
import mena.gov.bf.domain.NaturePrestationModePassation;
import mena.gov.bf.repository.ModePassationRepository;
import mena.gov.bf.repository.NaturePrestationModePassationRepository;
import mena.gov.bf.service.dto.ModePassationDTO;
import mena.gov.bf.service.dto.NaturePrestationModePassationDTO;
import mena.gov.bf.service.mapper.ModePassationMapper;
import mena.gov.bf.service.mapper.NaturePrestationModePassationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BesoinLigneBudgetaire}.
 */
@Service
@Transactional
public class NaturePrestationModePassationService {

    private final Logger log = LoggerFactory.getLogger(NaturePrestationModePassationService.class);

    private final NaturePrestationModePassationRepository naturePrestationModePassationRepository;

    private final NaturePrestationModePassationMapper naturePrestationModePassationMapper;

    @Autowired
    ModePassationRepository modePassationRepository;

    @Autowired
    ModePassationMapper modePassationMapper;

    public NaturePrestationModePassationService(NaturePrestationModePassationRepository naturePrestationModePassationRepository,
                                                    NaturePrestationModePassationMapper naturePrestationModePassationMapper) {
        this.naturePrestationModePassationRepository = naturePrestationModePassationRepository;
        this.naturePrestationModePassationMapper = naturePrestationModePassationMapper;
    }

    /**
     * Save a besoinLigneBudgetaire.
     *
     * @param naturePrestationModePassationDTO the entity to save.
     * @return the persisted entity.
     */
    public NaturePrestationModePassationDTO save(NaturePrestationModePassationDTO naturePrestationModePassationDTO) {
        log.debug("Request to save NaturePrestationModePassationDTO : {}", naturePrestationModePassationDTO);
        NaturePrestationModePassation naturePrestationModePassation = naturePrestationModePassationMapper.toEntity(naturePrestationModePassationDTO);
        naturePrestationModePassation = naturePrestationModePassationRepository.save(naturePrestationModePassation);
        return naturePrestationModePassationMapper.toDto(naturePrestationModePassation);
    }

    /**
     * methode pour verifier qu'une nature de prestation et son mode de passation
     * ne sont pas enregistré pour des intervalles différents.
     */

    public boolean naturePrestationModePassationIsExist (Long naturePrestationId, Long modePassationId) {
        Optional<NaturePrestationModePassation> naturePrestationModePassations1 = naturePrestationModePassationRepository.findTop1ByNaturePrestationIdAndModePassationId(naturePrestationId, modePassationId);
        log.debug("======================================");
        log.debug("================ NaturePrestationModePassation = {} ======================", naturePrestationModePassations1);
        log.debug("======================================");
        if (naturePrestationModePassations1.isPresent()) return true;
        else return false;
    }

    /**
     * methode pour verifier que les intervalles de montant
     * ne se chevauchent pas.
     */

    public boolean naturePrestationModePassationIsOverlap (Double montantMin, Double montantMax) {
        Boolean isOverlap = false;
        Double droite, gauche;
        List<NaturePrestationModePassation> naturePrestationModePassations  = naturePrestationModePassationRepository.findAll();
        for (NaturePrestationModePassation prestationModePassation: naturePrestationModePassations){
            Double montantMin2, montantMax2;
            montantMin2 = prestationModePassation.getMontantMin();
            montantMax2 = prestationModePassation.getMontantMax();
            if(montantMin<montantMin2)
                gauche = montantMin2;
            else
                gauche =montantMin;

            if (montantMax<montantMax2)
                droite = montantMax;
            else
                droite = montantMax2;

            if (gauche<droite || montantMin>montantMax) {
                isOverlap = true;
                break;
            }
        }

        return isOverlap;
    }

    /*private boolean intersectionIntervalles(Double montantMin1, Double montantMax1, Double montantMin2, Double montantMax2) {
        Double droite, gauche;
        Boolean intersection;


        if(montantMin1<montantMin2)
            gauche = montantMin2;
        else
            gauche =montantMin1;

        if (montantMax1<montantMax2)
            droite = montantMax1;
        else
            droite = montantMax2;

        if (gauche>droite)
            intersection = false;
        else
            intersection =true;

        return intersection;
    }*/


    /**
     * Get all the besoinLigneBudgetaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NaturePrestationModePassationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BesoinLigneBudgetaires");
        List<NaturePrestationModePassationDTO> modePassationDTOS = naturePrestationModePassationRepository.findAll().stream().map( naturePrestationModePassationMapper::toDto )
            .filter( naturePrestationModePassationDTO -> naturePrestationModePassationDTO.isDeleted() != null && !naturePrestationModePassationDTO.isDeleted() ).collect( Collectors.toList() );
        return new PageImpl<>( modePassationDTOS, pageable, modePassationDTOS.size() );
    }

    /**
     * Get one besoinLigneBudgetaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NaturePrestationModePassationDTO> findOne(Long id) {
        log.debug("Request to get BesoinLigneBudgetaire : {}", id);
        return naturePrestationModePassationRepository.findById(id)
            .map(naturePrestationModePassationMapper::toDto);
    }

    /**
     * Delete the besoinLigneBudgetaire by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BesoinLigneBudgetaire : {}", id);
        naturePrestationModePassationRepository.deleteById(id);
    }

    public ModePassationDTO findModePassationByNaturePrestationAndMontant(Long naturePrestationId, Double montant) {

        log.debug("------------------       {}       ------------------------       {}       ----------------------", naturePrestationId,montant);

        List<NaturePrestationModePassationDTO> naturePrestationModePassations = naturePrestationModePassationRepository.findByNaturePrestation_IdAndDeletedIsFalse(naturePrestationId)
            .stream()
            .map(naturePrestationModePassationMapper::toDto)
            .filter(npmp -> npmp.getMontantMax() != null && npmp.getMontantMin() != null && npmp.getMontantMin() <= montant && npmp.getMontantMax() >= montant)
            .collect(Collectors.toList());

        log.debug("+++++++++++++++naturePrestationModePassations+++++++++++++ ++{}",naturePrestationModePassations);

        if (!naturePrestationModePassations.isEmpty() && naturePrestationModePassations.size() == 1) {
            return modePassationMapper.toDto(modePassationRepository.getOne(naturePrestationModePassations.get(0).getModePassationId()));
        }

        return null;
    }

    public List<NaturePrestationModePassationDTO> updateAll (List<NaturePrestationModePassationDTO> naturePrestationModePassationDTOS) {
        naturePrestationModePassationDTOS.forEach(naturePrestationModePassationDTO ->{ naturePrestationModePassationDTO.setDeleted(true);

        });

        naturePrestationModePassationRepository.saveAll(naturePrestationModePassationDTOS.stream().map(naturePrestationModePassationMapper::toEntity).collect(Collectors.toList()));
            List<NaturePrestationModePassationDTO> naturePrestationModePassationDTOS1 = naturePrestationModePassationRepository.findAll().stream().map(naturePrestationModePassationMapper::toDto).filter(naturePrestationModePassationDTO ->
                naturePrestationModePassationDTO.isDeleted() != null && naturePrestationModePassationDTO.isDeleted()).collect(Collectors.toList());
        return naturePrestationModePassationDTOS;
    }

}
