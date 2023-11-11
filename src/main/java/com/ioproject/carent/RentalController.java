package com.ioproject.carent;
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
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @PostMapping("/rental")
    public ResponseEntity<Rental> createRental(@RequestBody Map<String,Object> payload){
        return new ResponseEntity<Rental>(rentalService.createRental(rentalService.findRentalId(),(Integer) payload.get("userId"),(Integer) payload.get("carId"), (String) payload.get("dateFrom"), (String) payload.get("dateTo"),(Integer) payload.get("cost")), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/rentals")
    public ResponseEntity<List<Rental>> rentalsOfUser(@PathVariable int userId){
        return new ResponseEntity<>(rentalService.getRentalsForUser(userId),HttpStatus.OK);
    }
}