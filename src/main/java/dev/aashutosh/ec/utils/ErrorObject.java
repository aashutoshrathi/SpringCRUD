package dev.aashutosh.ec.utils;

public class ErrorObject {
    private String code;
    private String details;
    private String message;

    public ErrorObject(String code, String details, String message) {
        this.setCode(code);
        this.setDetails(details);
        this.setMessage(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
