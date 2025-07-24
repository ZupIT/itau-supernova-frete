package br.com.zup.itau.supernova.frete.usecase;

import br.com.zup.itau.supernova.frete.domain.ShippingData;
import br.com.zup.itau.supernova.frete.gateway.FindShippingDataGateway;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetShippingDataUseCaseTest {

    @Mock
    private FindShippingDataGateway findShippingDataGateway;

    @InjectMocks
    private GetShippingDataUseCase getShippingDataUseCase;

    public GetShippingDataUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnShippingDataWhenFound() {
        // Arrange
        String zipcode = "12345678";
        String sku = "SKU12345";
        boolean isForever = true;
        Integer delivery = 5;

        ShippingData expectedShippingData = new ShippingData(zipcode, sku, delivery); // Replace with actual constructor or builder
        when(findShippingDataGateway.execute(zipcode, sku, isForever))
                .thenReturn(Optional.of(expectedShippingData));

        // Act
        ShippingData result = getShippingDataUseCase.execute(zipcode, sku, isForever);

        // Assert
        assertNotNull(result);
        assertEquals(expectedShippingData, result);
        verify(findShippingDataGateway, times(1)).execute(zipcode, sku, isForever);
    }

    @Test
    void shouldThrowExceptionWhenShippingDataNotFound() {
        // Arrange
        String zipcode = "12345-678";
        String sku = "SKU12345";
        boolean isForever = true;

        when(findShippingDataGateway.execute(zipcode, sku, isForever))
                .thenReturn(Optional.empty());

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                getShippingDataUseCase.execute(zipcode, sku, isForever)
        );

        assertEquals("Erro ao buscar dias de entrega no parceiro para CEP: [12345-678] e SKU: [SKU12345].", exception.getMessage());
        verify(findShippingDataGateway, times(1)).execute(zipcode, sku, isForever);
    }
}