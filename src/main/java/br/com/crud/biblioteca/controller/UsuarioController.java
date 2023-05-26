package br.com.crud.biblioteca.controller;

import br.com.crud.biblioteca.dto.UsuarioDTO;
import br.com.crud.biblioteca.model.Usuario;
import br.com.crud.biblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping(path = "cadastro")
    public ResponseEntity<Usuario> insert(@RequestBody UsuarioDTO userDto) {
        Usuario user = userDto.converterParaUsuario();
        user.setSenha(encriptarSenha(user.getSenha()));
        return new ResponseEntity<>(service.insert(user), HttpStatus.OK);
    }

    private String encriptarSenha(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }
}