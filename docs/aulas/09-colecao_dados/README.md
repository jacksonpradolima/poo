# Coleções de Dados em Java

1. Introdução às Coleções
    - Analogia intuitiva
2. Interfaces e Implementações
    - List
    - Set
    - Map
    - Queue e Deque
3. Iteração sobre Coleções
    - Iterator
    - For-each
   - Expressões Lambda e Streams
4. Complexidade de Algoritmos nas Coleções
   - Introdução à Notação Big-O
   - Commplexidade das Estruturas
   - Casos Específicos e Comparações
   - Conclusão da Seção
5. Estruturas Adicionais
   - PriorityQueue
   - Deque
   - Stack (estrutura clássica)
   - Comparação final
6. Ordenação de Coleções
   - Usando `Collections.sort`
   - Interface `Comparator`
   - Interface `Comparable`
   - Ordenações compostas e aninhadas
   - Aplicações Práticas
   - Boas Práticas
7. Estudos de Caso
   - Cadastro de usuários ordenado por nome
   - Filtros de dados com `Predicate`
   - Agrupamento por tipo em `Map`
8. Integração com Generics
9. Tratamento de Exceções com Coleções
10. Exercícios Práticos
11. Referências

---

# 1. Introdução às Coleções

As coleções representam estruturas de dados que armazenam grupos de elementos. Em Java, o pacote `java.util` oferece uma rica biblioteca de classes e interfaces para representar conjuntos, listas, mapas, filas e estruturas ordenadas.

Coleções facilitam a manipulação de dados e oferecem performance otimizada para tarefas como:
- Armazenamento sequencial ou aleatório.
- Busca e filtragem de dados.
- Inserções, remoções e atualizações.
- Ordenação personalizada.

## Analogia intuitiva
Imagine uma **estante de livros**:
- Uma `List` seria como uma fila de livros organizados por posição.
- Um `Set` seria como um armário onde não pode haver livros repetidos.
- Um `Map` seria como um dicionário com chave (palavra) e valor (definição).

As coleções promovem flexibilidade e consistência no armazenamento e processamento de dados.

---

# 2. Interfaces e Implementações

A biblioteca de coleções é composta por interfaces fundamentais e suas múltiplas implementações. A escolha correta da estrutura depende dos requisitos de ordenação, duplicidade, desempenho e acesso.

## 2.1 List
`List` é uma coleção ordenada que permite elementos duplicados e acesso por índice.

### Implementações principais:
- `ArrayList` (baseada em array, acesso rápido por índice)
- `LinkedList` (baseada em ponteiros, rápida em inserções/remoções nas extremidades)

### Exemplo:
```java
List<String> nomes = new ArrayList<>();
nomes.add("Ana");
nomes.add("João");
System.out.println(nomes.get(1)); // João
```

## 2.2 Set
`Set` é uma coleção que **não permite duplicatas**.

### Implementações:
- `HashSet`: não garante ordem.
- `LinkedHashSet`: mantém ordem de inserção.
- `TreeSet`: ordenado de forma natural ou por `Comparator`.

### Exemplo:
```java
Set<String> conjunto = new HashSet<>();
conjunto.add("maçã");
conjunto.add("maçã"); // ignorado
System.out.println(conjunto.size()); // 1
```

## 2.3 Map
Estrutura que mapeia **chaves** para **valores**. Não permite chaves duplicadas.

### Implementações:
- `HashMap`: acesso rápido, sem ordem.
- `LinkedHashMap`: mantém ordem de inserção.
- `TreeMap`: ordenado pelas chaves.

### Exemplo:
```java
Map<String, Integer> estoque = new HashMap<>();
estoque.put("caneta", 10);
estoque.put("caderno", 5);
System.out.println(estoque.get("caneta")); // 10
```

## 2.4 Queue e Deque
- `Queue`: estrutura FIFO (First-In, First-Out).
- `Deque`: fila dupla (pode inserir/remover dos dois lados).

