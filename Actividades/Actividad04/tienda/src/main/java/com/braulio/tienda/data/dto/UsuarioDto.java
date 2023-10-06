package com.braulio.tienda.data.dto;

import java.io.Serializable;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto implements Serializable{
    Integer idUsuario;
    String nombre;
    String apPat;
    String apMat;
    String email;
    String password;

    public UsuarioDto(){
        
    }
}
