package br.com.zup.itau.supernova.frete.gateway.feign.config;

import br.com.zup.itau.supernova.frete.gateway.feign.config.properties.SupplierBaseProperties;
import feign.Logger;
import feign.Request;
import feign.RequestInterceptor;
import feign.Retryer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
public class AuthenticationFeignConfig {

    private final SupplierBaseProperties properties;

    @Bean
    Logger.Level feignLoggerLevel() {
        String loggerLevel = properties.getFeignLoggerLevel();
        return loggerLevel.isEmpty() ? Logger.Level.NONE : Logger.Level.valueOf(loggerLevel.toUpperCase());
    }

    @Bean
    Retryer retryer() {
        return new Retryer.Default();
    }

    @Bean
    public Request.Options options() {
        return new Request.Options(
                properties.getConnectTimeout(),
                TimeUnit.MILLISECONDS,
                properties.getReadTimeout(),
                TimeUnit.MILLISECONDS,
                false
        );
    }

    @Bean("authenticationInterceptor")
    public RequestInterceptor authenticationInterceptor() {
        return requestTemplate -> requestTemplate.header(
                "x-application-key", properties.getXApplicationKey()
        );
    }
}