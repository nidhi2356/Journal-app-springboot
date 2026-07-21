package com.journal_app.java.controller;

import com.journal_app.java.api.response.WeatherResponse;
import com.journal_app.java.entity.User;
import com.journal_app.java.service.UserService;
import com.journal_app.java.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private WeatherService weatherService;

    /*
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);
        if(userInDB != null){
            userInDB.setUserName(user.getUserName());
            userInDB.setPassword(user.getPassword());
            userService.saveEntry(userInDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
     */

    @PutMapping
    public ResponseEntity<?> updateUser(
            @RequestBody User user,
            Authentication authentication) {

        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUser(Authentication authentication){
        String userName = authentication.getName();
        userService.deleteByUserName(userName);
        return ResponseEntity.noContent().build(); 
    }

    @GetMapping
    public ResponseEntity<?> greeting(Authentication authentication){
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if(weatherResponse != null){
            greeting = ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return ResponseEntity.ok("Hi "+ authentication.getName() + greeting);

    }
}
