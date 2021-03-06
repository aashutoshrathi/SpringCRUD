package dev.aashutosh.ec.dto;

import dev.aashutosh.ec.utils.ErrorStrings;
import dev.aashutosh.ec.utils.Patterns;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class GetUserDto {
    @Pattern(regexp = Patterns.VALID_MOBILE, message = ErrorStrings.INVALID_MOBILE)
    public String mobile;

    @Email(message = ErrorStrings.INVALID_EMAIL)
    public String email;
}
