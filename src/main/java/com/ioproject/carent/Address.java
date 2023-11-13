package com.ioproject.carent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    private ObjectId id;
    private int addressId;
    private String street;
    private String city;
    private int postcode;
    private String country;

    Address(int addressId, String street,String city,String country, int postcode){
        setCity(city);
        setCountry(country);
        setAddressId(addressId);
        setStreet(street);
        setPostcode(postcode);
    }
}