package com.example.url.Controller;

import com.example.url.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.NoSuchElementException;
import java.util.Optional;



@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class Controller {

    MyKey myKey=new MyKey();
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
//@PostMapping("/user/signup")
//public Boolean insert_user(@RequestBody User user){
//    if(userDao.exists(user.getUsername())) {
//        return false;
//    }
//    else {
//        userDao.save(user);
//        return true;
//    }
//
//}
@PostMapping("/user/signup")
public Boolean insert_user(@RequestBody User user){
    MyKey myKey1=new MyKey();
    myKey1.setUsername(user.getUsername());
    myKey1.setUrl_short(user.getUrl_shorten());
    if(userDao.exists(myKey1)) {
        return false;
    }
    else {
        userDao.save(user);
        return true;
    }

}
//@PostMapping("/user/login-check")//Post mapping changed to get
//public Integer check_login(@RequestBody LoginCheck loginCheck){
//    Optional<User> user;
//    k=userDao.exists(loginCheck.getUsername());
//    if(k==true){
//        user=userDao.find(loginCheck.getUsername());
////        System.out.println("***user.orElse(null).getPassword());
//        if(user.orElse(null).getPassword().equals(loginCheck.getPassword())){
//            return 3;
//        }
//        else{
//            return 1;
//        }
//    }
//    else{
//        return 2;
//    }
//
//}
@PostMapping("/user/login-check")//Post mapping changed to get
public Integer check_login(@RequestBody LoginCheck loginCheck){
    Optional<User> user;
    Iterable<User> userIterable=userDao.findall();
    for( User x: userIterable){
        if(x.getUsername()!=null && ( x.getUsername().equals(loginCheck.getUsername()))){
            k=true;
            myKey.setUrl_short(x.getUrl_shorten());
            myKey.setUsername(x.getUsername());
            break;
        }
    }
//    k=userDao.exists(loginCheck.getUsername());
    if(k==true){
//        user=userDao.find(loginCheck.getUsername());
        user=userDao.find(myKey);
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
    myKey.setUsername(deleteData.getUsername());
    myKey.setUrl_short(deleteData.getUrl_short());
    userDao.delete_by_ID(myKey);
    k=userDao.exists(myKey);
    return k;
}
@PostMapping("/user/change-password")
    public Integer change_password(@RequestBody ChangePassData changePassData){
    myKey.setUsername(changePassData.getUsername());
    myKey.setUrl_short(changePassData.getUrl_short());
//    user=userDao.find(changePassData.getUsername());
    user=userDao.find(myKey);
//    user1=user.orElseThrow(()->new NoSuchElementException("User not Found!"));
//    user2=user1;
    if(user.orElse(null).getPassword().equals(changePassData.getOld_password())){
        user.orElse(null).setPassword(changePassData.getNew_password());
        userDao.delete_by_ID(myKey);
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

//@PostMapping("/user/get-shortened")
//public StringPass shorten_url(@RequestBody UrlShorten urlShorten){
//    int c=1;
////    System.out.println("*/*/*/"+urlShorten.getUrl_shorten());
//    Iterable<User> userIterable=userDao.findall();
//    for( User x: userIterable){
//        if(x.getUrl_shorten()!=null && ( x.getUrl_shorten().equals(urlShorten.getUrl_shorten()))){
//            c=0;
//            break;
//        }
//    }
//    if(c==1) {
////        myKey.setUrl_short(urlShorten.getUrl_shorten());
////        myKey.setUsername(urlShorten.getUsername());
//        user = userDao.find(urlShorten.getUsername());
//        user.orElse(null).setUrl_Full(urlShorten.getUrl());
//        user.orElse(null).setUrl_shorten(urlShorten.getUrl_shorten());
//        userDao.delete_by_ID(urlShorten.getUsername());
//        userDao.save(user.orElse(null));
//        user = userDao.find(urlShorten.getUsername());
//        if (user.orElse(null).getUrl_shorten() != null && user.orElse(null).getUrl_Full() != null) {
//            System.out.println("*/*/*/"+user.orElse(null).getUrl_shorten());
//             //Change started
//            StringPass pass= new StringPass();
//            pass.setUrl_shorten(user.orElse(null).getUrl_shorten());
//            return pass;
//
////            String urlShorten = user.orElse(null).getUrl_shorten();
////            JSONObject json = new JSONObject();
////            json.put("url_shorten", urlShorten);
////            String jsonString = json.toString();
////            return user.orElse(null).getUrl_shorten();
//        }
//        else {
//            StringPass pass= new StringPass();
//            pass.setUrl_shorten("failed0");
//            return pass;
//        }
//    }
//    else{
//        StringPass pass= new StringPass();
//        pass.setUrl_shorten("failed1");
//        return pass;    }
//
//}
@PostMapping("/user/get-shortened")
public StringPass shorten_url(@RequestBody User user_loc){
    int c=1;
    int d=1;
//    System.out.println("*/*/*/"+urlShorten.getUrl_shorten());
    Iterable<User> userIterable=userDao.findall();
    for( User x: userIterable){
        if(x.getUrl_shorten()!=null && ( x.getUrl_shorten().equals(user_loc.getUrl_shorten()))){
            c=0;
            break;
        }
        if(x.getUsername().equals(user_loc.getUsername()) && x.getUrl_shorten()==null){
            d=0;
        }
    }
    if(c==1) {
        userDao.save(user_loc);
        if(d==0){
            myKey.setUrl_short(user_loc.getUrl_shorten());
            myKey.setUsername(user_loc.getUsername());
            userDao.delete_by_ID(myKey);
        }
//        myKey.setUrl_short(urlShorten.getUrl_shorten());
//        myKey.setUsername(urlShorten.getUsername());
//        user = userDao.find(urlShorten.getUsername());
//        user.orElse(null).setUrl_Full(urlShorten.getUrl());
//        user.orElse(null).setUrl_shorten(urlShorten.getUrl_shorten());
//        userDao.delete_by_ID(urlShorten.getUsername());
//        userDao.save(user.orElse(null));
//        user = userDao.find(urlShorten.getUsername());
        user = userDao.find(myKey);
        if (user.orElse(null).getUrl_shorten() != null && user.orElse(null).getUrl_Full() != null) {
            System.out.println("*/*/*/"+user.orElse(null).getUrl_shorten());
            //Change started
            StringPass pass= new StringPass();
            pass.setUrl_shorten(user.orElse(null).getUrl_shorten());
            return pass;

//            String urlShorten = user.orElse(null).getUrl_shorten();
//            JSONObject json = new JSONObject();
//            json.put("url_shorten", urlShorten);
//            String jsonString = json.toString();
//            return user.orElse(null).getUrl_shorten();
        }
        else {
            StringPass pass= new StringPass();
            pass.setUrl_shorten("failed0");
            return pass;
        }
    }
    else{
        StringPass pass= new StringPass();
        pass.setUrl_shorten("failed1");
        return pass;    }

}

@GetMapping("/{keyword}")
    public RedirectView redirect(@PathVariable String keyword){
    int c=1;
    User req_user = null;
    Iterable<User> userIterable=userDao.findall();
    for(User x:userIterable){
        if(x.getUrl_shorten()!=null && ( x.getUrl_shorten().equals(keyword))){
            c=0;
            req_user=x;
            break;
        }
    }
    if(c==0){
        RedirectView redirectView=new RedirectView();
        redirectView.setUrl(req_user.getUrl_Full());
        return redirectView;
    }
    else{
        return new RedirectView("/url-not-found");
    }

}
    @GetMapping("/url-not-found")
    public ResponseEntity<String> notFoundPage() {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested page was not found!");
    }
//@GetMapping("/url-not-found")
//    public RedirectView error(){
//    return new RedirectView("https://www.google.com/search?q=Page+Not+Found");
//
//}

}



