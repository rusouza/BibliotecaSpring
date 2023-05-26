package br.com.crud.biblioteca.service;

import br.com.crud.biblioteca.model.Usuario;
import br.com.crud.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.repository = usuarioRepository;
    }

    private void isLoginExist(Usuario usuario) {
        Optional<Usuario> user = repository.findByLoginIgnoreCaseContaining(usuario.getLogin());
        if(user.isPresent() && user.get().getLogin().equals(usuario.getLogin()))
            throw new DataIntegrityViolationException("Nome de login já utilizado!");
    }

    public Usuario insert(Usuario usuario){
        isLoginExist(usuario);
        return repository.save(usuario);
    }
}
