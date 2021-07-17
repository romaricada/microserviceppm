package mena.gov.bf.alerteNotification.service;

import mena.gov.bf.alerteNotification.entity.Account;
import mena.gov.bf.alerteNotification.entity.DelaiMessageDTO;
import mena.gov.bf.alerteNotification.entity.Etat;
import mena.gov.bf.bean.MembreCommission;
import mena.gov.bf.bean.TacheEtape;
import mena.gov.bf.domain.EtapeActivitePpm;
import mena.gov.bf.proxies.DelaiMessageReporitory;
import mena.gov.bf.proxies.MembreCommissionRepository;
import mena.gov.bf.repository.ActeurRepository;
import mena.gov.bf.repository.EtapeActivitePpmRepository;
import mena.gov.bf.repository.PpmActiviteRepository;
import mena.gov.bf.repository.UserNotificationRepository;
import mena.gov.bf.service.ReferentielDelaiService;
import mena.gov.bf.service.dto.*;
import mena.gov.bf.service.mapper.ActeurMapper;
import mena.gov.bf.service.mapper.EtapeActivitePpmMapper;
import mena.gov.bf.service.mapper.PpmActiviteMapper;
import mena.gov.bf.service.mapper.UserNotificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Component
//@EnableScheduling
@Transactional
public class AlerteNotificationService {
    final int delai = 3600000;

    @Autowired
    EtapeActivitePpmRepository etapeActivitePpmRepository;

    @Autowired
    PpmActiviteRepository ppmActiviteRepository;

    @Autowired
    PpmActiviteMapper ppmActiviteMapper;

    @Autowired
    EtapeActivitePpmMapper etapeActivitePpmMapper;

    @Autowired
    UserNotificationRepository userNotificationRepository;

    @Autowired
    UserNotificationMapper userNotificationMapper;

    @Autowired
    DelaiMessageReporitory delaiMessageReporitory;

    @Autowired
    MembreCommissionRepository membreCommissionRepository;

    @Autowired
    ActeurRepository acteurRepository;

    @Autowired
    ActeurMapper acteurMapper;

    @Autowired
    ReferentielDelaiService referentielDelaiService;

    private final Logger log = LoggerFactory.getLogger( AlerteNotificationService.class );

    public AlerteNotificationService() { }

    @Scheduled(fixedRate = delai, zone = "GMT")
    public void executeTask() {

        log.debug("----------------> {}  <---------------", "exécutuion !!!");
        //getCurentTask();

    }

    //@Scheduled(fixedRate = 10000, zone = "GMT")
    public Integer getCurentTask() {
        List<EtapeActivitePpm> etapeActivitePpmList = etapeActivitePpmRepository.findAll();
            //.stream()
            //.filter(eap -> eap != null && eap.getFin() != null && eap.getDebut().isBefore(LocalDate.now()) && eap.getFin().isAfter(LocalDate.now()))
            //.collect(Collectors.toList());

        log.debug("====================       {}        =====================", etapeActivitePpmList.size());

        return etapeActivitePpmList.size();
    }

    public List<Integer> getStatutEtapePPMActivite() {

        List<EtapeActivitePpm> etapeActivitePpmList = etapeActivitePpmRepository.findAll();

        Long statutEnCours =  getCountStatuCourant(etapeActivitePpmList);

        Long statutDelaiProche =  getCountStatuDelaiProche(etapeActivitePpmList);

        Long statutTermines =  getCountStatuTerminer(etapeActivitePpmList);

        List<Integer> delais = Arrays.asList(statutEnCours.intValue(), statutDelaiProche.intValue(), statutTermines.intValue());

        log.debug("====================       {}        =====================", delais);

        return delais;
    }

    public Long getCountStatuCourant(List<EtapeActivitePpm> etapeActivitePpmList) {

        return etapeActivitePpmList
            .stream()
            .filter(eap -> eap.getDebut() != null && eap.getFin() != null && eap.getDebut().isBefore(LocalDate.now()) && eap.getFin().isAfter(LocalDate.now()))
            .count();
    }

    public Long getCountStatuTerminer(List<EtapeActivitePpm> etapeActivitePpmList) {

        return etapeActivitePpmList
            .stream()
            .filter(eap -> eap.getDebut() != null && eap.getDebut().isAfter(LocalDate.now()) && eap.getDebut().isBefore(LocalDate.now().minusDays(2)) )
            .count();
    }

    public Long getCountStatuDelaiProche(List<EtapeActivitePpm> etapeActivitePpmList) {

        return etapeActivitePpmList
            .stream()
            .filter(eap -> eap.getFin() != null && eap.getFin().isAfter(LocalDate.now()))
            .count();
    }

