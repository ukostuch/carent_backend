package com.ioproject.carent;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends MongoRepository<Rental, ObjectId> {
    List<Rental> findRentalsByUserId(int userId);
    List<Rental> findByDateFromLessThanEqualAndDateToGreaterThanEqual(String dateTo,String dateFrom);
    List<Rental> findByIsCurrent(boolean cond);
    List<Rental> findByCarId(int carId);
}
