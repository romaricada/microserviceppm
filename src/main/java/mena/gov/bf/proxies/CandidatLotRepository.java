package mena.gov.bf.proxies;


import mena.gov.bf.bean.CandidatLot;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = ConstantsConfig.microserviceNameDacCam, url = ConstantsConfig.microserviceUrlDacCam)
public interface CandidatLotRepository {
    @GetMapping("/candidat-lots")
    List<CandidatLot> findCandidatLot();

    @PostMapping("/candidat-lots")
    CandidatLot saveCandidatLot();

    @GetMapping("/candidat-lots/all-candidat-by-lot")
    List<CandidatLot> findAllByLot(Long lotId);
}
