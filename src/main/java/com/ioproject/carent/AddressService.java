package com.ioproject.carent;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Address createAdress(int adrid, String street, String city, String country, int postcode){
        return mongoTemplate.insert(new Address(adrid, street,city,country,postcode),"addresses");
    }
}