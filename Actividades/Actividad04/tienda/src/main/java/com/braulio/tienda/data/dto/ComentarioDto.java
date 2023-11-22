package com.braulio.tienda.data.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ComentarioDto implements Serializable{
    private Integer idComentario;
    @NotBlank(message = "Introduzca un comentario.")
    @Size(min = 1,max = 255,message = "Introduzca un tamaño correcto de cadena")
    private String comentario;
    private Date fecha;
    @NotNull(message = "Debes ingresar un producto.")
    @Positive(message = "Debe ingresar un producto valido.")
    private int producto;
    @NotNull(message = "Debes ingresar una tienda.")
    @Positive(message = "Debe ingresar una tienda valida.")
    private int tienda;
    @NotNull(message = "Debes ingresar un usuario.")
    @Positive(message = "Debe ingresar un usuario valido.")
    private int usuario;
}
