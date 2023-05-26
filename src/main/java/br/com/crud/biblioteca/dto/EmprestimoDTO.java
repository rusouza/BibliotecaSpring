package br.com.crud.biblioteca.dto;

import br.com.crud.biblioteca.model.Emprestimo;
import br.com.crud.biblioteca.model.Livro;

import java.time.LocalDate;

public class EmprestimoDTO {

    private String login;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean isDevolvido;
    private Livro livro;

    public Emprestimo converterParaEmprestimo(){
        return new Emprestimo(login, dataEmprestimo, dataDevolucao, isDevolvido, livro);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean isDevolvido() {
        return isDevolvido;
    }

    public void setDevolvido(boolean devolvido) {
        isDevolvido = devolvido;
    }
}
