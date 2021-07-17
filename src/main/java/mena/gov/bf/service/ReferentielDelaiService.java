package mena.gov.bf.service;


import mena.gov.bf.domain.*;
import mena.gov.bf.repository.ExerciceBudgetaireRepository;
import mena.gov.bf.repository.JourFerierRepository;
import mena.gov.bf.repository.ReferentielDelaiRepository;
import mena.gov.bf.service.dto.*;
import mena.gov.bf.service.mapper.ExerciceBudgetaireMapper;
import mena.gov.bf.service.mapper.JourFerierMapper;
import mena.gov.bf.service.mapper.ReferentielDelaiMapper;

import mena.gov.bf.repository.*;
import mena.gov.bf.service.mapper.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link ReferentielDelai}.
 */
@Service
@Transactional
public class ReferentielDelaiService {

    private final Logger log = LoggerFactory.getLogger( ReferentielDelaiService.class );

    private final ReferentielDelaiRepository referentielDelaiRepository;

    private final ReferentielDelaiMapper referentielDelaiMapper;

    private final ModePassationRepository modePassationRepository;

    private final ModePassationMapper modePassationMapper;


    @Autowired
    ExerciceBudgetaireMapper exerciceBudgetaireMapper;

    @Autowired
    ExerciceBudgetaireRepository exerciceBudgetaireRepository;

    @Autowired
    NormeReferenceRepository normeReferenceRepository;

    @Autowired
    NormeReferenceMapper normeReferenceMapper;

    @Autowired
    CalculDelaiRepository calculDelaiRepository;

    @Autowired
    CalculDelaiMapper calculDelaiMapper;

    @Autowired
    CalculDelaiService calculDelaiService;

    @Autowired
    EtapeActivitePpmRepository etapeActivitePpmRepository;

    @Autowired
    EtapeActivitePpmMapper etapeActivitePpmMapper;


    private final ActeurRepository acteurRepository;

    private final ActeurMapper acteurMapper;

    private final EtapeRepository etapeRepository;

    private final EtapeMapper etapeMapper;

    private final JourFerierRepository jourFerierRepository;

    private final JourFerierMapper jourFerierMapper;

    public ReferentielDelaiService(ReferentielDelaiRepository referentielDelaiRepository, ReferentielDelaiMapper referentielDelaiMapper,
                                   ModePassationRepository modePassationRepository, ModePassationMapper modePassationMapper,
                                   ActeurRepository acteurRepository, ActeurMapper acteurMapper, EtapeRepository etapeRepository,
                                   EtapeMapper etapeMapper,NormeReferenceRepository normeReferenceRepository,NormeReferenceMapper normeReferenceMapper,
                                   JourFerierRepository jourFerierRepository, JourFerierMapper jourFerierMapper
    ) {

        this.referentielDelaiRepository = referentielDelaiRepository;
        this.referentielDelaiMapper = referentielDelaiMapper;
        this.modePassationRepository = modePassationRepository;
        this.modePassationMapper = modePassationMapper;
        this.acteurRepository = acteurRepository;
        this.acteurMapper = acteurMapper;
        this.normeReferenceRepository = normeReferenceRepository;
        this.normeReferenceMapper = normeReferenceMapper;
        this.etapeRepository = etapeRepository;
        this.etapeMapper = etapeMapper;
        this.jourFerierRepository = jourFerierRepository;
        this.jourFerierMapper = jourFerierMapper;
    }

    /**
     * Save a referentielDelai.
     *
     * @param referentielDelaiDTO the entity to save.
     * @return the persisted entity.
     */
    public ReferentielDelaiDTO save(ReferentielDelaiDTO referentielDelaiDTO) {
        log.debug( "Request to save ReferentielDelai : {}", referentielDelaiDTO );
        ReferentielDelai referentielDelai = referentielDelaiMapper.toEntity( referentielDelaiDTO );
        referentielDelai = referentielDelaiRepository.save( referentielDelai );
        return referentielDelaiMapper.toDto( referentielDelai );
    }


