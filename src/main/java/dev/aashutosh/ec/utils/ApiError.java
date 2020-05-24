package dev.aashutosh.ec.utils;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiError {

    private final HttpStatus status;
    private final List<ErrorObject> errors;

    public ApiError(HttpStatus status, List<ErrorObject> errors) {
        super();
        this.status = status;
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<ErrorObject> getErrors() {
        return errors;
    }
}
