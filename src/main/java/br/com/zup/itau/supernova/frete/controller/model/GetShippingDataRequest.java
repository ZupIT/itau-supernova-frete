package br.com.zup.itau.supernova.frete.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record GetShippingDataRequest(
        @JsonProperty("skuList")
        @Size(min = 1, max = 1)
        @NotNull
        List<@Size(min = 1, max = 36) String> skuList,

        @JsonProperty("zipCode")
        @NotBlank
        @Size(min = 1, max = 10)
        String zipCode
) {
}