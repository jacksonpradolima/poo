# Tipos Genéricos (Generics) em Java

1. **Introdução aos Generics**  
   1.1 O que são Generics  
   1.2 Contexto histórico  
   1.3 Analogia  
   1.4 Diferença entre parametrização e herança

2. **Motivação: Antes e Depois dos Generics**  
   2.1 O problema sem Generics  
   2.2 Solução com Generics  
   2.3 Analogia ilustrativa  
   2.4 Impacto nos frameworks e bibliotecas  
   2.5 Conclusão

3. **Sintaxe Básica dos Generics**  
   3.1 Classes Genéricas  
   3.2 Métodos Genéricos  
   3.3 Interfaces Genéricas  
   3.4 Inferência de tipo

4. **Wildcards (`?`)**  
   4.1 Uso Básico de `?`  
   4.2 `? extends` e `? super`  
   4.3 Comparações e Casos de Uso

5. **Bounded Type Parameters**  
   5.1 Upper Bounds  
   5.2 Multiple Bounds  
   5.3 Lower Bounds (com `super`)

6. **Generics com Coleções**  
   6.1 `List<T>`, `Set<T>`, `Map<K,V>`  
   6.2 Iteração segura e uso com lambdas

7. **Generics em Bibliotecas e Frameworks**  
   7.1 Spring  
   7.2 JPA / Hibernate  
   7.3 Gson e Jackson

8. **Boas Práticas com Generics**  
   8.1 Evitar casting  
   8.2 Design de APIs  
   8.3 Nomenclatura padrão

9. **Limitações dos Generics em Java**  
   9.1 Type Erasure  
   9.2 Não é possível usar `new T()`  
   9.3 Incompatibilidade com arrays

10. **Estudos de Caso**  
    10.1 Builder Genérico  
    10.2 Repositório Genérico  
    10.3 Conversor de Tipo

11. **Exercícios Práticos**  
    11.1 Classe genérica `Caixa<T>`  
    11.2 Método genérico `maiorElemento`  
    11.3 Interface `Conversor<S, T>`  
    11.4 Uso de `List<? super Integer>`

12. **Referências**

---

# 1. Introdução aos Generics

## 1.1 O que são Generics?
Generics (ou tipos genéricos) são um recurso da linguagem Java que permite definir classes, interfaces e métodos que operam sobre **tipos parametrizados**. Em outras palavras, você pode escrever um código que funcione com diferentes tipos de dados, mas mantendo a **segurança de tipo** em tempo de compilação.

Imagine que você está desenvolvendo uma biblioteca que precisa lidar com listas de vários tipos: inteiros, strings, objetos personalizados, etc. Ao invés de duplicar o código para cada tipo, você pode usar generics para fazer isso de forma reutilizável.

### Benefícios principais:
- **Segurança de tipo:** detecta erros em tempo de compilação.
- **Reutilização de código:** você escreve uma única vez e aplica a múltiplos tipos.
- **Eliminação de casting:** evita o uso de coerções manuais arriscadas.
- **Integração com APIs modernas:** as bibliotecas padrão e frameworks modernos utilizam generics extensivamente.

## 1.2 Contexto histórico
Antes do Java 5, as coleções utilizavam o tipo `Object` e exigiam castings explícitos:

```java
List lista = new ArrayList();
lista.add("texto");
String texto = (String) lista.get(0); // cast necessário
```

Esse modelo era flexível, mas propenso a erros:

```java
lista.add(123); // aceito em tempo de compilação
String erro = (String) lista.get(1); // ClassCastException em tempo de execução
```

Com generics, o código se torna mais seguro:

```java
List<String> lista = new ArrayList<>();
lista.add("texto");
String texto = lista.get(0); // sem casting, seguro
```

## 1.3 Analogia
Pense em generics como **formas de bolo com rótulo**:
- Sem generics: o padeiro faz bolos e coloca todos em caixas sem etiqueta. Ao receber, você tem que abrir a caixa e verificar o sabor.
- Com generics: cada caixa já diz "chocolate", "baunilha"... você pega a certa com segurança e sem surpresas.

## 1.4 Diferença entre parametrização e herança
Enquanto a herança permite criar uma nova especialização de uma classe, a parametrização permite reutilizar a lógica da classe com diferentes tipos.

```java
Caixa<Integer> caixaNumerica = new Caixa<>();
Caixa<String> caixaTextual = new Caixa<>();
```

