package br.com.crud.Biblioteca.repository;

import br.com.crud.Biblioteca.model.Emprestimo;
import br.com.crud.Biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
