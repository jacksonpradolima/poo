# Aula: Ferramentas de Cobertura e Mocks

## Sumário
1. Introdução
2. Objetivos
3. Metodologia
4. Ferramentas de Cobertura
   - Introdução ao Jacoco
   - Configuração do Jacoco em um projeto Java
   - Interpretação de relatórios de cobertura
5. Mocks e Stubs
   - Definição e diferenças entre mocks e stubs
   - Por que usar mocks e stubs
   - Exemplos simples com Mockito
6. Estudos de Caso
7. Exercícios Práticos
8. Referências

## Introdução
Nesta aula, exploraremos duas ferramentas essenciais para testes de software: cobertura de testes e mocks. A cobertura de testes ajuda a medir a eficácia dos testes, enquanto os mocks e stubs permitem simular comportamentos de dependências externas, tornando os testes mais robustos e isolados.

## Objetivos
- Ensinar como configurar e interpretar relatórios de ferramentas de cobertura de testes.
- Apresentar o uso de mocks e stubs, explicando sua importância e fornecendo exemplos práticos.

## Metodologia
- Aula expositiva com demonstração prática.
- Exercícios práticos de configuração do Jacoco e criação de mocks com Mockito.
- Discussão sobre os benefícios e limitações do uso de mocks e stubs.

## Ferramentas de Cobertura
### Introdução ao Jacoco
JaCoCo é uma ferramenta popular para medir a cobertura de testes em projetos Java. Ele gera relatórios detalhados que ajudam os desenvolvedores a identificar áreas do código que não estão sendo testadas.

### Configuração do Jacoco em um Projeto Java
Para configurar o JaCoCo em um projeto Maven:
1. Adicione o plugin JaCoCo ao arquivo `pom.xml`:

#### Explicação do `pom.xml`
O arquivo `pom.xml` é o coração de um projeto Maven. Ele define as configurações do projeto, incluindo dependências, plugins e propriedades. Aqui está uma explicação detalhada dos elementos usados para configurar o JaCoCo:

- **`<plugin>`**: Define um plugin Maven que será usado no projeto. No caso do JaCoCo, ele é responsável por medir a cobertura de testes.
- **`<groupId>`**: Identifica o grupo ao qual o plugin pertence. Para o JaCoCo, o grupo é `org.jacoco`.
- **`<artifactId>`**: Identifica o nome do plugin. O nome do plugin JaCoCo é `jacoco-maven-plugin`.
- **`<version>`**: Especifica a versão do plugin. É importante usar uma versão estável e atual, como `0.8.13`.
- **`<executions>`**: Define as etapas em que o plugin será executado.
  - **`<execution>`**: Representa uma configuração específica de execução do plugin.
    - **`<goals>`**: Define as metas (ações) que o plugin deve realizar. Para o JaCoCo, usamos:
      - `prepare-agent`: Configura o agente do JaCoCo para monitorar a execução dos testes.
      - `report`: Gera o relatório de cobertura após os testes.
    - **`<phase>`**: Define em qual fase do ciclo de vida do Maven o plugin será executado. Para o relatório do JaCoCo, usamos `prepare-package`.

#### Exemplo de Configuração
```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.13</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>prepare-package</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

2. Execute os testes e gere o relatório:
```bash
mvn clean test jacoco:report
```
3. Acesse o relatório gerado na pasta `target/site/jacoco/index.html`.

### Interpretação de Relatórios de Cobertura
Os relatórios do JaCoCo incluem:
- **Line Coverage**: Percentual de linhas de código executadas.
- **Branch Coverage**: Percentual de caminhos de decisão cobertos.
- **Métodos e Classes**: Cobertura por método e classe.

#### Exemplo de Relatório
Imagine que você tem o seguinte código na classe `PagamentoService`:
```java
public String processarPagamento(double valor) {
    if (valor <= 0) {
        throw new IllegalArgumentException("Valor inválido");
    }
    return servicoExterno.realizarPagamento(valor);
}
```
E os seguintes testes na classe `PagamentoServiceTest`:
```java
@Test
void testProcessarPagamentoComMock() {
    ServicoExterno mockServico = mock(ServicoExterno.class);
    when(mockServico.realizarPagamento(100.0)).thenReturn("Pagamento realizado com sucesso");

    PagamentoService pagamentoService = new PagamentoService(mockServico);
    String resultado = pagamentoService.processarPagamento(100.0);

    verify(mockServico).realizarPagamento(100.0);
    assertEquals("Pagamento realizado com sucesso", resultado);
}

