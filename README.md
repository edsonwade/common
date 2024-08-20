# 📦 Common Library Management Utilities

Welcome to the **Common Library Management Utilities** repository! 🎉

This repository is a central hub for all the shared components, utilities, and common functionalities used across various projects within the Library Management ecosystem.

## 📚 Purpose

The **Common Library Management Utilities** repository is designed to promote code reuse, reduce duplication, and ensure consistency across multiple projects. By centralizing shared code in this repository, we can maintain a clean, scalable, and maintainable architecture throughout our Library Management System.

## 🧩 What This Repository Contains

This repository includes various packages and classes that are commonly needed across different services and applications within the Library Management System. Here's a breakdown of what you'll find:

### 📌 Constants (`constants`)
- **`LibraryConstants.java`**: A collection of constant values used throughout the application, such as date formats, success messages, error messages, etc.

### 💼 Data Transfer Objects (`dto`)
- **`ApiResponse.java`**: A generic wrapper for API responses, making it easy to standardize the response format across services.
- **`ErrorResponse.java`**: A specialized response object for handling errors, providing clarity and consistency in error handling.
- **`PagingRequest.java`**: A simple DTO for handling pagination in API requests.

### 🚨 Exceptions (`exceptions`)
- **`CustomException.java`**: A base class for all custom exceptions, ensuring that exception handling is consistent.
- **`ResourceNotFoundException.java`**: Thrown when a requested resource is not found, providing a standard approach to this common scenario.
- **`ValidationException.java`**: Used for handling validation errors, making it easier to manage data integrity.

### 🔧 Utilities (`utils`)
- **`DateUtils.java`**: Utility methods for date manipulation and formatting.
- **`StringUtils.java`**: Helper methods for common string operations.
- **`ValidatorUtils.java`**: Utility methods for validating inputs, such as checking email formats.

### 🌐 HTTP Handling (`http`)
- **`RequestHandler.java`**: Common methods for managing and extracting data from HTTP requests.
- **`ResponseHandler.java`**: Utility methods for creating standardized HTTP responses.

## 🚀 How to Use This Repository

### 1. Add the Dependency

To use the `common` library in your project, include the following dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>com.library.management</groupId>
    <artifactId>common</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Key Features:
- **Emojis**: Added throughout the file to make it visually appealing and easier to read.
- **Clear Structure**: The README is structured to provide a quick overview of the repository’s purpose, its contents, and how to use it.
- **Versioning and Contributions**: Sections on versioning and contributions to guide developers on how to engage with the project.


### Resources for Further Learning
1. [Spring Boot Documentation](https://docs.spring.io/spring-boot/index.html)
2. [HTTP Specification](https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods)
3. [API Design Best Practices](https://restfulapi.net/)
4. [OpenAPI Specification:](https://swagger.io/specification/)