    /**
     * Get all the referentielDelais.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ReferentielDelaiDTO> findAll(Pageable pageable) {
        log.debug( "Request to get all ReferentielDelais" );
        List<NormeReferenceDTO> normeReferenceDTOS = normeReferenceRepository.findAll().stream().map(normeReferenceMapper::toDto).collect(Collectors.toList());
        List<ReferentielDelaiDTO> referentielDelaiDTOList = referentielDelaiRepository.findAll()
            .stream()
            .map( referentielDelaiMapper::toDto )
            .filter( referentielDelaiDTO -> referentielDelaiDTO.isDeleted() != null && !referentielDelaiDTO.isDeleted() )
            .collect( Collectors.toList() );
        referentielDelaiDTOList.forEach(referentielDelaiDTO -> {
            if (referentielDelaiDTO.getNormeReference() != null) {
                List<NormeReferenceDTO> normeReferenceDTOSTMP = normeReferenceDTOS
                    .stream()
                    .filter(normeReferenceDTO -> normeReferenceDTO.getId().equals(referentielDelaiDTO.getNormeReference().getId()))
                    .collect(Collectors.toList());
                if (!normeReferenceDTOSTMP.isEmpty()) {
                    referentielDelaiDTO.setNormeReference(
                        normeReferenceDTOSTMP.get(0)
                    );
                }
            }
        });
        return new PageImpl<>( referentielDelaiDTOList, pageable, referentielDelaiDTOList.size() );
    }

    /**
     * Get one referentielDelai by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ReferentielDelaiDTO> findOne(Long id) {
        log.debug( "Request to get ReferentielDelai : {}", id );
        return referentielDelaiRepository.findById( id )
            .map( referentielDelaiMapper::toDto );
    }

    /**
     * Delete the referentielDelai by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug( "Request to delete ReferentielDelai : {}", id );
        referentielDelaiRepository.deleteById( id );
    }

    public List<ReferentielDelaiDTO> updateAll(List<ReferentielDelaiDTO> referentielDelaiDTOS) {
        referentielDelaiDTOS.forEach( referentielDelaiDTO -> {
            referentielDelaiDTO.setDeleted( true );

        } );
        referentielDelaiRepository.saveAll( referentielDelaiDTOS.stream().map( referentielDelaiMapper::toEntity ).collect( Collectors.toList() ) );
        List<ReferentielDelaiDTO> referentielDelaiDTOS1 = referentielDelaiRepository.findAll().stream().map( referentielDelaiMapper::toDto ).filter( referentielDelaiDTO ->
            referentielDelaiDTO.isDeleted() != null && !referentielDelaiDTO.isDeleted() ).collect( Collectors.toList() );
        return referentielDelaiDTOS1;
    }

    public List<ReferentielDelaiDTO> findReferentielDelaiByModePassationId(Long modePassationId) {

        List<NormeReferenceDTO> normeReferenceDTOList = normeReferenceMapper.toDto(normeReferenceRepository.findNormeReferenceByDeletedIsFalse());

        List<ReferentielDelaiDTO> referentielDelaiDTOList = referentielDelaiRepository.findAllByModePassation_IdAndDeletedIsFalse( modePassationId )
            .stream()
            .map( referentielDelaiMapper::toDto )
            .collect( Collectors.toList() );

        referentielDelaiDTOList.forEach(referentielDelaiDTO -> {
            List<NormeReferenceDTO> referenceDTOList = normeReferenceDTOList.stream().filter(n -> n.getId().equals(referentielDelaiDTO.getNormeReference().getId())).collect(Collectors.toList());
            if (!referenceDTOList.isEmpty()) {
                referentielDelaiDTO.setNormeReference(referenceDTOList.get(0));
            }
        });

        return referentielDelaiDTOList;

    }

    public List<EtapeDTO> findEtapeByModePassationId(Long modePassationId) {

        return referentielDelaiRepository.findAllByModePassation_IdAndDeletedIsFalse( modePassationId )
            .stream()
            .map(ReferentielDelai::getEtape)
            .map( etapeMapper::toDto )
            .collect( Collectors.toList());
    }

    @Transactional
    public List<ReferentielDelaiDTO> calculerDelaisEtape(ReferentielDelaiDTO referentielDelaiDTO) {

        return calculerDelaiByModePassation(referentielDelaiDTO.getModePassation().getId(), referentielDelaiDTO.getFin(), referentielDelaiDTO.getObservation());

    }

    @Transactional
    public List<ReferentielDelaiDTO> calculerDelaiByModePassation( Long modePassationId, LocalDate dateButoire, String libelle) {

        log.debug(":::::::::::::       {}     ::::::::::::::::      {}      ::::::::::::::::   {}   ::::::", modePassationId, dateButoire, libelle);

        List<ReferentielDelaiDTO> referentielDelaiDTOList = new ArrayList<>();

        List<EtapeDTO> etapeDTOS = getEtapesByLibelle(libelle);

        log.debug("etapeDTOS          {}          etapeDTOS", etapeDTOS);

        List<ReferentielDelaiDTO> referentielDelais = getReferentieDelais(etapeDTOS, modePassationId);

        log.debug("=================1==============          {}          =================1==============", referentielDelais);

        ReferentielDelaiDTO referenceDTO = new ReferentielDelaiDTO();
        referenceDTO.setFin(dateButoire);

        List<NormeReferenceDTO> normeReferenceDTOS = normeReferenceRepository.findAll()
            .stream()
            .filter(n -> n.isDeleted() != null && !n.isDeleted())
            .map(normeReferenceMapper::toDto)
            .collect(Collectors.toList());

        normeReferenceDTOS.forEach(normeReferenceDTO -> {

            normeReferenceDTO.setReferentielDelais(
                referentielDelais.stream()
                    .filter(ref -> ref.getNormeReference() != null && ref.getNormeReference().getId() != null && ref.getNormeReference().getId().equals(normeReferenceDTO.getId()))
                    .collect(Collectors.toList())
            );
            if (!normeReferenceDTO.getReferentielDelais().isEmpty()) {
                normeReferenceDTO.getReferentielDelais().forEach(referentielDelaiDTO -> {
                    referentielDelaiDTO.setLibelleDate(libelle);
                    referentielDelaiDTO.setFin(referenceDTO.getFin());

                    if (normeReferenceDTO.getReferentiel() != null) {
                        if (normeReferenceDTO.isNormeOuvrable()) {
                            referentielDelaiDTO.setDebut(decalerParJourFerierReverse(referentielDelaiDTO.getFin(), referentielDelaiDTO.getNormeReference().getReferentiel()));
                        } else {
                            referentielDelaiDTO.setDebut(referentielDelaiDTO.getFin().minusDays(normeReferenceDTO.getReferentiel()));
                        }

                        referentielDelaiDTO.setDuration(normeReferenceDTO.getReferentiel());
                    } else {
                        if (normeReferenceDTO.getReferentielMax() != null) {
                            if (normeReferenceDTO.isNormeOuvrable()) {
                                referentielDelaiDTO.setDebut(decalerParJourFerierReverse(referentielDelaiDTO.getFin(), referentielDelaiDTO.getNormeReference().getReferentielMax()));
                            } else {
                                referentielDelaiDTO.setDebut(referentielDelaiDTO.getFin().minusDays(normeReferenceDTO.getReferentielMax()));
                            }
                            // referentielDelaiDTO.setDebut(referentielDelaiDTO.getFin().minusDays(normeReferenceDTO.getReferentielMax()));
                            referentielDelaiDTO.setDuration(normeReferenceDTO.getReferentielMax() - normeReferenceDTO.getReferentielMin());
                        }
                    }

                    referenceDTO.setFin(referentielDelaiDTO.getDebut());
                });
                referentielDelaiDTOList.addAll(normeReferenceDTO.getReferentielDelais());
            }
        });

        log.debug("================2===============          {}          =================2==============", referentielDelaiDTOList);


        return referentielDelaiDTOList;
    }

    /* public List<ReferentielDelaiDTO> getRefDelaisByAllNormeReference(List<NormeReferenceDTO> normeReferenceDTOS, List<ReferentielDelaiDTO> referentielDelais) {

    } */

