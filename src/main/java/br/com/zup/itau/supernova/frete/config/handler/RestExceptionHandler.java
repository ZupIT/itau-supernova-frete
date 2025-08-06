package br.com.zup.itau.supernova.frete.config.handler;

import br.com.zup.itau.supernova.frete.controller.model.ErrorResponse;
import br.com.zup.itau.supernova.frete.gateway.feign.exception.SupplierIntegrationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    private static final String DEFAULT_MESSAGE = "Erro ao processar a solicitação!";
    private static final String INVALID_MESSAGE = "Atributos inválidos!";
    private static final String INVALID_FIELD_PATTERN = "%s inválido!";
    private static final String PAYLOAD_RESPONSE = "payload resposta:";

    @ExceptionHandler(SupplierIntegrationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleSupplierException(final SupplierIntegrationException ex) {
        var errorResponse = ErrorResponse.builder().message(ex.getMessage()).build();
        LOGGER.warn("Supplier exception occurred: [{}]", errorResponse);
        return errorResponse;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleArgumentNotValidException(final MethodArgumentNotValidException ex) {
        final ErrorResponse errorResponse = ErrorResponse.builder().message(INVALID_MESSAGE).build();
        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                errorResponse.addError(String.format("%s - %s", fieldError.getField(), fieldError.getDefaultMessage()))
        );
        LOGGER.warn("{} [{}]", PAYLOAD_RESPONSE, errorResponse);
        return errorResponse;
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameterException(final MissingServletRequestParameterException ex) {
        final ErrorResponse errorResponse = ErrorResponse.builder().message(INVALID_MESSAGE).build();
        final String parameterNameCapitalized = ex.getParameterName();
        errorResponse.addError(String.format(INVALID_FIELD_PATTERN, parameterNameCapitalized));
        LOGGER.warn("{} [{}]", PAYLOAD_RESPONSE, errorResponse);
        return errorResponse;
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(final IllegalArgumentException ex) {
        var errorResponse = ErrorResponse.builder().message(ex.getMessage()).build();
        LOGGER.warn("{} [{}]", PAYLOAD_RESPONSE, errorResponse);
        return errorResponse;
    }

    @ExceptionHandler({IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalStateException(final IllegalStateException ex) {
        var errorResponse = ErrorResponse.builder().message(ex.getMessage()).build();
        LOGGER.warn("{} [{}]", PAYLOAD_RESPONSE, errorResponse);
        return errorResponse;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, InvalidFormatException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadableException(Exception exception) {
        final ErrorResponse errorResponse = ErrorResponse.builder().message(INVALID_MESSAGE).build();
        final Throwable cause = exception.getCause() == null ? exception : exception.getCause();
        if (cause instanceof UnrecognizedPropertyException) {
            final String propertyNameCapitalized = ((UnrecognizedPropertyException) cause).getPropertyName();
            errorResponse.addError(String.format(INVALID_FIELD_PATTERN, propertyNameCapitalized));
        } else if (cause instanceof InvalidFormatException) {
            for (JsonMappingException.Reference reference : ((InvalidFormatException) cause).getPath()) {
                final String fieldNameCapitalized = reference.getFieldName();
                errorResponse.addError(String.format(INVALID_FIELD_PATTERN, fieldNameCapitalized));
            }
        }
        LOGGER.warn("{} [{}]", PAYLOAD_RESPONSE, errorResponse);
        return errorResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public final ErrorResponse handleException(final Exception exception) {
        final ErrorResponse errorResponse = ErrorResponse.builder().message(DEFAULT_MESSAGE).build();
        LOGGER.error(
                "Erro ao processar a solicitação. payload resposta: [{}]", errorResponse, exception
        );
        return errorResponse;
    }
}