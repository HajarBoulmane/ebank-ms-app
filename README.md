# EBank Microservices

Application de gestion de comptes bancaires basée sur une architecture microservices (Spring Boot, Spring Cloud).

## Architecture

| Service | Port | Rôle |
|---|---|---|
| discovery-service | 8761 | Registre Eureka |
| customer-service | 8056 | Gestion des clients |
| ebank-service | 8057 | Gestion des comptes bancaires |
| gateway-service | 9999 | Point d'entrée unique, routage dynamique |

- Communication synchrone ebank-service vers customer-service via OpenFeign
- Tolérance aux pannes avec Resilience4j (Circuit Breaker et fallback)
- Documentation de l'API avec Swagger UI

## Prérequis

- Java 21
- Maven 3.9+
- Spring Boot 3.5.10

## Démarrage

Lancer les services dans cet ordre :

1. discovery-service
2. customer-service
3. ebank-service
4. gateway-service

Vérifier l'enregistrement des services sur `http://localhost:8761`.

## Endpoints

Via la gateway (port 9999) :

- `GET /CUSTOMER-SERVICE/customers`
- `GET /CUSTOMER-SERVICE/customers/{id}`
- `POST /CUSTOMER-SERVICE/customers`
- `GET /EBANK-SERVICE/accounts`
- `GET /EBANK-SERVICE/accounts/{id}`
- `POST /EBANK-SERVICE/accounts`

Swagger UI (accès direct) :

- `http://localhost:8056/swagger-ui.html`
- `http://localhost:8057/swagger-ui.html`

## Test de la tolérance aux pannes

1. Démarrer tous les services.
2. Appeler `GET /EBANK-SERVICE/accounts/{id}` : le compte contient les informations du client.
3. Arrêter customer-service et rappeler : le compte est retourné avec un client par défaut ("Not available").

## Configuration

Aucune clé n'est stockée dans le code. Pour les parties utilisant l'API OpenAI, définir la variable d'environnement :

```
OPENAI_API_KEY=<votre clé>
```

## Parties du projet

- Partie 1 : customer-service, ebank-service, OpenFeign, Resilience4j, gateway, discovery-service
- Partie 2 : chatbot Spring AI, exposition des services via MCP
