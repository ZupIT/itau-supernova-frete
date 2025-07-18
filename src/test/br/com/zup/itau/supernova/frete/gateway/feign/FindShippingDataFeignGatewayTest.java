package br.com.zup.itau.supernova.frete.gateway.feign;

import br.com.zup.itau.supernova.frete.domain.ShippingData;
import br.com.zup.itau.supernova.frete.gateway.feign.client.FindShippingDataOnSupplierFeignApi;
import br.com.zup.itau.supernova.frete.gateway.feign.model.FindShippingDataOnSupplierResponse;
import br.com.zup.itau.supernova.frete.mapper.ShippingDataMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FindShippingDataFeignGatewayTest {

    private FindShippingDataOnSupplierFeignApi apiMock;
    private FindShippingDataFeignGateway gateway;

    @BeforeEach
    void setUp() {
        apiMock = mock(FindShippingDataOnSupplierFeignApi.class);
        gateway = new FindShippingDataFeignGateway(apiMock);
    }

    @Test
    void shouldReturnShippingDataWhenApiReturnsValidResponse() {
        String zipcode = "12345-678";
        String sku = "SKU123";
        boolean isForever = false;

        FindShippingDataOnSupplierResponse response = new FindShippingDataOnSupplierResponse(zipcode, "SKU123", 5);

        ShippingData expectedDomain = ShippingDataMapper.SHIPPING_DATA_MAPPER.toDomain(response);

        when(apiMock.execute(zipcode, sku, isForever)).thenReturn(response);

        // when
        Optional<ShippingData> result = gateway.execute(zipcode, sku, isForever);

        // then
        assertTrue(result.isPresent());
        assertEquals(expectedDomain, result.get());
        verify(apiMock).execute(zipcode, sku, isForever);
    }

    @Test
    void shouldReturnEmptyWhenApiReturnsNull() {
        // given
        String zipcode = "12345-678";
        String sku = "SKU123";
        boolean isForever = true;

        when(apiMock.execute(zipcode, sku, isForever)).thenReturn(null);

        // when
        Optional<ShippingData> result = gateway.execute(zipcode, sku, isForever);

        // then
        assertTrue(result.isEmpty());
        verify(apiMock).execute(zipcode, sku, isForever);
    }
}
