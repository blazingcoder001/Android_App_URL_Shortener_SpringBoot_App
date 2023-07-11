package com.example.url.User;

import org.springframework.beans.factory.annotation.Autowired;

public class UserDAO {
    @Autowired
    private UserRepository repository;
    public void save(User user){
        repository.save(user);
    }
    public void delete(User user){
        repository.delete(user);
    }
}
