package br.com.crud.Biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String login;

    @NotNull
    @Column(name = "dataEmprestimo")
    private String dataEmprestimo;

    @NotNull
    private String dataEntrega;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "emprestimo")
    private List<Livro> livros;

    public Emprestimo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprestimo that = (Emprestimo) o;
        return Objects.equals(id, that.id) && Objects.equals(login, that.login) && Objects.equals(dataEmprestimo, that.dataEmprestimo) && Objects.equals(dataEntrega, that.dataEntrega) && Objects.equals(livros, that.livros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, dataEmprestimo, dataEntrega, livros);
    }
}
