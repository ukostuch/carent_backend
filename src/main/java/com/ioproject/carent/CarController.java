package com.ioproject.carent;


import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.bulk.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/offer")
public class CarController {
    @Autowired
    private CarService carService;
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(){
        return new ResponseEntity<List<Car>>(carService.getAllCars(),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Car>> getCars(@RequestParam(required = false) String dateFrom, @RequestParam(required = false) String dateTo, @RequestParam(required = false) Integer locationId, @RequestParam(required = false) String gear, @RequestParam(required = false) String fuel, @RequestParam(required = false) String type, @RequestParam(required = false) String carBrand){
        List<Car> cars;
        if(dateFrom!=null & dateTo!=null && locationId!=null && carBrand != null && fuel != null && type != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithAllParameters(fuel,type,locationId,dateFrom,dateTo,carBrand),HttpStatus.OK);
        }
        else if (locationId != null && dateFrom == null && dateTo == null && gear == null && fuel == null && type == null && carBrand == null)  { //
            cars = carService.getCarsFromLocation(locationId);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        else if (locationId == null && dateFrom == null && dateTo == null && gear == null && fuel == null && type == null && carBrand != null)  { //
            cars = carService.getCarsOfCarBrand(carBrand);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        else if (locationId != null && dateFrom == null && dateTo == null && gear == null && fuel == null && type == null && carBrand != null)  {
            cars = carService.getCarsFromLocationAndCarBrand(locationId, carBrand);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        else if (locationId == null && dateFrom == null && dateTo == null && gear != null && fuel == null && type == null && carBrand == null) { //
            cars = carService.getCarsWithGear(gear);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        else if (locationId == null && dateFrom == null && dateTo == null && gear != null && fuel == null && type == null && carBrand != null) {
            cars = carService.getCarsWithGearAndCarBrand(gear, carBrand);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        else if (locationId == null && dateFrom == null && dateTo == null && gear == null && fuel != null && type == null && carBrand == null) {//
            cars = carService.getCarsOfFuel(fuel);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        else if (locationId == null && dateFrom == null && dateTo == null && gear == null && fuel != null && type == null && carBrand != null) {
            cars = carService.getCarsOfFuelAndCarBrand(fuel, carBrand);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        else if (locationId == null && dateFrom == null && dateTo == null && gear == null && fuel == null && type != null && carBrand == null) {//
            cars = carService.getCarsOfType(type);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }////////////////
        else if (locationId == null && dateFrom == null && dateTo == null && gear == null && fuel == null && type != null && carBrand != null) {
            cars = carService.getCarsOfTypeAndCarBrand(type, carBrand);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        else if(locationId!=null && gear != null && fuel != null && type != null && carBrand == null && dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndTypeAndLocationAndGear(fuel,type,locationId,gear),HttpStatus.OK);
        }
        else if(locationId!=null && gear != null && fuel != null && type != null && carBrand != null && dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndTypeAndLocationAndGearAndCarBrand(fuel,type,locationId,gear, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear == null && fuel != null && type != null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFTLandDates(fuel,type,locationId,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear == null && fuel != null && type != null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFTLandDatesAndCarBrand(fuel,type,locationId,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId!=null && gear == null && fuel != null && type != null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndTypeAndLocation(fuel,type,locationId),HttpStatus.OK);
        }
        else if(locationId!=null && gear == null && fuel != null && type != null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndTypeAndLocationAndCarBrand(fuel,type,locationId, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear != null && fuel == null && type != null && carBrand==null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGTLandDates(gear,type,locationId,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear != null && fuel == null && type != null && carBrand!=null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGTLandDatesAndCarBrand(gear,type,locationId,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId!=null && gear != null && fuel == null && type != null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearAndTypeAndLocation(gear,type,locationId),HttpStatus.OK);
        }
        else if(locationId!=null && gear != null && fuel == null && type != null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearAndTypeAndLocationAndCarBrand(gear,type,locationId, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear != null && fuel != null && type == null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFGLandDates(fuel,gear,locationId,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear != null && fuel != null && type == null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFGLandDatesAndCarBrand(fuel,gear,locationId,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId!=null && gear != null && fuel != null && type == null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndGearAndLocation(fuel,gear,locationId),HttpStatus.OK);
        }
        else if(locationId!=null && gear != null && fuel != null && type == null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndGearAndLocationAndCarBrand(fuel,gear,locationId, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear != null && fuel != null && type == null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGFandDates(gear,fuel,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear != null && fuel != null && type == null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGFandDatesAndCarBrand(gear,fuel,dateFrom,dateTo,carBrand),HttpStatus.OK);
        }
        else if(locationId==null && gear != null && fuel != null && type == null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearAndFuel(gear,fuel),HttpStatus.OK);
        }
        else if(locationId==null && gear != null && fuel != null && type == null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearAndFuelAndCarBrand(gear,fuel, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear != null && fuel != null && type != null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFTGandDates(fuel,type,gear,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear != null && fuel != null && type != null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFTGandDatesAndBrandCar(fuel,type,gear,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId==null && gear != null && fuel != null && type != null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndTypeAndGear(fuel,type,gear),HttpStatus.OK);
        }
        else if(locationId==null && gear != null && fuel != null && type != null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndTypeAndGearAndCarBrand(fuel,type,gear, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear == null && fuel == null && type != null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithTLandDates(type,locationId,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear == null && fuel == null && type != null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithTLandDatesAndCarBrand(type,locationId,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId!=null && gear == null && fuel == null && type != null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithTypeAndLocationId(type,locationId),HttpStatus.OK);
        }
        else if(locationId!=null && gear == null && fuel == null && type != null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithTypeAndLocationIdAndCarBrand(type,locationId, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear == null && fuel != null && type != null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFLandDates(fuel,locationId,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear == null && fuel != null && type != null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFLandDatesAndCarBrand(fuel,locationId,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId!=null && gear == null && fuel != null && type == null  && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndLocationId(fuel,locationId),HttpStatus.OK);
        }
        else if(locationId!=null && gear == null && fuel != null && type == null  && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndLocationIdAndCarBrand(fuel,locationId, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear != null && fuel == null && type == null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGLandDates(gear,locationId,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear != null && fuel == null && type == null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGLandDatesAndCarBrand(gear,locationId,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId!=null && gear != null && fuel == null && type == null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearAndLocation(gear,locationId),HttpStatus.OK);
        }
        else if(locationId!=null && gear != null && fuel == null && type == null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearAndLocationAndCarBrand(gear,locationId, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear == null && fuel != null && type != null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFTandDates(fuel,type,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear == null && fuel != null && type != null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFTandDatesAndCarBrand(fuel,type,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId==null && gear == null && fuel != null && type != null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndType(fuel,type),HttpStatus.OK);
        }
        else if(locationId==null && gear == null && fuel != null && type != null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelAndTypeAndCarBrand(fuel,type, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear != null && fuel == null && type != null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGTandDates(gear,type,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear != null && fuel == null && type != null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGTandDatesAndCarBrand(gear,type,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId==null && gear != null && fuel == null && type != null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearAndType(gear,type),HttpStatus.OK);
        }
        else if(locationId==null && gear != null && fuel == null && type != null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearAndTypeAndCarBrand(gear,type, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear != null && fuel != null && type == null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGFandDates(gear,fuel,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear != null && fuel != null && type == null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGFandDatesAndCarBrand(gear,fuel,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId==null && gear != null && fuel != null && type == null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearAndFuel(gear,fuel),HttpStatus.OK);
        }
        else if(locationId==null && gear != null && fuel != null && type == null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearAndFuelAndCarBrand(gear,fuel, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear == null && fuel == null && type == null && carBrand==null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithLocationandDates(locationId,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId!=null && gear == null && fuel == null && type == null && carBrand!=null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithLocationandDatesAndCarBrand(locationId,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId!=null && gear ==null && fuel == null && type == null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsFromLocation(locationId),HttpStatus.OK);
        }
        else if(locationId!=null && gear ==null && fuel == null && type == null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsFromLocationAndCarBrand(locationId, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear != null && fuel == null && type== null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearandDates(gear,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear != null && fuel == null && type== null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearandDatesAndCarBrand(gear,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId==null && gear !=null && fuel == null && type == null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithGear(gear),HttpStatus.OK);
        }
        else if(locationId==null && gear !=null && fuel == null && type == null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithGearAndCarBrand(gear, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId == null && gear == null && fuel != null && type == null && carBrand == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelandDates(fuel,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId == null && gear == null && fuel != null && type == null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithFuelandDatesAndCArBrand(fuel,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId==null && gear ==null && fuel != null && type == null && carBrand == null & dateFrom == null && dateTo == null){ //
            return new ResponseEntity<List<Car>>(carService.getCarsOfFuel(fuel),HttpStatus.OK);
        }
        else if(locationId==null && gear ==null && fuel != null && type == null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsOfFuelAndCarBrand(fuel, carBrand),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear == null && fuel == null && type != null && carBrand == null){//
            return new ResponseEntity<List<Car>>(carService.getCarsWithTypeandDates(type,dateFrom,dateTo),HttpStatus.OK);
        }
        else if(dateFrom!=null & dateTo!=null && locationId==null && gear == null && fuel == null && type != null && carBrand != null){
            return new ResponseEntity<List<Car>>(carService.getCarsWithTypeandDatesAndCarBrand(type,dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else if(locationId==null && gear ==null && fuel == null && type != null && carBrand == null & dateFrom == null && dateTo == null){//
            return new ResponseEntity<List<Car>>(carService.getCarsOfType(type),HttpStatus.OK);
        }
        else if(locationId==null && gear ==null && fuel == null && type != null && carBrand != null & dateFrom == null && dateTo == null){
            return new ResponseEntity<List<Car>>(carService.getCarsOfTypeAndCarBrand(type, carBrand),HttpStatus.OK);
        }
        else if(locationId==null && gear ==null && fuel == null && type == null && dateFrom!=null && dateTo!=null && carBrand == null){//
            return new ResponseEntity<>(carService.findAvailableCarsBetweenDates(dateFrom,dateTo),HttpStatus.OK);
        }
        else if(locationId==null && gear ==null && fuel == null && type == null && dateFrom!=null && dateTo!=null && carBrand != null){
            return new ResponseEntity<>(carService.findAvailableCarsBetweenDatesAndCarBrand(dateFrom,dateTo, carBrand),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<Car>>(carService.getAllCars(),HttpStatus.OK);
        }
    }


    @GetMapping("/{carId}")
    public ResponseEntity<Optional<Car>> getSingleCar(@PathVariable int carId){
        return new ResponseEntity<Optional<Car>>(carService.singleCar(carId),HttpStatus.OK);
    }

    @PatchMapping("/update/{id}/techcond")
    public ResponseEntity<UpdateResult> updateTechCondition(@PathVariable int id, @RequestBody Map<String,Double> updateRequest) {
        return new ResponseEntity<UpdateResult>(carService.updateTechCond(id,updateRequest.get("updateValue")),HttpStatus.OK);
    }
    @PatchMapping("/update/{id}/techdesc")
    public ResponseEntity<UpdateResult> updateTechDescription(@PathVariable int id, @RequestBody Map<String,String> updateRequest) {
        return new ResponseEntity<>(carService.updateTechDesc(id,updateRequest.get("updateValue")),HttpStatus.OK);
    }


}

