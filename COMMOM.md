# Common Library for Multi-Project Use 🚀

## Overview
The **Common Library** is a shared repository designed to house utility classes, constants, HTTP response structures, exception handling, and configuration files that are reusable across multiple projects. This library aims to promote code reuse and consistency across different applications, making it easier to maintain and develop high-quality software.

## Structure of the Common Library

### 1. Utilities 🔧
- **`StringUtils.java`**: Shared string manipulation methods.
- **`DateUtils.java`**: Shared date and time manipulation methods.

### 2. Constants 🏷️
- **`HttpHeadersConstants.java`**: Common HTTP headers and values.
- **`StatusCodes.java`**: HTTP status codes or custom application status codes.
- **`ErrorMessages.java`**: Common error message constants that can be used across projects.

### 3. HTTP and Exception Handling 🌐
- **`GlobalExceptionHandler.java`**: A global exception handler that can be used across multiple Spring Boot projects.
- **`ApiResponse.java`**, **`SuccessResponse.java`**, **`ErrorResponse.java`**: Standardized response structures.

### 4. Configuration ⚙️
- **`logging-config.xml`**: Centralized logging configurations that could be shared across projects.
- **`application-common.yml`**: Base configuration settings that other applications can inherit from.

### 5. Internationalization 🌍
- **`messages.properties`**: Shared messages that can be used for consistency across projects.

## Issues and Tasks 📋

Below is a list of issues with estimated timelines and their corresponding labels.

### 1. Utilities 🔧
#### Issue 1: Create `StringUtils.java`
- **Description**: Implement shared string manipulation methods.
- **Labels**: `enhancement`, `utilities`, `backend`
- **Estimated Start Date**: `2024-08-19`
- **Estimated End Date**: `2024-08-20`

#### Issue 2: Create `DateUtils.java`
- **Description**: Implement shared date and time manipulation methods.
- **Labels**: `enhancement`, `utilities`, `backend`
- **Estimated Start Date**: `2024-08-21`
- **Estimated End Date**: `2024-08-22`

### 2. Constants 🏷️
#### Issue 3: Create `HttpHeadersConstants.java`
- **Description**: Implement common HTTP headers and values.
- **Labels**: `enhancement`, `constants`, `backend`
- **Estimated Start Date**: `2024-08-23`
- **Estimated End Date**: `2024-08-24`

#### Issue 4: Create `StatusCodes.java`
- **Description**: Implement HTTP status codes or custom application status codes.
- **Labels**: `enhancement`, `constants`, `backend`
- **Estimated Start Date**: `2024-08-25`
- **Estimated End Date**: `2024-08-26`

#### Issue 5: Create `ErrorMessages.java`
- **Description**: Implement common error message constants.
- **Labels**: `enhancement`, `constants`, `backend`
- **Estimated Start Date**: `2024-08-27`
- **Estimated End Date**: `2024-08-28`

### 3. HTTP and Exception Handling 🌐
#### Issue 6: Create `GlobalExceptionHandler.java`
- **Description**: Implement a global exception handler.
- **Labels**: `enhancement`, `http`, `backend`
- **Estimated Start Date**: `2024-08-29`
- **Estimated End Date**: `2024-08-30`

#### Issue 7: Create `ApiResponse.java`, `SuccessResponse.java`, `ErrorResponse.java`
- **Description**: Implement standardized response structures.
- **Labels**: `enhancement`, `http`, `backend`
- **Estimated Start Date**: `2024-08-31`
- **Estimated End Date**: `2024-09-01`

### 4. Configuration ⚙️
#### Issue 8: Create `logging-config.xml`
- **Description**: Implement centralized logging configurations.
- **Labels**: `enhancement`, `configuration`, `backend`
- **Estimated Start Date**: `2024-09-02`
- **Estimated End Date**: `2024-09-03`

#### Issue 9: Create `application-common.yml`
- **Description**: Implement base configuration settings.
- **Labels**: `enhancement`, `configuration`, `backend`
- **Estimated Start Date**: `2024-09-04`
- **Estimated End Date**: `2024-09-05`

### 5. Internationalization 🌍
#### Issue 10: Create `messages.properties`
- **Description**: Implement shared messages for consistency across projects.
- **Labels**: `enhancement`, `internationalization`, `backend`
- **Estimated Start Date**: `2024-09-06`
- **Estimated End Date**: `2024-09-07`

## Contributing 🎉
Please feel free to open issues or submit pull requests for any improvements. Contributions are welcome!

---

```markdown
This `README.md` file provides a clear overview of the project and detailed descriptions of the tasks/issues to be completed. The timeline estimates are based on a personal project schedule, with each task given a reasonable start and end date to fit around your other commitments.

This format should be easy for anyone contributing to the project to follow and will help keep everything organized in the Kanban board on GitHub.
