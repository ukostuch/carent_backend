package com.ioproject.carent;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends MongoRepository<Car, ObjectId> {
    Optional<Car> findCarByCarId(int carId);
    List<Car> findCarsByLocationIdAndFuelAndTypeAndGear(int locationId,String fuel,String type,String gearType);
    List<Car> findCarsByLocationIdAndTypeAndGear(int locationId, String type, String gearType);
    List<Car> findCarsByLocationIdAndFuelAndGear(int locationId,String fuel,String gearType);
    List<Car> findCarsByLocationIdAndFuelAndType(int locationId,String fuel,String type);
    List<Car> findCarsByFuelAndTypeAndGear(String fuel,String type,String gearType);
    List<Car> findCarsByLocationIdAndFuel(int locationId, String fuel);
    List<Car> findCarsByLocationIdAndType(int locationId, String type);
    List<Car> findCarsByLocationIdAndGear(int locationId, String gearType);
    List<Car> findCarsByTypeAndFuel(String type,String fuel);
    List<Car> findCarsByTypeAndGear(String type,String gearType);
    List<Car> findCarsByFuelAndGear(String fuel,String gear);
    List<Car> findCarsByLocationId(int locationId);
    List<Car> findCarsByFuel(String fuel);
    List<Car> findCarsByGear(String gear);
    List<Car> findCarsByType(String type);
    List<Car> findCarsByCarBrand(String carBrand);  //dodane
    List<Car> findCarsByFuelAndGearAndCarBrand(String fuel,String gear, String carBrand);

    List<Car> findCarsByLocationIdAndFuelAndTypeAndGearAndCarBrand(int locationId,String fuel,String type,String gearType, String carBrand);
    List<Car> findCarsByLocationIdAndTypeAndGearAndCarBrand(int locationId, String type, String gearType, String carBrand);
    List<Car> findCarsByLocationIdAndFuelAndGearAndCarBrand(int locationId,String fuel,String gearType, String carBrand);

    List<Car> findCarsByLocationIdAndFuelAndTypeAndCarBrand(int locationId,String fuel,String type, String carBrand);
    List<Car> findCarsByFuelAndTypeAndGearAndCarBrand(String fuel,String type,String gearType, String carBrand);

    List<Car> findCarsByLocationIdAndFuelAndCarBrand(int locationId, String fuel, String carBrand);
    List<Car> findCarsByLocationIdAndTypeAndCarBrand(int locationId, String type, String carBrand);
    List<Car> findCarsByLocationIdAndGearAndCarBrand(int locationId, String gearType, String carBrand);
    List<Car> findCarsByTypeAndFuelAndCarBrand(String type,String fuel, String carBrand);
    List<Car> findCarsByTypeAndGearAndCarBrand(String type,String gearType, String carBrand);
    List<Car> findCarsByLocationIdAndCarBrand(int locationId, String carBrand);
    List<Car> findCarsByFuelAndCarBrand(String fuel, String carBrand);
    List<Car> findCarsByGearAndCarBrand(String gear, String carBrand);
    List<Car> findCarsByTypeAndCarBrand(String type, String carBrand);


}
