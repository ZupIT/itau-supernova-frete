package br.com.zup.itau.supernova.frete.gateway.feign.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindANewPriceOnSupplierFeignResponse {
    private String sku;
    private Long price;
    private Integer stockBalance;
}