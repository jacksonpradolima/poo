# Visão Geral de Testes e Introdução ao JUnit

## Visão Geral de Testes
### Definição e Importância
Testes de software são processos para verificar se um sistema funciona conforme o esperado. Eles ajudam a identificar bugs, garantir qualidade e aumentar a confiabilidade do software.

#### Por que testar?
- **Prevenção de erros**: Identificar problemas antes que eles impactem o usuário.
- **Qualidade**: Garantir que o software atenda aos requisitos especificados.
- **Manutenção**: Facilitar futuras alterações no código.

#### Analogias
Imagine que você está construindo uma ponte. Antes de permitir que carros passem por ela, você realiza testes para garantir que ela seja segura. Da mesma forma, os testes de software garantem que o "produto final" seja confiável.

### Tipos de Testes
1. **Testes Unitários**: Validam partes individuais do código.
   - Exemplo: Testar um método que calcula a soma de dois números.
2. **Testes de Integração**: Verificam a interação entre diferentes módulos.
   - Exemplo: Testar a comunicação entre um sistema de login e um banco de dados.
3. **Testes de Sistema**: Avaliam o sistema como um todo.
   - Exemplo: Testar todas as funcionalidades de um aplicativo de e-commerce.
4. **Testes de Aceitação**: Garantem que o sistema atende aos requisitos do cliente.
   - Exemplo: Validar que o sistema de pagamento funciona conforme esperado.

## Introdução ao JUnit
### Estrutura do JUnit
JUnit é uma biblioteca para testes unitários em Java. Ele permite criar e executar testes de forma simples e eficiente.