---

# 2. Motivação: Antes e Depois dos Generics

## 2.1 O problema sem Generics
Antes da introdução de Generics no Java 5, os desenvolvedores precisavam lidar com estruturas de dados que aceitavam qualquer tipo de objeto, geralmente armazenando instâncias do tipo `Object`. Embora isso desse flexibilidade, introduzia um risco considerável: como `Object` pode representar qualquer tipo, era necessário fazer **casting explícito** para recuperar os dados originais, o que causava erros em tempo de execução difíceis de detectar antecipadamente.

### Exemplo sem Generics:
```java
List lista = new ArrayList();
lista.add("Texto");
lista.add(42);

String texto = (String) lista.get(0); // OK
String numero = (String) lista.get(1); // ClassCastException em tempo de execução
```

Neste exemplo, a execução da linha que tenta converter um número inteiro em string gera uma exceção em tempo de execução que poderia ser evitada se o compilador pudesse validar o tipo de forma mais rígida.

## 2.2 Solução com Generics
Com Generics, o compilador exige que você declare o tipo que será armazenado na estrutura de dados. Isso permite validação em tempo de compilação e elimina a necessidade de castings manuais.

### Exemplo com Generics:
```java
List<String> lista = new ArrayList<>();
lista.add("Texto");
// lista.add(42); // Erro de compilação

String texto = lista.get(0); // Sem casting, seguro
```

### Vantagens práticas:
- Redução de bugs.
- Código mais legível e intuitivo.
- Compatibilidade com IDEs, como autocompletar e checagem de tipos em tempo real.
- Padronização das APIs modernas da linguagem.

## 2.3 Analogia ilustrativa
Imagine um depósito de caixas. Antes dos generics, qualquer tipo de objeto poderia ser colocado dentro da caixa, mas o rótulo da caixa sempre dizia “Objeto”. Para saber o que havia dentro, era necessário abrir e tentar interpretar, com o risco de tirar algo inesperado.

Com generics, o rótulo da caixa já diz “String” ou “Integer”. O conteúdo é consistente e o manuseio é seguro.

## 2.4 Impacto nos frameworks e bibliotecas
- As coleções da Java Collection Framework foram todas reescritas para suportar generics (como `List<T>`, `Map<K,V>`).
- APIs modernas como Spring, Hibernate, JPA, JavaFX e bibliotecas JSON como Gson e Jackson são fortemente baseadas em generics.
- A integração com Lambdas e Streams a partir do Java 8 depende fortemente de tipos genéricos para inferência e segurança de tipo.

## 2.5 Conclusão
O uso de generics tornou o código Java mais seguro, mais legível e mais fácil de manter. Eles são especialmente úteis em contextos que exigem reutilização, como APIs, bibliotecas, frameworks, e estruturas de dados.

Nas próximas seções, veremos como utilizar generics em classes, métodos e interfaces, além de explorar os recursos mais avançados como wildcards, bounds e o comportamento de type erasure.

---

# 3. Sintaxe Básica dos Generics

## 3.1 Classes Genéricas
Uma classe genérica é uma classe parametrizada com tipo. Isso significa que você pode declarar que ela operará com um tipo arbitrário, e somente durante sua utilização será definido qual tipo será esse.

### Exemplo:
```java
public class Caixa<T> {
    private T conteudo;

    public void guardar(T item) {
        this.conteudo = item;
    }

    public T abrir() {
        return conteudo;
    }
}
```

Uso:
```java
Caixa<String> caixaDeTexto = new Caixa<>();
caixaDeTexto.guardar("Mensagem");
System.out.println(caixaDeTexto.abrir());

Caixa<Integer> caixaDeNumeros = new Caixa<>();
caixaDeNumeros.guardar(123);
```

## 3.2 Métodos Genéricos
Você pode criar métodos que trabalham com tipos genéricos, mesmo em classes que não são genéricas. A sintaxe envolve declarar o tipo genérico antes do tipo de retorno.

```java
public class Util {
    public static <T> void imprimir(T elemento) {
        System.out.println(elemento);
    }
}
```

Uso:
```java
Util.imprimir("Olá mundo");
Util.imprimir(42);
```

## 3.3 Interfaces Genéricas
Assim como classes, interfaces também podem ser genéricas.

