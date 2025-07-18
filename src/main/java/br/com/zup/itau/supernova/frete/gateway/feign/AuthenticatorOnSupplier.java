package br.com.zup.itau.supernova.frete.gateway.feign;

import br.com.zup.itau.supernova.frete.gateway.feign.client.AuthenticationOnSupplierFeignApi;
import br.com.zup.itau.supernova.frete.gateway.feign.config.properties.SupplierBaseProperties;
import br.com.zup.itau.supernova.frete.gateway.feign.model.AuthenticationOnSupplierResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticatorOnSupplier {
    private LocalDateTime expiration = LocalDateTime.now();
    private String accessToken = "";

    private final AuthenticationOnSupplierFeignApi client;
    private final SupplierBaseProperties properties;

    public AuthenticatorOnSupplier(AuthenticationOnSupplierFeignApi client, SupplierBaseProperties properties) {
        this.client = client;
        this.properties = properties;
    }

    public synchronized String getAccessToken() {
        if (tokenNeedsUpdate()) {
            updateToken();
        }
        return accessToken;
    }

    private boolean tokenNeedsUpdate() {
        return LocalDateTime.now().isAfter(expiration) || accessToken.isEmpty();
    }

    private void updateToken() {
        Map<String, String> request = new HashMap<>();
        request.put("clientId", properties.getClientId());
        request.put("clientSecret", properties.getClientSecret());

        AuthenticationOnSupplierResponse response = client.execute(request);

        expiration = LocalDateTime.now().plusSeconds(response.expiresIn() - 2L);
        accessToken = response.accessToken();
    }
}