package br.com.crud.biblioteca.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Não Encontrado");
    }

    public ResourceNotFoundException(String mensagem) {
        super(mensagem);
    }
}
