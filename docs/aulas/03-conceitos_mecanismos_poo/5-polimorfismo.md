# SUMÁRIO GERAL

1. [Conceito Geral de Polimorfismo e Objetivos](#cap1)  
   1.1. Origem e Etimologia  
   1.2. Polimorfismo em Linguagens Orientadas a Objetos  
   1.3. Por que Polimorfismo é Tão Importante?  

2. [Polimorfismo em Tempo de Compilação (Overloading / Sobrecarga)](#cap2)  
   2.1. Definição de Sobrecarga de Métodos  
   2.2. Como Funciona o “Binding” em Tempo de Compilação?  
   2.3. Regras para Overloading (Assinaturas Distintas)  
   2.4. Exemplos Práticos de Sobrecarga em Java  
   2.5. Vantagens, Limitações e Boas Práticas  

3. [Polimorfismo em Tempo de Execução (Override / Sobrescrita)](#cap3)  
   3.1. Definição de Sobrescrita de Métodos  
   3.2. Binding Dinâmico (Dynamic Binding)  
   3.3. Assinatura, `@Override` e Necessidades de Compatibilidade  
   3.4. Exemplos Práticos de Sobrescrita em Java  
   3.5. Relação com Herança e Interfaces  

4. [Invocação de Métodos Polimórficos e Binding Dinâmico](#cap4)  
   4.1. “Late Binding” vs. “Early Binding”  
   4.2. Usando Referências do Tipo da Superclasse para Objetos da Subclasse  
   4.3. Polimorfismo Paramétrico e Covariância de Retorno (Breve Menção)  
   4.4. Comparando Comportamentos de Sobrecarga e Sobrescrita Durante Chamada de Métodos  

5. [Exemplos Avançados de Polimorfismo](#cap5)  
   5.1. Hierarquias de Classes Mais Complexas  
       5.1.1. Exemplo: FormaGeometrica, Retangulo, Circulo, Triangulo  
       5.1.2. Exemplo: Funcionário, FuncionárioHorista, FuncionárioMensalista, FuncionárioPJ  
       5.1.3. Exemplo: ProcessadorArquivo (subclasses PDF, DOC, XML)  
   5.2. Cenários Concretos de Reuso de Código (ArrayList de Referências “mais gerais”)  
   5.3. Polimorfismo de Subtipo vs. Polimorfismo Paramétrico  
   5.4. Polimorfismo com Interfaces (comparando com Herança)  

6. [Boas Práticas e Desenhos Polimórficos](#cap6)  
   6.1. Evitando Abusos de Sobrecarga que Confundem a Leitura do Código  
   6.2. Planejando Sobrescrita de Métodos Importantes (equals, toString etc.)  
   6.3. Patterns como Strategy e Template Method que se Apoiam em Polimorfismo  
   6.4. Acoplamento e Fragilidade: Quando o Polimorfismo Gera Problemas  

7. [Demonstrações de Código: Passo a Passo](#cap7)  
   7.1. Jogo de RPG com Polimorfismo (Monstro, Dragao, Goblin, Esqueleto)  
       7.1.1. Overloading de Construtores e Métodos de Ataque  
       7.1.2. Override de Métodos “agredir()”  
   7.2. Aplicação Financeira Polimórfica (Conta, ContaCorrente, ContaPoupanca, Investimento)  
       7.2.1. Sobreposição de Métodos de Rendimento  
       7.2.2. Sobrecarga para Depósitos Diferenciados  

8. [Exercícios de Fixação e Projetos Sugeridos](#cap8)  
   8.1. Exercícios Simples  
   8.2. Exercícios Intermediários  
   
9. [Referências e Leituras Adicionais](#cap9)

---

## 1. CONCEITO GERAL DE POLIMORFISMO E OBJETIVOS

### 1.1. Origem e Etimologia
A palavra “polimorfismo” vem do grego “**poly**” (muitos) + “**morphos**” (formas). Em ciências, frequentemente se refere à capacidade de algo se apresentar em várias formas. Na **Programação Orientada a Objetos (POO)**, significa que **um mesmo “nome” (método, operação) pode se comportar de maneiras diferentes** dependendo do **tipo** específico de dados ou do **contexto** de invocação.

### 1.2. Polimorfismo em Linguagens Orientadas a Objetos
Em POO, polimorfismo é um dos pilares fundamentais, ao lado de **Abstração**, **Encapsulamento** e **Herança**. É o que permite escrever código orientado a objetos de maneira mais flexível e extensível, facilitando reuso e clareza.

### 1.3. Por que Polimorfismo é Tão Importante?
- **Redução de Condicionais**: Em vez de criar muitos “if (objeto é X) então faz A, else se (objeto é Y) então faz B...”, podemos confiar que `objeto.metodo()` chamará o método adequado para cada subtipo.  
- **Reuso de Código**: Cria-se uma única interface ou superclasse, e cada subtipo implementa seu comportamento. O código que invoca esses métodos não precisa saber qual subtipo está em uso.  
- **Extensibilidade**: Facilita adicionar novas subclasses ou implementações sem alterar o código que as chama.

---

## 2. POLIMORFISMO EM TEMPO DE COMPILAÇÃO (OVERLOADING / SOBRECARGA)

### 2.1. Definição de Sobrecarga de Métodos
**Sobrecarga** ocorre quando temos **múltiplos métodos** com **mesmo nome**, porém **assinaturas diferentes** (diferem no número ou tipo dos parâmetros). É um polimorfismo que se **resolve** em **tempo de compilação**.  

Por exemplo:
```java
public class Calculadora {
    public int somar(int a, int b) {
        return a + b;
    }

    public double somar(double a, double b) {
        return a + b;
    }

    public int somar(int a, int b, int c) {
        return a + b + c;
    }
}
```
Aqui, temos **três** métodos `somar`, cada um com **assinatura distinta**. Em tempo de compilação, o Java escolherá qual método chamar com base nos **tipos e quantidade** de argumentos.

### 2.2. Como Funciona o “Binding” em Tempo de Compilação?
- Quando chamamos `calc.somar(2, 3)`, o compilador vê que existe um método que recebe `(int, int)`.  
- Quando chamamos `calc.somar(2.5, 3.5)`, o compilador vê `(double, double)`.  
- Isso é o que chamamos de “**early binding**”: a resolução de qual método invocar acontece antes do programa rodar, com base na **assinatura**.

### 2.3. Regras para Overloading (Assinaturas Distintas)
1. Precisamos **mesmo nome de método**.  
2. Precisamos **número e/ou tipo** de parâmetros **diferentes**.  
3. O **tipo de retorno** sozinho **não** diferencia sobrecarga. Se só mudássemos `public int x()` para `public double x()`, isso geraria erro de compilação se a lista de parâmetros fosse idêntica.

### 2.4. Exemplos Práticos de Sobrecarga em Java
- **Construtores**: Muitas vezes sobrecarregamos construtores para oferecer formas diferentes de inicializar um objeto (ex.: construtor sem parâmetros, construtor com 2 parâmetros, construtor com 3 parâmetros...).  
- **Métodos “parse”**: Em muitas bibliotecas, podemos ter algo como `parseDate(String s)` e `parseDate(String s, String format)`.  
- **Métodos de cálculo**: Exemplo `Math.max(int a, int b)`, `Math.max(double a, double b)` etc.

### 2.5. Vantagens, Limitações e Boas Práticas
- **Vantagem**: Oferece **flexibilidade** ao invocar métodos com diferentes conjuntos de parâmetros usando o mesmo nome, facilitando a legibilidade do código.  
- **Limitação**: Pode causar **ambiguidade** se não for projetado com cuidado (ex.: `somar(float, float)` vs. `somar(double, double)` e chamamos com `somar(2.0f, 2.0)`, gerando conversões e possíveis conflitos).  
- **Boas Práticas**:  
  - Use sobrecarga apenas quando **faz sentido semântico**. Os métodos devem ter relação conceitual semelhante.  
  - Evite exagero que confunda o leitor do código.  
  - Documente cada método para deixar claro seus parâmetros e comportamentos distintos.

---

## 3. POLIMORFISMO EM TEMPO DE EXECUÇÃO (OVERRIDE / SOBRESCRITA)

### 3.1. Definição de Sobrescrita de Métodos
**Sobrescrita** ocorre quando uma subclasse **fornece uma nova implementação** para um método que já existe na superclasse (ou é definido em uma interface). Mantém-se **mesma assinatura** (nome, parâmetros, tipo de retorno compatível), mas o **corpo** (implementação) muda.  

Este é o **polimorfismo** que associamos a **herança** ou **interfaces** e é resolvido em **tempo de execução**.

### 3.2. Binding Dinâmico (Dynamic Binding)
Com sobrescrita, se escrevemos:
```java
Animal a = new Cachorro();
a.emitirSom();
```
O método chamado em tempo de execução (run time) é o de `Cachorro`, **mesmo** que a referência seja do tipo `Animal`. Isso se chama **“dynamic binding”** ou “late binding”, pois a decisão de qual método efetivamente chamar só ocorre **quando o programa roda**, baseado no **objeto concreto** (`new Cachorro()`).

### 3.3. Assinatura, `@Override` e Necessidades de Compatibilidade
- A assinatura do método sobrescrito na subclasse deve bater exatamente com a da superclasse, exceto:  
  - O tipo de retorno pode ser **covariante** (por exemplo, se a superclasse retorna `Animal`, a subclasse pode retornar `Cachorro`, que é um subtipo de `Animal`).  
  - O modificador de acesso pode ser mais permissivo (public ou protected em vez de private), mas não pode ser mais restritivo.  
- Em Java, costumamos anotar métodos sobrescritos com `@Override`, para deixar claro ao compilador (e aos devs) que é uma sobrescrita.

### 3.4. Exemplos Práticos de Sobrescrita em Java
Exemplo simples de classe Animal vs. subclasses:
```java
public class Animal {
    public void emitirSom() {
        System.out.println("Som genérico de animal!");
    }
}

public class Gato extends Animal {
    @Override
    public void emitirSom() {
        System.out.println("Miau, miau!");
    }
}

public class Cachorro extends Animal {
    @Override
    public void emitirSom() {
        System.out.println("Au, au!");
    }
}
```
Agora, 
```java
Animal a1 = new Gato();
Animal a2 = new Cachorro();
a1.emitirSom();  // Miau, miau!
a2.emitirSom();  // Au, au!
```
Isto exemplifica polimorfismo **em tempo de execução**.

### 3.5. Relação com Herança e Interfaces
- Geralmente sobrescrevemos métodos de **superclasses** que não se adequam totalmente às necessidades da subclasse, ou que são declarados como “abstract” (obrigando a subclasse a implementar).  
- Também sobrescrevemos métodos de **interfaces**. Nesse caso, a classe que implementa a interface deve fornecer as implementações concretas de todos os métodos abstratos declarados na interface.

---

## 4. INVOCAÇÃO DE MÉTODOS POLIMÓRFICOS E BINDING DINÂMICO

### 4.1. “Late Binding” vs. “Early Binding”
- **Early Binding** (sobrecarga) amarra qual método será chamado no momento da compilação, baseado nos tipos dos parâmetros.  
- **Late Binding** (sobrescrita) adia a decisão de qual método concreto chamar até sabermos o **tipo real** do objeto em runtime.

### 4.2. Usando Referências do Tipo da Superclasse para Objetos da Subclasse
Isso é a essência do polimorfismo de subtipo:
```java
Animal a = new Gato(); // "a" é do tipo Animal, mas aponta para Gato
a.emitirSom();         // chama Gato.emitirSom()
```
A cada subclasse nova adicionada, podemos reutilizar o mesmo código que manipula `Animal` sem precisar alterar nada (Open-Closed Principle).

### 4.3. Polimorfismo Paramétrico e Covariância de Retorno (Breve Menção)
- Polimorfismo paramétrico é aquele obtido com **Generics** em Java, permitindo criar classes e métodos genéricos que funcionam para diversos tipos (por ex. `List<T>`). Não é o foco aqui, mas é outra forma de polimorfismo.  
- Covariância de retorno: se a superclasse define `public Animal getFilhote()`, a subclasse pode sobrescrever retornando `public Cachorro getFilhote()` (Cachorro é subtipo de Animal).

### 4.4. Comparando Comportamentos de Sobrecarga e Sobrescrita Durante Chamada de Métodos
- **Sobrecarga**:  
  - Se chamamos `obj.somar(2, 3)`, a escolha do método é fixada no **compilador** baseado em `(int, int)`.  
  - Se a classe de `obj` tiver 3 versões de `somar`, a compatível é escolhida antes de rodar o programa.  

- **Sobrescrita**:  
  - Se chamamos `obj.somar(2, 3)`, e `obj` pode ser uma subclasse que sobrescreve `somar(int, int)`, a versão a ser chamada é decidida em runtime olhando o **tipo real** de `obj`.

---

## 5. EXEMPLOS AVANÇADOS DE POLIMORFISMO

### 5.1. Hierarquias de Classes Mais Complexas

#### 5.1.1. Exemplo: FormaGeometrica, Retangulo, Circulo, Triangulo
Geralmente implementamos algo como:
```java
public abstract class FormaGeometrica {
    public abstract double calcularArea();
    public abstract double calcularPerimetro();
}

public class Retangulo extends FormaGeometrica {
    private double largura, altura;
    // Construtor, getters e setters

    @Override
    public double calcularArea() { return largura * altura; }

    @Override
    public double calcularPerimetro() { return 2 * (largura + altura); }
}

public class Circulo extends FormaGeometrica {
    private double raio;
    // Construtor, getters e setters

    @Override
    public double calcularArea() { return Math.PI * raio * raio; }

    @Override
    public double calcularPerimetro() { return 2 * Math.PI * raio; }
}

// e assim por diante
```
Assim, podemos ter um array ou lista de `FormaGeometrica`:
```java
List<FormaGeometrica> formas = new ArrayList<>();
formas.add(new Retangulo(3, 4));
formas.add(new Circulo(2.5));
formas.add(new TrianguloEquilatero(2));

for (FormaGeometrica f : formas) {
    System.out.println("Área: " + f.calcularArea());
    System.out.println("Perímetro: " + f.calcularPerimetro());
}
```
**Cada** instância chama seus **métodos sobrescritos** de acordo com o tipo real (Retangulo, Circulo, etc.).

#### 5.1.2. Exemplo: Funcionário, FuncionárioHorista, FuncionárioMensalista, FuncionárioPJ
- `Funcionario` (superclasse) com método `calcularSalario()`.  
- Subclasses implementam lógicas específicas. Podemos ter:
```java
public class FuncionarioMensalista extends Funcionario {
    @Override
    public double calcularSalario() {
        return salarioBase; // fixo
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

public class FuncionarioPJ extends Funcionario {
    private double valorContrato;
    @Override
    public double calcularSalario() {
        return valorContrato; // simplificado
    }
}
```
Se temos um sistema que faz `folhaDePagamento(Funcionario f)`, bastará chamar `f.calcularSalario()` e cada subtipo faz algo diferente.

#### 5.1.3. Exemplo: ProcessadorArquivo (subclasses PDF, DOC, XML)
- Interface ou classe abstrata `ProcessadorArquivo` com método `processarArquivo(String path)`.  
- Subclasses `ProcessadorPDF`, `ProcessadorDOC`, `ProcessadorXML` cada uma interpretando o arquivo de forma distinta.  
- Quando o sistema recebe “Qual processador usar?”, podemos instanciar a subclasse correta e chamamos `processarArquivo(path)`. O polimorfismo faz com que cada tipo trate o arquivo de modo específico.

### 5.2. Cenários Concretos de Reuso de Código (ArrayList de Referências “mais gerais”)
Um dos maiores ganhos do polimorfismo em tempo de execução (override) é podermos manipular coleções de objetos de tipos diferentes (mas que compartilham a mesma superclasse ou interface) de forma **uniforme**.

Exemplo: `List<Funcionario> lista = new ArrayList<>();`  
Podemos armazenar `new FuncionarioHorista()`, `new FuncionarioMensalista()`, `new FuncionarioPJ()` e, ao iterar, basta chamar `calcularSalario()` sem ifs ou casts específicos.

### 5.3. Polimorfismo de Subtipo vs. Polimorfismo Paramétrico
- **Polimorfismo de subtipo**: o tipo real do objeto é uma subclasse ou implementação de uma interface, e, portanto, chama métodos sobrescritos.  
- **Polimorfismo paramétrico**: uso de **Generics** (ex.: `List<T>`), que permite que o mesmo código genérico funcione para vários tipos `T`.

Ambos são considerados **polimorfismo**, mas se aplicam a problemas diferentes. Overriding (sobrescrita) faz parte do polimorfismo de subtipo.

### 5.4. Polimorfismo com Interfaces (comparando com Herança)
Quando usamos interfaces:
```java
public interface Desenhavel {
    void desenhar();
}

public class Retangulo implements Desenhavel {
    @Override
    public void desenhar() {
        // lógica de desenho
    }
}
public class Circulo implements Desenhavel {
    @Override
    public void desenhar() {
        // outra lógica
    }
}
```
Um código que tem `List<Desenhavel> formas` pode chamar `desenhar()` sem precisar se preocupar se é Retangulo ou Circulo. Isso também é polimorfismo em tempo de execução (override de métodos de interface).

---

## 6. BOAS PRÁTICAS E DESENHOS POLIMÓRFICOS

### 6.1. Evitando Abusos de Sobrecarga que Confundem a Leitura do Código
Às vezes, programadores sobrecarregam vários métodos usando o mesmo nome para funcionalidades que não são tão relacionadas, gerando confusão.  
**Exemplo problemático**:
```java
public class Log {
    public void log(String mensagem) { ... }
    public void log(int codigo) { ... }
    public void log(Exception e) { ... }
    public void log(String mensagem, int codigo, Exception e) { ... }
    // ...
}
```
Em demasia, fica difícil saber qual método usar. Talvez seja melhor renomear alguns métodos para torná-los mais claros (`logMensagem`, `logCodigo`, `logErro`, etc.).

### 6.2. Planejando Sobrescrita de Métodos Importantes (equals, toString etc.)
Sobrescrever `equals` e `hashCode` adequadamente é crucial para objetos que serão inseridos em coleções baseadas em hash (`HashSet`, `HashMap`) ou para comparação de igualdade. Polimorfismo se aplica aqui, mas precisamos seguir **contratos** do Java (como reflexividade, simetria, transitividade em `equals`).

### 6.3. Patterns como Strategy e Template Method que se Apoiam em Polimorfismo
- **Strategy**: definimos uma interface “Strategy” com um método como `executar(...)`; várias estratégias concretas sobrescrevem esse método. Em tempo de execução, escolhemos a implementação de `Strategy` e chamamos `executar()`.  
- **Template Method**: uma superclasse define um método “template” que chama métodos “ganchos” (abstract ou virtual), e subclasses sobrescrevem esses ganchos para customizar partes do algoritmo.

### 6.4. Acoplamento e Fragilidade: Quando o Polimorfismo Gera Problemas
- Polimorfismo “oculto” pode gerar **acoplamento** se a subclasse não respeitar o contrato da superclasse. Liskov Substitution Principle (LSP) é fundamental para não quebrar a coerência.  
- Uma subclasse que sobrescreve métodos de formas contraditórias pode surpreender partes do código que esperavam o comportamento original.

---

## 7. DEMONSTRAÇÕES DE CÓDIGO: PASSO A PASSO

### 7.1. Jogo de RPG com Polimorfismo (Monstro, Dragao, Goblin, Esqueleto)

#### 7.1.1. Overloading de Construtores e Métodos de Ataque
```java
public class Monstro {
    protected int vida;
    protected int ataque;

    // Construtor padrão
    public Monstro() {
        this.vida = 100;
        this.ataque = 10;
    }

    // Sobrecarga de construtor
    public Monstro(int vida, int ataque) {
        this.vida = vida;
        this.ataque = ataque;
    }

    // Método de ataque (polimorfismo em tempo de execução, se sobrescrito)
    public void atacar(Monstro alvo) {
        alvo.vida -= this.ataque;
        System.out.println(getClass().getSimpleName() + " atacou " 
                           + alvo.getClass().getSimpleName());
    }
}

public class Dragao extends Monstro {
    private int fogo; // dano adicional

    public Dragao() {
        super(200, 25);  // construtor da superclasse
        this.fogo = 10;
    }

    // Overloading: outro construtor
    public Dragao(int vida, int ataque, int fogo) {
        super(vida, ataque);
        this.fogo = fogo;
    }

    @Override
    public void atacar(Monstro alvo) {
        // chamo método da super para aplicar dano básico
        super.atacar(alvo);
        // adiciono fogo
        alvo.vida -= this.fogo;
        System.out.println("Dano extra de fogo = " + this.fogo);
    }
}

public class Goblin extends Monstro {
    public Goblin() {
        super(50, 5);
    }

    @Override
    public void atacar(Monstro alvo) {
        super.atacar(alvo);
        System.out.println("Goblin dá gargalhada maligna! (Polimorfismo em ação)");
    }
}

public class Esqueleto extends Monstro {
    public Esqueleto() {
        super(70, 8);
    }
    // não sobrescreve atacar() => comportamento padrão
}
```

#### 7.1.2. Override de Métodos “agredir()”
Aqui, `atacar(Monstro alvo)` já é sobrescrito em `Dragao`, `Goblin`, etc. Cada classe “faz algo extra”.  
Podemos ver polimorfismo em tempo de compilação (overloading de construtores) e em tempo de execução (override de `atacar`).

Uso:
```java
public class TesteRPG {
    public static void main(String[] args) {
        Monstro drg = new Dragao();
        Monstro gob = new Goblin();
        Monstro esk = new Esqueleto();

        drg.atacar(gob);  // chama Dragao.atacar
        gob.atacar(esk);  // chama Goblin.atacar
        esk.atacar(drg);  // chama Monstro.atacar (versão padrão)
    }
}
```
**Binding** do método `atacar()` depende do tipo real do objeto: `Dragao`, `Goblin` ou `Esqueleto`.

### 7.2. Aplicação Financeira Polimórfica (Conta, ContaCorrente, ContaPoupanca, Investimento)

#### 7.2.1. Sobreposição de Métodos de Rendimento
```java
public class Conta {
    protected double saldo;

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        saldo -= valor;
    }

    // em uma aplicação real, teria verificações, etc.
    // ...
}

public class ContaPoupanca extends Conta {
    @Override
    public void depositar(double valor) {
        super.depositar(valor);
        System.out.println("ContaPoupanca: depositou valor com extra de cashback?...");
        // poderia ter uma lógica adicional
    }

    public void renderJuros() {
        saldo += saldo * 0.01;
    }
}

public class ContaCorrente extends Conta {
    @Override
    public void sacar(double valor) {
        // taxinha
        double taxa = 0.50;
        super.sacar(valor + taxa);
    }
}
```
Agora, dependendo se é `new ContaPoupanca()` ou `new ContaCorrente()`, a chamada a `sacar()` ou `depositar()` faz coisas diferentes (override).

#### 7.2.2. Sobrecarga para Depósitos Diferenciados
Podemos ter sobrecarga:
```java
public class Conta {
    public void depositar(double valor) {
        saldo += valor;
    }

    // Sobrecarga: depositar com 'descrição' ou outro parâmetro
    public void depositar(double valor, String descricao) {
        this.depositar(valor); // reutiliza a lógica
        System.out.println("Depósito: " + descricao);
    }
}
```
Isso é polimorfismo de **sobrecarga** (compile-time). Se chamamos `conta.depositar(100.0)`, usamos um método; se chamamos `conta.depositar(100.0, "Salário")`, invocamos outro.

---

## 8. EXERCÍCIOS DE FIXAÇÃO E PROJETOS SUGERIDOS

### 8.1. Exercícios Simples

1. **Criar uma classe “Calculadora”**  
   - Que tenha métodos `somar(int a, int b)`, `somar(double a, double b)`, `somar(int a, int b, int c)` para demonstrar **overloading**.  
   - Testar chamando cada versão para ver como a sobrecarga funciona.

2. **Classe “Forma” e subclasse “Quadrado”**  
   - `Forma` com método `calcularArea()` e `calcularPerimetro()`.  
   - `Quadrado` sobrescreve esses métodos de maneira apropriada.  
   - Testar polimorfismo chamando `Forma f = new Quadrado(...)`.

### 8.2. Exercícios Intermediários

3. **Classe “Pessoa”**  
   - Sobrecarga de construtores: um sem parâmetros, outro com `(String nome)`, outro com `(String nome, int idade)`.  
   - Duas subclasses: `Aluno` e `Professor`, sobrescrevendo `public String getCargo()` por exemplo.  
   - Testar polimorfismo criando `Pessoa p = new Aluno(...)`, `Pessoa p2 = new Professor(...)` e chamando `p.getCargo()`.

4. **Hierarquia “Veiculo”**  
   - `Veiculo` com método `acelerar()`.  
   - Subclasses: `Carro`, `Moto`, `Caminhao`. Cada um sobrescreve `acelerar()` de forma distinta.  
   - Criar um método `testarVeiculo(Veiculo v)` que chame `v.acelerar()`. Demonstrar polimorfismo.  
   - Adicionar sobrecarga: `acelerar(int incremento)`, `acelerar(double incremento)`.

---

## 9. REFERÊNCIAS E LEITURAS ADICIONAIS

1. **Documentação Oficial da Oracle**  
   - *Trail: Learning the Java Language – Overloading Methods and Constructors*  
   - *Trail: Learning the Java Language – Overriding Methods, Polymorphism*  
2. **“Effective Java”** – *Joshua Bloch*  
   - Há menções a boas práticas ao sobrescrever métodos `equals`, `hashCode`, `toString`.  
3. **“Head First Java”** – *Kathy Sierra, Bert Bates*  
   - Aborda de maneira didática polimorfismo e como usá-lo em exemplos reais.  
---

## ENCERRAMENTO

Nesta aula, aprofundamos:

1. **Polimorfismo em Tempo de Compilação (Overloading)**  
   - Diferentes métodos com o mesmo nome, mas assinaturas distintas, resolvidos no momento da compilação.  
2. **Polimorfismo em Tempo de Execução (Override)**  
   - Subclasses (ou implementações de interface) sobrescrevem métodos herdados, e o Java usa o tipo real do objeto para decidir em runtime qual método chamar (dynamic binding).  
3. **Invocação de Métodos Polimórficos**  
   - Como a referência a uma superclasse ou interface pode apontar para diversas subclasses e acionar comportamentos específicos, sem ifs ou casts manuais.  
4. **Binding Dinâmico**  
   - Essência do polimorfismo de subtipo: a decisão de qual método concreto chamar é tomada em tempo de execução, com base na instância real.  
5. **Exemplos Avançados**  
   - Demonstrações de reuso de código em hierarquias de classes como “FormaGeometrica” ou “Funcionario”.  
   - Uso de interfaces para polimorfismo.  
6. **Boas Práticas**  
   - Não exagerar em sobrecarga, planejar sobrescrita com contrato claro, conhecer LSP.  
   - Patterns que se apoiam em polimorfismo (Strategy, Template Method).

Com isso, você tem uma visão completa de **como** e **por que** usar Polimorfismo em Java, tanto em **tempo de compilação** quanto em **tempo de execução**, e como isso se conecta a uma programação orientada a objetos mais flexível, extensível e elegante.
