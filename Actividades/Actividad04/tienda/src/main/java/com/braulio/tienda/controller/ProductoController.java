package com.braulio.tienda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.braulio.tienda.data.dto.ProductoDto;
import com.braulio.tienda.service.ProductoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productos")
public class ProductoController {
 
    @Autowired
    private ProductoService productoService;

    @PostMapping("/guardarProducto")
    public ProductoDto saveProduct(@Valid @RequestBody ProductoDto dto){
        return productoService.nuevoProducto(dto);
    }
}
