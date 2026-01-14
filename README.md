# biblioRESTeca
Projeto de teste Back-End, onde o desafio proposto era de fazer uma aplicação no modelo Rest  de uma biblioteca pessoal 
de controle de livros, usando Kotlin como linguagem de programação e o framework Spring Boot.

## Instruções para rodar o projeto

### Pré-requisitos
- Java 17
- Gradle
- IDE de sua preferência (IntelliJ IDEA recomendado)
- Postman 

| Passo 1 | Passo 2 | Passo 3 |
|---------|---------|---------|
| Clonar o repositório | Execute a classe principal: ApiApplication.kt| Com o postman aberto fazer as consultas HTTP |
|| que será iniciado em: http://localhost:8080     |

  - O banco H2 pode ser acessado em:
  - http://localhost:8080/h2-console
  - Configurações padrão do H2:
    - JDBC URL: jdbc:h2:mem:testdb
    - User: sa
    - Password: (vazio)

## Como testar os endpoints
[Recomendação] - Utilizar o Postman para fazer os testes

### Criar um livro (POST)
insira a url http://localhost:8080/api/books no postman e insira os valores no body em formato JSON seguindo o modelo

**Exemplo de body (JSON)**
```
{
  "title": "Clean Code",
  "author": "Robert C. Martin",
  "isbn": "1234567890123",
  "category": "TECHNOLOGY"
}
```
Resposta esperada:
Status: 201 Created
Retorna o livro cadastrado com id, title, author, isbn, category, status e registeredAt, seguindo o modelo:
```
{
  "id": 1,
  "title": "Clean Code",
  "author": "Robert Martin",
  "isbn": "1234567890123",
  "category": "TECHNOLOGY",
  "status": "AVAILABLE",
  "registeredAt": "2025-01-12T20:10:30",
}
```

### Listar livros (GET)
insira a url http://localhost:8080/api/books no postman e você terá o seguinte modelo de reposta

```
{
    "id": 1,
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "isbn": "1234567890123",
    "category": "TECHNOLOGY",
    "status": "AVAILABLE",
    "registeredAt": "2026-01-13T14:32:10.123",
  }
```

Resposta esperada:
Status: 200 OK

### 🔧 PATCH – Atualizar status do livro
nsira a url http://localhost:8080/api/books/{id}/status e insira os valores no body em formato JSON seguindo o modelo:
```
{
  "status": "BORROWED"
}
```
o retorno esperado é: 
```
{
  "id": 1,
  "title": "Clean Code",
  "author": "Robert Martin",
  "isbn": "1234567890123",
  "category": "TECHNOLOGY",
  "status": "BORROWED",
  "registeredAt": "2025-01-12T20:10:30",
  "updatedAt": "2025-01-12T21:45:10"
}
```

## ⚠️ Tratamento de Erros

A API retorna códigos HTTP apropriados de acordo com o tipo de erro ocorrido durante o processamento das requisições.

### ❌ 400 — Bad Request

Retornado quando a requisição enviada pelo cliente é inválida.
Situações comuns:

Campos obrigatórios ausentes

Título ou autor vazios

ISBN com tamanho diferente de 13 caracteres

Corpo da requisição ausente ou malformado

#### Exemplo:
```
{
  "title": "",
  "author": "A",
  "isbn": "123"
}
```
Resposta:
```
{
  "status": 404,
"error": Bad Request,
  "message": "Erro de validação nos campos enviados"
}
```
### ❌ 404 — Not Found

Retornado quando o recurso solicitado não existe.

Situações comuns:

Livro não encontrado pelo id

Tentativa de atualizar um livro inexistente

### Exemplo:
```
  PATCH /api/books/999/status
```
Resposta:
```
{
  "status": 404,
  "error": "Not Found",
  "message": "Livro com id 999 não encontrado"
}
```
### ❌ 500 — Internal Server Error
Retornado quando ocorre um erro inesperado no servidor.

Possíveis causas:
Exceções não tratadas

Erros de conversão de tipos

Falhas internas do Spring ou JPA

Resposta:
{
  "status": 500,
  "error": "Internal Server Error",
  "message": "Erro interno no servidor"
}


## Decisões e Observações

- O projeto começou primariamenteo pelo banco de dados H2 foi para facilitar os testes dos endpoints durante o desenvolvimento.
  A aplicação foi iniciada com uma base reduzida (1 a 3 livros), permitindo validar as funcionalidades antes
  da implementação completa das requisições.
- Foi avaliada a inclusão de novos status para os livros (READED e TO-READ), porém a ideia foi descartada
  por serem conceitualmente subcategorias do status AVAILABLE, o que poderia gerar redundância no modelo
  de domínio.
- As validações nos DTOs foram implementadas ao final do desenvolvimento, pois não alteram o comportamento
  funcional da aplicação, atuando principalmente na melhoria da consistência e da qualidade dos dados recebidos.

## Ideias Implementadas

- Captura automática da data e hora de criação e atualização dos livros,
  utilizando o horário do sistema no momento da operação.
- Validações de entrada utilizando a biblioteca `jakarta.validation`,
  garantindo consistência e integridade dos dados recebidos pela API.

## Dificuldades encontradas:
* Configurar o ambiente do Kotlin e SpringBoot:
  - Como só tinha visto a parte introdutória e não cheguei a me aprofundar, tive dificuldade em configurar o ambiente,
    mas após isso ficou bem mais fácil;
* Adadptação a linguagem Kotlin:
  - Fiquei surpreso com a simplicidade para algumas coisas, diferente do que eu estava acostumado com C e C#;
  - Foi necessário traduzir alguns paradigmas, como `while` e o uso de expressões regulares (regex), para o modelo e as
    convenções do Kotlin;
* Entendimento do funcionamento do Postman:
  - Apesar de já possuir uma pequena experiência com o Insomnia, o Postman foi uma ferramenta nova,
    exigindo adaptação ao fluxo de testes de requisições HTTP, uso de parâmetros, corpo das requisições
    e interpretação das respostas retornadas pela API;
* Organização do projeto em camadas:
  - Mesmo tendo algum contato prévio com arquitetura em camadas,
    foi desafiador compreender a separação correta de responsabilidades entre controller, service, repository e DTOs,
    além de entender onde cada validação e regra de negócio deveria ser aplicada.
