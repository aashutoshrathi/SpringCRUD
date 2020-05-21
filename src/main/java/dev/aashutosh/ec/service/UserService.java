package dev.aashutosh.ec.service;

import dev.aashutosh.ec.dto.getUserDto;
import dev.aashutosh.ec.model.User;
import dev.aashutosh.ec.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUserByEmailOrMobile(getUserDto dto) throws Exception {
        Optional<User> userObj = null;
        if(dto.email != null) {
            userObj = getUserByEmail(dto.email);
        }
        else if(dto.mobile != null) {
            userObj = getUserByMobile(dto.mobile);
        }

        if(Objects.requireNonNull(userObj).isEmpty()) {
            throw new Exception("Unable to update");
        }
        return userObj;
    }

    public Optional<User> getUserByMobile(String mobile) {
        return repository.findByMobile(mobile);
    }

    public Optional<User> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User createUser(User entity) throws Exception {
        Optional<User> sameEmailUser = repository.findByEmail(entity.getEmail());
        if(sameEmailUser.isPresent())
            throw new Exception("Email already used by another user");

        Optional<User> sameMobileUser = repository.findByMobile(entity.getMobile());
        if(sameMobileUser.isPresent())
            throw new Exception("Mobile number already used by another user");

        entity = repository.save(entity);
        return entity;
    }

    public User updateUser(User entity) throws Exception {
        Optional<User> user = repository.findById(entity.getId());
        if(user.isPresent()) {
            User existingUser = user.get();
            Optional<User> sameEmailUser = repository.findByEmail(entity.getEmail());
            if(sameEmailUser.isPresent() && !sameEmailUser.get().getId().equals(entity.getId()))
                throw new Exception("Email already used by another user");
            existingUser.setEmail(entity.getEmail());

            Optional<User> sameMobileUser = repository.findByMobile(entity.getMobile());
            if(sameMobileUser.isPresent() && !sameMobileUser.get().getId().equals(entity.getId()))
                throw new Exception("Mobile number already used by another user");
            existingUser.setMobile(entity.getMobile());
            existingUser.setAddress(entity.getAddress());
            existingUser = repository.save(existingUser);
            return existingUser;
        } else {
            throw new Exception("No user with such user id found");
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

