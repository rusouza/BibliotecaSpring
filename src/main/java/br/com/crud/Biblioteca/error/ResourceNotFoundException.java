package br.com.crud.Biblioteca.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Não Encontrado");
    }

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}
