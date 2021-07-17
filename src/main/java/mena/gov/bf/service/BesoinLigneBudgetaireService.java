package mena.gov.bf.service;

import mena.gov.bf.domain.BesoinLigneBudgetaire;
import mena.gov.bf.repository.ActiviteRepository;
import mena.gov.bf.repository.BesoinLigneBudgetaireRepository;
import mena.gov.bf.repository.BesoinRepository;
import mena.gov.bf.service.dto.BesoinLigneBudgetaireDTO;
import mena.gov.bf.service.mapper.BesoinLigneBudgetaireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link BesoinLigneBudgetaire}.
 */
@Service
@Transactional
public class BesoinLigneBudgetaireService {

    private final Logger log = LoggerFactory.getLogger(BesoinLigneBudgetaireService.class);

    private final BesoinLigneBudgetaireRepository besoinLigneBudgetaireRepository;

    private final BesoinLigneBudgetaireMapper besoinLigneBudgetaireMapper;

    private final ActiviteRepository activiteRepository;

    private final BesoinRepository besoinRepository;

    public BesoinLigneBudgetaireService(
            BesoinLigneBudgetaireRepository besoinLigneBudgetaireRepository,
            BesoinLigneBudgetaireMapper besoinLigneBudgetaireMapper,
            ActiviteRepository activiteRepository,
            BesoinRepository besoinRepository) {
        this.besoinLigneBudgetaireRepository = besoinLigneBudgetaireRepository;
        this.besoinLigneBudgetaireMapper = besoinLigneBudgetaireMapper;
        this.besoinRepository = besoinRepository;
        this.activiteRepository = activiteRepository;
    }

    /**
     * Save a besoinLigneBudgetaire.
     *
     * @param besoinLigneBudgetaireDTO the entity to save.
     * @return the persisted entity.
     */
    public BesoinLigneBudgetaireDTO save(BesoinLigneBudgetaireDTO besoinLigneBudgetaireDTO) {
        log.debug("Request to save BesoinLigneBudgetaire : {}", besoinLigneBudgetaireDTO);
        BesoinLigneBudgetaire besoinLigneBudgetaire = besoinLigneBudgetaireMapper.toEntity(besoinLigneBudgetaireDTO);
        besoinLigneBudgetaire = besoinLigneBudgetaireRepository.save(besoinLigneBudgetaire);
        return besoinLigneBudgetaireMapper.toDto(besoinLigneBudgetaire);
    }

    /**
     * Get all the besoinLigneBudgetaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BesoinLigneBudgetaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BesoinLigneBudgetaires");
        return besoinLigneBudgetaireRepository.findAll(pageable)
                .map(besoinLigneBudgetaireMapper::toDto);
    }

    /**
     * Get one besoinLigneBudgetaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BesoinLigneBudgetaireDTO> findOne(Long id) {
        log.debug("Request to get BesoinLigneBudgetaire : {}", id);
        return besoinLigneBudgetaireRepository.findById(id)
                .map(besoinLigneBudgetaireMapper::toDto);
    }

    /**
     * Delete the besoinLigneBudgetaire by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BesoinLigneBudgetaire : {}", id);
        besoinLigneBudgetaireRepository.deleteById(id);
    }

    public Page<BesoinLigneBudgetaireDTO> findAllBesoinLigneBudgetaire(Pageable pageable, Long execiceId, Long directionId) {
        List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaires;
        if (directionId == 0) {
            besoinLigneBudgetaires = besoinLigneBudgetaireRepository.findAll()
                    .stream().filter(besoinLigneBudgetaire -> besoinLigneBudgetaire.getBesoin() != null
                    && besoinLigneBudgetaire.getBesoin().getExercice().getId().equals(execiceId))
                    .map(besoinLigneBudgetaireMapper::toDto).distinct().collect(Collectors.toList());
        } else {
            besoinLigneBudgetaires = besoinLigneBudgetaireRepository.findAll()
                    .stream().filter(besoinLigneBudgetaire -> besoinLigneBudgetaire.getBesoin() != null
                    && besoinLigneBudgetaire.getBesoin().getExercice().getId().equals(execiceId)
                    && besoinLigneBudgetaire.getBesoin().getUniteAdministrative().getId().equals(directionId))
                    .map(besoinLigneBudgetaireMapper::toDto).distinct().collect(Collectors.toList());
        }
        if (!besoinLigneBudgetaires.isEmpty()) {
            besoinLigneBudgetaires.forEach(vale -> {
                vale.setBesoinLibelle(besoinRepository.getOne(vale.getBesoinId()).getLibelle());
                vale.setActiviteLibelle(activiteRepository.getOne(vale.getActiviteId()).getNaturePrestation().getLibelle());
            });
        }
        return new PageImpl<>(besoinLigneBudgetaires, pageable, besoinLigneBudgetaires.size());
    }

    public List<BesoinLigneBudgetaireDTO> findBesoinLigneBudgetaireByActivite(Long activiteId) {
        return besoinLigneBudgetaireRepository.findBesoinLigneBudgetaireByDeletedIsFalse()
                .stream()
                .filter(besoinLigneBudgetaire -> besoinLigneBudgetaire.getActivite() != null && besoinLigneBudgetaire.getActivite().getId().equals(activiteId))
                .map(besoinLigneBudgetaireMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BesoinLigneBudgetaireDTO> findBesoinByActiviteAndLigne(Long activiteId, Long ligneId) {
        return besoinLigneBudgetaireRepository.findBesoinLigneBudgetaireByDeletedIsFalse()
                .stream()
                .map(besoinLigneBudgetaireMapper::toDto)
                .filter(besoinLigneBudgetaire -> besoinLigneBudgetaire.getActiviteId() != null && besoinLigneBudgetaire.getActiviteId().equals(activiteId) && besoinLigneBudgetaire.getLigneBudgetId() != null && besoinLigneBudgetaire.getLigneBudgetId().equals(ligneId))
                .collect(Collectors.toList());
    }

    public List<BesoinLigneBudgetaireDTO> findAllByLigneBudgetaire (Long ligneBudgetaireId) {
        return besoinLigneBudgetaireRepository.findAll().stream().map(besoinLigneBudgetaireMapper::toDto).filter(besoinLigneBudgetaire -> besoinLigneBudgetaire.getLigneBudgetId() !=null &&
            besoinLigneBudgetaire.getLigneBudgetId().equals(ligneBudgetaireId)).collect(Collectors.toList());
    }

    public List<BesoinLigneBudgetaireDTO> findAllBesoinLigneBudgetaireByContrat (List<Long> blblId) {
        List<BesoinLigneBudgetaireDTO> maListe = new ArrayList<>();
        List<BesoinLigneBudgetaire> besoinLigneBudgetaireDTOS =  besoinLigneBudgetaireRepository.findAll();
        blblId.forEach(c -> {
            Optional<BesoinLigneBudgetaireDTO> budgetaireDTO = besoinLigneBudgetaireDTOS.stream().filter(b-> b.getLigneBudget().getId().equals(c)).findFirst().map(besoinLigneBudgetaireMapper::toDto);
            budgetaireDTO.ifPresent(maListe::add);
        });

        return maListe;
    }
}
