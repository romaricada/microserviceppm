package mena.gov.bf.service;

import mena.gov.bf.bean.CandidatLot;
import mena.gov.bf.bean.Lot;
import mena.gov.bf.domain.Activite;
import mena.gov.bf.domain.PpmActivite;
import mena.gov.bf.domain.enumeration.Niveau;
import mena.gov.bf.proxies.CandidatLotRepository;
import mena.gov.bf.proxies.EngagementRepository;
import mena.gov.bf.proxies.LotRepository;
import mena.gov.bf.repository.ActiviteRepository;
import mena.gov.bf.repository.PpmActiviteRepository;
import mena.gov.bf.service.dto.PpmActiviteDTO;
import mena.gov.bf.service.mapper.PpmActiviteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link PpmActivite}.
 */
@Service
@Transactional
public class PpmActiviteService {

    private final Logger log = LoggerFactory.getLogger(PpmActiviteService.class);

    private final PpmActiviteRepository ppmActiviteRepository;

    private final EngagementRepository engagementRepository;

    private final LotRepository lotRepository;

    private final CandidatLotRepository candidatLotRepository;

    private final PpmActiviteMapper ppmActiviteMapper;

    private final ActiviteRepository activiteRepository;
    public PpmActiviteService(PpmActiviteRepository ppmActiviteRepository, EngagementRepository engagementRepository, LotRepository lotRepository, CandidatLotRepository candidatLotRepository, PpmActiviteMapper ppmActiviteMapper, ActiviteRepository activiteRepository) {
        this.ppmActiviteRepository = ppmActiviteRepository;
        this.engagementRepository = engagementRepository;
        this.lotRepository = lotRepository;
        this.candidatLotRepository = candidatLotRepository;
        this.ppmActiviteMapper = ppmActiviteMapper;
        this.activiteRepository = activiteRepository;
    }

    /**
     * Save a ppmActivite.
     *
     * @param ppmActiviteDTO the entity to save.
     * @return the persisted entity.
     */
    public PpmActiviteDTO save(PpmActiviteDTO ppmActiviteDTO) {
        log.debug("Request to save PpmActivite : {}", ppmActiviteDTO);
        PpmActivite ppmActivite = ppmActiviteMapper.toEntity(ppmActiviteDTO);
        ppmActivite = ppmActiviteRepository.save(ppmActivite);
        return ppmActiviteMapper.toDto(ppmActivite);
    }

    /**
     * Get all the ppmActivites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PpmActiviteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PpmActivites");
        return ppmActiviteRepository.findAll(pageable)
            .map(ppmActiviteMapper::toDto);
    }

    /**
     * Get one ppmActivite by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PpmActiviteDTO> findOne(Long id) {
        log.debug("Request to get PpmActivite : {}", id);
        return ppmActiviteRepository.findById(id)
            .map(ppmActiviteMapper::toDto);
    }

    /**
     * Delete the ppmActivite by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PpmActivite : {}", id);
        ppmActiviteRepository.deleteById(id);
    }

    public PpmActiviteDTO saveReport(PpmActiviteDTO ppmActiviteDTO) {
        log.debug("======ppmActiviteDTO==test======={}",ppmActiviteDTO);
        PpmActivite ppmActivite = ppmActiviteMapper.toEntity(ppmActiviteDTO);
        Optional<PpmActivite> ppmActiviteTmp = ppmActiviteRepository.findTopByActiviteId(ppmActiviteDTO.getActiviteId());
        if (ppmActiviteTmp.isPresent()) {
            ppmActivite.setPeriodeLancementAppel(ppmActiviteTmp.get().getPeriodeLancementAppel());
            ppmActivite.setPeriodeRemiseOffre(ppmActiviteTmp.get().getPeriodeRemiseOffre());
            ppmActivite.setTempsNecessaireEvaluationOffre(ppmActiviteTmp.get().getTempsNecessaireEvaluationOffre());
            ppmActivite.setDateProblableDemaragePrestation(ppmActiviteTmp.get().getDateProblableDemaragePrestation());
            ppmActivite.setDelaiExecutionPrevu(ppmActiviteTmp.get().getDelaiExecutionPrevu());
            ppmActivite.setDateButtoire(ppmActiviteTmp.get().getDateButtoire());
            ppmActivite.setPpm(ppmActiviteTmp.get().getPpm());
            ppmActivite.setActivite(ppmActiviteTmp.get().getActivite());
            ppmActivite.setReport(true);
            ppmActivite.setNiveau( Niveau.CENTRAL );

            Activite activite = activiteRepository.getOne(ppmActiviteDTO.getActiviteId());

            if (activite!=null) {
                activite.setReported(true);
                activiteRepository.save(activite);
                ppmActivite = ppmActiviteRepository.save(ppmActivite);
            }
        }

        return ppmActiviteMapper.toDto(ppmActivite);
    }

        public PpmActiviteDTO retiterUneActiviteReporter(PpmActiviteDTO ppmActiviteDTO){
            log.debug("======ppmActiviteDTO==test======={}",ppmActiviteDTO);
            PpmActivite ppmActiviteDTO1 = ppmActiviteRepository.getOne(ppmActiviteDTO.getId());
            Activite activite = activiteRepository.getOne(ppmActiviteDTO1.getActivite().getId());
            ppmActiviteDTO1.setDeleted(true);

            if (activite!=null){
                activite.setReported(false);
            }
            ppmActiviteRepository.delete(ppmActiviteDTO1);
            return ppmActiviteMapper.toDto(ppmActiviteDTO1);
    }

    public List<CandidatLot> findAllAttributaireByLot(Long lotId){
        return candidatLotRepository.findAllByLot(lotId).stream().filter(candidatLot ->  candidatLot.getAttributaire()!=null &&
            candidatLot.getAttributaire().equals(true)).collect(Collectors.toList());
    }

    public List<Lot> findAllLotByActivite(Long activiteId) {
        return lotRepository.findLot().stream().filter(lot -> lot.getActiviteId() != null && lot.getActiviteId().equals(activiteId)).collect(Collectors.toList());
    }
}
