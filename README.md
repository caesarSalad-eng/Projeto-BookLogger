# 📚 Projeto Book Logger: Knowledge Extractor (Spring Boot Console App)

## 🌟 Visão Geral do Projeto

O **Book Logger** é uma aplicação backend construída com **Spring Boot** e **Java** que funciona como um extrator de conhecimento. O objetivo principal é catalogar livros lidos ou em leitura e, mais importante, armazenar os **"Ensinamentos"** extraídos de cada obra (citações, resumos, insights pessoais).

Este projeto demonstra uma arquitetura sólida em camadas (MVC, Service, Repository) e utiliza um sistema de interface de linha de comando (`CommandLineRunner`) para interação e testes diretos no console, provando a robustez da lógica de negócios.

## 🧠 A Jornada de Aprendizado e o Papel da IA

A construção deste projeto foi extremamente gratificante, servindo como um marco crucial no meu desenvolvimento em Java e Spring Boot.

Os principais aprendizados incluíram:

* **Arquitetura em Camadas:** Reforço na implementação correta do padrão Service/Repository/Model.
* **Mapeamento JPA:** Consolidação do entendimento sobre o relacionamento **One-to-Many** entre Livro e Ensinamento.
* **Injeção de Dependência:** Solução de diversos erros de `UnsatisfiedDependencyException` e domínio sobre a criação de Beans personalizados (`Scanner`).
* **Desenvolvimento CLI (Console):** Implementação de um menu interativo com `CommandLineRunner` para simular as interações do usuário.

Vale ressaltar que como ainda estou iniciando tive muita ajuda para resolver problemas e felizmente tive o auxílio do **Gemini do Google**, que atuou como um mentor técnico, fornecendo *insights* precisos e sugerindo as melhores práticas de código, acelerando significativamente o processo de desenvolvimento.

## 🛠️ Tecnologias Utilizadas

| Categoria | Tecnologia | Detalhes |
| :--- | :--- | :--- |
| **Backend Core** | Java 17+ | Linguagem principal do projeto. |
| **Framework** | Spring Boot 3/4 | Configuração simplificada e execução autônoma. |
| **Persistência** | Spring Data JPA | Facilita o acesso e manipulação de dados. |
| **Banco de Dados** | H2 Database | Banco de dados em memória (ideal para testes rápidos). |
| **Ferramentas** | Git & GitHub | Controle de versão e hospedagem. |

## 🏗️ Arquitetura e Estrutura

O projeto segue o padrão **MVC em camadas**, garantindo a separação de responsabilidades e alta manutenibilidade:

1.  **`Model` (Entidades):** Classes `Livro`, `Ensinamento`, `StatusLeitura` e `TipoConteudo`. Define a estrutura do banco de dados.
2.  **`Repository`:** Interfaces JPA para operações CRUD básicas.
3.  **`Service`:** Contém a **lógica de negócios** (ex: salvar livro e seus ensinamentos, atualização de notas).
4.  **`Controller` (Desativado):** Estrutura inicial para Web (se o projeto fosse para ter um frontend HTML).
5.  **`Utils/Config`:** Contém o `TestDataLoader` para interação via console.

## ⚙️ Como Rodar a Aplicação

Este projeto foi configurado para rodar diretamente via terminal ou IDE (IntelliJ, VS Code, Eclipse), utilizando o `CommandLineRunner` como ponto de entrada.

### Pré-requisitos

* Java Development Kit (JDK) 17 ou superior.
* Maven (já integrado se usando a IDE).

### Passos

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/caesarSalad-eng/Projeto-BookLogger.git](https://github.com/caesarSalad-eng/Projeto-BookLogger.git)
    cd Projeto-BookLogger
    ```

2.  **Execute o Projeto:**
    * **Via Maven:** Abra o terminal na raiz do projeto e execute:
        ```bash
        ./mvnw spring-boot:run
        ```
    * **Via IDE:** Abra o projeto em sua IDE (IntelliJ ou Eclipse) e execute a classe principal: `ProjetoBookLoggerApplication.java`.

### 💻 Interação no Console

Após a inicialização do Spring Boot, um **menu interativo** será exibido no console, permitindo que você teste as funcionalidades de backend:

## 📝 Funcionalidades Implementadas (Backend)

* **CRUD Básico** (`Livro` e `Ensinamento`).
* **Associação:** Mapeamento One-to-Many entre `Livro` e `Ensinamento` (JPA).
* **Lógica de Negócios:** Salvamento e atualização de notas em `LivroService`.
* **Upload de Arquivos:** Estrutura pronta para salvar arquivos de capa em disco (embora desativada no console).
