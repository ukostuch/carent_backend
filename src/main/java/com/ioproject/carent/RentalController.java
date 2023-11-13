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

    @GetMapping("/rental/current")
    public ResponseEntity<List<Rental>> currentRentals(){
        return new ResponseEntity<>(rentalService.getCurrentRentals(),HttpStatus.OK);
    }

    @GetMapping("/rental/car/{id}")
    public ResponseEntity<List<Rental>> currentRentals(@PathVariable int id){
        return new ResponseEntity<>(rentalService.getRentalsForCar(id),HttpStatus.OK);
    }

    @DeleteMapping("/rental/delete/{id}")
    public ResponseEntity<DeleteResult> deleteRental(@PathVariable int id){
        return new ResponseEntity<>(rentalService.delete(id),HttpStatus.OK);
    }
}