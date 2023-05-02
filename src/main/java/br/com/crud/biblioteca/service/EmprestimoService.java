package br.com.crud.biblioteca.service;

import br.com.crud.biblioteca.error.ResourceNotFoundException;
import br.com.crud.biblioteca.model.Emprestimo;
import br.com.crud.biblioteca.repository.EmprestimoRepository;
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
        return emprestimo.orElseThrow(ResourceNotFoundException::new);
    }

    public List<Emprestimo> findByUserName(String userName) {
        List<Emprestimo> listEmprestimos = emprestimoRepository.findByLoginIgnoreCaseContaining(userName);
        if(listEmprestimos.isEmpty())
            throw new ResourceNotFoundException();
        return listEmprestimos;
    }

    public List<Emprestimo> findEmprestimoNaoDevolvido(String nome) {
        List<Emprestimo> listEmprestimos = emprestimoRepository.findByLoginAndIsDevolvido(nome,false);
        if(listEmprestimos.isEmpty())
            throw new ResourceNotFoundException();
        return listEmprestimos;
    }

    public Emprestimo emprestarLivro(Emprestimo emprestimo){
        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo devolverLivro(Emprestimo emprestimoLivro){
        return emprestimoRepository.save(emprestimoLivro);
    }
}
