package com.ioproject.carent;
import com.mongodb.client.result.DeleteResult;
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

    @PostMapping("/users/new")
    public ResponseEntity<User> addNewUser(@RequestBody Map<String,Object> payload){
        return new ResponseEntity<User>(userService.createUser(userService.findUserId(),(String) payload.get("username"),(String) payload.get("name"), (String) payload.get("surname"), (String) payload.get("email"),(Integer) payload.get("phoneNumber"),(Integer) payload.get("birthYear"),(Integer)payload.get("birthMonth"),(Integer)payload.get("birthDay"),(String)payload.get("driversLicense"),(String)payload.get("password"),(String)payload.get("street"),(String) payload.get("country"),(String) payload.get("city"),(Integer)payload.get("postcode")), HttpStatus.CREATED);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<DeleteResult> deleteUser(@PathVariable int id){
        return new ResponseEntity<>(userService.delete(id),HttpStatus.OK);
    }
}
