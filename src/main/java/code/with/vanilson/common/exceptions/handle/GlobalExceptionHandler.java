package code.with.vanilson.common.exceptions.handle;

import code.with.vanilson.common.exceptions.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.util.Objects;

import static code.with.vanilson.common.constants.ErrorCodes.*;
import static code.with.vanilson.common.constants.TimeZoneConstants.*;
import static java.time.LocalDateTime.now;

/**
 * GlobalExceptionHandler
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-07-05
 */
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex,
                                                                       HttpServletRequest request) {
        var errorResponse = new ErrorResponse(
                ex.getMessage(),
                NOT_FOUND,
                ZONE_LISBON,
                getPath(request),
                Objects.requireNonNull(HttpStatus.NOT_FOUND).value(),
                now()

        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleEntityBadRequestException(ResourceBadRequestException ex,
                                                                         HttpServletRequest request) {
        var errorResponse = new ErrorResponse(
                ex.getMessage(),
                BAD_REQUEST,
                ZONE_LONDON,
                getPath(request),
                Objects.requireNonNull(HttpStatus.BAD_REQUEST).value(),
                now()

        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEntityAlreadyExistsException(ResourceAlreadyExistsException ex,
                                                                            HttpServletRequest request) {
        var errorResponse = new ErrorResponse(
                ex.getMessage(),
                CONFLICT,
                ZONE_PARIS,
                getPath(request),
                Objects.requireNonNull(HttpStatus.CONFLICT).value(),
                now()

        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(ResourceValidationException.class)
    public ResponseEntity<ErrorResponse> handleEntityValidationException(ResourceValidationException ex,
                                                                         HttpServletRequest request) {
        var errorResponse = new ErrorResponse(
                ex.getMessage(),
                UNPROCESSABLE_ENTITY,
                ZONE_HONG_KONG,
                getPath(request),
                Objects.requireNonNull(HttpStatus.UNPROCESSABLE_ENTITY).value(),
                now()

        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException ex,
                                                                            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                INTERNAL_SERVER_ERROR,
                ZONE_HONG_KONG,
                getPath(request),
                Objects.requireNonNull(HttpStatus.INTERNAL_SERVER_ERROR).value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Helper method to get the path from the request
    private String getPath(HttpServletRequest request) {
        return new ServletWebRequest(request)
                .getRequest()
                .getRequestURI();
    }
}