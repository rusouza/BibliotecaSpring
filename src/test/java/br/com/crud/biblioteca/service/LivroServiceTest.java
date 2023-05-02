package br.com.crud.biblioteca.service;

import br.com.crud.biblioteca.error.ResourceNotFoundException;
import br.com.crud.biblioteca.model.Livro;
import br.com.crud.biblioteca.repository.LivroRepository;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class LivroServiceTest {

    public static final Long ID = 3L;
    public static final Integer INDEX = 0;
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
    void whenFindAllThenReturnAListOfLivros() {
        when(livroRepository.findAll()).thenReturn(listaLivros);

        List<Livro> response = service.findAll();
        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(Livro.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(TITULO, response.get(INDEX).getTitulo());
        assertEquals(AUTOR, response.get(INDEX).getAutor());
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

        when(livroRepository.findByTituloIgnoreCaseContaining(Mockito.anyString())).thenThrow(new ResourceNotFoundException(NAO_ENCONTRADO));
        try{
            service.findByTitulo(TITULO);
        } catch(Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(NAO_ENCONTRADO, ex.getMessage());
        }
    }

    @Test
    void whenInsertThenReturnSucess() {
        when(livroRepository.save(Mockito.any())).thenReturn(livro);

        Livro response = service.insert(livro);

        assertNotNull(response);
        assertEquals(Livro.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(TITULO, response.getTitulo());
        assertEquals(AUTOR, response.getAutor());
    }

    @Test
    void whenDeleteThenReturnSucess() {
        when(livroRepository.findById(Mockito.anyLong())).thenReturn(optionalLivro);
        Mockito.doNothing().when(livroRepository).delete(livro);
        service.delete(ID);
        Mockito.verify(livroRepository, times(1)).delete(livro);
    }

    @Test
    void whenDeleteWithResourceNotFound() {
        when(livroRepository.findById(Mockito.anyLong())).thenThrow(new ResourceNotFoundException(NAO_ENCONTRADO));

        try{
            service.delete(ID);
        } catch(Exception ex) {
            assertEquals(ResourceNotFoundException.class, ex.getClass());
            assertEquals(NAO_ENCONTRADO, ex.getMessage());
        }
    }

    private void startLivro(){
        livro = new Livro(ID, TITULO, AUTOR);
        optionalLivro = Optional.of(new Livro(ID, TITULO, AUTOR));
        listaLivros = new ArrayList<Livro>();
        listaLivros.add(new Livro(ID, TITULO, AUTOR));
    }
}