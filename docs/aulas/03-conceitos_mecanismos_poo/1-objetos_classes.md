## SUMÁRIO DETALHADO

1. [Conceitos Fundamentais de Classes e Objetos](#cap2)  
   1.1. A Ideia de Classe como “Molde”  
   1.2. Exemplos de Classes no Cotidiano  
   1.3. Objeto como Instância de uma Classe  
   1.4. Relação entre Objeto e Classe: Metáforas e Analogias  
   1.5. Terminologia: Atributos, Propriedades ou Variáveis de Instância?  

2. [Atributos, Métodos e Membros de Classe](#cap3)  
   2.1. Atributos (Variáveis de Instância)  
       2.1.1. Declaração e Tipos  
       2.1.2. Visibilidade (public, private, protected)  
       2.1.3. Atributos de Classe (static)  
   2.2. Métodos (Funções Associadas à Classe)  
       2.2.1. Métodos de Instância vs. Métodos de Classe (static)  
       2.2.2. Assinatura de Método (Retorno, Nome, Parâmetros)  
       2.2.3. Sobrecarregando Métodos (Overloading)  
       2.2.4. Sobrescrevendo Métodos (Overriding) – Visão Geral  
   2.3. Construtores  
       2.3.1. Definição e Papel do Construtor  
       2.3.2. Construtores Sobrecaregados  
       2.3.3. Construtores Padrão  
   2.4. Métodos Acessores (Getters/Setters)  
       2.4.1. Por que Encapsular?  
       2.4.2. Convenções de Nomenclatura  
   2.5. Exemplos de Atributos e Métodos em Diferentes Contextos  

3. [Estado, Comportamento e Identidade](#cap4)  
   3.1. O que é o “Estado” de um Objeto?  
   3.2. O que é o “Comportamento” de um Objeto?  
   3.3. O que é a “Identidade” de um Objeto?  
   3.4. Ilustrações Práticas com Exemplos do Dia a Dia
   3.5. A Importância de se Entender Esses Conceitos para Projetos Orientados a Objetos  

4. [Exemplos Práticos em Java](#cap5)  
   4.1. Exemplo 1: Classe “Carro”  
       4.1.1. Definição de Atributos e Construtor  
       4.1.2. Métodos de Comportamento  
       4.1.3. Encapsulamento, Getters e Setters  
       4.1.4. Estado Interno e Chamadas de Métodos  
       4.1.5. Criação e Uso de Objetos em um `main()`  
   4.2. Exemplo 2: Classe “ContaBancaria”  
       4.2.1. Atributos e Regras de Negócio (Saldo, Limite etc.)  
       4.2.2. Métodos de Depósito e Saque  
       4.2.3. Estado x Comportamento x Identidade (Diferenças de Contas)  
       4.2.4. Comparando Objetos (ContaA vs. ContaB)  
   4.3. Exemplo 3: Classe “Produto” e Métodos Estáticos  
       4.3.1. Atributos Estáticos e Contadores de Objetos  
       4.3.2. Diferença entre Atributo de Instância e de Classe  
       4.3.3. Métodos Utilitários Estáticos  
   4.4. Exemplo 4: Classe “Aluno” e Sobrecarga de Construtores  
       4.4.1. Construtor Padrão vs. Construtores com Parâmetros  
       4.4.2. Métodos para Cálculo de Média  
       4.4.3. Demonstração do Ciclo de Vida de um Objeto  
   4.5. Exercícios de Fixação  

 5. [Boas Práticas e Padrões de Projeto Iniciais](#cap6)  
   5.1. Princípios de Design (SOLID) – Breve Introdução  
   5.2. Naming Conventions (Java Code Conventions)  
   5.3. Encapsulamento Profundo e Lei de Demeter  
   5.4. Organizando Classes em Pacotes (Packages)  
   5.5. Comentários e Documentação (JavaDoc)  


6. [Conceitos Adicionais Relacionados](#cap7)  
   6.1. Instanciação e Memória (Stack vs. Heap)  
   6.2. Ciclo de Vida de um Objeto (Criação, Uso, Destruição pelo Garbage Collector)  
   6.3. Imutabilidade e Objetos Imutáveis     

7. [Referências e Materiais de Apoio](#cap8) 

---

## CAPÍTULO 1 – CONCEITOS FUNDAMENTAIS DE CLASSES E OBJETOS

### 1.1. A Ideia de Classe como “Molde”
Uma **classe** é, essencialmente, a “receita” ou o “molde” que define quais atributos (dados) e métodos (procedimentos) um determinado tipo de objeto terá.  

- Exemplo simples: Classe “Carro”. Podemos ter atributos como `marca`, `modelo`, `ano` e métodos como `acelerar()`, `frear()`, `ligarMotor()`.  
- A partir desta classe, podemos instanciar vários objetos (carros individuais), cada um contendo valores diferentes para seus atributos.

### 1.2. Exemplos de Classes no Cotidiano
- Classe “Pessoa”: atributos como nome, idade, CPF; métodos como falar(), andar().  
- Classe “Computador”: atributos como processador, memóriaRAM, disco; métodos como ligar(), desligar(), instalarSoftware().  
- Classe “Livro”: atributos como título, autor, número de páginas; métodos como abrir(), fechar(), folhear().

### 1.3. Objeto como Instância de uma Classe
Quando executamos o comando `new` em Java, estamos criando um objeto na memória com base no “modelo” definido por sua classe. Este objeto é chamado de **instância** da classe.

**Sintaxe Geral em Java**:
```java
NomeDaClasse nomeVariavel = new NomeDaClasse();
```
Por exemplo:
```java
Carro meuCarro = new Carro(); 
```
Aqui, `meuCarro` é um objeto do tipo `Carro`, ou seja, uma instância da classe `Carro`.

### 1.4. Relação entre Objeto e Classe: Metáforas e Analogias
- **Metáfora do “Molde de Bolo”**: a classe seria o molde, que define o formato do bolo, enquanto o objeto seria o bolo produzido.  
- **Metáfora do “Blueprint e Casas”**: o blueprint (planta) define a estrutura e as características da casa, mas cada casa construída (objeto) pode ter cores diferentes ou mobílias diferentes (valores de atributos).

### 1.5. Terminologia: Atributos, Propriedades ou Variáveis de Instância?
Em Java, é comum chamarmos as variáveis que pertencem a um objeto de **variáveis de instância** ou simplesmente **atributos**. Em outras linguagens ou contextos, podem ser chamadas de **propriedades**.  

O conceito permanece: elas guardam o estado do objeto. Quando o objeto é criado, cada atributo recebe valores padrão ou definidos pelo construtor; quando o objeto é modificado (chamada de métodos que alteram estado), esses atributos também mudam.

---

## CAPÍTULO 2 – ATRIBUTOS, MÉTODOS E MEMBROS DE CLASSE

Agora, vamos detalhar os principais membros que compõem uma classe em Java: **atributos** (variáveis de instância), **métodos** (funções que definem o comportamento) e **construtores** (responsáveis pela inicialização de um objeto).

### 2.1. Atributos (Variáveis de Instância)

#### 2.1.1. Declaração e Tipos
Um atributo é declarado dentro do escopo da classe, geralmente no topo, possuindo um tipo (primitivo ou objeto), um modificador de acesso (public, private, etc.) e um nome. Exemplo:

```java
public class Carro {
    private String marca;
    private String modelo;
    private int ano;
    private double velocidadeAtual;
    // ...
}
```
- `marca`, `modelo`, `ano` e `velocidadeAtual` são atributos da classe `Carro`.  
- `String` e `int`, `double` são exemplos de tipos em Java.

#### 2.1.2. Visibilidade (public, private, protected)
- `public`: Significa que esse atributo pode ser acessado livremente de fora da classe (recomendado em métodos, não tanto em atributos).  
- `private`: Apenas acessível dentro da própria classe. Geralmente recomendado para manter o encapsulamento.  
- `protected`: Acessível dentro do mesmo pacote e por subclasses, mas não para todas as classes.  

A prática mais comum em Java é **manter atributos como `private`** e oferecer métodos de acesso (getters e setters) para controlá-los.

#### 2.1.3. Atributos de Classe (static)
Se declararmos um atributo como `static`, ele passa a pertencer à classe em si, não mais às instâncias individuais. Por exemplo:

```java
public class Carro {
    private static int quantidadeCarrosCriados = 0;
    
    public Carro() {
        quantidadeCarrosCriados++;
    }
    
    // ...
}
```
- Todos os objetos `Carro` compartilham o mesmo `quantidadeCarrosCriados`.  
- É útil para contadores, configurações globais, etc.

### 2.2. Métodos (Funções Associadas à Classe)

#### 2.2.1. Métodos de Instância vs. Métodos de Classe (static)
- **Métodos de Instância**: Operam sobre uma instância específica, acessando atributos não estáticos.  
- **Métodos de Classe (static)**: Podem ser invocados sem precisar criar um objeto. Costumam ser métodos utilitários. Exemplo:

```java
public class Matematica {
    public static int somar(int a, int b) {
        return a + b;
    }
}
```
Nesse caso, chamamos `Matematica.somar(3, 4)` sem precisar instanciar `new Matematica()`.

#### 2.2.2. Assinatura de Método (Retorno, Nome, Parâmetros)
Cada método tem:
- Tipo de retorno (pode ser `void` se não retornar nada).  
- Nome do método (ex.: `acelerar`, `sacar`, `depositar`).  
- Lista de parâmetros entre parênteses (ex.: `int valor`, `String nome`).  
- Modificadores de acesso (public, private, etc.).

#### 2.2.3. Sobrecarregando Métodos (Overloading)
**Overloading** ocorre quando criamos vários métodos com **mesmo nome**, porém **assinaturas diferentes** (tipos ou quantidade de parâmetros). Por exemplo:

```java
public class MinhaCalculadora {
    public int somar(int a, int b) {
        return a + b;
    }

    public double somar(double a, double b) {
        return a + b;
    }
}
```
Aqui, `somar(int,int)` e `somar(double,double)` são dois métodos sobrecarregados.

#### 2.2.4. Sobrescrevendo Métodos (Overriding) – Visão Geral
É quando **redefinimos um método** que já existia na superclasse (herança). Como ainda não abordamos herança em detalhes, basta entender que se existe um método `falar()` em uma classe pai, a classe filha pode sobrescrever esse método para mudar seu comportamento.  

### 2.3. Construtores

#### 2.3.1. Definição e Papel do Construtor
O **construtor** é um método especial responsável por inicializar o objeto no momento em que ele é criado. Suas características:
- Tem o **mesmo nome da classe**.  
- **Não tem tipo de retorno**, nem `void`.  
- É chamado pelo `new`.

#### 2.3.2. Construtores Sobrecarregados
Podemos ter múltiplos construtores, cada um recebendo diferentes parâmetros, para flexibilizar a criação de objetos:

```java
public class Aluno {
    private String nome;
    private int idade;

    public Aluno() {
        // Construtor padrão
    }

    public Aluno(String nome) {
        this.nome = nome;
    }

    public Aluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}
```

#### 2.3.3. Construtores Padrão
Se você **não** declarar nenhum construtor, o Java cria um **construtor padrão** (sem parâmetros) automaticamente. Mas a partir do momento em que você cria **qualquer** construtor, o padrão deixa de existir (a menos que você o declare explicitamente).

### 2.4. Métodos Acessores (Getters/Setters)

#### 2.4.1. Por que Encapsular?
Encapsulamento ajuda a **proteger** o estado interno do objeto de acessos indevidos e facilita a manutenção e a evolução do código. Ao invés de permitir acesso direto aos atributos, oferecemos métodos que definem como ler ou alterar esses atributos.

#### 2.4.2. Convenções de Nomenclatura
- Para um atributo `nome`, o **getter** costuma ser `getNome()`, e o **setter** costuma ser `setNome(String nome)`.  
- Para atributos booleanos, pode-se usar `isAtivo()` em vez de `getAtivo()` como convenção.

### 2.5. Exemplos de Atributos e Métodos em Diferentes Contextos
- Em uma classe “Bicicleta”: `private int marcha; private double velocidade; public void pedalar(double incremento)` etc.  
- Em uma classe “ContaBancaria”: `private String titular; private double saldo; public void depositar(double valor)` etc.  

O essencial é que cada classe deve representar algo **coeso**, isto é, atributos e métodos que façam sentido juntos.

---

## CAPÍTULO 3 – ESTADO, COMPORTAMENTO E IDENTIDADE

Nesta seção, abordamos a célebre **tríade** que define o que é um objeto no contexto de POO: **estado**, **comportamento** e **identidade**.

### 3.1. O que é o “Estado” de um Objeto?
O **estado** se refere aos **valores dos atributos** naquele momento. Por exemplo, se você tem um objeto da classe `Carro`, o estado é dado pela `marca`, `modelo`, `ano` e qualquer outro atributo relevante.  

- Se `meuCarro.marca = "Toyota"` e `meuCarro.velocidadeAtual = 60`, podemos dizer que o estado atual do carro (nesse instante) tem velocidade 60 e marca Toyota.

### 3.2. O que é o “Comportamento” de um Objeto?
O **comportamento** é definido pelos **métodos** e pelas **ações** que o objeto pode realizar ou sofrer. Seguindo o exemplo de `Carro`:
- `acelerar()`, `frear()`, `ligarMotor()` são comportamentos.  
- Esses métodos podem **modificar** ou **consultar** o estado do objeto (por exemplo, aumentar `velocidadeAtual`).

### 3.3. O que é a “Identidade” de um Objeto?
A **identidade** diferencia um objeto de outro, mesmo que tenham o **mesmo estado**. Em Java, cada objeto tem um “endereço” na memória (ou uma referência) único. Isso fica mais evidente quando comparamos dois objetos que têm atributos idênticos, mas não são o mesmo objeto.

Por exemplo:
```java
Carro carroA = new Carro();
Carro carroB = new Carro();
```
Mesmo se ambos tiverem `marca = "Fiat"`, `modelo = "Uno"`, etc., eles são **objetos diferentes** (identidades distintas).  

### 3.4. Ilustrações Práticas com Exemplos do Dia a Dia
- Duas canetas “idênticas” na vida real: ambas podem ter tinta azul, mesmo formato, mesma marca, mas são objetos diferentes.  
- Duas instâncias de `Scanner` em Java: ambas podem estar lendo do `System.in`, mas ainda são duas instâncias distintas na memória.

### 3.5. A Importância de se Entender Esses Conceitos para Projetos Orientados a Objetos
- **Estado**: Permite rastrear o “histórico” ou a “situação” corrente do objeto.  
- **Comportamento**: Define como o objeto interage com outros objetos e com o mundo.  
- **Identidade**: Essencial para sabermos diferenciar objetos no sistema e tratar cada um como uma entidade única.

---

## CAPÍTULO 4 – EXEMPLOS PRÁTICOS EM JAVA

Agora, vamos ilustrar tudo isso com código Java, construindo classes e demonstrando como criar e manipular objetos.

### 4.1. Exemplo 1: Classe “Carro”

#### 4.1.1. Definição de Atributos e Construtor
Suponha que desejamos modelar um carro simples, com atributos para marca, modelo, ano e velocidade atual.  

```java
public class Carro {
    private String marca;
    private String modelo;
    private int ano;
    private double velocidadeAtual;

    // Construtor padrão
    public Carro() {
        this.velocidadeAtual = 0; // Inicia parado
    }

    // Construtor com parâmetros
    public Carro(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.velocidadeAtual = 0;
    }

    // Getters e setters
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getVelocidadeAtual() {
        return velocidadeAtual;
    }
}
```
Nesse ponto, temos os atributos encapsulados e alguns métodos de acesso.

#### 4.1.2. Métodos de Comportamento
Agora adicionamos métodos que representam ações de um carro:

```java
public void acelerar(double incremento) {
    if(incremento > 0) {
        velocidadeAtual += incremento;
    }
}

public void frear(double decremento) {
    if(decremento > 0 && velocidadeAtual > 0) {
        velocidadeAtual -= decremento;
        if(velocidadeAtual < 0) {
            velocidadeAtual = 0;
        }
    }
}

public void ligarMotor() {
    System.out.println("O motor do carro está ligado!");
}
```

#### 4.1.3. Encapsulamento, Getters e Setters
Já vimos que o carro possui getters e setters para `marca`, `modelo`, etc. Por segurança, muitas vezes restringimos certos atributos e não fornecemos sets, por exemplo, `velocidadeAtual` pode ser alterado apenas através de `acelerar()` e `frear()`.

#### 4.1.4. Estado Interno e Chamadas de Métodos
- O **estado interno** do objeto `Carro` é definido pelos valores de seus atributos (`marca`, `modelo`, `ano`, `velocidadeAtual`).  
- Quando chamamos `acelerar(10)`, estamos modificando esse estado (aumentando `velocidadeAtual` em 10).

#### 4.1.5. Criação e Uso de Objetos em um `main()`
Podemos testar essa classe com um método principal numa outra classe, por exemplo:

```java
public class TesteCarro {
    public static void main(String[] args) {
        Carro carro1 = new Carro("Fiat", "Uno", 2010);
        carro1.ligarMotor();
        carro1.acelerar(20);
        System.out.println("Velocidade atual: " + carro1.getVelocidadeAtual()); // Deve exibir 20.0

        carro1.frear(10);
        System.out.println("Velocidade atual após frear: " + carro1.getVelocidadeAtual()); // Deve exibir 10.0
    }
}
```

### 4.2. Exemplo 2: Classe “ContaBancaria”

#### 4.2.1. Atributos e Regras de Negócio (Saldo, Limite etc.)
Suponha uma classe que represente uma conta bancária simples:

```java
public class ContaBancaria {
    private String titular;
    private double saldo;

    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if(valor > 0) {
            saldo += valor;
        }
    }

    public boolean sacar(double valor) {
        if(valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }
}
```

#### 4.2.2. Métodos de Depósito e Saque
No exemplo, `depositar` e `sacar` representam comportamentos de uma conta bancária. O método `sacar` retorna um booleano indicando se a operação foi bem-sucedida.

#### 4.2.3. Estado x Comportamento x Identidade (Diferenças de Contas)
- **Estado**: o valor de `saldo` de cada conta, o `titular`, etc.  
- **Comportamento**: depositar, sacar, consultar saldo.  
- **Identidade**: mesmo que duas contas tenham o mesmo saldo e titular, elas podem ser objetos distintos (ou até no mundo real, teriam IDs únicos).  

#### 4.2.4. Comparando Objetos (ContaA vs. ContaB)
Se você criar:

```java
ContaBancaria contaA = new ContaBancaria("Alice", 1000);
ContaBancaria contaB = new ContaBancaria("Alice", 1000);
```
`contaA` e `contaB` possuem estados iguais inicialmente (titular: "Alice", saldo: 1000). Porém, são referências distintas em memória.

### 4.3. Exemplo 3: Classe “Produto” e Métodos Estáticos

#### 4.3.1. Atributos Estáticos e Contadores de Objetos
Imagine que queremos contar quantos produtos foram criados:

```java
public class Produto {
    private static int contador = 0; // Atributo de classe
    private String nome;

    public Produto(String nome) {
        this.nome = nome;
        contador++;
    }

    public static int getContador() {
        return contador;
    }
}
```
- Cada vez que criamos um novo `Produto`, `contador` é incrementado.  
- `getContador()` é um método de classe (static) que retorna o total de instâncias criadas até então.

#### 4.3.2. Diferença entre Atributo de Instância e de Classe
- `nome` é por instância, cada `Produto` tem seu próprio `nome`.  
- `contador` é único para toda a classe.

#### 4.3.3. Métodos Utilitários Estáticos
É comum criarmos métodos como `calcularDesconto(preco, porcentagem)` dentro de uma classe de utilidade, por exemplo `Utilidades`. Assim, chamamos diretamente `Utilidades.calcularDesconto(...)`.

### 4.4. Exemplo 4: Classe “Aluno” e Sobrecarga de Construtores

#### 4.4.1. Construtor Padrão vs. Construtores com Parâmetros
```java
public class Aluno {
    private String nome;
    private int idade;
    private double nota1, nota2;

    public Aluno() {
        // Construtor padrão
    }

    public Aluno(String nome) {
        this.nome = nome;
    }

    public Aluno(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    // getters e setters...
}
```

#### 4.4.2. Métodos para Cálculo de Média
```java
public double calcularMedia() {
    return (nota1 + nota2) / 2.0;
}
```
Aqui está o comportamento de “aluno”: calcular sua média com base nas notas.

#### 4.4.3. Demonstração do Ciclo de Vida de um Objeto
- Criação: `Aluno a1 = new Aluno("João", 20);`  
- Uso: `a1.setNota1(7.5); a1.setNota2(8.0); System.out.println(a1.calcularMedia());`  
- Destruição: é gerenciada pelo **Garbage Collector** em Java, sem necessidade de desalocação manual.

### 4.5. Exercícios de Fixação
1. Crie uma classe “Livro” com atributos `titulo`, `autor`, `paginasLidas`, e métodos para “abrir o livro”, “avançar página”, “retroceder página” e “fechar o livro”.  
2. Crie uma classe “Pessoa” que possua um construtor que recebe um nome e um método “dizerOla()” que imprime “Olá, meu nome é X”. Teste no `main()`.  
3. Crie uma classe “Retangulo” com atributos “largura” e “altura”. Adicione um método `calcularArea()` e `calcularPerimetro()`. Instancie alguns objetos e verifique os resultados.

---

## CAPÍTULO 5 – BOAS PRÁTICAS E PADRÕES DE PROJETO INICIAIS

### 5.1. Princípios de Design (SOLID) – Breve Introdução
- **S** Single Responsibility Principle (Princípio da Responsabilidade Única)  
- **O** Open/Closed Principle  
- **L** Liskov Substitution Principle  
- **I** Interface Segregation Principle  
- **D** Dependency Inversion Principle  

Para iniciantes, foque em “Single Responsibility”: cada classe deve ter uma única responsabilidade bem definida.

### 5.2. Naming Conventions (Java Code Conventions)
- Classes: começam com letra maiúscula (ex.: `MeuCarro`, `ContaBancaria`).  
- Métodos e atributos: começam com letra minúscula (ex.: `acelerar()`, `modelo`).  
- Constantes: `static final` normalmente em MAIÚSCULAS (ex.: `TAXA_JUROS`).

### 5.3. Encapsulamento Profundo e Lei de Demeter
- **Encapsulamento Profundo**: Evitar expor estruturas internas de forma que um cliente de classe não acesse diretamente seus objetos internos.  
- **Lei de Demeter**: “Não fale com estranhos”. Cada método deve interagir apenas com seus campos e parâmetros, reduzindo acoplamento.

### 5.4. Organizando Classes em Pacotes (Packages)
Em Java, agrupar classes em pacotes (ex.: `br.com.meuprojeto.model`, `br.com.meuprojeto.controller`) é importante para organização e modularização do projeto.

### 5.5. Comentários e Documentação (JavaDoc)
- Use comentários para explicar “por que” faz algo, não apenas “o que”.  
- Ferramenta JavaDoc gera documentação de forma automática com base em comentários específicos.

---

## CAPÍTULO 6 – CONCEITOS ADICIONAIS RELACIONADOS

### 6.1. Instanciação e Memória (Stack vs. Heap)
Quando criamos um objeto com `new`, o espaço para esse objeto é alocado no **Heap**. A variável de referência (por exemplo, `Carro carro1`) fica na **Stack** apontando para o objeto no Heap.

### 6.2. Ciclo de Vida de um Objeto (Criação, Uso, Destruição pelo Garbage Collector)
- **Criação**: `new Classe()`.  
- **Uso**: Chamadas a métodos.  
- **Elegível a Garbage Collection**: Quando não há referências acessíveis ao objeto, o GC remove da memória.

### 6.3. Imutabilidade e Objetos Imutáveis
Um **objeto imutável** não permite a alteração de seus atributos após ser criado. Exemplo clássico é a classe `String` em Java. Para dados críticos ou ambientes concorrentes, objetos imutáveis podem ser muito vantajosos (menos problemas de sincronização).

---

## CAPÍTULO 7 – REFERÊNCIAS E MATERIAIS DE APOIO

- [Documentação Oficial da Oracle para Java](https://docs.oracle.com/en/java/javase/)  
- Livros Clássicos de POO e Java:  
  - “Thinking in Java” (Bruce Eckel)  
  - “Effective Java” (Joshua Bloch)  
  - “Head First Java” (Kathy Sierra, Bert Bates)  
- Tutoriais e Cursos Online (Plataformas como Coursera, Alura, Udemy, etc.)  
- Documentação e Boa Práticas: [Java Code Conventions](https://www.oracle.com/technetwork/java/codeconventions-150003.pdf)

---

## ENCERRAMENTO

Nesta aula extensa, vimos:

1. **Conceito de Classes**: como “molde” que define quais atributos e métodos os objetos terão.  
2. **Atributos e Métodos**: os blocos de construção de qualquer classe em Java.  
3. **Estado, Comportamento e Identidade**: a tríade fundamental que caracteriza um objeto e sua existência no sistema.  
4. **Exemplos Práticos** (Carro, ContaBancaria, Produto, Aluno) para ilustrar como criar e manipular objetos em Java.  
5. **Boas Práticas** de organização e design, visando escrever código mais limpo e coeso.  
6. **Tópicos Relacionados** como memória, garbage collector, imutabilidade, herança e polimorfismo.

Entender esses conceitos forma a base para todo desenvolvimento em POO e, mais especificamente, para o ecossistema Java. Praticando e exercitando, você consolidará o conhecimento, aprendendo como construir sistemas modulares, manuteníveis e intuitivos.