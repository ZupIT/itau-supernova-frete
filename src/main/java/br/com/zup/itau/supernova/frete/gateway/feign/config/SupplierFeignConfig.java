package br.com.zup.itau.supernova.frete.gateway.feign.config;


import br.com.zup.itau.supernova.frete.gateway.feign.AuthenticatorOnSupplier;
import br.com.zup.itau.supernova.frete.gateway.feign.config.properties.SupplierBaseProperties;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class SupplierFeignConfig {

    private final SupplierBaseProperties supplierBaseProperties;
    private final AuthenticatorOnSupplier authenticator;

    @Bean
    Logger.Level feignLoggerLevel() {
        String loggerLevel = supplierBaseProperties.getFeignLoggerLevel();
        return  loggerLevel.isEmpty() ? Logger.Level.NONE : Logger.Level.valueOf(loggerLevel.toUpperCase());
    }

    @Bean
    Retryer retryer() {
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(
                supplierBaseProperties.getConnectTimeout(),
                TimeUnit.MILLISECONDS,
                supplierBaseProperties.getReadTimeout(),
                TimeUnit.MILLISECONDS,
                false
        );
    }

    @Bean("supplierRequestInterceptor")
    public RequestInterceptor supplierRequestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + authenticator.getAccessToken());
            requestTemplate.header("x-application-key", supplierBaseProperties.getXApplicationKey());
        };
    }

}
