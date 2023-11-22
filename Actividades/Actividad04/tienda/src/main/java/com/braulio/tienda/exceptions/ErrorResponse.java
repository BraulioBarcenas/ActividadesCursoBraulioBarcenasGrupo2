package com.braulio.tienda.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private int codigo;
    private String estatus;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(HttpStatus httpStatus, String mensaje){
        this.codigo = httpStatus.value();
        this.estatus = httpStatus.name();
        this.message = mensaje;
    }
}
