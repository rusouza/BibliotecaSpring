package br.com.crud.Biblioteca.repository;

import br.com.crud.Biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String nome);
    Usuario findByLoginIgnoreCaseContaining(String nome);
}