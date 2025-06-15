# Ferramentas de Cobertura e Dublês de Teste

## Sumário
1. Introdução
2. Objetivos
3. Metodologia
4. Ferramentas de Cobertura
   - Introdução ao Jacoco
   - Configuração do Jacoco em um projeto Java
   - Interpretação de relatórios de cobertura
5. Dublês de Teste
   - Tipos de Dublês de Teste
   - Explicação dos Tipos
   - Exemplos Práticos
6. Estudos de Caso
7. Exercícios Práticos
8. Referências

## Introdução
Nesta aula, exploraremos duas ferramentas essenciais para testes de software: cobertura de testes e dublês de teste. A cobertura de testes ajuda a medir a eficácia dos testes, enquanto os dublês de teste permitem simular comportamentos de dependências externas, tornando os testes mais robustos e isolados.

## Ferramentas de Cobertura
### Introdução ao Jacoco
JaCoCo é uma ferramenta popular para medir a cobertura de testes em projetos Java. Ele gera relatórios detalhados que ajudam os desenvolvedores a identificar áreas do código que não estão sendo testadas.

### Configuração do Jacoco em um Projeto Java
Para configurar o JaCoCo em um projeto Maven:
1. Adicione o plugin JaCoCo ao arquivo `pom.xml`:

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

## Dublês de Teste

### Tipos de Dublês de Teste

| Tipo     | Propósito Principal | Complexidade | Exemplo de Uso |
|----------|--------------------|--------------|----------------|
| Dummy    | Preencher parâmetros irrelevantes | Muito baixa   | Passar um objeto vazio quando o valor não será usado |
| Fake     | Implementação funcional simplificada | Baixa         | Banco de dados em memória, servidor HTTP fake |
| Stub     | Fornecer respostas pré-definidas | Média         | Retornar um valor fixo em um método de serviço |
| Spy      | Registrar interações para inspeção posterior | Média-alta   | Contar quantas vezes um método foi chamado |
| Mock     | Validar interações e comportamentos esperados | Alta         | Verificar se um método foi chamado com parâmetros específicos |

---

### Explicação dos Tipos

**Dummy**  
Objetos criados apenas para preencher parâmetros obrigatórios, mas que não são usados pelo teste. Exemplo: passar um objeto nulo ou vazio porque o método exige, mas o valor não é relevante para o teste.

**Fake**  
Implementa uma lógica real, porém simplificada, que pode ser usada em testes. Um exemplo clássico é um banco de dados em memória que simula operações de persistência sem acessar um banco real.

**Stub**  
Fornece respostas pré-definidas para chamadas de métodos, permitindo simular comportamentos de dependências externas. Não verifica interações, apenas retorna valores configurados.

**Spy**  
Além de fornecer respostas como um stub, também registra informações sobre como foi utilizado, como quantas vezes um método foi chamado ou quais parâmetros foram passados. Útil para verificar efeitos colaterais sem a complexidade de um mock completo.

**Mock**  
Permite definir expectativas sobre interações: verifica se métodos foram chamados, com quais parâmetros e em qual ordem. É o tipo mais completo e complexo, ideal para validar comportamentos e interações entre componentes.

---

### Exemplos Práticos

#### Dummy

Usado apenas para preencher o construtor, pois o método não será chamado.

```java
@Test
void testProcessarPagamentoComDummy() {
    ServicoExterno dummyServico = new ServicoExterno() {
        @Override
        public String realizarPagamento(double valor) {
            throw new UnsupportedOperationException("Não deve ser chamado");
        }
    };
    PagamentoService pagamentoService = new PagamentoService(dummyServico);

    assertThrows(IllegalArgumentException.class, () -> pagamentoService.processarPagamento(0.0));
}
```

#### Fake

Implementação funcional simplificada.


```java
class FakeServicoExterno implements ServicoExterno {
    @Override
    public String realizarPagamento(double valor) {
        if (valor > 1000) return "Limite excedido";
        return "Pagamento fake realizado";
    }
}

@Test
void testProcessarPagamentoComFake() {
    PagamentoService pagamentoService = new PagamentoService(new FakeServicoExterno());
    assertEquals("Pagamento fake realizado", pagamentoService.processarPagamento(500.0));
    assertEquals("Limite excedido", pagamentoService.processarPagamento(1500.0));
}
```

#### Stub

Retorna resposta fixa, independente do valor.


```java
@Test
void testProcessarPagamentoComStub() {
    ServicoExterno stubServico = new ServicoExterno() {
        @Override
        public String realizarPagamento(double valor) {
            return "Pagamento simulado com sucesso";
        }
    };
    PagamentoService pagamentoService = new PagamentoService(stubServico);
    assertEquals("Pagamento simulado com sucesso", pagamentoService.processarPagamento(50.0));
}
```

#### Spy

Permite inspecionar chamadas e argumentos.

```java
class SpyServicoExterno implements ServicoExterno {
    int chamadas = 0;
    double ultimoValor = 0;

    @Override
    public String realizarPagamento(double valor) {
        chamadas++;
        ultimoValor = valor;
        return "Spy: pagamento realizado";
    }
}

@Test
void testProcessarPagamentoComSpy() {
    SpyServicoExterno spyServico = new SpyServicoExterno();
    PagamentoService pagamentoService = new PagamentoService(spyServico);

    pagamentoService.processarPagamento(200.0);

    assertEquals(1, spyServico.chamadas);
    assertEquals(200.0, spyServico.ultimoValor);
}
```

#### Mock

Permite definir comportamentos e validar interações detalhadas.


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
```

## Exercícios Práticos
1. Configure o JaCoCo em um projeto Java e gere um relatório de cobertura.
2. Crie dublês de teste (dummy, fake, stub, spy e mock) para testar um projeto.
3. Analise um relatório do JaCoCo e identifique áreas do código que precisam de mais testes.

## Referências
- Documentação oficial do JaCoCo: https://www.jacoco.org
- Documentação oficial do Mockito: https://site.mockito.org
- Livro "Effective Java" de Joshua Bloch

## Conclusão
Ferramentas como JaCoCo e os diferentes tipos de dublês de teste são essenciais para garantir a qualidade e confiabilidade de sistemas. Com elas, você pode medir a eficácia dos testes e simular dependências externas, tornando os testes mais robustos e eficientes.