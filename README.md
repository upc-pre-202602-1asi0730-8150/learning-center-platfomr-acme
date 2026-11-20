# Tesla Autonomous Fleet Management Platform

## Overview

RESTful API for the Tesla autonomous fleet operations. It registers **fleet dispatches**
(routing plans with strict time windows, dynamic energy consumption and waypoint traceability).

- Java 27, Spring Boot 4.1.2, Spring Data JPA, MySQL (schema `tesla`)
- Domain-Driven Design, layered architecture, CQRS, Result pattern
- Bounded contexts: `fleet` (core) and `shared`
- OpenAPI / Swagger UI, i18n of messages (EN, EN-US, ES, ES-PE via `Accept-Language`)

## Authorship

- GitHub repository (private): <PASTE-THE-REPOSITORY-URL-HERE>

## Author

Fabrizio Rafael Tello Palacios (u202113310) - Section 7729

## Endpoint

| Method | URL                      | Description                |
|--------|--------------------------|----------------------------|
| POST   | `/api/v1/fleet-dispatches` | Creates a fleet dispatch |

Swagger UI: http://localhost:8080/swagger-ui/index.html

## Run

1. Start MySQL and set `spring.datasource.username` / `spring.datasource.password` in `application.properties`.
2. Run `Pc27729u202113310Application` (the `tesla` schema is created automatically).
