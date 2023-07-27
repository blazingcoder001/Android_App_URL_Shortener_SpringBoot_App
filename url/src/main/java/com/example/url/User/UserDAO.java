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

    public void delete_by_ID(MyKey myKey){
        repository.deleteById(myKey);

    }
    public boolean exists(MyKey myKey){
        k=repository.existsById(myKey);
        return k;
    }
    public Optional<User> find(MyKey myKey){
        user=repository.findById(myKey);
        return user;
    }
    public Iterable<User> findall(){
        return repository.findAll();
    }


}
