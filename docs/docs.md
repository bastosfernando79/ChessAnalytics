# Chess analytics

Este documento descreve a especificação técnica e arquitetural para o desenvolvimento de uma plataforma analítica voltada para jogadores de xadrez competitivos. O sistema integra dados da API pública do Chess.com para fornecer visualizações de progresso e métricas de desempenho.

## 1. Visão Geral
O objetivo principal é permitir que o usuário acompanhe sua trajetória em direção a metas específicas (como atingir os 1500 pontos de rating) através de um dashboard intuitivo que processa dados brutos em insights estratégicos.

## 2. Arquitetura do Sistema
A aplicação utiliza uma arquitetura de camadas separadas para garantir escalabilidade e manutenção simplificada:

* **Backend (BFF - Backend For Frontend):** Desenvolvido em **Java com Spring Boot**. Responsável por consumir a API do Chess.com, aplicar lógica de negócio e servir dados otimizados via REST.
* **Frontend:** Desenvolvido em **Angular**. Responsável pela interface reativa, gerenciamento de estado com RxJS e renderização de gráficos.
* **Banco de Dados:** **PostgreSQL (SQL)** para persistência de metas de usuários, cache de partidas e logs de evolução.

## 3. Especificação Técnica (Backend - Java)

### Tecnologias Principais
* Spring Boot 3.x
* Spring Data JPA (Hibernate)
* Spring WebFlux (para chamadas não-bloqueantes via `WebClient`)
* Lombok (produtividade)

### Contrato da API Interna
**Endpoint:** `GET /api/v1/analytics/dashboard/{username}`

**Estrutura do JSON de Resposta:**
```json
{
  "username": "string",
  "current_rating": "number",
  "target_rating": "number",
  "points_to_goal": "number",
  "recent_performance": {
    "total_games": "number",
    "wins": "number",
    "losses": "number",
    "draws": "number",
    "win_rate_percentage": "number"
  },
  "best_opening": "string"
}