package br.com.crud.biblioteca.dto;

import br.com.crud.biblioteca.model.Livro;
import lombok.Data;

@Data
public class LivroDTO {

    private String titulo;
    private String autor;

    public Livro converterParaLivro(){
        return new Livro(titulo, autor);
    }

}