```java
public interface Repositorio<T> {
    void salvar(T entidade);
    T buscarPorId(int id);
}

public class RepositorioUsuario implements Repositorio<Usuario> {
    @Override
    public void salvar(Usuario entidade) { /* ... */ }

    @Override
    public Usuario buscarPorId(int id) { return new Usuario(); }
}
```

Interfaces genéricas permitem definir contratos reutilizáveis que operam com tipos variados.

## 3.4 Inferência de tipo
Desde o Java 7, é possível usar o operador diamante `<>` para inferir o tipo com base no lado esquerdo da atribuição:
```java
List<String> nomes = new ArrayList<>();
```

---

# 4. Wildcards (`?`)

Wildcards são utilizados em generics para permitir maior flexibilidade nos tipos aceitos. Eles são representados pelo símbolo `?` e aparecem em três formas principais: **não limitado (`?`)**, **com limite superior (`? extends T`)** e **com limite inferior (`? super T`)**.

## 4.1 Uso Básico de `?`
Um wildcard simples (`?`) representa um tipo desconhecido. Ele é útil para criar métodos que operam sobre coleções de tipos genéricos, mas sem a necessidade de saber exatamente qual é o tipo:

```java
public void imprimirTodos(List<?> lista) {
    for (Object item : lista) {
        System.out.println(item);
    }
}
```

Esse método pode aceitar `List<String>`, `List<Integer>` ou qualquer outra lista, pois o tipo de seus elementos é irrelevante para o propósito da função.

## 4.2 `? extends` e `? super`

### `? extends T`
Esse wildcard indica que o tipo é **T ou um subtipo de T**. Ele é mais seguro para **leitura**, mas não para **escrita**:

```java
public void processarAnimais(List<? extends Animal> animais) {
    for (Animal a : animais) {
        a.fazerSom();
    }
    // animais.add(new Gato()); // erro de compilação
}
```

### `? super T`
Esse wildcard indica que o tipo é **T ou um supertipo de T**. Ele é seguro para **escrita**, mas menos para **leitura**:

```java
public void adicionarNumeros(List<? super Integer> numeros) {
    numeros.add(1);
    numeros.add(2);
    // Integer i = numeros.get(0); // erro, pois retorna Object
}
```

## 4.3 Comparações e Casos de Uso

| Wildcard       | Significado                      | Ideal para  |
|----------------|----------------------------------|--------------|
| `?`            | Tipo desconhecido                | Leitura genérica |
| `? extends T`  | T ou subtipo de T                | Leitura segura |
| `? super T`    | T ou supertipo de T              | Escrita segura |

---

# 5. Bounded Type Parameters

Em vez de permitir qualquer tipo, você pode restringir os tipos permitidos usando **limites superiores e inferiores**.

## 5.1 Upper Bounds (`<T extends Classe>`)
Esse tipo é usado quando o método ou classe precisa garantir que o tipo genérico implemente ou estenda determinada classe ou interface:

```java
public class Comparador<T extends Comparable<T>> {
    public T maximo(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
}
```

## 5.2 Multiple Bounds
Um tipo genérico pode ser limitado por várias interfaces:

```java
public <T extends Number & Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) > 0 ? a : b;
}
```

## 5.3 Lower Bounds com `super`
Permite adicionar itens a coleções com supertipos:

```java
public void adicionarValores(List<? super Integer> lista) {
    lista.add(1);
    lista.add(2);
}
```

---

# 6. Generics com Coleções

As coleções da Java Collections Framework são baseadas em generics.

## 6.1 `List<T>`, `Set<T>`, `Map<K,V>`
Permitem definir que tipo de elementos ou pares chave-valor serão usados:

```java
List<String> nomes = new ArrayList<>();
Set<Integer> numeros = new HashSet<>();
Map<String, Double> notas = new HashMap<>();
```

## 6.2 Iteração segura e uso com lambdas
```java
nomes.forEach(nome -> System.out.println(nome));
```

O uso de generics permite que o compilador saiba o tipo esperado e ofereça autocompletar, além de proteger contra erros.

---

# 7. Generics em Bibliotecas e Frameworks

## 7.1 Spring
Spring Data usa generics para repositórios:
```java
public interface JpaRepository<T, ID> extends Repository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
}
```

## 7.2 JPA / Hibernate
Permite o uso de DAO genérico:
```java
public abstract class GenericDao<T> {
    public void salvar(T entidade) { /* ... */ }
}
```

