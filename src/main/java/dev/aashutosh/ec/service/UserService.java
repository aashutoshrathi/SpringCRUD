package dev.aashutosh.ec.service;

import dev.aashutosh.ec.dto.GetUserDto;
import dev.aashutosh.ec.dto.UpdateUserDto;
import dev.aashutosh.ec.model.User;
import dev.aashutosh.ec.repo.UserRepository;
import dev.aashutosh.ec.utils.ErrorStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAllUsers() {
        return (List<User>) repository.findAll();
    }

    public Optional<User> getUserByEmailOrMobile(GetUserDto dto) throws Exception {
        Optional<User> userObj = Optional.empty();
        if (dto.email != null) {
            userObj = repository.findByEmail(dto.email);
        } else if (dto.mobile != null) {
            userObj = repository.findByMobile(dto.mobile);
        }

        if (Objects.requireNonNull(userObj).isEmpty()) {
            throw new Exception(ErrorStrings.NO_USER_FOUND);
        }
        return userObj;
    }

    public User createUser(User entity) throws Exception {
        Optional<User> sameEmailUser = repository.findByEmail(entity.getEmail());
        if (sameEmailUser.isPresent())
            throw new Exception(ErrorStrings.EMAIL_TAKEN);

        Optional<User> sameMobileUser = repository.findByMobile(entity.getMobile());
        if (sameMobileUser.isPresent())
            throw new Exception(ErrorStrings.MOBILE_TAKEN);

        entity = repository.save(entity);
        return entity;
    }

    public User updateUser(UpdateUserDto dto) throws Exception {
        Optional<User> user = repository.findByMobile(dto.mobile);
        if (user.isEmpty())
            throw new Exception(ErrorStrings.NO_USER_FOUND);

        User existingUser = user.get();
        existingUser.setAddress(dto.address);
        existingUser = repository.save(existingUser);
        return existingUser;
    }
}

