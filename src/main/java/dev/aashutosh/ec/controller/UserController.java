package dev.aashutosh.ec.controller;

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

import static dev.aashutosh.ec.helper.ErrorMessages.notFound;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User createUser = null;
        try {
            createUser = service.createUser(user);
            return new ResponseEntity<>(createUser, new HttpHeaders(), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, notFound(user.getId()), e.getCause());
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<User>> findAll() {
        List<User> users = service.getAllUsers();
        return new ResponseEntity<>(users, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<User> findAll(@PathVariable("id") Long id) {
        User user = service.getUserById(id);
        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        try {
            User updatedUser = service.updateUser(user);
            return new ResponseEntity<>(updatedUser, new HttpHeaders(), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, notFound(user.getId()), e.getCause());
        }
    }
}
