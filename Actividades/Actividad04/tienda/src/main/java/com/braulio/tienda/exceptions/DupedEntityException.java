package com.braulio.tienda.exceptions;

public class DupedEntityException extends RuntimeException{
    public DupedEntityException(String message){
        super(message);
    }
}
