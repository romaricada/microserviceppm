package mena.gov.bf.service;

import mena.gov.bf.domain.Besoin;
import mena.gov.bf.domain.BesoinLigneBudgetaire;
import mena.gov.bf.repository.BesoinLigneBudgetaireRepository;
import mena.gov.bf.repository.BesoinRepository;
import mena.gov.bf.repository.LigneBudgetaireRepository;
import mena.gov.bf.service.dto.BesoinDTO;
import mena.gov.bf.service.dto.BesoinLigneBudgetaireDTO;
import mena.gov.bf.service.dto.LigneBudgetaireDTO;
import mena.gov.bf.service.mapper.BesoinLigneBudgetaireMapper;
import mena.gov.bf.service.mapper.BesoinMapper;
import mena.gov.bf.service.mapper.LigneBudgetaireMapper;
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
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Besoin}.
 */
@Service
@Transactional
public class BesoinService {

    private final Logger log = LoggerFactory.getLogger(BesoinService.class);

    private final BesoinRepository besoinRepository;

    private final BesoinMapper besoinMapper;

    @Autowired
    BesoinLigneBudgetaireRepository besoinLigneBudgetaireRepository;

    @Autowired
    BesoinLigneBudgetaireMapper besoinLigneBudgetaireMapper;

    @Autowired
    LigneBudgetaireRepository ligneBudgetaireRepository;

    @Autowired
    LigneBudgetaireMapper ligneBudgetaireMapper;

    @Autowired
    LigneBudgetaireService ligneBudgetaireService;

    public BesoinService(BesoinRepository besoinRepository, BesoinMapper besoinMapper) {
        this.besoinRepository = besoinRepository;
        this.besoinMapper = besoinMapper;
    }

    /**
     * Save a besoin.
     *
     * @param besoinDTO the entity to save.
     * @return the persisted entity.
     */
    // @Transactional(readOnly = true)
    public BesoinDTO save(BesoinDTO besoinDTO) {
        besoinDTO.setUsed(false);
        Besoin besoin = besoinRepository.save(besoinMapper.toEntity(besoinDTO));

        final List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaires = besoinLigneBudgetaireRepository.findAllByBesoinIdAndDeletedIsFalse(besoin.getId())
            .stream()
            .map(besoinLigneBudgetaireMapper::toDto)
            .collect(Collectors.toList());

        if (!besoinLigneBudgetaires.isEmpty()) {
            besoinLigneBudgetaires.forEach(blb -> blb.setDeleted(true));
            besoinLigneBudgetaireRepository.saveAll(besoinLigneBudgetaireMapper.toEntity(besoinLigneBudgetaires));
        }
        log.debug("===========>> {}", besoinDTO.getBesoinLignes());
        if (!besoinDTO.getLigneBudgetaires().isEmpty()) {
            besoinDTO.getBesoinLignes().forEach(blb -> {
                blb.setDeleted(false);
                blb.setBesoinId(besoin.getId());
                if (!besoinDTO.getLigneBudgetaires().stream().filter(lb -> lb.getId() != null && lb.getId()
                    .equals(blb.getLigneBudgetId())).collect(Collectors.toList()).isEmpty()) {
                    blb.setMontantEstime(besoinDTO.getLigneBudgetaires().stream()
                        .filter(lb -> lb.getId() != null && lb.getId().equals(blb.getLigneBudgetId()))
                        .collect(Collectors.toList()).get(0).getMontantEstime());
                }
            });
            besoinLigneBudgetaireRepository.saveAll(besoinLigneBudgetaireMapper.toEntity(besoinDTO.getBesoinLignes()));
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Aucune ligne budgetaire associé au besoin");
        }
        return besoinMapper.toDto(besoin);
    }

