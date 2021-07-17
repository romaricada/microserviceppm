package mena.gov.bf.proxies;

import mena.gov.bf.alerteNotification.entity.DelaiMessageDTO;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = ConstantsConfig.microserviceNameDacCam, url = ConstantsConfig.microserviceUrlDacCam)
public interface DelaiMessageReporitory {

    @GetMapping("/delai-messages/all")
    List<DelaiMessageDTO> getDalaiMessage();
}
