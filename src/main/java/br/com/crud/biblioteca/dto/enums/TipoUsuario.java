package br.com.crud.biblioteca.dto.enums;

public enum TipoUsuario {

    ADM("Administrador"),
    USER("Usuario Comum");

    private String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}