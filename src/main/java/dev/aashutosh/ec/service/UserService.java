package dev.aashutosh.ec.service;

import dev.aashutosh.ec.model.User;
import dev.aashutosh.ec.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public User getUserById(Long id) {
        return repository.getOne(id);
    }

    public User createUser(User entity) {
        entity = repository.save(entity);
        return entity;
    }

    public User updateUser(User entity) throws Exception {
        Optional<User> user = repository.findById(entity.getId());
        if(user.isPresent()) {
            User existingUser = user.get();
            existingUser.setEmail(entity.getEmail());
            existingUser.setMobile(entity.getMobile());
            existingUser.setAddress(entity.getAddress());
            existingUser = repository.save(existingUser);
            return existingUser;
        } else {
            throw new Exception("No user with such user id found");
        }
    }
}

