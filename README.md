# prueba_backend_santander

API REST para la gestión de tarjetas (emisión, activación, bloqueo y consulta), desarrollada como prueba técnica.

## Stack

- Java 17
- Spring Boot 3.5.6 (Web, Validation, Data JPA, Security, OAuth2 Resource Server, Actuator)
- PostgreSQL 16
- Flyway para las migraciones de base de datos
- springdoc-openapi para la documentación de la API
- JUnit 5 y Testcontainers para las pruebas

## Requisitos

- JDK 17 o superior
- Maven 3.9+
- Docker y Docker Compose

## Puesta en marcha

1. Levantar la base de datos:

   ```bash
   docker compose up -d
   ```

   Esto crea el contenedor `base-postgres-backend` con PostgreSQL en el puerto `5437` (base de datos, usuario y contraseña: `base`).

2. Ejecutar la aplicación:

   ```bash
   mvn spring-boot:run
   ```

   La API queda disponible en `http://localhost:8080`. Al arrancar, Flyway aplica las migraciones de `src/main/resources/db/migration`.

## Configuración

La configuración está en `src/main/resources/application.yml`:

| Propiedad | Valor |
|---|---|
| `spring.datasource.url` | `jdbc:postgresql://localhost:5437/base` |
| `spring.jpa.hibernate.ddl-auto` | `validate` (el esquema lo gestiona Flyway) |
| `spring.flyway.locations` | `classpath:db/migration` |

## Modelo de datos

- **product**: productos de tarjeta (`product_id` de 6 dígitos, `name`, `type`: `DEBIT` o `CREDIT`).
- **card**: tarjetas emitidas, con número de 16 dígitos, titular, fechas de emisión y vencimiento (3 años), estado (`ISSUED`, `ACTIVE`, `BLOCKED`), saldo y moneda (`USD`). Usa la columna `version` para control de concurrencia optimista.

Las migraciones incluyen dos productos de ejemplo: `123434` (Tarjeta Platinium, débito) y `324335` (Tarjeta Clásica, crédito).

## Estructura del proyecto

```
src/main/java/com/santander/base
├── BaseApiApplication.java   # Punto de entrada
├── controller/               # Controladores REST
├── dto/                      # Objetos de petición y respuesta
├── exception/                # Excepciones de negocio
├── model/                    # Entidades JPA y enums
├── repository/               # Repositorios JPA
└── service/                  # Lógica de negocio
```

## Pruebas

```bash
mvn test
```

Las pruebas de integración usan Testcontainers, así que Docker debe estar en ejecución.

## Licencia

MIT