    public List<ReferentielDelaiDTO> getReferentieDelais(List<EtapeDTO> etapeDTOS, Long modePassationId) {

        log.debug("================3===============          {}          =================3==============", etapeDTOS);

        List<ReferentielDelaiDTO> referentielDelais = new ArrayList<>();

        List<ReferentielDelaiDTO> referentielDelaiDTOS = referentielDelaiRepository.findAllByModePassation_IdAndDeletedIsFalse(modePassationId)
            .stream()
            .map(referentielDelaiMapper::toDto)
            .filter(ref -> ref.getEtape() != null && ref.getEtape().getOrdre() != null)
            .sorted(Comparator.comparing((ReferentielDelaiDTO r) -> r.getEtape().getOrdre()).reversed())
            .collect(Collectors.toList());

        log.debug("================4===============          {}          =================4==============", referentielDelaiDTOS.size());

        etapeDTOS.forEach(etapeDTO -> {
            referentielDelais.addAll(
                referentielDelaiDTOS.stream().filter(ref -> ref.getEtape() != null && ref.getEtape().equals(etapeDTO))
                    .collect(Collectors.toList())
            );
        });
        log.debug("================4===============          {}          =================4==============", etapeDTOS);
        return referentielDelais;
    }

    public List<EtapeDTO> getEtapesByLibelle(String libelle) {
        return calculDelaiRepository.findCalculDelaiByLibelleAndDeletedIsFalse(libelle).stream()
            //.map(calculDelaiMapper::toDto)
            .map(CalculDelai::getEtape)
            .map(etapeMapper::toDto)
            .collect(Collectors.toList());
    }

