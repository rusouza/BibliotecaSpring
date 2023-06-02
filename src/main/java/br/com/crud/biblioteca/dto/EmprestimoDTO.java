package br.com.crud.biblioteca.dto;

import br.com.crud.biblioteca.model.Emprestimo;
import br.com.crud.biblioteca.model.Livro;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmprestimoDTO {

    private String login;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean isDevolvido;
    private Livro livro;

    public Emprestimo converterParaEmprestimo(){
        return new Emprestimo(login, dataEmprestimo, dataDevolucao, isDevolvido, livro);
    }

}
