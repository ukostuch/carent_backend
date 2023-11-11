package com.ioproject.carent;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="carReviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarReview {
    @Id
    private ObjectId id;
    private int carCommentId;
    private int userId;
    private int rating;
    private String comment;
    private int carId;
    private String userName;
    private String userCountry;

    public CarReview(int carCommentId, int i, int i1, String comment, int carId, String userName, String userCountry) {
        setCarCommentId(carCommentId);
        setUserId(i);
        setRating(i1);
        setComment(comment);
        setCarId(carId);
        setUserName(userName);
        setUserCountry(userCountry);
    }
}
