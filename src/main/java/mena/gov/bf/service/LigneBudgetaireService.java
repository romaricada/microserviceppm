package mena.gov.bf.service;

import mena.gov.bf.domain.LigneBudgetaire;
import mena.gov.bf.repository.BesoinLigneBudgetaireRepository;
import mena.gov.bf.repository.LigneBudgetaireRepository;
import mena.gov.bf.service.dto.BesoinLigneBudgetaireDTO;
import mena.gov.bf.service.dto.LigneBudgetaireDTO;
import mena.gov.bf.service.mapper.BesoinLigneBudgetaireMapper;
import mena.gov.bf.service.mapper.LigneBudgetaireMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link LigneBudgetaire}.
 */
@Service
@Transactional
public class LigneBudgetaireService {

    private final Logger log = LoggerFactory.getLogger(LigneBudgetaireService.class);

    private final LigneBudgetaireRepository ligneBudgetaireRepository;

    private final LigneBudgetaireMapper ligneBudgetaireMapper;

    @Autowired
    BesoinLigneBudgetaireMapper besoinLigneBudgetaireMapper;

    @Autowired
    BesoinLigneBudgetaireRepository besoinLigneBudgetaireRepository;

    public LigneBudgetaireService(LigneBudgetaireRepository ligneBudgetaireRepository,
            LigneBudgetaireMapper ligneBudgetaireMapper) {
        this.ligneBudgetaireRepository = ligneBudgetaireRepository;
        this.ligneBudgetaireMapper = ligneBudgetaireMapper;
    }

    /**
     * Save a ligneBudgetaire.
     *
     * @param ligneBudgetaireDTO the entity to save.
     * @return the persisted entity.
     */
    public LigneBudgetaireDTO save(LigneBudgetaireDTO ligneBudgetaireDTO) {
        log.debug("--------------------Request to save ligneBudgetaire : {}--------------------", ligneBudgetaireDTO);
        LigneBudgetaire ligneBudgetaire = ligneBudgetaireMapper.toEntity(ligneBudgetaireDTO);

        if (ligneBudgetaireDTO != null && ligneBudgetaire.getId() != null) {
            // Optional<LigneBudgetaire> ligneBudgetExist = ligneBudgetaireRepository.findById(ligneBudgetaire.getId());
            return ligneBudgetaireMapper.toDto(ligneBudgetaireRepository.save(ligneBudgetaireMapper.toEntity(ligneBudgetaireDTO)));
        } else {
            String ligne = "Prg " + ligneBudgetaireDTO.getProgramme() + " Act " + ligneBudgetaireDTO.getAction()
                    + " Actv " + ligneBudgetaireDTO.getActivite() + " Chap " + ligneBudgetaireDTO.getChapitre()
                    + " Art " + ligneBudgetaireDTO.getArticle() + " Par " + ligneBudgetaireDTO.getParagraphe();
            ligneBudgetaire.setLigneCredit(ligne);
            ligneBudgetaire = ligneBudgetaireRepository.save(ligneBudgetaire);
            return ligneBudgetaireMapper.toDto(ligneBudgetaire);
        }
    }

