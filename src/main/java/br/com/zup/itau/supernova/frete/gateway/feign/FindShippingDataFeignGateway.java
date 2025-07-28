package br.com.zup.itau.supernova.frete.gateway.feign;


import br.com.zup.itau.supernova.frete.domain.ShippingData;
import br.com.zup.itau.supernova.frete.gateway.FindShippingDataGateway;
import br.com.zup.itau.supernova.frete.gateway.feign.client.FindShippingDataOnSupplierFeignApi;
import br.com.zup.itau.supernova.frete.mapper.ShippingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindShippingDataFeignGateway implements FindShippingDataGateway {
    private final FindShippingDataOnSupplierFeignApi findShippingDataOnSupplierFeignApi;

    @Override
    public Optional<ShippingData> execute(final String zipcode, final String sku, final boolean isForever) {
        log.info("[GATEWAY] [START] Executing shipping data search | zipcode={} | sku={} | isForever={}", zipcode, sku, isForever);
        
        var response = findShippingDataOnSupplierFeignApi.execute(zipcode, sku, isForever);
        log.info("[GATEWAY] [SUCCESS] Shipping data search completed successfully | zipcode={} | sku={} | isForever={}", zipcode, sku, isForever);

        return Optional.ofNullable(ShippingDataMapper.SHIPPING_DATA_MAPPER.toDomain(response));
    }
}