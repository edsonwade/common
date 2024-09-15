package code.with.vanilson.common.exceptions.handle;

import code.with.vanilson.common.exceptions.*;
import code.with.vanilson.common.https.ErrorDetail;
import code.with.vanilson.common.utils.TimeZoneUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

import static code.with.vanilson.common.https.ErrorCodes.CONSTRAINT_VIOLATIONS_OCCURRED;
import static code.with.vanilson.common.https.ErrorCodes.INVALID_INPUT_DATA;
import static java.time.LocalDateTime.now;

/**
 * GlobalExceptionHandler
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-07-05
 */
public class GlobalExceptionHandler {
    /**
     * Handles and responds to ResourceNotFoundException exceptions.
     * This exception is thrown when a requested resource is not found in the system.
     *
     * @param ex      The ResourceNotFoundException that occurred.
     * @param request The HTTP request that triggered the exception.
     * @return A ResponseEntity containing an ErrorResponse object and an HTTP status of 404 (Not Found).
     * The ErrorResponse object contains specific error details about the not found resource.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ResourceNotFoundException ex,
                                                                 HttpServletRequest request) {
        var path = request.getRequestURI();
        return buildErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND,
                path
        );
    }

    /**
     * Handles and responds to ResourceBadRequestException exceptions.
     * This exception is thrown when a request is not well-formed or contains invalid data.
     *
     * @param ex      The ResourceBadRequestException that occurred.
     * @param request The HTTP request that triggered the exception.
     * @return A ResponseEntity containing an ErrorResponse object and an HTTP status of 400 (Bad Request).
     * The ErrorResponse object contains specific error details about the bad request.
     */
    @ExceptionHandler(ResourceBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestException(ResourceBadRequestException ex,
                                                                   HttpServletRequest request) {
        var path = request.getRequestURI();

        return buildErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                path
        );
    }

    /**
     * Handles and responds to ResourceConflictException exceptions.
     * This exception is thrown when a conflict occurs during processing a request,
     * such as attempting to create a resource that already exists.
     *
     * @param ex      The ResourceConflictException that occurred.
     * @param request The HTTP request that triggered the exception.
     * @return A ResponseEntity containing an ErrorResponse object and an HTTP status of 409 (Conflict).
     * The ErrorResponse object contains specific error details about the conflict.
     */
    @ExceptionHandler(ResourceConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleConflictRequestException(ResourceConflictException ex,
                                                                        HttpServletRequest request) {
        var path = request.getRequestURI();
        return buildErrorResponse(
                ex.getMessage(),
                HttpStatus.CONFLICT,
                path
        );
    }

    /**
     * Handles and responds to ResourceInternalServerErrorException exceptions.
     * This exception is thrown when an internal server error occurs during processing a request.
     *
     * @param ex      The ResourceInternalServerErrorException that occurred.
     * @param request The HTTP request that triggered the exception.
     * @return A ResponseEntity containing an ErrorResponse object and an HTTP status of 500 (Internal Server Error).
     * The ErrorResponse object contains specific error details about the internal server error.
     */
    @ExceptionHandler(ResourceInternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleInternalServerException(ResourceInternalServerErrorException ex,
                                                                       HttpServletRequest request) {
        var path = request.getRequestURI();

        return buildErrorResponse(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                path
        );
    }

    /**
     * Handles and responds to MethodArgumentNotValidException exceptions.
     * This exception is thrown when a method argument fails validation.
     *
     * @param ex      The MethodArgumentNotValidException that occurred.
     * @param request The HTTP request that triggered the exception.
     * @return A ResponseEntity containing an ErrorResponse object and an HTTP status of 400 (Bad Request).
     * The ErrorResponse object contains specific error details about the validation failures.
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex,
                                                                    HttpServletRequest request) {
        var path = request.getRequestURI();
        List<ErrorDetail> details = ex.getBindingResult().getAllErrors().stream()
                .map(error -> new ErrorDetail(
                        ((FieldError) error).getField(),
                        error.getDefaultMessage()
                ))
                .toList();

        return buildErrorResponse(
                INVALID_INPUT_DATA,
                HttpStatus.BAD_REQUEST,
                path
        );
    }

    /**
     * Handles and responds to ConstraintViolationException exceptions.
     * This exception is thrown when a bean validation constraint fails.
     *
     * @param ex      The ConstraintViolationException that occurred.
     * @param request The HTTP request that triggered the exception.
     * @return A ResponseEntity containing an ErrorResponse object and an HTTP status of 400 (Bad Request).
     * The ErrorResponse object contains specific error details about the constraint violations.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex,
                                                                            HttpServletRequest request) {
        var path = request.getRequestURI();
        return buildErrorResponse(
                CONSTRAINT_VIOLATIONS_OCCURRED,
                HttpStatus.BAD_REQUEST,
                path
        );
    }

    /**
     * Handles and responds to general exceptions that are not explicitly caught by other exception handlers.
     *
     * @param ex      The exception that occurred.
     * @param request The HTTP request that triggered the exception.
     * @return A ResponseEntity containing an ErrorResponse object and an HTTP status of 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex, HttpServletRequest request) {
        var path = request.getRequestURI();

        return buildErrorResponse(
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                path
        );
    }

    /**
     * Constructs a ResponseEntity containing an ErrorResponse object based on the provided parameters.
     *
     * @param message The error message.
     * @param status  The HTTP status of the error response.
     * @param path    The request URI that triggered the error.
     * @return A ResponseEntity containing an ErrorResponse object and the specified HTTP status.
     */

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus status, String path) {
        String traceId = UUID.randomUUID().toString();
        ErrorResponse errorResponse = new ErrorResponse(
                message,
                status.name(),
                TimeZoneUtil.getRandomTimeZone(),
                status.value(),
                path,
                now(),
                determineErrorCodeFromPath(path),  // Unique code for this error
                traceId
        );
        return new ResponseEntity<>(errorResponse, status);
    }

    /**
     * Determines the appropriate error code based on the given request path.
     *
     * @param path The request URI that triggered the error.
     * @return A string representing the error code.
     * <p>
     * The error code is determined by analyzing the request path and matching it to a specific resource.
     * If the path contains "/cars/", the error code "CAR_INVALID_DATA" is returned.
     */

    private String determineErrorCodeFromPath(String path) {
        if (path.contains("/api/cars/")) {
            return "Invalid car";
        } else {
            return "UNKNOWN_ERROR";
        }
    }
}