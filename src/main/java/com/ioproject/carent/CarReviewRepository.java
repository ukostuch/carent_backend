package com.ioproject.carent;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarReviewRepository extends MongoRepository<CarReview, ObjectId> {
    CarReview findCarReviewByCarCommentId(int id);
    List<CarReview> findCarReviewsByCarId(int carId);
}
