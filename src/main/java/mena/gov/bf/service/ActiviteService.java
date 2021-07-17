package mena.gov.bf.service;

import mena.gov.bf.domain.*;
import mena.gov.bf.domain.enumeration.Niveau;
import mena.gov.bf.proxies.EngagementRepository;
import mena.gov.bf.repository.*;
import mena.gov.bf.service.dto.*;
import mena.gov.bf.service.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Activite}.
 */
@Service
@Transactional
public class ActiviteService {

    private final Logger log = LoggerFactory.getLogger(ActiviteService.class);

    private final ActiviteRepository activiteRepository;
    private final ActiviteMapper activiteMapper;
    private final BesoinLigneBudgetaireRepository besoinLigneBudgetaireRepository;
    private final BesoinLigneBudgetaireMapper besoinLigneBudgetaireMapper;
    private final PpmActiviteRepository ppmActiviteRepository;
    private final PpmActiviteMapper ppmActiviteMapper;

    @Autowired
    BesoinRepository besoinRepository;

    @Autowired
    BesoinService besoinService;

    @Autowired
    BesoinMapper besoinMapper;

    @Autowired
    ReferentielDelaiRepository referentielDelaiRepository;

    @Autowired
    ReferentielDelaiMapper referentielDelaiMapper;

    @Autowired
    PPMRepository ppmRepository;

    @Autowired
    PPMMapper ppmMapper;

    @Autowired
    EngagementRepository engagementRepository;

    @Autowired
    ModePassationRepository modePassationRepository;

    @Autowired
    ModePassationMapper modePassationMapper;

    @Autowired
    NaturePrestationRepository naturePrestationRepository;

    @Autowired
    NaturePrestationMapper naturePrestationMapper;

    @Autowired
    EtapeActivitePpmRepository etapeActivitePpmRepository;

    @Autowired
    EtapeActivitePpmMapper etapeActivitePpmMapper;

    @Autowired
    ExerciceBudgetaireRepository exerciceBudgetaireRepository;

    @Autowired
    LigneBudgetaireMapper ligneBudgetaireMapper;

    @Autowired
    LigneBudgetaireRepository ligneBudgetaireRepository;

    public ActiviteService(ActiviteRepository activiteRepository, ActiviteMapper activiteMapper,
            BesoinLigneBudgetaireRepository besoinLigneBudgetaireRepository,
            BesoinLigneBudgetaireMapper besoinLigneBudgetaireMapper, PpmActiviteRepository ppmActiviteRepository,
            PpmActiviteMapper ppmActiviteMapper) {
        this.activiteRepository = activiteRepository;
        this.activiteMapper = activiteMapper;
        this.besoinLigneBudgetaireRepository = besoinLigneBudgetaireRepository;
        this.besoinLigneBudgetaireMapper = besoinLigneBudgetaireMapper;
        this.ppmActiviteRepository = ppmActiviteRepository;
        this.ppmActiviteMapper = ppmActiviteMapper;
    }

