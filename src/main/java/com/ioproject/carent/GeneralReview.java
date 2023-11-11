package com.ioproject.carent;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="generalReviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralReview {
    @Id
    private ObjectId id;
    private int genCommentId;
    private int userId;
    private int rating;
    private String comment;
    private String userName;
    private String userCountry;

    public GeneralReview(int generalCommentId, int user, int i, String comment, String userName, String userCountry) {
        setGenCommentId(generalCommentId);
        setUserId(user);
        setRating(i);
        setComment(comment);
        setUserName(userName);
        setUserCountry(userCountry);
    }
}
