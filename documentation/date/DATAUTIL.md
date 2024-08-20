# Key Methods and Their Uses with `java.time`

## Date Formatting and Parsing

- **`formatDate(LocalDate date)`**: Formats a `LocalDate` using the default format.
- **`formatDate(LocalDateTime dateTime, DateTimeFormatter formatter)`**: Formats a `LocalDateTime` using a specified format.
- **`parseDate(String dateStr)`**: Parses a `LocalDate` from a string with the default format.
- **`parseDateTime(String dateStr)`**: Parses a `LocalDateTime` from a string with the default format.

## Date Manipulation

- **`addDays(LocalDate date, int days)`**: Adds days to a `LocalDate`.
- **`subtractDays(LocalDate date, int days)`**: Subtracts days from a `LocalDate`.
- **`getDifferenceInDays(LocalDate startDate, LocalDate endDate)`**: Gets the difference in days between two `LocalDate` objects.

## Date Boundaries

- **`getStartOfDay(LocalDate date)`**: Gets the start of the day for a `LocalDate`.
- **`getEndOfDay(LocalDate date)`**: Gets the end of the day for a `LocalDate`.
- **`getFirstDayOfMonth(LocalDate date)`**: Gets the first day of the month for a `LocalDate`.
- **`getLastDayOfMonth(LocalDate date)`**: Gets the last day of the month for a `LocalDate`.

## Conversion Between Types

- **`toDate(LocalDateTime localDateTime)`**: Converts a `LocalDateTime` to a `Date`.
- **`toLocalDateTime(Date date)`**: Converts a `Date` to a `LocalDateTime`.

## Date Queries

- **`isDateInPast(LocalDate date)`**: Checks if a `LocalDate` is in the past.
- **`isDateInFuture(LocalDate date)`**: Checks if a `LocalDate` is in the future.
- **`isWeekend(LocalDate date)`**: Checks if a `LocalDate` falls on a weekend.
- **`isWeekday(LocalDate date)`**: Checks if a `LocalDate` falls on a weekday.
- **`getDayOfWeek(LocalDate date)`**: Gets the day of the week as a string.

## Additional Utilities

- **`getCurrentDateTime(DateTimeFormatter formatter)`**: Gets the current date-time formatted with a specified `DateTimeFormatter`.
- **`getDaysInMonth(int year, int month)`**: Gets the number of days in a specific month of a year.
- **`getCurrentTime(DateTimeFormatter formatter)`**: Gets the current time formatted with a specified `DateTimeFormatter`.

## Notes

- **`java.time` Package**: The `java.time` package is part of Java 8 and later, providing a more robust and flexible date-time API compared to the older `Date` and `Calendar` classes.
- **Thread Safety**: `DateTimeFormatter` is thread-safe and can be used safely in concurrent applications.


- **GET**: No request body, response body.
- **POST**: Request body, response body.
- **PUT**: Request body, response body.
- **PATCH**: Request body, response body.
- **DELETE**: Request body (optional), response body (optional).

1. POST
   - Purpose: Used to create a new resource.
   - Request Body: Should include a payload that specifies the details of the resource being created.
   - Payload Validation: Essential to ensure that the request body conforms to the expected format and contains all 
     required data.
2. PUT
   - Purpose: Used to update an existing resource or create a new resource if it does not exist at the specified URI.
   - Request Body: Should include a payload that specifies the updated or new resource.
   - Payload Validation: Important to check that the request body includes the correct data and adheres to the 
     expected format.
3. PATCH
   - Purpose: Used to apply partial updates to an existing resource.
   - Request Body: Should include a payload with the changes to be applied.
   - Payload Validation: Crucial to ensure that the partial update data is valid and follows the expected format.
   - Methods Without Request Body Validation


### Methods Without Request Body Validation
1. GET

    Purpose: Retrieve data from the server.
    Request Body: Generally, GET requests do not have a body.
    Payload Validation: Not applicable as there is no request body.
2. DELETE
    - Purpose: Remove a resource from the server.
    - Request Body: While some implementations may include a body, it is not common or standard practice.
    - Payload Validation: Typically not required, though it may be used in some specific scenarios or custom 
      implementations.s.


### POST Request Example
```java
@PostMapping
public ResponseEntity<Student> createStudent(@RequestBody Student student) {
    // Validate payload
    if (student == null || student.getName() == null || student.getAge() <= 0) {
        return ResponseEntity.badRequest().build();
    }

    try {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}

```

### PUT Request Example

```java
@PutMapping("/{id}")
public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
    // Validate payload
    if (student == null || student.getName() == null || student.getAge() <= 0) {
        return ResponseEntity.badRequest().build();
    }

    try {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedStudent);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


```
### PATCH Request Example
```java
@PatchMapping("/{id}")
public ResponseEntity<Student> partiallyUpdateStudent(@PathVariable Long id, @RequestBody Student student) {
    // Validate payload
    if (student == null || (student.getName() == null && student.getAge() <= 0)) {
        return ResponseEntity.badRequest().build();
    }

    try {
        Student updatedStudent = studentService.partiallyUpdateStudent(id, student);
        if (updatedStudent == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(updatedStudent);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
```

### Refined Header Validation in the Service Layer

1. Update the Service Method
```java
package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor injection for StudentRepository
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getListOfAllStudents(String acceptHeader, String contentTypeHeader) {
        // Validate Content-Type header
        if (!"application/json".equalsIgnoreCase(contentTypeHeader)) {
            throw new UnsupportedMediaTypeStatusException("Unsupported Content-Type", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        // Validate Accept header
        if (!"application/json".equalsIgnoreCase(acceptHeader)) {
            throw new UnsupportedMediaTypeStatusException("Unsupported Accept media type", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        // Fetch students from repository
        return studentRepository.findAll();
    }
}
```
- **In this example**:
- Custom Exception: We use UnsupportedMediaTypeStatusException from Spring Web to provide more specific HTTP status 
handling. This exception allows us to specify the status code directly.

2. Handle Exceptions in the Controller
```java
package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    // Constructor injection for StudentService
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getListOfAllStudents(
            @RequestHeader(value = HttpHeaders.ACCEPT, defaultValue = "application/json") String acceptHeader,
            @RequestHeader(value = HttpHeaders.CONTENT_TYPE, defaultValue = "application/json") String contentTypeHeader) {

        try {
            // Fetch students from the service
            List<Student> students = studentService.getListOfAllStudents(acceptHeader, contentTypeHeader);

            // Validate payload is not null or empty
            if (students == null || students.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }

            // Create response headers
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set(HttpHeaders.CONTENT_TYPE, "application/json");
            responseHeaders.set(HttpHeaders.CONTENT_LENGTH, String.valueOf(students.toString().length()));

            // Return response entity with headers and body
            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(students);
        } catch (UnsupportedMediaTypeStatusException e) {
            // Handle specific exception for unsupported media type
            return ResponseEntity.status(e.getStatus()).body(null);
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
```

- **In this updated controller**:

- **Exception Handling**: We handle UnsupportedMediaTypeStatusException specifically and provide a 415 Unsupported 
  Media Type response. This helps in giving a clear indication of what went wrong.
General Exception Handling: A catch block for Exception is added to handle unexpected errors gracefully.

### Summary
- **Service Layer**:

Validates the Content-Type and Accept headers.
Uses UnsupportedMediaTypeStatusException for clarity and appropriate HTTP status.

1. Controller Layer:

- Handles exceptions thrown by the service and provides appropriate HTTP responses.
- Includes logic for validating payloads and formatting response headers.
This approach ensures that header validation is robust and that the application responds with appropriate status codes and messages for different scenarios.