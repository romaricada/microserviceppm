package mena.gov.bf.proxies;

import mena.gov.bf.bean.Engagement;
import mena.gov.bf.utils.ConstantsConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient(name = ConstantsConfig.microserviceNameExecution, url = ConstantsConfig.microserviceUrlExecution)
public interface EngagementRepository {
    @GetMapping("/engagements")
    List<Engagement> findAllEngagement();

    @PostMapping("/engagements")
    Engagement saveEngagement(Engagement engagement);


    @PutMapping("/engagements")
    Engagement updateEngagement(Engagement engagement);

    @GetMapping("/engagements/montant-a-reporter")
    Double  getMontantAReporter(Long activiteId);


}
