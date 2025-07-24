package br.com.zup.itau.supernova.frete.usecase;


import br.com.zup.itau.supernova.frete.domain.ShippingData;
import br.com.zup.itau.supernova.frete.gateway.FindShippingDataGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class GetShippingDataUseCase {
    private final FindShippingDataGateway findShippingDataGateway;

    public ShippingData execute(final String zipcode, final String sku, final boolean isForever) {
        return findShippingDataGateway.execute(zipcode, sku, isForever)
                .orElseThrow(illegalStateException(zipcode, sku));
    }

    private static Supplier<IllegalStateException> illegalStateException(final String zipcode, final String sku) {
        return () -> new IllegalStateException(
                "Erro ao buscar dias de entrega no parceiro para CEP: [%S] e SKU: [%s].".formatted(zipcode, sku)
        );
    }
}