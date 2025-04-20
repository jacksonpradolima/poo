# File I/O e Serialização em Java

1. Introdução a File I/O
   - O que é File I/O?
   - Motivações para manipulação de arquivos
2. Trabalhando com Arquivos Texto
   - Classe `File`
   - Leitura com `FileReader` e `BufferedReader`
   - Escrita com `FileWriter` e `BufferedWriter`
   - Exemplos práticos e casos de uso
3. Trabalhando com Arquivos Binários
   - `FileInputStream` e `FileOutputStream`
   - Diferenças entre texto e binário
   - Casos de uso com imagens, vídeos e binários
4. Try-with-resources
   - Gerenciamento de recursos automático
   - Comparativo com try-finally tradicional
5. Tratamento de Exceções em File I/O
   - Exceções comuns
   - Estratégias de contorno e logging
6. Comparando Texto x Binário
   - Performance
   - Portabilidade
   - Legibilidade
7. Introdução à Serialização
   - O que é serialização?
   - Quando utilizar?
8. Implementando Serialização em Java
   - Interface `Serializable`
   - `ObjectOutputStream` e `ObjectInputStream`
   - Salvando e recuperando objetos
9. Conceitos Avançados de Serialização
   - `serialVersionUID`
   - `transient`
   - Compatibilidade entre versões
10. Armadilhas e Boas Práticas
    - Evitando inconsistências e falhas
    - Serialização customizada (métodos `writeObject`/`readObject`)
11. Estudos de Caso
    - Aplicativo de cadastro de clientes
    - Persistência temporária em aplicações desktop
    - Logs e arquivos de configuração
12. Exercícios
    - Práticos com correção
    - Desafio de serialização segura
13. Comparativo com outras linguagens
    - Java x Python x C# em File I/O e Serialização
14. Referências e Leitura Complementar


---

## 1. Introdução a File I/O

## 1.1 O que é File I/O?
File I/O (File Input/Output), ou Entrada e Saída de Arquivos, é a capacidade de um programa ler e escrever dados em arquivos armazenados em disco. Essa funcionalidade é essencial para a persistência de dados entre execuções de um programa.

Em Java, File I/O é tratado por um conjunto de classes na API do pacote `java.io` (e mais recentemente `java.nio`), que fornecem uma ampla variedade de recursos para manipular arquivos texto e binários.

## 1.2 Motivações para Manipulação de Arquivos
- **Persistência de Dados:** guardar informações além do tempo de execução (ex: configurações, logs, banco de dados em arquivo).
- **Leitura de Arquivos Externos:** carregar dados de entrada de arquivos existentes (ex: CSVs, XML, JSON).
- **Exportação de Informações:** gravar relatórios, logs ou backups.
- **Comunicação entre sistemas:** intercâmbio de dados via arquivos intermediários.
- **Armazenamento temporário:** uso de arquivos como cache local ou checkpoint de processamento.

## 1.3 Analogias para Compreender File I/O
Imagine que um programa Java é como uma pessoa com memória de curto prazo. Sem arquivos, ela esquece tudo ao encerrar. Os arquivos são como cadernos onde você escreve ou lê informações permanentes.

- `Input`: é como **ler um livro**.
- `Output`: é como **escrever em um diário**.

No mundo real, abrir um arquivo envolve:
1. Localizar o caminho (diretório e nome).
2. Verificar se o arquivo existe (ou criar).
3. Abrir um canal de comunicação (stream).
4. Ler ou gravar.
5. Fechar corretamente para evitar vazamento de recurso.

## 1.4 Fluxos de Dados (Streams)
Em Java, leitura e escrita são feitas com o conceito de **fluxos** (streams):
- **InputStream / Reader**: lê dados do disco.
- **OutputStream / Writer**: grava dados no disco.

Esses streams podem ser **orientados a bytes** (para arquivos binários) ou **caracteres** (para arquivos texto). A escolha depende do tipo de conteúdo que será manipulado.

## 1.5 Visão Geral das Classes I/O em Java
| Categoria           | Texto                                  | Binário                                |
|--------------------|----------------------------------------|----------------------------------------|
| Leitura            | `FileReader`, `BufferedReader`         | `FileInputStream`, `BufferedInputStream` |
| Escrita            | `FileWriter`, `BufferedWriter`         | `FileOutputStream`, `BufferedOutputStream` |
| Arquivos           | `File`, `Path`, `Files` (NIO)          | Mesmas classes com diferentes usos     |
| Outros recursos    | `PrintWriter`, `Scanner`, `Formatter`  | `DataInputStream`, `DataOutputStream` |