    public BesoinLigneBudgetaireDTO besoinLigneExist(Long besoinId, Long ligneId, List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOs) {

        List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOList = besoinLigneBudgetaireDTOs.stream().
            filter(besoinLigneBudgetaireDTO -> besoinLigneBudgetaireDTO.getLigneBudgetId() != null && besoinLigneBudgetaireDTO.getBesoinId() != null
                && besoinLigneBudgetaireDTO.getBesoinId().equals(besoinId) && besoinLigneBudgetaireDTO.getLigneBudgetId().equals(ligneId)
            )
            .collect(Collectors.toList());
        if (!besoinLigneBudgetaireDTOList.isEmpty()) {
            return besoinLigneBudgetaireDTOList.get(0);
        } else {
            BesoinLigneBudgetaireDTO besoinLigneBudgetaireDTO1 = new BesoinLigneBudgetaireDTO();
            besoinLigneBudgetaireDTO1.setLigneBudgetId(ligneId);
            besoinLigneBudgetaireDTO1.setBesoinId(besoinId);
            besoinLigneBudgetaireDTO1.setDeleted(false);
            return besoinLigneBudgetaireDTO1;
        }
    }

    /**
     * Get all the besoins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BesoinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Besoins");

        List<BesoinDTO> besoinDTOS = findAllBesoins();

        log.debug("=====================        {}      ====================", besoinDTOS);

        return new PageImpl<>(besoinDTOS, pageable, besoinDTOS.size());
    }

    @Transactional(readOnly = true)
    public List<BesoinDTO> findAllBesoins() {
        log.debug("Request to get all Besoins");
        final List<BesoinLigneBudgetaire> besoinLigneBudgetaireDTOS;
        final List<LigneBudgetaireDTO> ligneBudgetaireDTOS;
        List<BesoinDTO> besoinDTOS = besoinRepository.findAll().stream()
            .map(besoinMapper::toDto).filter(besoin -> besoin.isDeleted() != null && !besoin.isDeleted()).collect(Collectors.toList());
        if (!besoinDTOS.isEmpty()) {
            besoinLigneBudgetaireDTOS = besoinLigneBudgetaireRepository.findAll()
                .stream()
                .filter(besoinLigneBudgetaire -> besoinLigneBudgetaire.isDeleted() != null && !besoinLigneBudgetaire.isDeleted())
                .collect(Collectors.toList());

            //log.debug( "------------------------     {}      ----------------------", besoinLigneBudgetaireDTOS );

            ligneBudgetaireDTOS = ligneBudgetaireService.findAllLigneBudgetaire();

            //log.debug( "++++++++++++++++++++++++     {}      +++++++++++++++++++++++", ligneBudgetaireDTOS.size() );

            if (!besoinLigneBudgetaireDTOS.isEmpty() && !ligneBudgetaireDTOS.isEmpty()) {
                besoinDTOS.forEach(besoinDTO -> {
                    List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOList = besoinLigneBudgetaireDTOS.stream().map(besoinLigneBudgetaireMapper::toDto)
                        .filter(besoinLigneBudgetaireDTO -> besoinLigneBudgetaireDTO.getBesoinId() != null && besoinLigneBudgetaireDTO.getBesoinId().equals(besoinDTO.getId()))
                        .collect(Collectors.toList());
                    log.debug("..........................     {}      ..........................", besoinLigneBudgetaireDTOList);

                    besoinDTO.setBesoinLignes(besoinLigneBudgetaireDTOList);

                    List<LigneBudgetaireDTO> lignes = new ArrayList<>();
                    besoinLigneBudgetaireDTOList.forEach(besoinLigne -> {
                        lignes.addAll(ligneBudgetaireDTOS.stream().filter(l -> l.getId() != null && l.getId().equals(besoinLigne.getLigneBudgetId())).collect(Collectors.toList()));
                    });

                    besoinDTO.setLigneBudgetaires(lignes);
                });
            }
        }

        log.debug("=====================        {}      ====================", besoinDTOS);

        return besoinDTOS;
    }

    /**
     * Get one besoin by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BesoinDTO> findOne(Long id) {
        log.debug("Request to get Besoin : {}", id);
        Optional<BesoinDTO> besoinDTO = besoinRepository.findById(id)
            .map(besoinMapper::toDto);

        return besoinDTO.isPresent() ? besoinDTO : null;
    }

    /**
     * Delete the besoin by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Besoin : {}", id);
        besoinRepository.deleteById(id);
    }

    public List<BesoinDTO> updateAll(List<BesoinDTO> besoinDTOS) {

        List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOList = new ArrayList<>();

        besoinDTOS.forEach(besoinDTO -> {
            besoinLigneBudgetaireDTOList.addAll(
                besoinLigneBudgetaireMapper.toDto(besoinLigneBudgetaireRepository.findAllByBesoinIdAndDeletedIsFalse(besoinDTO.getId()))
            );
            besoinDTO.setDeleted(true);
        });

        besoinLigneBudgetaireDTOList.forEach(blb -> {
            blb.setDeleted(true);
        });

        besoinRepository.saveAll(besoinMapper.toEntity(besoinDTOS));

        besoinLigneBudgetaireRepository.saveAll(besoinLigneBudgetaireMapper.toEntity(besoinLigneBudgetaireDTOList));

        return besoinDTOS;
    }

    public String deletedConfirm(List<BesoinDTO> besoinDTOS) {
        List<BesoinLigneBudgetaire> besoinLigneBudgetaires = new ArrayList<>();
        List<BesoinLigneBudgetaire> besoinLigneBudgetaires1 = new ArrayList<>();
        if (!besoinDTOS.isEmpty()) {
            besoinDTOS.forEach(besoinDTO -> {
                besoinLigneBudgetaires.addAll(
                    besoinLigneBudgetaireRepository.findAllByBesoinIdAndDeletedIsFalse(besoinDTO.getId())
                );
            });

            if (!besoinLigneBudgetaires.isEmpty()) {
                besoinLigneBudgetaires.forEach(besoinLigneBudgetaire -> {
                    if (besoinLigneBudgetaire.getActivite() != null) {
                        besoinLigneBudgetaires1.add(besoinLigneBudgetaire);
                    }
                });
                if (!besoinLigneBudgetaires1.isEmpty()) {
                    return "impossible car certains de vos besoins sont utilisés dans des activités ";
                }
            }
        }
        return "POSSIBLE";
    }


    public List<BesoinDTO> useAllBesoins(List<BesoinDTO> besoinDTOS) {

        besoinDTOS.forEach(besoinDTO -> {
            besoinDTO.setUsed(true);
        });

        besoinRepository.saveAll(besoinMapper.toEntity(besoinDTOS));

        return besoinDTOS;
    }

    public Page<BesoinDTO> findAllByDirectionAndExercice(Long exerciceId, Long directionId, Pageable pageable) {
        List<BesoinDTO> besoinDTOS = findAllBesoins()
            .stream()
            .filter(besoinDTO -> besoinDTO.getExerciceId() != null && besoinDTO.getExerciceId().equals(exerciceId))
            .collect(Collectors.toList());

        if (directionId != 0) {
            besoinDTOS = besoinDTOS.stream().filter(besoinDTO -> besoinDTO.getUniteAdministrativeId() != null && besoinDTO.getUniteAdministrativeId().equals(directionId)).collect(Collectors.toList());
        }

        return new PageImpl<>(besoinDTOS, pageable, besoinDTOS.size());
    }

    public List<BesoinDTO> findBesoinsByNaturePrestationId(Long naturePrestationId, Long exerciceId) {
        return findAllBesoins()
            .stream()
            .filter(besoin -> besoin.getNaturePrestationId() != null && besoin.getNaturePrestationId().equals(naturePrestationId)
                && besoin.isDeleted() != null && !besoin.isDeleted()
                && besoin.getExerciceId() != null && besoin.getExerciceId().equals(exerciceId)
                && besoin.isUsed() != null && !besoin.isUsed()
            )
            .collect(Collectors.toList());
    }

    /*public List<BesoinDTO> findBesoinsByNatureProgrammeId(Long programmeId, Long exerciceId) {
        return findAllBesoins()
            .stream()
            .filter(besoin -> besoin.getP() != null && besoin.getNaturePrestationId().equals(naturePrestationId)
                && besoin.isDeleted() != null && !besoin.isDeleted()
                && besoin.getExerciceId() != null && besoin.getExerciceId().equals(exerciceId)
                && besoin.isUsed() != null && !besoin.isUsed()
            )
            .collect(Collectors.toList());
    }*/

