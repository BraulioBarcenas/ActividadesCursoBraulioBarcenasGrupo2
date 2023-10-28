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

public class ProductoDtoTest {
    private Validator validator;

    @BeforeEach
    public void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validProductoDtoTest(){
        ProductoDto productoDto = new ProductoDto();

        productoDto.setNombre("Shampoo");
        productoDto.setDescripcion("Shampooo anti caida");
        productoDto.setPrecio(20);
        productoDto.setStock(3);
        productoDto.setMarca("Head n shoulders");
        productoDto.setCategoria("Higiene");
        productoDto.setImg("c:/aaaa");
        productoDto.setTienda(2);
        
        Set<ConstraintViolation<ProductoDto>> violations = validator.validate(productoDto);
        
        assertTrue(violations.isEmpty());
    }
    
    @Test
    public void validProductoDtoAddStockTest(){
        ProductoDtoAddStock productoDtoAddStock = new ProductoDtoAddStock();
        
        productoDtoAddStock.setIdProducto(2);
        productoDtoAddStock.setStock(10);



        Set<ConstraintViolation<ProductoDtoAddStock>> violations = validator.validate(productoDtoAddStock);
        
        assertTrue(violations.isEmpty());
        
    }

    @Test
    public void invalidProductoDtoTest(){
        
        ProductoDto productoDto = new ProductoDto();

        Set<ConstraintViolation<ProductoDto>> violations = validator.validate(productoDto);
        
        assertEquals(6,violations.size());    
    }
    
    
    @Test
    public void invalidProductoDtoAddStock(){
        ProductoDtoAddStock productoDtoAddStock = new ProductoDtoAddStock();
        
        Set<ConstraintViolation<ProductoDtoAddStock>> violations = validator.validate(productoDtoAddStock);
        
        assertEquals(2,violations.size());    

    }
}
