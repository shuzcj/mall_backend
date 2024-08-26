
# **Mall Backend Service**

This is a backend service for a mall application, built using the Spring Cloud ecosystem. The application currently implements user authentication and authorization using JWT, as well as service registration and discovery using Nacos.

## **Technologies Used**

- **Spring Cloud**: A set of tools for building distributed systems and microservices in Java.
- **Nacos**: Service discovery and configuration management platform.
- **Spring Gateway**: A gateway service that handles routing and provides a unified entry point for microservices.
- **OpenFeign**: A declarative HTTP client for making REST API calls between microservices.
- **JWT (JSON Web Token)**: Used for securing APIs via authentication and authorization.
- **Spring Security**: Provides comprehensive security services for Java applications.
- **MyBatis**: A persistence framework that simplifies the implementation of SQL in Java.
- **Redis**: Used for caching user session data.
- **Elasticsearch** (Planned): Will be used for implementing search functionality on the main page.

## **Modules**

### **1. User Service**
Manages user registration, login, and session management.

### **2. Gateway Service**
Handles all incoming requests, routes them to the appropriate microservice, and performs JWT-based authentication.

### **3. Common Module**
Holds utility classes, shared DTOs, and exception handling mechanisms.

### **4. Search Service** (Planned)
Handles search functionalities for the mall’s products using Elasticsearch.

## **Project Structure**

```bash
mall_backend/
├── api/                  # API gateway service
├── common/               # Shared utilities and configurations
├── gateway/              # Spring Cloud Gateway service
├── search-service/       # Elasticsearch-based search service (Planned)
└── user-service/         # User management and authentication service
```

## **Setup Instructions**

### **1. Prerequisites**

- **Java 11 or higher**
- **Maven 3.x**
- **MySQL**
- **Redis**
- **Nacos**: Download and run Nacos as a service registry.
- **Elasticsearch** (for future implementation): Download and run Elasticsearch.

### **2. Configuration**

Make sure to configure the database, Nacos server, Redis, and other properties in the `application.yml` files located in each module.

### **3. Running the Application**

1. Start the Nacos server:
   ```bash
   sh startup.sh -m standalone
   ```

2. Start the Redis server.

3. Start each microservice using Maven:
   ```bash
   mvn spring-boot:run
   ```

### **4. Accessing the Application**

- **User Registration & Login**:
    - `POST /user/login` - User login
    - `POST /user/register` - User registration
    - The JWT token returned upon successful login should be used in the `Authorization` header for subsequent requests.

### **5. Planned Features**

- **Elasticsearch Integration**: Implementing a search service for products on the main page using Elasticsearch.
- **Authorization**: Implementing role-based access control to manage user permissions.

### **6. Contributing**

Feel free to fork this repository and submit pull requests. Contributions, issues, and feature requests are welcome!

### **7. License**

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.





