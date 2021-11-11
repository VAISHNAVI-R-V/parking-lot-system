package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
        vehicle = new Object();
    }

    @Test
    void givenMessage_ShouldPrintMessage() {
        parkingLotSystem.welcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle1 = new Vehicle("KA-2580", "Black", "Audi");
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
        Vehicle vehicle1 = new Vehicle("KA-2580", "Black", "Audi");
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkingLotSystemException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
            e.printStackTrace();
        }

    }

    @Test
    void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        Vehicle vehicle1 = new Vehicle("KA-3280", "Silver", "KIA");
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
            parkingLotSystem.unParkVehicle(vehicle);
        } catch (ParkingLotSystemException e) {
            Assertions.assertEquals("No such A Vehicle Found", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    void givenAVehicle_WhenUnParkedAnotherVehicle_ShouldReturnFalse() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assertions.assertFalse(isUnParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }
}
