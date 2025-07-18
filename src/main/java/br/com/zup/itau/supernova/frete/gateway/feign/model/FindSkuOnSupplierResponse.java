package br.com.zup.itau.supernova.frete.gateway.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;

public record FindSkuOnSupplierResponse(
        @JsonProperty("category") String category,
        @JsonProperty("product") String product,
        @JsonProperty("balloon") boolean isBalloon,
        @JsonProperty("sku") int sku,
        @JsonProperty("availableQuantity") int availableQuantity,
        @JsonProperty("image") String image,
        @JsonProperty("department") String department,
        @JsonProperty("screenSize") String screenSize,
        @JsonProperty("capacity") String capacity,
        @JsonProperty("color") String color,
        @JsonProperty("colorHexadecimal") String colorHexadecimal,
        @JsonProperty("voltage") String voltage,
        @JsonProperty("price") int price,
        @JsonProperty("description") FindSkuDescriptionOnSupplierResponse description,
        @JsonProperty("productId") int productId,
        @JsonProperty("departmentVTEX") String departmentId,
        @JsonProperty("ean") String ean,
        @JsonProperty("referenceId") String referenceId,
        @JsonProperty("categoriesMap") HashMap<String, String> categoriesMap,
        @JsonProperty("videos") List<String> videos,
        @JsonProperty("images") List<String> images
) {

    public record FindSkuDescriptionOnSupplierResponse(
            @JsonProperty("shortDescription") String shortDescription,
            @JsonProperty("detailedDescription") List<FindSkuDetailedDescriptionOnSupplierResponse> detailedDescription
    ) {}

    public record FindSkuDetailedDescriptionOnSupplierResponse(
            @JsonProperty("sectionName") String sectionName,
            @JsonProperty("subSections") List<FindSkuSubSectionDescriptionOnSupplierResponse> subSections
    ) {}

    public record FindSkuSubSectionDescriptionOnSupplierResponse(
            @JsonProperty("title") String title,
            @JsonProperty("content") String content,
            @JsonProperty("subtitle") String subtitle,
            @JsonProperty("level1") String level1,
            @JsonProperty("media") List<String> media
    ) {}
}

