package br.com.crud.Biblioteca.controller;

import br.com.crud.Biblioteca.model.Livro;
import br.com.crud.Biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping(path = "livros")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(livroService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "livros/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(livroService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "livros/findByTitulo/{nome}")
    public ResponseEntity<?> findEstudanteByTitulo(@PathVariable String nome){
        return new ResponseEntity<>(livroService.findByTitulo(nome), HttpStatus.OK);
    }

    @PostMapping(path = "admin/livros")
    public ResponseEntity<?> insert(@RequestBody Livro livro) {
        return new ResponseEntity<>(livroService.insert(livro), HttpStatus.OK);
    }

    @DeleteMapping(path = "admin/livros/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        livroService.delete(id);
        return new ResponseEntity<>(ResponseEntity.ok().build(), HttpStatus.OK);
    }
}