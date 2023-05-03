package br.com.crud.biblioteca.service;

import br.com.crud.biblioteca.error.ResourceNotFoundException;
import br.com.crud.biblioteca.model.Usuario;
import br.com.crud.biblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class UsuarioServiceTest {

    public static final Long ID = 1L;
    public static final String LOGIN = "admin";
    public static final String NOME = "Administrador";
    public static final String SENHA = "admin";
    public static final boolean IS_ADMIN = true;
    public static final String NOME_UTILIZADO = "Nome de login já utilizado!";

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
    void whenFindByIdThenReturnAUserInstance() {
        when(repository.findById(anyLong())).thenReturn(optionalUser);

        Optional<Usuario> response = repository.findById(ID);

        assertTrue(response.isPresent());
        assertEquals(ID, response.get().getId());
        assertEquals(LOGIN, response.get().getLogin());
        assertEquals(SENHA, response.get().getSenha());
        assertEquals(NOME, response.get().getNome());
        assertEquals(IS_ADMIN, response.get().isAdmin());
    }

    @Test
    void whenLoginExistThenReturnResourceNotFoundException() {

        when(repository.findById(anyLong())).thenThrow(new ResourceNotFoundException(NOME_UTILIZADO));

        try {
            repository.findById(ID);
        } catch(Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
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