    /**
     * Save a activite.
     *
     * @param activiteDTO the entity to save.
     * @return the persisted entity.
     */
    @Transactional
    public ActiviteDTO save(ActiviteDTO activiteDTO) {
        List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOList = new ArrayList<>();
        List<BesoinDTO> besoinDTOS = activiteDTO.getBesoins();// liste des besoins
        PPMDTO ppmdto = activiteDTO.getPpm();
        ppmdto.setDeleted(false);

        if (ppmdto.getId() == null) {
            ppmdto = ppmMapper.toDto(ppmRepository.save(ppmMapper.toEntity(ppmdto)));
        }
        /**
         * Etape 1: enregistrement de l'activite.
         */
        final Activite activite = activiteRepository.save(activiteMapper.toEntity(activiteDTO));
        /**
         * Etape 2: Mise à jour des besoins avec la ligne budgetaire.
         */

        log.debug("besoinDTOS.size() : {}", besoinDTOS.size());
        if (!besoinDTOS.isEmpty()) {
                List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireExists = besoinLigneBudgetaireRepository.findAllByActiviteIdAndDeletedIsFalse(activite.getId())
                    .stream()
                    .map(besoinLigneBudgetaireMapper::toDto)
                    .collect(Collectors.toList());
                log.debug("besoinLigneBudgetaireExists.size() ========>> {}", besoinLigneBudgetaireExists.size());
                // Mise à jour de la liste besoins lignes existant dans la base de donnée avec deleted = true.
            if (!besoinLigneBudgetaireExists.isEmpty()) {
                besoinLigneBudgetaireExists.forEach(b -> b.setDeleted(true));
                besoinLigneBudgetaireRepository
                        .saveAll(besoinLigneBudgetaireMapper.toEntity(besoinLigneBudgetaireExists));
            }

            besoinDTOS.forEach(besoinDTO -> {
                besoinDTO.setUsed(true);
                List<LigneBudgetaire> ligneBudgetaires = besoinLigneBudgetaireRepository.findAllByBesoinIdAndDeletedIsFalse(besoinDTO.getId())
                    .stream().map(BesoinLigneBudgetaire::getLigneBudget).collect(Collectors.toList());
                    if (!ligneBudgetaires.isEmpty()) {
                        ligneBudgetaires.forEach(ligneBudgetaire -> {
                            final List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOS = besoinLigneBudgetaireExists
                                .stream().filter(b -> b.getActiviteId() != null && b.getLigneBudgetId() != null
                                    && b.getLigneBudgetId().equals(ligneBudgetaire.getId())
                                    && b.getActiviteId().equals(activite.getId())).collect(Collectors.toList());

                            if (besoinLigneBudgetaireDTOS.isEmpty()){
                                List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOList1 = besoinLigneBudgetaireMapper.toDto(besoinLigneBudgetaireRepository.
                                    findBesoinLigneBudgetaireByBesoinIdAndLigneBudgetId(besoinDTO.getId(), ligneBudgetaire.getId()));
                                BesoinLigneBudgetaireDTO besoinLigneBudgetaireDTO = besoinLigneBudgetaireDTOList1.get(0);
                                //besoinLigneBudgetaireDTO.set
                                besoinLigneBudgetaireDTO.setDeleted(false);
                                besoinLigneBudgetaireDTO.setBesoinId(besoinDTO.getId());
                                besoinLigneBudgetaireDTO.setLigneBudgetId(ligneBudgetaire.getId());
                                besoinLigneBudgetaireDTO.setActiviteId(activite.getId());
                                besoinLigneBudgetaireDTO.setBudget(ligneBudgetaire.getBudget());
                               // besoinLigneBudgetaireDTO.setMontantEstime(ligneBudgetaireDTO.getMontantEstime());
                                besoinLigneBudgetaireDTO.setLigneCredit(ligneBudgetaire.getLigneCredit());
                                besoinLigneBudgetaireDTOList.add(besoinLigneBudgetaireDTO);
                            } else {
                                List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOs = besoinLigneBudgetaireDTOList.stream()
                                    .filter(b -> b.getActiviteId() != null && b.getLigneBudgetId() != null
                                        && b.getLigneBudgetId().equals(ligneBudgetaire.getId())
                                        && b.getActiviteId().equals(activite.getId()))
                                    .collect(Collectors.toList());
                                besoinLigneBudgetaireDTOs.forEach(b -> {
                                    b.setDeleted(false);
                                    b.setActiviteId(activite.getId());
                                });

                                besoinLigneBudgetaireDTOList.addAll(besoinLigneBudgetaireDTOs);
                            }
                        });
                    } else {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Aucune ligne budgetaire n'a été associé au besoin");
                    }
            });
            besoinRepository.saveAll(besoinMapper.toEntity(besoinDTOS));
            besoinLigneBudgetaireRepository.saveAll(besoinLigneBudgetaireMapper.toEntity(besoinLigneBudgetaireDTOList));

        }

        PpmActiviteDTO ppmActiviteDTO = activiteDTO.getPpmActivite();
        ppmActiviteDTO.setActiviteId(activite.getId());
        ppmActiviteDTO.setPpmId(ppmdto.getId());
        ppmActiviteDTO.setExerciceId(ppmdto.getIdExercice());
        ppmActiviteDTO.setNiveau(Niveau.CENTRAL);
        ppmActiviteDTO.setDeleted(false);
        PpmActivite ppmActivite = ppmActiviteRepository.save(ppmActiviteMapper.toEntity(ppmActiviteDTO));

        if (!activiteDTO.getReferentielDelais().isEmpty()) {
            if (activiteDTO.getId() != null) {
                List<EtapeActivitePpm> etapeActivitePpms = etapeActivitePpmRepository.findEtapeActivitePpmByPpmActiviteIdAndDeletedIsFalse(activiteDTO.getPpmActivite().getId());
                if (!etapeActivitePpms.isEmpty()) {
                    etapeActivitePpms.forEach(etapeActivitePpm -> {
                        etapeActivitePpm.setDeleted(true);
                    });
                    etapeActivitePpmRepository.saveAll(etapeActivitePpms);
                }
            }
            saveAllEtapeActivitePPM(activiteDTO, ppmActivite);
        }
        List<LigneBudgetaire> ligneBudgetaireExist = ligneBudgetaireRepository.findLigneBudgetaireByDeletedIsFalse();
        if(!ligneBudgetaireExist.isEmpty()) {
            ligneBudgetaireExist.forEach(ligneBudgetaire -> {
               // ligneBudgetaire.setDeleted(true);
            });
            ligneBudgetaireRepository.saveAll(ligneBudgetaireExist);
        }
        return activiteMapper.toDto(activite);
    }

