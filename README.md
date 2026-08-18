# 📦 Inventory Management API

API REST para gerenciamento de estoque desenvolvida com **Java + Spring Boot**.

O projeto permite gerenciar categorias e produtos, incluindo controle de estoque, busca de produtos e documentação da API com Swagger.

---

## 🚀 Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Docker
- Flyway
- Lombok
- Maven
- Swagger (OpenAPI)

---

## 📁 Estrutura do projeto

```
src
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
└── config
```

---

## ✨ Funcionalidades

### Categorias

- Criar categoria
- Listar categorias
- Buscar categoria por ID
- Atualizar categoria
- Excluir categoria

### Produtos

- Criar produto
- Listar produtos
- Buscar produto por ID
- Atualizar produto
- Excluir produto
- Buscar produtos por nome
- Listar produtos com estoque baixo

---

## 🛠️ Como executar

### Clone o projeto

```bash
git clone https://github.com/Jknpp/inventory-management-api.git
```

### Entre na pasta

```bash
cd inventory-management-api
```

### Suba o banco de dados

```bash
docker compose up -d
```

### Execute a aplicação

```bash
mvn spring-boot:run
```

---

## 📚 Documentação da API

Após iniciar a aplicação:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 🗄️ Banco de dados

O projeto utiliza:

- MySQL
- Flyway para controle de migrações

---

## 👨‍💻 Autor

**Jairo Karnopp**

GitHub:

https://github.com/Jknpp