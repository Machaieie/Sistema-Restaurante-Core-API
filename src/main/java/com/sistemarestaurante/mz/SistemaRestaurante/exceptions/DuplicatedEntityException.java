package com.sistemarestaurante.mz.SistemaRestaurante.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT)
public class DuplicatedEntityException extends Exception{

    public DuplicatedEntityException(String message){
        super(message);
    }
}
