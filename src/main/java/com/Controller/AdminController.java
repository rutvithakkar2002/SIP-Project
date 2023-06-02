package com.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.AdminloginBean;

@RestController
@RequestMapping("/login")
public class AdminController {

	@PostMapping
	public ResponseEntity<?> login(@RequestBody AdminloginBean ab) {
		String Username = "Admin";
		String password = "admin123";

		if (ab.getUsername().equals(Username) && ab.getPassword().equals(password)) {
			System.out.println("Login Sucessfully");
			return ResponseEntity.status(HttpStatus.OK).body(ab);

		}
		System.out.println("fail to login!!");
		return ResponseEntity.badRequest().body(ab);

	}

}

/*
 * 1) input | output -> json 2) POST new /user 3) GET read /user 4) PUT update
 * /user 5) DELETE destroy /user/:userId 6) GET read 1 /user/:userId
 */