    public List<PpmActiviteDTO> findActivitesByPPM(Long ppmId) {
        List<PpmActiviteDTO> ppmActiviteDTOList = ppmActiviteRepository.findPpmActiviteByPpmIdAndDeletedIsFalse(ppmId)
            .stream()
            .map(ppmActiviteMapper::toDto)
            .collect(Collectors.toList());

        ppmActiviteDTOList.forEach(ppmActiviteDTO -> {

            List<Etat> etatList = new ArrayList<>();

            Long enCours = getCountStatuCourant(getEtapeActivitePpmByPpm(ppmActiviteDTO.getId()));
            Long termine = getCountStatuTerminer(getEtapeActivitePpmByPpm(ppmActiviteDTO.getId()));
            Long proche = getCountStatuDelaiProche(getEtapeActivitePpmByPpm(ppmActiviteDTO.getId()));

            Etat etatC = new Etat("ÉTAPES TERMINÉES", termine, getTaux(termine, enCours + proche + termine));
            Etat etatT = new Etat("ÉTAPES EN COURS", enCours, getTaux(enCours, enCours + proche + termine));
            Etat etatP = new Etat("ÉTAPES PROCHES", proche, getTaux(proche, enCours + proche + termine));

            etatList.add(etatC);
            etatList.add(etatT);
            etatList.add(etatP);

            ppmActiviteDTO.setEtats(etatList);
            //ppmActiviteDTO.setTauxRealisation();
        });

        return ppmActiviteDTOList;
    }

    public List<EtapeActivitePpm> getEtapeActivitePpmByPpm(Long ppmActiviteId) {
        return etapeActivitePpmRepository.findEtapeActivitePpmByPpmActiviteIdAndDeletedIsFalse(ppmActiviteId);
    }

    public Double getTaux(Long l1, Long l2) {
        return l1 == 0 ? 0 : (l1.doubleValue() / l2.doubleValue()) * 100;
    }

    public List<EtapeActivitePpmDTO> getEtapeNotVisited() {
        return //getStatut(
            etapeActivitePpmMapper.toDto(etapeActivitePpmRepository.findEtapeActivitePpmByDeletedIsFalse());
            /*.stream()
            .filter(eap -> eap.getDebut() != null && eap.getFin() != null && eap.getDebut().isBefore(LocalDate.now())
                && eap.getFin().isBefore(LocalDate.now().plusDays(7))
            )
            .collect(Collectors.toList()) */
        //);
    }

    public  List<EtapeActivitePpmDTO> getStatut(List<EtapeActivitePpmDTO> etapeActivitePpmDTOList) {
        etapeActivitePpmDTOList.forEach(etapeActivitePpmDTO -> {
            if (etapeActivitePpmDTO.getDebut().isAfter(LocalDate.now()) && etapeActivitePpmDTO.getDebut().isBefore(LocalDate.now().plusDays(7))) {
                etapeActivitePpmDTO.setStatut("PROCHE");
                etapeActivitePpmDTO.setDelais(findDelais(LocalDate.now(), etapeActivitePpmDTO.getDebut()));
            } else {
                if (etapeActivitePpmDTO.getFin().isBefore(LocalDate.now())) {
                    etapeActivitePpmDTO.setStatut("TERMINÉE");
                    etapeActivitePpmDTO.setDelais(findDelais(etapeActivitePpmDTO.getFin(), LocalDate.now()));
                } else {
                    if (etapeActivitePpmDTO.getDebut().isBefore(LocalDate.now()) && LocalDate.now().isBefore(etapeActivitePpmDTO.getFin())) {
                        etapeActivitePpmDTO.setStatut("EN COURS");
                        etapeActivitePpmDTO.setDelais(findDelais(etapeActivitePpmDTO.getDebut(), LocalDate.now()));
                    }
                    if (etapeActivitePpmDTO.getDebut().isEqual(LocalDate.now())) {
                        etapeActivitePpmDTO.setStatut("EN COURS");
                        etapeActivitePpmDTO.setDelais(0);
                    }
                }
            }
            etapeActivitePpmDTO.setTootip(findToolTip(etapeActivitePpmDTO.getDebut(), etapeActivitePpmDTO.getFin()));
        });

        return etapeActivitePpmDTOList.stream()
            .filter(etapeActivitePpmDTO -> etapeActivitePpmDTO.getStatut() != null && (
                etapeActivitePpmDTO.getStatut().equals("EN COURS") || etapeActivitePpmDTO.getStatut().equals("PROCHE"))
            )
            .collect(Collectors.toList());
    }

