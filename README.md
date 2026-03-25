# Skill 9 - Global Exception Handling using ControllerAdvice

A comprehensive Spring Boot project demonstrating global exception handling using `@RestControllerAdvice` and `@ExceptionHandler` annotations with input validation.

## 📋 Project Overview

This module showcases:
- **Global Exception Handling** - Centralized exception handling across the application
- **ControllerAdvice** - Spring's `@RestControllerAdvice` for global exception management
- **Validation** - Request validation using `@Valid` and Jakarta validation constraints
- **Error Response Mapping** - Structured error responses for client consumption
- **Student CRUD Operations** - Complete REST API for student management
- **DTO Pattern** - Request and Response DTOs for data transfer

## ✨ Features

### 1. **Add Student** 
   - Endpoint: `POST /stud/addStudent`
   - Validates: name, email format, branch, course, positive fees
   - Returns: Student with auto-generated ID and password

### 2. **Get All Students**
   - Endpoint: `GET /stud/getAllStudents`
   - Retrieve all students from database

### 3. **Get Student by ID**
   - Endpoint: `GET /stud/getStudentById/{id}`
   - Retrieve specific student details

### 4. **Update Student**
   - Endpoint: `PUT /stud/updateStudent/{id}`
   - Update student information with validation
   - Returns: Updated student details

### 5. **Delete Student**
   - Endpoint: `DELETE /stud/deleteStudent/{id}`
   - Remove student from database

## 🏗️ Project Architecture

### Model (Entity)
- **Student.java** - JPA Entity with Lombok annotations
  - Fields: id, name, email, branch, course, fees, password
  - Table: student_skill10

### DTOs
- **StudentRequestDTO.java** - Input validation
  - Validates: name (not empty), email (proper format), branch/course (not blank), fees (positive)
- **StudentResponseDTO.java** - Output data transfer
  - Contains: id, name, email, branch, course, fees

### Controller
- **StudentController.java** - REST endpoints
  - CORS enabled for all origins
  - RequestMapping: `/stud`
  - Handles validation and delegates to service

### Service
- **StudentService.java** - Business logic
  - CRUD operations
  - DTO to Entity conversion using ModelMapper
  - Auto-assigns password "klu123" for new students

### Repository
- **StudentRepo.java** - Data access layer
  - Extends JpaRepository<Student, Long>
  - Basic CRUD operations

### Exception Handling
- **GlobalExceptionHandler.java** - Centralized exception handling
  - `@RestControllerAdvice` - Global exception handler
  - Handles `MethodArgumentNotValidException` - Validation errors with field-level error messages
  - Handles generic `Exception` - All other exceptions

### Configuration
- **ModelMapperConfig.java** - ModelMapper bean configuration
  - Provides ModelMapper for DTO-Entity mapping

## 📚 Exception Handling Flow

```
Request → Controller → Service → Repository
                         ↓
                   Exception thrown
                         ↓
                GlobalExceptionHandler
                         ↓
                 Structured Response
```

## 🗄️ Database Schema

**Table: student_skill10**
```sql
CREATE TABLE student_skill10 (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    branch VARCHAR(255),
    course VARCHAR(255),
    fees DOUBLE,
    password VARCHAR(255)
);
```

## 🛠️ Technologies Used

- **Spring Boot** 3.0.0
- **Spring Data JPA** - ORM framework
- **Hibernate** - JPA implementation
- **MySQL** - Database
- **Maven** - Build tool
- **Java** 21
- **Lombok** - Boilerplate reduction
- **ModelMapper** - DTO-Entity mapping
- **Springdoc OpenAPI** - Swagger UI documentation
- **Jakarta Validation** - Input validation

## 📁 Project Structure

```
src/main/
├── java/com/klu/
│   ├── Skill9Application.java
│   ├── model/
│   │   └── Student.java
│   ├── controller/
│   │   └── StudentController.java
│   ├── service/
│   │   └── StudentService.java
│   ├── repo/
│   │   └── StudentRepo.java
│   ├── dto/
│   │   ├── StudentRequestDTO.java
│   │   └── StudentResponseDTO.java
│   ├── exception/
│   │   └── GlobalExceptionHandler.java
│   └── config/
│       └── ModelMapperConfig.java
└── resources/
    └── application.properties

pom.xml
README.md
.gitignore
```

## ⚙️ Configuration

### Database Setup
Update `application.properties` with your MySQL configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/skill9_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

### Server Port
Default port: **2026**

## 🚀 Getting Started

### Prerequisites
- Java 21 or higher
- MySQL Server running
- Maven 3.8+

### Installation
1. Clone the repository
2. Update database credentials in `application.properties`
3. Create MySQL database: `CREATE DATABASE skill9_db;`
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

### API Documentation
Once running, access Swagger UI at: `http://localhost:2026/swagger-ui.html`

## 📝 Testing the API

### Using cURL Examples:

**Add a student (with validation):**
```bash
curl -X POST http://localhost:2026/stud/addStudent \
  -H "Content-Type: application/json" \
  -d '{
    "name":"John Doe",
    "email":"john@example.com",
    "branch":"CSE",
    "course":"Java",
    "fees":5000
  }'
```

**Invalid request (validation error):**
```bash
curl -X POST http://localhost:2026/stud/addStudent \
  -H "Content-Type: application/json" \
  -d '{
    "name":"",
    "email":"invalid-email",
    "branch":"",
    "course":"Java",
    "fees":-1000
  }'
# Response: Field validation errors
```

**Get all students:**
```bash
curl -X GET http://localhost:2026/stud/getAllStudents
```

**Get student by ID:**
```bash
curl -X GET http://localhost:2026/stud/getStudentById/1
```

**Update student:**
```bash
curl -X PUT http://localhost:2026/stud/updateStudent/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name":"Jane Doe",
    "email":"jane@example.com",
    "branch":"ECE",
    "course":"Python",
    "fees":6000
  }'
```

**Delete student:**
```bash
curl -X DELETE http://localhost:2026/stud/deleteStudent/1
```

## 🔍 Validation Constraints

| Field | Constraint | Message |
|-------|-----------|---------|
| name | @NotEmpty | Student name is required |
| email | @Email | Email must have proper format |
| branch | @NotBlank | Branch must not be null |
| course | @NotBlank | Course must not be null |
| fees | @Positive | Fees must be positive number |

## 📌 Key Concepts Demonstrated

1. **Global Exception Handling** - @RestControllerAdvice
2. **Input Validation** - Jakarta validation annotations
3. **DTO Pattern** - Request/Response separation
4. **ModelMapper** - Automatic DTO-Entity mapping
5. **REST API Best Practices** - Proper HTTP status codes and responses
6. **Spring Data JPA** - Database operations
7. **Error Response Formatting** - Structured error messages
8. **CORS Configuration** - Cross-Origin Resource Sharing

## 💡 Exception Handling Examples

**Validation Error Response:**
```json
{
  "name": "Student name is required",
  "email": "Email must have proper format",
  "fees": "Fees must be positive number"
}
```

**General Error Response:**
```
No value present
```

**Student Not Found:**
```
No value present (from orElseThrow())
```

## 👨‍💻 Author

KLU - Skill 9 Global Exception Handling Module

## 📄 License

This project is for educational purposes.
