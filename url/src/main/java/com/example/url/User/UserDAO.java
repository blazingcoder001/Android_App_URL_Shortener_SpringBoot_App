package com.example.url.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDAO {
    boolean k;
    Optional<User> user;
    @Autowired
    private UserRepository repository;
    public void save(User user){
    repository.save(user);
    }

    public void delete_by_ID(String username){
        repository.deleteById(username);

    }
    public boolean exists(String username){
        k=repository.existsById(username);
        return k;
    }
    public Optional<User> find(String username){
        user=repository.findById(username);
        return user;
    }


}
