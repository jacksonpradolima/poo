# SUMÁRIO GERAL

1. [Introdução ao Conceito de Herança](#cap1)  
   1.1. Motivação Histórica e Conceitual  
   1.2. Breve Comparação com Outros Paradigmas  
   1.3. Herança como Reuso de Código vs. Generalização/Especialização  

2. [Superclasses e Subclasses: Definições e Exemplos Simples](#cap2)  
   2.1. O que é uma Superclasse?  
   2.2. O que é uma Subclasse?  
   2.3. Representação em Java com a Palavra-chave `extends`  
   2.4. Hierarquias Simples (Exemplos Didáticos)  

3. [Uso do `extends` em Java](#cap3)  
   3.1. Sintaxe Geral  
   3.2. Acesso a Membros da Superclasse  
   3.3. Construtores e Fluxo de Inicialização (Chamada Automática do Construtor Padrão da Superclasse)  

4. [Palavra-chave `super`](#cap4)  
   4.1. Motivação e Utilização  
   4.2. Acessando Construtores da Superclasse  
   4.3. Invocando Métodos e Atributos Sobrescritos  
   4.4. Cuidados e Boas Práticas  

5. [Hierarquias Simples e Sobrescrita de Métodos (Override)](#cap5)  
   5.1. Definição de Sobrescrita (Overriding)  
   5.2. `@Override`: Papel e Benefícios  
   5.3. Exemplos Clássicos (e.g. `toString()`, `equals()`)  
   5.4. Comparando Sobrescrita vs. Sobrecarga (Overload)  

6. [Benefícios da Herança](#cap6)  
   6.1. Reuso de Código e Redução de Duplicação  
   6.2. Estrutura Natural de Generalização/Especialização  
   <!-- 6.3. Polimorfismo em Ação (Visão Rápida)   -->
   6.4. Facilita Extensibilidade (Adição de Novas Funcionalidades em Subclasses)  

7. [Riscos, Fragilidade e “Heresy” da Herança](#cap7)  
   7.1. O Problema da Fragilidade da Hierarquia  
   7.2. Overriding Inadequado: Efeitos Colaterais e Quebra de Contratos  
   7.3. Liskov Substitution Principle (LSP) e Quebra de Polimorfismo  
   7.4. Quando Evitar Herança e Preferir Composição  

8. [Exemplos Conceituais e Estudos de Caso](#cap8)  
   8.1. Hierarquia “Animal”: Subclasses “Mamifero”, “Ave” e “Peixe”  
   8.2. Hierarquia “FormaGeometrica”: Subclasses “Retangulo”, “Circulo”  
   8.3. Hierarquia “Funcionario”: Subclasses “FuncionarioHorista”, “FuncionarioMensalista”  
   8.4. Modelagem de Exemplos com `super` e Sobrescrita de Métodos  

9. [Discussão de Boas Práticas](#cap9)  
   9.1. Evitando Hierarquias Excessivamente Profundas  
   9.2. Final Classes e Final Methods: Limitações Intencionais  
   9.3. Múltiplos Níveis de Herança vs. Interfaces  
   9.4. Design Patterns que Minimizam Abusos de Herança (Strategy, Decorator)  

10. [Exercícios Propostos](#cap10)  
   10.1. Exercícios Simples  
   10.2. Exercícios Intermediários  
   
11. [Referências, Links e Leituras Adicionais](#cap11)

---

## 1. INTRODUÇÃO AO CONCEITO DE HERANÇA

### 1.1. Motivação Histórica e Conceitual
A herança é um dos princípios fundamentais da Programação Orientada a Objetos (POO) desde as primeiras linguagens como **Simula** e **Smalltalk**. O objetivo original era **reduzir duplicação de código** e **facilitar a modelagem** de uma “árvore” de classes, onde conceitos mais gerais (superclasses) pudessem ser reutilizados por conceitos mais específicos (subclasses).

A herança permite que **subclasses** **herdem** atributos e métodos de uma classe ancestral, podendo **complementar** ou **sobrescrever** alguns comportamentos. Essa ideia se inspira na forma como classificamos seres vivos, objetos e categorias no mundo real, partindo do geral para o específico.

### 1.2. Breve Comparação com Outros Paradigmas
- **Programação Estruturada**: não há ideia de agrupar dados e comportamentos em hierarquias. O reuso de código ocorre por meio de funções e sub-rotinas.  
- **Programação Genérica**: O reuso ocorre via templates ou generics, mas não há conceitualmente super e subclasses.  
- **POO**: Agrupamos atributos e métodos numa classe e, a partir dela, podemos criar classes derivadas que herdam esses atributos/métodos.  

### 1.3. Herança como Reuso de Código vs. Generalização/Especialização
- **Reuso de Código**: Em linguagens como Java, herança foi amplamente usada no início para compartilhar implementações, mas também trouxe problemas (como forte acoplamento e complexidade).  
- **Generalização/Especialização**: A forma mais conceitual de enxergar herança. Cria-se uma classe geral (“Animal”) e a especializa em subclasses (“Gato”, “Cachorro”). Cada subclasse ganha os atributos e métodos de “Animal” e adiciona comportamentos ou atributos específicos.

É importante notar que a herança não serve apenas para reuso de código, mas também para expressar **relacionamentos de tipo** (Is-a – ou “é um”). Falaremos sobre isso nos próximos tópicos.

---

## 2. SUPERCLASSES E SUBCLASSES: DEFINIÇÕES E EXEMPLOS SIMPLES

### 2.1. O que é uma Superclasse?
A **superclasse** (às vezes chamada de classe-pai, classe-base ou classe-mãe) é aquela que fornece atributos e métodos para suas subclasses herdarem. Ela representa, em geral, um conceito mais **genérico**.  

Exemplos:
- `Veiculo` pode ser superclasse de `Carro`, `Moto`, `Caminhao`.  
- `Pessoa` pode ser superclasse de `Aluno`, `Professor`, `Funcionario`.  
- `ContaBancaria` pode ser superclasse de `ContaCorrente`, `ContaPoupanca`.

### 2.2. O que é uma Subclasse?
A **subclasse** (também chamada de classe-filha, classe-derivada) é aquela que **“estende”** a superclasse, herdando todos os membros (atributos e métodos) não privados, e podendo sobrescrever métodos ou acrescentar novos. Ela representa um **conceito mais específico** ou “detalhado.”

Exemplos:
- `Carro extends Veiculo`  
- `Aluno extends Pessoa`  
- `ContaCorrente extends ContaBancaria`

### 2.3. Representação em Java com a Palavra-chave `extends`
Em Java, a herança é estabelecida de forma simples:
```java
public class Subclasse extends Superclasse {
    // ...
}
```
Para herdar de uma classe, basta usar a sintaxe `class Filha extends Mae`. A subclasse passa a ter acesso a:
- Atributos e métodos `protected` ou `public` da superclasse.  
- Atributos e métodos `private` da superclasse ficam inacessíveis diretamente (apenas via getters/setters ou métodos públicos).

### 2.4. Hierarquias Simples (Exemplos Didáticos)

**Exemplo 1**:
```java
public class Animal {
    public void emitirSom() {
        System.out.println("Som de animal genérico");
    }
}

public class Cachorro extends Animal {
    // Cachorro herda emitirSom()
    // e pode sobrescrever se quiser
}
```

**Exemplo 2**:
```java
public class ContaBancaria {
    protected double saldo;

    public void depositar(double valor) {
        saldo += valor;
    }
    public void sacar(double valor) {
        saldo -= valor;
    }
}

public class ContaPoupanca extends ContaBancaria {
    public void renderJuros() {
        saldo += saldo * 0.02; // Exemplo de juros mensais de 2%
    }
}
```
Nesta hierarquia simples, `ContaPoupanca` ganha `depositar()` e `sacar()` sem precisar reescrever.

---

## 3. USO DO `extends` EM JAVA

### 3.1. Sintaxe Geral
A declaração básica é:
```java
public class Subclasse extends Superclasse {
    // membros extras
}
```
Também é possível ter um modificador de acesso: `public`, `abstract`, `final`, etc. Se marcamos uma classe com `final`, ela **não** pode ser estendida.

### 3.2. Acesso a Membros da Superclasse
- Atributos e métodos com `public` ou `protected` são herdados e podem ser usados diretamente pela subclasse.  
- Membros `private` ficam encapsulados na superclasse. Para acessá-los, é preciso usar **métodos de acesso** (getters/setters) ou métodos públicos/protegidos que manipulem esses atributos.

### 3.3. Construtores e Fluxo de Inicialização (Chamada Automática do Construtor Padrão da Superclasse)
Em Java, quando instanciamos uma subclasse, é chamado primeiramente o **construtor** da superclasse (seja por chamada explícita `super()` ou implícita se não houver construtor definido). Se a superclasse não tiver um construtor padrão (sem parâmetros) e não chamarmos `super(...)`, teremos erro de compilação.

Exemplo:
```java
public class Pessoa {
    public Pessoa() {
        System.out.println("Construtor de Pessoa");
    }
}

public class Aluno extends Pessoa {
    public Aluno() {
        super(); // chama construtor de Pessoa
        System.out.println("Construtor de Aluno");
    }
}
```
Ao criar `new Aluno()`, a saída será:
```
Construtor de Pessoa
Construtor de Aluno
```

---

## 4. PALAVRA-CHAVE `super`

### 4.1. Motivação e Utilização
A palavra-chave `super` em Java é usada dentro de uma subclasse para se referir explicitamente a membros (métodos ou atributos) da superclasse, ou para chamar o construtor da superclasse.

### 4.2. Acessando Construtores da Superclasse
Quando a subclasse precisa chamar um construtor específico da superclasse, fazemos:
```java
public class Aluno extends Pessoa {
    public Aluno(String nome, int idade) {
        super(nome, idade); // chama construtor de Pessoa
        // inicializa atributos específicos de Aluno
    }
}
```
Sem essa chamada, Java tenta chamar `super()` (construtor sem parâmetros), o que pode falhar se a superclasse não o tiver.

### 4.3. Invocando Métodos e Atributos Sobrescritos
Se a subclasse sobrescreve um método e, em algum momento, quer usar a versão da superclasse, faz:
```java
@Override
public void emitirSom() {
    super.emitirSom();  // chama a versão de Animal
    System.out.println("Latido específico do Cachorro!");
}
```
Isso permite **estender** o comportamento herdado.

### 4.4. Cuidados e Boas Práticas
- Não é comum abusar de `super` para acessar atributos, pois geralmente preferimos `protected` ou getters.  
- O uso de `super` deve ser pontual, principalmente em construtores ou quando se quer complementar a lógica herdada.  
- Excesso de chamadas a `super` indica que possivelmente a hierarquia está complexa ou mal planejada.

---

## 5. HIERARQUIAS SIMPLES E SOBRESCRITA DE MÉTODOS (OVERRIDE)

### 5.1. Definição de Sobrescrita (Overriding)
**Sobrescrita de métodos** ocorre quando a subclasse **define novamente** um método que já existe na superclasse, mantendo a mesma **assinatura** (nome, parâmetros e tipo de retorno compatível). O método da subclasse substituirá o da superclasse quando for invocado por uma instância da subclasse.

### 5.2. `@Override`: Papel e Benefícios
Em Java, costumamos anotar métodos sobrescritos com `@Override` para:
- Sinalizar ao compilador que estamos sobrescrevendo um método.  
- Ajudar a detectar erros de digitação ou alteração de assinaturas.  
- Melhorar a leitura do código.

Exemplo:
```java
public class Animal {
    public void emitirSom() {
        System.out.println("Som genérico");
    }
}

public class Gato extends Animal {
    @Override
    public void emitirSom() {
        System.out.println("Miau, miau!");
    }
}
```
Se chamarmos:
```java
Animal a = new Gato();
a.emitirSom();  // "Miau, miau!"
```
É executada a versão de `Gato`.

### 5.3. Exemplos Clássicos (e.g. `toString()`, `equals()`)
Frequentemente, sobrescrevemos métodos herdados de `Object`, como:
- `toString()`: para retornar uma String representando o estado do objeto.  
- `equals(Object o)`: para comparar instâncias baseada em atributos.  
- `hashCode()`: para dar suporte a estruturas de dados baseadas em hash.

Exemplo:
```java
public class Pessoa {
    private String nome;
    private int idade;

    @Override
    public String toString() {
        return "Pessoa[nome=" + nome + ", idade=" + idade + "]";
    }
}
```

### 5.4. Comparando Sobrescrita vs. Sobrecarga (Overload)
- **Sobrescrita (Override)**: Mesma assinatura, classes diferentes (super e sub), polimorfismo.  
- **Sobrecarga (Overload)**: Mesmo método no **mesmo** escopo, mas assinaturas diferentes (quantidade ou tipos de parâmetros). Overload não é polimorfismo de subtipos, mas sim polimorfismo de métodos dentro da mesma classe.

---

## 6. BENEFÍCIOS DA HERANÇA

### 6.1. Reuso de Código e Redução de Duplicação
Um dos maiores atrativos da herança é evitar escrever métodos iguais em diversas classes. Podemos colocar métodos gerais na superclasse e reutilizá-los.

### 6.2. Estrutura Natural de Generalização/Especialização
A herança reflete bem a ideia de **“é um”** (IS-A). Exemplo: “Um `Cachorro` é um `Animal`.” Conceitualmente, é bastante intuitivo modelar seres e objetos usando a relação de herança.

### 6.3. Polimorfismo em Ação (Visão Rápida)
Com herança, podemos ter:
```java
Animal a = new Gato();
a.emitirSom();
```
Mesmo a referência sendo `Animal`, o método executado é o de `Gato` (se sobrescrito), mostrando polimorfismo. Assim, o código que “vê” apenas `Animal` não precisa se preocupar com qual subtipo está sendo usado em tempo de execução.

### 6.4. Facilita Extensibilidade (Adição de Novas Funcionalidades em Subclasses)
Ao criar um framework ou uma biblioteca, a herança é útil para permitir que desenvolvedores criem subclasses e ajustem comportamentos sem mexer na classe-base. Exemplo: classes de GUI (Swing, JavaFX) costumam ser estendidas para definir comportamento específico.

---

## 7. RISCOS, FRAGILIDADE E “HERESY” DA HERANÇA

### 7.1. O Problema da Fragilidade da Hierarquia
Chama-se **fragilidade da hierarquia** a dificuldade de se dar manutenção a uma superclasse sem afetar subclasses espalhadas pelo sistema. Se a superclasse muda de modo incompatível, a subclasse pode quebrar.  

Exemplo: se alteramos a forma como `ContaBancaria.sacar(double)` funciona (passando a cobrar uma tarifa extra), subclasses podem não estar preparadas e podem ter comportamentos inconsistentes.

### 7.2. Overriding Inadequado: Efeitos Colaterais e Quebra de Contratos
Subclasses podem sobrescrever métodos de maneira que **quebrem** suposições feitas na superclasse. Por exemplo, se a superclasse assumia que `saldo` nunca ficaria negativo, mas a subclasse sobrescreve `sacar()` para permitir saldo negativo, isso pode ocasionar bugs em outras partes do código que usam `ContaBancaria`.

### 7.3. Liskov Substitution Principle (LSP) e Quebra de Polimorfismo
O **Princípio de Substituição de Liskov** diz que, se `B` é subclasse de `A`, então devemos poder usar um objeto de `B` onde se espera um objeto de `A` **sem causar problemas**. Se a subclasse viola suposições da superclasse, o LSP é quebrado, gerando efeitos colaterais imprevisíveis.

### 7.4. Quando Evitar Herança e Preferir Composição
Uma regra de ouro é: **“Prefira composição a herança”** (do original *“Favor composition over inheritance”*). Em muitos casos, podemos inserir um objeto de outra classe como atributo (composição) ao invés de estender a classe. Isso reduz acoplamento, diminui a fragilidade da hierarquia e evita problemas de sobrescrita.

Exemplo:
- Ao invés de `class Piloto extends Carro` (o que não faz muito sentido), preferimos `class Piloto { private Carro carro; ... }`.

---

## 8. EXEMPLOS CONCEITUAIS E ESTUDOS DE CASO

### 8.1. Hierarquia “Animal”: Subclasses “Mamifero”, “Ave” e “Peixe”
- **Animal** define métodos genéricos como `respirar()`, `emitirSom()`.  
- **Mamifero** sobrescreve `emitirSom()` e pode ter métodos adicionais como `amamentar()`.  
- **Ave** sobrescreve `emitirSom()` e pode ter `voar()`.  
- **Peixe** pode ter `nadar()`, e `emitirSom()` pode ser inexistente (ou se não faz sentido, omite ou exibe “...”).

Código ilustrativo (simplificado):
```java
public class Animal {
    public void respirar() {
        System.out.println("Respirando...");
    }

    public void emitirSom() {
        System.out.println("Som genérico de animal");
    }
}

public class Mamifero extends Animal {
    @Override
    public void emitirSom() {
        System.out.println("Som de mamífero");
    }
    public void amamentar() {
        System.out.println("Amamentando filhote...");
    }
}
```

### 8.2. Hierarquia “FormaGeometrica”: Subclasses “Retangulo”, “Circulo”
- **FormaGeometrica** com método abstrato `calcularArea()`, se quisermos, ou poderia ser uma classe concreta com lógica parcial.  
- **Retangulo** e **Circulo** implementam `calcularArea()` cada um à sua maneira.

Exemplo:
```java
public abstract class FormaGeometrica {
    public abstract double calcularArea();
}

public class Retangulo extends FormaGeometrica {
    private double largura, altura;

    public Retangulo(double l, double a) {
        this.largura = l;
        this.altura = a;
    }

    @Override
    public double calcularArea() {
        return largura * altura;
    }
}
```
Essa é uma forma típica de usar **herança + polimorfismo** para representar objetos geométricos.

### 8.3. Hierarquia “Funcionario”: Subclasses “FuncionarioHorista”, “FuncionarioMensalista”
- **Funcionario** define atributos como `nome`, `salarioBase`.  
- **FuncionarioHorista** pode ter `valorHora` e `horasTrabalhadas`.  
- **FuncionarioMensalista** pode ter `salarioFixo`.  
- Ambos podem sobrescrever `calcularSalario()`.

```java
public class Funcionario {
    protected String nome;

    public double calcularSalario() {
        return 0.0;
    }
}

public class FuncionarioHorista extends Funcionario {
    private double valorHora;
    private int horasTrabalhadas;

    @Override
    public double calcularSalario() {
        return valorHora * horasTrabalhadas;
    }
}
```

### 8.4. Modelagem de Exemplos com `super` e Sobrescrita de Métodos
- Se `FuncionarioHorista` quiser aproveitar parte da lógica de `Funcionario` (por exemplo, se `Funcionario` já tivesse algum cálculo base), poderia chamar `super.calcularSalario()` e complementar.  
- Isso demonstra como a subclasse pode **estender** (usar `super`) ou **substituir** completamente a lógica herdada.

---

## 9. DISCUSSÃO DE BOAS PRÁTICAS

### 9.1. Evitando Hierarquias Excessivamente Profundas
Ter 3, 4, 5 ou mais níveis de herança pode complicar a manutenção. Cada nível depende de suposições do anterior, e o programador pode se perder no fluxo de chamadas, construtores e sobrescritas.  
- Regra empírica: manter a hierarquia **relativamente rasa**. Se ela se tornar muito profunda, reavalie se parte das sub-hierarquias poderia ser implementada por **composição** ou **interfaces**.

### 9.2. Final Classes e Final Methods: Limitações Intencionais
- `final class X` significa que `X` **não** pode ter subclasses. Útil quando queremos proteger uma classe de herança indesejada.  
- `final method doSomething()` significa que esse método não pode ser sobrescrito. Útil quando temos um método que deve permanecer inalterado em subclasses.

### 9.3. Múltiplos Níveis de Herança vs. Interfaces
Em Java, só é permitido **herança simples** (uma subclasse tem apenas uma superclasse direta). Para compartilhar comportamentos de múltiplas origens, é comum usar **interfaces** (que podem ter “default methods” desde Java 8). Assim, evitamos criar uma classe “mãe” que agrupe tudo.  

### 9.4. Design Patterns que Minimizam Abusos de Herança (Strategy, Decorator)
- **Strategy**: Encapsula algoritmos em objetos, evitando “inchar” subclasses com diferentes variações de comportamento.  
- **Decorator**: Adiciona funcionalidades dinamicamente por composição, em vez de herança.  
- **Template Method** (contrapartida do Strategy) também mostra uma forma de reuso, mas centraliza o fluxo na superclasse com ganchos para subclasses.

---

## 10. EXERCÍCIOS PROPOSTOS

### 10.1. Exercícios Simples

1. **Classe “Pessoa” e Subclasse “Aluno”**  
   - Crie uma superclasse `Pessoa` com atributos `nome`, `idade`.  
   - Crie uma subclasse `Aluno` que herda de `Pessoa` e acrescente atributo `matricula`.  
   - Use `super` no construtor de `Aluno`.  
   - Teste instanciando `new Aluno(...)`.  

2. **Sobrescrita de `toString()`**  
   - Na classe `Pessoa`, sobrescreva `toString()`. Na classe `Aluno`, sobrescreva novamente, chamando `super.toString()` e acrescentando `matricula`.  
   - Observe como, ao imprimir instâncias de `Aluno`, o método sobrescrito faz diferença.  

3. **Subclasse “Quadrado” de “Retangulo”**  
   - Se você já tem uma classe `Retangulo` com `largura` e `altura`, crie `Quadrado extends Retangulo`.  
   - No construtor de `Quadrado`, chame o construtor de `Retangulo` passando a mesma medida para `largura` e `altura`.  

### 10.2. Exercícios Intermediários

4. **Hierarquia de Veículos**  
   - Superclasse `Veiculo` com atributos como `marca`, `modelo`, e método `mover()`.  
   - Subclasses `Carro`, `Moto`, `Caminhao`, cada uma sobrescrevendo `mover()` de maneira distinta.  
   - Crie uma classe de teste que recebe `Veiculo` e chama `mover()`. Instancie diferentes subclasses e veja o polimorfismo.  

5. **Funcionário com Subclasses**  
   - `Funcionario` com método `calcularSalario()`.  
   - `FuncionarioHorista` e `FuncionarioComissionado`, sobrescrevendo `calcularSalario()`.  
   - Demonstre polimorfismo: um array `Funcionario[] funcionarios`, percorrendo e chamando `calcularSalario()`.  

6. **Organização de Classes: “Produto” e “Livro”**  
   - Superclasse `Produto` com atributos `nome`, `preco`, método `calcularPrecoVenda()`.  
   - Subclasse `Livro` com atributo `autor`, `numPaginas`, sobrescrevendo `calcularPrecoVenda()` para levar em conta a quantidade de páginas, por exemplo.  

---

## 11. REFERÊNCIAS, LINKS E LEITURAS ADICIONAIS

1. **“Effective Java”** – *Joshua Bloch*  
   - Embora não seja focado exclusivamente em herança, há capítulos sobre como e quando usar herança, e o porquê de preferir composição.  
2. **Documentação Oficial da Oracle (Java)**  
   - *Trail: Learning the Java Language – Inheritance*: [https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html)  
3. **“Design Patterns”** – *Erich Gamma et al. (Gang of Four)*  
   - Conceito de “Favor Composition over Inheritance” aparece em vários padrões, como Strategy e Decorator.  
5. **Livros de referência em POO**:  
   - “Object-Oriented Software Engineering” (Ivar Jacobson, Grady Booch, James Rumbaugh).  
   - “Applying UML and Patterns” (Craig Larman).

---

## ENCERRAMENTO

Nesta aula:

1. **Conceituamos Herança** como um mecanismo para reuso de código e generalização/especialização.  
2. **Diferenciamos** superclasse (classe-pai) e subclasse (classe-filha).  
3. Aprendemos a **usar `extends`** em Java e as implicações na inicialização (chamada ao construtor da superclasse).  
4. Vimos o **uso de `super`** para chamar construtores e métodos da superclasse.  
5. Discutimos **sobrescrita de métodos (`@Override`)** e como isso habilita polimorfismo.  
6. Enumeramos **benefícios da herança**, mas também **riscos**, incluindo a **fragilidade** de hierarquia e possíveis violações do LSP.  
7. Defendemos o **princípio “prefira composição à herança”** como boa prática em muitos cenários.  
8. Oferecemos **exemplos conceituais** (Animal, FormaGeometrica, Funcionario, etc.) e estudos de caso demonstrando como herança se aplica na vida real.  
9. Finalizamos com **exercícios** para fixação e **referências** para estudo mais avançado.

Você agora tem uma **visão ampla e profunda** de como funciona herança em Java: desde o básico (`extends`, `super`) até os **trade-offs** de design e riscos de hierarquias mal planejadas. A recomendação é praticar bastante, implementar hierarquias de classes diversas e, sobretudo, **analisar** quando a herança faz sentido e quando a composição ou interfaces seriam soluções mais robustas.  