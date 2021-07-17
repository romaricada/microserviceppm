package mena.gov.bf.proxies;

import mena.gov.bf.bean.MembreCommission;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = ConstantsConfig.microserviceNameDacCam, url = ConstantsConfig.microserviceUrlDacCam)
public interface MembreCommissionRepository {

    @GetMapping("/membre-commissions/membreid")
    List<MembreCommission> getMembreCommissionByMembre(@RequestParam(name = "membreId") Long membreId);
}
