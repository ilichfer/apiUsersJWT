# technical test API

SmartJob es una API RESTful desarrollada en Java con Spring Boot, diseñada para la gestión de usuarios y sus teléfonos, incluyendo autenticación y manejo de sesiones.

### tecnologias requeridas
`Java 17` `Maven`


## Características principales

- Registro de usuarios con validación de email y persistencia de teléfonos asociados.
- Autenticación y generación de tokens JWT.
- Gestión de sesiones de usuario.
- Endpoints documentados con Swagger/OpenAPI.
- Manejo de errores y respuestas estructuradas.

## Estructura del proyecto

- **Modelo:** Entidades JPA (`User`, `Phone`, `UserSession`).
- **DTOs:** Objetos de transferencia para entrada/salida de datos.
- **Mappers:** Conversión entre entidades y DTOs.
- **Repositorios:** Interfaces JPA para acceso a datos.
- **Servicios:** Lógica de negocio y validaciones.
- **Controladores:** Exposición de endpoints REST.

## Endpoints principales

- `POST /users/saveUser`  
  Registra un usuario con sus teléfonos.  
  **Body:** JSON con datos de usuario y lista de teléfonos.


## Validaciones

- El email se valida con expresión regular y debe ser único.
- La contraseña se almacena encriptada.
- Los teléfonos deben asociarse correctamente al usuario.

## Especificaciones técnicas

- **Java 17+**
- **Spring Boot 3+**
- **Maven**
- **JPA/Hibernate**
- **JWT para autenticación**
- **Swagger/OpenAPI para documentación**
- **Base de datos relacional** (configurable en `application.properties`)

## Instalación y ejecución

1. Clona el repositorio.
2. Configura la base de datos en `src/main/resources/application.properties`.
3. Ejecuta `mvn clean install`.
4. Inicia la aplicación con `mvn spring-boot:run`.
5. Accede a la documentación en `http://localhost:8080/swagger-ui.html`.

## Ejemplo de JSON para registro de usuario

```json
{
  "name": "Juan Perez",
  "email": "juan@mail.com",
  "password": "123456",
  "phones": [
    {
      "number": "123456789",
      "cityCode": "1",
      "countryCode": "57"
    }
  ]
}

## Diagrama de la solucion

+-------------------+
|   UserController  |
+-------------------+
          |
          v
+-------------------+
|   IUserService    |
+-------------------+
          |
          v
+-------------------+
|  UserServiceImpl  |
+-------------------+
          |
          v
+-------------------+
|   UserSession     |
|   User            |
+-------------------+
          |
          v
+-------------------+
|   Repositorios    |
+-------------------+
          |
          v
+-------------------+
|   Base de Datos   |
+-------------------+
