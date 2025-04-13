# Arquitetura em Camadas

1. Panorama: por que arquitetar em camadas?  
2. As três camadas clássicas  
    2.1 Camada de Apresentação (UI)  
    2.2 Camada de Negócio (Domain / Service)  
    2.3 Camada de Dados (Persistence / Repository)  
3. Benefícios do isolamento de responsabilidades  
4. Fluxo de chamadas e regras de dependência  
5. Exemplo prático: cadastro de produtos  
    5.1 Pacotes físicos  
    5.2 Código‑guia de cada camada  
6. Estratégias de testes por camada  
7. Evolução: quando quebrar a “regra” e quando adicionar mais camadas  
8. Boas práticas e armadilhas comuns  
9. Exercício

---

## 1 | PANORAMA

Em projetos de médio / grande porte, misturar **interface**, **lógica de negócio** e **acesso a dados** numa única classe cria dependências rígidas e dificulta:

* **Manutenção** (pequena mudança → efeito dominó).  
* **Testes** (UI depende de banco, etc.).  
* **Evolução tecnológica** (trocar banco ou framework web).  

A **arquitetura em camadas** resolve isso impondo **barreiras lógicas**. Cada camada tem **responsabilidades bem definidas** e depende **apenas** da camada imediatamente inferior.

---

## 2 | AS TRÊS CAMADAS CLÁSSICAS

### 2.1 Camada de Apresentação

| Aspecto | Conteúdo |
|---------|----------|
| **Responsável por** | Interagir com usuário (HTTP, desktop, CLI, mobile). |
| **Exemplos** | Controllers Spring MVC, Servlets, JavaFX controllers, React components (separados no front). |
| **Não deve** | Conter regras de negócio complexas, SQL, acesso direto a entidades JPA. |
| **Depende de** | Serviços da camada de Negócio (via DTOs / ViewModels). |

### 2.2 Camada de Negócio

| Aspecto | Conteúdo |
|---------|----------|
| **Responsável por** | Regras, validações, orquestração de casos de uso. |
| **Artefatos típicos** | *Service classes*, *Domain models*, *Use‑case* classes. |
| **Depende de** | Interfaces de repositório (não de implementações concretas). |
| **Não deve** | Acessar frameworks de UI, classes de banco de dados concretas. |

### 2.3 Camada de Dados

| Aspecto | Conteúdo |
|---------|----------|
| **Responsável por** | Persistir e recuperar entidades (JPA, JDBC, MyBatis, Mongo template). |
| **Artefatos típicos** | DAOs, Repositories, Mappers. |
| **Depende de** | Bibliotecas de driver/ORM; nunca da camada de Apresentação. |
| **Não deve** | Implementar lógica de negócio ou formatar HTML/JSON. |

---

## 3 | BENEFÍCIOS DO ISOLAMENTO

1. **Coesão**: cada camada foca em *um* tipo de preocupação.  
2. **Baixo acoplamento**: mudança de tecnologia em uma camada não quebra outras.  
3. **Testabilidade**:  
   - Serviços testados com mocks de repositório.  
   - Repositórios testados com banco em memória.  
4. **Escalabilidade de equipe**: times trabalham em camadas diferentes em paralelo.  
5. **Segurança**: validações centralizadas na camada de negócio evitam replicação na UI.

---

## 4 | FLUXO DE CHAMADAS

```
[Apresentação]  ->  [Negócio]  ->  [Dados]
      ^                 ^              |
      |                 |              |
   DTO/ViewModel     Domínio        Driver/ORM
```

**Regras de dependência (exemplo Java):**

* `web.*` pode importar `service.*`, nunca `repository.jpa.*`.  
* `service.*` pode importar `repository.*` (interfaces) e `domain.*`.  
* `repository.jpa.*` implementa `repository.*` mas não conhece `service.*`.

---

## 5 | EXEMPLO PRÁTICO – CADASTRO DE PRODUTOS

### 5.1 Estrutura de pacotes (gradle / maven)

```
com.exemplo.loja
 ├── web
 │    └── ProdutoController.java
 ├── service
 │    ├── ProdutoService.java
 │    └── dto
 │         └── ProdutoDTO.java
 ├── domain
 │    └── Produto.java
 ├── repository
 │    ├── ProdutoRepository.java   (interface)
 │    └── impl
 │         └── ProdutoRepositoryJpa.java
 └── config
      └── SpringConfig.java
```

