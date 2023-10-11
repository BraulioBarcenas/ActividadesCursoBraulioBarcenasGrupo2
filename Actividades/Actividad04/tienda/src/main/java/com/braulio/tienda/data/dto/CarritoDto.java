package com.braulio.tienda.data.dto;

import java.io.Serializable;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
public class CarritoDto implements Serializable{
    private Integer idCarrito;
    @NotNull(message = "Debes ingresar un usuario.")
    @Positive(message = "Debe ingresar un usuario valido.")
    private Integer usuario;
    @NotNull(message = "Debes ingresar un producto.")
    @Positive(message = "Debe ingresar un producto valido.")
    private Integer producto;
}