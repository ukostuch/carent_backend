package com.ioproject.carent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private RentalService rentalService;
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> singleCar(int carId){
        return carRepository.findCarByCarId(carId);
    }

    public List<Car> getCarsFromLocation(int locationId){
        return carRepository.findCarsByLocationId(locationId);
    }

    public List<Car> getCarsWithGear(String gear){
        return carRepository.findCarsByGear(gear);
    }
    public List<Car> getCarsOfType(String type) {
        return carRepository.findCarsByType(type);
    }
    public List<Car> getCarsOfFuel(String fuel){
        return carRepository.findCarsByFuel(fuel);
    }
    public List<Car> getCarsWithGearAndFuel(String gear,String fuel) {
        return carRepository.findCarsByFuelAndGear(fuel,gear);
    }
    public List<Car> getCarsWithGearAndType(String gear,String type) {
        return carRepository.findCarsByTypeAndGear(type,gear);
    }
    public List<Car> getCarsWithGearAndLocation(String gear,int locationId) {
        return carRepository.findCarsByLocationIdAndGear(locationId,gear);
    }
    public List<Car> getCarsWithFuelAndLocationId(String fuel,int locationId) {
        return carRepository.findCarsByLocationIdAndFuel(locationId,fuel);
    }
    public List<Car> getCarsWithTypeAndLocationId(String type,int locationId) {
        return carRepository.findCarsByLocationIdAndType(locationId,type);
    }
    public List<Car> getCarsWithFuelAndType(String fuel,String type) {
        return carRepository.findCarsByTypeAndFuel(type,fuel);
    }
    public List<Car> getCarsWithFuelAndTypeAndGear(String fuel,String type,String gear) {
        return carRepository.findCarsByFuelAndTypeAndGear(fuel,type,gear);
    }
    public List<Car> getCarsWithFuelAndTypeAndLocation(String fuel,String type,int locationId) {
        return carRepository.findCarsByLocationIdAndFuelAndType(locationId,fuel,type);
    }
    public List<Car> getCarsWithFuelAndGearAndLocation(String fuel,String gear,int locationId) {
        return carRepository.findCarsByLocationIdAndFuelAndGear(locationId,fuel,gear);
    }
    public List<Car> getCarsWithGearAndTypeAndLocation(String gear,String type,int locationId) {
        return carRepository.findCarsByLocationIdAndTypeAndGear(locationId,type,gear);
    }
    public List<Car> getCarsWithFuelAndTypeAndLocationAndGear(String fuel,String type,int locationId,String gear) {
        return carRepository.findCarsByLocationIdAndFuelAndTypeAndGear(locationId,fuel,type,gear);
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
    }
