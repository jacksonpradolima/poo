# SUMÁRIO GERAL

1. **Panorama do Design Orientado a Objetos**  
2. **Coesão**  
3. **Acoplamento**  
4. **Coesão × Acoplamento: a dança dos opostos**  
5. **Princípios SOLID**  
   - SRP – Single Responsibility  
   - OCP – Open/Closed  
   - LSP – Liskov Substitution  
   - ISP – Interface Segregation  
   - DIP – Dependency Inversion  
6. **Estudos de Caso: analisando “bom” e “mau” design**  
7. **Code Smells, Anti‑patterns e Refatoração**  
8. **Checklist de Qualidade para projetos OO**  
9. **Exercício**  
10. **Leituras recomendadas**

---

## 1. Panorama do Design OO

Quando passamos da fase “aprender sintaxe” para “construir sistemas”, a grande pergunta deixa de ser *“como faço para isso compilar?”* e vira *“como faço para esse software continuar respirando daqui a 3 anos?”*  
Design orientado a objetos, nesse contexto, não é sobre **diagramas bonitos**: é sobre **sobrevivência**. Um sistema “sobrevive” se consegue:

* **Evoluir** sem exigir cirurgias em todos os módulos.  
* **Ser compreendido** por novos membros da equipe.  
* **Ser testado** de forma isolada e automatizada.  
* **Ser confiável** em produção, com defeitos localizáveis.

Dois conceitos medulares sustentam esse ideal: **coesão** e **acoplamento**. E, sobre eles, foram erguidos diversos princípios — entre os quais os **SOLID** — que orientam a construção de software resiliente.

---

## 2. Coesão

### 2.1 Definição Intuitiva  
> “Coesão mede **o quão fortemente** as partes de um módulo **pertencem ao mesmo motivo de existir**.”  

Em outras palavras, uma classe coesa contém **funcionalidades que orbitam uma mesma finalidade**.

### 2.2 Escalas Clássicas de Coesão  
Em 1974, Tom DeMarco e Meilir Page‑Jones descreveram uma escala (de pior para melhor):

| Grau | Nome | Resumo |
|------|------|--------|
| 1 | **Coincidental** | Métodos reunidos ao acaso. |
| 2 | **Logical** | Agrupados só porque são “parecidos” (ex.: vários *handlers* genéricos). |
| 3 | **Temporal** | Executam juntos no mesmo momento do ciclo (ex.: `init()` carrega tudo). |
| 4 | **Procedural** | Seguem uma sequência, mas não manipulam mesmo dado. |
| 5 | **Communicational** | Compartilham dados. |
| 6 | **Sequential** | Saída de um método vira entrada de outro. |
| 7 | **Functional** | Todos contribuem a **uma única tarefa clara**. |

Quanto mais nos aproximamos do **nível 7**, melhor.

### 2.3 Exemplo – Classe *Anêmica* vs. Classe Coesa  

```java
// Baixa coesão: faz “de tudo um pouco”
public class UtilGeral {
    public static double calcularImposto(...) { ... }
    public static void enviarEmail(...)      { ... }
    public static String formatarCPF(...)    { ... }
}
```

*Refatoração*:

```java
public class CalculadoraImpostos { ... }
public class ServicoEmail        { ... }
public class FormatadorDocumento { ... }
```

Cada classe agora tem **uma responsabilidade identificável**.

### 2.4 Métricas de Coesão  
Ferramentas de análise estática (SonarQube, PMD) calculam métricas como *Lack of Cohesion in Methods* (LCOM). Não basta decorar números, mas usá‑los como **alertas**.

---

## 3. Acoplamento

### 3.1 Definição Intuitiva  
> “Acoplamento mede **o grau de dependência** entre dois módulos.”  

Quanto mais um módulo **precisa saber** sobre detalhes internos de outro, **maior** o acoplamento.

### 3.2 Tipos Comuns de Acoplamento  

| Tipo | Exemplo | Grau de Problema |
|------|---------|------------------|
| **Conteúdo** | Um objeto mexe diretamente no atributo `private` de outro via reflexão. | Crítico |
| **Comum** | Várias classes partilham variável global. | Alto |
| **Externo** | Dependência de formato de arquivo ou protocolo rígido. | Médio/Alto |
| **Controle** | Passar “flag” para dizer *como* o método deve agir. | Médio |
| **Dados** | Passar apenas o necessário (parâmetros simples). | Baixo |
| **Nenhum** | Módulos independentes. | Ideal |

### 3.3 Exemplo de Acoplamento Excessivo  

```java
Pedido p = new Pedido();
p.itens.add(new Item(...)); // acessa lista pública!
```

*Problema:* Se amanhã `Pedido` trocar `List` por `Set`, todo código externo quebra.

*Correção:*

```java
p.adicionarItem(new Item(...)); // expõe só o que precisa
```

---

## 4. Coesão × Acoplamento: a dança dos opostos  

* **Alta coesão** costuma levar a **baixo acoplamento**: classes focadas sabem pouco umas das outras.  
* **Baixa coesão** costuma exigir **alto acoplamento**: se tudo está em todo lugar, qualquer mudança vaza dependências.

O **objetivo de design** é **maximizar coesão** e **minimizar acoplamento** simultaneamente.

---

## 5. Princípios SOLID

Os princípios **SOLID** (formulados por Robert C. Martin) são heurísticas para atingir esse equilíbrio.

### 5.1 SRP – Single Responsibility Principle  

> “Uma classe deve ter **um — e somente um — motivo para mudar**.”

*Ligação com coesão:* Foca em **responsabilidade única** → coesão funcional.  
*Indicadores de violação:* Classe gigante, nomes genéricos (“Gestor”, “Helper”), mudanças frequentes por razões diversas.

---