#### Exemplo de Estrutura
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {
    @Test
    void testSoma() {
        int resultado = Calculadora.soma(2, 3);
        assertEquals(5, resultado);
    }
}
```

### Anotações Principais
- `@Test`: Indica um método de teste.
- `@BeforeEach`: Executa antes de cada teste.
- `@AfterEach`: Executa após cada teste.

#### Exemplo
```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ExemploTest {
    @BeforeEach
    void setup() {
        System.out.println("Preparando o teste...");
    }

    @Test
    void testeExemplo() {
        System.out.println("Executando o teste...");
    }

    @AfterEach
    void teardown() {
        System.out.println("Finalizando o teste...");
    }
}
```

### Assertivas
Assertivas são métodos fornecidos pelo JUnit para verificar se os resultados obtidos nos testes correspondem aos resultados esperados. Elas são fundamentais para validar o comportamento do código e garantir que ele funcione conforme o esperado.

#### Tipos de Assertivas
- `assertEquals(expected, actual)`: Compara valores esperados e reais.
- `assertTrue(condition)`: Verifica se uma condição é verdadeira.
- `assertFalse(condition)`: Verifica se uma condição é falsa.
- `assertNotNull(object)`: Verifica se um objeto não é nulo.
- `assertNull(object)`: Verifica se um objeto é nulo.
- `assertThrows(expectedType, executable)`: Verifica se uma exceção específica é lançada.

#### Exemplo
```java
@Test
void testAssertivas() {
    assertEquals(10, 5 + 5, "A soma está incorreta");
    assertTrue(3 > 2, "A condição deveria ser verdadeira");
    assertFalse(2 > 3, "A condição deveria ser falsa");
    assertNotNull("Texto", "O objeto não deveria ser nulo");
    assertNull(null, "O objeto deveria ser nulo");
    assertThrows(IllegalArgumentException.class, () -> {
        throw new IllegalArgumentException("Erro esperado");
    }, "A exceção esperada não foi lançada");
}
```

## Cobertura de Testes
### Conceitos de Cobertura
Cobertura de testes mede o quanto do código é exercitado pelos testes. Existem diferentes tipos de cobertura, cada um com um foco específico:

#### Line Coverage
**Line Coverage** (Cobertura de Linhas) mede a porcentagem de linhas de código executadas durante os testes. Ele verifica se cada linha do código foi executada pelo menos uma vez. Este tipo de cobertura é útil para garantir que todas as partes do código foram testadas, mas não considera os diferentes caminhos de execução dentro de estruturas de controle como `if`, `else` ou `switch`.

**Exemplo:**
Considere o seguinte código:
```java
public String verificarNumero(int numero) {
    if (numero > 0) {
        return "Positivo";
    } else {
        return "Negativo ou Zero";
    }
}
```
Para alcançar 100% de **Line Coverage**, os testes precisam executar todas as linhas do método, independentemente dos caminhos de decisão. Por exemplo:
- `verificarNumero(1)` (executa `return "Positivo";`)
- `verificarNumero(-1)` (executa `return "Negativo ou Zero";`)

#### Branch Coverage
**Branch Coverage** (Cobertura de Ramificações) mede a porcentagem de caminhos de decisão cobertos pelos testes. Ele verifica se cada ramificação de estruturas de controle como `if`, `else`, `switch` foi executada. Este tipo de cobertura é mais detalhado do que **Line Coverage**, pois garante que todos os cenários possíveis dentro de estruturas de controle sejam testados.

**Exemplo:**
Considere o mesmo código:
```java
public String verificarNumero(int numero) {
    if (numero > 0) {
        return "Positivo";
    } else {
        return "Negativo ou Zero";
    }
}
```
Para alcançar 100% de **Branch Coverage**, os testes precisam cobrir todos os caminhos de decisão:
- `numero > 0` (executa `return "Positivo";`)
- `numero <= 0` (executa `return "Negativo ou Zero";`)

**Diferença entre Line Coverage e Branch Coverage:**
- **Line Coverage** garante que todas as linhas do código sejam executadas, mas não verifica se todos os caminhos de decisão foram testados.
- **Branch Coverage** garante que todos os caminhos de decisão dentro de estruturas de controle sejam testados, mesmo que algumas linhas sejam executadas mais de uma vez.

#### Exemplo Detalhado
Considere o seguinte código mais complexo:
```java
public String verificarNumero(int numero) {
    if (numero > 0) {
        return "Positivo";
    } else if (numero < 0) {
        return "Negativo";
    } else {
        return "Zero";
    }
}
```
Para alcançar **Line Coverage**, os testes precisam executar todas as linhas do método:
- `verificarNumero(1)` (executa `return "Positivo";`)
- `verificarNumero(-1)` (executa `return "Negativo";`)
- `verificarNumero(0)` (executa `return "Zero";`)

Para alcançar **Branch Coverage**, os testes precisam cobrir todos os caminhos de decisão:
- `numero > 0` (executa `return "Positivo";`)
- `numero < 0` (executa `return "Negativo";`)
- `numero == 0` (executa `return "Zero";`)

#### Teste para Cobertura Completa
```java
@Test
void testVerificarNumero() {
    assertEquals("Positivo", verificarNumero(1));
    assertEquals("Negativo", verificarNumero(-1));
    assertEquals("Zero", verificarNumero(0));
}
```


#### Quando Usar Line Coverage ou Branch Coverage

Embora **Branch Coverage** seja mais detalhado e ideal para garantir que todos os caminhos de decisão sejam testados, existem cenários em que **Line Coverage** pode ser suficiente ou até mais prático. A escolha entre os dois depende do objetivo dos testes e da complexidade do código.

**Quando usar Line Coverage:**
- **Código simples:** Quando o código não possui muitas estruturas de controle ou ramificações, como `if`, `else` ou `switch`.
- **Validação inicial:** Para garantir que todas as linhas do código foram executadas pelo menos uma vez, sem se preocupar com os diferentes caminhos de decisão.
- **Rapidez:** Quando o objetivo é obter uma visão geral rápida da cobertura dos testes.

**Quando usar Branch Coverage:**
- **Código complexo:** Quando o código possui várias estruturas de controle e caminhos de decisão.
- **Garantia de qualidade:** Para garantir que todos os cenários possíveis dentro de estruturas de controle foram testados.
- **Prevenção de bugs:** Quando é essencial evitar erros que possam surgir em caminhos de decisão não testados.

**Quando usar ambos:**
- **Cobertura completa:** Para garantir que todas as linhas e caminhos de decisão foram testados.
- **Projetos críticos:** Em sistemas onde a confiabilidade é essencial, como aplicativos financeiros ou de saúde.
- **Análise detalhada:** Quando é necessário identificar áreas do código que não estão sendo exercitadas pelos testes.

**Exemplo prático:**
Imagine que você está desenvolvendo um sistema de autenticação. Para garantir que ele funcione corretamente, você pode usar:
- **Line Coverage** para verificar se todas as linhas de código foram executadas, como chamadas de métodos e inicializações de variáveis.
- **Branch Coverage** para testar todos os cenários possíveis, como autenticação bem-sucedida, falha de autenticação e bloqueio de conta após várias tentativas.

Ao combinar **Line Coverage** e **Branch Coverage**, você obtém uma visão mais completa da eficácia dos testes e da qualidade do código.

### Ferramentas para Medir Cobertura
Ferramentas como JaCoCo ajudam a medir e visualizar a cobertura de testes.

#### Exemplo de Uso
1. Adicione o JaCoCo ao seu projeto Maven:
```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.8</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
2. Execute os testes e gere o relatório:
```bash
mvn test
```

