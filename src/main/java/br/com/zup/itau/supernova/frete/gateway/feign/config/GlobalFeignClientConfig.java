package br.com.zup.itau.supernova.frete.gateway.feign.config;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalFeignClientConfig {

    @Bean
    public Client feignClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .build();
        return new ApacheHttpClient(httpClient);
    }
}