# Concorrência e Threads em Java

1. Introdução à Concorrência
    - O que é concorrência?
    - Concorrência vs. paralelismo
    - Processos vs. Threads
2. Criação de Threads em Java
    - Usando a classe Thread
    - Usando a interface Runnable
    - Diferença entre Thread e Runnable
3. Estados de uma Thread
    - Ciclo de vida
4. Sincronização em Java
    - Palavras-chave synchronized
    - Blocos sincronizados
    - Métodos sincronizados
    - Monitores
5. Problemas Clássicos de Concorrência
    - Race condition
    - Deadlock
    - Starvation
6. Soluções com API Avançada
    - API Executor e Thread Pools
    - Callable, Future, ScheduledExecutorService
    - Estruturas concorrentes (ConcurrentHashMap, CopyOnWriteArrayList)
    - Locks (ReentrantLock, ReadWriteLock)
7. Padrão Produtor-Consumidor
    - Problema clássico
    - Implementação com wait/notify
    - Soluções com BlockingQueue
8. Boas Práticas e Armadilhas
    - Evitar bloqueios desnecessários
    - Reduzir tempo de espera
    - Projetar para escalabilidade
    - Evitar sincronização excessiva
9. Estudos de Caso
    - Sistema de Log concorrente
    - Simulação de pool de conexões
10. Exercícios e Projeto Guiado
    - Exercícios práticos com correção
    - Implementação de solução concorrente controlada

---

### **1. Introdução à Concorrência**

#### 1.1 O que é Concorrência?
Concorrência é a capacidade de lidar com múltiplas tarefas que ocorrem dentro de um mesmo intervalo de tempo. Ela permite que um sistema lide com várias operações ao mesmo tempo (logicamente), mesmo que um processador execute uma tarefa de cada vez.

Na prática, uma aplicação concorrente pode executar partes do código ao mesmo tempo por meio do uso de **threads**. Essas threads compartilham recursos, permitindo mais agilidade e resposta em aplicações que lidam com várias tarefas simultâneas, como servidores web, jogos e interfaces gráficas.

#### 1.2 Concorrência vs. Paralelismo
Embora muitos tratem os dois como sinônimos, há distinções fundamentais:

- **Concorrência** trata da estrutura do programa que lida com múltiplas tarefas, independentemente de serem executadas ao mesmo tempo.
- **Paralelismo** refere-se à execução física simultânea de várias tarefas, geralmente com uso de múltiplos núcleos ou processadores.

> **Exemplo:** Uma CPU com apenas um núcleo pode ser concorrente, alternando rapidamente entre tarefas. Uma CPU com múltiplos núcleos pode ser paralela, executando múltiplas tarefas ao mesmo tempo.

#### 1.3 Diferença entre Processos e Threads
| Característica         | Processo                                  | Thread                                     |
|------------------------|-------------------------------------------|--------------------------------------------|
| Espaço de memória      | Independente                              | Compartilhado com outras threads do processo |
| Tempo de criação       | Alto                                       | Baixo                                       |
| Custo de troca         | Alto                                       | Baixo                                       |
| Comunicação            | Mais difícil (IPC)                         | Mais fácil (memória compartilhada)          |
| Isolamento             | Total                                      | Parcial                                     |

Threads são preferidas quando se deseja leveza, comunicação mais eficiente e acesso a dados compartilhados.

#### 1.4 Vantagens da Concorrência
- Aumento da performance percebida.
- Melhor uso dos recursos do sistema.
- Resposta mais rápida a eventos externos (UI, conexões de rede).
- Suporte a tarefas paralelas como I/O e processamento em segundo plano.

> **Exemplo Real:** Um navegador que carrega páginas em uma thread, renderiza imagens em outra e escuta eventos do usuário em uma terceira.

---

### **2. Criação de Threads em Java**

#### 2.1 Usando a classe Thread
A forma mais direta de criar uma thread em Java é estendendo a classe `Thread` e sobrescrevendo o método `run()`.

