package br.com.crud.Biblioteca.service;

import br.com.crud.Biblioteca.error.ResourceNotFoundException;
import br.com.crud.Biblioteca.model.Livro;
import br.com.crud.Biblioteca.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class LivroServiceTest {

    public static final Long ID = 3L;
    public static final String TITULO = "Neuromancer";
    public static final String AUTOR = "William Gibson";
    public static final String NAO_ENCONTRADO = "Não Encontrado";
    @InjectMocks
    private LivroService service;
    @Mock
    private LivroRepository livroRepository;
    private Livro livro;
    private List<Livro> listaLivros;
    private Optional<Livro>  optionalLivro;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        startLivro();
    }

    @Test
    void findAll() {
    }

    @Test
    void whenFindByIdThenReturnLivroInstance() {
        when(livroRepository.findById(Mockito.anyLong())).thenReturn(optionalLivro);
        Livro response = service.findById(ID);

        assertNotNull(response);
        assertEquals(Livro.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(TITULO, response.getTitulo());
        assertEquals(AUTOR, response.getAutor());
    }

    @Test
    void whenFindByTituloThenReturnResourceNotFoundException() {

        when(livroRepository.findById(Mockito.anyLong())).thenThrow(new ResourceNotFoundException(NAO_ENCONTRADO));

        try{
            service.findById(ID);
        } catch(Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void insert() {
    }

    @Test
    void delete() {
    }

    private void startLivro(){
        livro = new Livro(ID, TITULO, AUTOR);
        optionalLivro = Optional.of(new Livro(ID, TITULO, AUTOR));
        listaLivros = new ArrayList<Livro>();
        listaLivros.add(new Livro(ID, TITULO, AUTOR));
    }
}