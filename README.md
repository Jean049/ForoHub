# Api ForoHub 
  ![spring](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white)
  ![java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
  ![mysql](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)

Api Rest de un foro para challenge de Allura utilizando spring boot 3 para la creacion del proyecto, 
MYSQL para la base de datos y Java 17 para el desarrollo utilizando el IDE Intellij y Insomnia para
realizar pruebas de los endpoints

## Tecnologias utilizadas:

- Java 17
- Spring boot 3
- MySql
- Intellij IDE
- Insomnia

## Dependencias utilizadas en Spring Boot

- Spring web
- Spring Security
- Validation
- Spring data Jpa
- Flayway
- MySql
- Devtools
- Lombok
- Springdocs

## Funcionalidades de la Api

De los endpoints `/usuarios`, `/topicos`, `/respuestas`, el metodo delete solo puede ser utilizado por Admin el resto por usuarios

- `Crear-Post`: Se puede crear un nuevo elemento
- `Actualizar-Put`: Se puede actualizar un nuevo elemento
- `Listar-Get`: Se puede listar todos los elementos guardados
- `Eliminar-Delete`: Se pueden elminar elementos

## Documentacion de la Api
Para la documentacion se utilizo SpringDocs y Swagger, para visualizarla se puede ingresar a los siguientes enlaces:

- [Swagger-ui](http://localhost:8080/swagger-ui/index.html#/)
- [api-docs](http://localhost:8080/context-path/v3/api-docs)
