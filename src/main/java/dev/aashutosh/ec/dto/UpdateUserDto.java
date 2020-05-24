package dev.aashutosh.ec.dto;

import dev.aashutosh.ec.utils.ErrorStrings;
import dev.aashutosh.ec.utils.Patterns;


import javax.validation.constraints.Pattern;

public class UpdateUserDto {
    @Pattern(regexp = Patterns.VALID_MOBILE, message = ErrorStrings.INVALID_MOBILE)
    public String mobile;

    @Pattern(regexp = Patterns.VALID_ADDRESS, message = ErrorStrings.INVALID_ADDRESS)
    public String address;
}