## 7.3 Gson e Jackson
Serialização e desserialização de objetos:
```java
Type type = new TypeToken<List<Produto>>(){}.getType();
List<Produto> produtos = gson.fromJson(json, type);
```

---

# 8. Boas Práticas com Generics

## 8.1 Evitar casting
Sempre que possível, use generics em vez de castings explícitos:
```java
List<String> lista = new ArrayList<>();
String s = lista.get(0); // sem cast
```

## 8.2 Design de APIs
Declare métodos e interfaces com generics para reutilização:
```java
<T> T primeiroElemento(List<T> lista) {
    return lista.get(0);
}
```

## 8.3 Nomeclatura padrão
Use `T`, `E`, `K`, `V`, `N`, `S` conforme convenções:
- `T`: Type
- `E`: Element
- `K`: Key
- `V`: Value

---

# 9. Limitações dos Generics em Java

## 9.1 Type Erasure
O Java usa apagamento de tipo em tempo de compilação. Isso significa que a informação do tipo genérico é **removida** após a compilação:
```java
List<String> lista1 = new ArrayList<>();
List<Integer> lista2 = new ArrayList<>();
System.out.println(lista1.getClass() == lista2.getClass()); // true
```

## 9.2 Não é possível usar `new T()`
Você não pode instanciar diretamente um tipo genérico:
```java
// T obj = new T(); // erro de compilação
```

## 9.3 Incompatibilidade com arrays
Generics e arrays não se misturam bem:
```java
List<String>[] listas = new ArrayList<String>[10]; // erro de compilação
```

---

# 10. Estudos de Caso

## 10.1 Builder Genérico
```java
public class Builder<T> {
    private T instancia;
    public Builder(T obj) { this.instancia = obj; }
    public T build() { return instancia; }
}
```

## 10.2 Repositório Genérico
```java
public interface Repositorio<T> {
    void salvar(T entidade);
    T buscarPorId(int id);
}
```

## 10.3 Conversor de Tipo
```java
public interface Conversor<S, T> {
    T converter(S origem);
}
```

---

# 11. Exercícios Práticos

Os exercícios a seguir visam fixar os conceitos principais abordados nesta aula, envolvendo criação de classes genéricas, uso de wildcards, manipulação de coleções com generics, e desenvolvimento de estruturas genéricas mais elaboradas.

### Exercício 1 – Classe genérica `Caixa<T>`
**Objetivo:** Praticar a criação de uma classe genérica simples com métodos de acesso ao tipo parametrizado.

**Descrição:**
Implemente uma classe chamada `Caixa<T>` que armazene um único objeto de qualquer tipo. A classe deve conter:
- Um método `void guardar(T item)` para armazenar o item.
- Um método `T abrir()` que retorna o item guardado.

**Dica:** Teste com `Caixa<String>`, `Caixa<Integer>` e `Caixa<Usuario>`.

### Exercício 2 – Método genérico `maiorElemento`
**Objetivo:** Criar um método genérico com bounded types (`<T extends Comparable<T>>`).

**Descrição:**
Implemente um método que receba uma lista de elementos que implementam `Comparable<T>` e retorne o maior elemento presente:
```java
public static <T extends Comparable<T>> T maiorElemento(List<T> lista)
```

Teste com listas de `Integer`, `Double` e `String`.

### Exercício 3 – Interface `Conversor<S, T>`
**Objetivo:** Usar múltiplos parâmetros genéricos em uma interface funcional.

**Descrição:**
Crie uma interface genérica chamada `Conversor<S, T>` com o método:
```java
T converter(S origem);
```
Depois, implemente:
- Um conversor de `String` para `Integer`.
- Um conversor de `Integer` para `Double`.

Utilize lambdas quando possível.

### Exercício 4 – Uso de `List<? super Integer>`
**Objetivo:** Compreender o uso de wildcards com limite inferior (`? super`).

**Descrição:**
Crie um método chamado `adicionarNumeros` que receba uma `List<? super Integer>` e adicione os números de 1 a 5 nessa lista.

Teste com:
- `List<Number>`
- `List<Object>`

**Importante:** Verifique o tipo de retorno de `get()` e justifique por que `Integer i = lista.get(0)` pode não funcionar.

---

# 12. Referências

- Java™ Tutorials: https://docs.oracle.com/javase/tutorial/java/generics/
- Effective Java – Joshua Bloch
- Java Generics and Collections – Naftalin & Wadler
- Baeldung: https://www.baeldung.com/java-generics