package br.com.zup.itau.supernova.frete.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetShippingDataResponse(
        @JsonProperty("zipcode") String zipcode,
        @JsonProperty("cost") String cost,
        @JsonProperty("delivery") Integer delivery
) {
}