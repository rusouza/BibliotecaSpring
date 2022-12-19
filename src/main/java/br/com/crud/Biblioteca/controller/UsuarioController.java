package br.com.crud.Biblioteca.controller;

import br.com.crud.Biblioteca.model.Livro;
import br.com.crud.Biblioteca.model.Usuario;
import br.com.crud.Biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(path = "cadastro")
    public ResponseEntity<?> insert(@RequestBody Usuario usuario) {
        usuario.setSenha(encriptarSenha(usuario.getSenha()));
        return new ResponseEntity<>(usuarioService.insert(usuario), HttpStatus.OK);
    }

    private String encriptarSenha(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
