# Backend Aplikacji do Notatek

## Opis aplikacji

Aplikacja do zarządzania notatkami oparta na architekturze mikroserwisowej. Umożliwia tworzenie, edycję i organizację
notatek z możliwością tagowania oraz przypisywania zadań. System wykorzystuje Keycloak do autoryzacji oraz Kafka do
komunikacji asynchronicznej między serwisami.

## Architektura

### Serwisy:

- **Config Server** - centralna konfiguracja dla wszystkich serwisów
- **Discovery Server** - rejestracja i odkrywanie serwisów
- **Gateway** - brama API, routing
- **Note** - zarządzanie notatkami
- **Tag** - zarządzanie tagami
- **Task** - zarządzanie zadaniami
- **Notification** - obsługa powiadomień

### Technologie:

- Kotlin
- Spring Boot
- Kafka
- PostgreSQL
- Mongo
- Docker
- Keycloak
- Grafana (monitoring)
- Prometheus (metryki)
- Tempo (distributed tracing)

## Uruchomienie aplikacji

### Wymagania:

- Docker
- Docker Compose
- Java 17+
- Maven

### Kroki uruchomienia:

- **Zbudowanie mikroserwisów**
    - Żeby zbudować mikroserwisy należy użyć polecenia maven lub uruchomić plik build-services.sh
        - maven: mvn clean install
        - skrypt bash: ./build-services.sh
- **Uruchomienie infrastruktur**
    - Żeby uruchomić infrastruktórę należe uzyć polecenia docker-compiose:
        - docker-compose up

## Domyślne porty serwisów:

- Config Server: 8888
- Discovery Server: 8761
- Gateway: 8080
- Note Service: 8081
- Tag Service: 8082
- Task Service: 8083
- Notification Service: 8084

## Dostępne interfejsy:

| Narzędzie  | URL                                            | Opis                            |
|------------|------------------------------------------------|---------------------------------|
| Keycloak   | [http://localhost:8080](http://localhost:8080) | Panel zarządzania autoryzacją   |
| Grafana    | [http://localhost:3000](http://localhost:3000) | Monitoring i dashboardy         |
| AKHQ       | [http://localhost:8090](http://localhost:8090) | Zarządzanie Kafka               |
| Dev-Mail   | [http://localhost:1080](http://localhost:1080) | Przechwytywanie maili testowych |
| Prometheus | [http://localhost:9090](http://localhost:9090) | Metryki aplikacji               |

## Dane logowania:

#### Keycloak:

- Login: `admin`
- Hasło: `admin`

#### Grafana:

- Login: `admin`
- Hasło: `admin`

## Uzyskanie tokenu JWT

Aby uzyskać token JWT dla środowiska docker należy użyć realmu `note-docker`, dla środowiska lokalnego `note`
Przykład uzyskanie tokenu JWT dla środowiska docker:



```bash
curl -X POST 'http://localhost:8080/realms/note-docker/protocol/openid-connect/token' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'grant_type=password' \
  -d 'client_id=public-client' \
  -d 'username=YOUR_USERNAME' \
  -d 'password=YOUR_PASSWORD'
```

W polach `username` i `password` należy podać dane użytkownika stworzonego przez panel administracyjny keycloak

# Frontend Aplikacji do Notatek

Aplikacja frontend powstała w celu nauczenia się frontendu, nie posiada żadnej działającej funkcjonalności, wyświetlane
dane są mockowe i nie odzwierciedlają danych backendowych.