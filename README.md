# 🍔 Food Ordering System – Backend (Spring Boot)

A **secure and scalable backend REST API** for a food ordering application built using **Spring Boot**, **Spring Security (JWT)**, **JPA/Hibernate**, and **H2/MySQL**.  
This project demonstrates **real-world backend concepts** such as authentication, authorization, role-based access, password encryption, and API documentation with Swagger.

---

## 🚀 Features

- 🔐 JWT-based Authentication & Authorization
- 👤 User Signup & Login APIs
- 🔑 Secure Password Encryption (BCrypt)
- 🛡️ Spring Security with Stateless Sessions
- 📦 RESTful APIs using Spring MVC
- 🗄️ Database support:
    - H2 (In-Memory for development)
    - MySQL (Production-ready)
- 📄 Swagger (OpenAPI) API Documentation
- ⚙️ Exception handling & clean architecture
- 🧩 Layered architecture (Controller → Service → Repository)

---

## 🧱 Tech Stack

| Technology | Description |
|---------|------------|
| Java | Java 8 |
| Spring Boot | 2.6.6 |
| Spring Security | JWT-based authentication |
| JPA / Hibernate | ORM |
| Databases | H2, MySQL |
| JWT | jjwt (0.11.5) |
| Swagger | springdoc-openapi-ui |
| Build Tool | Maven |
| Server | Embedded Tomcat |

---

## 📂 Project Package Structure

```text
com.foodies
├─ controllers   # REST Controllers
├─ service       # Service Interfaces
├─ serviceImpl   # Business Logic Implementations
├─ repository    # JPA Repositories
├─ entity        # JPA Entities
├─ config        # Security Configuration
├─ util          # JWT Utilities & Filters
└─ dto           # Request / Response DTOs

```

---

## 🔐 Authentication Flow (JWT)

1. **User Signup**
    - Password is encrypted using BCrypt
    - User stored in database

2. **User Login**
    - Credentials validated
    - JWT Token generated and returned

3. **Access Secured APIs**
    - Client sends JWT in `Authorization` header
    - Token validated via `JwtFilter`
    - Spring Security sets authentication context

---

## 📌 API Endpoints (Sample)

### 🔑 Auth APIs
| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/user/signup` | Register new user |
| POST | `/user/login` | Login & get JWT |
| POST | `/user/change_password` | Change password |

### 🔒 Secured APIs
All other APIs are **JWT protected** and require:

Authorization: Bearer <JWT_TOKEN>

## 📄 Swagger API Documentation

Once the application is running, open:

http://localhost:8080/swagger-ui/index.html

yaml
Copy code

Use Swagger UI to test APIs and pass JWT token via **Authorize** button.

---

## ⚙️ Application Configuration

### H2 Database (Development)

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:foodies
    driver-class-name: org.h2.Driver
    username: sa
    password:
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

H2 Console
### 🔗 H2 Database Console

```bash
http://localhost:8080/h2-console
```

## ▶️ How to Run the Project

### 🔧 Prerequisites
- Java 8
- Maven
- IDE (IntelliJ IDEA / Eclipse)

### 🚀 Ensure Git is Installed
Make sure Git is installed on your system before proceeding.

### 📦 Steps to Run

```bash
git clone https://github.com/aniketghule045/food-ordering-systems.git
cd food-ordering-systems
mvn clean install
mvn spring-boot:run
```

## 🌐 Application Access

The application will start at:

```text
http://localhost:8080
```

## 🛡️ Security Highlights

- 🔐 Stateless authentication using **JWT**
- 🔑 Password encryption using **BCrypt**
- 🚫 CSRF disabled for **REST APIs**
- 👥 Role-based authorization support
- 🛡️ Secure endpoints using **SecurityFilterChain**

## 🔑 Sample Login Request & Response

### 📥 Login Request
```json
{
  "email": "user@gmail.com",
  "password": "password123"
}
```

### 📤 Login Response

```json
{
  "status": "success",
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "user": {
    "id": 1,
    "email": "user@gmail.com",
    "role": "customer"
  }
}
```

## 🚀 Future Enhancements

- 🛒 Cart & Order Management
- 🧾 Payment Gateway Integration
- 📦 Restaurant & Menu APIs
- 👥 Role-based Access (Admin / Customer)
- ☁️ Deployment on Docker
- 📊 Logging & Monitoring


## 👨‍💻 Author

**Aniket Ghule**  
Backend Developer | Java | Spring Boot | Microservices
