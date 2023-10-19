package com.braulio.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.ProductoDto;
import com.braulio.tienda.data.dto.ProductoDtoAddStock;
import com.braulio.tienda.data.dto.RespuestaGenerica;
import com.braulio.tienda.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
@Validated
public class ProductoController {
 
    @Autowired
    private ProductoService productoService;

    @PostMapping("/guardarProducto")
    public ResponseEntity<RespuestaGenerica> saveProduct(@Valid @RequestBody ProductoDto dto){
        RespuestaGenerica respuesta = productoService.nuevoProducto(dto);
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

    @PostMapping("/addStock")
    public ResponseEntity<RespuestaGenerica> addStock(@Valid @RequestBody ProductoDtoAddStock dto){
        RespuestaGenerica respuesta = productoService.agregarStock(dto);
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
