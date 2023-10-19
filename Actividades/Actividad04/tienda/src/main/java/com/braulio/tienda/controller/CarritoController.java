package com.braulio.tienda.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.CarritoDto;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.exceptions.NullParamsException;
import com.braulio.tienda.service.CarritoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carrito")
@Validated
public class CarritoController {
    @Autowired
    private CarritoService carritoService;

    @PostMapping("/addCarrito")
    public ResponseEntity<RespuestaGenerica> addToCart(@Valid @RequestBody CarritoDto carritoDto){

        RespuestaGenerica respuesta = carritoService.agregarProductoACarrito(carritoDto);
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

    @GetMapping("/getCarrito")
    public ResponseEntity<RespuestaGenerica> getCart(Integer idUsuario){
        if (!( idUsuario instanceof Integer) || idUsuario == null) {
            throw new NullParamsException("Especifique un id de usuario");
        }

        RespuestaGenerica respuesta = carritoService.obtenerCarrito(idUsuario);
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