### Exemplo:
```java
Queue<String> fila = new LinkedList<>();
fila.add("primeiro");
fila.add("segundo");
System.out.println(fila.poll()); // "primeiro"
```

Deque é útil em algoritmos como buscas em grafos e estruturas como stacks (pilhas).

---

# 3. Iteração sobre Coleções

Iterar sobre coleções é uma das atividades mais comuns ao manipular dados em Java. A linguagem oferece diversas formas para percorrer listas, conjuntos, mapas e outras estruturas de dados de forma eficiente, segura e legível.

## 3.1 Iterator
A interface `Iterator` oferece um mecanismo padrão para percorrer elementos de uma coleção sequencialmente.

### Exemplo:
```java
List<String> nomes = Arrays.asList("Ana", "Bruno", "Carlos");
Iterator<String> it = nomes.iterator();
while (it.hasNext()) {
    String nome = it.next();
    System.out.println(nome);
}
```

O iterador é útil para remover elementos durante a iteração sem causar `ConcurrentModificationException`.

### Remoção segura:
```java
while (it.hasNext()) {
    String nome = it.next();
    if (nome.startsWith("C")) {
        it.remove();
    }
}
```

## 3.2 For-each (enhanced-for)
Desde o Java 5, a sintaxe do `for-each` tornou o código mais conciso:

```java
for (String nome : nomes) {
    System.out.println(nome);
}
```

Ideal para leitura quando não é necessário remover elementos.

## 3.3 Expressões Lambda e Streams
Desde o Java 8, coleções podem ser percorridas com expressões lambda:

```java
nomes.forEach(nome -> System.out.println(nome));
```

### Stream com filtros:
```java
nomes.stream()
     .filter(n -> n.length() > 4)
     .forEach(System.out::println);
```

### Mapas:
```java
Map<String, Integer> estoque = new HashMap<>();
estoque.put("caneta", 10);
estoque.put("caderno", 5);

estoque.forEach((chave, valor) -> System.out.println(chave + ": " + valor));
```

Iterações modernas combinam clareza com alto desempenho e são preferidas em contextos de leitura, agregação e filtragem de dados.

---

# 4. Complexidade de Algoritmos nas Coleções

Ao trabalhar com coleções em Java, entender como **operações básicas escalam com o tamanho da entrada** é essencial para escrever código eficiente. Para isso, usamos a **notação Big-O**, que nos ajuda a prever o comportamento de algoritmos conforme a quantidade de dados aumenta — sem depender da máquina onde são executados.

---

## 4.1 Fundamentos da Notação Big-O

A **notação Big-O** expressa o crescimento do tempo ou memória em função do tamanho da entrada (`n`), focando no **comportamento assintótico** (ou seja, quando `n` cresce muito). Isso é particularmente útil ao projetar aplicações que precisam escalar com segurança.

### Por que Big-O é importante?

* **Evita gargalos**: permite prever operações que podem se tornar lentas com muitos dados.
* **Ajuda a escolher estruturas ideais**: como `HashSet`, `TreeMap`, `ArrayList`, etc.
* **Facilita comparação entre algoritmos**: mesmo entre linguagens diferentes.

---

## 4.2 Regras Gerais para Analisar Complexidade

### 1. **Sequência de comandos**

Soma-se as complexidades, mas considera-se apenas o **termo dominante**.

```java
for (int i = 0; i < n; i++) {}  // O(n)
for (int j = 0; j < n; j++) {}  // O(n)
// Total: O(n + n) → O(n)
```

### 2. **Comandos aninhados**

