package br.com.zup.itau.supernova.frete.gateway.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationOnSupplierResponse(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("expires_in") Integer expiresIn
) {
}