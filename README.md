# ♟️ Chess Analytics Dashboard

Uma plataforma full-stack desenvolvida para jogadores de xadrez competitivos que buscam acompanhar a evolução do seu rating de partidas rápidas (foco inicial na meta de 1500 pontos). O sistema atua como um intermediário inteligente (BFF), consumindo os dados públicos do Chess.com e renderizando painéis interativos.

<!--![Screenshot do Dashboard](./assets/dashboard-preview.png)
*(Dica: Crie uma pasta chamada `assets` na raiz do projeto, salve o print da sua tela lá com este nome e apague esta frase de dica!)* -->

---

## 🚀 Tecnologias Utilizadas

### **Backend (BFF - Backend For Frontend)**
* **Java 21** & **Spring Boot 3**
* **Spring Data JPA**: Para persistência e modelagem de dados.
* **Spring WebFlux (WebClient)**: Cliente HTTP não-bloqueante para consumir a API pública do Chess.com de forma rápida.
* **PostgreSQL**: Banco de dados relacional (preparado para cache e histórico de evolução).
* **Lombok**: Automação de getters, setters e construtores.

### **Frontend (SPA)**
* **Angular (v19+)**: Framework estrutural com componentes autônomos (Standalone Components).
* **Chart.js & ng2-charts**: Renderização do gráfico de rosca para a taxa de vitórias.
* **SCSS**: Estilização baseada em flexbox.
* **SSR (Server-Side Rendering)**: Pré-renderização no lado do servidor para maior performance.

---

## 🏗️ Arquitetura do Sistema

O projeto utiliza a arquitetura **BFF (Backend For Frontend)**. Para garantir segurança, performance e baixo acoplamento:
1. O Angular (Frontend) nunca se comunica diretamente com o Chess.com. Ele faz uma requisição para a API local em Java.
2. O Java (Backend) busca o JSON gigantesco do Chess.com, extrai apenas o necessário, calcula a porcentagem de vitórias e a distância para a meta, e devolve um objeto leve e limpo para o navegador.

---

## 🔧 Como Executar o Projeto Localmente

### 1. Pré-requisitos
* **Java Development Kit (JDK) 21**
* **Node.js (v22+)**
* **PostgreSQL** rodando localmente na porta padrão (5432).

### 2. Configuração do Banco de Dados
Abra o terminal do PostgreSQL (`psql`) e execute:

```sql
CREATE DATABASE chess_analytics;
CREATE USER admin_chess WITH PASSWORD 'senha123';
GRANT ALL PRIVILEGES ON DATABASE chess_analytics TO admin_chess;
ALTER DATABASE chess_analytics OWNER TO admin_chess;
```

### 3. Executando o Backend (Java)
Navegue até a pasta do backend (onde está o arquivo `pom.xml`) e inicie o servidor Spring Boot:

```bash
./mvnw spring-boot:run
```
*O servidor estará disponível em `http://localhost:8080`*

### 4. Executando o Frontend (Angular)
Abra um novo terminal, navegue até a pasta do frontend e instale as dependências:

```bash
npm install
```

Em seguida, inicie o servidor de desenvolvimento:

```bash
npm start
```
*O dashboard estará disponível no seu navegador em `http://localhost:4200`*

---

## 📈 Funcionalidades
- [x] Consumo em tempo real da PubAPI do Chess.com (`/pub/player/{username}/stats`).
- [x] Lógica de Backend para cálculo de *Win Rate* (Taxa de Vitórias) e diferença de pontos para a meta.
- [x] Apresentação visual de dados através de gráficos interativos com Chart.js.
- [x] Configuração de Proxy no Angular para evitar bloqueios de CORS em ambiente de desenvolvimento.

---

## 🛠️ Próximos Passos (Roadmap)
- [ ] **Persistência de Histórico:** Salvar o rating de cada busca no PostgreSQL para gerar um gráfico de linha temporal.
- [ ] **Tematização:** Aplicar a paleta de cores oficial do Palmeiras à interface.
- [ ] **Múltiplos Ritmos:** Adicionar botões para alternar as estatísticas entre Rapid, Blitz e Bullet.
- [ ] **Tratamento de Erros de UI:** Toast notifications (alertas) quando o usuário não for encontrado na API.

---
