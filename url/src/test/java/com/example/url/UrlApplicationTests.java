package com.example.url;

import com.example.url.User.User;
import com.example.url.User.UserDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UrlApplicationTests {

	@Autowired
	private UserDAO userDAO;

	@Test
	void insert() {
		User user= new User();
		user.setFirstname("jkljljk");
		user.setUsername("jki");
		user.setPassword("afgfg");
		user.setLastname("gffdgfg");
		userDAO.save(user);
	}

	@Test
	void delete(){
		userDAO.delete_by_ID("jki");
	}

}
