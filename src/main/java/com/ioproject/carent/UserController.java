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

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/update/user/{id}/{password}")
    public ResponseEntity<UpdateResult> updateUserPassword(@PathVariable int id, @PathVariable String password) {
        return new ResponseEntity<>(userService.updatePassword(id,password),HttpStatus.OK);
    }

    @PostMapping("/add/user")
    public ResponseEntity<User> addUser(@RequestBody Map<String,Object> payload){
        return new ResponseEntity<>(userService.add((String) payload.get("username"),(String) payload.get("firstName"), (String) payload.get("surname"),(String) payload.get("email"),(String) payload.get("password"),(int) payload.get("phoneNumber"),(int) payload.get("birthYear"),(int) payload.get("birthMonth"),(int) payload.get("birthDay"),(String) payload.get("seriesDriverLicense"),
                (String) payload.get("street"), (String) payload.get("city"), (String) payload.get("country"), (int) payload.get("postcode")), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<DeleteResult> deleteUser(@PathVariable int id){
        return new ResponseEntity<>(userService.delete(id),HttpStatus.OK);
    }
}
