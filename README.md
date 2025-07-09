# Programação Orientada a Objetos (POO)

[![CI](https://github.com/jacksonpradolima/poo/actions/workflows/continuous-integration.yml/badge.svg)](https://github.com/jacksonpradolima/poo/actions/workflows/continuous-integration.yml)
[![Java](https://img.shields.io/badge/Java-11-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

## 📚 Sobre

Este repositório abriga os recursos e materiais utilizados no curso de **Programação Orientada a Objetos**. O curso oferece uma abordagem completa e prática dos conceitos fundamentais da POO, desde os paradigmas de programação até tópicos avançados como design patterns e arquitetura de software.

### 🎯 Objetivos do Curso

- Compreender os fundamentos da programação orientada a objetos
- Dominar os pilares da POO: abstração, encapsulamento, herança e polimorfismo
- Aplicar princípios SOLID no design de software
- Implementar design patterns clássicos
- Desenvolver habilidades em testes de software e TDD
- Projetar sistemas com arquitetura em camadas

## 🗓️ Estrutura do Curso

O curso está organizado em 11 aulas progressivas:

| Aula | Tópico | Status |
|------|--------|--------|
| 01 | [Paradigmas de Programação](docs/aulas/01-paradigmas_programacao/) | 🔄 Em desenvolvimento |
| 02 | [Introdução ao Paradigma POO](docs/aulas/02-introducao_paradigma_poo/) | 🔄 Em desenvolvimento |
| 03 | [Conceitos e Mecanismos da POO](docs/aulas/03-conceitos_mecanismos_poo/) | ✅ Completo |
| 04 | [Tratamento de Exceções](docs/aulas/04-tratamento_excecoes/) | ✅ Completo |
| 05 | [Projeto Orientado a Objetos](docs/aulas/05-projeto_orientado_objetos/) | ✅ Completo |
| 06 | [Programação Concorrente e Threads](docs/aulas/06-programacao_concorrente_threads/) | ✅ Completo |
| 07 | [Arquivos e Serialização](docs/aulas/07-file_serializacao/) | ✅ Completo |
| 08 | [Tipos Genéricos](docs/aulas/08-tipos_genericos/) | ✅ Completo |
| 09 | [Coleções de Dados](docs/aulas/09-colecao_dados/) | ✅ Completo |
| 10 | [Tópicos Avançados em Java](docs/aulas/10-topicos_avancados_java/) | ✅ Completo |
| 11 | [Teste de Software](docs/aulas/11-teste_software/) | ✅ Completo |

## 💻 Tecnologias Utilizadas

- **Java 11**: Linguagem principal do curso
- **Maven**: Gerenciamento de dependências e build
- **JUnit 5**: Framework de testes unitários
- **Mockito**: Framework para criação de mocks
- **JaCoCo**: Cobertura de testes
- **GitHub Actions**: Integração contínua

## 🚀 Como Executar

### Pré-requisitos

- Java 11 ou superior
- Maven 3.6 ou superior

### Executando os Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com relatório de cobertura
mvn clean test jacoco:report

# Visualizar relatório de cobertura
open target/site/jacoco/index.html
```

### Compilando o Projeto

```bash
# Compilar o projeto
mvn compile

# Criar package
mvn package
```

## 📖 Destaques do Conteúdo

### 🔧 Conceitos Fundamentais
- **Classes e Objetos**: Definição, estado, comportamento e identidade
- **Associação, Agregação e Composição**: Relacionamentos entre objetos
- **Herança e Polimorfismo**: Reutilização e flexibilidade de código
- **Interfaces**: Contratos e abstração

### 🏗️ Design e Arquitetura
- **Princípios SOLID**: Fundamentos para código limpo e manutenível
- **Design Patterns**: Factory, Singleton, Strategy, Observer
- **Coesão e Acoplamento**: Métricas de qualidade de software
- **Arquitetura em Camadas**: Separação de responsabilidades

### 🧪 Qualidade de Software
- **Testes Unitários**: JUnit 5 e boas práticas
- **Test-Driven Development (TDD)**: Desenvolvimento guiado por testes
- **Mocks e Stubs**: Isolamento de dependências

## 🔮 Trabalhos Futuros

### 📋 Conteúdo Pendente
- [ ] Finalizar aula 01 - Paradigmas de Programação
- [ ] Finalizar aula 02 - Introdução ao Paradigma POO
- [ ] Adicionar mais exercícios práticos em cada aula
- [ ] Criar projetos integradores

### 🚀 Expansões Planejadas
- [ ] **Programação Funcional em Java**
  - Streams e expressões lambda
  - Optional e programação funcional
  - Collectors e operações avançadas

- [ ] **Frameworks Modernos**
  - Introdução ao Spring Framework
  - Spring Boot para APIs REST
  - Hibernate/JPA para persistência

- [ ] **Microserviços e Arquitetura**
  - Padrões de microserviços
  - API Gateway e Service Discovery
  - Containerização com Docker

- [ ] **Tópicos Avançados**
  - Reflection e Annotations
  - Processamento assíncrono
  - Performance e otimização

- [ ] **Ferramentas de Desenvolvimento**
  - SonarQube para análise de código
  - Profiles do Maven
  - Documentação com JavaDoc

### 🎯 Melhorias Técnicas
- [ ] Adicionar mais exemplos executáveis
- [ ] Implementar todos os design patterns mencionados
- [ ] Criar dashboards de métricas de código
- [ ] Adicionar validação automática de exemplos

> 📝 **Nota**: Para uma visão detalhada das expansões planejadas, cronograma e propostas de contribuição, consulte o arquivo [FUTURE_WORKS.md](FUTURE_WORKS.md).

## 🤝 Reúso do Material

Sinta-se à vontade para utilizar este conteúdo. Pedimos apenas que, ao reutilizá-lo, forneça a URL deste repositório como referência. Se possível, nos envie `Pull Requests` com melhorias do conteúdo. 

Para dúvidas, comentários ou sugestões, crie uma `issue` neste repositório. Dessa forma, outros interessados também poderão participar da discussão sobre o tema.

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

## 👥 Contribuidores

<a href="https://github.com/jacksonpradolima/poo/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=jacksonpradolima/poo" />
</a>