    //@Transactional(readOnly = true)
    public void saveAllEtapeActivitePPM(ActiviteDTO activiteDTO, PpmActivite ppmActivite) {

        // PpmActiviteDTO ppmActiviteDTO = ppmActiviteMapper.toDto(ppmActivite);

        List<EtapeActivitePpmDTO> etapeActivitePpmDTOList = new ArrayList<>();

        //ActiviteDTO activiteDTO = activiteMapper.toDto(activite);
        activiteDTO.getReferentielDelais().forEach(etapeActiviteppm -> {
            log.debug(":::::::::debut:::::::::       {}      :::::::::::fin::::::       {}      :::::::::::", etapeActiviteppm.getDebut(), etapeActiviteppm.getFin());
            EtapeActivitePpmDTO etapeActivitePpmDTO = new EtapeActivitePpmDTO();
            etapeActivitePpmDTO.setEtapeId(etapeActiviteppm.getEtape().getId());
            etapeActivitePpmDTO.setDebut(etapeActiviteppm.getDebut());
            etapeActivitePpmDTO.setFin(etapeActiviteppm.getFin());
            etapeActivitePpmDTO.setPpmActiviteId(ppmActivite.getId());
            etapeActivitePpmDTO.setDeleted(false);

            etapeActivitePpmDTOList.add(etapeActivitePpmDTO);
        });
        // referentielDelaiRepository.saveAll( referentielDelaiMapper.toEntity( activiteDTO.getReferentielDelais() ) );
        etapeActivitePpmMapper.toDto(etapeActivitePpmRepository.saveAll(etapeActivitePpmMapper.toEntity(etapeActivitePpmDTOList)));
    }

