package com.braulio.tienda.data.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class CarritoDtoTest {
    private Validator validator;

    @BeforeEach
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }    


    @Test
    public void validCarritoDtoTest(){
        CarritoDto carritoDto = new CarritoDto();

        carritoDto.setIdCarrito(1);
        carritoDto.setProducto(2);
        carritoDto.setUsuario(2);
        carritoDto.setStock(10);

        Set<ConstraintViolation<CarritoDto>> violations = validator.validate(carritoDto);
        assertTrue(violations.isEmpty());        
    }
    
    @Test
    public void invalidCarritoDtoTest(){
        CarritoDto carritoDto = new CarritoDto();
        
        Set<ConstraintViolation<CarritoDto>> violations = validator.validate(carritoDto);
        assertEquals(3,violations.size());
    }
}
