package br.com.zup.itau.supernova.frete.gateway.feign.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SupplierIntegrationException extends RuntimeException {
    public SupplierIntegrationException(String message) {
        super(message);
        log.error("[SUPPLIER EXCEPTION] {}", message);
    }
}
