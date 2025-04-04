# Abstração e Encapsulamento

## Sumário

1. [Visão Geral: Por que Abstração e Encapsulamento são importantes?](#1)  
   1.1. Contexto: Pilares essenciais da POO  
   1.2. Relação com manutenção e extensibilidade de código  
   1.3. Consequências de ignorar esses princípios  

2. [Abstração](#2)  
   2.1. O que é Abstração na Programação Orientada a Objetos?  
   2.2. Níveis de Abstração  
   2.3. Formas de Aplicar Abstração em Java  
   2.4. Abstração e Generalização x Especialização  
   2.5. Classes Abstratas e Interfaces (abordagem detalhada)  
       2.5.1. Classes Abstratas: características, uso e exemplos  
       2.5.2. Interfaces: contrato, implementação padrão (default methods), boas práticas  
   2.6. Casos de Uso Reais e Exemplos Práticos em Java  
   2.7. Más Práticas que Quebram o Princípio de Abstração  

3. [Encapsulamento](#3)  
   3.1. Definição: Ocultação de Dados x Proteção de Comportamentos  
   3.2. Como o Encapsulamento se Manifesta em Java  
   3.3. Modificadores de Acesso (public, private, protected, “default”) e Seus Efeitos  
   3.4. Getters, Setters e Outras Formas de Controlar Acesso     
   3.5. Boas Práticas e Padrões de Projeto Relacionados ao Encapsulamento

4. [Exemplos Avançados e Estudos de Caso](#4)  
   4.1. Exemplo Avançado: Sistema de Pagamentos  
       4.1.1. Abstração para Diferentes Formas de Pagamento  
       4.1.2. Encapsulamento de Processos Internos e Segurança   

5. [Boas Práticas e Antipadrões](#5)     
   5.1. Antipadrões de Abstração: Classes Anêmicas, Objeto Deus, etc.  
   5.2. Antipadrões de Encapsulamento: Exposição de Campos, “Setters para Tudo”  
   5.3. Reflexão sobre Coesão e Acoplamento  

6. [Exemplos de Código Comentados](#6)  
   6.1. Implementando Classes Abstratas e Interfaces em um Cenário Concreto  
   6.2. Restringindo Acesso de Atributos e Métodos (Encapsulamento Forte)  
   6.3. Transformando um Código “Estrutural” em Código “Encapsulado e Abstrato”  

7. [Exercícios](#7)  

8. [Discussão e Reflexões Finais](#8)  

9. [Referências e Leituras Sugeridas](#9)

---

## 1. VISÃO GERAL: POR QUE ABSTRAÇÃO E ENCAPSULAMENTO SÃO IMPORTANTES?

Nesta aula, focaremos especificamente em **Abstração** e **Encapsulamento**, dois pilares essenciais da Programação Orientada a Objetos (POO). Embora já tenhamos falado sobre classes, objetos, atributos, métodos, estado e comportamento em aulas anteriores, agora vamos **aprofundar** como esses dois princípios norteiam o design de software robusto, flexível e manutenível.

### 1.1. Contexto: Pilares Essenciais da POO
A POO é frequentemente associada a quatro pilares (ou princípios) fundamentais: **Abstração, Encapsulamento, Herança e Polimorfismo**. Entre eles, **Abstração** e **Encapsulamento** são a base para reduzir complexidade e isolar detalhes internos.  
- **Abstração**: Foca em “o que” um objeto pode fazer, não “como” ele faz internamente.  
- **Encapsulamento**: Garante que os detalhes internos fiquem protegidos, expondo apenas o necessário e controlando o acesso ao estado do objeto.

### 1.2. Relação com Manutenção e Extensibilidade de Código
- **Manutenção**: Quando um software está bem encapsulado, alterações em detalhes internos de uma classe não impactam outras partes do sistema.  
- **Extensibilidade**: A abstração correta permite criar novas funcionalidades, classes ou módulos sem “quebrar” o restante do código, pois as dependências se dão por “interfaces” ou “contratos”, não por implementações concretas.

### 1.3. Consequências de Ignorar Esses Princípios
- **Exposição Excessiva**: Quando uma classe não encapsula devidamente seus dados, qualquer outro código pode alterá-los de maneira arbitrária, gerando bugs difíceis de rastrear.  
- **Alta Complexidade**: Sem abstração, cada parte do software precisa entender detalhes internos das demais, resultando em altíssimo acoplamento.  
- **Dificuldade de Evolução**: Falta de encapsulamento e abstração leva a um sistema onde qualquer mudança simples propaga efeitos colaterais em cadeia.

---

## 2. ABSTRAÇÃO

### 2.1. O que é Abstração na Programação Orientada a Objetos?
**Abstração** é o ato de representar conceitos complexos de forma simplificada, exibindo apenas as características essenciais, relevantes para determinado contexto, e omitindo detalhes desnecessários. Em POO, isso se traduz em **classes e interfaces** que focam nos comportamentos e características importantes para o problema, sem expor (ou sequer conter) todos os detalhes internos de implementação.

A abstração pode ser entendida como uma barreira conceitual que separa:
- **O que** uma entidade (classe/objeto) faz.  
- **Como** ela faz internamente.

Se você imaginar um carro, a abstração é: “Ele acelera, freia, tem velocidade, marca e modelo.” Você não precisa saber como funciona exatamente o motor, o sistema de injeção eletrônica, etc., para utilizar um carro. Isso é abstração na prática.

### 2.2. Níveis de Abstração
Em engenharia de software, pode-se falar em vários níveis de abstração, desde:
1. **No nível de negócio**: Conceitos como “pedido”, “fatura”, “cliente” (não importa ainda como iremos gravar no banco de dados).  
2. **No nível de design de classes**: Definimos classes, interfaces e métodos que traduzem o domínio para código.  
3. **No nível de implementação**: Cada classe concreta implementa métodos e lida com detalhes, mas para o usuário (ou para as demais classes), esse detalhe está “invisível”.

### 2.3. Formas de Aplicar Abstração em Java
- **Classes Abstratas**: Permitem definir uma classe incompleta, que contém métodos abstratos (sem corpo) ou parcialmente implementados, servindo como “modelo” para subclasses.  
- **Interfaces**: Espécie de “contrato” que obriga quem a implementa a fornecer determinadas funcionalidades. Também uma forma de abstração, pois você não define “como” vai funcionar, apenas “o que deve existir”.  
- **Ocultação de Implementação Interna**: As classes concretas podem ter detalhes privados, visíveis apenas a elas mesmas.

### 2.4. Abstração e Generalização x Especialização
Dentro de POO, existe a noção de que podemos criar abstrações mais gerais (ex.: classe `FormaGeometrica`) e depois especializá-las (ex.: `Circulo`, `Retangulo`, `Triangulo`). Essa hierarquia “geral -> específico” é essencial para reuso de código e para simplificar a visão do problema.  

**Generalização**: extrair o que há em comum em diferentes classes e colocar em uma classe ou interface mais abstrata.  
**Especialização**: detalhar as diferenças em subclasses específicas.

### 2.5. Classes Abstratas e Interfaces (abordagem detalhada)

#### 2.5.1. Classes Abstratas: Características, Uso e Exemplos
- Uma classe abstrata **não pode** ser instanciada diretamente.  
- Pode conter métodos concretos (com implementação) e métodos abstratos (sem implementação, a serem implementados pelas subclasses).  
- Exemplo:

```java
public abstract class FormaGeometrica {
    private String cor;

    public FormaGeometrica(String cor) {
        this.cor = cor;
    }

    public String getCor() {
        return cor;
    }

    // Método abstrato: as subclasses serão obrigadas a implementar
    public abstract double calcularArea();
}
```
Aqui, `FormaGeometrica` estabelece a obrigatoriedade de um método `calcularArea()`, mas não diz **como**. Isso é abstração. `Circulo`, `Retangulo` etc. terão que fornecer suas próprias fórmulas.

**Por que usar classes abstratas?**  
- Quando se quer fornecer **parte** da implementação comum, mas garantir que certas partes sejam definidas pelas subclasses.  
- Para **proibir** criação de instâncias diretas, pois a classe é conceitualmente genérica demais.

#### 2.5.2. Interfaces: Contrato, Implementação Padrão (Default Methods), Boas Práticas
- Uma `interface` em Java define **métodos** (públicos e abstratos por padrão), mas não a implementação (antes do Java 8).  
- A partir do Java 8, surgiram **default methods**, que permitem ter algum código padrão na interface (mantendo a coerência com a ideia de “contrato + implementação opcional”).  
- Interfaces podem fornecer uma forma de “múltipla especialização” sem herança múltipla (Java não permite herdar de mais de uma classe, mas permite implementar diversas interfaces).

Exemplo simples de interface:

```java
public interface Processavel {
    void processar();
}
```

Qualquer classe que implemente `Processavel` **precisa** ter o método `processar()`. Não importam os detalhes, esse é o contrato. Isso é abstração pura.

**Default methods** (desde Java 8):

```java
public interface Ordenavel {
    void ordenar();

    default void exibirMensagem() {
        System.out.println("Iniciando ordenação...");
    }
}
```
As classes que implementam `Ordenavel` têm a opção de usar `exibirMensagem()` como está, ou sobrescrevê-lo.

### 2.6. Casos de Uso Reais e Exemplos Práticos em Java

1. **Frameworks**: Geralmente expõem interfaces para “plugins”, “listeners” e “services”. Os detalhes de execução ficam encapsulados no framework; o desenvolvedor somente implementa a interface.  
2. **Biblioteca de Conexão com Banco de Dados**: Pode ter classes abstratas definindo a lógica de conexão comum, e subclasses que implementam detalhes de cada banco (MySQL, PostgreSQL etc.).  
3. **Processamento de Pagamentos**: Pode haver uma interface `MetodoPagamento` com um método `efetuarPagamento()`. Cada classe concreta (Cartão, Boleto, Pix) implementa esse método à sua maneira.

### 2.7. Más Práticas que Quebram o Princípio de Abstração
- **Revelar detalhes de implementação nos nomes ou nas assinaturas**: por exemplo, expor que você está usando “HashMap” em vez de um simples “Map” no parâmetro de um método, forçando o uso de uma implementação específica.  
- **Criar métodos muito específicos em interfaces gerais**: “empurrar” para a interface algo que só faz sentido para 1 ou 2 implementações é mau sinal.  
- **Não dividir interfaces “gigantes”**: viola o princípio de “Interface Segregation” (ISP, do SOLID).

---

## 3. ENCAPSULAMENTO

### 3.1. Definição: Ocultação de Dados x Proteção de Comportamentos
**Encapsulamento** significa **limitar o acesso** aos detalhes internos de um objeto e **fornecer** uma interface de acesso controlado para manipular ou ler esses detalhes. É um dos principais mecanismos para garantir **segurança** (no sentido de evitar interações indevidas) e **consistência** do estado do objeto.

Na prática, encapsulamento envolve:
- Tornar atributos `private` (ou `protected`)  
- Fornecer métodos `public` de acesso (getters/setters) **ou** métodos que executam ações internas (e alteram estado) de maneira controlada.

### 3.2. Como o Encapsulamento se Manifesta em Java
Em Java, o encapsulamento se apoia em:
- **Modificadores de Acesso**: `public`, `private`, `protected` e “default”.  
- **Pacotes**: Membros “default” só são visíveis dentro do mesmo pacote.  
- **Classes**: Podemos também restringir acesso a classes inteiras se elas não precisarem ser vistas fora de determinado pacote (classes “package-private”).

### 3.3. Modificadores de Acesso (public, private, protected, “default”) e Seus Efeitos
1. **public**: Acesso de qualquer lugar.  
2. **private**: Acesso apenas de dentro da própria classe.  
3. **protected**: Acesso por classes do mesmo pacote ou por subclasses (mesmo em pacotes diferentes).  
4. **default (sem modificador)**: Acesso somente dentro do mesmo pacote (também chamado “package-private”).

**Exemplo**:

```java
public class Pessoa {
    private String nome;         // somente a classe "Pessoa" acessa
    protected int idade;         // acesso no mesmo pacote OU subclasses
    String endereco;             // default, acesso apenas no mesmo pacote
    public String nacionalidade; // pode ser acessado de qualquer lugar
}
```

### 3.4. Getters, Setters e Outras Formas de Controlar Acesso
A forma clássica de encapsulamento é declarar atributos como `private` e fornecer métodos “get” e “set”. Porém, nem sempre isso é a melhor abordagem. Às vezes, é melhor **não expor** sets e, em vez disso, ter métodos como `depositar(double valor)` ou `trocarSenha(String senhaAntiga, String novaSenha)`. Assim, o estado só muda de forma controlada e validada.

### 3.5. Boas Práticas e Padrões de Projeto Relacionados ao Encapsulamento
- **Encapsular variações**: Se há um trecho de lógica que tende a mudar com frequência, encapsule-o numa classe ou método privado, expondo apenas um método público estável.  
- **Lei de Demeter** (“Não fale com estranhos”): Cada objeto deve interagir apenas com seus “parentes” próximos, evitando “encadear” muitas chamadas de métodos de outros objetos retornados por getters.  

---

## 4. EXEMPLOS AVANÇADOS E ESTUDOS DE CASO

### 4.1. Exemplo Avançado: Sistema de Pagamentos

#### 4.1.1. Abstração para Diferentes Formas de Pagamento
Imagine um sistema que tenha que lidar com cartão de crédito, boleto, PIX, etc. Usamos uma **interface** `ProcessadorPagamento`:

```java
public interface ProcessadorPagamento {
    boolean processar(double valor);
}
```

Cada implementação interna (cartão, boleto, pix, etc.) **encapsula** seus detalhes. Assim, o resto do sistema fica abstrato: só chama `processar(valor)`.

#### 4.1.2. Encapsulamento de Processos Internos e Segurança
Por exemplo, no caso de um “ProcessadorCartaoCredito”, podemos ter:

```java
public class ProcessadorCartaoCredito implements ProcessadorPagamento {
    private String numeroCartao;
    private String titular;
    private String cvv;

    public ProcessadorCartaoCredito(String numeroCartao, String titular, String cvv) {
        // Poderíamos validar dados aqui
        this.numeroCartao = numeroCartao;
        this.titular = titular;
        this.cvv = cvv;
    }

    @Override
    public boolean processar(double valor) {
        // Encapsula a lógica de comunicação com a operadora
        // ...
        return true; // ou false se der erro
    }
}
```
Note que `numeroCartao` e `cvv` não são expostos para ninguém de fora. A lógica de como efetuar o pagamento com cartão fica **encapsulada** dentro dessa classe. O sistema só sabe que, se chamar `processar(100.0)`, tentará pagar R$ 100,00.

---

## 5. BOAS PRÁTICAS E ANTIPADRÕES

### 5.1. Antipadrões de Abstração: Classes Anêmicas, Objeto Deus, etc.
- **Classe Anêmica**: é uma classe que só tem atributos e getters/setters, mas nenhuma lógica. Para alguns, isso viola a ideia de “encapsular comportamento junto com dados.”  
- **Objeto Deus**: quando uma só classe concentra responsabilidades demais, o que também destrói a ideia de abstração. É um “monstro” que conhece e faz tudo.

### 5.2. Antipadrões de Encapsulamento: Exposição de Campos, “Setters para Tudo”
- **Campos Públicos**: Deixar atributos como `public` (ex.: `public int saldo;`) é normalmente contraindicado, pois qualquer um pode alterar `saldo` sem controle.  
- **Setters para Tudo**: Colocar `set` em todos os atributos sem lógica ou restrições é quase tão ruim quanto deixar público. Torna as classes expostas a alterações indevidas.

### 5.3. Reflexão sobre Coesão e Acoplamento
- **Coesão**: Alta coesão significa que a classe encapsula comportamentos e dados intimamente relacionados.  
- **Acoplamento**: Baixo acoplamento ocorre quando as classes se comunicam por interfaces/contratos e não acessam detalhes internos umas das outras.

---

## 6. EXEMPLOS DE CÓDIGO COMENTADOS

### 6.1. Implementando Classes Abstratas e Interfaces em um Cenário Concreto
**Cenário**: Uma aplicação de logística, onde temos diferentes tipos de “Veículo” (Caminhão, Van, Moto) para entrega de produtos.

1. **Classe Abstrata** `Veiculo`:
   ```java
   public abstract class Veiculo {
       private String placa;

       public Veiculo(String placa) {
           this.placa = placa;
       }

       public String getPlaca() {
           return placa;
       }

       // Método abstrato - cada Veiculo específico define como se movimenta
       public abstract void mover();
   }
   ```

2. **Subclasse** `Caminhao`:
   ```java
   public class Caminhao extends Veiculo {
       public Caminhao(String placa) {
           super(placa);
       }

       @Override
       public void mover() {
           System.out.println("Caminhão " + getPlaca() + " está se movendo a 80km/h!");
       }
   }
   ```

3. **Outra Subclasse** `Moto`:
   ```java
   public class Moto extends Veiculo {
       public Moto(String placa) {
           super(placa);
       }

       @Override
       public void mover() {
           System.out.println("Moto " + getPlaca() + " está se movendo rapidamente pela via.");
       }
   }
   ```

4. **Interface** `Transportador`:
   ```java
   public interface Transportador {
       void carregar(String mercadoria);
       void descarregar(String mercadoria);
   }
   ```

5. **Classe** `Caminhao` também implementa `Transportador` (além de estender `Veiculo`):
   ```java
   public class Caminhao extends Veiculo implements Transportador {
       public Caminhao(String placa) {
           super(placa);
       }

       @Override
       public void mover() {
           System.out.println("Caminhão " + getPlaca() + " está se movendo a 80km/h!");
       }

       @Override
       public void carregar(String mercadoria) {
           System.out.println("Carregando " + mercadoria + " no caminhão de placa " + getPlaca());
       }

       @Override
       public void descarregar(String mercadoria) {
           System.out.println("Descarregando " + mercadoria + " do caminhão de placa " + getPlaca());
       }
   }
   ```

**Observe**:  
- `Veiculo` é uma **classe abstrata**.  
- `Transportador` é uma **interface** que define o contrato de como se carrega e descarrega.  
- `Caminhao` exemplifica **herança (Veiculo)** + **implementação de interface** (Transportador).  
- A noção de “como o caminhão se move” e “como carrega mercadoria” está encapsulada na subclasse.

### 6.2. Restringindo Acesso de Atributos e Métodos (Encapsulamento Forte)
Dado:

```java
public class Pedido {
    private List<Item> itens = new ArrayList<>();
    private double valorTotal;

    // Acesso somente via métodos controlados
    public void adicionarItem(Item i) {
        if(i != null) {
            itens.add(i);
            valorTotal += i.getPreco();
        }
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    // ...
}
```

- `itens` e `valorTotal` são `private`.  
- Só é possível alterar `valorTotal` chamando `adicionarItem()`, que ajusta a soma com base no `preco`.  
- Isso **garante consistência** (não se corre o risco de alguém “esquecer de somar” ao valor total).

### 6.3. Transformando um Código “Estrutural” em Código “Encapsulado e Abstrato”
Suponha que antes o código era assim (má prática):

```java
public class Pedido {
    public ArrayList<Item> itens;
    public double valorTotal;
}
```
Qualquer classe podia fazer:
```java
Pedido p = new Pedido();
p.itens.add(new Item("Café", 10.0));
p.valorTotal = 100000; // Bagunça total
```
**Não há controle**, qualquer lugar do sistema pode estragar o `valorTotal`.  

Encapsulando, criamos métodos e escondemos detalhes, como mostrado no exemplo anterior. Isso **reflete** o princípio: “apenas a classe ‘Pedido’ sabe como atualizar o valor total corretamente”.

---

## 7. EXERCÍCIOS

### Exercício 1 – Criando uma Hierarquia com Classe Abstrata

1. **Cenário**: Imagine que você está desenvolvendo um sistema para controlar diferentes tipos de “Jogadores” de um game (ex.: Jogador de futebol, Jogador de xadrez, Jogador de vôlei).  
2. **Tarefa**:  
   - Crie uma **classe abstrata** chamada `Jogador` que contenha:  
     - Atributo `nome` (String).  
     - Construtor que receba `nome`.  
     - Um método abstrato `treinar()`.  
     - Um método concreto `getNome()` que retorne o nome do jogador.  
   - Em seguida, crie **duas subclasses** concretas: `JogadorFutebol` e `JogadorXadrez`. Cada uma deve implementar o método `treinar()` de forma distinta (por exemplo, `JogadorFutebol` treina “chutes ao gol” e “condicionamento físico”, enquanto `JogadorXadrez` treina “análise de aberturas”, “final de jogo” etc.).  
3. **Objetivo**:  
   - Praticar a ideia de **classe abstrata** para representar um conceito genérico (`Jogador`) que não deve ser instanciado diretamente.  
   - Ver como cada subclasse dá sua **própria implementação** ao método abstrato `treinar()`.  
4. **Dicas**:  
   - Mantenha o atributo `nome` como `private`, encapsulado na classe `Jogador`. Se necessário, forneça `getNome()` para permitir acesso controlado.  
   - Teste criando uma classe `TesteJogadores` com um método `main()`, instanciando `JogadorFutebol` e `JogadorXadrez`, chamando o método `treinar()` em cada instância.

---

### Exercício 2 – Encapsulando o Estado de uma “Agenda de Contatos”

1. **Cenário**: Você está construindo uma **Agenda** que guarda objetos `Contato`. Cada `Contato` tem `nome`, `email`, `telefone`.  
2. **Problema**: Queremos garantir que, ao cadastrar um contato, o campo `email` seja válido e o `telefone` possua um mínimo de dígitos. Não queremos deixar esses campos públicos.  
3. **Tarefa**:  
   - Crie a classe `Contato` com todos os atributos `private`.  
   - Forneça construtor(es) e os **setters** de modo que, antes de atribuir `email`, seja realizada uma pequena validação (ex.: verificar se contém “@”). Antes de atribuir o `telefone`, verifique se tem pelo menos 8 caracteres (ou o formato adequado). Caso contrário, lance uma exceção ou atribua valores padrão.  
   - Crie a classe `Agenda`, contendo um `List<Contato>` privado. Ofereça métodos públicos como `adicionarContato(Contato c)` e `removerContato(String email)` (removendo pelo e-mail), sempre aplicando encapsulamento (ou seja, ninguém altera a lista internamente sem passar por esses métodos).  
4. **Objetivo**:  
   - Refletir sobre **encapsulamento** de atributos (`nome`, `email`, `telefone`).  
   - Garantir validação de dados dentro dos setters, evitando que outras partes do código “quebrem” a coerência do objeto.  
   - Restringir o acesso à lista de contatos na `Agenda`, expondo apenas operações controladas.  
5. **Dicas**:  
   - Pode ser interessante criar um método `pesquisarContato(String email)` dentro de `Agenda`, para retornar o contato correspondente. Esse método deve ser `public`, mas a lista em si (`List<Contato>`) continua `private`.  
   - Tente não fornecer um “getter” que retorne a lista inteira, pois isso pode permitir que o chamador manipule a lista diretamente. Uma alternativa é retornar uma **cópia** ou um **unmodifiableList**.

---

### Exercício 3 – Interface para “Processar Documentos”

1. **Cenário**: Em um escritório virtual, há diferentes tipos de documentos que precisam ser “processados” (ex.: reler, categorizar, extrair texto). Por outro lado, existem documentos de diferentes formatos (PDF, DOC, TXT).  
2. **Tarefa**:  
   - Crie uma `interface` chamada `ProcessadorDocumento` que defina um método `processarDocumento(String caminhoArquivo)`.  
   - Crie classes concretas que **implementam** essa interface: `ProcessadorPDF`, `ProcessadorDOC`, `ProcessadorTXT`. Cada uma deve **encapsular** a forma como processa ou lê aquele tipo de arquivo.  
   - No método `main()`, crie um código que recebe como parâmetro o tipo do arquivo e instancia o `Processador` adequado. Em seguida, chama `processarDocumento()`.  
3. **Objetivo**:  
   - Exercitar a **abstração** via `interface`: a aplicação principal não sabe detalhes de como cada documento é tratado, apenas chama `processarDocumento()`.  
   - Cada classe concreta implementa o comportamento (encapsula o parsing do arquivo).  
4. **Dicas**:  
   - Faça o corpo do método `processarDocumento()` apenas imprimir simulações, como “Lendo PDF…” ou “Processando DOC…”.  
   - Mostre no console que você consegue trocar `ProcessadorPDF` por `ProcessadorDOC` sem mudar a lógica do código principal (pois tudo depende da `interface`).

---

### Exercício 4 – Classe Abstrata vs. Interface na Modelagem de “Veículos Aquáticos”

1. **Cenário**: Você possui uma classe base `VeiculoMaritimo` que prevê atributos como `nomeEmbarcacao`, `capacidadePassageiros` e métodos como `navegar()` e `ancorar()`. Você também quer que algumas embarcações sejam “Pesqueiras”, ou seja, que tenham um método `pescarPeixe()`.  
2. **Tarefa**:  
   - Crie uma **classe abstrata** `VeiculoMaritimo` com:  
     - `nomeEmbarcacao` (private)  
     - `capacidadePassageiros` (private)  
     - Construtor(es) para inicializar esses atributos.  
     - Método abstrato `navegar()` e um método concreto `ancorar()`.  
   - Crie duas subclasses concretas: `NavioDeGuerra` e `CruzeiroTuristico`, cada qual implementando `navegar()` de forma distinta (imprima mensagens condizentes com o uso).  
   - Agora crie uma `interface` chamada `Pesqueiro`, com método `pescarPeixe()`.  
   - Crie uma classe `BarcoPesqueiro` que **estenda** `VeiculoMaritimo` **e** implemente `Pesqueiro`.  
3. **Objetivo**:  
   - Ver como uma **classe abstrata** é usada para unificar comportamento de embarcações, mas não pode ser instanciada diretamente.  
   - Ver como uma **interface** pode adicionar capacidade extra (“pescarPeixe()”) que nem todas as embarcações possuem.  
4. **Dicas**:  
   - Demonstre que você pode ter um `ArrayList<VeiculoMaritimo>` que guarde `NavioDeGuerra`, `CruzeiroTuristico` e `BarcoPesqueiro`. Porém, ao chamar `pescarPeixe()`, você precisa ter referências do tipo `Pesqueiro`.  
   - Isso exemplifica **polimorfismo** e **abstração**.  

---

### Exercício 5 – Comparando Encapsulamento “Forte” e “Fraco”

1. **Cenário**: Você suspeita que no seu projeto algumas classes expõem atributos com `protected` mesmo quando não há herança ou necessidade real. Outras usam `private` e métodos de domínio.  
2. **Tarefa**:  
   - Pegue uma classe hipotética (ex.: `Cliente`) que está assim:  
     ```java
     public class Cliente {
         protected String nome;
         protected String cpf;
         protected int idade;
     }
     ```
   - Reescreva-a usando **encapsulamento forte** (atributos `private`) e criando **métodos específicos** para alterações relevantes (ex.: `atualizarDados(String nome, String cpf, int idade)` com validações).  
3. **Objetivo**:  
   - Conscientizar sobre o uso indevido de `protected` e a falta de **motivos** para isso em classes que não são projetadas para herança, levando a violações de encapsulamento.  
4. **Dicas**:  
   - Justifique **por que** é preferível `private` em muitos cenários, permitindo a classe controlar melhor seu estado.  
   - Se `nome` ou `cpf` nunca mudam, talvez nem mesmo haja um setter público. A classe poderia receber tais valores apenas no construtor, e pronto.

---

## 8. DISCUSSÃO E REFLEXÕES FINAIS

- **Abstração** e **Encapsulamento** são **pilares fundamentais** de POO que trabalham em conjunto para criar sistemas mais robustos e organizados.  
- **Abstração** nos permite pensar em termos de “contratos” e “conceitos” ao invés de detalhes de implementação, promovendo baixo acoplamento.  
- **Encapsulamento** garante que o estado interno de um objeto só possa ser manipulado de maneira controlada e coerente, evitando efeitos colaterais e erros de manutenção.  
- Em aplicações grandes (enterprise, microservices), esses conceitos se aplicam não só a classes, mas a módulos, serviços, APIs, garantindo independência, segurança e clareza.  
- As **más práticas** (exposição de atributos, interfaces superinchadas, etc.) podem comprometer a modularidade, escalabilidade e a testabilidade do software.  
- As **boas práticas** (usar classes abstratas, interfaces, métodos de acesso bem definidos, pacotes e módulos com visibilidade restrita) tornam o código mais profissional e duradouro.

---

## 9. REFERÊNCIAS E LEITURAS SUGERIDAS

1. **“Effective Java”**, Joshua Bloch – Diversos itens discutem práticas de criação de interfaces, classes, encapsulamento.  
2. **Documentação Oficial da Oracle (Java)**:  
   - Seção “Trail: Learning the Java Language” – Abstraction, Interfaces, Classes etc.  
   - Seção de “Java Platform Module System” (a partir do Java 9).  
3. **Design Patterns** (Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides – GoF): Alguns padrões reforçam ou fazem uso direto de abstração e encapsulamento (Factory Method, Template Method, Strategy, etc.).

---

### PALAVRAS FINAIS

Abstração e encapsulamento não são apenas “conceitos teóricos”: eles se manifestam constantemente em qualquer sistema orientado a objetos, seja ele pequeno (um simples projeto universitário) ou de grande porte (frameworks e plataformas corporativas). Dominar essas práticas é essencial para produzir código confiável, de fácil manutenção e com boa capacidade de evolução ao longo do tempo.