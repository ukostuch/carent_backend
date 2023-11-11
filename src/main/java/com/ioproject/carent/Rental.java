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

@Document(collection="rentals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
    private ObjectId id;
    private int rentalId;
    private int carId;
    private int userId;
    private String dateFrom;
    private String dateTo;
    private int howManyDays;
    private int locationId;
    private int cost;
    private boolean isPaid;
    private boolean isCurrent;

    public void setIsCurrent(boolean val){
        isCurrent = val;
    }
    public void setIsPaid(boolean val){
        isPaid = val;
    }

    public Rental(int rental_id,int car_id,int user_id, String dateFrom, String dateTo, int cost){
        setRentalId(rental_id);
        setCarId(car_id);
        setUserId(user_id);
        setDateFrom(dateFrom);
        setDateTo(dateTo);
        setCost(cost);
        setIsCurrent(true);
        setIsPaid(true);
        setLocationId(car_id/100);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Period period = Period.between(LocalDate.parse(dateFrom,formatter),LocalDate.parse(dateTo,formatter));
        int inf = period.getDays()+1;
        setHowManyDays(inf);
    }
}
