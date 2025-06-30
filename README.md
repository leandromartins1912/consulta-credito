# Consulta de Créditos

Sistema composto por frontend Angular, backend Spring Boot, banco de dados PostgreSQL e Kafka para mensagens. Utiliza Flyway para versionamento e migração automática do banco de dados.

---

## 🔧 Requisitos

- Docker e Docker Compose instalados
- (Opcional) Node.js e Angular CLI para rodar o frontend localmente
- (Opcional) Java e Maven para rodar o backend localmente

---

## 🚀 Como subir o sistema com Docker

1. Clone o repositório:

```bash
git clone https://github.com/seuusuario/consulta-credito.git
cd consulta-credito
```

2. Crie um arquivo .env na raiz do projeto com o seguinte conteúdo:
   
POSTGRES_DB=credito
POSTGRES_USER=login
POSTGRES_PASSWORD=senha
POSTGRES_PORT=5432
KAFKA_SERVERS=kafka:29092

3. Suba os containers com build limpo:
   
```bash
docker-compose up --build
```

Isso iniciará:

📦 Banco de Dados PostgreSQL (porta 5432)

🐘 Zookeeper (porta 2181)

🧭 Kafka (porta externa 9092, interna 29092)

🔧 Backend Spring Boot (porta 8080)

🌐 Frontend Angular (porta 4200)


🌐 Acessos
Frontend Angular: http://localhost:4200

Backend API (Swagger): http://localhost:8080/swagger-ui.html



🗂️ Migrations com Flyway
As migrations SQL estão localizadas em:

back/src/main/resources/db/migration


O Flyway é executado automaticamente ao iniciar o backend e aplica as alterações no banco PostgreSQL.

🧑‍💻 Rodando localmente (opcional)
Backend

```bash
cd back
./mvnw spring-boot:run
```

> Certifique-se de configurar as variáveis de ambiente conforme o .env ou diretamente no application.properties.


Frontend

```bash
cd front
npm install
npm run start
```

⚙️ Detalhes Técnicos
O Dockerfile do frontend realiza o build Angular e copia o conteúdo para o Nginx.

Kafka é configurado com kafka:29092 como listener interno (para comunicação via Docker).

O volume postgres-data garante persistência dos dados no PostgreSQL.

📌 Observações
Se fizer alterações no frontend, lembre-se de rodar:

```bash
docker-compose build frontend
```

Para reiniciar todos os serviços:

```bash
docker-compose down
docker-compose up --build
```

Para verificar os logs de qualquer container:

```bash
docker logs <nome_do_container>
```

Obrigado por testar o sistema! 💬 Qualquer dúvida ou sugestão, fico à disposição.



