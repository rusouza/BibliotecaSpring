package br.com.crud.Biblioteca.service;

import br.com.crud.Biblioteca.error.ResourceNotFoundException;
import br.com.crud.Biblioteca.model.Livro;
import br.com.crud.Biblioteca.model.Usuario;
import br.com.crud.Biblioteca.repository.UsuarioRepository;
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
        return usuario.orElseThrow( () -> new ResourceNotFoundException());
    }

    public boolean isLoginExist(String login) {
        Usuario usuario = usuarioRepository.findByLoginIgnoreCaseContaining(login);
        if(usuario == null)
            return false;
        else
            return true;
    }

    public Usuario insert(Usuario usuario){
        if(!isLoginExist(usuario.getLogin()))
            return usuarioRepository.save(usuario);
        else
            throw new ResourceNotFoundException("Nome de login já utilizado!");
    }
}
