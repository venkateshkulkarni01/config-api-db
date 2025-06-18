# Config API

#Swagger :  http://localhost:8080/swagger-ui/index.html
This project is a Spring Boot application that provides a RESTful API for managing configuration properties. The application allows users to create, read, update, and delete configuration properties, which can be stored in an H2 in-memory database.

## Project Structure

The project follows a standard Maven structure with the following key components:

- **src/main/java/com/example/configapi**: Contains the main application code.
  - **ConfigApiApplication.java**: The entry point of the Spring Boot application.
  - **controller**: Contains the REST controller for managing configuration properties.
    - **ConfigController.java**: Defines the endpoints for CRUD operations.
  - **model**: Contains the entity class for configuration properties.
    - **ConfigProperty.java**: Represents the configuration property entity.
  - **repository**: Contains the Spring Data JPA repository interface.
    - **ConfigPropertyRepository.java**: Provides methods for CRUD operations on configuration properties.
  - **service**: Contains the service layer for business logic.
    - **ConfigPropertyService.java**: Interacts with the repository to manage configuration properties.

- **src/main/resources**: Contains application configuration files.
  - **application.properties**: Configuration settings for the Spring Boot application, including H2 database settings.
  - **data.sql**: Initializes the H2 database with default configuration properties.

- **src/test/java/com/example/configapi**: Contains unit tests for the application.
  - **ConfigApiApplicationTests.java**: Ensures that the application context loads correctly.

- **pom.xml**: The Maven configuration file that includes dependencies for Spring Boot, Spring Data JPA, H2 database, and testing libraries.

## Getting Started

### Prerequisites

- Java 21
- Maven

### Running the Application

1. Clone the repository:
   ```
   git clone <repository-url>
   cd config-api
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

### API Endpoints

- **GET /api/config**: Retrieve all configuration properties.
- **GET /api/config/{id}**: Retrieve a specific configuration property by ID.
- **POST /api/config**: Create a new configuration property.
- **PUT /api/config/{id}**: Update an existing configuration property by ID.
- **DELETE /api/config/{id}**: Delete a configuration property by ID.

### Database Initialization

The application uses an H2 in-memory database, and the `data.sql` file is used to initialize it with default values upon startup.

## License

This project is licensed under the MIT License. See the LICENSE file for details.