### 5.2 Trechos de código

**domain/Produto.java**

```java
@Entity
public class Produto {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private BigDecimal preco;
    private int estoque;
    // getters, setters, regras de invariantes
}
```

**repository/ProdutoRepository.java**

```java
public interface ProdutoRepository {
    Produto salvar(Produto p);
    Optional<Produto> encontrarPorId(Long id);
    List<Produto> listar();
}
```

**repository/impl/ProdutoRepositoryJpa.java**

```java
@Repository
class ProdutoRepositoryJpa implements ProdutoRepository {
    @PersistenceContext private EntityManager em;
    public Produto salvar(Produto p){ em.persist(p); return p; }
    public Optional<Produto> encontrarPorId(Long id){
        return Optional.ofNullable(em.find(Produto.class, id));
    }
    public List<Produto> listar(){
        return em.createQuery("from Produto", Produto.class).getResultList();
    }
}
```

**service/ProdutoService.java**

```java
@Service
public class ProdutoService {
    private final ProdutoRepository repo;
    public ProdutoService(ProdutoRepository repo){ this.repo = repo; }

    public ProdutoDTO cadastrar(ProdutoDTO dto){
        validar(dto);
        Produto p = dto.toEntity();
        repo.salvar(p);
        return ProdutoDTO.fromEntity(p);
    }
    private void validar(ProdutoDTO d){
        if(d.preco().compareTo(BigDecimal.ZERO) <= 0)
            throw new NegocioException("Preço deve ser positivo");
    }
}
```

**web/ProdutoController.java**

```java
@RestController @RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService service;
    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(@RequestBody ProdutoDTO dto){
        ProdutoDTO salvo = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }
}
```

*Observações*:

- **Controller** apenas traduz HTTP ↔ DTO, delega regra a Service.  
- **Service** aplica validações, regras, transações.  
- **Repository** encapsula JPA; trocar por Mongo requer só nova implementação.

---

## 6 | ESTRATÉGIAS DE TESTES

| Camada | Tipo de teste | Ferramentas |
|--------|---------------|-------------|
| Apresentação | *Web layer test* (mock MVC) | Spring MockMvc, Rest‑Assured |
| Negócio | Unitário, sem banco | JUnit 5, Mockito |
| Dados | *Integration test* com DB em memória | Testcontainers, H2 |

---

## 7 | EVOLUÇÃO E VARIAÇÕES

*Quando adicionar mais camadas?*  

- **DTO ↔ Mapper** layer se há transformação complexa.  
- **Application layer** (DDD) para orquestrar use‑cases, deixando domínio mais puro.  
- **Anti‑corruption layer** quando integra sistemas legados.

*Quando quebrar a “regra” de dependência?*  
- Leituras de **projeções** (CQRS) podem acessar banco direto para performance.  
- Micro‑services podem colapsar UI+Service se front‑end é thin server.

---

## 8 | BOAS PRÁTICAS & ARMADILHAS

| Boa prática | Armadilha oposta |
|-------------|------------------|
| DTOs na fronteira, entidades internas. | Expor entidade JPA direto no JSON (quebra encapsulamento). |
| Services sem estado (stateless). | Guardar `HttpSession` dentro de Service (dificulta testes). |
| Repository depende de abstração (interface). | Controller chamando `EntityManager` direto. |
| Pacotes nomeados por **papel** (`service`, `repository`). | Pacotes por tecnologia (`spring`, `hibernate`) misturam responsabilidades. |

---

## 9 | EXERCÍCIO

1. **Refatorar projeto monolítico**:  identifique um repositório público no GitHub e proponha separar UI, lógica e DAO em pacotes.  

---

### CONCLUSÃO

Arquitetura em camadas oferece **organização progressiva**, **isolamento de responsabilidades** e **facilidade de manutenção**. Quando cada módulo conhece **apenas o necessário**, mudanças locais não provocam terremotos globais. A chave é **disciplinar dependências**, testar cada camada em seu nível adequado e evoluir a estrutura conforme o domínio e o contexto do projeto.