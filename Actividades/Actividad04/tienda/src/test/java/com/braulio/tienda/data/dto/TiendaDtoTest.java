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

public class TiendaDtoTest {
    private Validator validator;

    @BeforeEach
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validTiendaDtoTest(){
        TiendaDto tiendaDto = new TiendaDto();

        tiendaDto.setIdTienda(1);
        tiendaDto.setNombre("Wallmart");
        tiendaDto.setDescripcion("Pared comercial");
        tiendaDto.setUsuario(2);

        Set<ConstraintViolation<TiendaDto>> violations = validator.validate(tiendaDto);
        
        assertTrue(violations.isEmpty());
    }
    
    
    @Test 
    public void invalidTiendaDtoTest(){
        TiendaDto tiendaDto = new TiendaDto();
        
        
        Set<ConstraintViolation<TiendaDto>> violations = validator.validate(tiendaDto);
        
        assertEquals(3,violations.size());
    }
}
