package demoapp.validation.controller.controlleradvice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
class ErrorHandlingControllerAdvice {

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ResponseEntity<List<Violation>> onConstraintValidationException(ConstraintViolationException e) {
    return ResponseEntity.badRequest().body(
            e.getConstraintViolations()
            .stream()
            .map(item -> new Violation(item.getPropertyPath().toString(), item.getMessage()))
            .collect(Collectors.toList())
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  ResponseEntity<List<Violation>> onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return ResponseEntity.badRequest().body(
            e.getBindingResult().getFieldErrors()
                    .stream()
                    .map(fieldError -> new Violation(fieldError.getField(), fieldError.getDefaultMessage()))
                    .collect(Collectors.toList())
    );
  }



}
