package com.ioproject.carent;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentalService rentalService;

    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Car> getCarsOfCarBrand(String carBrand) {
        return carRepository.findCarsByCarBrand(carBrand);
    }

    public List<Car> getCarsWithGearAndFuelAndCarBrand(String gear, String fuel, String carBrand) {
        return carRepository.findCarsByFuelAndGearAndCarBrand(fuel, gear, carBrand);
    }
    public List<Car> getCarsWithGearAndTypeAndCarBrand(String gear, String type, String carBrand) {
        return carRepository.findCarsByTypeAndGearAndCarBrand(type, gear, carBrand);
    }
    public List<Car> getCarsFromLocationAndCarBrand(int locationId, String carBrand) {
        return carRepository.findCarsByLocationIdAndCarBrand(locationId, carBrand);
    }
    public List<Car> getCarsWithGearAndCarBrand(String gear, String carBrand) {
        return carRepository.findCarsByGearAndCarBrand(gear, carBrand);
    }
    public List<Car> getCarsOfTypeAndCarBrand(String type, String carBrand) {
        return carRepository.findCarsByTypeAndCarBrand(type, carBrand);
    }
    public List<Car> getCarsOfFuelAndCarBrand(String fuel, String carBrand) {
        return carRepository.findCarsByFuelAndCarBrand(fuel, carBrand);
    }
    public List<Car> getCarsWithGearAndLocationAndCarBrand(String gear, int locationId, String carBrand) {
        return carRepository.findCarsByLocationIdAndGearAndCarBrand(locationId, gear, carBrand);
    }
    public List<Car> getCarsWithFuelAndLocationIdAndCarBrand(String fuel, int locationId, String carBrand) {
        return carRepository.findCarsByLocationIdAndFuelAndCarBrand(locationId, fuel, carBrand);
    }
    public List<Car> getCarsWithTypeAndLocationIdAndCarBrand(String type, int locationId, String carBrand) {
        return carRepository.findCarsByLocationIdAndTypeAndCarBrand(locationId, type, carBrand);
    }
    public List<Car> getCarsWithFuelAndTypeAndCarBrand(String fuel, String type, String carBrand) {
        return carRepository.findCarsByTypeAndFuelAndCarBrand(type, fuel, carBrand);
    }
    public List<Car> getCarsWithFuelAndTypeAndGearAndCarBrand(String fuel, String type, String gear, String carBrand) {
        return carRepository.findCarsByFuelAndTypeAndGearAndCarBrand(fuel, type, gear,carBrand);
    }
    public List<Car> getCarsWithFuelAndTypeAndLocationAndCarBrand(String fuel, String type, int locationId, String carBrand) {
        return carRepository.findCarsByLocationIdAndFuelAndTypeAndCarBrand(locationId, fuel, type, carBrand);
    }
    public List<Car> getCarsWithFuelAndGearAndLocationAndCarBrand(String fuel, String gear, int locationId, String carBrand) {
        return carRepository.findCarsByLocationIdAndFuelAndGearAndCarBrand(locationId, fuel, gear, carBrand);
    }
    public List<Car> getCarsWithGearAndTypeAndLocationAndCarBrand(String gear, String type, int locationId, String carBrand) {
        return carRepository.findCarsByLocationIdAndTypeAndGearAndCarBrand(locationId, type, gear,carBrand);
    }
    public List<Car> getCarsWithFuelAndTypeAndLocationAndGearAndCarBrand(String fuel, String type, int locationId, String gear, String carBrand) {
        return carRepository.findCarsByLocationIdAndFuelAndTypeAndGearAndCarBrand(locationId, fuel, type, gear,carBrand);
    }



    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> singleCar(int carId) {
        return carRepository.findCarByCarId(carId);
    }

    public List<Car> getCarsFromLocation(int locationId) {
        return carRepository.findCarsByLocationId(locationId);
    }

    public List<Car> getCarsWithGear(String gear) {
        return carRepository.findCarsByGear(gear);
    }

    public List<Car> getCarsOfType(String type) {
        return carRepository.findCarsByType(type);
    }

    public List<Car> getCarsOfFuel(String fuel) {
        return carRepository.findCarsByFuel(fuel);
    }

    public List<Car> getCarsWithGearAndFuel(String gear, String fuel) {
        return carRepository.findCarsByFuelAndGear(fuel, gear);
    }

    public List<Car> getCarsWithGearAndType(String gear, String type) {
        return carRepository.findCarsByTypeAndGear(type, gear);
    }

    public List<Car> getCarsWithGearAndLocation(String gear, int locationId) {
        return carRepository.findCarsByLocationIdAndGear(locationId, gear);
    }

    public List<Car> getCarsWithFuelAndLocationId(String fuel, int locationId) {
        return carRepository.findCarsByLocationIdAndFuel(locationId, fuel);
    }

    public List<Car> getCarsWithTypeAndLocationId(String type, int locationId) {
        return carRepository.findCarsByLocationIdAndType(locationId, type);
    }

    public List<Car> getCarsWithFuelAndType(String fuel, String type) {
        return carRepository.findCarsByTypeAndFuel(type, fuel);
    }

    public List<Car> getCarsWithFuelAndTypeAndGear(String fuel, String type, String gear) {
        return carRepository.findCarsByFuelAndTypeAndGear(fuel, type, gear);
    }

    public List<Car> getCarsWithFuelAndTypeAndLocation(String fuel, String type, int locationId) {
        return carRepository.findCarsByLocationIdAndFuelAndType(locationId, fuel, type);
    }

    public List<Car> getCarsWithFuelAndGearAndLocation(String fuel, String gear, int locationId) {
        return carRepository.findCarsByLocationIdAndFuelAndGear(locationId, fuel, gear);
    }

    public List<Car> getCarsWithGearAndTypeAndLocation(String gear, String type, int locationId) {
        return carRepository.findCarsByLocationIdAndTypeAndGear(locationId, type, gear);
    }

    public List<Car> getCarsWithFuelAndTypeAndLocationAndGear(String fuel, String type, int locationId, String gear) {
        return carRepository.findCarsByLocationIdAndFuelAndTypeAndGear(locationId, fuel, type, gear);
    }

    public List<Car> findAvailableCarsBetweenDates(String dateFrom, String dateTo) {
        List<Rental> overlappingRentals = rentalService.getAllRentalsBetweenDates(dateFrom, dateTo);

        List<Integer> overlappingCarIds = overlappingRentals.stream()
                .map(Rental::getCarId)
                .toList();

        List<Car> allCars = getAllCars();

        return allCars.stream()
                .filter(car -> !overlappingCarIds.contains(car.getCarId()))
                .collect(Collectors.toList());
    }

    public List<Car> findAvailableCarsBetweenDatesAndCarBrand(String dateFrom,String dateTo,String carBrand){
        List<Car> temp = getCarsOfCarBrand(carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithAllParameters(String fuel, String type, int locationId, String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsWithFuelAndTypeAndLocationAndCarBrand(fuel,type,locationId,carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithGTLandDates(String gear, String type, int locationId,String dateFrom,String dateTo){
        List<Car> temp = getCarsWithGearAndTypeAndLocation(gear,type,locationId);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithGTLandDatesAndCarBrand(String gear, String type, int locationId,String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsWithGearAndTypeAndLocationAndCarBrand(gear,type,locationId,carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithFGLandDates(String fuel, String gear, int locationId,String dateFrom,String dateTo){
        List<Car> temp = getCarsWithFuelAndGearAndLocation(fuel,gear,locationId);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithFGLandDatesAndCarBrand(String fuel, String gear, int locationId,String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsWithFuelAndGearAndLocationAndCarBrand(fuel,gear,locationId,carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithFTLandDatesAndCarBrand(String fuel, String type, int locationId,String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsWithFuelAndTypeAndLocationAndCarBrand(fuel,type,locationId,carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithFTLandDates(String fuel, String type, int locationId,String dateFrom,String dateTo){
        List<Car> temp = getCarsWithFuelAndTypeAndLocation(fuel,type,locationId);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithFTGandDates(String fuel, String type, String gear,String dateFrom,String dateTo){
        List<Car> temp = getCarsWithFuelAndTypeAndGear(fuel, type, gear);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithFTGandDatesAndBrandCar(String fuel, String type, String gear,String dateFrom,String dateTo,String carBrand){
        List<Car> temp = getCarsWithFuelAndTypeAndGearAndCarBrand(fuel, type, gear, carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithFTandDates(String fuel, String type,String dateFrom,String dateTo){
        List<Car> temp = getCarsWithFuelAndType(fuel, type);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithFTandDatesAndCarBrand(String fuel, String type,String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsWithFuelAndTypeAndCarBrand(fuel, type, carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithTLandDates(String type, int locationId,String dateFrom,String dateTo){
        List<Car> temp = getCarsWithTypeAndLocationId(type,locationId);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithTLandDatesAndCarBrand(String type, int locationId,String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsWithTypeAndLocationIdAndCarBrand(type,locationId, carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithFLandDates(String fuel, int locationId,String dateFrom,String dateTo){
        List<Car> temp = getCarsWithFuelAndLocationId(fuel, locationId);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithFLandDatesAndCarBrand(String fuel, int locationId,String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsWithFuelAndLocationIdAndCarBrand(fuel, locationId, carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithGLandDates(String gear, int locationId,String dateFrom,String dateTo){
        List<Car> temp = getCarsWithGearAndLocation(gear,locationId);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithGLandDatesAndCarBrand(String gear, int locationId,String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsWithGearAndLocationAndCarBrand(gear,locationId, carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithGTandDates(String gear, String type, String dateFrom,String dateTo){
        List<Car> temp = getCarsWithGearAndType(gear,type);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithGTandDatesAndCarBrand(String gear, String type, String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsWithGearAndTypeAndCarBrand(gear,type, carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithGFandDates(String gear, String fuel, String dateFrom,String dateTo){
        List<Car> temp = getCarsWithGearAndFuel(gear,fuel);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithGFandDatesAndCarBrand(String gear, String fuel, String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsWithGearAndFuelAndCarBrand(gear,fuel, carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithFuelandDates(String fuel, String dateFrom,String dateTo){
        List<Car> temp = getCarsOfFuel(fuel);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithFuelandDatesAndCArBrand(String fuel, String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsOfFuelAndCarBrand(fuel, carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithGearandDates(String gear, String dateFrom,String dateTo){
        List<Car> temp = getCarsWithGear(gear);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithGearandDatesAndCarBrand(String gear, String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsWithGearAndCarBrand(gear, carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithLocationandDates(int locationId,String dateFrom,String dateTo){
        List<Car> temp = getCarsFromLocation(locationId);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithLocationandDatesAndCarBrand(int locationId,String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsFromLocationAndCarBrand(locationId, carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public List<Car> getCarsWithTypeandDates(String type,String dateFrom,String dateTo){
        List<Car> temp = getCarsOfType(type);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }
    public List<Car> getCarsWithTypeandDatesAndCarBrand(String type,String dateFrom,String dateTo, String carBrand){
        List<Car> temp = getCarsOfTypeAndCarBrand(type, carBrand);
        List<Car> temp2 = findAvailableCarsBetweenDates(dateFrom,dateTo);
        List<Car> commonCars = new ArrayList<>(temp);
        commonCars.retainAll(temp2);
        return commonCars;
    }

    public UpdateResult updateTechDesc(int id, String newFieldValue) {
        Query query = new Query(Criteria.where("carId").is(id));
        Update update = new Update().set("technicalDescription", newFieldValue);
        return mongoTemplate.updateFirst(query, update, Car.class);
    }

    public UpdateResult updateTechCond(int id, double newFieldValue) {
        Query query = new Query(Criteria.where("carId").is(id));
        Update update = new Update().set("technicalCondition", newFieldValue);
        return mongoTemplate.updateFirst(query, update, Car.class);
    }
}



