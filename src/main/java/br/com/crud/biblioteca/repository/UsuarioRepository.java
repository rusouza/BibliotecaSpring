package br.com.crud.biblioteca.repository;

import br.com.crud.biblioteca.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String nome);
    Optional<Usuario> findByLoginIgnoreCaseContaining(String nome);
}