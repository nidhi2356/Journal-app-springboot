package com.journal_app.java.controller;

import com.journal_app.java.api.response.WeatherResponse;
import com.journal_app.java.entity.User;
import com.journal_app.java.service.UserService;
import com.journal_app.java.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
@Tag(
        name = "User APIs",
        description = "APIs for managing the authenticated user's profile, account, and personalized greeting."
)
@SecurityRequirement(name = "Bearer Authentication")
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

    @Operation(
            summary = "Update User Profile",
            description = "Updates the profile information of the authenticated user."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User profile updated successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid user details provided."),
            @ApiResponse(responseCode = "401", description = "User is not authenticated."),
            @ApiResponse(responseCode = "404", description = "User not found.")
    })
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


    @Operation(
            summary = "Delete User Account",
            description = "Deletes the authenticated user's account along with all associated journal entries."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User account deleted successfully."),
            @ApiResponse(responseCode = "401", description = "User is not authenticated."),
            @ApiResponse(responseCode = "404", description = "User not found.")
    })
    @DeleteMapping
    public ResponseEntity<?> deleteUser(Authentication authentication){
        String userName = authentication.getName();
        userService.deleteByUserName(userName);
        return ResponseEntity.noContent().build(); 
    }



    @Operation(
            summary = "Get Personalized Greeting",
            description = "Returns a personalized greeting for the authenticated user along with the current weather information for Mumbai."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Greeting retrieved successfully."),
            @ApiResponse(responseCode = "401", description = "User is not authenticated."),
            @ApiResponse(responseCode = "500", description = "Unable to retrieve weather information from the external weather service.")
    })
    @GetMapping("/greeting")
    public ResponseEntity<?> greeting(Authentication authentication){
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting = "";
        if(weatherResponse != null){
            greeting = ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }
        return ResponseEntity.ok("Hi "+ authentication.getName() + greeting);

    }
}
