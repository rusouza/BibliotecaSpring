package br.com.crud.biblioteca.service;

import br.com.crud.biblioteca.model.Emprestimo;
import br.com.crud.biblioteca.model.Livro;
import br.com.crud.biblioteca.repository.EmprestimoRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmprestimoServiceTest {

    public static final Integer INDEX = 0;
    public static final Long ID = 1L;
    public static final String LOGIN = "admin";
    public static final String DATA_EMPRESTIMO = "2023/01/01";
    public static final String DATA_DEVOLUCAO = "2023/02/01";
    public static final boolean IS_DEVOLVIDO = false;

    public static final Long ID_LIVRO = 3L;
    public static final String TITULO = "Neuromancer";
    public static final String AUTOR = "William Gibson";
    public static final String NAO_ENCONTRADO = "Não Encontrado";

    @Mock
    private EmprestimoRepository repository;
    private Emprestimo emprestimo;
    private Livro livro;
    private List<Emprestimo> listaEmprestimo;
    private Optional<Emprestimo>  optionalEmprestimo;

    @InjectMocks
    private EmprestimoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        startEmprestimo();
    }

    @Test
    void whenFindAllThenReturnAlistOfEmprestimos(){
        when(repository.findAll()).thenReturn(listaEmprestimo);

        List<Emprestimo> response = repository.findAll();

        assertNull(response);
        assertEquals(1, response.size());
        assertEquals(Emprestimo.class, response.get(INDEX).getClass());
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(LOGIN, response.get(INDEX).getLogin());
        assertEquals(DATA_EMPRESTIMO, response.get(INDEX).getDataEmprestimo().toString());
    }


    private void startEmprestimo(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        livro = new Livro(ID_LIVRO, TITULO, AUTOR);
        emprestimo = new Emprestimo(ID, LOGIN,
                LocalDate.parse(DATA_EMPRESTIMO, formatter), LocalDate.parse(DATA_DEVOLUCAO, formatter),
                IS_DEVOLVIDO, livro);
        optionalEmprestimo = Optional.of(emprestimo);
        listaEmprestimo = new ArrayList<Emprestimo>();
        listaEmprestimo.add(emprestimo);
    }
}