    public String findToolTip(LocalDate debut, LocalDate fin) {
        return "début: " + debut.toString() + "\nfin: " + fin.toString();
    }

    public Integer findDelais(LocalDate debut, LocalDate fin) {
        Integer n = 0;
        for (LocalDate date = debut; date.isBefore(fin); date = date.plusDays(1)) {
            n++;
        }
        return n;
    }

    public List<DelaiMessageDTO> getDelaiMessages() {
        return delaiMessageReporitory.getDalaiMessage();
    }

    public ActeurDTO getCurentActeur(Long id) {
        return acteurMapper.toDto(acteurRepository.findActeurByUserId(id));
    }

    //@Transactional(readOnly = true)
    public List<UserNotificationDTO> getAllEtapeAndTaskNotVisited(Account account) {

        List<EtapeActivitePpmDTO> etapeActivitePpmDTOList = new ArrayList<>();

        List<UserNotificationDTO> userNotificationDTOList = new ArrayList<>();

        List<DelaiMessageDTO> delaiMessageDTOList = getDelaiMessages();

        log.debug("°°°°°°°°°°°°°°°°°°       {}      °°°°°°°°°°°°°°°°°°°°°", delaiMessageDTOList);

        ActeurDTO acteurDTO = getCurentActeur(account.getId());

        log.debug(".................        {}      ..............", acteurDTO);

        List<UserNotificationDTO> userNotificationDTOArrayList = new ArrayList<>();

        List<EtapeDTO> etapeDTOList = new ArrayList<>();

        if(acteurDTO != null && acteurDTO.getUserId() != null) {
            userNotificationDTOArrayList.addAll(getUserNotificationByUserAndEtapeActivitePPM(acteurDTO.getUserId()));
            etapeDTOList.addAll(referentielDelaiService.getEpateByActeur(acteurDTO.getId()));
        }

        log.debug("::::::::::etapeDTOList:::::::::::        {}      :::::::::::etapeDTOList::::::::::", etapeDTOList);

        List<MembreCommission> membreCommissionList = membreCommissionRepository.getMembreCommissionByMembre(account.getId());

        if (!membreCommissionList.isEmpty()) {
            membreCommissionList.forEach(membreCommission -> {
                UserNotificationDTO userNotificationDTO = new UserNotificationDTO();

                userNotificationDTO.setUserId(account.getId());
                userNotificationDTO.setTacheEtapeId(membreCommission.getTacheId());
                userNotificationDTO.setTypeTache("TACHEETAPE");
                userNotificationDTO.setVisited(false);
                userNotificationDTO.setJour(LocalDate.now());
                userNotificationDTO.setDeleted(false);

                if (!contien(userNotificationDTOArrayList, userNotificationDTO)) {
                    userNotificationDTOList.add(userNotificationDTO);
                }

            });
        }

        List<EtapeActivitePpmDTO> etapeActivitePpms = getEtapeNotVisited()
            .stream()
            .filter(eap -> eap.getEtape() != null)
            .collect(Collectors.toList());

        log.debug("============etapeActivitePpms============      {}      ============etapeActivitePpms=============", etapeActivitePpms);

        etapeDTOList.forEach(etapeDTO -> {
            etapeActivitePpmDTOList.addAll(etapeActivitePpms.stream()
                    .filter(e -> e.getEtape() != null && e.getEtape().getId() != null && e.getEtape().getId().equals(etapeDTO.getId()))
                    .collect(Collectors.toList())
            );
        });

        log.debug("==========etapeActivitePpmDTOList==========      {}      =============etapeActivitePpmDTOList============", etapeActivitePpmDTOList);

        etapeActivitePpmDTOList.forEach(eap -> {
            UserNotificationDTO userNotificationDTO = new UserNotificationDTO();

            userNotificationDTO.setUserId(account.getId());
            userNotificationDTO.setTacheEtapeId(eap.getId());
            userNotificationDTO.setTypeTache("ETAPEACTIVITEPPM");
            userNotificationDTO.setVisited(false);
            userNotificationDTO.setJour(LocalDate.now());
            userNotificationDTO.setDeleted(false);

            if (!contien(userNotificationDTOArrayList, userNotificationDTO)) {
                userNotificationDTOList.add(userNotificationDTO);
            }
        });

        List<UserNotificationDTO> userNotificationDTOS1 = new ArrayList<>();
        List<UserNotificationDTO> userNotificationDTOS2 = new ArrayList<>();

        userNotificationRepository.saveAll(userNotificationMapper.toEntity(userNotificationDTOList));

        List<UserNotificationDTO> userNotificationDTOS = getNotificationNotVisited(account.getId());

        userNotificationDTOS.forEach(userNotif -> {
            if (userNotif.getTypeTache() != null && userNotif.getTypeTache().equals("ETAPEACTIVITEPPM")) {
                UserNotificationDTO<EtapeActivitePpmDTO> userNotificationDTO1 = new UserNotificationDTO<>();
                List<EtapeActivitePpmDTO> etapeActivitePpmDTOList1 = etapeActivitePpmDTOList.stream()
                    .filter(eap -> eap.getId().equals(userNotif.getTacheEtapeId()))
                    .collect(Collectors.toList());

                log.debug("=================<       {}      >==============", etapeActivitePpmDTOList1);

                userNotificationDTO1.setId(userNotif.getId());
                userNotificationDTO1.setTypeTache(userNotif.getTypeTache());
                userNotificationDTO1.setJour(userNotif.getJour());
                userNotificationDTO1.setDeleted(userNotif.getDeleted());
                userNotificationDTO1.setVisited(userNotif.getVisited());
                userNotificationDTO1.setUserId(userNotif.getUserId());
                userNotificationDTO1.setTacheEtapeId(userNotif.getTacheEtapeId());
                userNotificationDTO1.setTacheEtape(!etapeActivitePpmDTOList1.isEmpty() ? etapeActivitePpmDTOList1.get(0) : null);

                userNotificationDTOS1.add(userNotificationDTO1);
            }
            if (userNotif.getTypeTache() != null && userNotif.getTypeTache().equals("TACHEETAPE")) {
                UserNotificationDTO<EtapeActivitePpmDTO> userNotificationDTO2 = new UserNotificationDTO<>();
                List<MembreCommission> membreCommissionList1 = membreCommissionList.stream()
                    .filter(mc -> mc.getTacheId().equals(userNotif.getTacheEtapeId()))
                    .collect(Collectors.toList());

                userNotificationDTO2.setId(userNotif.getId());
                userNotificationDTO2.setTypeTache(userNotif.getTypeTache());
                userNotificationDTO2.setJour(userNotif.getJour());
                userNotificationDTO2.setDeleted(userNotif.getDeleted());
                userNotificationDTO2.setVisited(userNotif.getVisited());
                userNotificationDTO2.setUserId(userNotif.getUserId());
                userNotificationDTO2.setTacheEtapeId(userNotif.getTacheEtapeId());
                //userNotificationDTO2.setTacheEtape(!membreCommissionList1.isEmpty() ? membreCommissionList1.get(0). : null);
                //userNotif.setTacheEtape(!membreCommissionList1.isEmpty() ? membreCommissionList1.get(0).getT : null);

                userNotificationDTOS2.add(userNotificationDTO2);
            }

        });

        userNotificationDTOS = new ArrayList<>();
        userNotificationDTOS.addAll(userNotificationDTOS1);
        //userNotificationDTOS.addAll(userNotificationDTOS2);
        //userNotificationDTOS.

        return userNotificationDTOS;
    }

