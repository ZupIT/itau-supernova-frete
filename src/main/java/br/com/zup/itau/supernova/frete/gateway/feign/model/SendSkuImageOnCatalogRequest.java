package br.com.zup.itau.supernova.frete.gateway.feign.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class SendSkuImageOnCatalogRequest {
    private MultipartFile skuImage;
    private MultipartFile categoryImage;

    public SendSkuImageOnCatalogRequest(
            @RequestPart(value = "skuImage") MultipartFile skuImage,
            @RequestPart(value = "categoryImage") MultipartFile categoryImage
    ){
        this.skuImage = skuImage;
        this.categoryImage = categoryImage;
    }
}