```java
class MinhaThread extends Thread {
    @Override
    public void run() {
        System.out.println("Executando thread: " + Thread.currentThread().getName());
    }
}

public class ExemploThread {
    public static void main(String[] args) {
        MinhaThread t1 = new MinhaThread();
        t1.start(); // Nunca use .run() diretamente
    }
}
```

#### 2.2 Usando a interface Runnable
A alternativa mais recomendada é implementar a interface `Runnable`. Isso promove maior flexibilidade e evita herança desnecessária.

```java
class MinhaTarefa implements Runnable {
    @Override
    public void run() {
        System.out.println("Executando com Runnable: " + Thread.currentThread().getName());
    }
}

public class ExemploRunnable {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MinhaTarefa());
        t1.start();
    }
}
```

#### 2.3 Usando Lambda (a partir do Java 8)
```java
public class ExemploLambda {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread via lambda executando: " + Thread.currentThread().getName());
        });
        t1.start();
    }
}
```

#### 2.4 Diferenças entre Thread e Runnable
| Aspecto                  | Thread                              | Runnable                             |
|--------------------------|--------------------------------------|--------------------------------------|
| Herança                 | Requer estender a classe Thread       | Permite herança de outra classe       |
| Flexibilidade            | Menor                                | Maior                                |
| Reusabilidade            | Limitada                             | Alta                                 |
| Boas práticas            | Menos recomendado                    | Mais usado no mercado                |

#### 2.5 Estados de uma Thread
O ciclo de vida de uma thread em Java pode ser representado com os seguintes estados:

1. **NEW** – Quando a thread é criada, mas ainda não foi iniciada.
2. **RUNNABLE** – Quando a thread está pronta para executar.
3. **RUNNING** – A thread está atualmente sendo executada.
4. **BLOCKED** – Aguardando acesso exclusivo a um recurso.
5. **WAITING / TIMED_WAITING** – Esperando que outra thread a notifique ou esperando por tempo.
6. **TERMINATED** – A thread completou sua execução.

```java
public class EstadosThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Estado antes do start: " + t.getState()); // NEW
        t.start();
        Thread.sleep(100);
        System.out.println("Estado logo após start: " + t.getState()); // RUNNABLE or TIMED_WAITING
        Thread.sleep(1100);
        System.out.println("Estado após término: " + t.getState()); // TERMINATED
    }
}
```
---

### **3. Sincronização em Java**

#### 3.1 Por que sincronizar?
Quando múltiplas threads acessam os **mesmos recursos** (como uma variável ou estrutura de dados), há risco de **condições de corrida (race conditions)**. Para evitar que dados sejam lidos ou modificados de forma inconsistente, usamos mecanismos de **sincronização**.

#### 3.2 Palavra-chave `synchronized`
A palavra-chave `synchronized` impede que **mais de uma thread acesse simultaneamente** um bloco de código ou método.

##### 3.2.1 Sincronização em métodos
```java
public class Contador {
    private int valor = 0;

    public synchronized void incrementar() {
        valor++;
    }

    public synchronized int getValor() {
        return valor;
    }
}
```

##### 3.2.2 Sincronização em blocos
```java
public class Contador {
    private int valor = 0;

    public void incrementar() {
        synchronized (this) {
            valor++;
        }
    }
}
```

#### 3.3 Race Condition (Condição de Corrida)
Ocorre quando **duas ou mais threads acessam um recurso compartilhado** e tentam modificar seu estado ao mesmo tempo, produzindo resultados imprevisíveis.

> **Exemplo:** Um banco que desconta saldo de duas transações concorrentes pode gerar saldo incorreto se as operações ocorrerem sem controle.

#### 3.4 Deadlock
Um **deadlock** acontece quando **duas ou mais threads ficam esperando indefinidamente por recursos que estão bloqueados entre si**.

