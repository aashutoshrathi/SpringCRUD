package dev.aashutosh.ec.model;

import dev.aashutosh.ec.utils.ErrorStrings;
import dev.aashutosh.ec.utils.Patterns;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Email(message = ErrorStrings.INVALID_EMAIL)
    @Column(name = "email", nullable = false)
    private String email;

    @Pattern(regexp= Patterns.VALID_MOBILE, message = ErrorStrings.INVALID_MOBILE)
    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Pattern(regexp = Patterns.VALID_ADDRESS,  message = ErrorStrings.INVALID_ADDRESS)
    @Column(name = "address")
    private String address;

    public User() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }
}
