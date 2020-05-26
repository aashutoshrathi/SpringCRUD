package dev.aashutosh.ec.exception;

import dev.aashutosh.ec.utils.ApiError;
import dev.aashutosh.ec.utils.ErrorObject;
import dev.aashutosh.ec.utils.ErrorStrings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleDefault(Exception ex, WebRequest request) {
        List<ErrorObject> errors = new ArrayList<>();
        String code = ex.getLocalizedMessage();
        errors.add(new ErrorObject(code, ErrorStrings.getErrorMessage(code), ErrorStrings.getErrorReason(code)));

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<ErrorObject> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String code = error.getDefaultMessage();
            errors.add(new ErrorObject(code, ErrorStrings.getErrorMessage(code), ErrorStrings.getErrorReason(code)));
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorObject> errors = new ArrayList<>();
        String code = ErrorStrings.INVALID_INPUT_FORMAT;
        errors.add(new ErrorObject(code, ErrorStrings.getErrorMessage(code), ErrorStrings.getErrorReason(code)));

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST, errors);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }
}