```java
public class DeadlockExemplo {
    private static final Object recurso1 = new Object();
    private static final Object recurso2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (recurso1) {
                System.out.println("Thread 1: bloqueou recurso 1");
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized (recurso2) {
                    System.out.println("Thread 1: bloqueou recurso 2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (recurso2) {
                System.out.println("Thread 2: bloqueou recurso 2");
                try { Thread.sleep(50); } catch (InterruptedException e) {}
                synchronized (recurso1) {
                    System.out.println("Thread 2: bloqueou recurso 1");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
```

#### 3.5 Starvation (Inanição)
Ocorre quando uma thread **fica esperando indefinidamente por CPU** ou acesso a um recurso, enquanto outras threads monopolizam os recursos do sistema.

#### 3.6 Boas práticas para sincronização
- Use o menor escopo possível para `synchronized`.
- Prefira `ConcurrentHashMap` em vez de `HashMap` sincronizado.
- Evite múltiplos locks aninhados.
- Considere `ReentrantLock` quando precisar de mais controle.

---

### **4. API Executor e Thread Pools**

#### 4.1 O problema de criar muitas threads
Criar uma nova thread com `new Thread(...).start()` toda vez **não escala bem** — pode esgotar recursos do sistema rapidamente.

#### 4.2 ExecutorService
```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExemplo {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            final int tarefa = i;
            executor.submit(() -> {
                System.out.println("Executando tarefa " + tarefa);
            });
        }

        executor.shutdown();
    }
}
```

#### 4.3 Tipos de thread pools
- `newFixedThreadPool(int nThreads)`
- `newCachedThreadPool()`
- `newSingleThreadExecutor()`
- `newScheduledThreadPool(int corePoolSize)`

#### 4.4 Callable e Future
Permitem que threads **retornem valores**.
```java
Callable<Integer> tarefa = () -> 10 + 5;
Future<Integer> futuro = executor.submit(tarefa);
Integer resultado = futuro.get();
```

---

### **5. Estruturas Concorrentes e Locks Avançados**

#### 5.1 Estruturas concorrentes
- `ConcurrentHashMap`
- `CopyOnWriteArrayList`
- `BlockingQueue` e `LinkedBlockingQueue`

#### 5.2 ReentrantLock
Oferece mais controle que `synchronized`, como **tentativas temporizadas** de obter lock.
```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    // região crítica
} finally {
    lock.unlock();
}
```

---

### **6. Padrão Produtor-Consumidor**

#### 6.1 Exemplo com `wait()` e `notify()`
```java
class Buffer {
    private int dado;
    private boolean disponivel = false;

    public synchronized void produzir(int valor) throws InterruptedException {
        while (disponivel) wait();
        dado = valor;
        disponivel = true;
        notifyAll();
    }

    public synchronized int consumir() throws InterruptedException {
        while (!disponivel) wait();
        disponivel = false;
        notifyAll();
        return dado;
    }
}
```

#### 6.2 Exemplo com `BlockingQueue`
```java
BlockingQueue<Integer> fila = new LinkedBlockingQueue<>(10);

Runnable produtor = () -> {
    try {
        fila.put(42);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
};

Runnable consumidor = () -> {
    try {
        Integer dado = fila.take();
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
};
```

---

### **7. Boas Práticas e Armadilhas em Concorrência**

- **Evite acesso direto a variáveis compartilhadas.**
- **Evite bloqueios desnecessários.**
- **Prefira abstrações de alto nível** (`ExecutorService`, `ConcurrentHashMap`, `BlockingQueue`).
- **Reduza o tempo dentro de regiões críticas.**
- **Sempre libere locks em blocos `finally`.**
- **Evite `Thread.sleep()` para sincronização.**
- **Use `volatile` apenas quando necessário.**
- **Documente código concorrente claramente.**

---

### **8. Conclusão**
Programação concorrente em Java é uma habilidade poderosa, porém complexa. Entender como **threads são criadas, sincronizadas, coordenadas e controladas** é essencial para construir **sistemas robustos, performáticos e escaláveis**. Utilizar as ferramentas modernas da linguagem — como `ExecutorService`, `Locks`, `Concurrent Collections` — permite maior clareza, segurança e manutenção.