## 1.6 Relação com Outras Áreas do Curso
- Utilizado em **persistência de dados** (ex: substituindo um banco de dados simples).
- Base para **processamento de grandes volumes de dados** (ex: arquivos CSV com milhares de linhas).
- Fundamenta conceitos de **serialização**, estudados em disciplinas como Engenharia de Software, Orientação a Objetos e Programação Distribuída.

---

## 2. Trabalhando com Arquivos Texto

## 2.1 Classe File
A classe `java.io.File` representa caminhos e arquivos no sistema de arquivos. Ela não realiza leitura ou escrita diretamente, mas é essencial para verificar, criar e manipular arquivos e diretórios.

```java
import java.io.File;

public class ExemploFile {
    public static void main(String[] args) {
        File arquivo = new File("exemplo.txt");

        if (arquivo.exists()) {
            System.out.println("Arquivo já existe!");
        } else {
            try {
                boolean criado = arquivo.createNewFile();
                System.out.println("Arquivo criado? " + criado);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```

## 2.2 Leitura com FileReader e BufferedReader
- `FileReader` é uma classe básica para ler arquivos texto caractere a caractere.
- `BufferedReader` adiciona eficiência ao agrupar a leitura em blocos e permite ler linha por linha.

```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeituraArquivoTexto {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("exemplo.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 2.3 Escrita com FileWriter e BufferedWriter
Semelhante ao leitor, `FileWriter` escreve caractere por caractere, enquanto `BufferedWriter` permite escrever com mais desempenho.

```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscritaArquivoTexto {
    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("saida.txt"))) {
            bw.write("Primeira linha\n");
            bw.write("Segunda linha\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 2.4 Uso de PrintWriter
`PrintWriter` oferece métodos convenientes como `println()` e `printf()` para escrita formatada.

```java
import java.io.PrintWriter;
import java.io.IOException;

public class EscritaComPrintWriter {
    public static void main(String[] args) throws IOException {
        try (PrintWriter pw = new PrintWriter("saida_formatada.txt")) {
            pw.println("Texto simples");
            pw.printf("Número formatado: %.2f%n", 123.456);
        }
    }
}
```

## 2.5 Considerações
- Sempre use `try-with-resources` para garantir que o arquivo seja fechado corretamente.
- A leitura com `BufferedReader` é preferível para arquivos grandes.
- A escrita com `BufferedWriter` ou `PrintWriter` é mais performática.

---

## 3. Trabalhando com Arquivos Binários

## 3.1 O que é um arquivo binário?
Diferente de arquivos texto, os arquivos binários armazenam dados na forma de **bytes brutos**, que representam números, imagens, áudio, vídeo ou estruturas complexas. A leitura e escrita nesses arquivos requer cuidado com **codificação, ordem dos bytes e estruturas de dados**.

## 3.2 Leitura com FileInputStream
```java
import java.io.FileInputStream;
import java.io.IOException;

public class LeituraBinaria {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("dados.bin")) {
            int dado;
            while ((dado = fis.read()) != -1) {
                System.out.print((char) dado);  // cuidado: pode imprimir caracteres "estranhos"
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 3.3 Escrita com FileOutputStream
```java
import java.io.FileOutputStream;
import java.io.IOException;

public class EscritaBinaria {
    public static void main(String[] args) {
        try (FileOutputStream fos = new FileOutputStream("dados.bin")) {
            fos.write(65); // letra A
            fos.write(66); // letra B
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## 3.4 Quando usar binário em vez de texto?
- Desempenho: arquivos binários geralmente são **menores e mais rápidos de ler**.
- Precisão: dados como `float` e `double` mantêm precisão binária.
- Segurança: mais difícil de ler/modificar manualmente.

## 3.5 Boas práticas
- Utilize `BufferedOutputStream` e `BufferedInputStream` para melhorar a performance.
- Para estruturas complexas, considere **DataOutputStream/DataInputStream** ou serialização.

---

## 4. Try-With-Resources

## 4.1 Problema com try/catch tradicional
```java
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("arquivo.txt"));
    // leitura
} catch (IOException e) {
    e.printStackTrace();
} finally {
    try {
        if (br != null) br.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

## 4.2 try-with-resources
```java
try (BufferedReader br = new BufferedReader(new FileReader("arquivo.txt"))) {
    String linha;
    while ((linha = br.readLine()) != null) {
        System.out.println(linha);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```
✅ Mais limpo, mais seguro, menos propenso a vazamento de recursos.

## 4.3 Regras
- Só funciona com recursos que implementam `AutoCloseable` (quase todos os da API I/O fazem).
- Pode declarar múltiplos recursos.

---

## 5. Tratamento de Exceções

## 5.1 Principais exceções
- `FileNotFoundException`: arquivo inexistente.
- `IOException`: erro genérico de leitura/escrita.
- `SecurityException`: falta de permissões.

## 5.2 Estratégias
- Sempre tratar exceções de forma contextualizada.
- Use `Logger` para evitar perda de rastreabilidade.

---

## 6. Comparação Texto vs Binário

| Critério            | Texto                    | Binário                   |
|---------------------|---------------------------|----------------------------|
| Legibilidade        | Alta                      | Baixa                     |
| Tamanho             | Maior                     | Menor                     |
| Performance         | Média                     | Alta                      |
| Portabilidade       | Alta (se UTF-8)           | Média (requer conversão)  |
| Aplicações          | Configurações, logs       | Imagens, vídeos, objetos  |

---

## 7. Introdução à Serialização

## 7.1 O que é Serialização?
Transformar um objeto em uma **sequência de bytes** para:
- Salvar em disco.
- Enviar por rede.
- Persistir entre execuções.

## 7.2 Por que usar?
- Simples de implementar.
- Evita reprocessar ou reconstruir dados.
- Permite comunicação entre sistemas.

---

## 8. Serialização em Java

## 8.1 Interface Serializable
```java
import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private int idade;

    // getters e setters
}
```

## 8.2 Gravando com ObjectOutputStream
```java
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuario.ser"))) {
    Usuario u = new Usuario("Ana", 25);
    oos.writeObject(u);
} catch (IOException e) {
    e.printStackTrace();
}
```

## 8.3 Lendo com ObjectInputStream
```java
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuario.ser"))) {
    Usuario u = (Usuario) ois.readObject();
    System.out.println(u.getNome());
} catch (IOException | ClassNotFoundException e) {
    e.printStackTrace();
}
```

---

## 9. serialVersionUID e transient

## 9.1 serialVersionUID
- Garante que a classe é compatível com a versão do objeto serializado.

```java
private static final long serialVersionUID = 1L;
```

## 9.2 transient
- Impede que certos atributos sejam serializados.

```java
private transient String senha;
```

---

## 10. Armadilhas e Boas Práticas

## Armadilhas:
- Alterar a classe após serializar.
- Falta de `serialVersionUID`.
- Serializar grandes estruturas na memória.
- Objetos que referenciam recursos não serializáveis (ex: `Socket`).

## Boas Práticas:
- Use `serialVersionUID` explícito.
- Use `transient` para dados sensíveis.
- Prefira `Externalizable` se precisar de mais controle.

---

## 11. Estudos de Caso

## 11.1 Sistema de cadastro local
Usuário é salvo entre execuções com serialização automática.

## 11.2 Logs binários
Soluções críticas podem salvar snapshots de objetos para diagnóstico posterior.

---

## 12. Exercícios

1. Crie um programa que leia e escreva nomes em um arquivo texto.
2. Crie uma classe `Produto` serializável e grave-a em disco.
3. Adicione atributo `transient` e observe o resultado.
4. Compare tempo de leitura de arquivos texto vs binário.

---

## 13. Comparativo com Outras Linguagens

| Funcionalidade       | Java                  | Python                   | C#                       |
|----------------------|------------------------|---------------------------|---------------------------|
| Serialização         | `Serializable`         | `pickle`, `json`         | `[Serializable]`         |
| Leitura de texto     | `BufferedReader`       | `open()`, `.readlines()` | `StreamReader`           |
| Binário              | `FileInputStream`      | `rb`/`wb`                | `BinaryReader`/`Writer`  |

---

## 14. Referências
- Java SE Documentation: https://docs.oracle.com/javase/8/docs/api/
- Bloch, J. Effective Java (3ª ed.)
- Clean Code, Robert C. Martin — Capítulo sobre persistência