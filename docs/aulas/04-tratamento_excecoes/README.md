# SUMÁRIO GERAL

1. [Conceito Geral de Exceções](#sec1)  
   1.1. Motivação e Origem do Tratamento de Exceções  
   1.2. Definição de Exceção: Quando, Como e Por Quê?  
   1.3. Fluxo Normal x Fluxo de Exceção  

2. [Visão Geral das Exceções em Java](#sec2)  
   2.1. Hierarquia de Exceções (Throwable, Error, Exception)  
   2.2. Exceções Checadas vs. Não Checadas  
   2.3. Exceções de Sistema (Error)  
   2.4. Relação com “Stack Trace”  

3. [Exceções Checadas (Checked Exceptions)](#sec3)  
   3.1. Conceito e Exemplos (IOException, SQLException etc.)  
   3.2. Obrigatoriedade de Tratamento ou Declaração (throws)  
   3.3. Impacto no Design: Por que Java Força Checked Exceptions?  

4. [Exceções Não Checadas (Unchecked Exceptions)](#sec4)  
   4.1. Conceito e Exemplos (RuntimeException, NullPointerException, IllegalArgumentException)  
   4.2. Não Obrigatoriedade de Declaração ou Tratamento  
   4.3. Quando usar Exceções Não Checadas?  

5. [Blocos try/catch/finally e Multi-Catch](#sec5)  
   5.1. Estrutura Básica de try/catch  
   5.2. Papel do Bloco finally  
   5.3. Uso de Resources (try-with-resources) – Breve Menção  
   5.4. Multi-Catch (Java 7+) e Boas Práticas  

6. [Classes de Exceção Padrão (RuntimeException, IOException etc.)](#sec6)  
   6.1. Principais Classes no Pacote Java  
   6.2. Exceções Comuns no Dia a Dia (NullPointerException, NumberFormatException, etc.)  
   6.3. Hierarquia e Pacotes (java.lang, java.io, java.sql)  

7. [Uso de throw e Criação de Exceções Personalizadas](#sec7)  
   7.1. Lançando Exceções Explicitamente com `throw`  
   7.2. Declaração com `throws` vs. Bloco try/catch  
   7.3. Criando Subclasses de Exception ou RuntimeException  
   7.4. Mensagens e Construtores Adequados  

8. [Boas Práticas de Tratamento de Exceções](#sec8)  
   8.1. Evitar “Swallowing” Exceções (catch vazio)  
   8.2. Mensagens Claras e Log Adequado  
   8.3. Não Abusar de Checked Exceptions  
   8.4. Princípios de Design: Retorno de Erro vs. Exceções  
   8.5. Encapsulamento de Exceções: “Exception Chaining”  

9. [Exercícios Propostos](#sec9)  
    9.1. Exercícios Simples  
    9.2. Exercícios Intermediários  
    9.3. Exercícios Avançados  

10. [Referências e Leituras Adicionais](#sec10)

---

<div id="sec1"></div>

## 1. CONCEITO GERAL DE EXCEÇÕES

### 1.1. Motivação e Origem do Tratamento de Exceções
Em linguagens mais antigas, antes da popularização da Programação Orientada a Objetos, erros eram comumente tratados via códigos de retorno (por exemplo, retornar -1 para indicar falha). Isso tornava o código confuso, pois o programador precisava verificar a cada chamada se o resultado indicava erro ou sucesso.  

**Com exceções**, o controle de fluxo normal é interrompido quando ocorre um erro ou situação inesperada; a linguagem fornece mecanismos para capturar e tratar esses eventos, ou propagá-los para métodos superiores, permitindo um design mais claro e **separando** a lógica principal do tratamento de erros.

### 1.2. Definição de Exceção: Quando, Como e Por Quê?
Uma **exceção** é um evento anormal que ocorre durante a execução de um programa, interrompendo o fluxo normal de instruções. Na maioria das linguagens, inclusive Java, uma exceção é representada por um **objeto** (instância de uma classe que estende `Throwable`). Quando algo dá errado – por exemplo, ao acessar um índice inválido de array – a JVM cria ou lança (`throw`) um objeto de exceção.  

- **Por que usar exceções?**  
  - Para **sinalizar** erros ou condições especiais, sem forçar o código que chama a função a verificar manualmente retorno.  
  - Para **organizar** o tratamento de erros de forma mais modular, podendo tratar exceções em diferentes níveis.

### 1.3. Fluxo Normal x Fluxo de Exceção
No fluxo normal, as instruções são executadas sequencialmente. Mas se um método lança uma exceção, o fluxo **muda**: a JVM procura um “handler” (bloco `catch`) que possa tratar aquela exceção. Se não encontrar em um nível, sobe a pilha de chamadas até encontrar. Se chegar ao nível principal (main) sem tratamento, o programa normalmente encerra exibindo uma mensagem de erro (stack trace).

---

<div id="sec2"></div>

## 2. VISÃO GERAL DAS EXCEÇÕES EM JAVA

### 2.1. Hierarquia de Exceções (Throwable, Error, Exception)
Na linguagem Java, há uma **hierarquia** com a classe `Throwable` no topo, que tem duas ramificações principais:

1. **Error**: indica problemas sérios que normalmente não são recuperáveis pelo aplicativo, como `OutOfMemoryError`. Geralmente não tratamos `Error` em blocos `catch` (ou não devíamos), pois são erros do sistema ou da JVM.
2. **Exception**: indica condições que a aplicação pode (e deve) tratar. É subdividida em:
   - **RuntimeException** (não checadas)  
   - Outras exceções (checadas).

Exemplo de hierarquia simplificada:
```
java.lang.Throwable
 ├── java.lang.Error
 └── java.lang.Exception
      ├── java.lang.RuntimeException
      └── java.io.IOException
          └── ...
```

### 2.2. Exceções Checadas vs. Não Checadas
- **Checadas (checked)**: A linguagem **exige** que o código trate ou declare essas exceções. Ex.: `IOException`, `SQLException`. São subclasses de `Exception`, mas não de `RuntimeException`.  
- **Não Checadas (unchecked)**: São subclasses de `RuntimeException`. Não é obrigatório tratar explicitamente. Ex.: `NullPointerException`, `IndexOutOfBoundsException`.

### 2.3. Exceções de Sistema (Error)
Como mencionado, `Error` não faz parte das exceções que normalmente tratamos. Exemplos: `OutOfMemoryError`, `StackOverflowError`. Elas indicam problemas de VM ou do ambiente. Em casos raríssimos, podemos capturá-las, mas normalmente não faz sentido, pois a aplicação não consegue se recuperar facilmente.

### 2.4. Relação com “Stack Trace”
Quando ocorre uma exceção, a JVM gera um “stack trace”: um relatório das chamadas de método (pilha) que foram executadas até o ponto da exceção. Isso ajuda a **debugar** o problema. Podemos imprimir esse stack trace com métodos como `printStackTrace()`.

---

<div id="sec3"></div>

## 3. EXCEÇÕES CHECADAS (CHECKED EXCEPTIONS)

### 3.1. Conceito e Exemplos (IOException, SQLException etc.)
As “exceções checadas” são aquelas que o compilador **força** a manusear. Precisamos usar bloco `try/catch` ou declarar `throws` na assinatura do método. Exemplos:  
- `IOException` (erros de I/O),  
- `SQLException` (erros de acesso a banco de dados),  
- `ClassNotFoundException`, etc.  

Caso tenhamos um método que lança `IOException`, por exemplo:
```java
public void lerArquivo(String caminho) throws IOException {
    // ...
}
```
Qualquer código que chame `lerArquivo` terá que envolvê-lo em try/catch ou propagar com `throws`.

### 3.2. Obrigatoriedade de Tratamento ou Declaração (throws)
Em Java, se um método pode lançar uma checked exception e não a trata internamente, ele **deve** declará-la em `throws`. Assim, quem chamar o método saberá que precisa lidar com essa exceção. Por exemplo:
```java
public void meuMetodo() throws IOException {
    // algo que lança IOException
}
```
Se o programador não fizer isso, o código não compila.

### 3.3. Impacto no Design: Por que Java Força Checked Exceptions?
A filosofia original de Java é que se a exceção for recuperável (como problemas de I/O ou de conexão), o programador deve **explicitamente** lidar com ela. Isso teoricamente aumenta a robustez, porém alguns desenvolvedores criticam que acaba forçando exceções a “subir” demasiadamente ou gerando blocos `try/catch` repetitivos.

---

<div id="sec4"></div>

## 4. EXCEÇÕES NÃO CHECADAS (UNCHECKED EXCEPTIONS)

### 4.1. Conceito e Exemplos (RuntimeException, NullPointerException, IllegalArgumentException)
Exceções não checadas (unchecked) são subclasses de `RuntimeException`. Por exemplo:  
- `NullPointerException`,  
- `ArrayIndexOutOfBoundsException`,  
- `IllegalArgumentException`,  
- `ArithmeticException`, etc.

Essas exceções ocorrem, em geral, por **erros de lógica** do programador ou violações de contratos. A linguagem **não** força o tratamento ou declaração – assumindo que se elas ocorrem, muitas vezes são bugs que devem ser corrigidos no código, não necessariamente tratados com try/catch.

### 4.2. Não Obrigatoriedade de Declaração ou Tratamento
Você pode lançar explicitamente uma `RuntimeException`, mas não precisa colocar `throws` na assinatura do método, nem capturá-la se não quiser. Isso dá liberdade, mas também pode gerar problemas se forem usados de forma indevida (sem indicar que o método pode lançar).

### 4.3. Quando usar Exceções Não Checadas?
- Para **erros de programação** (ex.: se um método não aceita argumentos nulos, pode jogar `NullPointerException` ou `IllegalArgumentException`).  
- Para cenários em que “ou eu garanto que não ocorrerá, ou se ocorrer, é bug e devo consertar”.

---

<div id="sec5"></div>

## 5. BLOCOS TRY/CATCH/FINALLY E MULTI-CATCH

### 5.1. Estrutura Básica de try/catch
O bloco try/catch é a forma clássica de tratar exceções:
```java
try {
    // código que pode lançar exceção
} catch (TipoExcecao e) {
    // tratamento
}
```
Se ocorrer `TipoExcecao` dentro do `try`, a execução pula para dentro do `catch`. Se não ocorrer, o `catch` é ignorado.

### 5.2. Papel do Bloco finally
O bloco `finally` é executado **sempre**, independente de exceção ou não. Útil para liberar recursos (fechar arquivos, conexões), por exemplo:
```java
FileReader fr = null;
try {
    fr = new FileReader("arquivo.txt");
    // ler...
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (fr != null) {
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
Mesmo que ocorra exceção ou não, `finally` roda.

### 5.3. Uso de Resources (try-with-resources) – Breve Menção
Desde Java 7, existe `try-with-resources`:
```java
try (FileReader fr = new FileReader("arquivo.txt");
     BufferedReader br = new BufferedReader(fr)) {
    // usar br
} catch (IOException e) {
    e.printStackTrace();
}
```
O recurso (`fr`, `br`) será fechado automaticamente. Substitui parte da necessidade de `finally`.

### 5.4. Multi-Catch (Java 7+) e Boas Práticas
No Java 7+, podemos capturar várias exceções com um único `catch`, se tivermos tratamento igual:
```java
try {
    // ...
} catch (IOException | SQLException e) {
    e.printStackTrace();
}
```
Isso evita duplicar blocos catch parecidos. Mas só funciona se o tratamento for idêntico.

---

<div id="sec6"></div>

## 6. CLASSES DE EXCEÇÃO PADRÃO (RUNTIMEEXCEPTION, IOEXCEPTION ETC.)

### 6.1. Principais Classes no Pacote Java
- **RuntimeException**: Raiz das exceções não checadas.  
- **IOException**: Relacionada a operações de I/O que falham ou são interrompidas.  
- **SQLException** (no pacote java.sql): Acesso a banco de dados.  
- **ClassNotFoundException**, **FileNotFoundException**, etc.

### 6.2. Exceções Comuns no Dia a Dia (NullPointerException, NumberFormatException, etc.)
- **NullPointerException (NPE)**: Ocorre ao acessar membro de uma referência `null`.  
- **NumberFormatException**: Ao tentar converter string para número em formato inválido.  
- **IndexOutOfBoundsException**: Acessar índice fora do intervalo de array, lista etc.

### 6.3. Hierarquia e Pacotes (java.lang, java.io, java.sql)
- `java.lang`: Contém `RuntimeException` e várias subclasses como `NullPointerException`, `ArithmeticException`, etc.  
- `java.io`: Contém `IOException`, `FileNotFoundException`, etc.  
- `java.sql`: Contém `SQLException` e subclasses específicas.

---

<div id="sec7"></div>

## 7. USO DE THROW E CRIAÇÃO DE EXCEÇÕES PERSONALIZADAS

### 7.1. Lançando Exceções Explicitamente com `throw`
É possível criar e lançar manualmente uma exceção:
```java
if (valor < 0) {
    throw new IllegalArgumentException("Valor não pode ser negativo: " + valor);
}
```
Isso interrompe o fluxo imediatamente, procurando um `catch` correspondente.

### 7.2. Declaração com `throws` vs. Bloco try/catch
- Se você lança uma exceção checada, é preciso cercar com `try/catch` ou declarar `throws` na assinatura.  
- Para exceções não checadas, pode apenas `throw` sem obrigatoriedade de `throws`, embora possa.

### 7.3. Criando Subclasses de Exception ou RuntimeException
Para criar uma exceção específica da sua aplicação, basta estender `Exception` (checada) ou `RuntimeException` (não checada). Exemplo:

```java
public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String msg) {
        super(msg);
    }
}
```
Depois, em algum lugar:
```java
if (saldo < valor) {
    throw new SaldoInsuficienteException("Saldo insuficiente para sacar!");
}
```
Agora o chamador precisa tratar ou declarar `SaldoInsuficienteException`.

### 7.4. Mensagens e Construtores Adequados
Na criação de exceções personalizadas, é importante fornecer construtores que chamem `super(msg)` para exibir uma mensagem adequada. Podemos também sobrescrever métodos ou armazenar dados adicionais (ex.: ID, contexto etc.) se necessário.

---

<div id="sec8"></div>

## 8. BOAS PRÁTICAS DE TRATAMENTO DE EXCEÇÕES

### 8.1. Evitar “Swallowing” Exceções (catch vazio)
Um erro comum é capturar exceções e não fazer nada:
```java
try {
    // ...
} catch (IOException e) {
    // silenciosamente ignora
}
```
Isso mascara erros e dificulta a depuração. Sempre faça algo (log, mensagem ao usuário, relançar exceção etc.).

### 8.2. Mensagens Claras e Log Adequado
Ao tratar exceções, forneça **informações úteis**: qual operação falhou, que dados estavam sendo processados, etc. O log deve ser claro para ajudar a resolver problemas.

### 8.3. Não Abusar de Checked Exceptions
Excessivas exceções checadas podem poluir o código com blocos try/catch. Pense se a falha é algo recuperável ou se poderia ser uma `RuntimeException`. Siga convenções do projeto ou framework.

### 8.4. Princípios de Design: Retorno de Erro vs. Exceções
Em alguns cenários, retornar algo como `Optional` ou `null` é preferível a lançar exceções para condições esperadas (por ex. “não há resultado”). Exceções devem ser para situações **anormais**.

### 8.5. Encapsulamento de Exceções: “Exception Chaining”
Às vezes, ao capturar uma exceção, criamos e lançamos outra, mas mantemos a original como “causa”:
```java
} catch (SQLException e) {
    throw new MinhaException("Erro ao acessar DB", e);
}
```
Isso preserva o stack trace e dá contexto adicional.

---

<div id="sec9"></div>

## 9. EXERCÍCIOS PROPOSTOS

### 9.1. Exercício Simples

1. **Capturando Input Inválido**     
  - Peça ao usuário que digite um número inteiro (pode usar Scanner).
  - Se ele digitar algo que não seja número, gere ou capture NumberFormatException.
  - Se a exceção ocorrer, informe: `Valor inválido. Digite novamente`.
  - Repita até o usuário acertar.

### 9.2. Exercício Intermediário

2. **Criação de Exceção Personalizada**  
   - Crie `SaldoInsuficienteException extends Exception`.  
   - Faça uma classe “ContaBancaria” com método “sacar(double valor)” que lança `SaldoInsuficienteException` se `saldo < valor`.  
   - No `main`, capture essa exceção e mostre mensagem “Saque inválido”.

---

<div id="sec10"></div>

## 10. REFERÊNCIAS E LEITURAS ADICIONAIS

1. **Documentação Oficial Java** – Capítulo sobre Exceções (Tutorial da Oracle).  
2. **Effective Java** – *Joshua Bloch*, capítulos sobre exceções (itens específicos que falam de checked vs. unchecked, chaining etc.).  
3. **Java: The Complete Reference** – *Herbert Schildt*, seção de exceções para ver exemplos de multi-catch, try-with-resources.  

---

## ENCERRAMENTO

Nesta aula, exploramos em **muitos detalhes** o mecanismo de **Exceções em Java**:

1. **Conceito Geral**: Exceções permitem tratar erros de forma estruturada, separando fluxo normal do fluxo de erro.  
2. **Diferença Checadas / Não Checadas**: Checadas (Exception, mas não RuntimeException) exigem try/catch ou throws; não checadas (RuntimeException) não.  
3. **try/catch/finally, multi-catch**: Estruturas para capturar exceções, garantir limpeza de recursos, e tratar múltiplas exceções de forma simplificada.  
4. **Classes de Exceção Padrão**: Relações com `RuntimeException`, `IOException`, e hierarquia `Throwable`.  
5. **Uso de throw e Criação de Exceções Personalizadas**: Como lançar e definir classes próprias de exceção, incluindo `SaldoInsuficienteException` e afins.  
6. **Boas Práticas**: Evitar engolir exceções, dar mensagens claras, não abusar de checked exceptions, encadear exceções (chaining) de forma adequada.  

Com esse panorama, você está apto a **compreender** e **aplicar** o tratamento de exceções em seus projetos, garantindo **robustez**, **manutenibilidade** e **clareza** no tratamento de erros.  