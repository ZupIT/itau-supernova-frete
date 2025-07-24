package br.com.zup.itau.supernova.frete.mapper;


import br.com.zup.itau.supernova.frete.controller.model.GetShippingDataResponse;
import br.com.zup.itau.supernova.frete.domain.ShippingData;
import br.com.zup.itau.supernova.frete.gateway.feign.model.FindShippingDataOnSupplierResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShippingDataMapper {

    ShippingDataMapper SHIPPING_DATA_MAPPER = Mappers.getMapper(ShippingDataMapper.class);

    ShippingData toDomain(FindShippingDataOnSupplierResponse response);

    GetShippingDataResponse toResponse(ShippingData shippingData);
}
