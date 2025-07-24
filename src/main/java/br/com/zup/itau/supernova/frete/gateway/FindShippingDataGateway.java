package br.com.zup.itau.supernova.frete.gateway;


import br.com.zup.itau.supernova.frete.domain.ShippingData;

import java.util.Optional;

public interface FindShippingDataGateway {

    Optional<ShippingData> execute(final String zipcode, final String sku, final boolean isForever);

}