Multiplica-se as complexidades internas:

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {}
}
// Total: O(n * n) → O(n²)
```

### 3. **Recursão**

Avalia-se a profundidade e o custo de cada chamada:

```java
void divide(int n) {
    if (n <= 1) return;
    divide(n / 2);
}
// Total: O(log n)
```

---

## 4.3 Tabela de Complexidades Comuns

| Notação      | Nome               | Descrição                                                                              | Exemplo de Aplicação                                          |
| ------------ | ------------------ | -------------------------------------------------------------------------------------- | ------------------------------------------------------------- |
| `O(1)`       | Constante          | O tempo de execução não varia com o tamanho da entrada. Ideal para operações pontuais. | Acesso por índice em `ArrayList`, `HashMap.get(key)`          |
| `O(log n)`   | Logarítmica        | Cresce lentamente, reduzindo a entrada pela metade a cada passo.                       | Busca binária, operações em `TreeMap`/`TreeSet`               |
| `O(n)`       | Linear             | Tempo cresce proporcionalmente ao número de elementos da coleção.                      | Iteração em `List`, `LinkedList`, `Set`                       |
| `O(n log n)` | Linear-Logarítmica | Combina divisão e varredura linear. Comum em algoritmos de ordenação eficientes.       | Merge Sort, Quick Sort, `Collections.sort()`                  |
| `O(n²)`      | Quadrática         | O tempo cresce rapidamente; comum em loops aninhados e comparações entre pares.        | Bubble Sort, Selection Sort, comparação de todos contra todos |
| `O(2ⁿ)`      | Exponencial        | Tempo dobra a cada novo elemento. Viável apenas para entradas pequenas.                | Geração de subconjuntos, resolução por backtracking           |
| `O(n!)`      | Fatorial           | Tempo cresce de forma combinatorial. Inviável para grandes entradas.                   | Permutações, problema do Caixeiro Viajante                    |

---

## 4.4 Complexidade nas Estruturas de Coleção

| Estrutura    | Acesso | Busca    | Inserção      | Remoção  | Comentários                         |
| ------------ | ------ | -------- | ------------- | -------- | ----------------------------------- |
| `ArrayList`  | O(1)   | O(n)     | O(1) / O(n)\* | O(n)     | Rápido no fim; lento no meio/início |
| `LinkedList` | O(n)   | O(n)     | O(1)⁑         | O(1)⁑    | Rápido nas extremidades             |
| `HashSet`    | —      | O(1)†    | O(1)†         | O(1)†    | Depende de função hash              |
| `TreeSet`    | —      | O(log n) | O(log n)      | O(log n) | Mantém ordenação                    |
| `HashMap`    | —      | O(1)†    | O(1)†         | O(1)†    | Associativo, rápido                 |
| `TreeMap`    | —      | O(log n) | O(log n)      | O(log n) | Ordenação por chave                 |

`*` Inserção no meio pode causar deslocamentos.
`⁑` Inserção/remoção nas extremidades.
`†` Tempo médio; com colisões, pode degradar para O(n).

---

## 4.5 Casos Específicos de Complexidade

### Inserção ordenada com `ArrayList`:

```java
int pos = Collections.binarySearch(lista, valor); // O(log n)
lista.add(pos, valor); // O(n)
```

### Busca por chave em `HashMap`:

```java
Integer valor = mapa.get("id"); // O(1) em média
```

### Inserção ordenada em `TreeSet`:

```java
set.add(elemento); // O(log n)
```

---

## 4.6 Complexidades Mais Altas

### `O(2ⁿ)` – Algoritmos Exponenciais

Usado em problemas que avaliam **todas as combinações possíveis**:

```java
void subconjuntos(String s, String atual, int i) {
    if (i == s.length()) {
        System.out.println(atual);
        return;
    }
    subconjuntos(s, atual + s.charAt(i), i + 1); // incluir caractere
    subconjuntos(s, atual, i + 1);               // excluir caractere
}
// Para uma string de tamanho n, gera 2ⁿ subconjuntos.
```

### `O(n!)` – Algoritmos Fatoriais

Usado para **gerar permutações**:

```java
void permutar(List<Integer> lista, int i) {
    if (i == lista.size()) {
        System.out.println(lista);
        return;
    }
    for (int j = i; j < lista.size(); j++) {
        Collections.swap(lista, i, j);
        permutar(lista, i + 1);
        Collections.swap(lista, i, j);  // desfazer troca
    }
}
// Para uma lista de tamanho n, gera n! permutações.

