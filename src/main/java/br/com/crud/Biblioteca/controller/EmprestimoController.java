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

    @GetMapping(path = "admin/emprestimo/findByUsuario/{userName}")
    public ResponseEntity<?> getEmprestimoByUsuario(@PathVariable(name = "userName") String userName) {
        return new ResponseEntity<>(emprestimoService.findByUserName(userName), HttpStatus.OK);
    }

    @GetMapping(path = "admin/emprestimo/findByDataDevolucao/{userName}")
    public ResponseEntity<?> getAllEmprestimoNaoDevolvido(@PathVariable(name = "userName") String userName) {
        return new ResponseEntity<>(emprestimoService.findByDataEntrega(userName, false), HttpStatus.OK);
    }

    @PostMapping(path = "admin/emprestimo/{livroId}/emprestar")
    public ResponseEntity<?> emprestarLivro(@PathVariable(name = "livroId") Long id,
                                                @RequestBody Emprestimo emprestimo) {

        Livro livro = livroService.findById(id);
        emprestimo.setLivro(livro);

        return new ResponseEntity<>(emprestimoService.emprestarLivro(emprestimo), HttpStatus.OK);
    }

    @PutMapping(path = "admin/emprestimo/{livroId}/devolver")
    public ResponseEntity<?> devolverLivro(@PathVariable(name = "livroId") Long livroId,
                                           @RequestBody Emprestimo emprestimo) {

        Optional<Livro> livro = Optional.ofNullable(livroService.findById(livroId));
        livro.orElseThrow(() -> new ResourceNotFoundException());
        emprestimo.setLivro(livro.get());

        return new ResponseEntity<>(emprestimoService.devolverLivro(emprestimo), HttpStatus.OK);
    }
}