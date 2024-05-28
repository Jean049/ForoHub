package com.allura.forohub.errores;

import org.springframework.validation.FieldError;

public record ErrorValidacionDTO(
        String campo,
        String error
) {
    public ErrorValidacionDTO(FieldError e){
        this(e.getField(), e.getDefaultMessage());
    }
}
