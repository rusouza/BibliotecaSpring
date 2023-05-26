package br.com.crud.biblioteca.controller;

import br.com.crud.biblioteca.dto.EmprestimoDTO;
import br.com.crud.biblioteca.error.ResourceNotFoundException;
import br.com.crud.biblioteca.model.Emprestimo;
import br.com.crud.biblioteca.model.Livro;
import br.com.crud.biblioteca.service.EmprestimoService;
import br.com.crud.biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Emprestimo>> getAll() {
        return new ResponseEntity<>(emprestimoService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "admin/emprestimo/findByUsuario/{userName}")
    public ResponseEntity<List<Emprestimo>> getEmprestimoByUsuario(@PathVariable(name = "userName") String userName) {
        return new ResponseEntity<>(emprestimoService.findByUserName(userName), HttpStatus.OK);
    }

    @GetMapping(path = "admin/emprestimo/findByDataDevolucao/{userName}")
    public ResponseEntity<List<Emprestimo>> getAllEmprestimoNaoDevolvido(@PathVariable(name = "userName") String userName) {
        return new ResponseEntity<>(emprestimoService.findEmprestimoNaoDevolvido(userName), HttpStatus.OK);
    }

    @PostMapping(path = "admin/emprestimo/{livroId}/emprestar")
    public ResponseEntity<Emprestimo> emprestarLivro(@PathVariable(name = "livroId") Long id,
                                                @RequestBody EmprestimoDTO dto) {

        Emprestimo emprestimo = dto.converterParaEmprestimo();
        Livro livro = livroService.findById(id);
        emprestimo.setLivro(livro);

        return new ResponseEntity<>(emprestimoService.emprestarLivro(emprestimo), HttpStatus.OK);
    }

    @PutMapping(path = "admin/emprestimo/{livroId}/devolver")
    public ResponseEntity<Emprestimo> devolverLivro(@PathVariable(name = "livroId") Long livroId,
                                           @RequestBody EmprestimoDTO dto) {

        Emprestimo emprestimo = dto.converterParaEmprestimo();
        Optional<Livro> livro = Optional.ofNullable(livroService.findById(livroId));
        if(livro.isPresent()) {
            emprestimo.setLivro(livro.get());
            return new ResponseEntity<>(emprestimoService.devolverLivro(emprestimo), HttpStatus.OK);
        }
        throw new ResourceNotFoundException();
    }
}