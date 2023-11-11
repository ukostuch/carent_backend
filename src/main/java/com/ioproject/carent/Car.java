package com.ioproject.carent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="car")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    private ObjectId id;
    private int carId;
    private String plateNumber;
    private String carModel;
    private String carBrand;
    private boolean isRented;
    private int rentPriceForDay;
    private int yearOfProduction;
    private String type;
    private String gear;
    private String fuel;
    private int locationId;
    private double technicalCondition;
    private int passengers;
    private String color;
    private String description;
    private boolean airConditioning;
    private String drive;
    private String technicalDescription;
    private String frontPhoto;
    private String sidePhoto;
    private String insidePhoto;


}
