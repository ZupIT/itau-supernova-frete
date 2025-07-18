package br.com.zup.itau.supernova.frete.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkuUpdatedNotificationRequest {
    @NotBlank
    private String sku;

    @NotNull
    private Boolean isActive;

    @NotNull
    private Boolean stockModified;

    @NotNull
    private Boolean priceModified;

    @NotNull
    private Boolean descriptionModified;

    @NotNull
    @JsonProperty("balloon")
    private Boolean balloonPayment;
}