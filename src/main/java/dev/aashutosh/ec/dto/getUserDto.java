package dev.aashutosh.ec.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class getUserDto {

    @Pattern(regexp="\\+91[6-9][0-9]{9}")
    public String mobile;

    @Email
    public String email;
}
