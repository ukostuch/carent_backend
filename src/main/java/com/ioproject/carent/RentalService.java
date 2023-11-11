package com.ioproject.carent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Rental createRental(int rental_id, int user_id, int car_id, String date_from, String date, int cost){
        return insert(new Rental(rental_id,car_id,user_id,date_from,date,cost));
    }

    public Rental insert(Rental rental){ return mongoTemplate.insert(rental,"rentals");}

    public int findRentalId() {
        int maxRentalId = mongoTemplate.findAll(Rental.class, "rentals")
                .stream()
                .mapToInt(Rental::getRentalId)
                .max()
                .orElse(0);

        return maxRentalId + 1;
    }

    public List<Rental> getAllRentalsBetweenDates(String dateFrom, String dateTo) {
        return rentalRepository.findByDateFromLessThanEqualAndDateToGreaterThanEqual(dateTo, dateFrom);
    }
    public List<Rental> getRentalsForUser(int userId){
        return rentalRepository.findRentalsByUserId(userId);
    }
}



