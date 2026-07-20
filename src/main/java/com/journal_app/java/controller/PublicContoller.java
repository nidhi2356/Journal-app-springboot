package com.journal_app.java.controller;


import com.journal_app.java.entity.User;
import com.journal_app.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/public")
@RestController
public class PublicContoller {

    @Autowired
    private UserService userService;


    /*
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
     */

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody User user) {

        boolean isSaved = userService.saveNewUser(user);

        if (isSaved) {
            return ResponseEntity.ok("User created successfully");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Failed to create user");
    }

}
