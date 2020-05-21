package dev.aashutosh.ec.controller;

import dev.aashutosh.ec.dto.getUserDto;
import dev.aashutosh.ec.model.User;
import dev.aashutosh.ec.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<User>> findAll() {
        List<User> users = service.getAllUsers();
        return new ResponseEntity<>(users, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User createUser = null;
        try {
            createUser = service.createUser(user);
            return new ResponseEntity<>(createUser, new HttpHeaders(), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Unable to create user", e.getCause());
        }
    }

    @PostMapping("/get")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Optional<User>> findAll(@Valid @RequestBody getUserDto dto) {
        try {
            Optional<User> user = service.getUserByEmailOrMobile(dto);
            return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found", e.getCause());
        }
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<User> update(@Valid @RequestBody User dto) {
        try {
            User user = service.updateUser(dto);
            return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user found", e.getCause());
        }
    }
}
