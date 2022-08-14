package claiborne.blog.api.springboot.exception;

import claiborne.blog.api.springboot.payload.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* Specific Exceptions */

/*
A controller advice allows you to use exactly the same exception handling techniques but apply them across the
whole application, not just to an individual controller. You can think of them as an annotation driven interceptor.
 */
@ControllerAdvice

public class GlobalExceptionHandler {

  // Works without modifying ResourceNotFoundException class code
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                      WebRequest webRequest) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
        webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BlogAPIException.class)
  public ResponseEntity<ErrorDetails> handleBlogAPIException(BlogAPIException exception, WebRequest webRequest) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
        webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  /* Global/Catch-All Exception Handler */
  @ExceptionHandler(Exception.class) // catch-all
  public ResponseEntity<ErrorDetails> catchAllException(Exception exception, WebRequest webRequest) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),
                                                 webRequest.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Enhanced/custom web response when @Valid checks in DTO fail
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                                      WebRequest webRequest) {
    Map<String, String> errors = new HashMap<>();
    exception.getBindingResult().getAllErrors().forEach((err) -> {
      String fieldName = ((FieldError)err).getField();
      String errorMessage = err.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}
