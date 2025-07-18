package br.com.zup.itau.supernova.frete.gateway.feign.client;

import br.com.zup.itau.supernova.frete.gateway.feign.config.AuthenticationFeignConfig;
import br.com.zup.itau.supernova.frete.gateway.feign.model.AuthenticationOnSupplierResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(
        name = "authenticationOnSupplierFeignApi",
        url = "${supplier-properties.url}",
        configuration = AuthenticationFeignConfig.class
)
public interface AuthenticationOnSupplierFeignApi {

    @PostMapping(
            value = "/v1/token",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    AuthenticationOnSupplierResponse execute(@RequestBody Map<String, ?> request);
}