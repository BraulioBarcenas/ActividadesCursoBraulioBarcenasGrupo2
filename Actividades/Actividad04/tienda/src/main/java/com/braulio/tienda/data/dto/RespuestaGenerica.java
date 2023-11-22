package com.braulio.tienda.data.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RespuestaGenerica {
    
    private int codigo;
    private boolean exito;
    private String mensaje;
    private List<Object> datos;

    public RespuestaGenerica(){
        this.datos = new ArrayList<>();
    }

   public RespuestaGenerica(int codigo, boolean exito , String mensaje){
        super();
        this.codigo = codigo;
        this.exito = exito;
        this.mensaje = mensaje;
   }
}
