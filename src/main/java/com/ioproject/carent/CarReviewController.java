package com.ioproject.carent;//package com.ioproject.carent.carreview;

import com.ioproject.carent.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CarReviewController {
    @Autowired
    private CarReviewService carReviewService;

    @PostMapping("/carreview/write")
    public ResponseEntity<CarReview> createReview(@RequestBody Map<String,Object> payload){
        return new ResponseEntity<CarReview>(carReviewService.createReview((String) payload.get("comment"), (Integer) payload.get("carId"),(String)payload.get("userName"),(String)payload.get("userCountry"),(Integer) payload.get("userId")), HttpStatus.CREATED);
    }

    @GetMapping("/carreview/{revId}")
    public ResponseEntity<CarReview> getSingleCarReview(@PathVariable int revId){
        return new ResponseEntity<CarReview>(carReviewService.singleCar(revId),HttpStatus.OK);
    }

    @GetMapping("/carreview/car/{carId}")
    public ResponseEntity<List<CarReview>> getCarReviewsofCar(@PathVariable int carId){
        return new ResponseEntity<List<CarReview>>(carReviewService.getCarReviewsofCar(carId),HttpStatus.OK);
    }

    @PostMapping("/carreviews/delete/{id}")
    public void delete(@PathVariable int id){
        carReviewService.deleteRecord(id);
    }

}