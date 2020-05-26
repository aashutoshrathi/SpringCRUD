package dev.aashutosh.ec.utils;

public class ErrorStrings {

    public static final String INVALID_MOBILE = "001";
    public static final String INVALID_EMAIL = "002";
    public static final String INVALID_ADDRESS = "003";
    public static final String MOBILE_TAKEN = "004";
    public static final String EMAIL_TAKEN = "005";
    public static final String NO_USER_FOUND = "006";
    public static final String INVALID_INPUT_FORMAT = "007";
    public static final String VALIDATION_FAILED = "Validation Failed";
    public static final String CONFLICT = "Constraint Check Failed";

    public static String getErrorMessage(String errorCode) {
        return switch (errorCode) {
            case INVALID_MOBILE -> "Mobile Number is invalid";
            case INVALID_EMAIL -> "Email Id is invalid";
            case INVALID_ADDRESS -> "Address contains special character(s).";
            case MOBILE_TAKEN -> "Mobile Number already taken";
            case EMAIL_TAKEN -> "Email Id already taken";
            case NO_USER_FOUND -> "No User with such credentials found";
            case INVALID_INPUT_FORMAT -> "Invalid Content";
            default -> "Unidentified error occurred";
        };
    }

    public static String getErrorReason(String errorCode) {
        return switch (errorCode) {
            case INVALID_MOBILE, INVALID_EMAIL, INVALID_ADDRESS, NO_USER_FOUND -> VALIDATION_FAILED;
            case MOBILE_TAKEN, EMAIL_TAKEN -> CONFLICT;
            case INVALID_INPUT_FORMAT -> "Invalid JSON Format";
            default -> "Something terrible happened";
        };
    }
}
