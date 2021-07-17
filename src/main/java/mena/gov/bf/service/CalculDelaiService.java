package mena.gov.bf.service;


import mena.gov.bf.domain.CalculDelai;
import mena.gov.bf.repository.CalculDelaiRepository;
import mena.gov.bf.repository.EtapeRepository;
import mena.gov.bf.service.dto.CalculDelaiDTO;
import mena.gov.bf.service.dto.EtapeDTO;
import mena.gov.bf.service.mapper.CalculDelaiMapper;
import mena.gov.bf.service.mapper.EtapeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class CalculDelaiService {

    @Autowired
    CalculDelaiRepository calculDelaiRepository;

    @Autowired
    CalculDelaiMapper calculDelaiMapper;

    @Autowired
    EtapeRepository etapeRepository;

    @Autowired
    EtapeMapper etapeMapper;

    private final Logger log = LoggerFactory.getLogger( CalculDelaiService.class );

    public List<CalculDelaiDTO> save(CalculDelaiDTO calculDelaiDTO) {

        List<CalculDelaiDTO> calculDelaiDTOList = new ArrayList<>();

        List<EtapeDTO> etapeDTOList = calculDelaiDTO.getEtapes();

        if (!etapeDTOList.isEmpty()) {

            if (calculDelaiDTO.getId() != null) {

                log.debug("requeste de update   --------   {}   -------", calculDelaiDTO);

                List<CalculDelaiDTO> calculDelaiDTOList1 = calculDelaiRepository.findAllByModePassation_IdAndLibelleAndDeletedIsFalse(calculDelaiDTO.getModePassation().getId(), calculDelaiDTO.getLibelle())
                    .stream()
                    .map(calculDelaiMapper::toDto)
                    .collect(Collectors.toList());

                calculDelaiDTOList1.forEach(etapeDTO -> etapeDTO.setDeleted(true));

                calculDelaiRepository.saveAll(calculDelaiMapper.toEntity(calculDelaiDTOList1));

                etapeDTOList.forEach(etapeDTO -> {
                    CalculDelaiDTO calculDelai = new CalculDelaiDTO();

                    calculDelai.setEtape(etapeDTO);
                    calculDelai.setLibelle(calculDelaiDTO.getLibelle());
                    calculDelai.setDeleted(false);
                    calculDelai.setModePassation(calculDelaiDTO.getModePassation());
                    calculDelaiDTOList.add(calculDelai);
                });

            } else {
                log.debug("requeste de save   ---<<<<<<<   {}   >>>>>>>>>---", calculDelaiDTO);

                etapeDTOList.forEach(etapeDTO -> {
                    CalculDelaiDTO calculDelai = new CalculDelaiDTO();

                    calculDelai.setEtape(etapeDTO);
                    calculDelai.setLibelle(calculDelaiDTO.getLibelle());
                    calculDelai.setDeleted(false);
                    calculDelai.setModePassation(calculDelaiDTO.getModePassation());
                    calculDelaiDTOList.add(calculDelai);
                });
            }

            return calculDelaiMapper.toDto(calculDelaiRepository.saveAll(calculDelaiMapper.toEntity(calculDelaiDTOList)));
        }

        // return calculDelaiMapper.toDto(calculDelaiRepository.saveAll(calculDelaiMapper.toEntity(calculDelaiDTOList)));

        return null;

    }


    public CalculDelaiDTO modifier(CalculDelaiDTO calculDelaiDTO) {
        log.debug( "Request to save calcul : {}", calculDelaiDTO );
        CalculDelai calculDelai = calculDelaiMapper.toEntity(calculDelaiDTO);
        calculDelai = calculDelaiRepository.save( calculDelai );
        return calculDelaiMapper.toDto( calculDelai );
    }

    public List<CalculDelaiDTO> getCalculDealiByLibelle(String libvelle) {
        //CalculDelaiDTO calculDelaiDTO = calculDelaiMapper.toDto(calculDelaiRepository.findFirstByLibelleAndDeletedIsFalse(libvelle));

        return calculDelaiMapper.toDto(calculDelaiRepository.findCalculDelaiByLibelleAndDeletedIsFalse(libvelle));
    }

    public CalculDelaiDTO getEtapesByDate(String libvelle) {
        CalculDelaiDTO calculDelai = calculDelaiMapper.toDto(calculDelaiRepository.findFirstByLibelleAndDeletedIsFalse(libvelle));

        calculDelai.setEtapes(
            getCalculDealiByLibelle(libvelle).stream().map(CalculDelaiDTO::getEtape).collect(Collectors.toList())
        );
        return calculDelai;
    }

    public List<CalculDelaiDTO> getAll() {
        int compte=0;
        String mot;
        List<CalculDelaiDTO> calculDelaiDTOList = getEtapes();
        List<CalculDelaiDTO> calculDelaiList= new ArrayList<>();
        List<CalculDelai> calculDelaiList1= calculDelaiRepository.findAll()
            .stream()
            .filter(calculDelai -> calculDelai.isDeleted() != null && !calculDelai.isDeleted())
            .collect(Collectors.toList());
        for(int i= 0 ; i<calculDelaiList1.size(); i++){
            mot= calculDelaiList1.get(i).getLibelle();
            for(int j=0 ; j<calculDelaiList.size(); j++){
                if(calculDelaiList.get(j).getLibelle().equals(mot)){
                    compte++;

                }
                else {
                    compte=0;
                }


            }
            if(compte == 0){
                calculDelaiList.add(calculDelaiMapper.toDto(calculDelaiList1.get(i)));
            }
        }

        calculDelaiList.forEach(calculDelaiDTO -> {
            calculDelaiDTO.setEtapes(
                calculDelaiDTOList.stream()
                    .filter(e -> e.getLibelle().equals(calculDelaiDTO.getLibelle()))
                    .map(CalculDelaiDTO::getEtape)
                    .collect(Collectors.toList())
            );
        });

        return calculDelaiList;

    }


    public List<CalculDelaiDTO> updateAall(List<CalculDelaiDTO> calculDelaiDTOS) {
        calculDelaiDTOS.forEach( calculDelaiDTO -> {
            calculDelaiDTO.setDeleted( true );
        } );

        calculDelaiRepository.saveAll( calculDelaiDTOS.stream().map( calculDelaiMapper::toEntity ).collect( Collectors.toList() ) );
        List<CalculDelaiDTO> calculDelaiDTOS1 = calculDelaiRepository.findAll().stream().map( calculDelaiMapper::toDto ).filter( calculDelaiDTO ->
            calculDelaiDTO.isDeleted() != null && !calculDelaiDTO.isDeleted() ).collect( Collectors.toList() );
        return calculDelaiDTOS;
    }


    public List<CalculDelaiDTO> updateall(CalculDelaiDTO calculDelaiDTOS) {
        List<CalculDelaiDTO> calculDelaiDTOS1 =calculDelaiRepository.findAll().stream()
            .map(calculDelaiMapper:: toDto)
            .filter(calculDelaiDTO -> calculDelaiDTO.isDeleted() != null && !calculDelaiDTO.isDeleted() &&
                calculDelaiDTO.getLibelle().equals(calculDelaiDTOS.getLibelle())).collect(Collectors.toList());

       calculDelaiDTOS1.forEach(calculDelaiDTO2 -> {
           calculDelaiDTO2.setDeleted(true);
       });

        calculDelaiRepository.saveAll( calculDelaiDTOS1.stream().map( calculDelaiMapper::toEntity ).collect( Collectors.toList() ) );
        return calculDelaiDTOS1;
    }

    public List<CalculDelaiDTO> getEtapes() {
        return calculDelaiRepository.findAll()
            .stream()
            //.map(CalculDelai::getEtape)
            .map(calculDelaiMapper::toDto)
            .filter(calculDelaiDTO -> calculDelaiDTO.isDeleted() != null && !calculDelaiDTO.isDeleted())
            .collect(Collectors.toList());
    }


    public List<CalculDelaiDTO> getModePassation(Long modePassationId) {
        return calculDelaiRepository.findAllByModePassation_IdAndDeletedIsFalse(modePassationId).stream().map(calculDelaiMapper::toDto).collect(Collectors.toList());
    }

    public List<CalculDelaiDTO> getModePassationAndDelaiCalcul(Long modePassationId, String libelle) {

        return calculDelaiRepository.findAllByModePassation_IdAndLibelleAndDeletedIsFalse(modePassationId,libelle).stream().map(calculDelaiMapper::toDto).collect(Collectors.toList());
    }

}