@Test
void testProcessarPagamentoComValorInvalido() {
    ServicoExterno mockServico = mock(ServicoExterno.class);
    PagamentoService pagamentoService = new PagamentoService(mockServico);

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
        pagamentoService.processarPagamento(0.0);
    });

    assertEquals("Valor inválido", exception.getMessage());
}
```
O relatório do JaCoCo mostrará quais linhas e ramificações foram cobertas pelos testes. Por exemplo:
- **Linha 1**: `if (valor <= 0)` - Coberta pelo teste `testProcessarPagamentoComValorInvalido`.
- **Linha 2**: `throw new IllegalArgumentException("Valor inválido")` - Coberta pelo teste `testProcessarPagamentoComValorInvalido`.
- **Linha 3**: `return servicoExterno.realizarPagamento(valor)` - Coberta pelo teste `testProcessarPagamentoComMock`.

## Mocks e Stubs

Mocks e stubs são ferramentas poderosas para simular dependências externas em testes de software. Elas ajudam a isolar o código em teste, garantindo que ele funcione corretamente sem depender de serviços externos ou partes do sistema que não estão sendo testadas diretamente.

### O que são Mocks e Stubs?
- **Mocks**: São objetos simulados que verificam interações com dependências externas. Eles permitem validar se métodos específicos foram chamados com os parâmetros corretos.
- **Stubs**: São objetos simulados que retornam valores predefinidos. Eles são usados para simular respostas de dependências externas sem verificar interações.

### Diferenças entre Mocks e Stubs
| Característica         | Mocks                          | Stubs                          |
|------------------------|---------------------------------|---------------------------------|
| **Propósito**          | Verificar interações           | Simular respostas              |
| **Validação**          | Confirma chamadas de métodos   | Retorna valores predefinidos   |
| **Uso comum**          | Testar comportamento           | Testar resultados esperados    |

### Quando usar Mocks e Stubs?
- **Mocks**: Use quando você precisa verificar como o código interage com dependências externas, como chamadas de métodos ou serviços.
- **Stubs**: Use quando você precisa simular respostas de dependências externas sem se preocupar com as interações.

### Exemplos com Mockito
Mockito é uma biblioteca popular para criar mocks e stubs em Java. Aqui estão exemplos simples:

#### Exemplo de Mock
Mocks são usados para verificar interações com dependências externas.
```java
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

public class MockTest {
    @Test
    void testMock() {
        // Criando um mock
        List<String> mockList = mock(List.class);

        // Definindo comportamento
        when(mockList.size()).thenReturn(10);

        // Verificando interações
        mockList.add("Item");
        verify(mockList).add("Item");

        // Asserções
        assertEquals(10, mockList.size());
    }
}
```

#### Exemplo de Stub
Stubs são usados para simular respostas de dependências externas.
```java
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

public class StubTest {
    @Test
    void testStub() {
        // Criando um stub
        List<String> stubList = mock(List.class);

        // Definindo comportamento
        when(stubList.get(0)).thenReturn("Item");

        // Asserções
        assertEquals("Item", stubList.get(0));
    }
}
```

### Boas Práticas
1. **Isolamento**: Sempre isole o código em teste das dependências externas.
2. **Clareza**: Certifique-se de que os mocks e stubs sejam configurados de forma clara e fácil de entender.
3. **Evite Excesso de Mocks**: Não use mocks para tudo. Apenas simule dependências externas quando necessário.

### Benefícios
- **Facilidade de Teste**: Permite testar o código sem dependências externas.
- **Rapidez**: Reduz o tempo de execução dos testes.
- **Confiabilidade**: Garante que o código funcione corretamente em diferentes cenários.

## Estudos de Caso
### Caso 1: Sistema de E-commerce
Um sistema de e-commerce utilizou o JaCoCo para identificar áreas do código não testadas e o Mockito para simular interações com serviços de pagamento. Isso resultou em maior cobertura de testes e maior confiabilidade.

## Exercícios Práticos
1. Configure o JaCoCo em um projeto Java e gere um relatório de cobertura.
2. Crie mocks e stubs usando Mockito para testar um sistema de login.
3. Analise um relatório do JaCoCo e identifique áreas do código que precisam de mais testes.

## Referências
- Documentação oficial do JaCoCo: https://www.jacoco.org
- Documentação oficial do Mockito: https://site.mockito.org
- Livro "Effective Java" de Joshua Bloch

## Conclusão
Ferramentas como JaCoCo e Mockito são essenciais para garantir a qualidade e confiabilidade de sistemas. Com elas, você pode medir a eficácia dos testes e simular dependências externas, tornando os testes mais robustos e eficientes.