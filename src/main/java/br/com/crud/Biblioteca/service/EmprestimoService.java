package br.com.crud.Biblioteca.service;

import br.com.crud.Biblioteca.error.ResourceNotFoundException;
import br.com.crud.Biblioteca.model.Emprestimo;
import br.com.crud.Biblioteca.model.Livro;
import br.com.crud.Biblioteca.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;

    @Autowired
    public EmprestimoService(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public Emprestimo findByid(Long id) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
        return emprestimo.orElseThrow( () -> new ResourceNotFoundException());
    }

    public Emprestimo emprestarLivro(Emprestimo emprestimo){
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo devolverLivro(Emprestimo emprestimoLivro){
        return emprestimoRepository.save(emprestimoLivro);
    }

    private void atualizarInfoEmprestimo(Emprestimo newEmp, Emprestimo oldEmp){
        newEmp.setDataEmprestimo(oldEmp.getDataEmprestimo());
        newEmp.setDataEntrega(oldEmp.getDataEntrega());
    }
}
