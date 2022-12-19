package br.com.crud.Biblioteca.service;

import br.com.crud.Biblioteca.error.ResourceNotFoundException;
import br.com.crud.Biblioteca.model.Livro;
import br.com.crud.Biblioteca.repository.LivroRepository;
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
        return livro.orElseThrow( () -> new ResourceNotFoundException());
    }

    public Livro findByTitulo(String titulo) {
        Optional<Livro> livro = livroRepository.findByTituloIgnoreCaseContaining(titulo);
        return livro.orElseThrow( () -> new ResourceNotFoundException());
    }

    public Livro insert(Livro livro){
        return livroRepository.save(livro);
    }

    public void delete(Long id){
        Optional<Livro> livro = Optional.ofNullable(livroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException()));
        livroRepository.delete(livro.get());
    }
}
