package com.example.url.Controller;

import com.example.url.User.User;
import com.example.url.User.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
public class Controller {
    Boolean k;
    @Autowired
    UserDAO userDao;
    Optional<User> user;
    User user1, user2;
    @Autowired
    JdbcTemplate jdbcTemplate;


//    @PostMapping("/user/get-shortened")
//    public String get_shortened_url(String original, String short_pref){
//
//    }
@PostMapping("/user/signup")
    public void insert_user( User user){
    userDao.save(user);
}
@PostMapping("/user/login-check")
    public Boolean check_login( String username){
    k=userDao.exists(username);
    return k;
}
@PostMapping("/user/delete-user")
    public Boolean delete(String username){
    userDao.delete_by_ID(username);
    k=userDao.exists(username);
    return k;
}
@PostMapping("/user/change-password")
    public Boolean change_password(String username, String oldpas, String newpas){
    user=userDao.find(username);
    user1=user.orElseThrow(()->new NoSuchElementException("User not Found!"));
    user2=user1;
    user1.setPassword(newpas);
    return !user1.getPassword().equals(user2.getPassword());
}



}
