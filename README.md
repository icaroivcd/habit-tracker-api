# ControlHabits - Uma API Spring Boot para Gerenciamento de Hábitos

Este projeto é uma aplicação Spring Boot desenvolvida para gerenciar hábitos do usuário através de uma API RESTful. Ele oferece funcionalidades para criar, recuperar, atualizar e deletar hábitos.

## Tecnologias Utilizadas

*   **Java 21**: A linguagem de programação.
*   **Spring Boot**: Framework para construção da aplicação.
*   **Spring Data JPA**: Para interação com o banco de dados e mapeamento objeto-relacional.
*   **H2 Database**: Um banco de dados relacional em memória usado para desenvolvimento e testes.
*   **Lombok**: Para reduzir o código boilerplate (getters, setters, construtores).
*   **Maven**: Ferramenta de gerenciamento de dependências e automação de build.

## Primeiros Passos

Estas instruções permitirão que você obtenha uma cópia do projeto em funcionamento em sua máquina local para fins de desenvolvimento e teste.

### Pré-requisitos

*   Java Development Kit (JDK) 21 ou superior
*   Apache Maven 3.6.x ou superior

### Instalação

1.  **Construa o projeto usando Maven:**
    ```bash
    ./mvnw clean install
    ```

## Executando a Aplicação

Para executar a aplicação Spring Boot, você pode usar o plugin Maven do Spring Boot:

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada em `http://localhost:8080` por padrão.

O console do H2 estará acessível em `http://localhost:8080/h2-console` (pode ser necessário configurar a URL JDBC em `application.properties` para `jdbc:h2:mem:testdb` e o nome de usuário para `sa` sem senha, ou conforme especificado em seu `application.properties`).

## Endpoints da API

A API é exposta sob o caminho base `/habits`.

### 1. Criar um Novo Hábito

*   **URL:** `/habits/create-habit`
*   **Método:** `POST`
*   **Corpo da Requisição (JSON - `CreateHabitDTO`):**
    ```json
    {
        "name": "Leitura Diária",
        "description": "Ler por 30 minutos todos os dias"
    }
    ```
*   **Resposta (JSON - `Habit`):**
    ```json
    {
        "id": 1,
        "name": "Leitura Diária",
        "description": "Ler por 30 minutos todos os dias"
    }
    ```
*   **Códigos de Status:**
    *   `201 Created`: Se o hábito for criado com sucesso.
    *   `400 Bad Request`: Se a validação falhar (ex: `name` em branco).
    *   (Nota: O cabeçalho `Location` apontará para o recurso recém-criado, ex: `/habits/1`)

### 2. Obter Todos os Hábitos

*   **URL:** `/habits`
*   **Método:** `GET`
*   **Resposta (JSON - `List<Habit>`):**
    ```json
    [
        {
            "id": 1,
            "name": "Leitura Diária",
            "description": "Ler por 30 minutos todos os dias"
        },
        {
            "id": 2,
            "name": "Exercício",
            "description": "Ir à academia 3 vezes por semana"
        }
    ]
    ```
*   **Códigos de Status:**
    *   `200 OK`: Sempre retorna uma lista, que pode estar vazia.

### 3. Obter Hábito por ID

*   **URL:** `/habits/{id}`
*   **Método:** `GET`
*   **Parâmetros de Caminho:**
    *   `id` (Long): O ID do hábito a ser recuperado.
*   **Resposta (JSON - `Habit`):**
    ```json
    {
        "id": 1,
        "name": "Leitura Diária",
        "description": "Ler por 30 minutos todos os dias"
    }
    ```
*   **Códigos de Status:**
    *   `200 OK`: Se o hábito for encontrado.
    *   `404 Not Found`: Se nenhum hábito com o ID fornecido existir.

### 4. Atualizar um Hábito Existente (Atualização Parcial)

*   **URL:** `/habits/update/{id}`
*   **Método:** `PATCH`
*   **Parâmetros de Caminho:**
    *   `id` (Long): O ID do hábito a ser atualizado.
*   **Corpo da Requisição (JSON - `UpdateHabitDTO`):**
    ```json
    {
        "name": "Ler 45 minutos"
        // "description": "Descrição atualizada" // description também pode ser atualizada
    }
    ```
    *   Você pode fornecer `name`, `description` ou ambos. Campos não fornecidos permanecerão inalterados.
*   **Resposta (JSON - `Habit`):**
    ```json
    {
        "id": 1,
        "name": "Ler 45 minutos",
        "description": "Ler por 30 minutos todos os dias"
    }
    ```
*   **Códigos de Status:**
    *   `200 OK`: Se o hábito for atualizado com sucesso.
    *   `404 Not Found`: Se nenhum hábito com o ID fornecido existir.

### 5. Deletar um Hábito

*   **URL:** `/habits/delete/{id}`
*   **Método:** `DELETE`
*   **Parâmetros de Caminho:**
    *   `id` (Long): O ID do hábito a ser deletado.
*   **Resposta:** (Sem Conteúdo)
*   **Códigos de Status:**
    *   `204 No Content`: Se o hábito for deletado com sucesso (ou se o hábito não existia, a operação é idempotente).

---