package mena.gov.bf.service;

import mena.gov.bf.domain.UniteAdministrative;
import mena.gov.bf.repository.UniteAdministrativeRepository;
import mena.gov.bf.service.dto.UniteAdministrativeDTO;
import mena.gov.bf.service.mapper.UniteAdministrativeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link UniteAdministrative}.
 */
@Service
@Transactional
public class UniteAdministrativeService {

    private final Logger log = LoggerFactory.getLogger( UniteAdministrativeService.class );

    private final UniteAdministrativeRepository uniteAdministrativeRepository;

    private final UniteAdministrativeMapper uniteAdministrativeMapper;

    public UniteAdministrativeService(UniteAdministrativeRepository uniteAdministrativeRepository, UniteAdministrativeMapper uniteAdministrativeMapper) {
        this.uniteAdministrativeRepository = uniteAdministrativeRepository;
        this.uniteAdministrativeMapper = uniteAdministrativeMapper;
    }

    /**
     * Save a uniteAdministrative.
     *
     * @param uniteAdministrativeDTO the entity to save.
     * @return the persisted entity.
     */
    public UniteAdministrativeDTO save(UniteAdministrativeDTO uniteAdministrativeDTO) {
        log.debug( "Request to save UniteAdministrative : {}", uniteAdministrativeDTO );
        UniteAdministrative uniteAdministrative = uniteAdministrativeMapper.toEntity( uniteAdministrativeDTO );
        uniteAdministrative = uniteAdministrativeRepository.save( uniteAdministrative );
        return uniteAdministrativeMapper.toDto( uniteAdministrative );
    }

    /**
     * Get all the uniteAdministratives.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<UniteAdministrativeDTO> findAll(Pageable pageable) {
        log.debug( "Request to get all UniteAdministratives" );
        List<UniteAdministrativeDTO> modePassationDTOS = uniteAdministrativeRepository.findAll().stream().map( uniteAdministrativeMapper::toDto )
            .filter( uniteAdministrativeDTO -> uniteAdministrativeDTO.isDeleted() != null && !uniteAdministrativeDTO.isDeleted() ).collect( Collectors.toList() );

        return new PageImpl<>( modePassationDTOS, pageable, modePassationDTOS.size() );
    }

    /**
     * Get one uniteAdministrative by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UniteAdministrativeDTO> findOne(Long id) {
        log.debug( "Request to get UniteAdministrative : {}", id );
        return uniteAdministrativeRepository.findById( id )
            .map( uniteAdministrativeMapper::toDto );
    }

    /**
     * Delete the uniteAdministrative by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug( "Request to delete UniteAdministrative : {}", id );
        uniteAdministrativeRepository.deleteById( id );
    }

    public List<UniteAdministrativeDTO> updateAall(List<UniteAdministrativeDTO> uniteAdministrativeDTOS) {
        uniteAdministrativeDTOS.forEach( uniteAdministrativeDTO -> {
            uniteAdministrativeDTO.setDeleted( true );
        } );
        uniteAdministrativeRepository.saveAll( uniteAdministrativeDTOS.stream().map( uniteAdministrativeMapper::toEntity ).collect( Collectors.toList() ) );
        List<UniteAdministrativeDTO> modePassationDTOS1 = uniteAdministrativeRepository.findAll().stream().map( uniteAdministrativeMapper::toDto ).filter( uniteAdministrativeDTO ->
            uniteAdministrativeDTO.isDeleted() != null && !uniteAdministrativeDTO.isDeleted() ).collect( Collectors.toList() );
        return uniteAdministrativeDTOS;
    }

    public List<UniteAdministrativeDTO> findAllUniteAdministrative() {
        return uniteAdministrativeRepository.findAll()
            .stream().filter( uniteAdministrative -> uniteAdministrative.isDeleted() != null && !uniteAdministrative.isDeleted() )
            .map( uniteAdministrativeMapper::toDto ).collect( Collectors.toList() );
    }
}