```

> ⚠️ **Evite algoritmos exponenciais e fatoriais para entradas grandes.** Use heurísticas, aproximações ou algoritmos especializados.

---

## 4.7 Resumo Visual: Complexidades em Coleções

| Operação          | `ArrayList` | `LinkedList` | `HashMap` | `TreeMap` | `HashSet` | `TreeSet` |
| ----------------- | ----------- | ------------ | --------- | --------- | --------- | --------- |
| Inserção no fim   | O(1)        | O(1)⁑        | O(1)†     | O(log n)  | O(1)†     | O(log n)  |
| Inserção ordenada | O(n)        | O(n)         | —         | O(log n)  | —         | O(log n)  |
| Acesso por índice | O(1)        | O(n)         | —         | —         | —         | —         |
| Busca por valor   | O(n)        | O(n)         | O(1)†     | O(log n)  | O(1)†     | O(log n)  |
| Remoção           | O(n)        | O(1)⁑        | O(1)†     | O(log n)  | O(1)†     | O(log n)  |

---

## 4.8 Conclusão

A **eficiência de um algoritmo** está diretamente ligada à **estrutura de dados escolhida**. A seguir algumas recomendações práticas:

✅ Use `HashMap`/`HashSet` para buscas rápidas por chave ou valor.
✅ Use `TreeMap`/`TreeSet` quando a ordenação for essencial.
✅ Evite `LinkedList` para acessos aleatórios.
✅ Evite algoritmos com `O(n²)` ou pior sempre que possível.

> ✨ Escolher bem sua estrutura de dados **não é micro-otimização**, é design inteligente.

---

# 5. Estruturas Adicionais

Nesta seção, exploraremos estruturas de dados complementares oferecidas pela Java Collections Framework. Embora `List`, `Set` e `Map` sejam os pilares principais, muitas aplicações reais exigem comportamentos como priorização de elementos, acesso duplo em filas ou empilhamento e desempilhamento rápido. Para isso, contamos com estruturas como `Queue`, `Deque`, `PriorityQueue` e `Stack`.

## 5.1 PriorityQueue

### O que é?
A `PriorityQueue` é uma fila com base em **prioridades**, onde o elemento de menor prioridade (ou maior, dependendo do comparador) sempre será o primeiro a ser removido. Internamente, utiliza um **heap binário mínimo**, oferecendo complexidade O(log n) para inserção e remoção.

### Características:
- **Ordenação natural** por padrão, ou personalizada via `Comparator`.
- Não permite `null`.
- Permite elementos duplicados.

### Exemplo:
```java
PriorityQueue<Integer> fila = new PriorityQueue<>();
fila.add(10);
fila.add(1);
fila.add(5);

while (!fila.isEmpty()) {
    System.out.println(fila.poll()); // imprime 1, 5, 10
}
```

### Exemplo com Classe Personalizada:
```java
class Tarefa {
    String nome;
    int prioridade;

    public Tarefa(String nome, int prioridade) {
        this.nome = nome;
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return nome + " (Prioridade: " + prioridade + ")";
    }
}

PriorityQueue<Tarefa> tarefas = new PriorityQueue<>(Comparator.comparingInt(t -> t.prioridade));
tarefas.add(new Tarefa("Limpar", 3));
tarefas.add(new Tarefa("Enviar relatório", 1));
tarefas.add(new Tarefa("Responder e-mails", 2));