    public List<BesoinDTO> findBesoinsByExerciceId(Long exerciceId) {
        List<BesoinDTO> besoinDTOS = besoinRepository.findBesoinByExerciceIdAndUsedIsFalseAndDeletedIsFalse(exerciceId)
            .stream().map(besoinMapper::toDto)
            .collect(Collectors.toList());
        log.debug("============besoinADA=========", besoinDTOS);
        return besoinDTOS;
    }
    public List<BesoinDTO> findBesoinsByExerciceUpdate(Long exerciceId) {
        List<BesoinDTO> besoinDTOS = besoinRepository.findBesoinByExerciceIdAndDeletedIsFalse(exerciceId)
            .stream().map(besoinMapper::toDto)
            .collect(Collectors.toList());
        return besoinDTOS;
    }

    public List<BesoinDTO> findBesoinsByExerciceIdAndUsed(Long exerciceId) {
        return findAllBesoins()
            .stream()
            .filter(besoin -> besoin.getExerciceId() != null && besoin.getExerciceId().equals(exerciceId)
                && besoin.isDeleted() != null && !besoin.isDeleted() && besoin.isUsed() != null && !besoin.isUsed())
            .collect(Collectors.toList());
    }

    public Double montantRestant(Long ligneId, Long begoinId) {
        List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOS = besoinLigneBudgetaireRepository.findAll().stream().map(besoinLigneBudgetaireMapper::toDto).filter(besoinLigneBudgetaire ->
            besoinLigneBudgetaire.getLigneBudget() != null && besoinLigneBudgetaire.getBesoinId().equals(begoinId) && !besoinLigneBudgetaire.isDeleted() && besoinLigneBudgetaire.getLigneBudgetId().equals(ligneId)).collect(Collectors.toList());

        double montantdeslignes = besoinLigneBudgetaireDTOS.stream().mapToDouble(BesoinLigneBudgetaireDTO::getMontantEstime).sum();


        double montantDotCorEA = besoinLigneBudgetaireDTOS.stream().mapToDouble(BesoinLigneBudgetaireDTO::getDotCorAE).sum();

        double montantrestant = montantDotCorEA - montantdeslignes;

        return montantrestant;
    }

