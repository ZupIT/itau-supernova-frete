package br.com.zup.itau.supernova.frete.gateway.feign.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("supplier-properties")
@Data
public class SupplierBaseProperties {

    private int connectTimeout;
    private int readTimeout;
    private String xApplicationKey;
    private String url;
    private String clientId;
    private String clientSecret;
    private String feignLoggerLevel = "NONE";
}
