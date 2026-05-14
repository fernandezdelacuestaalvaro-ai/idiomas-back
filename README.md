# English B2 Trainer

Proyecto Spring Boot Java 21 para practicar vocabulario de inglés B2/C1 utilizando arquitectura por capas.

## Tecnologías

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- MySQL
- Maven

---

# Arquitectura

```text
src/main/java/com/example/englishb2
│
├── controller
├── dto
├── model
├── repository
└── service
```

---

# Base de datos

Base de datos utilizada:

```text
idiomas
```

Tabla utilizada:

```text
vocabulary
```

Campos principales:

| Campo | Descripción |
|---|---|
| idioma | Idioma |
| castellano | Traducción en castellano |
| traduccion | Texto en inglés |
| categoria | Categoría |
| bloque_examen | Tipo de bloque |
| nivel | Nivel B2/C1 |
| orden | Orden |
| dificultad | Dificultad |

---

# Configuración

## application.properties

```properties
spring.application.name=english-b2-trainer

spring.profiles.active=local
```

## application-local.properties

```properties
server.port=8100

spring.datasource.url=jdbc:mysql://localhost:3306/idiomas?useSSL=false&useUnicode=true&serverTimezone=UTC

spring.datasource.username=root
spring.datasource.password=root

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format_sql=true
```

---

# Ejecución

## Compilar

```bash
mvn clean install
```

## Ejecutar

```bash
mvn spring-boot:run
```

Aplicación:

```text
http://localhost:8100
```

---

# Endpoints

## Iniciar sesión

### Todos

```http
POST /api/vocabulary/start
```

### Sólo B2

```http
POST /api/vocabulary/start?nivel=B2
```

### Sólo C1 Reading

```http
POST /api/vocabulary/start?nivel=C1&bloqueExamen=READING
```

---

## Pregunta actual

```http
GET /api/vocabulary/current
```

---

## Mostrar traducción

```http
GET /api/vocabulary/reveal
```

---

## Responder BIEN

```http
POST /api/vocabulary/answer
```

Body:

```json
{
  "correct": true
}
```

---

## Responder MAL

```json
{
  "correct": false
}
```

---

## Nota final

```http
GET /api/vocabulary/score
```

---

# Lógica de puntuación

- BIEN = 1 punto
- MAL = 0 puntos

Cálculo:

```text
(aciertos / total) * 10
```

Ejemplos:

| Aciertos | Nota |
|---|---|
| 10/10 | 10 |
| 8/10 | 8 |
| 5/10 | 5 |
| 3/10 | 3 |

---

# Funcionalidades futuras

- Front Angular
- Login
- Estadísticas
- Historial
- Exámenes tipo Cambridge
- Temporizador
- Modo Speaking
- Modo Grammar
- Preguntas favoritas
- Cache
- Docker
- AWS
