package com.braulio.tienda.data.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.braulio.tienda.data.Carrito;
import com.braulio.tienda.data.Usuario;

public class DetalleCarritoDtoTest {
    

    @Test
    public void validDetalleCarritoDtoTest(){
        DetalleCarritoDto detalleCarritoDto = new DetalleCarritoDto();
        Carrito carrito = new Carrito();
        Usuario usuario = new Usuario();

        usuario.setIdUsuario(1);
        usuario.setNombre("Juan");
        usuario.setApPat("Perez");
        usuario.setEmail("Juan@mail.com");
        usuario.setPassword("123");

        carrito.setIdCarrito(1);
        carrito.setUsuario(usuario);

        detalleCarritoDto.setCarrito(carrito);
        detalleCarritoDto.setProductos(2);

        
        assertNotNull(detalleCarritoDto.getProductos());
        assertSame(usuario, carrito.getUsuario());
        assertSame(carrito, detalleCarritoDto.getCarrito());
    }
}