    public List<DateCalcule> setDateCalcule(List<ReferentielDelaiDTO> referentielDelaiDTOS) {
        ModePassationDTO modePassation = new ModePassationDTO();
        if (!referentielDelaiDTOS.isEmpty()) {
            modePassation = referentielDelaiDTOS.get(0).getModePassation();
        }

        log.debug("$$$$$$$$$$$$$$$$$$$$        {}      *********************", referentielDelaiDTOS.size());

        // List<ReferentielDelaiDTO> referentielDelaiDTOS1 = new ArrayList<>();

        String[] dateCalcules = {"PERIODELANCEMENTAPPELCONCURENCE", "PERIODEREMISEOFFRES", "DATEPROBABLEDEMARAGEPRESTATIONS"};

        List<DateCalcule> dateCalculeList = new ArrayList<>();

        List<EtapeDTO> etapeDTOS = new ArrayList<>();

        for (String calcule : dateCalcules) {
            DateCalcule date = new DateCalcule();
            date.setLibelle(calcule);

            etapeDTOS = getEtapesByLibelle(calcule);
            List<ReferentielDelaiDTO> referentielDelais = getReferentieDelais(etapeDTOS, modePassation.getId());
            List<ReferentielDelaiDTO> referentielDelaiDTOList = new ArrayList<>();

            referentielDelais.forEach(ref -> {
                referentielDelaiDTOList.addAll(referentielDelaiDTOS.stream().filter(r -> r.getId() != null && r.getId().equals(ref.getId())).collect(Collectors.toList()));
            });

            log.debug("aaaaaaaaaaaaa        {}      pppppppppppppp", referentielDelaiDTOList);

            if (!referentielDelaiDTOList.isEmpty()) {
                date.setDate(referentielDelaiDTOList.get(referentielDelaiDTOList.size() -1).getFin());

                dateCalculeList.add(date);
            }
        }

        // List<ReferentielDelaiDTO> referentielDelais = getReferentieDelais(etapeDTOS, modePassationId);


        return dateCalculeList;
    }


    public Integer getTemps(int a, int b) {
        return (b - a) > 0 ? b - a : 0;
    }

    public LocalDate decalerParJourFerier(LocalDate dateDebut, LocalDate dateFin) {

        for (LocalDate debut = dateDebut; debut.isBefore(dateFin); debut = debut.plusDays(1)) {

            if (DayOfWeek.from(debut).getValue() == 6 ) {
                dateFin = dateFin.plusDays(1);
            }
            if ( DayOfWeek.from(debut).getValue() == 7) {
                dateFin = dateFin.plusDays(1);
            }
        }
        return dateFin;

    }

