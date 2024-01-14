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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RentalController {
    @Autowired
    private RentalService rentalService;

   @PostMapping("/rental")
   public ResponseEntity<Rental> createRental(@RequestBody Map<String, Object> payload) {
       String username = (String) payload.get("username");
       String carId = (String) payload.get("carId");
       String dateFrom = (String) payload.get("dateFrom");
       String dateTo = (String) payload.get("dateTo");
       int cost = (int) payload.get("cost");

       Rental rental = rentalService.createRental(rentalService.findRentalId(), username, Integer.parseInt(carId), dateFrom, dateTo, cost);
       return new ResponseEntity<>(rental, HttpStatus.CREATED);
   }

    @GetMapping("/{username}/rentals")
    public ResponseEntity<List<Rental>> rentalsOfUser(@PathVariable String username){
        return new ResponseEntity<>(rentalService.getRentalsForUser(username),HttpStatus.OK);
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