while (!tarefas.isEmpty()) {
    System.out.println(tarefas.poll());
}
// Saída ordenada por prioridade:
// Enviar relatório (Prioridade: 1)
// Responder e-mails (Prioridade: 2)
// Limpar (Prioridade: 3)
```

### Aplicações:
- Filas de impressão (prioridade por urgência).
- Sistemas de agendamento.
- Algoritmos de caminho mínimo (ex.: Dijkstra).

## 5.2 Deque (Double Ended Queue)

### O que é?
`Deque` permite inserção e remoção de elementos **em ambas as extremidades** da fila. Ideal para aplicações onde a ordem pode ser manipulada mais dinamicamente, funcionando tanto como `Queue` quanto como `Stack`.

### Implementações:
- `ArrayDeque`: implementação mais comum, rápida e sem limite de tamanho.

### Exemplo:
```java
Deque<String> deque = new ArrayDeque<>();
deque.addFirst("a"); // [a]
deque.addLast("b");  // [a, b]
System.out.println(deque.removeLast()); // imprime: b
```

### Aplicações:
- Algoritmos que exigem acesso dos dois lados (ex.: sliding window).
- Backtracking.
- LRU Cache.

## 5.3 Stack (Pilha)

### O que é?
`Stack` representa uma **pilha**, ou seja, uma estrutura LIFO (Last-In, First-Out). Embora exista uma classe `Stack` em Java (herança de `Vector`), recomenda-se usar `Deque` via `ArrayDeque` por ser mais eficiente e moderna.

### Operações principais:
- `push(E item)`: adiciona no topo.
- `pop()`: remove e retorna o item do topo.
- `peek()`: retorna o topo sem remover.

### Exemplo com Deque:
```java
Deque<Integer> pilha = new ArrayDeque<>();
pilha.push(1);
pilha.push(2);
System.out.println(pilha.pop()); // imprime: 2
```

### Aplicações:
- Avaliação de expressões matemáticas (ex.: notação polonesa).
- Desfazer/refazer (Undo/Redo).
- Simulação de chamadas recursivas.

## Comparativo final:

| Estrutura       | Ordem              | Inserção | Remoção | Acesso Direto |
|-----------------|---------------------|----------|---------|----------------|
| PriorityQueue   | Prioridade (heap)   | O(log n) | O(log n)| Não suportado  |
| Deque           | Fila dupla          | O(1)     | O(1)    | Não recomendado|
| Stack (Deque)   | LIFO (último entra) | O(1)     | O(1)    | Não recomendado|

Essas estruturas ampliam significativamente a capacidade do desenvolvedor em construir soluções eficientes e orientadas ao contexto da aplicação. Escolher corretamente entre elas pode ser decisivo na escalabilidade e clareza do sistema.

---

# 6. Ordenação de Coleções

Ordenar coleções é uma prática recorrente no desenvolvimento de software. Em Java, isso pode ser feito de maneira flexível, utilizando tanto ordenações naturais (via `Comparable`) quanto personalizadas (via `Comparator`). Com esses recursos, conseguimos ordenar dados complexos como listas de objetos por múltiplos critérios, de forma crescente ou decrescente.

## 6.1 Usando `Collections.sort`
O método `Collections.sort(List<T>)` é a forma mais direta de ordenar uma lista. Ele modifica a lista original e depende de a classe dos elementos implementarem a interface `Comparable`.

### Exemplo com ordem natural (Strings):
```java
List<String> nomes = Arrays.asList("Carlos", "Ana", "Bruno");
Collections.sort(nomes);
System.out.println(nomes); // [Ana, Bruno, Carlos]
```

Se a lista contiver tipos que não implementam `Comparable`, o método lançará uma exceção em tempo de execução.

## 6.2 Interface `Comparable`
A interface `Comparable<T>` define a ordem natural dos objetos de uma classe. Ao sobrescrever o método `compareTo`, você define a lógica de comparação para ordenações padrão.

### Exemplo:
```java
class Produto implements Comparable<Produto> {
    String nome;
    double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public int compareTo(Produto outro) {
        return Double.compare(this.preco, outro.preco);
    }
}

