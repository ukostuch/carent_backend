package com.ioproject.carent;


import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RentalService rentalService;

    @Autowired
    private AddressService addressService;

    public boolean exists(String username){
        return userRepository.existsByUsername(username);
    }
    public User add(String username,String firstName, String lastName,String email,String password,int phoneNumber,int birthYear,int birthMonth,int birthDay,String seriesDriverLicense,String street, String city, String country, int postcode){
        User user = new User(findUserId(),username,firstName,lastName,email,phoneNumber,birthYear,birthMonth,birthDay,seriesDriverLicense,password);
        addressService.createAdress(findUserId(),street,city,country,postcode);
        return mongoTemplate.insert(user);
    }


    public DeleteResult delete(int userId){
        mongoTemplate.remove(Query.query(Criteria.where("addressId").is(userId)), Address.class);
        return mongoTemplate.remove(Query.query(Criteria.where("userId").is(userId)), User.class);
    }
    public Address createAdress(int adrid, String street, String city, String country, int postcode){
        return mongoTemplate.insert(new Address(adrid, street,city,country,postcode),"addresses");
    }

    public List<User> getAll() {

        return userRepository.findAll();
    }

    public User findByUsername(String username){
        return userRepository.findByEmail(username);
    }

    public List<Rental> findRentalsforUser(String username) {
        User realuser = findByUsername(username);
        return rentalService.getRentalsForUser(realuser.getUserId());
    }

    public User getUserDetails(String username){
        User realuser = findByUsername(username);
        return userRepository.findByUserId(realuser.getUserId());
    }
    public int findUserId() {
        int maxUserId = mongoTemplate.findAll(User.class, "users")
                .stream()
                .mapToInt(User::getUserId)
                .max()
                .orElse(0);

        return maxUserId + 1;
    }

    public UpdateResult updatePassword(int userId, String password){
        Query query = new Query(Criteria.where("userId").is(userId));
        Update update = new Update().set("password", password);
        updateRoles(userId);
        return mongoTemplate.updateFirst(query, update, User.class);
    }

    public void updateRoles(int userId){
        Query query = new Query(Criteria.where("userId").is(userId));
        Update update = new Update().set("roles",Arrays.asList(new Role("ROLE_USER")));
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
