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

    public List<Car> getCarsWithAllParameters(String fuel, String type, int locationId, String gear, String dateFrom,String dateTo){
        List<Car> temp = getCarsWithFuelAndTypeAndLocationAndGear(fuel,type,locationId,gear);
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

    public List<Car> getCarsWithFGLandDates(String fuel, String gear, int locationId,String dateFrom,String dateTo){
        List<Car> temp = getCarsWithFuelAndGearAndLocation(fuel,gear,locationId);
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

    public List<Car> getCarsWithFTandDates(String fuel, String type,String dateFrom,String dateTo){
        List<Car> temp = getCarsWithFuelAndType(fuel, type);
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

    public List<Car> getCarsWithFLandDates(String fuel, int locationId,String dateFrom,String dateTo){
        List<Car> temp = getCarsWithFuelAndLocationId(fuel, locationId);
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

    public List<Car> getCarsWithGTandDates(String gear, String type, String dateFrom,String dateTo){
        List<Car> temp = getCarsWithGearAndType(gear,type);
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


    public List<Car> getCarsWithFuelandDates(String fuel, String dateFrom,String dateTo){
        List<Car> temp = getCarsOfFuel(fuel);
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

    public List<Car> getCarsWithLocationandDates(int locationId,String dateFrom,String dateTo){
        List<Car> temp = getCarsFromLocation(locationId);
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