List<Produto> produtos = Arrays.asList(
    new Produto("Caneta", 2.5),
    new Produto("Lápis", 1.0),
    new Produto("Borracha", 1.5)
);

Collections.sort(produtos);
```

## 6.3 Interface `Comparator`
A interface `Comparator<T>` permite criar **regras de ordenação personalizadas** sem modificar a classe original. É ideal quando se precisa de múltiplas formas de ordenação para a mesma entidade.

### Exemplo:
```java
List<Produto> produtos = Arrays.asList(
    new Produto("Caneta", 2.5),
    new Produto("Lápis", 1.0),
    new Produto("Borracha", 1.5)
);

// Ordenar por nome
Collections.sort(produtos, Comparator.comparing(p -> p.nome));
```

### Ordenação reversa:
```java
Comparator<Produto> precoDesc = Comparator.comparing(Produto::getPreco).reversed();
produtos.sort(precoDesc);
```

## 6.4 Ordenações Compostas e Aninhadas
Com a API de `Comparator`, é possível construir ordenações baseadas em múltiplos critérios:

```java
Comparator<Produto> ordemComposta = Comparator
    .comparingDouble(Produto::getPreco)
    .thenComparing(Produto::getNome);

produtos.sort(ordemComposta);
```

Essa abordagem é ideal para relatórios e interfaces gráficas onde múltiplas colunas são utilizadas para classificar os dados.

## Aplicações práticas:
- Ordenar listas de usuários por nome e data de cadastro.
- Classificar transações por valor e tipo.
- Implementar ranking de pontuações.

## Boas práticas:
- Utilize `Comparator` para manter flexibilidade.
- Use `Comparator.comparing()` e `thenComparing()` para código mais legível.
- Prefira lambdas e métodos de referência ao invés de classes anônimas.

Essa seção estabelece a base para manipulações complexas com coleções ordenadas e prepara para casos mais práticos na próxima seção de estudos de caso.

---

# 7. Estudos de Caso

Nesta seção, exploraremos cenários práticos e realistas em que as coleções são aplicadas para resolver problemas comuns. Cada estudo de caso será apresentado com uma descrição do problema, a solução utilizando coleções, exemplos de código e observações de boas práticas.

## 7.1 Cadastro de usuários ordenado por nome
### Contexto
Deseja-se armazenar e ordenar uma lista de usuários para apresentação em um relatório alfabético.

### Solução
Utilizar uma `List<Usuario>` com `Comparable<Usuario>` ou `Comparator<Usuario>`.

### Código:
```java
class Usuario implements Comparable<Usuario> {
    String nome;
    int idade;

    public Usuario(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public int compareTo(Usuario outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public String toString() {
        return nome + " - " + idade + " anos";
    }
}

List<Usuario> usuarios = new ArrayList<>();
usuarios.add(new Usuario("Carlos", 30));
usuarios.add(new Usuario("Ana", 25));
usuarios.add(new Usuario("Bruno", 28));

Collections.sort(usuarios);
usuarios.forEach(System.out::println);
```

## 7.2 Filtros de dados com `Predicate`
### Contexto
A partir de uma lista de produtos, retornar apenas aqueles com preço abaixo de um valor.

### Solução
Uso de `Stream` e `Predicate` com coleções.

### Código:
```java
class Produto {
    String nome;
    double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return nome + " - R$" + preco;
    }
}

List<Produto> produtos = List.of(
    new Produto("Caneta", 2.5),
    new Produto("Lápis", 1.0),
    new Produto("Caderno", 12.0)
);

produtos.stream()
    .filter(p -> p.preco < 5.0)
    .forEach(System.out::println);
```

## 7.3 Agrupamento por tipo em `Map`
### Contexto
Agrupar livros por categoria utilizando `Map<String, List<Livro>>`.

### Código:
```java
class Livro {
    String titulo;
    String categoria;

