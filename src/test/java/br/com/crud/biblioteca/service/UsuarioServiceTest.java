package br.com.crud.biblioteca.service;

import br.com.crud.biblioteca.model.Usuario;
import br.com.crud.biblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioServiceTest {

    public static final Long ID = 1L;
    public static final String LOGIN = "admin";
    public static final String NOME = "Administrador";
    public static final String SENHA = "admin";
    public static final boolean IS_ADMIN = true;
    public static final String NOME_UTILIZADO = "Nome de login já utilizado!";
    public static final String NAO_ENCONTRADO = "Não Encontrado";

    @InjectMocks
    private UsuarioService service;

    @Mock
    private UsuarioRepository repository;
    private Usuario user;
    private Optional<Usuario> optionalUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        startUser();
    }

    @Test
    void whenFindByLoginThenReturnResourceException(){
        when(repository.findByLoginIgnoreCaseContaining(anyString())).thenThrow(new DataIntegrityViolationException(NOME_UTILIZADO));
        try {
            repository.findByLoginIgnoreCaseContaining(LOGIN);

        } catch(Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(NOME_UTILIZADO, ex.getMessage());
        }
    }

    @Test
    void whenInsertThenReturnException() {

        when(repository.save(any())).thenThrow(new DataIntegrityViolationException(NOME_UTILIZADO));

        try {
            repository.save(user);
        } catch(Exception ex) {
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(NOME_UTILIZADO, ex.getMessage());
        }
    }

    @Test
    void whenInsertThenReturnSucess() {
        when(repository.save(any())).thenReturn(user);

        Usuario response = service.insert(user);

        assertNotNull(response);
        assertEquals(ID, response.getId());
        assertEquals(LOGIN, response.getLogin());
        assertEquals(SENHA, response.getSenha());
        assertEquals(NOME, response.getNome());
        assertEquals(IS_ADMIN, response.isAdmin());
    }

    private void startUser(){
        user = new Usuario(ID, LOGIN, SENHA, NOME, IS_ADMIN);
        optionalUser = Optional.of(new Usuario(ID, LOGIN, SENHA, NOME, IS_ADMIN));
    }
}