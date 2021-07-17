package mena.gov.bf.proxies;

import mena.gov.bf.bean.Lot;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = ConstantsConfig.microserviceNameDacCam, url = ConstantsConfig.microserviceUrlDacCam)
public interface LotRepository {

    @GetMapping("/lots")
    List<Lot> findLot();
}