    public Livro(String titulo, String categoria) {
        this.titulo = titulo;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return titulo;
    }
}

List<Livro> livros = List.of(
    new Livro("Java Essencial", "Programação"),
    new Livro("Clean Code", "Programação"),
    new Livro("Dom Quixote", "Literatura")
);

Map<String, List<Livro>> agrupados = new HashMap<>();
for (Livro livro : livros) {
    agrupados.computeIfAbsent(livro.categoria, k -> new ArrayList<>()).add(livro);
}

agrupados.forEach((categoria, lista) -> {
    System.out.println("Categoria: " + categoria);
    lista.forEach(System.out::println);
});
```

---

# 8. Integração com Generics

As coleções em Java foram projetadas com **generics** para promover segurança de tipo e evitar casting em tempo de execução. Isso significa que, ao utilizar coleções como `List<T>`, `Set<T>` ou `Map<K,V>`, o compilador pode detectar tipos incorretos antes mesmo da aplicação rodar.

### Exemplo sem generics (Java 1.4):
```java
List lista = new ArrayList();
lista.add("texto");
String valor = (String) lista.get(0); // necessário cast
```

### Exemplo com generics (Java 5+):
```java
List<String> lista = new ArrayList<>();
lista.add("texto");
String valor = lista.get(0); // sem cast, seguro
```

### Vantagens:
- Evita `ClassCastException`.
- Permite autocompletar com IDEs.
- Facilita manutenção e legibilidade.

Coleções com generics devem ser a escolha padrão em qualquer código moderno em Java.

---

# 9. Tratamento de Exceções com Coleções

Embora o uso de coleções raramente envolva o lançamento explícito de exceções, diversos cenários podem exigir controle e proteção contra falhas:

## 9.1 Exceções comuns
- `IndexOutOfBoundsException`: acessar índice inexistente em uma lista.
- `NullPointerException`: acessar método em objeto nulo dentro de coleção.
- `ClassCastException`: casting inválido ao recuperar objetos.
- `ConcurrentModificationException`: modificar coleção durante iteração com `for-each`.

## 9.2 Como evitar
- Verifique sempre limites de índices antes de acessar.
- Utilize `Optional` ou `containsKey/containsValue` com mapas.
- Prefira `Iterator` com `remove()` ao modificar coleções durante a iteração.

### Exemplo:
```java
List<String> nomes = new ArrayList<>();
nomes.add("Ana");
nomes.add("Carlos");

try {
    System.out.println(nomes.get(5));
} catch (IndexOutOfBoundsException e) {
    System.out.println("Índice inválido!");
}
```

---

# 10. Exercícios Práticos

### Exercício 1: Cadastro de Produtos
Crie uma lista de produtos (`nome`, `preço`) e ordene por preço utilizando `Comparable`. Em seguida, filtre os produtos abaixo de R$10,00 usando stream.

### Exercício 2: Agrupamento de Estudantes
Crie uma classe `Estudante` com `nome`, `curso`. Agrupe estudantes por curso em um `Map<String, List<Estudante>>`.

### Exercício 3: Fila de Atendimento
Simule uma fila de atendimento utilizando `PriorityQueue`. Cada `Cliente` tem `nome` e `prioridade`. Atenda os clientes pela ordem de prioridade.

### Exercício 4: Pilha de Navegação
Implemente uma pilha de páginas navegadas (`Stack` ou `Deque`). Adicione e remova páginas conforme o usuário navega e volta.

---

# 11. Referências

- Oracle Java Collections Documentation: https://docs.oracle.com/javase/tutorial/collections/index.html
- Effective Java – Joshua Bloch
- Java Generics and Collections – Maurice Naftalin & Philip Wadler
- Baeldung Java Collections Guide: https://www.baeldung.com/java-collections

---

