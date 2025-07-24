package br.com.zup.itau.supernova.frete.gateway.feign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CreateSkuInCatalogRequest(
        @JsonProperty("category") String category,
        @JsonProperty("product") String product,
        @JsonProperty("isBalloon") boolean isBalloon,
        @JsonProperty("sku") String sku,
        @JsonProperty("department") String department,
        @JsonProperty("image") String image,
        @JsonProperty("availableQuantity") int availableQuantity,
        @JsonProperty("screenSize") String screenSize,
        @JsonProperty("capacity") String capacity,
        @JsonProperty("color") String color,
        @JsonProperty("colorHexadecimal") String colorHexadecimal,
        @JsonProperty("price") int price,
        @JsonProperty("voltage") String voltage,
        @JsonProperty("description") DescriptionRequest description

) {
    public record DescriptionRequest(
            @JsonProperty("shortDescription") String shortDescription,
            @JsonProperty("detailedDescription") List<DetailedDescriptionRequest> detailedDescription
    ) {
    }

    public record DetailedDescriptionRequest(
            @JsonProperty("sectionName") String sectionName,
            @JsonProperty("subSections") List<SubSectionDescriptionRequest> subSections
    ) {
    }

    public record SubSectionDescriptionRequest(
            @JsonProperty("title") String title,
            @JsonProperty("content") String content
    ) {
    }
}