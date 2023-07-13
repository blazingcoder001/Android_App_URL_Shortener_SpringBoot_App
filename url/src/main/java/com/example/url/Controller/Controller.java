package com.example.url.Controller;

import com.example.url.User.ChangePassData;
import com.example.url.User.LoginCheck;
import com.example.url.User.User;
import com.example.url.User.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class Controller {
    Boolean k;
    Boolean j;
    @Autowired
    private UserDAO userDao;
    Optional<User> user;
    User user1, user2;
//    @Autowired
//    JdbcTemplate jdbcTemplate;


//    @PostMapping("/user/get-shortened")
//    public String get_shortened_url(String original, String short_pref){
//
//    }
@PostMapping("/user/signup")
    public Boolean insert_user(@RequestBody User user){
    if(userDao.exists(user.getUsername())) {
        return false;
    }
    else {
        userDao.save(user);
        return true;
    }

}
@PostMapping("/user/login-check")
    public Integer check_login(@RequestBody LoginCheck loginCheck){
    Optional<User> user;
    k=userDao.exists(loginCheck.getUsername());
    if(k==true){
        user=userDao.find(loginCheck.getUsername());
//        System.out.println("***user.orElse(null).getPassword());
        if(user.orElse(null).getPassword().equals(loginCheck.getPassword())){
            return 3;
        }
        else{
            return 1;
        }
    }
    else{
        return 2;
    }

}
@PostMapping("/user/delete-user")
    public Boolean delete(@RequestBody String username){
    userDao.delete_by_ID(username);
    k=userDao.exists(username);
    return k;
}
@PostMapping("/user/change-password")
    public Integer change_password(@RequestBody ChangePassData changePassData){
    user=userDao.find(changePassData.getUsername());
//    user1=user.orElseThrow(()->new NoSuchElementException("User not Found!"));
//    user2=user1;
    if(user.orElse(null).getPassword().equals(changePassData.getOld_password())){
        user.orElse(null).setPassword(changePassData.getNew_password());
        userDao.delete_by_ID(changePassData.getUsername());
        userDao.save(user.orElse(null));
        return 1;
    }
    else{
        return 2;
    }

}
@PostMapping("/user/getoneuser")
    public User getoneuser(@RequestBody String username){
    user=userDao.find(username);
    return user.orElse(null);
}




}
