package br.com.zup.itau.supernova.frete.gateway.feign.client;

import br.com.zup.itau.supernova.frete.gateway.feign.config.SupplierFeignConfig;
import br.com.zup.itau.supernova.frete.gateway.feign.model.FindShippingDataOnSupplierResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "findShippingCostSupplierFeignApi",
        url = "${supplier-properties.url}",
        configuration = SupplierFeignConfig.class
)
public interface FindShippingDataOnSupplierFeignApi {

    @GetMapping(
            value = "/v1/frete/{zipcode}/{sku}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    FindShippingDataOnSupplierResponse execute(
            @PathVariable String zipcode,
            @PathVariable String sku,
            @RequestParam("balloon") Boolean balloon
    );
}