    public LocalDate decalerParJourFerierReverse(LocalDate dateFin, Integer delais) {

        LocalDate dateDebut = dateFin.minusDays(delais);

        for (LocalDate jour = dateFin; jour.isAfter(dateDebut); jour = jour.minusDays(1)) {

            if (DayOfWeek.from(jour).getValue() == 6 ) {
                dateDebut = dateDebut.minusDays(1);
            }
            if ( DayOfWeek.from(jour).getValue() == 7) {
                dateDebut = dateDebut.minusDays(1);
            }
        }
        return dateDebut;

    }

    public Integer countJourFerier(Long etapeId, Long modeId) {

        int nb = 0;

        ReferentielDelaiDTO referentielDelai = referentielDelaiMapper.toDto(referentielDelaiRepository.findReferentielDelaiByEtapeIdAndModePassationIdAndDeletedIsFalse(etapeId, modeId));

        List<JourFerierDTO> jourFerierDTOList = jourFerierRepository.findAll()
            .stream()
            .map(jourFerierMapper::toDto)
            .filter(jourFerierDTO -> jourFerierDTO.isDeleted() != null && !jourFerierDTO.isDeleted())
            .collect(Collectors.toList());

        if (!jourFerierDTOList.isEmpty() && referentielDelai != null && referentielDelai.getDebut() != null && referentielDelai.getFin() != null) {

            for ( LocalDate ldate = referentielDelai.getDebut(); ldate.isBefore(referentielDelai.getFin()); ldate = ldate.plusDays(1)) {
                for ( LocalDate j = jourFerierDTOList.get(0).getJour(); j.isBefore(jourFerierDTOList.get(jourFerierDTOList.size() -1 ).getJour()); j = j.plusDays(1)) {
                    if (ldate.equals(j)) {
                        nb++;
                    }
                };

            }

        }

        return nb;

    }

    private Acteur createActeur(Acteur acteur) {
        // String finalLibelle = acteur.getLibelle().toLowerCase().replaceAll( " ", "" );
        Optional<Acteur> optionalActeur = acteurRepository.findById(acteur.getId()); //.stream()
            // .filter( vale -> vale.getLibelle().toLowerCase().replaceAll( " ", "" ).equals( finalLibelle ) ).findFirst();
        if (optionalActeur.isPresent()) {
            return optionalActeur.get();
        } else {
            acteur = acteurRepository.save( acteur );
            return acteur;
        }
    }


    @Transactional
    public Long saveListeReferencielDelai(List<ReferentielDelaiDTO> referentielDelaiDTOS) {
        referentielDelaiDTOS.forEach(referentielDelaiDTO -> {

        });

        if (!referentielDelaiDTOS.isEmpty()) {
            List<ReferentielDelai> referentielDelaiList = new ArrayList<>();
            ModePassation modePassation = modePassationRepository.getOne( referentielDelaiDTOS.get( 0 ).getModePassationId() );
           /* List<ReferentielDelai> referentielDelaiListExiste = referentielDelaiRepository.findReferentielDelaiByModePassationIdAndDeletedIsFalse( modePassation.getId() );
            if (!referentielDelaiListExiste.isEmpty()) {
                referentielDelaiRepository.saveAll( referentielDelaiListExiste.stream().peek( vale -> vale.setDeleted( true ) ).collect( Collectors.toList() ) );
            }*/
            Acteur acteur;

            Etape etape;
            for (ReferentielDelaiDTO ref : referentielDelaiDTOS) {

                etape = etapeMapper.toEntity( ref.getEtape() );
                etape.setModePassation( modePassation );
                etape = etapeRepository.save( etape );


                NormeReference normeReference;

                if (acteurMapper.toEntity( ref.getActeur() ) != null) {
                    // acteur = createActeur( acteurMapper.toEntity( ref.getActeur() ) );
                    ReferentielDelai referentielDelai = referentielDelaiMapper.toEntity( ref );
                    referentielDelai.setActeur( acteurMapper.toEntity( ref.getActeur()));
                    referentielDelai.setEtape( etape );
                    referentielDelai.setModePassation(modePassation);

                    if (ref.getNormeReference()!=null) {
                        normeReference = normeReferenceRepository.save(normeReferenceMapper.toEntity(ref.getNormeReference()));
                        referentielDelai.setNormeReference(normeReference);
                    }
                    referentielDelaiList.add( referentielDelai );
                } else {
                    ReferentielDelai referentielDelai = referentielDelaiMapper.toEntity( ref );
                    if (ref.getNormeReference()!=null) {
                        normeReference = normeReferenceRepository.save(normeReferenceMapper.toEntity(ref.getNormeReference()));
                        referentielDelai.setNormeReference(normeReference);
                    }
                    referentielDelai.setEtape( etape );
                    referentielDelai.setModePassation(modePassation);
                    referentielDelaiList.add( referentielDelai );
                }
            }

            referentielDelaiRepository.saveAll( referentielDelaiList );
        }
        return Long.parseLong( "" + referentielDelaiDTOS.size() );
    }

