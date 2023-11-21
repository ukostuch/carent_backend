package com.ioproject.carent;


import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralReviewService {
    @Autowired
    private GeneralReviewRepository generalReviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate2;

    public GeneralReview createReview(String comment, String userName,
                                      String userCountry){
        return insertGeneralReview(new GeneralReview(findGeneralCommentId(),5,comment,userName,userCountry));
    }


    public GeneralReview insertGeneralReview(GeneralReview genReview) {
        return mongoTemplate2.insert(genReview,"generalReviews");
    }


    public int findGeneralCommentId() {
        int maxGenCommentId = mongoTemplate2.findAll(GeneralReview.class, "generalReviews")
                .stream()
                .mapToInt(GeneralReview::getGenCommentId)
                .max()
                .orElse(0);

        return maxGenCommentId + 1;
    }

    public List<GeneralReview> getAllReviews(){return generalReviewRepository.findAll();}
    public DeleteResult deleteRecord(int id) {
        return mongoTemplate2.remove(Query.query(Criteria.where("genCommentId").is(id)), GeneralReview.class);
    }
}
