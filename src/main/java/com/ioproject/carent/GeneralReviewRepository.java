package com.ioproject.carent;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralReviewRepository extends MongoRepository<GeneralReview, ObjectId> {
    void deleteByGenCommentId(int id);
}
