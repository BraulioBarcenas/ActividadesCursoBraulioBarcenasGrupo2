package com.braulio.tienda.data.dto;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class PedidoDtoTest {
    
    private Validator validator;

    @BeforeEach
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validPedidoDtoTest(){
        PedidoDto pedidoDto = new PedidoDto();

        pedidoDto.setIdPedidos(1);
        pedidoDto.setFecha(new Date());
        pedidoDto.setTotal(2000.2);
        pedidoDto.setIva(pedidoDto.getTotal()*0.16);
        pedidoDto.setUsuario(2);
        pedidoDto.setPlataforma("Paypal");
        pedidoDto.setNumCuenta("34567891293");
        pedidoDto.setCalle("Mango");
        pedidoDto.setColonia("Frutas");
        pedidoDto.setEstado("Sonora");
        pedidoDto.setCiudad("Juarez");
        pedidoDto.setNumCasa(234);

        Set<ConstraintViolation<PedidoDto>> violations = validator.validate(pedidoDto);
        
        assertTrue(violations.isEmpty());
    }


    @Test
    public void invalidPedidoDtoTest(){
        PedidoDto pedidoDto = new PedidoDto();

        Set<ConstraintViolation<PedidoDto>> violations = validator.validate(pedidoDto);
        
        assertEquals(8,violations.size());  
    }
}
