package com.korbes.jarvis.order.processing.service.lib.handler;

import com.korbes.jarvis.order.processing.service.lib.error.ErrorResponse;
import com.korbes.jarvis.order.processing.service.lib.error.ValidatorError;
import com.korbes.jarvis.order.processing.service.lib.exceptions.KorbesNotFoundException;
import com.korbes.jarvis.order.processing.service.lib.exceptions.KorbesServiceRunTimeException;
import com.korbes.jarvis.order.processing.service.lib.exceptions.KorbesValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(KorbesNotFoundException.class)
  public ResponseEntity<?> handleInsuranceNotFoundException(KorbesNotFoundException exception) {
    ErrorResponse errorResponse = ErrorResponse.builder().
            localDateTime(LocalDateTime.now()).message(exception.getMessage())
            .details(String.valueOf(exception)).build();
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(KorbesServiceRunTimeException.class)
  public ResponseEntity<?> handleInsuranceNotFoundException(KorbesServiceRunTimeException exception) {
    ErrorResponse errorResponse = ErrorResponse.builder().
            localDateTime(LocalDateTime.now()).message(exception.getMessage())
            .details(String.valueOf(exception)).build();
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(KorbesValidationException.class)
  public ResponseEntity<?> handleInsuranceValidationException(
          List<ValidatorError> validatorErrors) {
    return new ResponseEntity<>(validatorErrors, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleMethodArgumentNotValidException(
          BindingResult bindingResult) {
    Map<String, List<String>> errorMap = bindingResult.getAllErrors()
            .stream()
            .collect(Collectors.groupingBy(
                    ObjectError::getCode,
                    Collectors.mapping(ObjectError::getDefaultMessage, Collectors.toList())
            ));
    return new ResponseEntity<>(errorMap,
            HttpStatus.UNPROCESSABLE_ENTITY);
  }
}