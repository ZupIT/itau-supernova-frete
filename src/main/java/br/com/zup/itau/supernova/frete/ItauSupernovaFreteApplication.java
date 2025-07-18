package br.com.zup.itau.supernova.frete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "br.com.zup.itau")
public class ItauSupernovaFreteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItauSupernovaFreteApplication.class, args);
    }
}