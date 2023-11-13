package com.ioproject.carent;


import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean exists(String username){
        return userRepository.existsByUsername(username);
    }
    public User createUser(int user_id, String username, String name, String surname, String email, int phoneNumber, int birthYear, int birthMonth, int birthDay, String seriesDriverLicense, String password,String street, String country,String city, int postcode){
        mongoTemplate.insert(new Address(user_id, street,city,country,postcode));
        return mongoTemplate.insert(new User(user_id,username,name,surname,email,phoneNumber,birthYear,birthMonth,birthDay,seriesDriverLicense,password),"users");
    }

    public DeleteResult delete(int userId){
        mongoTemplate.remove(Query.query(Criteria.where("addressId").is(userId)), Address.class);
        return mongoTemplate.remove(Query.query(Criteria.where("userId").is(userId)), User.class);
    }
    public Address createAdress(int adrid, String street, String city, String country, int postcode){
        return mongoTemplate.insert(new Address(adrid, street,city,country,postcode),"addresses");
    }

    public int findUserId() {
        int maxUserId = mongoTemplate.findAll(User.class, "users")
                .stream()
                .mapToInt(User::getUserId)
                .max()
                .orElse(0);

        return maxUserId + 1;
    }

}
