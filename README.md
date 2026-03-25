# Skill 9 - Global Exception Handling using ControllerAdvice

A Spring Boot project demonstrating global exception handling using `@RestControllerAdvice`.

## Project Overview

This module showcases:
- **Global Exception Handling** - Centralized exception handling across the application
- **ControllerAdvice** - Spring's `@RestControllerAdvice` for global exception management
- **Validation** - Request validation using `@Valid` and Jakarta validation constraints
- **Student Management** - Complete REST API for student CRUD operations
- **DTO Pattern** - Request and Response DTOs for data transfer

## Features

### API Endpoints

1. **Add Student**
   - `POST /stud/addStudent`
   - Add a new student with validation

2. **Get All Students**
   - `GET /stud/getAllStudents`
   - Retrieve all students

3. **Get Student by ID**
   - `GET /stud/getStudentById/{id}`
   - Retrieve specific student

4. **Update Student**
   - `PUT /stud/updateStudent/{id}`
   - Update student information

5. **Delete Student**
   - `DELETE /stud/deleteStudent/{id}`
   - Remove student from database

## Project Structure

```
src/main/java/com/klu/
├── model/
│   └── Student.java
├── controller/
│   └── StudentController.java
├── service/
│   └── StudentService.java
├── repo/
│   └── StudentRepo.java
├── dto/
│   ├── StudentRequestDTO.java
│   └── StudentResponseDTO.java
├── exception/
│   └── GlobalExceptionHandler.java
└── config/
    └── ModelMapperConfig.java
```

## Technologies Used

- Spring Boot 3.0.0
- Spring Data JPA
- MySQL
- Maven
- Java 21
- Lombok
- ModelMapper
- Jakarta Validation

## Configuration

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/skill9_db
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```