    /**
     * Get all the ligneBudgetaires.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LigneBudgetaireDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ligneBudgetaires");
        List<LigneBudgetaireDTO> ligneBudgetaireDTOList = ligneBudgetaireRepository.findAll().stream()
                .map(ligneBudgetaireMapper::toDto)
                .filter(ligneBudgetaireDTO -> ligneBudgetaireDTO.isDeleted() != null && !ligneBudgetaireDTO.isDeleted())
                .collect(Collectors.toList());
        return new PageImpl<>(ligneBudgetaireDTOList, pageable, ligneBudgetaireDTOList.size());
    }

    /**
     * Get one ligneBudgetaire by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LigneBudgetaireDTO> findOne(Long id) {
        log.debug("Request to get ligneBudgetaire : {}", id);
        return ligneBudgetaireRepository.findById(id).map(ligneBudgetaireMapper::toDto);
    }

    /**
     * Delete the ligneBudgetaire by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ligneBudgetaire : {}", id);
        ligneBudgetaireRepository.deleteById(id);
    }

    public List<LigneBudgetaireDTO> updateAll(List<LigneBudgetaireDTO> ligneBudgetaireDTOS) {
        ligneBudgetaireDTOS.forEach(ligneBudgetaireDTO -> {
            ligneBudgetaireDTO.setDeleted(false);
        });
        ligneBudgetaireRepository.saveAll(
                ligneBudgetaireDTOS.stream().map(ligneBudgetaireMapper::toEntity).collect(Collectors.toList()));
        List<LigneBudgetaireDTO> ligneBudgetaireDTOS1 = ligneBudgetaireRepository.findAll().stream()
                .map(ligneBudgetaireMapper::toDto)
                .filter(ligneBudgetaireDTO -> ligneBudgetaireDTO.isDeleted() != null && !ligneBudgetaireDTO.isDeleted())
                .collect(Collectors.toList());
        return ligneBudgetaireDTOS1;

    }

    public List<LigneBudgetaireDTO> findAllByAnneeAndAeCp(Long idAnnee, String typeAeCp) {
        if (idAnnee == 0) {
            return ligneBudgetaireRepository.findAll().stream()
                    .filter(ligneBudgetaire -> ligneBudgetaire.getExercice() != null
                            && ligneBudgetaire.getExercice().isActive())
                    .map(ligneBudgetaireMapper::toDto).collect(Collectors.toList());
        } else if (idAnnee != 0 && typeAeCp.isEmpty()) {
            return ligneBudgetaireRepository.findAll().stream()
                    .filter(ligneBudgetaire -> ligneBudgetaire.getExercice() != null
                            && ligneBudgetaire.getExercice().getId().equals(idAnnee))
                    .map(ligneBudgetaireMapper::toDto).collect(Collectors.toList());
        } else {

            return ligneBudgetaireRepository.findAll().stream()
                    .filter(ligneBudgetaire -> ligneBudgetaire.getExercice() != null
                            && ligneBudgetaire.getExercice().getId().equals(idAnnee))
                    .map(ligneBudgetaireMapper::toDto).collect(Collectors.toList());
        }
    }

    public List<LigneBudgetaireDTO> findAllByAnnee(Long idAnnee) {
        return ligneBudgetaireRepository.findLigneBudgetairesByExerciceIdAndDeletedIsFalse(idAnnee).stream()
                .map(ligneBudgetaireMapper::toDto).collect(Collectors.toList());
    }

    public List<LigneBudgetaireDTO> findAllLigneBudgetaire() {

        List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOS = besoinLigneBudgetaireRepository.findAll()
            .stream()
            .map( besoinLigneBudgetaireMapper::toDto )
            .filter( besoinLigneBudgetaire -> besoinLigneBudgetaire.isDeleted() != null && !besoinLigneBudgetaire.isDeleted() )
            .collect( Collectors.toList() );

        List<LigneBudgetaireDTO> ligneBudgetaireDTOS = ligneBudgetaireRepository.findAll().stream().filter(value -> value.isDeleted() != null && !value.isDeleted())
            .map(ligneBudgetaireMapper::toDto).collect(Collectors.toList());


        ligneBudgetaireDTOS.forEach(ligneBudget -> {
            List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOList = besoinLigneBudgetaireDTOS.stream().filter(ligne -> ligne.getLigneBudgetId() != null && ligne.getLigneBudgetId().equals(ligneBudget.getId()))
                .collect(Collectors.toList());
            ligneBudget.setBesoinLigneBuget(
                !besoinLigneBudgetaireDTOList.isEmpty() ? besoinLigneBudgetaireDTOList.get(0) : new BesoinLigneBudgetaireDTO()
            );
        });

        return ligneBudgetaireDTOS;
    }

    public List<LigneBudgetaireDTO> findAllListe() {
        List<LigneBudgetaireDTO> ligneBudgetaireDTOList = ligneBudgetaireRepository.findAll()
            .stream()
            .filter(value -> value.isDeleted() != null && !value.isDeleted())
            .map(ligneBudgetaireMapper::toDto)
            .collect(Collectors.toList());
        System.out.println("======ligneBudgetaireDTOList ada========={}"+ligneBudgetaireDTOList);
        return ligneBudgetaireDTOList;
    }

    public List<LigneBudgetaireDTO> findAllProgramme() {
        return ligneBudgetaireRepository.findAllByDeletedIsFalse()
            .stream().filter(value -> value.isDeleted() != null && !value.isDeleted())
            .map(ligneBudgetaireMapper::toDto).distinct()
            .collect(Collectors.toList());
    }

    /* public List<LigneBudgetaireDTO> findLigneBudgetByActivite(Long activiteId) {
        List<BesoinLigneBudgetaireDTO> besoinLigneBudgetaireDTOList = besoinLigneBudgetaireRepository.findAll()
            .stream()
            .map(besoinLigneBudgetaireMapper::toDto)
            .filter(besoinLigneBudgetaire -> besoinLigneBudgetaire.isDeleted() != null && !besoinLigneBudgetaire.isDeleted() && besoinLigneBudgetaire.getActiviteId != null && besoinLigneBudgetaire.getActiviteId == activiteId)
            .collect(Collectors.toList());
        List<LigneBudgetaireDTO> ligneBudgetaireDTOList = ligneBudgetaireRepository.findAll()
            .stream()
            .filter(value -> value.isDeleted() != null && !value.isDeleted())
            .map(ligneBudgetaireMapper::toDto)
            .collect(Collectors.toList());

        if (besoinLigneBudgetaireDTOList.isEmpty() && ligneBudgetaireDTOList.isEmpty()) {
            ligneBudgetaireDTOList.forEach(ligne -> ligne.ligneBudgetaireRepository.findAll
                .stream()
                .filter(ligne -> ligne.isDeleted() != null && !ligne.isDeleted() && ligne.getId() != null && ligne.getId().equals(besoinLigneBudgetaireDTOList.get(0).getligneBudgetId() : new BesoinLigneBudgetaireDTO()))
                .map(ligneBudgetaireMapper::toDto)
                .collect(Collectors.toList());
                );
        }
        return ligneBudgetaireDTOList;
    } */

    public List<BesoinLigneBudgetaireDTO> findbesosoinLigne(Long idligne){
        return besoinLigneBudgetaireMapper.toDto(besoinLigneBudgetaireRepository.findAllByLigneBudgetIdAndDeletedIsFalse(idligne));
    }

    /*public List<LigneBudgetaireDTO> findAllLigneBudgetaire(){
        List<LigneBudgetaireDTO> ligneBudgetaireList = ligneBudgetaireRepository.findAll().stream().filter(value -> value.isDeleted() != null && !value.isDeleted()).map(ligneBudgetaireMapper::toDto).collect(Collectors.toList());
        return ligneBudgetaireList;
    }*/

}