    public List<Double> montantRestantdotationAE(Long ligneBudgetId) {
        List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaires = besoinLigneBudgetaireRepository.findAll().stream().filter(besoinLigneBudgetaire ->
            besoinLigneBudgetaire.getLigneBudget() != null && besoinLigneBudgetaire.getLigneBudget().getId().equals(ligneBudgetId)).map(besoinLigneBudgetaireMapper::toDto).
            collect(Collectors.toList());
        List<Double> newBesoinLigneBudgetaire = new ArrayList<>();

        besoinLigneBudgetaires.forEach(besoinLigneBudgetaireDTO -> {
            Double montantEstime = besoinLigneBudgetaireDTO.getMontantEstime();
            Double montantDotationAE = besoinLigneBudgetaireDTO.getLigneBudget().getDotCorAE();
            if (montantDotationAE == null) {
                montantDotationAE = 0.0;
            }
            Double montantDotCorAERestant = montantDotationAE - montantEstime;
            if (montantDotCorAERestant < 0) {
                montantDotCorAERestant = 0.0;
            }
            newBesoinLigneBudgetaire.add(montantDotCorAERestant);
        });

        return newBesoinLigneBudgetaire;
    }


    public List<Double> montantRestantdotationCP(Long ligneBudgetId) {
        List<BesoinLigneBudgetaireDTO> besoinLigneBudgetairesTMP = besoinLigneBudgetaireRepository.findAll().stream().filter(besoinLigneBudgetaire ->
            besoinLigneBudgetaire.getLigneBudget() != null && besoinLigneBudgetaire.getLigneBudget().getId().equals(ligneBudgetId)).map(besoinLigneBudgetaireMapper::toDto).
            collect(Collectors.toList());

        List<Double> newBesoinLigneBudgetaireTMP = new ArrayList<>();

        besoinLigneBudgetairesTMP.forEach(besoinLigneBudgetaireDTO -> {
            Double montantEstime = besoinLigneBudgetaireDTO.getMontantEstime();
            Double montantDotationCP = besoinLigneBudgetaireDTO.getLigneBudget().getDotCorCP();
            if (montantDotationCP == null) {
                montantDotationCP = 0.0;
            }
            Double montantDotCorCPRestant = montantDotationCP - montantEstime;

            if (montantDotCorCPRestant < 0) {
                montantDotCorCPRestant = 0.0;
            }
            newBesoinLigneBudgetaireTMP.add(montantDotCorCPRestant);
        });

        return newBesoinLigneBudgetaireTMP;
    }
}
