package com.ioproject.carent;

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
public class GeneralReviewController {
    @Autowired
    private GeneralReviewService genReviewService;

    @PostMapping("/reviews/write")
    public ResponseEntity<GeneralReview> createReview(@RequestBody Map<String,Object> payload){
        return new ResponseEntity<GeneralReview>(genReviewService.createReview((String) payload.get("comment"), (String) payload.get("userName"), (String) payload.get("userCountry"), (Integer) payload.get("userId")), HttpStatus.CREATED);
    }


    @GetMapping("/reviews")
    public ResponseEntity<List<GeneralReview>> getGenReviews(){
        return new ResponseEntity<List<GeneralReview>>(genReviewService.getAllReviews(),HttpStatus.OK);
    }

    @PostMapping("/reviews/delete/{id}")
    public void delete(@PathVariable int id){
        genReviewService.deleteRecord(id);
    }
}