### 5.2 OCP – Open/Closed Principle  

> “Entidades de software devem ser **abertas para extensão** mas **fechadas para modificação**.”

*Ideia prática:* Adicionar novas funcionalidades **sem** editar código testado; usa‑se herança, composição ou políticas (Strategy).  
*Benefício:* Reduz risco de regressão; promove **baixo acoplamento**.

---

### 5.3 LSP – Liskov Substitution Principle  

> “Objetos de uma subclasse devem poder **substituir** objetos da superclasse **sem alterar a correção** do programa.”

*Exemplo famoso:* `Quadrado` não deveria herdar de `Retângulo` se viola invariantes (largura ≠ altura).  
*Consequência:* Mantém **contratos**; reforça **polimorfismo seguro**.

---

### 5.4 ISP – Interface Segregation Principle  

> “Nenhum cliente deve ser forçado a depender de métodos que não usa.”

*Boa prática:* Prefira **interfaces pequenas** e específicas.  
*Relaciona‑se a:* **Alta coesão** (interfaces coesas) e **baixo acoplamento** (clientes dependem só do necessário).

---

### 5.5 DIP – Dependency Inversion Principle  

> “Módulos de alto nível não devem depender de módulos de baixo nível; ambos devem depender de abstrações.”

*Aplicação:* Injeção de dependências (DI), fábricas, inversão de controle.  
*Efeito:* Reduz acoplamento, facilita testes (podemos injetar *mocks*).

---

## 6. Estudos de Caso: “bom” × “mau” design

### 6.1 Caso 1 – Serviço de Envio de Notificações

**Versão 1 (mau design):**

```java
public class Notificador {
    public void enviarEmail(String to, String msg) { ... }
    public void enviarSMS(String numero, String msg) { ... }
    public void enviarPush(String token, String msg) { ... }
}
```

*Problemas:*  
- **SRP violado** (três canais).  
- **OCP violado** (novo canal exige editar classe).  
- **Baixa coesão**.

**Refatoração (bom design):**

```java
public interface CanalNotificacao {
    void enviar(String destino, String mensagem);
}

public class EmailCanal implements CanalNotificacao { ... }
public class SMSCanal   implements CanalNotificacao { ... }
public class PushCanal  implements CanalNotificacao { ... }

public class Notificador {
    private final CanalNotificacao canal;
    public Notificador(CanalNotificacao canal) { this.canal = canal; }
    public void notificar(String destino, String msg) {
        canal.enviar(destino, msg);
    }
}
```

*Agora:*  
- **SRP** ok (cada classe tem uma responsabilidade).  
- **OCP** ok (novo canal = nova classe).  
- **DIP** ok (Notificador depende de abstração).

---

### 6.2 Caso 2 – Repositório de Dados

**Versão rígida:**

```java
public class ClienteDAO {
    public void salvarNoMySQL(Cliente c) { ... }
}
```

*Problemas:* Forte acoplamento a **MySQL**.  

**Versão flexível:**

```java
public interface ClienteRepository {
    void salvar(Cliente c);
}

public class ClienteRepositoryMySQL implements ClienteRepository { ... }
public class ClienteRepositoryMongo implements ClienteRepository { ... }
```

Inversão de dependência → facilita testes (mocka `ClienteRepository`), troca de banco etc.

---

## 7. Code Smells, Anti‑patterns e Refatoração

| Code Smell | Relação | Sinal de alerta |
|------------|---------|-----------------|
| **God Class** | Baixa coesão | Classe gigantesca, métodos heterogêneos |
| **Shotgun Surgery** | Alto acoplamento | Pequena mudança causa edições em vários arquivos |
| **Feature Envy** | Acoplamento errado | Método que mexe mais em dados de outra classe do que na própria |
| **Switch Flag** | Violação OCP | Muitos `switch/case` decidindo comportamento; devia ser polimorfismo |

**Refatoração** consiste em aplicar técnicas (Extrair Classe, Extrair Interface, Introduzir Strategy, etc.) para reduzir esses cheiros, melhorando coesão e acoplamento.

---

## 8. Checklist de Qualidade OO

1. **Cada classe tem um nome que revela um único propósito?**  
2. **Interfaces são pequenas e focadas?**  
3. **Mudança de implementação exige alteração mínima em módulos clientes?**  
4. **Dependências apontam de alto nível → baixo nível ou vice‑versa?** (Deveriam apontar **para abstrações**.)  
5. **Existem testes automatizados que provam o contrato?**  
6. **Regra de ouro:** *“Se eu precisar dar manutenção daqui a seis meses, entenderei o que essa classe faz em menos de cinco minutos?”*

---

## 9. Exercício

1. **Analisar Código**: identifique um repositório público no GitHub e liste problemas de coesão/acoplamento, indicando quais princípios SOLID são violados.  

---

## 10. Leituras Recomendadas

* **“Clean Architecture”** – Robert C. Martin  
* **“Domain‑Driven Design”** – Eric Evans (cap. sobre Bounded Context reforça coesão)  
* **“Refactoring”** – Martin Fowler  
* **Artigos de L. Briand** sobre métricas de coesão/acoplamento  
* Documentação do **SonarQube** para métricas LCOM, CBO (Coupling Between Objects) etc.

---

### ENCERRAMENTO

Coesão e acoplamento são os **eixos** do design OO; os princípios **SOLID** são **bússolas** que apontam para um terreno fértil onde esses eixos se equilibram. Bons sistemas:

* **Crescem** por extensão, não por remendos.  
* **Dialogam** através de contratos claros.  
* **Encapsulam** detalhes, expondo apenas intenções.  

E, talvez o mais importante, **respeitam seus futuros mantenedores** — que, muitas vezes, seremos nós mesmos daqui a algum tempo.  