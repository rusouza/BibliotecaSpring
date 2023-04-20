package br.com.crud.Biblioteca.service;

import br.com.crud.Biblioteca.error.ResourceNotFoundException;
import br.com.crud.Biblioteca.model.Emprestimo;
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

    public List<Emprestimo> findByUserName(String userName) {
        List<Emprestimo> listEmprestimos = emprestimoRepository.findByLoginIgnoreCaseContaining(userName);
        if(listEmprestimos.size() == 0)
            throw new ResourceNotFoundException();
        return listEmprestimos;
    }

    public List<Emprestimo> findByDataEntrega(String nome, boolean isDevolvido) {
        List<Emprestimo> listEmprestimos = emprestimoRepository.findByLoginAndIsDevolvido(nome,isDevolvido);
        if(listEmprestimos.size() == 0)
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
