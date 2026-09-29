# CRUD de Cliente

API REST criada com Spring Boot, JPA e H2.

## Endpoints

- `GET /clientes` — lista todos os clientes
- `GET /clientes/{id}` — busca um cliente pelo ID
- `POST /clientes` — cadastra um cliente
- `PUT /clientes/{id}` — atualiza um cliente
- `DELETE /clientes/{id}` — remove um cliente

## Exemplo de cadastro

```json
{
  "nome": "Carlos Oliveira",
  "email": "carlos@email.com",
  "telefone": "11977777777",
  "cpf": "33333333333"
}
```

## Observações

- O ID é gerado automaticamente pelo banco.
- O CPF não pode ser repetido.
- Nome, e-mail e CPF são obrigatórios.
- O banco utilizado é o H2 em memória.
- O projeto está configurado para Java 21.
