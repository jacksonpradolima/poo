package io.github.jacksonpradolima;

/**
 * Classe responsável por processar pagamentos utilizando um serviço externo.
 * <p>
 * Esta classe encapsula a lógica de validação do valor do pagamento e delega
 * a execução do pagamento ao serviço externo fornecido.
 */
public class PagamentoService {
    /**
     * Instância do serviço externo utilizado para realizar pagamentos.
     */
    private final ServicoExterno servicoExterno;

    /**
     * Construtor da classe PagamentoService.
     *
     * @param servicoExterno Instância do serviço externo que será utilizada para realizar pagamentos.
     */
    public PagamentoService(ServicoExterno servicoExterno) {
        this.servicoExterno = servicoExterno;
    }

    /**
     * Processa o pagamento de um valor específico.
     *
     * @param valor Valor do pagamento a ser processado. Deve ser maior que zero.
     * @return Uma string representando o resultado do pagamento, conforme retornado pelo serviço externo.
     * @throws IllegalArgumentException Se o valor fornecido for menor ou igual a zero.
     */
    public String processarPagamento(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        return servicoExterno.realizarPagamento(valor);
    }
}

/**
 * Interface que define o contrato para serviços externos de pagamento.
 * <p>
 * Implementações desta interface devem fornecer a lógica para realizar pagamentos.
 */
interface ServicoExterno {
    /**
     * Realiza o pagamento de um valor específico.
     *
     * @param valor Valor do pagamento a ser realizado.
     * @return Uma string representando o resultado do pagamento.
     */
    String realizarPagamento(double valor);
}
