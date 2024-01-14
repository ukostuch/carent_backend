package com.ioproject.carent;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/update/user/{username}/{password}")
    public ResponseEntity<UpdateResult> updateUserPassword(@PathVariable String username, @PathVariable String password) {
        return new ResponseEntity<>(userService.updatePassword(username,password),HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add/user")
    public ResponseEntity<Object> addUser(@RequestBody Map<String, Object> payload) {
        try {
            String username = (String) payload.get("username");
            String firstName = (String) payload.get("firstName");
            String surname = (String) payload.get("surname");
            String email = (String) payload.get("email");
            String password = (String) payload.get("password");
            String phoneNumber = (String) payload.get("phoneNumber");
            String birthYear = (String) payload.get("birthYear");
            String birthMonth = (String) payload.get("birthMonth");
            String birthDay = (String) payload.get("birthDay");
            String seriesDriverLicense = (String) payload.get("seriesDriverLicense");
            String street = (String) payload.get("street");
            String city = (String) payload.get("city");
            String country = (String) payload.get("country");
            String postcode = (String) payload.get("postcode");

            User user = userService.add(username, firstName, surname, email, password, Integer.parseInt(phoneNumber), Integer.parseInt(birthYear), Integer.parseInt(birthMonth), Integer.parseInt(birthDay), seriesDriverLicense,
                    street, city, country, Integer.parseInt(postcode));

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add user: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<DeleteResult> deleteUser(@PathVariable int id){
        return new ResponseEntity<>(userService.delete(id),HttpStatus.OK);
    }

    @PostMapping("/login2")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (userService.authenticateUser(username, password)) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
}
