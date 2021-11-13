package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    //    Object vehicle = null;
    Vehicle vehicle = null;
    ParkingLotSystemOwner owner = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(1);
        owner = new ParkingLotSystemOwner();
//        vehicle = new Object();
    }

    @Test
    void givenMessage_ShouldPrintMessage() {
        parkingLotSystem.welcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("KA-2580", "Black", "Audi");
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotSystemException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
            e.printStackTrace();
        }
    }


    @Test
    void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        Vehicle vehicle = new Vehicle("KA-2580", "Black", "Audi");
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
//            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkingLotSystemException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("KA-3280", "Silver", "KIA");
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenUnParkedFromEmptySlot_ShouldReturnFalse() {
        Vehicle vehicle = new Vehicle("IN-4586", "Marron", "Porsche");
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assertions.assertFalse(isUnParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    void givenNullVehicle_WhenUnParked_ShouldReturnException() {
        try {
            vehicle = null;
            parkingLotSystem.unParkVehicle(vehicle);
        } catch (ParkingLotSystemException e) {
            Assertions.assertEquals("No such A Vehicle Found", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenUnParkedAnotherVehicle_ShouldReturnFalse() {
        Vehicle vehicle1 = new Vehicle("MH-2021", "Red", "Ford");
        Vehicle vehicle2 = new Vehicle("MH-2020", "Yellow", "Toyato");
        try {
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.unParkVehicle(vehicle2);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assertions.assertFalse(isUnParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() {
//        ParkingLotSystemOwner owner = new ParkingLotSystemOwner();
        parkingLotSystem.registerOwner(owner);
        Vehicle vehicle1 = new Vehicle("KA-3690", "Brown","Toyota");
        Vehicle vehicle2 = new Vehicle("KA-8520", "Grey","Fiat");
        Vehicle vehicle3 = new Vehicle("MH-3214", "Blue","Tata");
        Vehicle vehicle4 = new Vehicle("MH-7895", "White","Lexus");
        try {
//            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(vehicle4);
//            boolean isCapacityFull = owner.getStatusIfCapacityFull();
        } catch (ParkingLotSystemException e) {
           boolean capacityFull = owner.isCapacityFull();
            Assertions.assertTrue(capacityFull);
            e.printStackTrace();
        }
    }
}
