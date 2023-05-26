package br.com.crud.biblioteca.dto;

import br.com.crud.biblioteca.model.Livro;

public class LivroDTO {

    private String titulo;
    private String autor;

    public Livro converterParaLivro(){
        return new Livro(titulo, autor);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
