package br.com.zup.itau.supernova.frete.gateway.feign.model;

public record FindShippingDataOnSupplierResponse(String zipcode, String cost, Integer delivery) {}
