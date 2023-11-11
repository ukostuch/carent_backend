package com.ioproject.carent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

@Document(collection="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private ObjectId id;
    private int userlId;
    private String username;
    private String name;
    private String surname;
    private String email;
    private int phoneNumber;
    private int birthYear;
    private int birthMonth;
    private int birthDay;
    private int addressId;
    private String seriesDriverLicense;
    private String password;



}
