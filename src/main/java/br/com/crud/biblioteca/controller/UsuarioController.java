package br.com.crud.biblioteca.controller;

import br.com.crud.biblioteca.model.Usuario;
import br.com.crud.biblioteca.service.UsuarioService;
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
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario) {
        usuario.setSenha(encriptarSenha(usuario.getSenha()));
        return new ResponseEntity<>(usuarioService.insert(usuario), HttpStatus.OK);
    }

    private String encriptarSenha(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
}