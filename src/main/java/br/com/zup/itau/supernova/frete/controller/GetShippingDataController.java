package br.com.zup.itau.supernova.frete.controller;

import br.com.zup.itau.supernova.frete.controller.model.GetShippingDataRequest;
import br.com.zup.itau.supernova.frete.controller.model.GetShippingDataResponse;
import br.com.zup.itau.supernova.frete.domain.ShippingData;
import br.com.zup.itau.supernova.frete.usecase.GetShippingDataUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static br.com.zup.itau.supernova.frete.mapper.ShippingDataMapper.SHIPPING_DATA_MAPPER;


@RestController
public class GetShippingDataController {
    private final GetShippingDataUseCase getShippingDataUseCase;

    public GetShippingDataController(GetShippingDataUseCase getShippingDataUseCase) {
        this.getShippingDataUseCase = getShippingDataUseCase;
    }

    @PostMapping(value = "/v1/calculateFreight")
    public ResponseEntity<GetShippingDataResponse> execute(
            @RequestBody @Valid GetShippingDataRequest request,
            @RequestParam(value = "isForever") Boolean isForever
    ) {
        ShippingData shippingData = getShippingDataUseCase.execute(
                request.zipCode(),
                request.skuList().getFirst(),
                isForever
        );

        return ResponseEntity.ok(SHIPPING_DATA_MAPPER.toResponse(shippingData));
    }
}
