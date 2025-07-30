
# Simplify Tasks API

API RESTful para gerenciamento de tarefas desenvolvida com Spring Boot. Este projeto é parte de um desafio técnico backend júnior e possui operações completas de CRUD com suporte a prioridade e status das tarefas.

## 🚀 Tecnologias

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Jakarta Persistence (JPA)
- UUID como identificador de entidade

## 📦 Funcionalidades

- ✅ Criar uma nova tarefa
- ✅ Listar todas as tarefas
- ✅ Buscar uma tarefa por ID
- ✅ Filtrar tarefas por status ou prioridade
- ✅ Atualizar informações da tarefa
- ✅ Atualizar apenas o status da tarefa
- ✅ Deletar uma tarefa

## 🧩 Entidade

```java
TaskModel {
  UUID idTask;
  String name;
  String description;
  Priority priority; // BAIXA, MEDIA, ALTA
  Status status;     // FEITO, EM_ANDAMENTO, PARADO
}
````

## 🧠 Enums

* `Priority`: `BAIXA`, `MEDIA`, `ALTA`
* `Status`: `FEITO`, `EM_ANDAMENTO`, `PARADO`

## 🛠️ Requisitos

* Java 17+
* MySQL rodando na porta `3306`
* Maven

## ⚙️ Configuração

Configure o banco de dados em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/simplify_tasks
spring.datasource.username=root
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## ▶️ Executando o Projeto

```bash
# Compile e rode a aplicação
./mvnw spring-boot:run
```

A API será iniciada em: `http://localhost:8080`

## 🔁 Rotas principais

| Método | Endpoint              | Ação                     |
| ------ | --------------------- | ------------------------ |
| GET    | `/tasks`              | Lista todas as tarefas   |
| GET    | `/tasks/{id}`         | Busca tarefa por ID      |
| GET    | `/tasks?status=...`   | Filtra por status        |
| GET    | `/tasks?priority=...` | Filtra por prioridade    |
| POST   | `/tasks`              | Cria uma nova tarefa     |
| PUT    | `/tasks/{id}`         | Atualiza tarefa completa |
| PUT    | `/tasks/{id}/status`  | Atualiza apenas o status |
| DELETE | `/tasks/{id}`         | Deleta uma tarefa        |

### 📝 Exemplo de body (POST)

```json
{
  "name": "Estudar Spring Boot",
  "description": "Completar o módulo de API REST",
  "priority": "ALTA",
  "status": "EM_ANDAMENTO"
}
```

### 📝 Exemplo de body (PUT status)

```json
{
  "status": "FEITO"
}
```

## 🔄 CORS

CORS habilitado para o frontend rodando em `http://localhost:3000` localmente. Isso permite integração com aplicações React ou Next.js durante o desenvolvimento.

---

## 🤝 Contribuição

Sinta-se livre para abrir issues ou sugestões para melhorar o projeto.

---

## 🧑‍💻 Autor

Desenvolvido por **Davi Garutti Diniz**

