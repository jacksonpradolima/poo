package io.github.jacksonpradolima;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Classe de teste para a classe PagamentoService.
 * <p>
 * Esta classe utiliza mocks e stubs para testar os diferentes cenários de uso
 * da classe PagamentoService, incluindo casos de sucesso e falha.
 */
public class PagamentoServiceTest {

    private ServicoExterno mockServico;
    private ServicoExterno stubServico;
    private PagamentoService pagamentoService;

    /**
     * Configuração inicial antes de cada teste.
     * <p>
     * Este método inicializa os mocks, stubs e a instância da classe principal
     * para garantir que cada teste seja executado em um ambiente limpo.
     */
    @BeforeEach
    void setUp() {
        // Inicializando mock
        mockServico = mock(ServicoExterno.class);

        // Inicializando stub
        stubServico = new ServicoExterno() {
            @Override
            public String realizarPagamento(double valor) {
                return "Pagamento simulado com sucesso";
            }
        };

        // Inicializando a classe principal
        pagamentoService = new PagamentoService(mockServico);
    }

    /**
     * Limpeza após cada teste.
     * <p>
     * Este método garante que os recursos utilizados durante os testes sejam
     * liberados e que o ambiente seja restaurado para o estado inicial.
     */
    @AfterEach
    void tearDown() {
        mockServico = null;
        stubServico = null;
        pagamentoService = null;
    }

    /**
     * Testa o método processarPagamento utilizando um mock do serviço externo.
     * <p>
     * Este teste verifica se o método processarPagamento delega corretamente
     * a execução ao serviço externo e retorna o resultado esperado.
     */
    @Test
    void testProcessarPagamentoComMock() {
        // Definindo comportamento
        when(mockServico.realizarPagamento(100.0)).thenReturn("Pagamento realizado com sucesso");

        // Testando a classe principal
        String resultado = pagamentoService.processarPagamento(100.0);

        // Verificando interações
        verify(mockServico).realizarPagamento(100.0);

        // Asserções
        assertEquals("Pagamento realizado com sucesso", resultado);
    }

    /**
     * Testa o método processarPagamento utilizando um stub do serviço externo.
     * <p>
     * Este teste verifica se o método processarPagamento retorna o resultado
     * esperado ao utilizar um stub do serviço externo.
     */
    @Test
    void testProcessarPagamentoComStub() {
        // Substituindo o serviço externo por um stub
        PagamentoService pagamentoServiceComStub = new PagamentoService(stubServico);
        String resultado = pagamentoServiceComStub.processarPagamento(50.0);

        // Asserções
        assertEquals("Pagamento simulado com sucesso", resultado);
    }

    /**
     * Testa o método processarPagamento para valores inválidos.
     * <p>
     * Este teste verifica se o método processarPagamento lança uma exceção
     * quando o valor fornecido é menor ou igual a zero.
     */
    @Test
    void testProcessarPagamentoComValorInvalido() {
        // Verificando se a exceção é lançada
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            pagamentoService.processarPagamento(0.0);
        });

        // Asserções
        assertEquals("Valor inválido", exception.getMessage());
    }
}