## Estudos de Caso
Apresentaremos exemplos reais de projetos que se beneficiaram de testes bem implementados.

### Estudo de Caso: Implementação de Teste Básico Completo
#### Cenário
Imagine que você está desenvolvendo uma calculadora simples que realiza operações básicas como soma, subtração, multiplicação e divisão. Você deseja garantir que todas as operações funcionem corretamente.

#### Código da Calculadora
```java
public class Calculadora {
    public int soma(int a, int b) {
        return a + b;
    }

    public int subtracao(int a, int b) {
        return a - b;
    }

    public int multiplicacao(int a, int b) {
        return a * b;
    }

    public int divisao(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Divisão por zero não é permitida");
        }
        return a / b;
    }
}
```

#### Testes para a Calculadora
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {
    private final Calculadora calculadora = new Calculadora();

    @Test
    void testSoma() {
        assertEquals(5, calculadora.soma(2, 3));
        assertEquals(-1, calculadora.soma(-2, 1));
    }

    @Test
    void testSubtracao() {
        assertEquals(1, calculadora.subtracao(3, 2));
        assertEquals(-3, calculadora.subtracao(-2, 1));
    }

    @Test
    void testMultiplicacao() {
        assertEquals(6, calculadora.multiplicacao(2, 3));
        assertEquals(-2, calculadora.multiplicacao(-2, 1));
    }

    @Test
    void testDivisao() {
        assertEquals(2, calculadora.divisao(6, 3));
        assertThrows(IllegalArgumentException.class, () -> calculadora.divisao(6, 0));
    }
}
```

#### Resultado
Com esses testes, você garante que todas as operações da calculadora funcionem corretamente e que exceções sejam tratadas adequadamente.

## Exercícios Práticos
1. Crie testes unitários para uma classe simples em Java.
2. Use diferentes assertivas do JUnit.
3. Meça a cobertura de testes usando JaCoCo.

## Referências
- Documentação oficial do JUnit: https://junit.org
- Documentação do JaCoCo: https://www.jacoco.org

## Conclusão
Testes de software são fundamentais para garantir a qualidade e confiabilidade de sistemas. Com o JUnit, você pode implementar testes de forma eficiente e medir sua cobertura com ferramentas como JaCoCo. Pratique os conceitos aprendidos e aplique-os em seus projetos.