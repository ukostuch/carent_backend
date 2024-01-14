package com.ioproject.carent;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private UserRepository userRepository;

    public Rental createRental(int rental_id, String username, int car_id, String date_from, String date, int cost){
        User user = userRepository.findByUsername(username);
        int user_id = user.getUserId();
        return insert(new Rental(rental_id,car_id,user_id,date_from,date,cost));
    }


    public Rental insert(Rental rental){ return mongoTemplate.insert(rental,"rentals");}

    public DeleteResult delete(int id){
        return mongoTemplate.remove(Query.query(Criteria.where("rentalId").is(id)), Rental.class);
    }

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
    public List<Rental> getCurrentRentals(){
        return rentalRepository.findByIsCurrent(true);
    }
    public List<Rental> getRentalsForUser(String username){
        User user = userRepository.findByUsername(username);
        int user_id = user.getUserId();
        return rentalRepository.findRentalsByUserId(user_id);
    }
    public List<Rental> getRentalsForCar(int carId){ return rentalRepository.findByCarId(carId);}
}



