package br.com.zup.itau.supernova.frete.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class ErrorResponse {

	@JsonProperty("mensagem")
    private String message;

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("erros")
    private List<String> errors;

    public ErrorResponse() {
        this.errors = new ArrayList<>();
    }

    public ErrorResponse(final String message) {
        this();
        this.message = message;
    }

    public void addError(final String key){
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(key);
    }
}


