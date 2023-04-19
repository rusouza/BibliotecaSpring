package br.com.crud.Biblioteca.controller;

import br.com.crud.Biblioteca.error.ResourceNotFoundException;
import br.com.crud.Biblioteca.model.Emprestimo;
import br.com.crud.Biblioteca.model.Livro;
import br.com.crud.Biblioteca.service.EmprestimoService;
import br.com.crud.Biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private LivroService livroService;

    @GetMapping(path = "admin/emprestimo")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(emprestimoService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "admin/emprestimo/{livroId}/emprestar")
    public ResponseEntity<?> emprestarLivro(@PathVariable(name = "livroId") Long id,
                                                @RequestBody Emprestimo emprestimo) {
        Livro livro = livroService.findById(id);
        List<Livro> listLivro = new ArrayList<>();
        listLivro.add(livro);
        emprestimo.setLivros(listLivro);
        return new ResponseEntity<>(emprestimoService.emprestarLivro(emprestimo), HttpStatus.OK);
    }

    @PutMapping(path = "admin/emprestimo/{livroId}/devolver/{id}")
    public ResponseEntity<?> devolverLivro(@PathVariable(name = "livroId") Long livroId,
                                           @PathVariable(name = "id") Long id,
                                           @RequestBody Emprestimo emprestimo) {

        Optional<Livro> livro = Optional.ofNullable(livroService.findById(livroId));
        livro.orElseThrow(() -> new ResourceNotFoundException());
        System.out.println(emprestimo.getLivros());

        return new ResponseEntity<>(emprestimoService.devolverLivro(emprestimo), HttpStatus.OK);
    }
}