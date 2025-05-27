package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.UserRepository;
import com.kodilla.ecommercee.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private JwtUtil jwtUtil;

    public List<User> getAllUsers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .toList();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public boolean blockUser(Long userId) throws UserNotFoundException {
        User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);
        if (!user.isBlocked()) {
            user.setBlocked(true);
            repository.save(user);
            return true;
        }
        return false;
    }

    public String generateToken(User user) {
        return jwtUtil.generateToken(user.getFirstName() + " " + user.getLastName());
    }


}
