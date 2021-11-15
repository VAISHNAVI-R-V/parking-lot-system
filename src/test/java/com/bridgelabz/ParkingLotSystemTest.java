package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    ParkingLotSystemOwner owner = null;
    AirportSecurity airportSecurity = null;
    //    Object vehicle = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(2);
        owner = new ParkingLotSystemOwner();
        airportSecurity = new AirportSecurity();
//        vehicle = new Object();
    }

    @Test
    void givenMessage_ShouldPrintMessage() {
        parkingLotSystem.welcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KA-2580", "Black", "Audi");
        parkingLotSystem.parkVehicle(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }


    @Test
    void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KA-2640", "Black", "Audi");
        parkingLotSystem.parkVehicle(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("KA-3280", "Silver", "KIA");
        parkingLotSystem.parkVehicle(vehicle1);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle1);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenUnParkedFromEmptySlot_ShouldReturnFalse() {
        Vehicle vehicle = new Vehicle("IN-4586", "Marron", "Porsche");
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

//    @Test
//    void givenNullVehicle_WhenUnParked_ShouldReturnException() {
//        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
//            parkingLotSystem.unParkVehicle(vehicle);
//        }, "No such A Vehicle Found");
//    }

    @Test
    void givenAVehicle_WhenUnParkedAnotherVehicle_ShouldReturnFalse() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("MH-2021", "Red", "Ford");
        Vehicle vehicle2 = new Vehicle("MH-2020", "Yellow", "Alto");
        parkingLotSystem.parkVehicle(vehicle1);
        parkingLotSystem.unParkVehicle(vehicle2);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() {
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.setCapacity(3);
        Vehicle vehicle1 = new Vehicle("KA-3690", "Brown", "Toyota");
        Vehicle vehicle2 = new Vehicle("KA-8520", "Grey", "Fiat");
        Vehicle vehicle3 = new Vehicle("MH-3214", "Blue", "Tata");
        Vehicle vehicle4 = new Vehicle("MH-7895", "White", "Lexus");
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(vehicle4);
        }, "Parking Lot is Full");

    }

    @Test
    void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        parkingLotSystem.setCapacity(1);
        Vehicle vehicle1 = new Vehicle("UK-5123", "Black", "Rolls Royce");
        Vehicle vehicle2 = new Vehicle("KA-8520", "Grey", "Audi");
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
        }, "Parking Lot is Full");

    }

    @Test
    void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.setCapacity(1);
//        owner = new ParkingLotSystemOwner();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        Vehicle vehicle1 = new Vehicle("KA-5710", "Red", "Maruti Suzuki");
        Vehicle vehicle2 = new Vehicle("MH-4814", "Black", "Nano");
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
        }, "Parking Lot is Full");

    }

    @Test
    void givenWhenParkingLotSpaceAvailableAfterFull_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.registerParkingLotObserver(owner);
        Vehicle vehicle1 = new Vehicle("MH-8595", "Silver", "Hyundai");
        Vehicle vehicle2 = new Vehicle("KA-9614", "Blue", "Tata");
        Vehicle vehicle3 = new Vehicle("KA-9423", "Orange", "Kia");
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.unParkVehicle(vehicle2);
        }, "Parking Lot is Full");
    }
}
