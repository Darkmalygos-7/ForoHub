# ForoHub API 🚀

API REST desarrollada como challenge técnico para la gestión de un foro, implementando autenticación con **JWT**, control de acceso con **Spring Security**, arquitectura en capas y buenas prácticas de desarrollo backend.

---

## 🧩 Descripción General

ForoHub es una API que permite:

* Registro y autenticación de usuarios.
* Gestión de cursos.
* Creación, listado, detalle, actualización y eliminación lógica de tópicos.
* Protección de endpoints mediante tokens JWT.

La aplicación está construida con **Spring Boot**, **Spring Security**, **JPA/Hibernate**, **Flyway** y **MySQL**.

---

## 🏗️ Arquitectura del Proyecto

La estructura del proyecto sigue una arquitectura en capas clara y mantenible:

```
java
└── com.darkmalygos.forohub
    ├── config
    │   └── CorsConfiguration
    ├── controller
    │   ├── AuthenticationController
    │   ├── CursoController
    │   ├── TopicoController
    │   └── UsuarioController
    ├── domain
    │   ├── curso
    │   │   ├── Categoria (enum)
    │   │   ├── Curso
    │   │   ├── CursoRepository
    │   │   ├── CursoService
    │   │   ├── DatosDetalleCurso
    │   │   └── DatosRegistroCurso
    │   ├── topico
    │   │   ├── DatosActualizacionTopicos
    │   │   ├── DatosDetalleTopico
    │   │   ├── DatosListaTopicos
    │   │   ├── DatosRegistroTopico
    │   │   ├── Topico
    │   │   ├── TopicoRepository
    │   │   └── TopicoService
    │   └── usuario
    │       ├── DatosAutenticacion
    │       ├── DatosDetalleUsuario
    │       ├── DatosRegistroUsuario
    │       ├── Usuario
    │       ├── UsuarioRepository
    │       └── UsuarioService
    ├── infra
    │   ├── exceptions
    │   │   ├── GestorDeErrores
    │   │   └── ValidacionException
    │   └── security
    │       ├── AutenticacionService
    │       ├── DatosTokenJWT
    │       ├── SecurityConfiguration
    │       ├── SecurityFilter
    │       └── TokenService
    └── ForohubApplication
```

---

## 🔐 Seguridad y Autenticación

La API implementa autenticación basada en **JWT**:

* Endpoint público `/login` para autenticación.
* Generación de token JWT con expiración de **1 hora**.
* Filtro de seguridad (`SecurityFilter`) que intercepta las peticiones y valida el token.
* Uso de `UserDetails` y `AuthenticationManager` de Spring Security.
* API configurada como **STATELESS** (sin sesiones).

---

## ⚠️ Manejo de Errores

Se implementó un sistema de manejo de errores personalizado:

* `GestorDeErrores`: maneja errores HTTP comunes como **400 (Bad Request)** y **404 (Not Found)**.
* `ValidacionException`: utilizada para enviar mensajes claros y controlados al cliente.

---

## 🗄️ Base de Datos y Migraciones

La base de datos es gestionada con **Flyway**, asegurando control de versiones del esquema.

Migraciones incluidas:

```
V1__create_table_usuarios.sql
V2__create_table_cursos.sql
V3__create_table_topicos.sql
V4__add_status_to_topicos.sql
```

Se implementa eliminación lógica de tópicos mediante el campo `status`.

---

## ⚙️ Configuración (application.properties)

La aplicación utiliza variables de entorno para proteger datos sensibles.

Ejemplo de configuración:

```
spring.application.name=forohub

spring.datasource.url=jdbc:mysql://${DB_HOST}/foro_hub
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}

spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.flyway.locations=classpath:db/migration
spring.flyway.validate-on-migrate=true
spring.flyway.repair-on-migrate=true
logging.level.org.flywaydb=info

spring.jpa.show-sql=true

api.security.token.secret=${JWT_SECRET}
```

### 🔎 Explicación rápida:

* `DB_HOST`: host de la base de datos MySQL.
* `DB_USER` / `DB_PASSWORD`: credenciales de la base de datos.
* `JWT_SECRET`: clave secreta para firmar los tokens JWT.
* Flyway gestiona automáticamente las migraciones al iniciar la aplicación.

---

## ▶️ Ejecución del Proyecto

1. Clonar el repositorio
2. Configurar variables de entorno:

    * `DB_HOST`
    * `DB_USER`
    * `DB_PASSWORD`
    * `JWT_SECRET`
3. Ejecutar la aplicación:

```bash
./mvnw spring-boot:run
```

---

## 🧪 Endpoints Principales

* `POST /login` → Autenticación y generación de JWT
* `POST /usuarios` → Registro de usuario
* `GET /topicos` → Listar tópicos
* `GET /topicos/{id}` → Detalle de tópico
* `PUT /topicos/{id}` → Actualizar tópico
* `DELETE /topicos/{id}` → Eliminación lógica de tópico

*(Los endpoints protegidos requieren el header `Authorization: Bearer <token>`)*

---

## 📌 Tecnologías Utilizadas

* Java 17+
* Spring Boot
* Spring Security
* JWT (Auth0)
* JPA / Hibernate
* Flyway
* MySQL
* Maven

---

## 👨‍💻 Autor

Desarrollado por **Jaiver Andrey Manso Osorio** como challenge técnico backend.

---

✅ Proyecto enfocado en buenas prácticas, seguridad y escalabilidad.
