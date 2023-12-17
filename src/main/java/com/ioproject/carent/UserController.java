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

    @GetMapping("/update/user/{id}/{password}")
    public ResponseEntity<UpdateResult> updateUserPassword(@PathVariable int id, @PathVariable String password) {
        return new ResponseEntity<>(userService.updatePassword(id,password),HttpStatus.OK);
    }

   /*@CrossOrigin(origins = "http://localhost:3000")
   @PostMapping("/add/user")
    public ResponseEntity<User> addUser(@RequestBody Map<String,Object> payload){
        return new ResponseEntity<>(userService.add((String) payload.get("username"),(String) payload.get("firstName"), (String) payload.get("surname"),(String) payload.get("email"),(String) payload.get("password"),(int) payload.get("phoneNumber"),(int) payload.get("birthYear"),(int) payload.get("birthMonth"),(int) payload.get("birthDay"),(String) payload.get("seriesDriverLicense"),
                (String) payload.get("street"), (String) payload.get("city"), (String) payload.get("country"), (int) payload.get("postcode")), HttpStatus.CREATED);
    }*/
    /*@PostMapping("/add/user")
    public ResponseEntity<User> addUser() {
        String user_id="23";
        String username = "magda2";
        String name = "Magda";
        String surname = "Kowalska";
        String email = "magda@gmail.com";
        String password = "mypassword";
        int phoneNumber = 14567890;
        int birthYear = 1989;
        int birthMonth = 11;
        int birthDay = 23;
        String seriesDriverLicense="145ASC";
        String street = "Kwaitowa";
        String city = "Krakow";
        String country = "Poland";
        int postcode = 1357;

        User newUser = userService.add(username, name, surname, email, password, phoneNumber, birthYear, birthMonth, birthDay, seriesDriverLicense, street, city, country, postcode);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }*/

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add/user")
    public ResponseEntity<Object> addUser(@RequestBody Map<String, Object> payload) {
        try {
            String username = (String) payload.get("username");
            String firstName = (String) payload.get("firstName");
            String surname = (String) payload.get("surname");
            String email = (String) payload.get("email");
            String password = (String) payload.get("password");
            //int phoneNumber = (int) payload.get("phoneNumber");
            String phoneNumber = (String) payload.get("phoneNumber");
            //int birthYear = (int) payload.get("birthYear");
            String birthYear = (String) payload.get("birthYear");
            //int birthMonth = (int) payload.get("birthMonth");
            String birthMonth = (String) payload.get("birthMonth");
            //int birthDay = (int) payload.get("birthDay");
            String birthDay = (String) payload.get("birthDay");
            String seriesDriverLicense = (String) payload.get("seriesDriverLicense");
            String street = (String) payload.get("street");
            String city = (String) payload.get("city");
            String country = (String) payload.get("country");
            //int postcode = (int) payload.get("postcode");
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
        String email = credentials.get("email");
        String password = credentials.get("password");

        if (userService.authenticateUser(email, password)) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }
}
