package com.braulio.tienda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.data.dto.TiendaDto;
import com.braulio.tienda.data.dto.UsuarioDtoPass;
import com.braulio.tienda.service.TiendaService;
import com.braulio.tienda.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@Validated
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TiendaService tiendaService;

    @GetMapping("/getUsuarios")
    public ResponseEntity<RespuestaGenerica> getAllUsers(){
        RespuestaGenerica respuesta = usuarioService.getUsuarios();
        HttpStatus status = null;
        if (respuesta.isExito()) {
            status = HttpStatus.OK;
            respuesta.setCodigo(status.value());
        } else {
            status = HttpStatus.BAD_REQUEST;
            respuesta.setCodigo(status.value());
        }
        return new ResponseEntity<>(respuesta,status);
    }

    @PostMapping("/guardarUsuarios")
    public ResponseEntity<RespuestaGenerica> saveUser(@Valid @RequestBody UsuarioDtoPass dto){
        RespuestaGenerica respuesta = usuarioService.guardarUsuario(dto);
        HttpStatus status = null;
        if (respuesta.isExito()) {
            status = HttpStatus.OK;
            respuesta.setCodigo(status.value());
        } else {
            status = HttpStatus.BAD_REQUEST;
            respuesta.setCodigo(status.value());
        }
        return new ResponseEntity<>(respuesta,status);
    }

    @PutMapping("/actualizarUsuario")
    public ResponseEntity<RespuestaGenerica> updateUser(@Valid @RequestBody UsuarioDtoPass dto){
        RespuestaGenerica respuesta = usuarioService.actualizarUsuario(dto);
        HttpStatus status = null;
        if (respuesta.isExito()) {
            status = HttpStatus.OK;
            respuesta.setCodigo(status.value());
        } else {
            status = HttpStatus.BAD_REQUEST;
            respuesta.setCodigo(status.value());
        }
        return new ResponseEntity<>(respuesta,status);
    }

   @PostMapping("/crearTienda")
   public ResponseEntity<RespuestaGenerica> newStore(@Valid @RequestBody TiendaDto dto){
        RespuestaGenerica respuesta = tiendaService.crearTienda(dto);
        HttpStatus status = null;
        if (respuesta.isExito()) {
            status = HttpStatus.OK;
            respuesta.setCodigo(status.value());
        } else {
            status = HttpStatus.BAD_REQUEST;
            respuesta.setCodigo(status.value());
        }

        return new ResponseEntity<>(respuesta,status);
   }
    
}
