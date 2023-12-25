/*package com.ioproject.carent;//package com.ioproject.carent.carreview;

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
public class CarReviewController {
    @Autowired
    private CarReviewService carReviewService;

    @PostMapping("/carreview/write")
    public ResponseEntity<CarReview> createReview(@RequestBody Map<String,Object> payload){
        return new ResponseEntity<CarReview>(carReviewService.createReview((String) payload.get("comment"), (Integer) payload.get("carId"),(String)payload.get("userName"),(String)payload.get("userCountry")), HttpStatus.CREATED);
    }

    @GetMapping("/carreview/{revId}")
    public ResponseEntity<CarReview> getSingleCarReview(@PathVariable int revId){
        return new ResponseEntity<CarReview>(carReviewService.singleCar(revId),HttpStatus.OK);
    }

    @GetMapping("/carreview/car/{carId}")
    public ResponseEntity<List<CarReview>> getCarReviewsofCar(@PathVariable int carId){
        return new ResponseEntity<List<CarReview>>(carReviewService.getCarReviewsofCar(carId),HttpStatus.OK);
    }

    @DeleteMapping("/carreviews/delete/{id}")
    public ResponseEntity<DeleteResult> delete(@PathVariable int id){
        return new ResponseEntity<>(carReviewService.deleteRecord(id),HttpStatus.OK);
    }
}*/


package com.ioproject.carent;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CarReviewController {
    @Autowired
    private CarReviewService carReviewService;

    /*@PostMapping("/carreview/write")
    public ResponseEntity<?> createReview(@RequestBody Map<String, Object> payload) {
        try {
            if (payload.get("comment") == null || payload.get("carId") == null || payload.get("userName") == null || payload.get("userCountry") == null) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Wymagane pola nie są ustawione.");
            }

            CarReview review = carReviewService.createReview(
                    (String) payload.get("comment"),
                    (Integer) payload.get("carId"),
                    (String) payload.get("userName"),
                    (String) payload.get("userCountry"));


            return new ResponseEntity<>(review, HttpStatus.CREATED);

        } catch (HttpClientErrorException e) {
            // Przechwyć błąd HTTP 400 i zwróć odpowiedź z odpowiednim statusem.
            return new ResponseEntity<>(e.getStatusCode());
        } catch (Exception e) {
            // Przechwyć inne wyjątki i zwróć odpowiedź z kodem 500 (Internal Server Error).
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    @PostMapping("/carreview/write")
    public ResponseEntity<?> createReview(@RequestBody Map<String, Object> payload) {
        try {
            if (payload.get("comment") == null || payload.get("carId") == null || payload.get("userName") == null || payload.get("userCountry") == null) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Wymagane pola nie są ustawione.");
            }


            String comment = (String) payload.get("comment");
            String carId = (String) payload.get("carId");
            String userName = (String) payload.get("userName");
            String userCountry = (String) payload.get("userCountry");

            CarReview review = carReviewService.createReview(
                    comment, Integer.parseInt(carId),userName, userCountry);

            return new ResponseEntity<>(review, HttpStatus.CREATED);

        } catch (HttpClientErrorException e) {
            // Przechwyć błąd HTTP 400 i zwróć odpowiedź z odpowiednim statusem.
            return new ResponseEntity<>(e.getStatusCode());
        } catch (Exception e) {
            // Przechwyć inne wyjątki i zwróć odpowiedź z kodem 500 (Internal Server Error).
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/carreview/{revId}")
    public ResponseEntity<CarReview> getSingleCarReview(@PathVariable int revId) {
        return new ResponseEntity<>(carReviewService.singleCar(revId), HttpStatus.OK);
    }

    @GetMapping("/carreview/car/{carId}")
    public ResponseEntity<List<CarReview>> getCarReviewsofCar(@PathVariable int carId) {
        return new ResponseEntity<>(carReviewService.getCarReviewsofCar(carId), HttpStatus.OK);
    }

    @DeleteMapping("/carreviews/delete/{id}")
    public ResponseEntity<DeleteResult> delete(@PathVariable int id) {
        return new ResponseEntity<>(carReviewService.deleteRecord(id), HttpStatus.OK);
    }
}
