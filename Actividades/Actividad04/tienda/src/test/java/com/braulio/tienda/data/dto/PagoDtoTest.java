package com.braulio.tienda.data.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class PagoDtoTest {
    private Validator validator;
    
    @BeforeEach
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testParametrizedConstructor(){
        PagoDto pagoDto = new PagoDto("Paypal", new Date(), 234, "41151597");

        assertNull(pagoDto.getIdPagos());
        assertNotNull(pagoDto.getFecha());
        assertNotNull(pagoDto.getCargo());

        Set<ConstraintViolation<PagoDto>> violations = validator.validate(pagoDto);
        
        assertTrue(violations.isEmpty());
    }
}
