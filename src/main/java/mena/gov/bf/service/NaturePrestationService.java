package mena.gov.bf.service;

import mena.gov.bf.domain.NaturePrestation;
import mena.gov.bf.domain.NaturePrestationModePassation;
import mena.gov.bf.repository.ModePassationRepository;
import mena.gov.bf.repository.NaturePrestationModePassationRepository;
import mena.gov.bf.repository.NaturePrestationRepository;
import mena.gov.bf.service.dto.NaturePrestationDTO;
import mena.gov.bf.service.dto.NaturePrestationModePassationDTO;
import mena.gov.bf.service.mapper.NaturePrestationMapper;
import mena.gov.bf.service.mapper.NaturePrestationModePassationMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link NaturePrestation}.
 */
@Service
@Transactional
public class NaturePrestationService {

    private final Logger log = LoggerFactory.getLogger(NaturePrestationService.class);

    private final NaturePrestationRepository naturePrestationRepository;

    private final NaturePrestationMapper naturePrestationMapper;

    private final NaturePrestationModePassationRepository naturePrestationModePassationRepository;

    private final NaturePrestationModePassationMapper naturePrestationModePassationMapper;

    private final ModePassationRepository modePassationRepository;

    public NaturePrestationService(NaturePrestationRepository naturePrestationRepository,
            NaturePrestationMapper naturePrestationMapper,
            NaturePrestationModePassationRepository naturePrestationModePassationRepository,
            NaturePrestationModePassationMapper naturePrestationModePassationMapper,
            ModePassationRepository modePassationRepository) {
        this.naturePrestationRepository = naturePrestationRepository;
        this.naturePrestationMapper = naturePrestationMapper;
        this.naturePrestationModePassationRepository = naturePrestationModePassationRepository;
        this.naturePrestationModePassationMapper = naturePrestationModePassationMapper;
        this.modePassationRepository = modePassationRepository;
    }

    /**
     * Save a naturePrestation.
     *
     * @param naturePrestationDTO the entity to save.
     * @return the persisted entity.
     */
    public NaturePrestationDTO save(NaturePrestationDTO naturePrestationDTO) {
        log.debug("Request to save NaturePrestation : {}", naturePrestationDTO);
        List<NaturePrestationModePassationDTO> naturePrestationModePassationDTOs = naturePrestationDTO
                .getNaturePrestationModePassations();
        List<NaturePrestationModePassation> naturePrestationModePassations = new ArrayList<>();
        List<NaturePrestationModePassation> naturePrestationModePassations2 = new ArrayList<>();

        if (naturePrestationDTO.getId() != null) {
            List<NaturePrestationModePassationDTO> existNatureModeListDtos = naturePrestationModePassationRepository
                    .findAll().stream().map(naturePrestationModePassationMapper::toDto)
                    .filter(nature -> nature.getNaturePrestationId() != null
                            && nature.getNaturePrestationId().equals(naturePrestationDTO.getId())
                            && !nature.isDeleted())
                    .collect(Collectors.toList());

            if (!existNatureModeListDtos.isEmpty()) {
                for (NaturePrestationModePassationDTO natureMode : existNatureModeListDtos) {
                    NaturePrestationModePassation natureMode1 = naturePrestationModePassationMapper
                            .toEntity(natureMode);
                    natureMode1.setDeleted(true);
                    naturePrestationModePassations2.add(natureMode1);
                }
            }

        }
        NaturePrestation naturePrestation = naturePrestationMapper.toEntity(naturePrestationDTO);
        naturePrestation = naturePrestationRepository.save(naturePrestation);
        for (NaturePrestationModePassationDTO natureModeDTO : naturePrestationModePassationDTOs) {
            natureModeDTO.setNaturePrestationId(naturePrestation.getId());
            naturePrestationModePassations.add(naturePrestationModePassationMapper.toEntity(natureModeDTO));
        }
        naturePrestationModePassationRepository.saveAll(naturePrestationModePassations2);
        naturePrestationModePassationRepository.saveAll(naturePrestationModePassations);
        return naturePrestationMapper.toDto(naturePrestation);
    }

    /**
     * Get all the naturePrestations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<NaturePrestationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NaturePrestations");
        List<NaturePrestationDTO> naturePrestationDTOS = naturePrestationRepository.findAllByDeletedIsFalse().stream()
                .map(naturePrestationMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(naturePrestationDTOS, pageable, naturePrestationDTOS.size());
    }

    /**
     * Get one naturePrestation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NaturePrestationDTO> findOne(Long id) {
        log.debug("Request to get NaturePrestation : {}", id);
        return naturePrestationRepository.findById(id).map(naturePrestationMapper::toDto);
    }

    /**
     * Delete the naturePrestation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NaturePrestation : {}", id);
        naturePrestationRepository.deleteById(id);
    }

    public void deleteNature(NaturePrestationDTO nature) {
        NaturePrestation naturePrestation = naturePrestationMapper.toEntity(nature);
        naturePrestation.setDeleted(true);
        naturePrestationRepository.save(naturePrestation);
        System.out.println("============ " + naturePrestation.getLibelle() + "  " + naturePrestation.isDeleted());
    }

    public List<NaturePrestationDTO> updateAall(List<NaturePrestationDTO> naturePrestationDTOS) {
        naturePrestationDTOS.forEach(naturePrestationDTO -> {
            naturePrestationDTO.setDeleted(true);
        });
        naturePrestationRepository.saveAll(
                naturePrestationDTOS.stream().map(naturePrestationMapper::toEntity).collect(Collectors.toList()));
        List<NaturePrestationDTO> naturePrestationDTOS1 = naturePrestationRepository.findAll().stream()
                .map(naturePrestationMapper::toDto)
                .filter(naturePrestationDTO -> naturePrestationDTO.isDeleted() != null
                        && !naturePrestationDTO.isDeleted())
                .collect(Collectors.toList());
        return naturePrestationDTOS;
    }

    public List<NaturePrestationDTO> findAllNaturePrestation() {
        List<NaturePrestationDTO> lisNature = naturePrestationRepository.findAllByDeletedIsFalse().stream()
                .map(naturePrestationMapper::toDto)
                .collect(Collectors.toList());

        List<NaturePrestationModePassationDTO> naturePrestationModePassationDTOs = naturePrestationModePassationRepository
            .findAllByDeletedIsFalse().stream().map(naturePrestationModePassationMapper::toDto).collect(Collectors.toList());

        lisNature.forEach(naturePrestationDTO -> {
            naturePrestationDTO.setNaturePrestationModePassations(
                naturePrestationModePassationDTOs.stream().filter(n -> n.getNaturePrestationId() != null &&
                    n.getNaturePrestationId().equals(naturePrestationDTO.getId())).collect(Collectors.toList())
            );
        });

        return lisNature;
    }

    public List<NaturePrestationDTO> findAllNaturePrestationList() {
        List<NaturePrestationDTO> lisNature = naturePrestationRepository.findAllByDeletedIsFalse().stream()
            .map(naturePrestationMapper::toDto)
            .collect(Collectors.toList());
        log.debug("naturePrestation listes {}",lisNature);

        return lisNature;
    }
}