    public Long removeListeByModePassation(Long idMode) {
        List<ReferentielDelai> referentielDelaiList = referentielDelaiRepository.findReferentielDelaiByModePassationIdAndDeletedIsFalse( idMode );
        if (!referentielDelaiList.isEmpty()) {
            referentielDelaiList.forEach( val -> val.setDeleted( true ) );
            referentielDelaiRepository.saveAll( referentielDelaiList );
        }
        return Long.parseLong( "" + referentielDelaiList.size() );
    }

    public List<ReferentielDelaiDTO> getReferentielDelaisByPpmActivite(Long ppmActiviteId, Long modeId) {

        List<ReferentielDelaiDTO> referentielDelaiDTOS = findReferentielDelaiByModePassationId(modeId);
        List<EtapeActivitePpm> etapeActivitePpms = etapeActivitePpmRepository.findEtapeActivitePpmByPpmActiviteIdAndDeletedIsFalse(ppmActiviteId);

        List<ReferentielDelaiDTO> referentielDelaiDTOList = new ArrayList<>();

        etapeActivitePpms.forEach(etapeActivitePpm -> {
            ReferentielDelaiDTO referentielDelaiDTO = new ReferentielDelaiDTO();

            List<ReferentielDelaiDTO> referentielDelaiDTOList1 = referentielDelaiDTOS.stream().filter(ref -> ref.getEtape() != null && ref.getEtape().equals(etapeMapper.toDto(etapeActivitePpm.getEtape()))).collect(Collectors.toList());
            if (!referentielDelaiDTOList1.isEmpty()) {
                referentielDelaiDTO.setId(referentielDelaiDTOList1.get(0).getId());

                referentielDelaiDTO.setNormeReference(referentielDelaiDTOList1.get(0).getNormeReference());

            }
            referentielDelaiDTO.setEtape(etapeMapper.toDto(etapeActivitePpm.getEtape()));
            referentielDelaiDTO.setDebut(etapeActivitePpm.getDebut());
            referentielDelaiDTO.setFin(etapeActivitePpm.getFin());
            referentielDelaiDTO.setDuration(getDifference(etapeActivitePpm.getDebut(), etapeActivitePpm.getFin()));
            referentielDelaiDTO.setDuration(getDifference(etapeActivitePpm.getDebut(), LocalDate.now()));

            referentielDelaiDTOList.add(referentielDelaiDTO);
        });

        return referentielDelaiDTOList;
    }

    public int getDifference(LocalDate dateDebut, LocalDate dateFin) {

        int nb = 0;
        for (LocalDate debut = dateDebut; debut.isBefore(dateFin); debut = debut.plusDays(1)) {
            nb++;
        }
        return nb;
    }

    public List<EtapeDTO> getEpateByActeur(Long acteurId) {
        return referentielDelaiRepository.findReferentielDelaiByActeurIdAndDeletedIsFalse(acteurId).stream()
            .map(referentielDelaiMapper::toDto)
            .map(ReferentielDelaiDTO::getEtape)
            .collect(Collectors.toList());
    }
}
