package br.com.crud.biblioteca.service;

import br.com.crud.biblioteca.error.ResourceNotFoundException;
import br.com.crud.biblioteca.model.Livro;
import br.com.crud.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public Livro findById(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        return livro.orElseThrow(ResourceNotFoundException::new);
    }

    public List<Livro> findByTitulo(String titulo) {
        List<Livro> livros = livroRepository.findByTituloIgnoreCaseContaining(titulo);
        if(!livros.isEmpty())
            return livros;
        throw new ResourceNotFoundException("Nenhum Livro com esse nome foi encontrado!");
    }

    public Livro insert(Livro livro){
        return livroRepository.save(livro);
    }

    public void delete(Long id){
        Optional<Livro> livro = livroRepository.findById(id);
        if(livro.isPresent())
            livroRepository.delete(livro.get());
        else
            throw new ResourceNotFoundException();
    }
}
