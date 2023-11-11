package com.ioproject.carent;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarReviewService {
    @Autowired
    private CarReviewRepository carReviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public CarReview createReview(String comment,
    int carId, String userName,
    String userCountry, int user){
        return insertCarReview(new CarReview(findCarCommentId(),user,5,comment,carId,userName,userCountry));
    }


    public CarReview insertCarReview(CarReview carReview) {
        return mongoTemplate.insert(carReview,"carReviews");
    }


    public int findCarCommentId() {
        int maxCarCommentId = mongoTemplate.findAll(CarReview.class, "carReviews")
                .stream()
                .mapToInt(CarReview::getCarCommentId)
                .max()
                .orElse(0);

        return maxCarCommentId + 1;
    }

    public CarReview singleCar(int carId){
        return carReviewRepository.findCarReviewByCarCommentId(carId);
    }
    public List<CarReview> getCarReviewsofCar(int carId){return carReviewRepository.findCarReviewsByCarId(carId);}
    public void deleteRecord(int id) {
        mongoTemplate.remove(Query.query(Criteria.where("carCommentId").is(id)), CarReview.class);
    }
}