    /**
     * Get all the activites.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ActiviteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Activites");

        List<ActiviteDTO> activiteDTOS = activiteRepository.findAll(pageable).stream()
                .filter(activite -> activite.isDeleted() != null && !activite.isDeleted()).map(activiteMapper::toDto)
                .collect(Collectors.toList());

        List<BesoinDTO> besoinDTOS = besoinService.findAllBesoins();

        List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOList = besoinLigneBudgetaireRepository.findAll().stream()
                .map(besoinLigneBudgetaireMapper::toDto)
                .filter(besoinLigneBudgetaireDTO -> besoinLigneBudgetaireDTO.isDeleted() != null
                        && !besoinLigneBudgetaireDTO.isDeleted())
                .collect(Collectors.toList());

        activiteDTOS.forEach(activiteDTO -> {

            PpmActiviteDTO ppmActiviteDTO = ppmActiviteMapper.toDto( ppmActiviteRepository.findByActiviteIdAndDeletedIsFalse( activiteDTO.getId() ) );

            activiteDTO.setPpmActivite( ppmActiviteDTO );
            if (ppmActiviteDTO != null) {
                Optional<PPM> ppm = ppmRepository.findById(ppmActiviteDTO.getPpmId());
                activiteDTO.setPpm(ppm != null ? ppmMapper.toDto(ppm.get()) : new PPMDTO());
            }
            Optional<ModePassation> modePassationDTO = modePassationRepository.findById(activiteDTO.getPassationId());
            activiteDTO.setModePassation(modePassationDTO.isPresent() ? modePassationMapper.toDto(modePassationDTO.get())
                            : new ModePassationDTO());

            Optional<NaturePrestation> naturePrestation = naturePrestationRepository
                    .findById(activiteDTO.getNaturePrestationId());
            activiteDTO.setNaturePrestation(
                    naturePrestation.isPresent() ? naturePrestationMapper.toDto(naturePrestation.get())
                            : new NaturePrestationDTO());

            List<BesoinDTO> besoinDTOS1 = new ArrayList<>();
            besoinLigneBudgetaireDTOList.forEach(besoinLigneBudgetaireDTO -> {
                // List<BesoinDTO> besoinDTOS1 = new ArrayList<>();
                if (besoinLigneBudgetaireDTO != null && besoinLigneBudgetaireDTO.getActiviteId() != null
                        && besoinLigneBudgetaireDTO.getActiviteId().equals(activiteDTO.getId())) {
                    besoinDTOS1.addAll(besoinDTOS.stream()
                            .filter(besoinDTO -> besoinDTO.getId().equals(besoinLigneBudgetaireDTO.getBesoinId()))
                            .collect(Collectors.toList()));
                }
            });
            activiteDTO.setBesoins(besoinDTOS1);

        });

        return new PageImpl<>(activiteDTOS, pageable, activiteDTOS.size());

    }

    /**
     * Get one activite by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ActiviteDTO> findOne(Long id) {
        log.debug("Request to get Activite : {}", id);
        return activiteRepository.findById(id).map(activiteMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Optional<Activite> findById(Long id) {
        log.debug("Request to get Activite : {}", id);
        return activiteRepository.findById(id);
    }

    /**
     * Delete the activite by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Activite : {}", id);
        activiteRepository.deleteById(id);
    }

    public List<Activite> findAllByBesoinLigneBudgetaireAndPpmActivite(List<Activite> activites) {
        List<Activite> activiteList = activiteRepository.findAll().stream()
                .filter(activite -> activite.getPpmActivites() != null && activite.getBesoinLignes() != null)
                .collect(Collectors.toList());
        return activiteList;
    }

    public List<ActiviteDTO> findAllActiviteByAnneBudgetaire(Long idAnnee) {
        return this.initActiviteElements(this.getActiviteByAnnee(idAnnee, false), false);
    }

    public List<ActiviteDTO> findAllActiviteDeconcentreByAnneBudgetaire(Long idAnnee) {
        return this.initActiviteElements(this.getActiviteDeconcentreByAnnee(idAnnee, false), false);
    }

    private List<ActiviteDTO> getActiviteByAnnee(Long idAnnee, boolean isReport) {
        return ppmActiviteRepository.findAll().stream()
                .filter(value -> value.getPpm().getIdExercice() != null
                        && value.getPpm().getIdExercice().equals(idAnnee) && value.getNiveau() != null
                        && value.getNiveau().equals(Niveau.CENTRAL) && value.getReport() != null
                        && value.getReport().equals(isReport) && value.isDeleted() != null && !value.isDeleted())
                .map(PpmActivite::getActivite).map(activiteMapper::toDto).collect(Collectors.toList());
    }


    private List<ActiviteDTO> getActiviteDeconcentreByAnnee(Long idAnnee, boolean isReport) {
        return ppmActiviteRepository.findAll().stream()
                .filter(value -> value.getPpm().getIdExercice() != null
                        && value.getPpm().getIdExercice().equals(idAnnee) && value.getNiveau() != null
                        && value.getNiveau().equals(Niveau.DECONCENTRE) && value.getReport() != null
                        && value.getReport().equals(isReport) && value.isDeleted() != null && !value.isDeleted())
                .map(PpmActivite::getActivite).map(activiteMapper::toDto).collect(Collectors.toList());
    }

    public List<ActiviteDTO> getActiviteByAnneeNew(Long idAnnee) {
        return ppmActiviteRepository.findAll().stream()
            .filter(value -> value.getPpm().getIdExercice() != null
                && value.getPpm().getIdExercice().equals(idAnnee) && value.isDeleted() != null && !value.isDeleted())
            .map(PpmActivite::getActivite).map(activiteMapper::toDto).collect(Collectors.toList());
    }

    private List<ActiviteDTO> initActiviteElements(List<ActiviteDTO> activiteDTOS, boolean isReport) {
        activiteDTOS.forEach(activiteDTO -> {
            Set<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOList = besoinLigneBudgetaireRepository
                    .findAllByActiviteIdAndDeletedIsFalse(activiteDTO.getId()).stream()
                    .map(besoinLigneBudgetaireMapper::toDto).collect(Collectors.toSet());
            besoinLigneBudgetaireDTOList.forEach(besoin -> {
                System.out.println("==========================");
                System.out.println(besoin);
                System.out.println("==========================");
            });
            /*
             * if (!besoinLigneBudgetaireDTOList.isEmpty()) { Double total =
             * besoinLigneBudgetaireDTOList.stream() .mapToDouble(
             * BesoinLigneBudgetaireDTO::getMontantEstimeLigneBudget ).sum();
             * activiteDTO.setTotal( total ); activiteDTO.setBesoinLignes(
             * besoinLigneBudgetaireDTOList ); } else { activiteDTO.setTotal( 0.0 ); }
             */
            activiteDTO.setPpmActivite(this.findPpmActiviteByActivite(activiteDTO.getId(), isReport));
        });
        return activiteDTOS;
    }

    public List<ActiviteDTO> findAllActiviteReportedByAnne(Long idAnnee, boolean isReport) {
        List<ActiviteDTO> activites;
        if (!isReport) {
            activites = this.getActiviteByAnnee(idAnnee, false).stream().filter(activiteDTO -> activiteDTO.getReported().equals(false)).collect(Collectors.toList());
        } else {
            activites = this.getActiviteByAnnee(idAnnee, true);
        }

        return this.initActiviteElements(activites, isReport);
    }

    private PpmActiviteDTO findPpmActiviteByActivite(Long id, boolean isReport) {
        Optional<PpmActiviteDTO> ppmActiviteDTO = ppmActiviteRepository.findAllByActiviteIdAndDeletedIsFalse(id)
                .stream().filter(value -> value.getReport() != null && value.getReport().equals(isReport))
                .map(ppmActiviteMapper::toDto).findFirst();
        return ppmActiviteDTO.orElse(null);
    }

    private List<ActiviteDTO> findActiviteById(Long activiteId) {
        return activiteRepository.findAll().stream()
                .filter(value -> value.getId() != null && value.getId().equals(activiteId)).map(activiteMapper::toDto)
                .collect(Collectors.toList());
    }

    public ActiviteDTO generateCodeLignePlan() {
        Activite activite = activiteRepository.findTop1ByOrderByCodeLignePlanDesc();

        log.debug("========activite==========       {}      ========activite=======", activite);

        ExerciceBudgetaire exerciceBudgetaire = exerciceBudgetaireRepository.findTop1ByOrderByAnneeDesc();

        Optional<PPM> ppmList = ppmRepository.findTop1ByIdExercice(exerciceBudgetaire.getId());

        ActiviteDTO activiteDTO = new ActiviteDTO();

        if (ppmList.isPresent()) {
            if (activite != null ) {
                String[] code = activite.getCodeLignePlan().split("/");
                activiteDTO.setCodeLignePlan(code[0].concat("/").concat(code[1]).concat("/") + (Integer.parseInt(code[2]) + 1));
            } else {
                activiteDTO.setCodeLignePlan(ppmList.get().getReferencePlan().concat("/1"));
            }
        } else {
            activiteDTO.setCodeLignePlan(exerciceBudgetaire.getAnnee().toString().concat(".23/1/1"));
        }
        log.debug("==========================<<<<       {}      >>>>>>>>>>>>>================", activiteDTO);
        return activiteDTO;
    }

    /*public String deleteAll(List<ActiviteDTO> activiteDTOList) {

        log.debug("---------------      {}      ----------------", activiteDTOList.size());

        AtomicReference<String> delete =  new AtomicReference<String>("");

        List<PpmActiviteDTO> ppmActiviteDTOs = ppmActiviteRepository.findAll().stream().map(ppmActiviteMapper::toDto).collect(Collectors.toList());

        activiteDTOList.forEach(activiteDTO -> {
            if (ppmActiviteDTOs.stream().map(ppmActiviteMapper::toEntity).map(PpmActivite::getActivite).collect(Collectors.toList()).stream().map(activiteMapper::toDto).collect(Collectors.toList()).contains(activiteDTO)){
               delete.set("NON");
            }
        });

        if (delete.get().equals("OK")) {
            activiteDTOList.forEach(activiteDTO -> activiteDTO.setDeleted(true));
            activiteRepository.saveAll(activiteMapper.toEntity(activiteDTOList));
        }

        return delete.get();

    }*/

    public List<ActiviteDTO> deleteAll(List<ActiviteDTO> activiteDTOList) {
        activiteDTOList.forEach( activiteDTO -> {
            activiteDTO.setDeleted( true );
        } );
        activiteRepository.saveAll( activiteDTOList.stream().map( activiteMapper::toEntity ).collect( Collectors.toList() ) );
        List<ActiviteDTO> activiteDTOList1 = activiteRepository.findAll().stream().map( activiteMapper::toDto ).filter( activiteDTO ->
            activiteDTO.isDeleted() != null && !activiteDTO.isDeleted() ).collect( Collectors.toList() );
        return activiteDTOList1;
    }

}
