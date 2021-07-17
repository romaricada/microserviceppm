package mena.gov.bf.service;

import mena.gov.bf.domain.ModePassation;
import mena.gov.bf.repository.ModePassationRepository;
import mena.gov.bf.repository.ReferentielDelaiRepository;
import mena.gov.bf.service.dto.ModePassationDTO;
import mena.gov.bf.service.mapper.ModePassationMapper;
import mena.gov.bf.service.mapper.ReferentielDelaiMapper;
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
 * Service Implementation for managing {@link ModePassation}.
 */
@Service
@Transactional
public class ModePassationService {

    private final Logger log = LoggerFactory.getLogger( ModePassationService.class );

    private final ModePassationRepository modePassationRepository;

    private final ModePassationMapper modePassationMapper;

    private final ReferentielDelaiRepository referentielDelaiRepository;

    private final ReferentielDelaiMapper referentielDelaiMapper;


    public ModePassationService(
        ModePassationRepository modePassationRepository,
        ModePassationMapper modePassationMapper,
        ReferentielDelaiRepository referentielDelaiRepository,
        ReferentielDelaiMapper referentielDelaiMapper
    ) {
        this.modePassationRepository = modePassationRepository;
        this.modePassationMapper = modePassationMapper;
        this.referentielDelaiRepository = referentielDelaiRepository;
        this.referentielDelaiMapper = referentielDelaiMapper;
    }

    /**
     * Save a modePassation.
     *
     * @param modePassationDTO the entity to save.
     * @return the persisted entity.
     */
    public ModePassationDTO save(ModePassationDTO modePassationDTO) {
        log.debug( "Request to save ModePassation : {}", modePassationDTO );
        ModePassation modePassation = modePassationMapper.toEntity( modePassationDTO );
        modePassation = modePassationRepository.save( modePassation );
        return modePassationMapper.toDto( modePassation );
    }

    /**
     * Get all the modePassations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ModePassationDTO> findAll(Pageable pageable) {
        log.debug( "Request to get all ModePassations" );
        List<ModePassationDTO> modePassationDTOS = modePassationRepository.findAll().stream().map( modePassationMapper::toDto )
            .filter( modePassationDTO -> modePassationDTO.isDeleted() != null && !modePassationDTO.isDeleted() ).collect( Collectors.toList() );

        return new PageImpl<>( modePassationDTOS, pageable, modePassationDTOS.size() );

    }

    /**
     * Get one modePassation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ModePassationDTO> findOne(Long id) {
        log.debug( "Request to get ModePassation : {}", id );
        return modePassationRepository.findById( id )
            .map( modePassationMapper::toDto );
    }

    /**
     * Delete the modePassation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug( "Request to delete ModePassation : {}", id );
        modePassationRepository.deleteById( id );
    }

    public List<ModePassationDTO> updateAall(List<ModePassationDTO> modePassationDTOS) {
        modePassationDTOS.forEach( modePassationDTO -> {
            modePassationDTO.setDeleted( true );
        } );
        modePassationRepository.saveAll( modePassationDTOS.stream().map( modePassationMapper::toEntity ).collect( Collectors.toList() ) );
        List<ModePassationDTO> modePassationDTOS1 = modePassationRepository.findAll().stream().map( modePassationMapper::toDto ).filter( modePassationDTO ->
            modePassationDTO.isDeleted() != null && !modePassationDTO.isDeleted() ).collect( Collectors.toList() );
        return modePassationDTOS1;
    }

    public List<ModePassationDTO> findAllReferencielByModePassation() {
        List<ModePassationDTO> modePassations = modePassationRepository.findAllByDeletedIsFalse()
            .stream()
            .map( modePassationMapper::toDto )
            .collect( Collectors.toList() );

        if (!modePassations.isEmpty()) {
            modePassations.forEach( value -> {
                value.setReferenciels( referentielDelaiRepository.findReferentielDelaiByModePassationIdAndDeletedIsFalse( value.getId() )
                    .stream().map( referentielDelaiMapper::toDto ).collect( Collectors.toList() ) );
            } );
      /*      List<ModePassationDTO> modePassationDTOList = new ArrayList<>();
            modePassations.forEach( mode -> {
                if (!mode.getReferenciels().isEmpty()) {
                    modePassationDTOList.add( mode );
                }
            } );
            return modePassationDTOList;*/
        }
        log.debug("=============mode=============",modePassations);
        return modePassations;
    }
}
