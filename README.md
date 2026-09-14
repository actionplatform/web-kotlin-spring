# web-kotlin-spring



## Run

```bash
mvn -B spring-boot:run          # http://localhost:8000/ping
```

## Test / lint

```bash
mvn -B verify                   # tests + ktlint
```

## Layout

Five layers; each one depends only on the ones below it.

```
src/main/kotlin/com/actionplatform/webkotlinspring/
├── Application.kt     Spring Boot entry point · Version.kt
├── api/               HTTP only — controllers; PingController, v1/ItemsController
├── dto/               data classes that cross the HTTP boundary
├── service/           business rules; throw DomainException; no web imports
├── repository/        data access — one class per store, one method per query
└── core/              DomainException (+ NotFound, Validation, Conflict), ApiExceptionHandler
src/test/kotlin/…/
├── service/           rules, with a repository and no HTTP
└── api/               contract through MockMvc
```

`api` calls `service`, never a repository. Services throw `DomainException` subclasses; `core/ApiExceptionHandler` answers `{"detail": {"code", "message", "field"}}`. The in-memory `ItemRepository` is the placeholder for a database or an external API.
