package com.example.url.Controller;

import com.example.url.User.*;
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
@GetMapping("/user/login-check")//Post mapping changed to get
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
//@PostMapping("/user/delete-user")
//    public Boolean delete(@RequestBody String username){
//    System.out.println(username+"//**/*/");
//    userDao.delete_by_ID(username);
//    k=userDao.exists(username);
//    return k;
//}
@PostMapping("/user/delete-user")
public Boolean delete(@RequestBody DeleteData deleteData){
    userDao.delete_by_ID(deleteData.getUsername());
    k=userDao.exists(deleteData.getUsername());
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
//@GetMapping("/user/getoneuser")
//    public User getoneuser(@RequestBody String username){
//    user=userDao.find(username);
//    return user.orElse(null);
//}

@GetMapping("/user/get-shortened")
    public String shorten_url(@RequestBody UrlShorten urlShorten){
    int c=1;
    Iterable<User> userIterable=userDao.findall();
    for( User x: userIterable){
        if(x.getUrl_shorten().equals(urlShorten.getUrl_shorten())){
            c=0;
            break;
        }
    }
    if(c==1) {
        user = userDao.find(urlShorten.getUsername());
        user.orElse(null).setUrl_Full(urlShorten.getUrl());
        user.orElse(null).setUrl_shorten(urlShorten.getUrl_shorten());
        userDao.save(user.orElse(null));
        user = userDao.find(urlShorten.getUsername());
        if (user.orElse(null).getUrl_shorten() != null && user.orElse(null).getUrl_Full() != null) {
            return user.orElse(null).getUrl_shorten();
        }
        else {
            return "failed0";
        }
    }
    else{
        return "failed1";
    }

}

}



