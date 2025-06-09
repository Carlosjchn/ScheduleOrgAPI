package com.jpa1prueba.scheduleorganizermodule.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ScheduleGenerationException.class)
    public ResponseEntity<ErrorResponse> handleScheduleGenerationException(ScheduleGenerationException ex) {
        ErrorResponse error = new ErrorResponse(
            "Error al generar el horario",
            ex.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpClientErrorException.class, HttpServerErrorException.class})
    public ResponseEntity<ErrorResponse> handleHttpException(Exception ex) {
        String message = "Error al generar el horario";
        String details = ex.getMessage();
        
        // Extraer el mensaje de error del microservicio si está disponible
        if (ex instanceof HttpServerErrorException) {
            String responseBody = ((HttpServerErrorException) ex).getResponseBodyAsString();
            if (responseBody.contains("detail")) {
                details = responseBody.split("detail\":\"")[1].split("\"")[0];
            }
        }

        ErrorResponse error = new ErrorResponse(message, details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
            "Error interno del servidor",
            "Se produjo un error inesperado. Por favor, inténtelo de nuevo más tarde."
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 