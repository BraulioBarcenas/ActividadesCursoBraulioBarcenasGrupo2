package com.braulio.tienda.data.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ComentarioDto implements Serializable{
    private Integer idComentario;
    private String comentario;
    private Date fecha;
    private int producto;
    private int tienda;
    private int usuario;
}
