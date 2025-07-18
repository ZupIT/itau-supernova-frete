package br.com.zup.itau.supernova.frete.gateway.feign;


import br.com.zup.itau.supernova.frete.domain.ShippingData;
import br.com.zup.itau.supernova.frete.gateway.FindShippingDataGateway;
import br.com.zup.itau.supernova.frete.gateway.feign.client.FindShippingDataOnSupplierFeignApi;
import br.com.zup.itau.supernova.frete.mapper.ShippingDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindShippingDataFeignGateway implements FindShippingDataGateway {
    private final FindShippingDataOnSupplierFeignApi findShippingDataOnSupplierFeignApi;

    @Override
    public Optional<ShippingData> execute(final String zipcode, final String sku, final boolean isForever) {
        var response = findShippingDataOnSupplierFeignApi.execute(zipcode, sku, isForever);
        return Optional.ofNullable(ShippingDataMapper.SHIPPING_DATA_MAPPER.toDomain(response));
    }
}