package dev.aashutosh.ec.controller;

import dev.aashutosh.ec.dto.GetUserDto;
import dev.aashutosh.ec.dto.UpdateUserDto;
import dev.aashutosh.ec.model.User;
import dev.aashutosh.ec.service.UserService;
import dev.aashutosh.ec.utils.ErrorStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public List<User> findAll() throws Exception {
        List<User> users = service.getAllUsers();
        if (users.isEmpty()) {
            throw new Exception(ErrorStrings.NO_USER_FOUND);
        }
        return users;
    }

    @PostMapping("/create")
    public User create(@RequestBody @Valid User user) throws Exception {
        return service.createUser(user);
    }

    @PostMapping("/get")
    public Optional<User> findByMobileOrEmail(@RequestBody @Valid GetUserDto dto) throws Exception {
        return service.getUserByEmailOrMobile(dto);
    }

    @PutMapping("/update")
    public User update(@Valid @RequestBody UpdateUserDto dto) throws Exception {
        return service.updateUser(dto);
    }
}
