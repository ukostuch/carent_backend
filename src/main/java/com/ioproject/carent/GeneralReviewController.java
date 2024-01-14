/*package com.ioproject.carent;

import com.ioproject.carent.CarService;
import com.mongodb.client.result.DeleteResult;
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

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GeneralReviewController {
    @Autowired
    private GeneralReviewService genReviewService;

    @PostMapping("/reviews/write")
    public ResponseEntity<GeneralReview> createReview(@RequestBody Map<String,Object> payload){
        return new ResponseEntity<GeneralReview>(genReviewService.createReview((String) payload.get("comment"), (String) payload.get("userName"), (String) payload.get("userCountry")), HttpStatus.CREATED);
    }


    @GetMapping("/reviews")
    public ResponseEntity<List<GeneralReview>> getGenReviews(){
        return new ResponseEntity<List<GeneralReview>>(genReviewService.getAllReviews(),HttpStatus.OK);
    }

    @DeleteMapping("/reviews/delete/{id}")
    public ResponseEntity<DeleteResult> delete(@PathVariable int id){

        return new ResponseEntity<>(genReviewService.deleteRecord(id),HttpStatus.OK);
    }
}*/

package com.ioproject.carent;

import com.ioproject.carent.CarService;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class GeneralReviewController {
    @Autowired
    private GeneralReviewService genReviewService;

    @PostMapping("/reviews/write")
    public ResponseEntity<?> createReview(@RequestBody Map<String, Object> payload) {
        try {
            if (payload.get("comment") == null || payload.get("userName") == null || payload.get("userCountry") == null) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Wymagane pola nie są ustawione.");
            }
            GeneralReview review = genReviewService.createReview(
                    (String) payload.get("comment"),
                    (String) payload.get("userName"),
                    (String) payload.get("userCountry"));

            return new ResponseEntity<>(review, HttpStatus.CREATED);

        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>(e.getStatusCode());
        } catch (Exception e) {
            // Przechwyć inne wyjątki i zwróć odpowiedź z kodem 500 (Internal Server Error).
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<GeneralReview>> getGenReviews() {
        return new ResponseEntity<>(genReviewService.getAllReviews(), HttpStatus.OK);
    }

    @DeleteMapping("/reviews/delete/{id}")
    public ResponseEntity<DeleteResult> delete(@PathVariable int id) {
        return new ResponseEntity<>(genReviewService.deleteRecord(id), HttpStatus.OK);
    }
}