    public List<UserNotificationDTO> getNotificationNotVisited(Long userId) {
        return userNotificationRepository.findUserNotificationsByUserIdAndDeletedIsFalse(userId)
            .stream()
            .map(userNotificationMapper::toDto)
            .collect(Collectors.toList());
    }

    public List<UserNotificationDTO> getUserNotificationByUserAndEtapeActivitePPM(Long userId) {
        return userNotificationMapper.toDto(userNotificationRepository.findUserNotificationsByUserIdAndDeletedIsFalse(userId));
    }

    public Boolean contien(List<UserNotificationDTO> userNotificationDTOList, UserNotificationDTO userNotificationDTO) {
         AtomicBoolean exist = new AtomicBoolean(false);

        userNotificationDTOList.forEach(userNotificationDTO1 -> {
            if (userNotificationDTO.getUserId().equals(userNotificationDTO1.getUserId()) &&
                (userNotificationDTO.getTacheEtapeId() != null && userNotificationDTO.getTacheEtapeId().equals(userNotificationDTO1.getTacheEtapeId())
                    || (userNotificationDTO.getTacheEtapeId() != null && userNotificationDTO.getTacheEtapeId().equals(userNotificationDTO1.getTacheEtapeId()))
                )
            ) {
                exist.set(true);
                log.debug("******************       {}      *********************       {}      ****************", userNotificationDTO1, userNotificationDTO);
            }
        });
        return exist.get();

    }
}
