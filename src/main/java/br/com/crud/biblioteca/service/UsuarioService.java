package br.com.crud.biblioteca.service;

import br.com.crud.biblioteca.error.ResourceNotFoundException;
import br.com.crud.biblioteca.model.Usuario;
import br.com.crud.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario findByid(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(ResourceNotFoundException::new);
    }

    public boolean isLoginExist(String login) {
        Usuario usuario = usuarioRepository.findByLoginIgnoreCaseContaining(login);
        return usuario != null;
    }

    public Usuario insert(Usuario usuario){
        if(!isLoginExist(usuario.getLogin()))
            return usuarioRepository.save(usuario);
        else
            throw new ResourceNotFoundException("Nome de login já utilizado!");
    }
}
